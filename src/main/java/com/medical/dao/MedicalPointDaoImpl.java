package com.medical.dao;

import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnit;
import com.medical.domain.MedicalUnitType;
import com.medical.domain.Specialty;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Repository("medicalPointDao")
public class MedicalPointDaoImpl extends AbstractGenericDao<MedicalPoint> implements MedicalPointDao {

    MedicalPointDaoImpl(){super("MedicalPoint");};
    public MedicalUnit FindMedicalUnitWithSpecialty(String specialtyName){
        Query query = currentSession().createQuery("from MedicalUnit.specialties S where S.name=:name");
        query.setParameter("name", specialtyName);
        return (MedicalUnit) query.uniqueResult();
    }

    @Override
    public List<MedicalPoint> findWithIllnessAndCity(String illnessName, String cityName) {

        Query query = currentSession().createQuery("select m from MedicalPoint m " +
                                                    "inner join fetch m.medicalUnits u " +
                                                    "inner join fetch  u.specialties s " +
                                                     "inner join fetch s.illnesses " +
                                                     "i where i.name = :illnessName and m.city.name = :cityName");
        query.setParameter("illnessName", illnessName);
        query.setParameter("cityName", cityName);

/*
        Query query = currentSession().createQuery("from MedicalPoint m where  ");
        query.setParameter("illnessName", illnessName);
  */
        return (List<MedicalPoint>) query.list();
    }

    public List<MedicalUnit> findAllMedicalUnits(MedicalPoint medicalPoint) {

        Query query = currentSession().createQuery("from MedicalUnit mu "
        +"where mu.medicalPoint = :medicalPoint");
        query.setParameter("medicalPoint", medicalPoint);
        return (List<MedicalUnit>) query.list();
    }

    @Override
    public MedicalPoint findById(int id){
        Query query = currentSession().createQuery("from MedicalPoint MP where MP.id =:id");
        query.setParameter("id", id);
        EntityGraph entityGraph = currentSession().getEntityGraph("medicalPoint.city.province.country");
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return (MedicalPoint) query.uniqueResult();
    }
}
