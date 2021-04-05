package com.millionaire.compound.livermore.service.impl;

import com.millionaire.compound.common.models.IndexPriceModel;
import com.millionaire.compound.common.models.utils.MathUtil;
import com.millionaire.compound.livermore.model.LivermoreModel;
import com.millionaire.compound.livermore.model.LivermoreResponse;
import com.millionaire.compound.livermore.service.IlivermoreService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public abstract class AbstractLivermoreService implements IlivermoreService {

    public static enum LIVERMORE_STATUS{
        START,
        UP,
        DOWN,
        RETRACE,
        REBOUND,
    }

    protected boolean isSecondRetrace = false;


    protected Map<String, LivermoreModel> livermoreTable = new HashMap<>();

    public static final String startPoint = "START";
    public static final String UpPoint = "UP";
    public static final String FallPoint = "FALL";
    public static final String ReboundPoint = "REBOUND";
    public static final String DownPoint = "DOWN";

    protected List<IndexPriceModel> earningsList = new ArrayList<>();
    protected double baseEarning = 1;

    protected void setBuyModel(LivermoreModel current){
        IndexPriceModel copyModel = new IndexPriceModel();
        copyModel.setTicker(current.getTicker());
        copyModel.setOpen(current.getClose());
        copyModel.setClose(0);
        copyModel.setDate(current.getDate());
        copyModel.setRange(0);
        earningsList.add(copyModel);
    }

    protected void setSellModel(LivermoreModel current){
        if(earningsList.size()>0) {
            IndexPriceModel lastModel = earningsList.get(earningsList.size() - 1);
            lastModel.setClose(current.getClose());
            lastModel.setDate(lastModel.getDate() + "-" + current.getDate());
            lastModel.setRange(MathUtil.getEarning(lastModel.getOpen(), lastModel.getClose()));
            baseEarning = baseEarning * (1 + lastModel.getRange());
            System.out.println("Sell and get earning  " + lastModel.getDate() +" "+ lastModel.getOpen()+"->"+lastModel.getClose()+" range=" + lastModel.getRange() + " earning=" + baseEarning);
        }

    }

    protected abstract void getNextModelStatus(LivermoreModel previous, LivermoreModel current);

    public LivermoreModel getModelfromTable(String point){
        return livermoreTable.get(point);
    }

    protected boolean isInUpColumn(LivermoreModel model){
        return model.getStatus() == LIVERMORE_STATUS.UP ||
                model.getStatus() == LIVERMORE_STATUS.START;
    }

    protected double getUpPointClose(){
        return getModelfromTable(UpPoint) != null ? getModelfromTable(UpPoint).getClose(): getModelfromTable(startPoint).getClose();
    }

    protected double getFallPointClose() {
        return getModelfromTable(FallPoint) != null? getModelfromTable(FallPoint).getClose(): -1.0;
    }

    protected double getReboundPointClose() {
        return getModelfromTable(ReboundPoint) != null? getModelfromTable(ReboundPoint).getClose(): -1.0;
    }

    protected double getDownPointClose() {
        return getModelfromTable(DownPoint) != null ? getModelfromTable(DownPoint).getClose(): -1.0;
    }

    private void restContext(){
        earningsList = new ArrayList<>();
        livermoreTable = new HashMap<>();
    }

    public void process(List<LivermoreModel> modelList){
        if(modelList.size()>1){
            LivermoreModel previous = modelList.get(0);
            previous.setStatus(LIVERMORE_STATUS.START);
            livermoreTable.put(startPoint, previous);


            for(int i=1; i<modelList.size();i++){
                LivermoreModel current = modelList.get(i);
                getNextModelStatus(previous, current);
                completeLivermoreModel(current);
//                System.out.println("set current close = " + current.getClose()+" status = "+current.getStatus());
                previous = current;


            }
        }

    }

    private void completeLivermoreModel(LivermoreModel model){
        model.setUp(getUpPointClose());
        model.setDown(getDownPointClose());
        model.setRebound(getReboundPointClose());
        model.setFall(getFallPointClose());
    }

    public abstract List<LivermoreModel> processLivermoreForTicker(String ticker);


}
