package com.medical.service;

import com.medical.dao.IllnessDao;
import com.medical.domain.Illness;
import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnit;
import com.medical.domain.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.Math.*;

@Service("illnessService")
public class FindByIllnessImpl extends GenericServiceImpl<Illness> implements FindByIllness {

    @Autowired
    IllnessDao illnessDao;

    /*@Autowired
    FindByIllnessImpl(IllnessDao illnessDao){
        super(illnessDao);
    }*/

    /**
     * Finding all medical points found by specific illness name
     * @param illnessName given illnessName to find specific medical points
     * @return list of medical points found by specific illness name
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<MedicalPoint> getMedicalPoints(String illnessName) {
        Illness illness = illnessDao.findByName(illnessName);
        List<MedicalPoint> medicalPoints = new ArrayList<MedicalPoint>();
        List<MedicalUnit> medicalUnits = new ArrayList<MedicalUnit>();
        Set<Specialty> specialties = illness.getSpecialties();

        for(Specialty specialty : specialties)
            medicalUnits.addAll(specialty.getMedicalUnits());

        for(MedicalUnit medicalUnit : medicalUnits)
            medicalPoints.add(medicalUnit.getMedicalPoint());

        return medicalPoints;
    }

    /**
     * Finding nearest medical point for specific illness
     * @param latitude given latitude
     * @param longitude given longitude
     * @param illnessName given illnessName to find specific medical points
     * @return nearest medical point
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public MedicalPoint getNearestMedicalPoint(double latitude, double longitude, String illnessName) {

        List<MedicalPoint> medicalPoints = getMedicalPoints(illnessName);
        double shortestDistance = 1000000.0 ;
        double distance;
        MedicalPoint medicalPoint = null;

        for(MedicalPoint medPoint : medicalPoints)
        {
            distance = getDistanceHeversine(latitude, longitude, medPoint.getCoordinates().getLatitude(), medPoint.getCoordinates().getLongitude());

            if(shortestDistance > distance)
            {
                shortestDistance = distance;
                medicalPoint = medPoint;
            }
        }

        System.out.println(shortestDistance);

        return medicalPoint;
    }

    /**
     * Calculating distance using simpler calculations than using Heversine formula
     * @param latitude1 first latitude
     * @param longitude1 first longitude
     * @param latitude2 second latitude
     * @param longitude2 second longitude
     * @return distance between two locations
     */
    private double getDistance( double latitude1, double longitude1, double latitude2, double longitude2)
    {
        double distance = sqrt(pow(latitude2 - latitude1, 2) + pow(longitude2 - longitude1, 2));

        return  distance;
    }

    /**
     * Calculating distance using Heversine formula
     * @param latitude1 first latitude
     * @param longitude1 first longitude
     * @param latitude2 second latitude
     * @param longitude2 second longitude
     * @return distance between two locations
     */
    private double getDistanceHeversine(double latitude1, double longitude1, double latitude2, double longitude2)
    {
        double r = 6371; // Radius of Earth in km

        double dLat = degToRad(latitude2 - latitude1);
        double dLon = degToRad(longitude2 - longitude1);

        double a = sin(dLat/2) * sin(dLat/2) + cos(degToRad(latitude1)) * cos(degToRad(latitude2)) * sin(dLon/2) * sin(dLon/2);

        double c = 2 * atan2(sqrt(a), sqrt(1-a));

        return  r * c;
    }

    /**
     * Converting to radians
     */
    private double degToRad(double d)
    {
        return d * (PI /180);
    }



}
