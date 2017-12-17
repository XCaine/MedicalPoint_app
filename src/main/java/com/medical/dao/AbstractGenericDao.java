package com.medical.dao;

import com.medical.domain.Country;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class AbstractGenericDao<E> implements GenericDao<E> {

    private final Class<E> entityClass;
    private final String tableName;

    @Autowired
    public  AbstractGenericDao(String tableName) {
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        this.tableName = tableName;
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

    @Override
    public void update(E entity) {currentSession().update(entity);}

// MICHAŁ SPRAWDŹ
    @Override
    public void deleteById(final int id){
        final E entity = findById( id);
        remove(entity);
    }
// MICHAŁ SPRAWDŹ
    @Override
    public E findByName(String name) {
        Query query = currentSession().createQuery("from " + tableName +" where name = :name");
        query.setParameter("name", name);

        return (E) query.uniqueResult();
    }
}
