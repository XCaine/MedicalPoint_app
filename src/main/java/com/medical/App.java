package com.medical;

import com.medical.config.AppConfig;
import com.medical.domain.*;
import com.medical.service.CountryService;
import com.medical.service.FindMedicalPointService;
import com.medical.service.MedicalPointService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.List;

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

        FindMedicalPointService illnessService = (FindMedicalPointService) context.getBean("illnessService");





        List<MedicalPoint> xd = medService.findWithIllnessAndCity("Eye pain", "sochaczewski");

        for(MedicalPoint medPoint : xd)
        {
            System.out.println(medPoint.getName());
        }

        //medService.addMedicalUnit("Szpitalny Oddział ratunkowy", "medicalPoint,);

     /*   medService.addMedicalPointWithName("CENTRUM MEDYCZNE IM. BITWY WARSZAWSKIEJ 1920 R. W RADZYMINIE - SAMODZIELNY PUBLICZNY ZESPÓŁ " +
                "ZAKŁADÓW OPIEKI ZDROWOTNEJ");
        medService.addMedicalPointWithName("ZESPÓŁ OPIEKI ZDROWOTNEJ \"SZPITALA POWIATOWEGO\" W SOCHACZEWIE");
        medService.addMedicalPointWithName("SZPITAL WOLSKI IM. DR ANNY GOSTYŃSKIEJ SAMODZIELNY PUBLICZNY ZAKŁAD OPIEKI ZDROWOTNEJ");
        medService.addMedicalPointWithName("CENTRUM MEDYCZNE \"ŻELAZNA\" SPÓŁKA Z OGRANICZONĄ ODPOWIEDZIALNOŚCIĄ");
        medService.addMedicalPointWithName("SZPITAL SPECJALISTYCZNY IM. ŚWIĘTEJ RODZINY SAMODZIELNY PUBLICZNY ZAKŁAD OPIEKI ZDROWOTNEJ");
        medService.addMedicalPointWithName("SAMODZIELNY PUBLICZNY ZESPÓŁ ZAKŁADÓW OPIEKI ZDROWOTNEJ W WYSZKOWIE");
        medService.addMedicalPointWithName("SAMODZIELNY PUBLICZNY ZAKŁAD OPIEKI ZDROWOTNEJ - ZESPÓŁ ZAKŁADÓW");
        medService.addMedicalPointWithName("SAMODZIELNY PUBLICZNY ZESPÓŁ ZAKŁADÓW OPIEKI ZDROWOTNEJ W PRZASNYSZU");
        medService.addMedicalPointWithName("SAMODZIELNY PUBLICZNY ZESPÓŁ ZAKŁADÓW OPIEKI ZDROWOTNEJ W OSTROWI MAZOWIECKIEJ");
        medService.addMedicalPointWithName("SZPITAL DZIECIĘCY IM. PROF. DR. MED. JANA BOGDANOWICZA SAMODZIELNY PUBLICZNY ZAKŁAD OPIEKI " +
                "ZDROWOTNEJ");
        medService.addMedicalPointWithName("WARSZAWSKI SZPITAL DLA DZIECI SP ZOZ");
        medService.addMedicalPointWithName("MAZOWIECKI SZPITAL SPECJALISTYCZNY SPÓŁKA Z OGRANICZONĄ ODPOWIEDZIALNOŚCIĄ");
        medService.addMedicalPointWithName("SPECJALISTYCZNY SZPITAL WOJEWÓDZKI W CIECHANOWIE");
        medService.addMedicalPointWithName("RADOMSKI SZPITAL SPECJALISTYCZNY IM.DR TYTUSA CHAŁUBIŃSKIEGO");
        medService.addMedicalPointWithName("SZPITAL BIELAŃSKI IM.KS.JERZEGO POPIEŁUSZKI SAMODZIELNY PUBLICZNY ZAKŁAD OPIEKI ZDROWOTNEJ");
        medService.addMedicalPointWithName("WOJEWÓDZKI SZPITAL ZAKAŹNY W WARSZAWIE");
        medService.addMedicalPointWithName("MAZOWIECKIE CENTRUM REHABILITACJI STOCER SPÓŁKA Z OGRANICZONĄ ODPOWIEDZIALNOŚCIĄ");
        medService.addMedicalPointWithName("MAZOWIECKI SZPITAL WOJEWÓDZKI W SIEDLCACH SP. Z O.O");
        medService.addMedicalPointWithName("SAMODZIELNY PUBLICZNY ZESPÓŁ ZAKŁADÓW OPIEKI ZDROWOTNEJ W KOZIENICACH");
        medService.addMedicalPointWithName("MIĘDZYLESKI SZPITAL SPECJALISTYCZNY W WARSZAWIE");
        medService.addMedicalPointWithName("SZPITAL POWIATOWY W WOŁOMINIE - SAMODZIELNY ZESPÓŁ PUBLICZNYCH ZAKŁADÓW OPIEKI ZDROWOTNEJ");

*/


      //  medService.addMedicalPointWithName("Krakowski Szpital Specjalistyczny im. Jana Pawła II");

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


