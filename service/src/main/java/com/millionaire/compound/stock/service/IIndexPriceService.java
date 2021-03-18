package com.millionaire.compound.stock.service;

import com.millionaire.compound.common.models.IndexPriceModel;
import com.millionaire.compound.common.models.StockPriceModel;

import java.util.List;

public interface IIndexPriceService {

    public void saveIndexDailyPrice(List<StockPriceModel> indexPriceModels);
}
