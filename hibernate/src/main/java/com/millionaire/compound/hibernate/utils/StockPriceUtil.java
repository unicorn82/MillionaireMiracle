package com.millionaire.compound.hibernate.utils;

import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@FunctionalInterface
interface StockPriceAttribute{
    double getPriceAttribute(StockPriceModel stockPriceModel);

}

public class StockPriceUtil {

    private static int[] _MADAYS = new int[]{5,10,20,30,60};

    static StockDailyPrice convertStockDailyPrice2Enity(StockPriceModel stockPriceModel){
        StockDailyPrice stockDailyPrice = new StockDailyPrice();
        stockDailyPrice.setTicker(stockPriceModel.getTicker());
        stockDailyPrice.setDate(stockPriceModel.getDate());
        stockDailyPrice.setOpen(BigDecimal.valueOf(stockPriceModel.getOpen()));
        stockDailyPrice.setClose(BigDecimal.valueOf(stockPriceModel.getClose()));
        stockDailyPrice.setHigh(BigDecimal.valueOf(stockPriceModel.getHigh()));
        stockDailyPrice.setLow(BigDecimal.valueOf(stockPriceModel.getLow()));
        stockDailyPrice.setRange(BigDecimal.valueOf(stockPriceModel.getRange()));
        stockDailyPrice.setVolume(stockPriceModel.getVolume());

        return stockDailyPrice;
    }




    public static StockDailyPrice convertStockDailyPrice2Enity(List<StockPriceModel> stockPriceModelList, int index){
        StockDailyPrice stockDailyPrice = convertStockDailyPrice2Enity(stockPriceModelList.get(index));

        for (int day: _MADAYS) {
            try {
                invokeStockMaMethod(stockPriceModelList, index, stockDailyPrice, day);
                invokeStockVolumeMethod(stockPriceModelList, index, stockDailyPrice, day);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        stockDailyPrice.setPe(BigDecimal.valueOf(0));

        return stockDailyPrice;

    }

    private static void invokeStockMaMethod(List<StockPriceModel> stockPriceModelList, int index, StockDailyPrice stockDailyPrice, int day) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = stockDailyPrice.getClass();
        Method setMaMethod = clazz.getDeclaredMethod("setMa"+day, BigDecimal.class);
        setMaMethod.invoke(stockDailyPrice, BigDecimal.valueOf(setMAAttribute(stockPriceModelList, index, day, (StockPriceModel stockPriceModel)->{
            return Double.valueOf(stockPriceModel.getClose());
        })));
    }

    private static void invokeStockVolumeMethod(List<StockPriceModel> stockPriceModelList, int index, StockDailyPrice stockDailyPrice, int day) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = stockDailyPrice.getClass();
        Method setVolumeMethod = clazz.getDeclaredMethod("setAvg"+day+"Volume", int.class);
        setVolumeMethod.invoke(stockDailyPrice, (int)setMAAttribute(stockPriceModelList, index, day, (StockPriceModel stockPriceModel)->{
            return stockPriceModel.getVolume();
        }));
    }


    private static double setMAAttribute(List<StockPriceModel> stockPriceModelList, int index, int days, StockPriceAttribute priceAttribute){
        if(index < days-1){
            return priceAttribute.getPriceAttribute(stockPriceModelList.get(index));
        }
        double sum = 0.0;
        for(int i=0;i<days;i++){
            sum += priceAttribute.getPriceAttribute(stockPriceModelList.get(index-i));
        }

        return (double) Math.round((sum/(double)days) * 10000) / 10000;
    }

}
