package com.medical.service;


import com.medical.domain.MedicalUnit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("medicalUnitService")
@Transactional
public class MedicalUnitServiceImpl extends GenericServiceImpl<MedicalUnit> implements MedicalUnitService {

}
