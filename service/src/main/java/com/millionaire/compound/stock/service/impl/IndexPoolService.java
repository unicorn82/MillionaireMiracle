package com.millionaire.compound.stock.service.impl;

import com.millionaire.compound.common.models.IndexModel;
import com.millionaire.compound.hibernate.dao.MiracleIndexRepository;
import com.millionaire.compound.hibernate.entity.basic.MiracleIndex;
import com.millionaire.compound.hibernate.utils.MiracleIndexUtil;
import com.millionaire.compound.stock.service.IIndexPoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IndexPoolService implements IIndexPoolService {


    @Resource
    MiracleIndexRepository miracleIndexRepository;

    @Override
    public void saveIndexItem(IndexModel indexModel) {

        if(miracleIndexRepository.findByTicker(indexModel.getTicker()) == null) {
            MiracleIndex miracleIndex = MiracleIndexUtil.convertIndexModeltoEntity(indexModel);
            miracleIndexRepository.save(miracleIndex);
        }

    }

    @Override
    public List<IndexModel> listAllIndexes() {
        List<MiracleIndex> miracleIndices = miracleIndexRepository.findAll();
        return MiracleIndexUtil.convertListIndexEntitytoModels(miracleIndices);
    }
}
