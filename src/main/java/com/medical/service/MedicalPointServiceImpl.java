package com.medical.service;

import com.medical.dao.MedicalPointDao;
import com.medical.domain.MedicalPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("medicalPointService")
@Transactional(readOnly = true)
public class MedicalPointServiceImpl implements  MedicalPointService{

    @Autowired
    MedicalPointDao medicalPointDao;

    @Override
    @Transactional(readOnly = false)
    public void add(MedicalPoint medicalPoint) {
        medicalPointDao.save(medicalPoint);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(MedicalPoint medicalPoint) {
        medicalPointDao.saveOrUpdate(medicalPoint);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(MedicalPoint medicalPoint) {
        medicalPointDao.saveOrUpdate(medicalPoint);
    }

    @Override
    @Transactional(readOnly = false)
    public void remove(MedicalPoint medicalPoint) {
        medicalPointDao.remove(medicalPoint);
    }

    @Override
    public MedicalPoint findById(Integer id) {
       return medicalPointDao.findById(id);
    }

    @Override
    public List<MedicalPoint> findALL() {
        return medicalPointDao.findAll();
    }

    @Override
    public MedicalPoint findByName(String medicalPointName) {
        return medicalPointDao.findByName(medicalPointName);
    }

    @Override
    @Transactional(readOnly = false)
    public void removeByName(String medicalPointName) {
        MedicalPoint temp = medicalPointDao.findByName(medicalPointName);
        this.remove(temp);
    }

    @Override
    @Transactional(readOnly = false)
    public void removeById(Integer id) {
        MedicalPoint temp = medicalPointDao.findById(id);
        this.remove(temp);
    }
}
