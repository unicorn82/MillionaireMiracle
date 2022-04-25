package com.millionaire.compound.hibernate.dao;

import com.millionaire.compound.hibernate.entity.basic.MiracleStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiracleStockRepository extends ExtendedRepository<MiracleStock, Integer> {

    MiracleStock findByTicker(String ticker);

    @Query("select p.ticker from MiracleStock p where p.marketCap > 1000000000  order by p.marketCap desc")
    List<String> listAllTickers();
}
