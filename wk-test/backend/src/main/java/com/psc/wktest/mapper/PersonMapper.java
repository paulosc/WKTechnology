package com.psc.wktest.mapper;

import com.psc.wktest.entities.Person;
import com.psc.wktest.model.PersonDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonMapper {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static Person convertToPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getNome());
        person.setCpf(personDTO.getCpf());
        person.setRg(personDTO.getRg());
        person.setDateOfBirth(LocalDate.parse(personDTO.getData_nasc(), formatter));
        person.setGender(personDTO.getSexo());
        person.setMother(personDTO.getMae());
        person.setFather(personDTO.getPai());
        person.setEmail(personDTO.getEmail());
        person.setZipCode(personDTO.getCep());
        person.setAddress(personDTO.getEndereco());
        person.setNumber(personDTO.getNumero());
        person.setNeighborhood(personDTO.getBairro());
        person.setCity(personDTO.getCidade());
        person.setState(personDTO.getEstado());
        person.setLandline(personDTO.getTelefone_fixo());
        person.setMobile(personDTO.getCelular());
        person.setHeight(personDTO.getAltura());
        person.setWeight(personDTO.getPeso());
        person.setBloodType(personDTO.getTipo_sanguineo());
        return person;
    }
}
