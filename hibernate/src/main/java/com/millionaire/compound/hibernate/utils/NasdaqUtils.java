package com.millionaire.compound.hibernate.utils;

import com.millionaire.compound.common.models.NasdaqStockModel;
import com.millionaire.compound.hibernate.entity.basic.MiracleStock;

import java.util.ArrayList;
import java.util.List;

public class NasdaqUtils {
    private static  String _NASDAQ_CATEGORY = "NASDAQ";

    public static MiracleStock covertNasdaqModel2Entity(NasdaqStockModel nasdaqStockModel){
        MiracleStock entity = new MiracleStock();
        entity.setId(nasdaqStockModel.getId());
        entity.setCompany(nasdaqStockModel.getCompany());
        entity.setMarketCap(nasdaqStockModel.getMarketCap());
        entity.setMarketCapGroup(nasdaqStockModel.getMarketCapGroup());
        entity.setSector(nasdaqStockModel.getSector());
        entity.setSectorName(nasdaqStockModel.getSectorName());
        entity.setTicker(nasdaqStockModel.getTicker());

        entity.setCategory(_NASDAQ_CATEGORY);

        return entity;

    }

    public static NasdaqStockModel convertNasdaqEntity2Model(MiracleStock nasdaqStock){
        NasdaqStockModel model = new NasdaqStockModel();
        model.setId(nasdaqStock.getId());
        model.setCompany(nasdaqStock.getCompany());
        model.setMarketCap(nasdaqStock.getMarketCap());
        model.setMarketCapGroup(nasdaqStock.getMarketCapGroup());
        model.setSector(nasdaqStock.getSector());
        model.setSectorName(nasdaqStock.getSectorName());
        model.setTicker(nasdaqStock.getTicker());
        model.setCategory(nasdaqStock.getCategory());
        return model;
    }

    public static List<NasdaqStockModel> convertNasdaqModelList(Iterable<MiracleStock> nasdaqStockList){
        List<NasdaqStockModel> nasdaqStockModelList = new ArrayList<>();
        nasdaqStockList.forEach(nasdaqStock -> {
            nasdaqStockModelList.add(convertNasdaqEntity2Model(nasdaqStock));
        });
        return nasdaqStockModelList;
    }
}
