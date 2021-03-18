package com.millionaire.compound.common.models.comparator;

import com.millionaire.compound.common.models.StockDailyPriceCandidateModel;

import java.util.Comparator;

public class StockDailyPriceComparator implements Comparator<StockDailyPriceCandidateModel> {


    @Override
    public int compare(StockDailyPriceCandidateModel o1, StockDailyPriceCandidateModel o2) {
        double volume10Rate1 = (double)o1.getVolume()/o1.getAvg10Volume();
        double volume10Rate2 = (double)o2.getVolume()/o2.getAvg10Volume();
//        if(o1.getPassMaNum() != o2.getPassMaNum()){
//            return Integer.compare(o1.getPassMaNum(), o2.getPassMaNum());
//        }else {
            return Double.compare(volume10Rate1,volume10Rate2);
//        }

    }
}
