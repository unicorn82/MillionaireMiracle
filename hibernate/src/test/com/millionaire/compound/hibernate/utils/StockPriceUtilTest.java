package com.millionaire.compound.hibernate.utils;

import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class StockPriceUtilTest {

    List<StockPriceModel> stockPriceModelList;
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    int total = 60;

    @BeforeAll
    void prepareTestData(){

        stockPriceModelList = new ArrayList<>();

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

            String dateInString = "1-Jan-2020";

            Date date = formatter.parse(dateInString);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            for(int i=0;i<total;i++) {
                StockPriceModel stockPriceModel = new StockPriceModel();
                stockPriceModel.setTicker("testTicker");
                cal.add(Calendar.DATE, i);
                stockPriceModel.setDate(dateFormat.format(cal.getTime()));
                stockPriceModel.setClose((i+1));
                stockPriceModel.setVolume(10+i);
                stockPriceModelList.add(stockPriceModel);
                System.out.println(stockPriceModelList.get(i).toString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testStockDailyPriceMA(){
        prepareTestData();
        for(int i=0;i<total;i++) {
            StockDailyPrice stockDailyPrice = StockPriceUtil.convertStockDailyPrice2Enity(stockPriceModelList, i);
            System.out.println(stockDailyPrice.getMa20());
        }

        Assert.assertEquals(3.0, StockPriceUtil.convertStockDailyPrice2Enity(stockPriceModelList, 4).getMa5().doubleValue(), 0.0001);
        Assert.assertEquals(5.5, StockPriceUtil.convertStockDailyPrice2Enity(stockPriceModelList, 9).getMa10().doubleValue(), 0.0001);
        Assert.assertEquals(10.5, StockPriceUtil.convertStockDailyPrice2Enity(stockPriceModelList, 19).getMa20().doubleValue(), 0.0001);

    }

    @Test
    public void testStockDailyVolumMA(){
        prepareTestData();
        for(int i=0;i<total;i++) {
            StockDailyPrice stockDailyPrice = StockPriceUtil.convertStockDailyPrice2Enity(stockPriceModelList, i);
            System.out.println(stockDailyPrice.getVolume()+" "+stockDailyPrice.getAvg5Volume());

        }
        Assert.assertEquals(12, StockPriceUtil.convertStockDailyPrice2Enity(stockPriceModelList, 4).getAvg5Volume());
        Assert.assertEquals(14, StockPriceUtil.convertStockDailyPrice2Enity(stockPriceModelList, 9).getAvg10Volume());
        Assert.assertEquals(19, StockPriceUtil.convertStockDailyPrice2Enity(stockPriceModelList, 19).getAvg20Volume());


    }
}
