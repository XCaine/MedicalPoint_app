package com.medical.dao;

import com.medical.domain.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("countryDao")
public class CountryDaoImpl extends AbstractDao implements CountryDao{

    @Autowired
    private SessionFactory sessionFactory;




    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    public void add(Country country) {
        currentSession().save(country);
    }


    public void saveOrUpdate(Country country) {
        currentSession().saveOrUpdate(country);
    }


    public void update(Country country) {
        currentSession().saveOrUpdate(country);
    }


    public void remove(Country country) {
        currentSession().delete(country);
    }
    /**
     * Finding coutry by name
     *
     * @param countryName
     * @return Country object
     */
    @Override
    public Country findByName(String countryName) {
        Query query = currentSession().createQuery("from Country " + "where name=:name");
        query.setParameter("name",countryName);
        return (Country) query.uniqueResult();
    }



    @Override
    public Country find(Integer id) {
        return currentSession().get(Country.class, id);
    }

    @Override
    public List<Country> findALL() {
        return currentSession().createQuery("from Country ").getResultList();
    }
}
