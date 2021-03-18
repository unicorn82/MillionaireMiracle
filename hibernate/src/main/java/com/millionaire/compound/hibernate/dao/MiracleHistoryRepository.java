package com.millionaire.compound.hibernate.dao;

import com.millionaire.compound.hibernate.entity.basic.MiracleHistory;
import com.millionaire.compound.hibernate.entity.basic.StockDailyPrice;

import java.util.List;

public interface MiracleHistoryRepository extends ExtendedRepository<MiracleHistory, Integer> {

    MiracleHistory queryMiracleHistoryByTickerAndIsClosedIsNull(String ticker);





}
