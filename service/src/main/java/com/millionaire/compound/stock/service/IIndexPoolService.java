package com.millionaire.compound.stock.service;

import com.millionaire.compound.common.models.IndexModel;
import com.millionaire.compound.common.models.StockModel;

import java.util.List;

public interface IIndexPoolService{

    void saveIndexItem(IndexModel indexModel);

    List<IndexModel> listAllIndexes();
}
