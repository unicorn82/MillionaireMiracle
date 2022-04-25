package com.millionaire.compound.stock.service.utils;

import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;

public class KlineUtils {

    public static boolean isLongDownKline(StockDailyPrice stockDailyPrice){
        double rate = 100*(stockDailyPrice.getClose().doubleValue()-stockDailyPrice.getLow().doubleValue())/stockDailyPrice.getOpen().doubleValue();
        return rate > 3;

    }

    public static boolean isLongUpKline(StockDailyPrice stockDailyPrice){
        double rate1 = 100*(stockDailyPrice.getHigh().doubleValue()-stockDailyPrice.getClose().doubleValue())/stockDailyPrice.getOpen().doubleValue();
        double rate2 = Math.abs((stockDailyPrice.getClose().doubleValue()-stockDailyPrice.getOpen().doubleValue())/stockDailyPrice.getOpen().doubleValue());
        return rate1 > rate2 && rate1>3;
    }


    public static boolean isCrossLine(StockDailyPrice stockDailyPrice){
        double rate1 = 100*(stockDailyPrice.getClose().doubleValue()-stockDailyPrice.getLow().doubleValue())/stockDailyPrice.getOpen().doubleValue();
        double rate2 = 100*(stockDailyPrice.getHigh().doubleValue()-stockDailyPrice.getClose().doubleValue())/stockDailyPrice.getOpen().doubleValue();
        double rate3 = Math.abs((stockDailyPrice.getClose().doubleValue()-stockDailyPrice.getOpen().doubleValue())/stockDailyPrice.getOpen().doubleValue());
        return rate1>rate3 && rate2>rate3;

    }


}
