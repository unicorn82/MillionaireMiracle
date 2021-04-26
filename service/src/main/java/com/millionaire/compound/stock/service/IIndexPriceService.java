package com.millionaire.compound.stock.service;

import com.millionaire.compound.common.models.IndexPriceModel;
import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.hibernate.entity.basic.MiracleIndexDailyPrice;

import java.util.List;

public interface IIndexPriceService {

    public void saveIndexDailyPrice(List<StockPriceModel> indexPriceModels);

    public void saveIndexDailyPrice(String ticker, List<StockPriceModel> indexPriceModels);

    public List<MiracleIndexDailyPrice> listIndexDailyPrice(String ticker);
}
