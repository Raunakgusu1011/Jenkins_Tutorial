package com.FamilyService.repositories;

import com.FamilyService.model.Family;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class FamilyRepositoryTest {
    @Autowired
    private FamilyRepository familyRepository;
    Family family;

    @BeforeEach
    void setUp() {
        family = new Family(32,"Tabrez","Male",77000);
        familyRepository.save(family);
    }


    @AfterEach
    void tearDown() {
        family = null;
        familyRepository.deleteAll();
    }

    @Test
    void findByName() {
        List<Family> familyList = familyRepository.findByName("Tabrez");
        Assertions.assertThat(familyList.get(0).getAge()).isEqualTo(family.getAge());
    }

    @Test
    void findSalaryBySql() {
        List<Family> salaryList = familyRepository.findSalaryBySql(70000);
        Assertions.assertThat(salaryList.get(0).getName()).isEqualTo(family.getName());
    }

    @Test
    void findByAgeBetween() {
        List<Family> ageBetween = familyRepository.findByAgeBetween(31, 32);
        Assertions.assertThat(ageBetween.get(0).getName()).isEqualTo(family.getName());
    }

    @Test
    void avgSalary() {
        Optional<Double> avg = familyRepository.avgSalary();
        assertEquals(avg,Optional.of(77000.00));
    }
}