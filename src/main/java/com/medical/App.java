package com.medical;

import com.medical.config.AppConfig;
import com.medical.domain.*;
import com.medical.service.CountryService;
import com.medical.service.MedicalPointService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * Hello world!
 *
 */

@Controller
public class App
{
    public static void main(String[] args) throws Exception {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CountryService countryService = (CountryService) context.getBean("countryService");

        MedicalPointService medService = (MedicalPointService) context.getBean("medicalPointService");

/*
    MedicalPoint medicalPoint = new MedicalPoint();
    medicalPoint.setName("Szpital2");
    medicalPoint.setPhoneNumber("123123123");



    MedicalUnitType medicalUnitType = new MedicalUnitType();
    medicalUnitType.setName("SOR");

    Country country = new Country();
    country.setName("Niemcy");

    medicalUnitType.setCountry(country);

    countryService.add(country);
    medService.add(medicalPoint);
  */

    MedicalPoint med = new MedicalPoint();

    med = medService.findByName("Szpital2");
    System.out.println(med.getPhoneNumber());



    }
}
