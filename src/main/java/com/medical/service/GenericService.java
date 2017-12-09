package com.medical.service;


import java.io.Serializable;
import java.util.List;

public interface GenericService<E> {

    public void saveOrUpdate(E entity);

    public List<E> findAll();

    public E find(final Serializable id);

    public void save(E entity);

    public void remove(E entity);

    public E findByName(String name);
}
