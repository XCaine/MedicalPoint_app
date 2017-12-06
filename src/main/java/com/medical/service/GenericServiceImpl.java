package com.medical.service;

import com.medical.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


public abstract class GenericServiceImpl<E> implements GenericService<E> {

    @Autowired
    private  GenericDao<E> genericDao;

    public  GenericServiceImpl(GenericDao<E> genericDao){
        this.genericDao=genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(E entity){
        genericDao.saveOrUpdate(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<E> findAll(){
        return genericDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public E find(final Serializable id){
        return genericDao.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(E entity){
        genericDao.save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(E entity){
        genericDao.remove(entity);
    }


}