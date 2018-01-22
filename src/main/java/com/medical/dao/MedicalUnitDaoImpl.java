package com.medical.dao;

import com.medical.domain.MedicalUnit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class MedicalUnitDaoImpl extends AbstractGenericDao<MedicalUnit> implements MedicalUnitDao {

    MedicalUnitDaoImpl(){super("MedicalUnit");}


}
