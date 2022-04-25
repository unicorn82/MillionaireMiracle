package com.millionaire.compound.common.models;

public class StockVerficationModel {

    private double close_5;

    private double close_3;

    private double close_1;

    private double retracement_5;

    private double retracement_3;

    private double retracement_1;

    private double earning_5;

    private double earning_3;

    private double earning_1;

    private String ticker;

    private String date;

    public StockVerficationModel(){
        this.earning_1 = Double.MIN_VALUE;
        this.earning_3 = Double.MIN_VALUE;
        this.earning_5 = Double.MIN_VALUE;
        this.retracement_1 = Double.MIN_VALUE;
        this.retracement_3 = Double.MIN_VALUE;
        this.retracement_5 = Double.MIN_VALUE;
    }

    public double getClose_5() {
        return close_5;
    }

    public void setClose_5(double close_5) {
        this.close_5 = close_5;
    }

    public double getClose_3() {
        return close_3;
    }

    public void setClose_3(double close_3) {
        this.close_3 = close_3;
    }

    public double getClose_1() {
        return close_1;
    }

    public void setClose_1(double close_1) {
        this.close_1 = close_1;
    }

    public double getRetracement_5() {
        return retracement_5;
    }

    public void setRetracement_5(double retracement_5) {
        this.retracement_5 = retracement_5;
    }

    public double getRetracement_3() {
        return retracement_3;
    }

    public void setRetracement_3(double retracement_3) {
        this.retracement_3 = retracement_3;
    }

    public double getRetracement_1() {
        return retracement_1;
    }

    public void setRetracement_1(double retracement_1) {
        this.retracement_1 = retracement_1;
    }

    public double getEarning_5() {
        return earning_5;
    }

    public void setEarning_5(double earning_5) {
        this.earning_5 = earning_5;
    }

    public double getEarning_3() {
        return earning_3;
    }

    public void setEarning_3(double earning_3) {
        this.earning_3 = earning_3;
    }

    public double getEarning_1() {
        return earning_1;
    }

    public void setEarning_1(double earning_1) {
        this.earning_1 = earning_1;
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

    @Override
    public String toString() {
        return "StockVerficationModel{" +
                "earning_5=" + earning_5 +
                ", earning_3=" + earning_3 +
                ", earning_1=" + earning_1 +
                '}';
    }
}
