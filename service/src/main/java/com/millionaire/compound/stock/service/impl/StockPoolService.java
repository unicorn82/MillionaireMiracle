package com.millionaire.compound.stock.service.impl;

import com.millionaire.compound.common.models.StockModel;
import com.millionaire.compound.hibernate.dao.MiracleStockRepository;
import com.millionaire.compound.hibernate.entity.basic.MiracleStock;
import com.millionaire.compound.hibernate.utils.MiracleStockUtil;
import com.millionaire.compound.stock.service.IStockPoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StockPoolService implements IStockPoolService {


    @Resource
    MiracleStockRepository miracleStockRepository;

    @Override
    public void saveStockItem(StockModel stockModel) {
        MiracleStock stock = miracleStockRepository.findByTicker(stockModel.getTicker());
        if(stock !=  null){
            stockModel.setId(stock.getId());
        }
        miracleStockRepository.save(MiracleStockUtil.convertStockModeltoEntity(stockModel));

    }



    @Override
    public List<StockModel> listAllStocks() {
        List<StockModel> stockModelList = MiracleStockUtil.convertListStockEntitytomodels(miracleStockRepository.findAll());
        return stockModelList;

    }
}
