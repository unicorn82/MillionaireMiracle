package com.millionaire.compound.hibernate.utils;

import com.millionaire.compound.common.models.StockModel;
import com.millionaire.compound.hibernate.entity.basic.MiracleStock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MiracleStockUtil {

    public static MiracleStock convertStockModeltoEntity(StockModel stockModel){
        MiracleStock miracleStock = new MiracleStock();
        miracleStock.setCategory(stockModel.getCategory());
        miracleStock.setAvg3mVolumn(BigDecimal.valueOf(stockModel.getAvg3mVolumn()));
        miracleStock.setBeta(BigDecimal.valueOf(stockModel.getBeta()));
        miracleStock.setCirMarketCap(BigDecimal.valueOf(stockModel.getCirMarketCap()));
        miracleStock.setCompany(stockModel.getCompany());
        miracleStock.setCompanyName(stockModel.getCompanyName());
        miracleStock.setEarning(BigDecimal.valueOf(stockModel.getEarning()));
        miracleStock.setEps(BigDecimal.valueOf(stockModel.getEps()));
        miracleStock.setForwardDividend(BigDecimal.valueOf(stockModel.getForwardDividend()));
        miracleStock.setId(stockModel.getId());
        miracleStock.setMarketCap(BigDecimal.valueOf(stockModel.getMarketCap()));
        miracleStock.setNextReportDate(stockModel.getNextReportDate());
        miracleStock.setPe(BigDecimal.valueOf(stockModel.getPe()));
        miracleStock.setW52Range(stockModel.getW52Range());
        miracleStock.setTicker(stockModel.getTicker());
        miracleStock.setYearRange(BigDecimal.valueOf(stockModel.getYearRange()));
        miracleStock.setYield(BigDecimal.valueOf(stockModel.getYield()));
        return miracleStock;

    }

    public static StockModel convertStockEntitytoModel(MiracleStock miracleStock){
        StockModel stockModel = new StockModel();
        stockModel.setCategory(miracleStock.getCategory());
        stockModel.setAvg3mVolumn(miracleStock.getAvg3mVolumn().doubleValue());
        stockModel.setBeta(miracleStock.getBeta().doubleValue());
        stockModel.setCirMarketCap(miracleStock.getCirMarketCap().doubleValue());
        stockModel.setCompany(miracleStock.getCompany());
        stockModel.setCompanyName(miracleStock.getCompanyName());
        stockModel.setEarning(miracleStock.getEarning().doubleValue());
        stockModel.setEps(miracleStock.getEps().doubleValue());
        stockModel.setForwardDividend(miracleStock.getForwardDividend().doubleValue());
        stockModel.setId(miracleStock.getId());
        stockModel.setMarketCap(miracleStock.getMarketCap().doubleValue());
        stockModel.setNextReportDate(miracleStock.getNextReportDate());
        stockModel.setPe(miracleStock.getPe().doubleValue());
        stockModel.setW52Range(miracleStock.getW52Range());
        stockModel.setTicker(miracleStock.getTicker());
        stockModel.setYearRange(miracleStock.getYearRange().doubleValue());
        stockModel.setYield(miracleStock.getYield().doubleValue());

        return stockModel;


    }

    public static List<StockModel> convertListStockEntitytomodels(List<MiracleStock> miracleStocks) {
        List<StockModel> stockModels = new ArrayList<>();
        for (MiracleStock miracleStock :miracleStocks) {
            stockModels.add(convertStockEntitytoModel(miracleStock));
        }
        return stockModels;
    }
}
