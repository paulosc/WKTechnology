package com.psc.wktest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;
    private String cpf;
    private String rg;
    private LocalDate dateOfBirth;
    private String gender;
    private String mother;
    private String father;
    private String zipCode;
    private String address;
    private int number;
    private String neighborhood;
    private String city;
    private String state;
    private String landline;
    private String mobile;
    private double height;
    private int weight;
    private String bloodType;

    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(this.dateOfBirth, currentDate);
        return period.getYears();
    }
}
