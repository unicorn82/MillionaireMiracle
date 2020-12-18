package com.millionaire.compound.nasdaq.service.impl;

import com.millionaire.compound.common.models.NasdaqStockModel;
import com.millionaire.compound.hibernate.dao.impl.NasdaqRepository;
import com.millionaire.compound.hibernate.utils.NasdaqUtils;
import com.millionaire.compound.nasdaq.service.INasdaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NasdaqService implements INasdaqService {


    @Autowired
    NasdaqRepository nasdaqRepository;

    @Override
    public void saveNasdaqItem(NasdaqStockModel nasdaqModel) {
        nasdaqRepository.save(NasdaqUtils.covertNasdaqModel2Entity(nasdaqModel));

    }



    @Override
    public List<NasdaqStockModel> listNasdaqItems() {
        System.out.println("list nasdaq items");
//        System.out.println(nasdaqRepository.findAll());
        List<NasdaqStockModel> nasdaqStockModelList = NasdaqUtils.convertNasdaqModelList(nasdaqRepository.findAll());
        return nasdaqStockModelList;

    }
}
