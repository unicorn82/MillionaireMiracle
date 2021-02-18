package com.millionaire.compound.stock.service.impl;

import com.millionaire.compound.common.models.StockPriceModel;
import com.millionaire.compound.hibernate.dao.StockDailyPriceRepository;
import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;
import com.millionaire.compound.hibernate.utils.StockPriceUtil;
import com.millionaire.compound.stock.service.IStockPriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockPriceService implements IStockPriceService {

    @Resource
    StockDailyPriceRepository stockDailyPriceRepository;

    @Override
    public void saveStockDailyPrice(List<StockPriceModel> stockPriceModels) {


        List<StockPriceModel> reverseStockPriceModels = new ArrayList<>();
        String ticker = stockPriceModels.get(0).getTicker();



//        if (stockDailyPriceRepository.getStockDailyPricesByTicker(ticker).size()>0){
//            System.out.println(ticker + " has been found");
//            return;
//        }

        for (int i=stockPriceModels.size()-1;i>=0;i--){
            reverseStockPriceModels.add(stockPriceModels.get(i));


        }
        for (int i=0;i<reverseStockPriceModels.size();i++){
            String date = reverseStockPriceModels.get(i).getDate();

            StockDailyPrice dailyPrice = StockPriceUtil.convertStockDailyPrice2Enity(reverseStockPriceModels, i);

//            if(stockDailyPriceRepository.getStockDailyPricesByTickerAndDate(ticker,date).size()>0){
//                System.out.println(ticker+" "+date+" has been found");
//                continue;
//            }
            System.out.println(dailyPrice);
            if(stockDailyPriceRepository.getStockDailyPricesByTickerAndDate(dailyPrice.getTicker(),dailyPrice.getDate()).size() == 0){
                stockDailyPriceRepository.save(dailyPrice);
                System.out.println(ticker+" "+date+" save daily price");

            }else{
                System.out.println(ticker+" "+date+" has been found");
            }

        }




    }
}
