package com.medical.dao;

import com.medical.domain.MedicalPoint;
import com.medical.domain.Province;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("provinceDao")
public class ProvinceDaoImpl extends AbstractGenericDao<Province> implements ProvinceDao{

    ProvinceDaoImpl(){super("Province");}
}
