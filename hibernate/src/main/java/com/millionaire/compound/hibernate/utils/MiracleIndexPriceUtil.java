package com.millionaire.compound.hibernate.utils;

import com.millionaire.compound.common.models.IndexPriceModel;
import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.common.models.utils.DateUtil;
import com.millionaire.compound.common.models.utils.ObjectUtil;
import com.millionaire.compound.hibernate.entity.basic.MiracleIndexDailyPrice;
import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@FunctionalInterface
interface IndexPriceAttribute{
    double getPriceAttribute(MiracleIndexDailyPrice miracleIndexDailyPrice);

}

public class MiracleIndexPriceUtil {

    private static int[] _MADAYS = new int[]{5,10,20,30,60};

    public static MiracleIndexDailyPrice convertStockEntity2IndexEntity(StockDailyPrice stockDailyPrice){
        MiracleIndexDailyPrice miracleIndexDailyPrice = new MiracleIndexDailyPrice();
        if(stockDailyPrice != null) {
            miracleIndexDailyPrice.setTicker(stockDailyPrice.getTicker());
            miracleIndexDailyPrice.setAvg5Volume(stockDailyPrice.getAvg5Volume());
            miracleIndexDailyPrice.setAvg10Volume(stockDailyPrice.getAvg10Volume());
            miracleIndexDailyPrice.setAvg20Volume(stockDailyPrice.getAvg20Volume());
            miracleIndexDailyPrice.setAvg30Volume(stockDailyPrice.getAvg30Volume());
            miracleIndexDailyPrice.setAvg60Volume(stockDailyPrice.getAvg60Volume());
            miracleIndexDailyPrice.setClose(stockDailyPrice.getClose());

            miracleIndexDailyPrice.setDate(stockDailyPrice.getDate());

            miracleIndexDailyPrice.setHigh(stockDailyPrice.getHigh());
//            miracleIndexDailyPrice.setId(stockDailyPrice.getId());
            miracleIndexDailyPrice.setLow(stockDailyPrice.getLow());
            miracleIndexDailyPrice.setMa5(stockDailyPrice.getMa5());
            miracleIndexDailyPrice.setMa10(stockDailyPrice.getMa10());
            miracleIndexDailyPrice.setMa20(stockDailyPrice.getMa20());
            miracleIndexDailyPrice.setMa30(stockDailyPrice.getMa30());
            miracleIndexDailyPrice.setMa60(stockDailyPrice.getMa60());
            miracleIndexDailyPrice.setOpen(stockDailyPrice.getOpen());
            miracleIndexDailyPrice.setRange(stockDailyPrice.getRange());
            miracleIndexDailyPrice.setVolume(stockDailyPrice.getVolume());


        }
        return miracleIndexDailyPrice;
    }



    public static void convertIndexDailyPrice(List<MiracleIndexDailyPrice> miracleIndexDailyPrices, int index){
        for (int day: _MADAYS) {
            try {
                invokeIndexMaMethod(miracleIndexDailyPrices, index, day);
                invokeIndexVolumeMethod(miracleIndexDailyPrices, index, day);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        return ;

    }

    private static void invokeIndexMaMethod(List<MiracleIndexDailyPrice> miracleIndexDailyPrices, int index,  int day) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MiracleIndexDailyPrice miracleIndexDailyPrice = miracleIndexDailyPrices.get(index);
        Class clazz = miracleIndexDailyPrice.getClass();
        Method setMaMethod = clazz.getDeclaredMethod("setMa"+day, BigDecimal.class);
        setMaMethod.invoke(miracleIndexDailyPrice, BigDecimal.valueOf(setMAAttribute(miracleIndexDailyPrices, index, day, (MiracleIndexDailyPrice indexDailyPrice)->{
            return indexDailyPrice.getClose().doubleValue();
        })));
    }

    private static void invokeIndexVolumeMethod(List<MiracleIndexDailyPrice> miracleIndexDailyPrices, int index, int day) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MiracleIndexDailyPrice miracleIndexDailyPrice = miracleIndexDailyPrices.get(index);
        Class clazz = miracleIndexDailyPrice.getClass();
        Method setVolumeMethod = clazz.getDeclaredMethod("setAvg"+day+"Volume", long.class);
        setVolumeMethod.invoke(miracleIndexDailyPrice, (long)setMAAttribute(miracleIndexDailyPrices, index, day, (MiracleIndexDailyPrice indexDailyPrice)->{
            return indexDailyPrice.getVolume();
        }));
    }

    private static double setMAAttribute(List<MiracleIndexDailyPrice> miracleIndexDailyPrices, int index, int days, IndexPriceAttribute priceAttribute){
        if(index < days-1){
            return priceAttribute.getPriceAttribute(miracleIndexDailyPrices.get(index));
        }
        double sum = 0.0;
        for(int i=0;i<days;i++){
            sum += priceAttribute.getPriceAttribute(miracleIndexDailyPrices.get(index-i));
        }

        return (double) Math.round((sum/(double)days) * 10000) / 10000;
    }


}
