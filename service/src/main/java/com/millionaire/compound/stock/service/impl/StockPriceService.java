package com.millionaire.compound.stock.service.impl;

import com.millionaire.compound.common.models.StockDailyPriceCandidateModel;
import com.millionaire.compound.common.models.comparator.StockDailyPriceComparator;
import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.common.models.utils.DateUtil;
import com.millionaire.compound.hibernate.dao.StockDailyPriceRepository;
import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;
import com.millionaire.compound.hibernate.utils.StockPriceCandidateUtil;
import com.millionaire.compound.hibernate.utils.StockPriceUtil;
import com.millionaire.compound.stock.service.IStockPriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StockPriceService implements IStockPriceService {

    @Resource
    StockDailyPriceRepository stockDailyPriceRepository;

    @Override
    public void saveStockDailyPrice(String ticker, List<StockPriceModel> stockPriceModels) {

//        List<StockPriceModel> reverseStockPriceModels = new ArrayList<>();

//        for (int i=stockPriceModels.size()-1;i>=0;i--){
//            reverseStockPriceModels.add(stockPriceModels.get(i));
//
//
//        }
        for (int i=0;i<stockPriceModels.size();i++){
            String date = stockPriceModels.get(i).getDate();

            StockDailyPrice dailyPrice = StockPriceUtil.convertStockDailyPrice2Enity(stockPriceModels, i);
            System.out.println(dailyPrice);
            if(stockDailyPriceRepository.getStockDailyPriceByTickerAndDate(dailyPrice.getTicker(),dailyPrice.getDate()).size() == 0){
                stockDailyPriceRepository.save(dailyPrice);
                System.out.println(ticker+" "+date+" save daily price");

            }else{
                System.out.println(ticker+" "+date+" has been found");
            }

        }




    }

    @Override
    public List<StockDailyPriceCandidateModel>  getPotentialStocks() {
        String date = DateUtil.getTodayDate();
        return getPotentialStocks(date);



    }

    @Override
    public List<StockDailyPriceCandidateModel> getPotentialStocks(String date) {
        List<StockDailyPrice> stockDailyPrices = stockDailyPriceRepository.getPotentialStocks(date);


        List<StockDailyPriceCandidateModel> stockDailyPriceCandidateModels =  StockPriceCandidateUtil.convertStockDailyCandidate2Models(stockDailyPrices);

        StockDailyPriceComparator stockDailyPriceComparator = new StockDailyPriceComparator();

        Collections.sort(stockDailyPriceCandidateModels, stockDailyPriceComparator.reversed());

        return stockDailyPriceCandidateModels;
    }

}
