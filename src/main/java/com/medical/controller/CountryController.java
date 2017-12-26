package com.medical.controller;

import com.medical.domain.Country;
import com.medical.service.CountryService;
import com.medical.service.MedicalPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;
    @Autowired
    private MedicalPointService medicalPointService;

    // Add new Country
    @PostMapping("/country")
    public ResponseEntity<?> save(@RequestBody Country country) {
        countryService.add(country);
        return ResponseEntity.ok().body("New Country has been saved.");
    }

    // Get Country by id
    @GetMapping("/country/{id}")
    public ResponseEntity<Country> get(@PathVariable("id") Integer id) {
        Country country = countryService.find(id);
        return ResponseEntity.ok().body(country);
    }



    // Get all Countries
    @GetMapping("/country")
    public ResponseEntity<List<Country>> list() {
        List<Country> books = countryService.findAll();
        return ResponseEntity.ok().body(books);
    }

    // Update Country
    @PutMapping("/country")
    public ResponseEntity<?> update(@RequestBody Country country) {
        countryService.update(country);
        return ResponseEntity.ok().body("Country has been updated.");
    }

    // Delete Country
    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> delete(@RequestBody Country country) {
        countryService.remove(country);
        return ResponseEntity.ok().body("Country has been deleted.");
    }
}