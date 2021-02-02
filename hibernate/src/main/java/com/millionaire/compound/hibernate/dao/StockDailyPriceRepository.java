package com.millionaire.compound.hibernate.dao;


import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockDailyPriceRepository extends ExtendedRepository<StockDailyPrice, Integer> {

//    @Query("select * from StockDailyPrice p where ")
    List<StockDailyPrice> getStockDailyPricesByTickerAndDate(String ticker, String date);

    List<StockDailyPrice> getStockDailyPricesByTicker(String ticker);



}
