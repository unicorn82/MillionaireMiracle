package com.millionaire.compound.stock.service.impl;

import com.millionaire.compound.common.models.MiracleHistoryModel;
import com.millionaire.compound.common.models.utils.DateUtil;
import com.millionaire.compound.common.models.utils.MathUtil;
import com.millionaire.compound.hibernate.dao.MiracleHistoryRepository;
import com.millionaire.compound.hibernate.entity.basic.MiracleHistory;
import com.millionaire.compound.stock.service.IMiracleHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

@Service
public class MiracleHistoryService implements IMiracleHistoryService {

    @Autowired
    MiracleHistoryRepository miracleHistoryRepository;

    @Override
    public void openHistoryRecord(MiracleHistoryModel miracleHistoryModel) {

        try {
            MiracleHistory miracleHistory = new MiracleHistory();
            miracleHistory.setTicker(miracleHistoryModel.getTicker());
            miracleHistory.setBuyPrice(BigDecimal.valueOf(miracleHistoryModel.getBuyPrice()));
            miracleHistory.setBuyDate(DateUtil.formateDate(miracleHistoryModel.getBuyDate()));
            miracleHistoryRepository.save(miracleHistory);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void closeHistoryRecord(MiracleHistoryModel miracleHistoryModel) {

        MiracleHistory miracleHistory = miracleHistoryRepository.queryMiracleHistoryByTickerAndIsClosedIsNull(miracleHistoryModel.getTicker());
        miracleHistory.setSellPrice(BigDecimal.valueOf(miracleHistoryModel.getSellPrice()));
        miracleHistory.setIsClosed(true);
        miracleHistory.setUpdateDate(new Date());
        miracleHistory.setEarning(BigDecimal.valueOf( MathUtil.getEarning(miracleHistory.getBuyPrice().doubleValue(), miracleHistory.getSellPrice().doubleValue())));
        miracleHistoryRepository.save(miracleHistory);


    }
}
