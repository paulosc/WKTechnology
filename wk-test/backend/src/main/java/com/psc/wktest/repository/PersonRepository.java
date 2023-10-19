package com.psc.wktest.repository;

import com.psc.wktest.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p.state, COUNT(p) FROM Person p GROUP BY p.state")
    List<Object[]> countPersonsByState();

    @Query("SELECT p FROM Person p WHERE YEAR(CURRENT_DATE) - YEAR(p.dateOfBirth) BETWEEN :minAge AND :maxAge")
    List<Person> findPersonsInAgeRange(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

    @Query("SELECT p FROM Person p WHERE p.gender = :gender")
    List<Person> findPersonsByGender(@Param("gender") String gender);

    @Query("SELECT p FROM Person p WHERE p.bloodType = :bloodType")
    List<Person> findPersonsByBloodType(@Param("bloodType") String bloodType);
}
