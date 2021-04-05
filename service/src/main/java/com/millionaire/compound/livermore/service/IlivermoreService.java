package com.millionaire.compound.livermore.service;

import com.millionaire.compound.livermore.model.LivermoreModel;
import com.millionaire.compound.livermore.model.LivermoreResponse;

import java.util.List;

public interface IlivermoreService {

    public List<LivermoreModel> processLivermoreForTicker(String ticker);

}
