package com.medical.dao;

import com.medical.domain.Illness;

public class IllnessDaoImpl extends AbstractGenericDao<Illness> implements IllnessDao {

    IllnessDaoImpl(){super("illness");};
}
