package com.millionaire.compound.hibernate.dao;

import com.millionaire.compound.hibernate.entity.basic.MiracleIndex;


public interface MiracleIndexRepository extends ExtendedRepository<MiracleIndex, Integer> {

    public MiracleIndex findByTicker(String ticker);

}
