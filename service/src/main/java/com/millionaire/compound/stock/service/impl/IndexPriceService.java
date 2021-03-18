package com.millionaire.compound.stock.service.impl;

import com.millionaire.compound.common.models.IndexPriceModel;
import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.common.models.utils.DateUtil;
import com.millionaire.compound.hibernate.dao.MiracleIndexPriceRespository;
import com.millionaire.compound.hibernate.entity.basic.MiracleIndexDailyPrice;
import com.millionaire.compound.hibernate.utils.MiracleIndexPriceUtil;
import com.millionaire.compound.hibernate.utils.MiracleIndexUtil;
import com.millionaire.compound.stock.service.IIndexPoolService;
import com.millionaire.compound.stock.service.IIndexPriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IndexPriceService implements IIndexPriceService {

    @Resource
    MiracleIndexPriceRespository miracleIndexPriceRespository;


    @Override
    public void saveIndexDailyPrice(List<StockPriceModel> indexPriceModels) {

        List<StockPriceModel> reversedIndexPriceModels = new ArrayList<>();


        for (int i=indexPriceModels.size()-1;i>=0;i--){
            reversedIndexPriceModels.add(indexPriceModels.get(i));

        }

        for (int i=0; i<reversedIndexPriceModels.size(); i++) {
//        for(IndexPriceModel indexPriceModel : reversedIndexPriceModels){
            StockPriceModel indexPriceModel = reversedIndexPriceModels.get(i);

//            MiracleIndexDailyPrice miracleIndexDailyPrice = MiracleIndexPriceUtil.convertStockDailyPrice2Enity(indexPriceModel);
            try {
                MiracleIndexDailyPrice miracleIndexDailyPrice =
                        miracleIndexPriceRespository.queryFirstByTickerAndDate(indexPriceModel.getTicker(), DateUtil.formateDate(indexPriceModel.getDate()));
                if(miracleIndexDailyPrice != null){
                    miracleIndexPriceRespository.delete(miracleIndexDailyPrice);
                }
                miracleIndexPriceRespository.save(MiracleIndexPriceUtil.convertStockDailyPrice2Enity(reversedIndexPriceModels, i));
            } catch (ParseException e) {
                e.printStackTrace();
            }



        }



    }
}
