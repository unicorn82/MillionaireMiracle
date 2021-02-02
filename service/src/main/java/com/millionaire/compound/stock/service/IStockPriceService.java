package com.millionaire.compound.stock.service;

import com.millionaire.compound.common.models.StockPriceModel;

import java.util.List;

public interface IStockPriceService {

    public void saveStockDailyPrice(List<StockPriceModel> stockPriceModels);


}
