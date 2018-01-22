package com.medical.service;


import java.io.Serializable;
import java.util.List;

public interface GenericService<E> {

    void saveOrUpdate(E entity);

    List<E> findAll();

    E find(final Serializable id);

    void save(E entity);

    void remove(E entity);

    E findByName(String name);
}
