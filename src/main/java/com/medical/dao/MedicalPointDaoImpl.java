package com.medical.dao;

import com.medical.domain.MedicalPoint;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository("medicalPointDao")
public class MedicalPointDaoImpl extends AbstractGenericDao<MedicalPoint> implements MedicalPointDao {

    MedicalPointDaoImpl(){super("medical_point");};
}
