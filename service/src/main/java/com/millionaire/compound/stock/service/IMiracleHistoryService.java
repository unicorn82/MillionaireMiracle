package com.millionaire.compound.stock.service;

import com.millionaire.compound.common.models.MiracleHistoryModel;
import com.millionaire.compound.hibernate.entity.basic.MiracleHistory;

public interface IMiracleHistoryService {

    public void openHistoryRecord(MiracleHistoryModel miracleHistoryModel);

    public void closeHistoryRecord(MiracleHistoryModel miracleHistoryModel);
}

