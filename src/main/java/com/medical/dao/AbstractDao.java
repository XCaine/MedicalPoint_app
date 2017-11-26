package com.medical.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDao {
    @Autowired
    private SessionFactory sessionFactory;




    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    public void add(Object entity) {
        currentSession().save(entity);
    }


    public void saveOrUpdate(Object entity) {
        currentSession().saveOrUpdate(entity);
    }


    public void update(Object entity) {
        currentSession().saveOrUpdate(entity);
    }


    public void remove(Object entity) {
        currentSession().delete(entity);
    }



}
