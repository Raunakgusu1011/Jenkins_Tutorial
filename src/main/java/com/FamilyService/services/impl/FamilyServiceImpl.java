package com.FamilyService.services.impl;

import com.FamilyService.exceptions.FamilyAlreadyExisting;
import com.FamilyService.exceptions.MemberNotFound;
import com.FamilyService.model.Family;
import com.FamilyService.repositories.FamilyRepository;
import com.FamilyService.services.FamilyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    public FamilyServiceImpl(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    @Override
    public Family saveFamilyMembers(Family family) {
        Family existingFamily = familyRepository.findById(family.getAge()).orElse(null);
        if (existingFamily==null) return familyRepository.save(family);
        else throw new FamilyAlreadyExisting();
    }

    @Override
    public List<Family> getAllFamilyMembers() {
        return familyRepository.findAll();
    }

    @Override
    public Family getFamilyMembersByAge(int age) {
       return familyRepository.findById(age).orElseThrow(() -> new MemberNotFound());
    }

    @Override
    public Family updateFamilyMembers(int age, Family family) {
        Family family1 = familyRepository.findById(age).orElseThrow(() -> new MemberNotFound());
        family1.setAge(family.getAge());
        family1.setName(family.getName());
        family1.setGender(family.getGender());
        family1.setSalary(family.getSalary());
        return familyRepository.save(family1);
    }

    @Override
    public String deleteFamilyMembers(int age) {
        familyRepository.findById(age).orElseThrow(()->new MemberNotFound());
        familyRepository.deleteById(age);
        return "Deleted";
    }

    @Override
    public List<Family> getByName(String name) {
        return familyRepository.findByName(name);
    }

    @Override
    public List<Family> findSalaryUsingSql(int salary) {
        return familyRepository.findSalaryBySql(salary);
    }

    @Override
    public List<Family> findBySalary(int salary) {
        return familyRepository.findBySalaryGreaterThan(salary);
    }

    @Override
    public List<Family> findByAgeInBetween(int minAge,int maxAge) {
        return familyRepository.findByAgeBetween(minAge,maxAge);
    }

    @Override
    public Optional<Double> averageSalary() {
        return familyRepository.avgSalary();
    }

    @Override
    public List<Family> findFamilyWithSorting(String field) {
        return familyRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Family> findFamilyByPagination(int offset, int pageSize){
        return familyRepository.findAll(PageRequest.of(offset,pageSize));
    }

    public Page<Family> findFamilyByPaginationAndSorting(int offset, int pageSize, String field){
        return familyRepository.findAll(PageRequest.of(offset,pageSize,Sort.by(Sort.Direction.ASC,field)));
    }
}
