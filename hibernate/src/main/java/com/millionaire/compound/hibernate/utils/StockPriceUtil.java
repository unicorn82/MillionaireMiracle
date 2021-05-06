package com.millionaire.compound.hibernate.utils;

import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.common.models.utils.CommonUtil;
import com.millionaire.compound.common.models.utils.DateUtil;
import com.millionaire.compound.common.models.utils.MathUtil;
import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@FunctionalInterface
interface StockPriceAttribute{
    double getPriceAttribute(StockDailyPrice stockPriceModel);

}

public class StockPriceUtil {

    private static int[] _MADAYS = new int[]{5,10,20,30,60};

    public static String datePattern = "M/dd/yyyy";

    private static long defaultValue = 0l;

    public static StockDailyPrice convertStockDailyPrice2Enity(StockPriceModel stockPriceModel) {
        StockDailyPrice stockDailyPrice = new StockDailyPrice();
        stockDailyPrice.setTicker(stockPriceModel.getTicker());
        try {
            stockDailyPrice.setDate(DateUtil.formateDate(stockPriceModel.getDate(), datePattern));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        stockDailyPrice.setOpen(BigDecimal.valueOf(stockPriceModel.getOpen()));
        stockDailyPrice.setClose(BigDecimal.valueOf(stockPriceModel.getClose()));
        stockDailyPrice.setHigh(BigDecimal.valueOf(stockPriceModel.getHigh()));
        stockDailyPrice.setLow(BigDecimal.valueOf(stockPriceModel.getLow()));
        stockDailyPrice.setRange(BigDecimal.valueOf(stockPriceModel.getRange()));
        stockDailyPrice.setVolume(stockPriceModel.getVolume());


        return stockDailyPrice;
    }

    private static double getStockDailyRange(List<StockPriceModel> stockPriceModelList, int index){
        double range = 0.0;
        if(index>0){
            range = MathUtil.getEarning(stockPriceModelList.get(index-1).getClose(), stockPriceModelList.get(index).getClose());
        }

        return range;
    }


    public static void updateStockDailyPrice(List<StockDailyPrice> stockDailyPrices, int index){
        if(CommonUtil.isNotEmpty(stockDailyPrices)) {
            StockDailyPrice stockDailyPrice = stockDailyPrices.get(index);
            for (int day: _MADAYS) {
                try {
                    invokeStockMaMethod(stockDailyPrices, index, stockDailyPrice, day);
                    invokeStockVolumeMethod(stockDailyPrices, index, stockDailyPrice, day);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }




    private static void invokeStockMaMethod(List<StockDailyPrice> stockPriceModelList, int index, StockDailyPrice stockDailyPrice, int day) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = stockDailyPrice.getClass();
        Method setMaMethod = clazz.getDeclaredMethod("setMa"+day, BigDecimal.class);
        setMaMethod.invoke(stockDailyPrice, BigDecimal.valueOf(setMAAttribute(stockPriceModelList, index, day, (StockDailyPrice stockPriceModel)->{
            return stockPriceModel.getClose().doubleValue();
        })));
    }

    private static void invokeStockVolumeMethod(List<StockDailyPrice> stockPriceModelList, int index, StockDailyPrice stockDailyPrice, int day) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = stockDailyPrice.getClass();
        Method setVolumeMethod = clazz.getDeclaredMethod("setAvg"+day+"Volume", long.class);
        setVolumeMethod.invoke(stockDailyPrice, (int)setMAAttribute(stockPriceModelList, index, day, (StockDailyPrice stockPriceModel)->{
            return stockPriceModel.getVolume();
        }));
    }


    private static double setMAAttribute(List<StockDailyPrice> stockPriceModelList, int index, int days, StockPriceAttribute priceAttribute){
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
