package com.medical.dao;

import com.medical.domain.MedicalPoint;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository("medicalPointDao")
public class MedicalPointDaoImpl extends AbstractGenericDao<MedicalPoint> implements MedicalPointDao {

    @Override
    public MedicalPoint findByName(String medicalPointName) {
        Query query = currentSession().createQuery("from MedicalPoint " + "where name=:name");
        query.setParameter("name",medicalPointName);
        return (MedicalPoint) query.uniqueResult();
    }
}
