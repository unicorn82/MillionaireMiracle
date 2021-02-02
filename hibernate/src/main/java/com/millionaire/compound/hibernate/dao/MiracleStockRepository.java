package com.millionaire.compound.hibernate.dao;

import com.millionaire.compound.hibernate.entity.basic.MiracleStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiracleStockRepository extends ExtendedRepository<MiracleStock, Integer> {

    MiracleStock findByTicker(String ticker);
}
