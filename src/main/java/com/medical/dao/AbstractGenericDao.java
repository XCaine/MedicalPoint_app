package com.medical.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class AbstractGenericDao<E> implements GenericDao<E> {

    private final Class<E> entityClass;

    public  AbstractGenericDao() {
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session currentSession() {

        return sessionFactory.getCurrentSession();
    }

    @Override
    public Serializable save(E entity) {
        return currentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(E entity) {
        currentSession().saveOrUpdate(entity);
    }

    @Override
    public void remove(E entity) {
        currentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<E> entities = findAll();
        for( E entity : entities) {
            currentSession().delete(entity);
        }
    }

    @Override
    public List<E> findAll() {
        return currentSession().createCriteria(this.entityClass).list();
    }

    @Override
    public E findById(final Serializable id) {
        return (E) currentSession().get(this.entityClass, id);
    }

    @Override
    public void clear() {
        currentSession().clear();
    }

    @Override
    public void flush() {
        currentSession().flush();
    }
}