package com.medical.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Interface to provide common DAO methods
 *
 */

public interface GenericDao<E> {


    /**
     *
     * @param entity: entity to save
     * @return  Identifier of saved entity
     */
    Serializable save(E entity);

    /**
     *
     * @param entity: entity to save or update
     */
    public void saveOrUpdate(E entity);

    /**
     *
     * @param entity: entity to delete
     */
    void remove(E entity);

    /**
     * Delete all records
     */
    void deleteAll();

    /**
     *  Find all records
     * @return List of all records
     */
    List<E> findAll();

    /**
     *  Find by primary key
     * @param id
     * @return unique entity
     */
    E findById(Serializable id);

    /**
     * Clear session
     */
    void clear();

    /**
     * Flush session
     */
    void flush();

}
