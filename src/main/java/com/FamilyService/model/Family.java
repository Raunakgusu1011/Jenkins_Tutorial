package com.FamilyService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "FAMILY")
public class Family {
    @Id
    private int age;
    private String name;
    private String gender;
    private int salary;
}
