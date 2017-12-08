package com.medical.dao;

import com.medical.domain.MedicalPoint;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("medicalPointDao")
public class MedicalPointDaoImpl extends AbstractGenericDao<MedicalPoint> implements MedicalPointDao {

    MedicalPointDaoImpl(){super("medical_point");};
}
