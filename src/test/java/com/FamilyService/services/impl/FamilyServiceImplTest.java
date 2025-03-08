package com.FamilyService.services.impl;

import com.FamilyService.model.Family;
import com.FamilyService.repositories.FamilyRepository;
import com.FamilyService.services.FamilyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FamilyServiceImplTest {
    @Mock
    FamilyRepository familyRepository;
    FamilyService familyService;
    Family family;
    AutoCloseable autoCloseable;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        familyService = new FamilyServiceImpl(familyRepository);
        family = new Family(32,"Tabrez","Male",770000);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void saveFamilyMembers() {
        mock(Family.class);
        mock(FamilyRepository.class);
        when(familyRepository.save(family)).thenReturn(family);
        Assertions.assertThat(familyService.saveFamilyMembers(family)).isEqualTo(family);
    }

    @Test
    void getAllFamilyMembers() {
        mock(Family.class);
        mock(FamilyRepository.class);
        when(familyRepository.findAll()).thenReturn(new ArrayList<>(Collections.singleton(family)));
        Assertions.assertThat(familyService.getAllFamilyMembers().get(0).getName()).isEqualTo(family.getName());
    }

    @Test
    void getFamilyMembersByAge() {
        mock(Family.class);
        mock(FamilyRepository.class);
        when(familyRepository.findById(32)).thenReturn(Optional.ofNullable(family));
        Assertions.assertThat(familyService.getFamilyMembersByAge(32)).isEqualTo(family);
    }

    @Test
    void updateFamilyMembers() {
        mock(Family.class);
        mock(FamilyRepository.class);
        when(familyRepository.findById(32)).thenReturn(Optional.ofNullable(family));
        when(familyRepository.save(family)).thenReturn(family);
        Assertions.assertThat(familyService.updateFamilyMembers(32,family)).isEqualTo(family);
    }

    @Test
    void deleteFamilyMembers() {
        mock(Family.class);
        mock(FamilyRepository.class);
        when(familyRepository.findById(32)).thenReturn(Optional.ofNullable(family));
        doNothing().when(familyRepository).delete(any(Family.class));
        Assertions.assertThat(familyService.deleteFamilyMembers(32)).isEqualTo("Deleted");
    }

    @Test
    void getByName() {
        mock(Family.class);
        mock(FamilyRepository.class);
        when(familyRepository.findByName("Tabrez")).thenReturn(new ArrayList<>(Collections.singleton(family)));
        Assertions.assertThat(familyService.getByName("Tabrez").get(0).getAge()).isEqualTo(family.getAge());
    }
}