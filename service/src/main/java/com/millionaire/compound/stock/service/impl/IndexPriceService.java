package com.millionaire.compound.stock.service.impl;

import com.millionaire.compound.common.models.IndexPriceModel;
import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.common.models.utils.DateUtil;
import com.millionaire.compound.hibernate.dao.MiracleIndexPriceRespository;
import com.millionaire.compound.hibernate.entity.basic.MiracleIndexDailyPrice;
import com.millionaire.compound.hibernate.utils.MiracleIndexPriceUtil;
import com.millionaire.compound.hibernate.utils.MiracleIndexUtil;
import com.millionaire.compound.hibernate.utils.StockPriceUtil;
import com.millionaire.compound.stock.service.IIndexPoolService;
import com.millionaire.compound.stock.service.IIndexPriceService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Service
public class IndexPriceService implements IIndexPriceService {

    @Resource
    MiracleIndexPriceRespository miracleIndexPriceRespository;



    private static final Logger logger = LoggerFactory.getLogger(IndexPriceService.class);

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
                        miracleIndexPriceRespository.queryFirstByTickerAndDate(indexPriceModel.getTicker(), DateUtil.formateDate(indexPriceModel.getDate(), StockPriceUtil.datePattern));
                if(miracleIndexDailyPrice != null){
                    miracleIndexPriceRespository.delete(miracleIndexDailyPrice);
                }
                miracleIndexPriceRespository.save(MiracleIndexPriceUtil.convertStockDailyPrice2Enity(reversedIndexPriceModels, i));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void saveIndexDailyPrice(String ticker, List<StockPriceModel> indexPriceModels) {
        logger.info("Accept index daily price "+ticker);
        for (StockPriceModel stockPriceModel:indexPriceModels) {
            MiracleIndexDailyPrice enity = MiracleIndexPriceUtil.convertStockEntity2IndexEntity(StockPriceUtil.convertStockDailyPrice2Enity(stockPriceModel));
            if(miracleIndexPriceRespository.queryFirstByTickerAndDate(ticker, enity.getDate()) == null) {
                logger.info("Save index daily price "+ticker+" "+ DateUtil.formateDate2String(enity.getDate()));
                miracleIndexPriceRespository.save(enity);
            }
        }

    }


    @Override
    public List<MiracleIndexDailyPrice> listIndexDailyPrice(String ticker) {

        return miracleIndexPriceRespository.queryByTickerOrderByDateAsc(ticker);

    }
}
