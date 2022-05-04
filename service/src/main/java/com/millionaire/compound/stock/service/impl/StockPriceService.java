package com.millionaire.compound.stock.service.impl;

import com.millionaire.compound.common.models.MiracleHistoryModel;
import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.common.models.StockVerficationModel;
import com.millionaire.compound.common.models.utils.CommonUtil;
import com.millionaire.compound.common.models.utils.DateUtil;
import com.millionaire.compound.hibernate.dao.MiracleStockRepository;
import com.millionaire.compound.hibernate.dao.StockDailyPriceRepository;
import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;
import com.millionaire.compound.hibernate.utils.StockPriceUtil;
import com.millionaire.compound.stock.service.IEvaluateVerificationService;
import com.millionaire.compound.stock.service.IStockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockPriceService implements IStockPriceService {

    @Resource
    StockDailyPriceRepository stockDailyPriceRepository;

    @Resource
    MiracleStockRepository miracleStockRepository;

    @Autowired
    MiracleHistoryService miracleHistoryService;

    @Autowired
    IEvaluateVerificationService evaluateVerification;


    @Override
    public void saveStockDailyPrice(String ticker, List<StockPriceModel> stockPriceModels) {

        for (int i=0;i<stockPriceModels.size();i++){
            String date = stockPriceModels.get(i).getDate();

            StockDailyPrice dailyPrice = StockPriceUtil.convertStockDailyPrice2Enity(stockPriceModels.get(i));

            System.out.println(dailyPrice);
            if(stockDailyPriceRepository.getStockDailyPriceByTickerAndDate(dailyPrice.getTicker(),dailyPrice.getDate()).size() == 0){
                stockDailyPriceRepository.save(dailyPrice);
                System.out.println(ticker+" "+date+" save daily price");

            }

        }
        updateStockDailyPrice(ticker);



    }

    @Override
    public void updateStockDailyPrice(String ticker) {
        Runnable runnable = () -> {
            List<StockDailyPrice> dailyPrices = stockDailyPriceRepository.getThisYearListByTickerOrderByDate(ticker);
            for (int i = 0; i < dailyPrices.size(); i++) {
                StockDailyPrice currentDailyPrice = dailyPrices.get(i);
                if (currentDailyPrice.getMa5() == null || CommonUtil.isDoubleZero(currentDailyPrice.getRange().doubleValue())) {
                    StockPriceUtil.updateStockDailyPrice(dailyPrices, i);
                    System.out.println(currentDailyPrice.getTicker() + " " + DateUtil.formateDate2String(currentDailyPrice.getDate()) + " " + currentDailyPrice.getAvg5Volume());
                    stockDailyPriceRepository.save(currentDailyPrice);
                }
            }
        };
        Thread t = new Thread(runnable);
        t.start();

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
//            String ticker = "PXLW";
//            System.out.println("start to filter "+ ticker);if
//            if(!ticker.equalsIgnoreCase("TSLA")){
//                System.out.println("skip");
//                continue;
//            }
            List<StockDailyPrice> stocks = stockDailyPriceRepository.getStockDailyPriceByTickerOrderByDateDesc(ticker);
            int target = validateStockDate(stocks, date);
            if(target >= 0 ){
                if(this.validateTouchMa20(stocks, target)){
                    System.out.println("==========="+ticker+"=====================");
                    potentialTickers.add(ticker);
                }
//                if(this.validate520(stocks, target)){
//                    System.out.println("==========="+ticker+"=====================");
//                    potentialTickers.add(ticker);
//                }
            }


//            if(findTarget != null){
//                potentialTickers.add(ticker);
//
//
//                MiracleHistoryModel miracleHistoryModel = new MiracleHistoryModel();
//                miracleHistoryModel.setTicker(ticker);
//                miracleHistoryModel.setBuyDate(date);
//                miracleHistoryModel.setBuyPrice(findTarget.getClose().doubleValue());
//
//                miracleHistoryService.openHistoryRecord(miracleHistoryModel);
//            }

        }

//        for (String ticker:potentialTickers) {
//            System.out.println(ticker);
//
//        }



        return potentialTickers;

//


//        List<StockDailyPriceCandidateModel> stockDailyPriceCandidateModels =  StockPriceCandidateUtil.convertStockDailyCandidate2Models(stockDailyPrices);
//
//        StockDailyPriceComparator stockDailyPriceComparator = new StockDailyPriceComparator();
//
//        Collections.sort(stockDailyPriceCandidateModels, stockDailyPriceComparator.reversed());

//        return stockDailyPriceCandidateModels;

    }

    private void verifyStockDailyCandidateByTicker(String ticker){
        List<StockVerficationModel> list = new ArrayList<>();
        List<StockDailyPrice> stocks = stockDailyPriceRepository.getStockDailyPriceByTickerOrderByDateDesc(ticker);
        double earning_1 = 1;
        double earning_3 = 1;
        double earning_5 = 1;
        int pos_1 = 0;
        int neg_1 = 0;
        int pos_3 = 0;
        int neg_3 = 0;
        int pos_5 =0;
        int neg_5 =0;

        for (int i = 5; i < stocks.size()-5; i++) {
            StockDailyPrice stockDailyPrice = stocks.get(i);


            if(validateCoverMa5Ma20(stocks, i)){

                StockVerficationModel stockVerficationModel = evaluateVerification.evaluate5Days(stocks,i);
                stockVerficationModel.setTicker(ticker);
                stockVerficationModel.setDate(DateUtil.formateDate2String(stockDailyPrice.getDate()));
                list.add(stockVerficationModel);
                earning_1 = earning_1*(1+stockVerficationModel.getEarning_1()/100);
                earning_3 = earning_3*(1+stockVerficationModel.getEarning_3()/100);
                earning_5 = earning_5*(1+stockVerficationModel.getEarning_5()/100);

                if(stockVerficationModel.getEarning_1()>0){
                    pos_1++;
                }else {
                    neg_1++;
                }
                if(stockVerficationModel.getEarning_3()>0){
                    pos_3++;
                }else {
                    neg_3++;
                }
                if(stockVerficationModel.getEarning_5()>0){
                    pos_5++;
                }else {
                    neg_5++;
                }


                System.out.println(stockDailyPrice.getDate() +  " earning 1 = "+stockVerficationModel.getEarning_1()+
                        " earning 3 = "+stockVerficationModel.getEarning_3() + " earning 5 = "+stockVerficationModel.getEarning_5());


            }
        }
        System.out.println("==============================="+ticker+"============================");
        System.out.println("earning 1 "+earning_1+" earning 3 "+earning_3+" earning 5 "+earning_5);
        System.out.println(pos_1+"/"+neg_1+"; "+pos_3+"/"+neg_3+"; "+pos_5+"/"+neg_5);

    }

    @Override
    public void verifyStockDailyCandidate(String ticker) {
        if(ticker.equalsIgnoreCase("ALL")){
            List<String> tickers = miracleStockRepository.listAllTickers();
            for (String t:tickers) {
                verifyStockDailyCandidateByTicker(t);
            }
        }else{
            verifyStockDailyCandidateByTicker(ticker);
        }

        return ;

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

    private boolean validate520(List<StockDailyPrice> stocks, int target){
        if(stocks.size()>30) {
            StockDailyPrice dailyPrice = stocks.get(target);
            if(dailyPrice.getVolume() < 800000){
                return false;
            }
            StockDailyPrice predailyPrice = stocks.get(target + 1);
            if(predailyPrice.getMa5().doubleValue()<predailyPrice.getMa60().doubleValue() && dailyPrice.getMa5().doubleValue()>dailyPrice.getMa60().doubleValue()){
                if(predailyPrice.getClose().doubleValue() < predailyPrice.getMa20().doubleValue() && dailyPrice.getClose().doubleValue() > dailyPrice.getMa20().doubleValue()){
                    for(int i=1;i<10;i++){
                        StockDailyPrice daily = stocks.get(target +i);
                        if(daily.getMa5().doubleValue() > daily.getMa60().doubleValue()){
                            return false;
                        }
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private boolean validateTouchMa20(List<StockDailyPrice> stocks, int target){
        if(stocks.size()>target+30) {
            StockDailyPrice dailyPrice = stocks.get(target);
            System.out.println("verify "+dailyPrice.getTicker()+" "+dailyPrice.getDate());
            if(dailyPrice.getVolume() < 800000){
                return false;
            }

            StockDailyPrice predailyPrice = stocks.get(target + 1);
            if( dailyPrice.getClose().doubleValue()>dailyPrice.getMa5().doubleValue() && dailyPrice.getMa5().doubleValue()>dailyPrice.getMa20().doubleValue()&& dailyPrice.getMa20().doubleValue() > dailyPrice.getMa60().doubleValue() ){
                double rate = (dailyPrice.getMa5().doubleValue() - dailyPrice.getMa20().doubleValue())*100/dailyPrice.getMa20().doubleValue();
                if(dailyPrice.getRange().doubleValue()>0 && rate < 3 && rate > 0 && predailyPrice.getMa5().doubleValue() > dailyPrice.getMa20().doubleValue() && dailyPrice.getMa5().doubleValue()>predailyPrice.getMa5().doubleValue()){
//                    StockDailyPrice predailyPrice = stocks.get(target + 1);
                    return true;
                }
//                if(dailyPrice.getLow().doubleValue() < 1.02*dailyPrice.getMa20().doubleValue() && dailyPrice.getClose().doubleValue() > dailyPrice.getMa5().doubleValue()){
//                    return true;
//                }

//                if(dailyPrice.getMa20().doubleValue() >predailyPrice.getMa20().doubleValue() && dailyPrice.getMa5().doubleValue() > dailyPrice.getMa20().doubleValue() && dailyPrice.getMa20().doubleValue() > dailyPrice.getMa30().doubleValue()) {
//                    double rate1 = 100*(dailyPrice.getMa20().doubleValue() - predailyPrice.getMa20().doubleValue())/predailyPrice.getMa20().doubleValue();
//                    double rate2 = 100*(dailyPrice.getMa5().doubleValue()-dailyPrice.getMa20().doubleValue())/dailyPrice.getMa20().doubleValue();
//                    if(rate1 > 0.3 && rate2 < 5 && dailyPrice.getLow().doubleValue()<dailyPrice.getMa20().doubleValue() && dailyPrice.getClose().doubleValue()> dailyPrice.getMa20().doubleValue()){
//                        return true;
//                    }
//
//
////                    for (int i = 1; i < 5; i++) {
//////                        StockDailyPrice next = stocks.get(target+i);
////                        if (validateMa5Ma20Crossing(stocks, target + i)) {
////                            return true;
////                        }
////
////                    }
//                }



            }

        }
        return false;
    }

    private boolean validateMa5Ma20Crossing(List<StockDailyPrice> stocks, int target){
        if(stocks.size()>3) {
            StockDailyPrice dailyPrice = stocks.get(target);
            StockDailyPrice predailyPrice = stocks.get(target + 1);
            if(predailyPrice.getMa5().doubleValue() < predailyPrice.getMa20().doubleValue() && dailyPrice.getMa5().doubleValue() > dailyPrice.getMa20().doubleValue() && dailyPrice.getMa10().doubleValue() > predailyPrice.getMa10().doubleValue()){
                double rate = (dailyPrice.getClose().doubleValue() - dailyPrice.getMa20().doubleValue())/dailyPrice.getMa20().doubleValue();
                if(rate >0 && rate < 0.05){
                    return true;
                }
            }

        }
        return false;

    }

    private boolean validateCoverMa5Ma20(List<StockDailyPrice> stocks, int target){
        if(stocks.size()>3) {
            StockDailyPrice dailyPrice = stocks.get(target);
            StockDailyPrice predailyPrice = stocks.get(target+1);
            StockDailyPrice prepredailyPrice = stocks.get(target+2);

            double rate = 100*(dailyPrice.getMa5().doubleValue() - dailyPrice.getMa20().doubleValue())/dailyPrice.getMa20().doubleValue();
            if(predailyPrice.getClose().doubleValue()<predailyPrice.getMa5().doubleValue() && predailyPrice.getClose().doubleValue()<predailyPrice.getMa20().doubleValue()
                    && dailyPrice.getClose().doubleValue()>dailyPrice.getMa5().doubleValue() && dailyPrice.getClose().doubleValue()>dailyPrice.getMa20().doubleValue()  ){
                if(predailyPrice.getMa20().doubleValue() < dailyPrice.getMa20().doubleValue() && dailyPrice.getClose().doubleValue()>dailyPrice.getMa20().doubleValue()){
                    if(dailyPrice.getVolume()>2*dailyPrice.getAvg5Volume() && dailyPrice.getRange().doubleValue()>0.05) {
                        return true;
                    }
                    }
                }


//                 }

        }
        return false;

    }



//    private boolean validateCoverMa5Ma20(List<StockDailyPrice> stocks, int target){
//        if(stocks.size()>3) {
//            StockDailyPrice dailyPrice = stocks.get(target);
//            StockDailyPrice predailyPrice = stocks.get(target+1);
//            StockDailyPrice prepredailyPrice = stocks.get(target+2);
//            double rate = 100*(dailyPrice.getMa5().doubleValue() - dailyPrice.getMa20().doubleValue())/dailyPrice.getMa20().doubleValue();
//            if(predailyPrice.getClose().doubleValue()<predailyPrice.getMa5().doubleValue() && predailyPrice.getClose().doubleValue()<predailyPrice.getMa20().doubleValue() && dailyPrice.getClose().doubleValue()>dailyPrice.getMa5().doubleValue() && rate >0 && rate <5 ){
//                if(predailyPrice.getMa20().doubleValue() < dailyPrice.getMa20().doubleValue() && dailyPrice.getClose().doubleValue()>dailyPrice.getMa20().doubleValue()){{
//                    if(dailyPrice.getVolume()>predailyPrice.getVolume() ) {
//                        return true;
//                    }
//                }}
//
//
////                 }
//            }
//        }
//        return false;
//
//    }

    private boolean validateKline1(List<StockDailyPrice> stocks){
        StockDailyPrice dailyPrice = stocks.get(0);
        StockDailyPrice predailyPrice = stocks.get(1);
//        StockDailyPrice nextdailyPrice = stocks.get(0);
        if(dailyPrice.getOpen().doubleValue()<dailyPrice.getMa5().doubleValue() && dailyPrice.getMa5().doubleValue() < dailyPrice.getMa10().doubleValue() && dailyPrice.getMa10().doubleValue()<dailyPrice.getMa20().doubleValue() ){
          if(predailyPrice.getRange().doubleValue()<0 && dailyPrice.getVolume() > predailyPrice.getVolume() && dailyPrice.getVolume() > dailyPrice.getAvg5Volume()){
              return true;
          }
        }
        return false;
    }

}
