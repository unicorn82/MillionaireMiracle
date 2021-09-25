package com.millionaire.compound.stock.service;

import com.millionaire.compound.common.models.StockDailyPriceCandidateModel;
import com.millionaire.compound.common.models.StockPriceModel;

import java.util.List;

public interface IStockPriceService {

    public void saveStockDailyPrice(String ticker, List<StockPriceModel> stockPriceModels);

    public void updateStockDailyPrice(String ticker);

    public List<String> getPotentialStocks();

    public List<String> getPotentialStocks(String date);


}
