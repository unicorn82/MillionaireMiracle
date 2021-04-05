package com.millionaire.compound.common.models.utils;

import java.math.BigDecimal;

public class MathUtil {

    public static double getEarning(double buy_price, double sell_price){

        double earing = (sell_price-buy_price)/buy_price;
        return Math.round(earing*10000)/10000.0;
    }
}
