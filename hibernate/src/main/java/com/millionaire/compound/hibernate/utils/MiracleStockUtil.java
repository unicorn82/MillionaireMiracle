package com.millionaire.compound.hibernate.utils;

import com.millionaire.compound.common.models.StockModel;
import com.millionaire.compound.hibernate.entity.basic.MiracleStock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MiracleStockUtil {

    public static MiracleStock convertStockModeltoEntity(StockModel stockModel){
        MiracleStock miracleStock = new MiracleStock();
        miracleStock.setTicker(stockModel.getTicker());
        miracleStock.setEps(BigDecimal.valueOf(stockModel.getEPS()));
        miracleStock.setDividend(BigDecimal.valueOf(stockModel.getDividend()));
        miracleStock.setCompanyName(stockModel.getCompanyName());
        miracleStock.setBeta(BigDecimal.valueOf(stockModel.getBeta()));
        miracleStock.setCategory(stockModel.getCategory());
        miracleStock.setEarning(BigDecimal.valueOf(stockModel.getEarning()));
        miracleStock.setForwardPe1yr(BigDecimal.valueOf(stockModel.getForwardPE1Yr()));
        miracleStock.setId(stockModel.getId());
        miracleStock.setIndustry(stockModel.getIndustry());
        miracleStock.setMarketCap(BigDecimal.valueOf(stockModel.getMarketCap()));
        miracleStock.setNextReportDate(stockModel.getNextReportDate());
        miracleStock.setOneYrTarget(BigDecimal.valueOf(stockModel.getOneYrTarget()));
        miracleStock.setPe(BigDecimal.valueOf(stockModel.getPE()));
        miracleStock.setSector(stockModel.getSector());
        miracleStock.setUrl(stockModel.getUrl());
        miracleStock.setYield(BigDecimal.valueOf(stockModel.getYield()));

        return miracleStock;

    }

    public static StockModel convertStockEntitytoModel(MiracleStock miracleStock){
        StockModel stockModel = new StockModel();
        stockModel.setId(miracleStock.getId());
        stockModel.setBeta(miracleStock.getBeta().doubleValue());
        stockModel.setCategory(miracleStock.getCategory());
        stockModel.setCompanyName(miracleStock.getCompanyName());
        stockModel.setDividend(miracleStock.getDividend().doubleValue());
        stockModel.setEarning(miracleStock.getEarning().doubleValue());
        stockModel.setEPS(miracleStock.getEps().doubleValue());
        stockModel.setForwardPE1Yr(miracleStock.getForwardPe1yr().doubleValue());
        stockModel.setIndustry(miracleStock.getIndustry());
        stockModel.setMarketCap(miracleStock.getMarketCap().doubleValue());
        stockModel.setNextReportDate(miracleStock.getNextReportDate());
        stockModel.setOneYrTarget(miracleStock.getOneYrTarget().doubleValue());
        stockModel.setPE(miracleStock.getPe().doubleValue());
        stockModel.setSector(miracleStock.getSector());
        stockModel.setTicker(miracleStock.getTicker());
        stockModel.setUrl(miracleStock.getUrl());
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
