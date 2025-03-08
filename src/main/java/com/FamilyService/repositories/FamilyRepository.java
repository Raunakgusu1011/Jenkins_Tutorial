package com.FamilyService.repositories;

import com.FamilyService.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family,Integer> {
    List<Family> findByName(String name);


    @Query(value = "select * from family where salary>?1",nativeQuery = true)
    List<Family> findSalaryBySql(double salary);
//                             or
    List<Family> findBySalaryGreaterThan(int salary);

    List<Family> findByAgeBetween(int min, int max);
    @Query(value = "select avg(salary) from family",nativeQuery = true)
    Optional<Double> avgSalary();


}
