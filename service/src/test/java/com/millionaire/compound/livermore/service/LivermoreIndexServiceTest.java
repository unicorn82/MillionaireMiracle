package com.millionaire.compound.livermore.service;

import com.millionaire.compound.livermore.model.LivermoreModel;
import com.millionaire.compound.livermore.service.impl.AbstractLivermoreService;
import com.millionaire.compound.livermore.service.impl.LivermoreIndexService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class LivermoreIndexServiceTest {

    List<LivermoreModel> list1 = new ArrayList<>();
    List<LivermoreModel> list2 = new ArrayList<>();

    @Autowired
//    @Qualifier("LivermoreIndexService")
    private LivermoreIndexService livermoreService1;
    private LivermoreIndexService livermoreService2;

    private void createModel(double close,  List<LivermoreModel> list){
        LivermoreModel model = new LivermoreModel();
        model.setTicker("test");
        model.setClose(close);
        list.add(model);



    }

    @Before
    public void prepareLivermoreList1(){ // rebound to up
        System.out.println("prepare livermore list1");
        createModel(10.0, list1);
        createModel(10.5, list1);
        createModel(11.0, list1);
        createModel(10.75,list1);
        createModel(10.6, list1);
        createModel(10.8, list1);
        createModel(9.5, list1);
        createModel(9.0, list1);
        createModel(8.0, list1);
        createModel(7.0, list1);
        createModel(8.0, list1);
        createModel(9.0, list1);
        createModel(10.0, list1);
        createModel(12.0, list1);

        livermoreService1 = new LivermoreIndexService();
        //livermoreService1.process(list1);
    }

    @Before
    public void prepareLivermoreList2(){ //retrace to down
        System.out.println("prepare livermore list2");
        createModel(10.0, list2);
        createModel(10.5, list2);
        createModel(11.0, list2);
        createModel(10.75,list2);
        createModel(10.6, list2);
        createModel(10.8, list2);
        createModel(9.5, list2);
        createModel(9.0, list2);
        createModel(8.0, list2);
        createModel(7.0, list2);
        createModel(8.0, list2);
        createModel(7.9, list2);
        createModel(8.5, list2);
        createModel(7.5, list2);
        createModel(8.1, list2);

        createModel(6.5, list2);
        createModel(6.0, list2);
        createModel(5.0, list2);
        createModel(5.5, list2);

        livermoreService2 = new LivermoreIndexService();
        livermoreService2.process(list2);
    }

    @Test
    public void testStartStatus(){
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.START, list1.get(0).getStatus());
    }

    @Test
    public void testUPStatus(){
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.UP, list1.get(1).getStatus());
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.UP, list1.get(2).getStatus());
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.UP, list1.get(3).getStatus());
        LivermoreModel livermoreModel = livermoreService1.getModelfromTable(LivermoreIndexService.UpPoint);
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.RETRACE, list1.get(4).getStatus());

        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.RETRACE, list1.get(5).getStatus());
        Assert.assertEquals(12.0, livermoreModel.getClose(), 0.0001);
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.RETRACE, list1.get(6).getStatus());
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.RETRACE, list1.get(7).getStatus());
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.RETRACE, list1.get(8).getStatus());
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.RETRACE, list1.get(9).getStatus());
        Assert.assertEquals(7.0, livermoreService1.getModelfromTable(LivermoreIndexService.FallPoint).getClose(), 0.0001);
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.REBOUND, list1.get(10).getStatus());
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.REBOUND, list1.get(11).getStatus());
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.REBOUND, list1.get(12).getStatus());
        Assert.assertEquals(10.0, livermoreService1.getModelfromTable(LivermoreIndexService.ReboundPoint).getClose(), 0.0001);
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.UP, list1.get(13).getStatus());


    }

    @Test
    public void testDownStatus(){

        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.REBOUND, list2.get(11).getStatus());
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.REBOUND, list2.get(12).getStatus());
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.RETRACE, list2.get(13).getStatus());
        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.REBOUND, list2.get(14).getStatus());
//        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.DOWN, list2.get(13).getStatus());
//        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.DOWN, list2.get(14).getStatus());
//        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.DOWN, list2.get(15).getStatus());
//        Assert.assertEquals(LivermoreIndexService.LIVERMORE_STATUS.REBOUND, list2.get(16).getStatus());
//        Assert.assertEquals(5.0, livermoreService2.getModelfromTable(LivermoreIndexService.DownPoint).getClose(), 0.0001);

    }

    @Test
    public void testIndexLivermore(){
        String ticker = "IXIC";
        LivermoreIndexService livermoreService = new LivermoreIndexService();
        livermoreService.processLivermoreForTicker(ticker);

    }

    @Test
    public void testfetchAllIndex(){

    }
}
