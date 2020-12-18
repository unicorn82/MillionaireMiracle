package com.millionaire.compound.nasdaq.service;

import com.millionaire.compound.common.models.NasdaqStockModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface INasdaqService {

    void saveNasdaqItem(NasdaqStockModel nasdaqModel);

    List<NasdaqStockModel> listNasdaqItems();

}
