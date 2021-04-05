package com.millionaire.compound.livermore.model;

import com.millionaire.compound.livermore.service.impl.AbstractLivermoreService;

public class LivermoreModel {


    private int id;
    private String ticker;
    private String date;
    private double open;
    private double close;

    private double up;
    private double down;
    private double fall;
    private double rebound;

    private AbstractLivermoreService.LIVERMORE_STATUS status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public AbstractLivermoreService.LIVERMORE_STATUS getStatus() {
        return status;
    }

    public void setStatus(AbstractLivermoreService.LIVERMORE_STATUS status) {
        this.status = status;
    }

    public double getUp() {
        return up;
    }

    public void setUp(double up) {
        this.up = up;
    }

    public double getDown() {
        return down;
    }

    public void setDown(double down) {
        this.down = down;
    }

    public double getFall() {
        return fall;
    }

    public void setFall(double fall) {
        this.fall = fall;
    }

    public double getRebound() {
        return rebound;
    }

    public void setRebound(double rebound) {
        this.rebound = rebound;
    }
}
