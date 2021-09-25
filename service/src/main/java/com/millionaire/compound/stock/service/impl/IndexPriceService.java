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
    public void saveIndexDailyPrice(String ticker, List<StockPriceModel> indexPriceModels) {
        logger.info("Accept index daily price "+ticker);
        for (StockPriceModel stockPriceModel:indexPriceModels) {
            MiracleIndexDailyPrice enity = MiracleIndexPriceUtil.convertStockEntity2IndexEntity(StockPriceUtil.convertStockDailyPrice2Enity(stockPriceModel));
            if(miracleIndexPriceRespository.queryFirstByTickerAndDate(ticker, enity.getDate()) == null) {
                logger.info("Save index daily price "+ticker+" "+ DateUtil.formateDate2String(enity.getDate()));
                miracleIndexPriceRespository.save(enity);
            }
        }
        updateIndexDailyPrice(ticker);

    }


    @Override
    public List<MiracleIndexDailyPrice> listIndexDailyPrice(String ticker) {

        return miracleIndexPriceRespository.queryByTickerOrderByDateAsc(ticker);

    }

    @Override
    public void updateIndexDailyPrice(String ticker){
        Runnable runnable = () -> {
            List<MiracleIndexDailyPrice> indexDailyPrices = miracleIndexPriceRespository.getThisYearListByTickerOrderByDate(ticker);

            for(int i=0;i<indexDailyPrices.size();i++){
                MiracleIndexDailyPrice miracleIndexDailyPrice = indexDailyPrices.get(i);
                logger.info("Update Thread: {} daily price {} ", ticker,DateUtil.formateDate2String(miracleIndexDailyPrice.getDate()));
                if(miracleIndexDailyPrice.getMa5() == null){
                    logger.info("Update Thread: {} find daily price {} should be updated", ticker, DateUtil.formateDate2String(miracleIndexDailyPrice.getDate()));
                    MiracleIndexPriceUtil.convertIndexDailyPrice(indexDailyPrices,i);
                    logger.info(miracleIndexDailyPrice.toString());
                    miracleIndexPriceRespository.save(miracleIndexDailyPrice);
                }

            }
        };
        Thread t = new Thread(runnable);
        t.start();




    }
}
