package com.FamilyService.controllers;

import com.FamilyService.model.Family;
import com.FamilyService.services.FamilyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/families")
public class FamilyController {

    @Autowired
    private FamilyService familyService;
    @PostMapping
    public ResponseEntity<Family> saveFamilyMember(@RequestBody Family family){
        return new ResponseEntity<Family>(familyService.saveFamilyMembers(family), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Family>> getAllFamilyMembers(){
        return new ResponseEntity<>( familyService.getAllFamilyMembers(),HttpStatus.OK);
    }
    @GetMapping("/parivaar/{ageId}")
    public ResponseEntity<Family> getFamilyMemberByAge(@PathVariable("ageId") int age){
        return new ResponseEntity<>(familyService.getFamilyMembersByAge(age),HttpStatus.OK);
    }
    @PutMapping("/family/{ageIdd}")
    public ResponseEntity<Family> updateFamilyMember(@PathVariable("ageIdd") int age, @RequestBody Family family){
        return new ResponseEntity<Family>(familyService.updateFamilyMembers(age, family),HttpStatus.CREATED);
    }
    @DeleteMapping("/fam/{ageIddd}")
    public ResponseEntity<String> deleteFamilyMembers(@PathVariable("ageIddd") int age){
        familyService.deleteFamilyMembers(age);
        return new ResponseEntity<String>("Family member deleted successfully",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/sinni/ashu/{name}")
    public ResponseEntity<List<Family>> getFamilyMemberByName(@PathVariable String name){
        return new ResponseEntity<List<Family>>(familyService.getByName(name),HttpStatus.OK);
    }
    @GetMapping("/jigal")
    public ResponseEntity<List<Family>> getFamilyByName(@RequestParam(value = "name",required = false) String name){
        return new ResponseEntity<List<Family>>(name!=null?familyService.getByName(name):familyService.getAllFamilyMembers()
                ,HttpStatus.OK);
    }
    @GetMapping("/bySalary")
    public ResponseEntity<List<Family>> findFamilyBySalary(@RequestParam int salary){
        return new ResponseEntity<>(familyService.findSalaryUsingSql(salary),HttpStatus.OK);
    }
    @GetMapping("/salaries")
    public ResponseEntity<List<Family>> findSalary(@RequestParam int salary){
        return new ResponseEntity<List<Family>>(familyService.findBySalary(salary),HttpStatus.OK);
    }
    @GetMapping("/byAge/{minAge}/{maxAge}")
    public ResponseEntity<List<Family>> filterByAge(@PathVariable int minAge,@PathVariable int maxAge){
        return new ResponseEntity<>(familyService.findByAgeInBetween(minAge,maxAge),HttpStatus.OK);
    }
    @GetMapping("/avg")
    public ResponseEntity<Optional<Double>> avgSalary(){
        return new ResponseEntity<Optional<Double>>(familyService.averageSalary(),HttpStatus.OK);
    }
    @GetMapping("/sort")
    public ResponseEntity<List<Family>> findFamilyBySorting(@RequestParam String field){
        return new ResponseEntity<List<Family>>(familyService.findFamilyWithSorting(field),HttpStatus.OK);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<Family>> findFamilyWithPagination(@RequestParam int offset, @RequestParam int pageSize){
        return new ResponseEntity<Page<Family>>(familyService.findFamilyByPagination(offset,pageSize),HttpStatus.OK);
    }
    @GetMapping("/pageAndSort")
    public ResponseEntity<Page<Family>> findFamilyWithPaginationAndSorting(@RequestParam int offset, @RequestParam int pageSize, String field){
        return new ResponseEntity<Page<Family>>(familyService.findFamilyByPaginationAndSorting(offset,pageSize,field),HttpStatus.OK);
    }
}
