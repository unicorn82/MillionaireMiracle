package com.millionaire.compound.livermore.service.impl;

import com.millionaire.compound.common.models.utils.MathUtil;
import com.millionaire.compound.hibernate.dao.MiracleIndexRepository;
import com.millionaire.compound.hibernate.entity.basic.MiracleIndexDailyPrice;
import com.millionaire.compound.livermore.model.LivermoreModel;
import com.millionaire.compound.livermore.model.LivermoreResponse;
import com.millionaire.compound.livermore.model.LivermoreUtil;
import com.millionaire.compound.livermore.service.IlivermoreService;
import com.millionaire.compound.stock.service.IIndexPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivermoreIndexService extends AbstractLivermoreService implements IlivermoreService {

    @Autowired
    MiracleIndexRepository miracleIndexRepository;



    @Autowired
    IIndexPriceService indexPriceService;

    private final double threshhold = 0.06;
    private final double returnThreshold = 0.03;


    protected void getNextModelStatus(LivermoreModel previous, LivermoreModel current) {
//        System.out.println("current close = "+current.getClose());
//        System.out.println("previous status "+previous.getStatus());
        switch (previous.getStatus()) {
            case START:
            case UP:
                getNextUpModelStatus(current);
                break;
            case RETRACE :

                getNextRetraceModelStatus(current);

                break;
            case REBOUND:
                getNextReboundModelStatus(current);
                break;
            case DOWN:
                getNextDownModelStatus(current);
                break;
                default:
                    break;

        }
    }

    private void getNextUpModelStatus(LivermoreModel current){
        isSecondRetrace = false;
        double earning = MathUtil.getEarning(getUpPointClose(), current.getClose());
//        System.out.println("next up model earning = "+ earning);
        if(earning>0){
            current.setStatus(LIVERMORE_STATUS.UP);
            livermoreTable.put(UpPoint,current);
        }else if(earning > (0-threshhold)) {
            current.setStatus(LIVERMORE_STATUS.UP);

        }else{
            current.setStatus(LIVERMORE_STATUS.RETRACE);
            livermoreTable.put(FallPoint, current);
            setSellModel(current);

        }


    }

    private void getNextRetraceModelStatus(LivermoreModel current){

        double earning1 = MathUtil.getEarning(getUpPointClose(), current.getClose());
//        System.out.println("next retrace model earning = "+ earning1);
        if(earning1 > returnThreshold){ // up
            current.setStatus(LIVERMORE_STATUS.UP);
            livermoreTable.put(UpPoint,current);
//            livermoreTable.put(ReboundPoint, current);
            setBuyModel(current);

        }else {
            double earning2 = MathUtil.getEarning(getFallPointClose(), current.getClose());
            if(earning2 > returnThreshold){ //rebound
                current.setStatus(LIVERMORE_STATUS.REBOUND);
                livermoreTable.put(ReboundPoint, current);
            }else{

                if(isSecondRetrace && earning2 < (0-returnThreshold)) {
                    current.setStatus(LIVERMORE_STATUS.DOWN);
                    livermoreTable.put(DownPoint, current);
                }else{
                    current.setStatus(LIVERMORE_STATUS.RETRACE);
                    if(current.getClose() < getFallPointClose() && !isSecondRetrace) {
                        livermoreTable.put(FallPoint, current);
                    }



                }



            }
        }

    }


    private void getNextReboundModelStatus(LivermoreModel current){
        double earning1 = MathUtil.getEarning(getUpPointClose(), current.getClose());
        if(earning1 > returnThreshold){ // up
            current.setStatus(LIVERMORE_STATUS.UP);
            livermoreTable.put(UpPoint,current);
//            livermoreTable.put(ReboundPoint, current);
            setBuyModel(current);
        }else {
            double earning2 = MathUtil.getEarning(getFallPointClose(), current.getClose());
            if(earning2 < (0-returnThreshold)){//return to down
                current.setStatus(LIVERMORE_STATUS.DOWN);
                livermoreTable.put(DownPoint, current);
            } else {//in rebound
                if(current.getClose()>getReboundPointClose()){
                    double earning3 = MathUtil.getEarning(getReboundPointClose(), current.getClose());

                    if(isSecondRetrace && earning3>returnThreshold ){
                        current.setStatus(LIVERMORE_STATUS.UP);
                        livermoreTable.put(UpPoint,current);
//                        livermoreTable.put(ReboundPoint, current);
                        setBuyModel(current);

                    }else {
                        current.setStatus(LIVERMORE_STATUS.REBOUND);
                        if(!isSecondRetrace) {
                            livermoreTable.put(ReboundPoint, current);
                        }
                    }

                }else{
                    double earning3 = MathUtil.getEarning(getReboundPointClose(), current.getClose());
                    if(earning3 < (0-returnThreshold)) {
                        //return to retrace column
                        isSecondRetrace = true;
                        current.setStatus(LIVERMORE_STATUS.RETRACE);
                    }else{
                        current.setStatus(LIVERMORE_STATUS.REBOUND);
                    }

                }
            }

        }

    }

    private void getNextDownModelStatus(LivermoreModel current){
//        System.out.println("get down point "+getDownPointClose());
        isSecondRetrace = false;
        if(current.getClose() < getDownPointClose()){
            current.setStatus(LIVERMORE_STATUS.DOWN);
            livermoreTable.put(DownPoint, current);
        }else{
            double earning1 = MathUtil.getEarning(getDownPointClose(), current.getClose());
            if(earning1 > threshhold){ // rebound
                current.setStatus(LIVERMORE_STATUS.REBOUND);
                livermoreTable.put(FallPoint, current);

            }else {
                current.setStatus(LIVERMORE_STATUS.DOWN);
            }
        }
    }


    public List<LivermoreModel> processLivermoreForTicker(String ticker){
        LivermoreResponse response = new LivermoreResponse();
        List<MiracleIndexDailyPrice > entities = indexPriceService.listIndexDailyPrice(ticker);
        List<LivermoreModel> livermoreModelList = LivermoreUtil.convertLivermoreModelsfromEntity(entities);
        process(livermoreModelList);
        response.setLivermoreModelList(livermoreModelList);
        return livermoreModelList;
    }



}
