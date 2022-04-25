package com.millionaire.compound.stock.service;

import com.millionaire.compound.common.models.StockVerficationModel;
import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;

import java.util.List;

public interface IEvaluateVerificationService {

    public StockVerficationModel evaluate5Days(List<StockDailyPrice> stockDailyPrices, int target);
}
