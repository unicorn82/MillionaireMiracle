package com.millionaire.compound.hibernate.utils;

import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;

import java.math.BigDecimal;
import java.util.List;

public class StockPriceUtil {
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
        stockDailyPrice.setMa5(BigDecimal.valueOf(setMAPrice(stockPriceModelList, index, 5)));
        stockDailyPrice.setMa10(BigDecimal.valueOf(setMAPrice(stockPriceModelList, index, 10)));
        stockDailyPrice.setMa20(BigDecimal.valueOf(setMAPrice(stockPriceModelList, index, 20)));
        stockDailyPrice.setMa30(BigDecimal.valueOf(setMAPrice(stockPriceModelList, index, 30)));
        stockDailyPrice.setMa60(BigDecimal.valueOf(setMAPrice(stockPriceModelList, index, 60)));
        stockDailyPrice.setAvg5Volume(setAvgVolume(stockPriceModelList, index, 5));
        stockDailyPrice.setAvg10Volume(setAvgVolume(stockPriceModelList, index, 10));
        stockDailyPrice.setAvg20Volume(setAvgVolume(stockPriceModelList, index, 20));
        stockDailyPrice.setAvg30Volume(setAvgVolume(stockPriceModelList, index, 30));
        stockDailyPrice.setAvg60Volume(setAvgVolume(stockPriceModelList, index, 60));
        stockDailyPrice.setPe(BigDecimal.valueOf(0));

        return stockDailyPrice;

    }



    private static double setMAPrice(List<StockPriceModel> stockPriceModelList, int index, int days){
        if(index < days-1){
            return 0.0;
        }
        double sum = 0.0;
        for(int i=0;i<days;i++){
            sum += stockPriceModelList.get(index-i).getClose();
        }
        return (double) Math.round(sum/days * 100) / 100;
    }

    private static int setAvgVolume(List<StockPriceModel> stockPriceModelList, int index, int days){
        if(index < days-1){
            return 0;
        }
        int sum = 0;
        for(int i=0;i<days;i++){
            sum += stockPriceModelList.get(index-i).getVolume();
        }
        return Math.round(sum/days);
    }


}
