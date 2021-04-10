package com.millionaire.compound.hibernate.utils;

import com.millionaire.compound.common.models.StockDailyPriceCandidateModel;
import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.common.models.utils.DateUtil;
import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StockPriceCandidateUtil {

    static StockDailyPriceCandidateModel convertStockDailyCandidate2Model(StockDailyPrice stockDailyPrice){
        StockDailyPriceCandidateModel stockDailyPriceCandidateModel = new StockDailyPriceCandidateModel();
        stockDailyPriceCandidateModel.setTicker(stockDailyPrice.getTicker());
        stockDailyPriceCandidateModel.setVolume(stockDailyPrice.getVolume());
        stockDailyPriceCandidateModel.setDate(DateUtil.formateDate2String(stockDailyPrice.getDate()));
        stockDailyPriceCandidateModel.setOpen(stockDailyPrice.getOpen().doubleValue());
        stockDailyPriceCandidateModel.setClose(stockDailyPrice.getClose().doubleValue());
        stockDailyPriceCandidateModel.setHigh(stockDailyPrice.getHigh().doubleValue());
        stockDailyPriceCandidateModel.setLow(stockDailyPrice.getLow().doubleValue());
        stockDailyPriceCandidateModel.setRange(stockDailyPrice.getRange().doubleValue());
        stockDailyPriceCandidateModel.setAvg5Volume(stockDailyPrice.getAvg5Volume());
        stockDailyPriceCandidateModel.setAvg10Volume(stockDailyPrice.getAvg10Volume());
        stockDailyPriceCandidateModel.setAvg20Volume(stockDailyPrice.getAvg20Volume());
        stockDailyPriceCandidateModel.setAvg30Volume(stockDailyPrice.getAvg30Volume());
        stockDailyPriceCandidateModel.setAvg60Volume(stockDailyPrice.getAvg60Volume());
        stockDailyPriceCandidateModel.setMa5(stockDailyPrice.getMa5().doubleValue());
        stockDailyPriceCandidateModel.setMa10(stockDailyPrice.getMa10().doubleValue());
        stockDailyPriceCandidateModel.setMa20(stockDailyPrice.getMa20().doubleValue());
        stockDailyPriceCandidateModel.setMa30(stockDailyPrice.getMa30().doubleValue());
        stockDailyPriceCandidateModel.setMa60(stockDailyPrice.getMa60().doubleValue());

        return stockDailyPriceCandidateModel;

    }

    public static List<StockDailyPriceCandidateModel> convertStockDailyCandidate2Models(List<StockDailyPrice> stockDailyPrices){
        List<StockDailyPriceCandidateModel> stockDailyPriceCandidateModels = new ArrayList<>();
        StockDailyPriceCandidateModel previousModel = null;
        for (StockDailyPrice stockDailPrice: stockDailyPrices
        ) {
            StockDailyPriceCandidateModel model = convertStockDailyCandidate2Model(stockDailPrice);
            model.setPassMaNum(countPassMaNum(previousModel,model));
            stockDailyPriceCandidateModels.add(model);
            previousModel = model;
        }
        return stockDailyPriceCandidateModels;
    }

    public static int countPassMaNum(StockDailyPriceCandidateModel previousModel, StockDailyPriceCandidateModel model){
        int[] _MADAYS = new int[]{5,10,20,30,60};
        if(previousModel == null || model == null){
            return -1;
        }
        int passnum = 0;
        for (int day: _MADAYS) {
            try {
                if(invokePassMaMethod(previousModel, model, day)){
                    passnum++;

                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return passnum;
    }

    private static boolean invokePassMaMethod(StockDailyPriceCandidateModel previousModel, StockDailyPriceCandidateModel model, int day) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = model.getClass();

        Method getMaMethod = clazz.getDeclaredMethod("getMa"+day);
        double targetMa = Double.valueOf(getMaMethod.invoke(model).toString());
        System.out.println("getMa "+day+" "+targetMa+" "+previousModel.getClose()+" "+model.getClose());
        return ((targetMa-previousModel.getClose())>0) && ((model.getClose()-targetMa)>0);

    }
}
