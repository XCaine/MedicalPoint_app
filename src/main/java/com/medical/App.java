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
public class App
{
    public static void main(String[] args) throws Exception {

        /*
        SessionFactory sessFact = HibernateUtil.getSessionFactory();
        Session session = sessFact.getCurrentSession();
        org.hibernate.Transaction tr = session.beginTransaction();
        Country emp = new Country();
        emp.setId(0);
        emp.setName("Eeeexss12d");
        Province province = new Province();
        province.setId(0);
        province.setName("EEEsE2s23d5");
        City city = new City();
        city.setId(0);
        city.setName("ebess2d3");
        emp.addProvinces(province);
        province.addCities(city);

        session.save(city);
        session.save(emp);
        session.save(province);

        tr.commit();
        System.out.println("Successfully inserted");
        sessFact.close();

    */

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CountryService service = (CountryService) context.getBean("countryService");

        MedicalPointService medService = (MedicalPointService) context.getBean("medicalPointService");

        IllnessService illnessService = (IllnessService) context.getBean("illnessService");


        Country country = service.find(5);
        System.out.println(country.getName());

        Illness illness = illnessService.findByName("Eye pain");
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



        service.findByName("Poland");



    //    CountryDaoImpl countryDao = new CountryDaoImpl();

      // countryDao.findByName("Poland");

       // medService.addMedicalPointWithName("SPZOZ Uniwersytecki Szpital Kliniczny nr 1 im. Norberta Barlickiego");


/*
        genericService.save(new Specialty("Allergology"));
        genericService.save(new Specialty("Anaesthesiology"));
        genericService.save(new Specialty("Cardiology"));
        genericService.save(new Specialty("Cardiothoracic Surgery"));
        genericService.save(new Specialty("Child and Adolescent Psychiatry"));
        genericService.save(new Specialty("Clinical Genetics"));
        genericService.save(new Specialty("Clinical Neurophysiology"));
        genericService.save(new Specialty("Dermatology and Venereology"));
        genericService.save(new Specialty("Emergency Medicine"));
        genericService.save(new Specialty("Endocrinology"));
        genericService.save(new Specialty("Gastroenterology"));
        genericService.save(new Specialty("Geriatrics"));
        genericService.save(new Specialty("Gynaecology and Obstetrics"));
        genericService.save(new Specialty("Infectious Diseases"));
        genericService.save(new Specialty("Internal Medicine"));
        genericService.save(new Specialty("Laboratory Medicine / Medical Biopathology"));
        genericService.save(new Specialty("Medical Microbiology"));
        genericService.save(new Specialty("Medical Oncology"));
        genericService.save(new Specialty("Nephrology"));
        genericService.save(new Specialty("Neurology"));
        genericService.save(new Specialty("Neurosurgery"));
        genericService.save(new Specialty("Nuclear Medicine"));
        genericService.save(new Specialty("Occupational Medicine"));
        genericService.save(new Specialty("Ophthalmology"));
        genericService.save(new Specialty("Oro-Maxillo-Facial Surgery"));
        genericService.save(new Specialty("Orthopaedics"));
        genericService.save(new Specialty("Otorhinolaryngology"));
        genericService.save (new Specialty("Paediatric Surgery"));
        genericService.save(new Specialty("Paediatrics"));
        genericService.save(new Specialty("Pathology"));
        genericService.save(new Specialty("Pharmacology"));
        genericService.save(new Specialty("Physical Medicine and Rehabilitation"));
        genericService.save(new Specialty("Plastic, Reconstructive and Aesthetic Surgery"));
        genericService.save(new Specialty("Pneumology"));
        genericService.save(new Specialty("Psychiatry"));
        genericService.save(new Specialty("Public Health Medicine"));
        genericService.save(new Specialty("Radiology"));
        genericService.save(new Specialty("Radiation Oncology and Radiotherapy"));
        genericService.save(new Specialty("Rheumatology"));
        genericService.save(new Specialty("Surgery"));
        genericService.save(new Specialty("Thoracic Surgery"));
        genericService.save(new Specialty("Urology"));
        genericService.save(new Specialty("Vascular Surgery"));
*/
       /* Address address = new Address();
        address.setPostalCode("01-809");
        address.setStreetName("Cegłowska");
        address.setStreetNumber("80");

        MedicalPoint medPoint = new MedicalPoint();

       medPoint.setId(0);
       medPoint.setName("SzpitalA");
       medPoint.setAddress(address);

       medService.add(medPoint);
*/


    }
}
