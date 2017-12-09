package com.medical;

import com.medical.config.AppConfig;
import com.medical.dao.CityDao;
import com.medical.dao.CityDaoImpl;
import com.medical.dao.CountryDaoImpl;
import com.medical.dao.GenericDao;
import com.medical.domain.*;
import com.medical.service.CountryService;
import com.medical.service.GenericService;
import com.medical.service.IllnessService;
import com.medical.service.MedicalPointService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import com.medical.googleAPI.GoogleMapsAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */

@Controller
public class App {
    public static void main(String[] args) throws Exception {




        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CountryService service = (CountryService) context.getBean("countryService");

        MedicalPointService medService = (MedicalPointService) context.getBean("medicalPointService");

        IllnessService illnessService = (IllnessService) context.getBean("illnessService");



        medService.addMedicalPointWithName("Krakowski Szpital Specjalistyczny im. Jana Pawła II");

     /*   Illness illness = illnessService.findByName("Eye pain");
        System.out.println("halo1");
        Set<Specialty> specialties = illness.getSpecialties();
        System.out.println("halo2");
        List<MedicalUnit> allmedUnits = new ArrayList<MedicalUnit>();
        for(Specialty specialty : specialties)
        {
            Set<MedicalUnit> medicalUnits= specialty.getMedicalUnits();
            allmedUnits.addAll(medicalUnits);
            System.out.println(specialty.getName());
        }
        List<MedicalPoint> allmedPoints = new ArrayList<MedicalPoint>();
        for(MedicalUnit medicalUnit: allmedUnits)
        {
            allmedPoints.add(medicalUnit.getMedicalPoint());
            System.out.println(medicalUnit.getName());
        }
        System.out.println(allmedPoints.get(0).getName());
       // for(Medical)


        List<MedicalPoint> xd = illnessService.getMedicalPoints("Eye pain");


        System.out.println(xd.size());


        System.out.println(xd.get(0).getAddressLine());
*/

       /* if (service.findByName("Polannd") == null) {
            System.out.println("ni ma");
        }*/





            //    CountryDaoImpl countryDao = new CountryDaoImpl();

            // countryDao.findByName("Poland");

            // medService.addMedicalPointWithName("SPZOZ Uniwersytecki Szpital Kliniczny nr 1 im. Norberta Barlickiego");

    }

        }


