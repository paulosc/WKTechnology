package com.psc.wktest.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psc.wktest.entities.Person;
import com.psc.wktest.mapper.PersonMapper;
import com.psc.wktest.model.PersonDTO;
import com.psc.wktest.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FileService {

    private final PersonRepository personRepository;
    public String process(MultipartFile multipartFile) throws IOException {
        List<PersonDTO> personDTOList = convertMultipartFileToArrayList(multipartFile);
        return saveAll(personDTOList);
    }

    private String saveAll(List<PersonDTO> personDTOList) {
        String result = "";
        int countSuccess = 0;
        int contError = 0;
        for(PersonDTO personDTO : personDTOList) {
            Person person = PersonMapper.convertToPerson(personDTO);
            try {
                personRepository.save(person);
                countSuccess++;
            } catch (DataIntegrityViolationException e) {
                contError++;
                result += e.getMessage() + "\n";
            }
        }

        if(result.isBlank()) {
            result = "Todos os registros foram processados sem falhas";
        } else {
            result = "Registros processados com sucesso: " + countSuccess + ", registros processados com falha: " + contError + ", realatorio de falhas: " + result;
        }

        return result;
    }

    private List<PersonDTO> convertMultipartFileToArrayList(MultipartFile multipartFile) throws IOException {
        byte[] fileBytes = multipartFile.getBytes();
        String fileContent = new String(fileBytes, StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<PersonDTO> personDTOList = objectMapper.readValue(fileContent, objectMapper.getTypeFactory().constructCollectionType(List.class, PersonDTO.class));

        return personDTOList;
    }
}
