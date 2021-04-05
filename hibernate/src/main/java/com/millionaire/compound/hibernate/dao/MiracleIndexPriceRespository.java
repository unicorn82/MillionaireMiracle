package com.millionaire.compound.hibernate.dao;

import com.millionaire.compound.hibernate.entity.basic.MiracleIndex;
import com.millionaire.compound.hibernate.entity.basic.MiracleIndexDailyPrice;

import java.util.Date;
import java.util.List;

public interface MiracleIndexPriceRespository extends ExtendedRepository<MiracleIndexDailyPrice, Integer> {



    public List<MiracleIndexDailyPrice> queryByTickerOrderByDateAsc(String ticker);

    public MiracleIndexDailyPrice queryFirstByTickerAndDate(String ticker, Date date);

}
