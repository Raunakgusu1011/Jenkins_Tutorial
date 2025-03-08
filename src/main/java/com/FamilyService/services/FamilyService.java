package com.FamilyService.services;

import com.FamilyService.model.Family;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface FamilyService {
    Family saveFamilyMembers(Family family);
    List<Family> getAllFamilyMembers();
    Family getFamilyMembersByAge(int age);
    Family updateFamilyMembers(int age, Family family);
    String deleteFamilyMembers(int age);
    List<Family> getByName(String name);

    List<Family> findSalaryUsingSql(int salary);

    List<Family> findBySalary(int salary);

    List<Family> findByAgeInBetween(int minAge, int maxAge);

    Optional<Double> averageSalary();

    List<Family> findFamilyWithSorting(String field);

    Page<Family> findFamilyByPagination(int pageNumber, int pageSize );

    Page<Family> findFamilyByPaginationAndSorting(int pageNumber, int pageSize, String field);
}
