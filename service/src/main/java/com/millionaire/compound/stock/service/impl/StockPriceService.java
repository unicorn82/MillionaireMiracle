package com.millionaire.compound.stock.service.impl;

import com.millionaire.compound.common.models.MiracleHistoryModel;
import com.millionaire.compound.common.models.StockDailyPriceCandidateModel;
import com.millionaire.compound.common.models.comparator.StockDailyPriceComparator;
import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.common.models.utils.DateUtil;
import com.millionaire.compound.hibernate.dao.MiracleStockRepository;
import com.millionaire.compound.hibernate.dao.StockDailyPriceRepository;
import com.millionaire.compound.hibernate.entity.basic.MiracleStock;
import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;
import com.millionaire.compound.hibernate.utils.StockPriceCandidateUtil;
import com.millionaire.compound.hibernate.utils.StockPriceUtil;
import com.millionaire.compound.stock.service.IStockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StockPriceService implements IStockPriceService {

    @Resource
    StockDailyPriceRepository stockDailyPriceRepository;

    @Resource
    MiracleStockRepository miracleStockRepository;

    @Autowired
    MiracleHistoryService miracleHistoryService;


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
    public List<String>  getPotentialStocks() {
        String date = DateUtil.getTodayDate();
        return getPotentialStocks(date);



    }

    @Override
    public List<String> getPotentialStocks(String date) {

        List<String> tickers = miracleStockRepository.listAllTickers();
        List<String> potentialTickers = new ArrayList<>();

        for (String ticker: tickers) {
//            ticker = "EXC";
            System.out.println("start to filter "+ ticker);
            StockDailyPrice findTarget = filterStockByFilter(ticker, date);
            if(findTarget != null){
                potentialTickers.add(ticker);


                MiracleHistoryModel miracleHistoryModel = new MiracleHistoryModel();
                miracleHistoryModel.setTicker(ticker);
                miracleHistoryModel.setBuyDate(date);
                miracleHistoryModel.setBuyPrice(findTarget.getClose().doubleValue());

                miracleHistoryService.openHistoryRecord(miracleHistoryModel);
            }

        }

        for (String ticker:potentialTickers) {
            System.out.println(ticker);

        }



        return potentialTickers;

//


//        List<StockDailyPriceCandidateModel> stockDailyPriceCandidateModels =  StockPriceCandidateUtil.convertStockDailyCandidate2Models(stockDailyPrices);
//
//        StockDailyPriceComparator stockDailyPriceComparator = new StockDailyPriceComparator();
//
//        Collections.sort(stockDailyPriceCandidateModels, stockDailyPriceComparator.reversed());

//        return stockDailyPriceCandidateModels;

    }

    private StockDailyPrice  filterStockByFilter(String ticker, String date){
        List<StockDailyPrice> stocks = stockDailyPriceRepository.getStockDailyPriceByTickerOrderByDateDesc(ticker);
        int target = validateStockDate(stocks, date);
        StockDailyPrice price = stocks.get(target);
        stocks = stocks.subList(target, stocks.size());
        if( target > 0 && validateCoverMa5Ma20(stocks)){
            System.out.println("=====ticker======"+ticker);

            return stocks.get(0);
        }

        return null;
        

    }

    private int validateStockDate(List<StockDailyPrice> stocks, String date){
        int maxLen = stocks.size();
        for(int i=0;i<maxLen;i++){
            int index = maxLen-i-1;

            if(DateUtil.formateDate2String(stocks.get(index).getDate()).equalsIgnoreCase(date)){
                return index;
            }
        }
        return -1;

    }

    private boolean validateCoverMa5Ma20(List<StockDailyPrice> stocks){
        if(stocks.size()>3) {
            StockDailyPrice dailyPrice = stocks.get(0);
            StockDailyPrice predailyPrice = stocks.get(1);
            StockDailyPrice prepredailyPrice = stocks.get(2);
            double rate = 100*(dailyPrice.getMa5().doubleValue() - dailyPrice.getMa20().doubleValue())/dailyPrice.getMa20().doubleValue();
            if(predailyPrice.getClose().doubleValue()<predailyPrice.getMa5().doubleValue() && predailyPrice.getClose().doubleValue()<predailyPrice.getMa20().doubleValue() && dailyPrice.getClose().doubleValue()>dailyPrice.getMa5().doubleValue() && rate >0 && rate <5 ){
                if(predailyPrice.getMa20().doubleValue() < dailyPrice.getMa20().doubleValue() && dailyPrice.getClose().doubleValue()>dailyPrice.getMa20().doubleValue()){{
                    if(dailyPrice.getVolume()>predailyPrice.getVolume() ) {
                        return true;
                    }
                }}


//                 }
            }
        }
        return false;

    }

}
