package com.millionaire.compound.hibernate.utils;

import com.millionaire.compound.common.models.IndexPriceModel;
import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.common.models.utils.DateUtil;
import com.millionaire.compound.common.models.utils.ObjectUtil;
import com.millionaire.compound.hibernate.entity.basic.MiracleIndexDailyPrice;
import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MiracleIndexPriceUtil {

    private static String datePattern = "yyyy-M-dd";

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

    public static MiracleIndexDailyPrice convertStockDailyPrice2Enity(List<StockPriceModel> indexPriceModelList, int index){

        StockDailyPrice stockDailyPrice = StockPriceUtil.convertStockDailyPrice2Enity(indexPriceModelList, index);

        return convertStockEntity2IndexEntity(stockDailyPrice);
    }


}
