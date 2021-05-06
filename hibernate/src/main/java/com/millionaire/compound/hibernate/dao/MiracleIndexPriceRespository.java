package com.millionaire.compound.hibernate.dao;

import com.millionaire.compound.hibernate.entity.basic.MiracleIndex;
import com.millionaire.compound.hibernate.entity.basic.MiracleIndexDailyPrice;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MiracleIndexPriceRespository extends ExtendedRepository<MiracleIndexDailyPrice, Integer> {



    public List<MiracleIndexDailyPrice> queryByTickerOrderByDateAsc(String ticker);

    public MiracleIndexDailyPrice queryFirstByTickerAndDate(String ticker, Date date);


    @Query("select midp from MiracleIndexDailyPrice midp  where ticker = ?1 and date > '2021-01-01' order by date")
    public List<MiracleIndexDailyPrice> getThisYearListByTickerOrderByDate(String ticker);

}
