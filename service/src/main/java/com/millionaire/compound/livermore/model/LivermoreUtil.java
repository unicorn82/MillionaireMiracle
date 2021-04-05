package com.millionaire.compound.livermore.model;

import com.millionaire.compound.common.models.utils.DateUtil;
import com.millionaire.compound.hibernate.entity.basic.MiracleIndexDailyPrice;

import java.util.ArrayList;
import java.util.List;

public class LivermoreUtil {

    public static List<LivermoreModel> convertLivermoreModelsfromEntity(List<MiracleIndexDailyPrice> miracleIndexDailyPriceList){
        List<LivermoreModel> livermoreModelList = new ArrayList<>();
        for (MiracleIndexDailyPrice miracleIndexDailyPrice: miracleIndexDailyPriceList) {
            livermoreModelList.add(convertLivermoreModelfromEntity(miracleIndexDailyPrice));
        }
        return livermoreModelList;
    }

    public static LivermoreModel convertLivermoreModelfromEntity(MiracleIndexDailyPrice miracleIndexDailyPrice){
        LivermoreModel livermoreModel = new LivermoreModel();
        livermoreModel.setTicker(miracleIndexDailyPrice.getTicker());
        livermoreModel.setClose(miracleIndexDailyPrice.getClose().doubleValue());
        livermoreModel.setOpen(miracleIndexDailyPrice.getOpen().doubleValue());
        livermoreModel.setDate(DateUtil.formateDate2String(miracleIndexDailyPrice.getDate()));
        return livermoreModel;
    }
}
