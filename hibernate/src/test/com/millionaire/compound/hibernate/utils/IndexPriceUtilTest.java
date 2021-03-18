package com.millionaire.compound.hibernate.utils;

import com.millionaire.compound.hibernate.entity.basic.MiracleIndexDailyPrice;
import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class IndexPriceUtilTest {

    @Test
    public void testConvertStockEntity2IndexEntity(){

        StockDailyPrice stockDailyPrice = new StockDailyPrice();
        stockDailyPrice.setTicker("ticker1");
        stockDailyPrice.setClose(BigDecimal.TEN);
        stockDailyPrice.setOpen(BigDecimal.ONE);
        stockDailyPrice.setMa5(BigDecimal.valueOf(5));

        MiracleIndexDailyPrice miracleIndexDailyPrice = MiracleIndexPriceUtil.convertStockEntity2IndexEntity(stockDailyPrice);
        Assert.assertEquals("ticker1", miracleIndexDailyPrice.getTicker());
    }
}
