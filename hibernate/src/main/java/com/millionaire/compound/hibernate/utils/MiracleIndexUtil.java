package com.millionaire.compound.hibernate.utils;

import com.millionaire.compound.common.models.IndexModel;
import com.millionaire.compound.common.models.StockModel;
import com.millionaire.compound.hibernate.entity.basic.MiracleIndex;
import com.millionaire.compound.hibernate.entity.basic.MiracleStock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MiracleIndexUtil {

    public static MiracleIndex convertIndexModeltoEntity(IndexModel indexModel){
        MiracleIndex miracleStock = new MiracleIndex();
        miracleStock.setDescription(indexModel.getDescription());
        miracleStock.setId(indexModel.getId());
        miracleStock.setTicker(indexModel.getTicker());

        return miracleStock;

    }

    public static IndexModel convertIndexEntitytoModel(MiracleIndex miracleIndex){
        IndexModel indexModel = new IndexModel();
        indexModel.setDescription(miracleIndex.getDescription());
        indexModel.setTicker(miracleIndex.getTicker());
        indexModel.setId(miracleIndex.getId());
        return indexModel;


    }

    public static List<IndexModel> convertListIndexEntitytoModels(List<MiracleIndex> miracleIndices) {
        List<IndexModel> indexModels = new ArrayList<>();
        for (MiracleIndex miracleIndex :miracleIndices) {
           indexModels.add(convertIndexEntitytoModel(miracleIndex));
        }
        return indexModels;
    }
}
