package com.medical.dao;

import com.medical.domain.Illness;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class IllnessDaoImpl extends AbstractGenericDao<Illness> implements IllnessDao {

    IllnessDaoImpl(){super("illness");};
}
