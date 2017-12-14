package com.medical.dao;

import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnit;
import com.medical.domain.MedicalUnitType;
import com.medical.domain.Specialty;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Repository("medicalPointDao")
public class MedicalPointDaoImpl extends AbstractGenericDao<MedicalPoint> implements MedicalPointDao {

    MedicalPointDaoImpl(){super("medical_point");};
    public MedicalUnit FindMedicalUnitWithSpecialty(String specialtyName){
        Query query = currentSession().createQuery("from MedicalUnit.specialties S where S.name=:name");
        query.setParameter("name", specialtyName);
        return (MedicalUnit) query.uniqueResult();
    }

    @Override
    public List<MedicalPoint> findWithIllnessAndCity(String illnessName, String cityName) {

        Query query = currentSession().createQuery("select m from MedicalPoint m inner join m.medicalUnits u inner join u.specialties s inner join s.illnesses " +
                "i where i.name = :illnessName ");
        query.setParameter("illnessName", illnessName);
        //query.setParameter("cityName", cityName);

/*
        Query query = currentSession().createQuery("from MedicalPoint m where  ");
        query.setParameter("illnessName", illnessName);
  */
        return (List<MedicalPoint>) query.list();
    }
}
