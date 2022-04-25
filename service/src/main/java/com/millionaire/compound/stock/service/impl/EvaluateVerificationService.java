package com.millionaire.compound.stock.service.impl;

import com.millionaire.compound.common.models.StockVerficationModel;
import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;
import com.millionaire.compound.stock.service.IEvaluateVerificationService;
import com.millionaire.compound.stock.service.utils.EvaluationUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateVerificationService implements IEvaluateVerificationService {
    @Override
    public StockVerficationModel evaluate5Days(List<StockDailyPrice> stockDailyPrices, int target) {
        final int _day = 5;
        StockVerficationModel stockVerficationModel = new StockVerficationModel();
        StockDailyPrice currentDailyPrice = stockDailyPrices.get(target);
        for(int i=1;i<_day;i++){
            StockDailyPrice targetDailyPrice = stockDailyPrices.get(target-i);
            if(i<=1){
                stockVerficationModel.setClose_1(targetDailyPrice.getClose().doubleValue());
                stockVerficationModel.setEarning_1(EvaluationUtils.getEarning(currentDailyPrice, targetDailyPrice));
            }
            if(i<=3){
                stockVerficationModel.setClose_3(targetDailyPrice.getClose().doubleValue());
                stockVerficationModel.setEarning_3(EvaluationUtils.getEarning(currentDailyPrice, targetDailyPrice));

            }
            if(i<=5){
                stockVerficationModel.setClose_5(targetDailyPrice.getClose().doubleValue());
                stockVerficationModel.setEarning_5(EvaluationUtils.getEarning(currentDailyPrice, targetDailyPrice));

            }

        }

        return stockVerficationModel;
    }
}
