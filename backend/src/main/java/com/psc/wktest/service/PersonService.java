package com.psc.wktest.service;

import com.psc.wktest.entities.BloodType;
import com.psc.wktest.entities.Person;
import com.psc.wktest.model.ImcByAge;
import com.psc.wktest.model.MediaAge;
import com.psc.wktest.model.PercentageOfObese;
import com.psc.wktest.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private static final String MAN = "Masculino";
    private static final String WOMAN = "Feminino";

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Object[]> countPersonsByState() {
        return personRepository.countPersonsByState();
    }

    public List<ImcByAge> imcByAge() {
        int interval = 10;
        int start = 0;
        int end = 10;
        List<ImcByAge> imcByAges = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            List<Person> personList = personRepository.findPersonsInAgeRange(start, end);
            if(personList.size() > 0) {
                double totalImc = personList.stream()
                        .mapToDouble(person -> calculateBMI(person))
                        .sum();

                ImcByAge imcByAge = new ImcByAge();
                imcByAge.setStart(start);
                imcByAge.setEnd(end);
                imcByAge.setImc(decimalFormat.format(totalImc / personList.size()));

                imcByAges.add(imcByAge);
            }
            start += interval;
            end += interval;
        }

        return imcByAges;
    }

    public double calculateBMI(Person person) {
        return person.getWeight() / (person.getHeight() * person.getHeight());
    }

    public List<PercentageOfObese> percentageOfObese() {
        List<PercentageOfObese> percentageOfObeseList = new ArrayList<>();

        List<Person> malePersons = personRepository.findPersonsByGender(MAN);
        List<Person> femalePersons = personRepository.findPersonsByGender(WOMAN);

        if(!malePersons.isEmpty()) {
            long countMaleObese = countObesePersons(malePersons);
            String percentageMale = calculatePercentage(countMaleObese, malePersons.size());
            PercentageOfObese percentageOfObeseMan = new PercentageOfObese(MAN, percentageMale);
            percentageOfObeseList.add(percentageOfObeseMan);
        }

        if(!femalePersons.isEmpty()) {
            long countFemaleObese = countObesePersons(femalePersons);
            String percentageFemale = calculatePercentage(countFemaleObese, femalePersons.size());
            PercentageOfObese percentageOfObeseWoman = new PercentageOfObese(WOMAN, percentageFemale);
            percentageOfObeseList.add(percentageOfObeseWoman);
        }

        return percentageOfObeseList;
    }

    private long countObesePersons(List<Person> persons) {
        return persons.stream()
                .filter(person -> calculateBMI(person) > 30)
                .count();
    }

    private String calculatePercentage(long countObese, int totalCount) {
        double percentage = (countObese / (double) totalCount) * 100;
        return decimalFormat.format(percentage);
    }

    public List<MediaAge> mediaAge() {
        return Arrays.stream(BloodType.values())
            .map(bloodType -> {
                List<Person> personList = personRepository.findPersonsByBloodType(bloodType.getValue());

                if (!personList.isEmpty()) {
                    int totalAge = personList.stream()
                            .mapToInt(Person::getAge)
                            .sum();

                    int averageAge = totalAge / personList.size();

                    return new MediaAge(averageAge, bloodType.getValue());
                } else {
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    public Map<String, Integer> possibleDonors() {
        List<Person> personList = personRepository.findAll();
        if (personList.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, Integer> donorsByBloodType = initializeBloodTypeMap();

        for (Person person : personList) {
            if (isEligibleDonor(person)) {
                String donorBloodType = person.getBloodType();
                for (String recipientBloodType : donorsByBloodType.keySet()) {
                    if (isCompatible(donorBloodType, recipientBloodType)) {
                        donorsByBloodType.put(recipientBloodType, donorsByBloodType.get(recipientBloodType) + 1);
                    }
                }
            }
        }

        return donorsByBloodType;
    }

    private boolean isEligibleDonor(Person person) {
        int idade = person.getAge();
        double peso = person.getWeight();
        return idade >= 16 && idade <= 69 && peso > 50;
    }

    public static Map<String, Integer> initializeBloodTypeMap() {
        return Arrays.stream(BloodType.values()).collect(Collectors.toMap(BloodType::getValue, bloodType -> 0));
    }

    public static boolean isCompatible(String donorBloodType, String recipientBloodType) {
        switch (recipientBloodType) {
            case "A+":
                return donorBloodType.equals("A+") || donorBloodType.equals("A-") || donorBloodType.equals("O+") || donorBloodType.equals("O-");
            case "A-":
                return donorBloodType.equals("A-") || donorBloodType.equals("O-");
            case "B+":
                return donorBloodType.equals("B+") || donorBloodType.equals("B-") || donorBloodType.equals("O+") || donorBloodType.equals("O-");
            case "B-":
                return donorBloodType.equals("B-") || donorBloodType.equals("O-");
            case "AB+":
                return true;
            case "AB-":
                return donorBloodType.equals("A-") || donorBloodType.equals("B-") || donorBloodType.equals("AB-") || donorBloodType.equals("O-");
            case "O+":
                return donorBloodType.equals("O+") || donorBloodType.equals("O-");
            case "O-":
                return donorBloodType.equals("O-");
            default:
                return false;
        }
    }
}
