package com.millionaire.compound.nasdaq.service;

import com.millionaire.compound.common.models.NasdaqStockModel;
import com.millionaire.compound.common.models.StockModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IStockPoolService {

    void saveStockItem(StockModel stockModel);

    List<StockModel> listAllStocks();

}
