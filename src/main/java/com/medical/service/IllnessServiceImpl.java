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

@Service("illnessService")
public class IllnessServiceImpl extends GenericServiceImpl<Illness> implements IllnessService {

    @Autowired
    IllnessDao illnessDao;

    /*@Autowired
    IllnessServiceImpl(IllnessDao illnessDao){
        super(illnessDao);
    }*/

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
}
