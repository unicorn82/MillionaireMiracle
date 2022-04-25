package com.millionaire.compound.stock.service.utils;

import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;

public class EvaluationUtils {

    public static double getEarning(StockDailyPrice stockDailyPrice1, StockDailyPrice stockDailyPrice2){
        return 100*(stockDailyPrice2.getClose().doubleValue()-stockDailyPrice1.getClose().doubleValue())/stockDailyPrice1.getClose().doubleValue();
    }

    public static double getHRetracement(StockDailyPrice stockDailyPrice1, StockDailyPrice stockDailyPrice2){
        return 100*(stockDailyPrice2.getLow().doubleValue()-stockDailyPrice1.getClose().doubleValue())/stockDailyPrice1.getClose().doubleValue();
    }
}
