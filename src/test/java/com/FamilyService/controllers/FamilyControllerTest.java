package com.FamilyService.controllers;

import com.FamilyService.model.Family;
import com.FamilyService.services.FamilyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FamilyController.class)
class FamilyControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    FamilyService familyService;

    Family family1;
    Family family2;

    List<Family> familyList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        family1 = new Family(32,"Tabrez","Male",77000);
        family2 = new Family(30,"Jawed","Male",100000);
        familyList.add(family1);
        familyList.add(family2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveFamilyMember() throws Exception {
        when(familyService.saveFamilyMembers(family1)).thenReturn(family1);
        mockMvc.perform(post("/families")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(family1)))
                .andExpect(status().isCreated());

    }

    @Test
    void getAllFamilyMembers() {
    }

    @Test
    void getFamilyMemberByAge() {
    }

    @Test
    void updateFamilyMember() {
    }

    @Test
    void deleteFamilyMembers() throws Exception {
        when(familyService.deleteFamilyMembers(32)).thenReturn("Deleted");
        mockMvc.perform(delete("/families/")).andDo(print());
    }

    @Test
    void getFamilyMemberByName() {
    }
}