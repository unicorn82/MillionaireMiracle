package com.millionaire.compound.common.models;

import java.math.BigDecimal;

public class StockDailyPriceCandidateModel {

    private String ticker;
    private String date;
    private double open;
    private double close;
    private double high;
    private double low;
    private double range;
    private int volume;
    private int avg5Volume;
    private int avg10Volume;
    private int avg20Volume;
    private int avg30Volume;
    private int avg60Volume;
    private double ma5;
    private double ma10;
    private double ma20;
    private double ma30;
    private double ma60;
    private int passMaNum;

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

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getAvg5Volume() {
        return avg5Volume;
    }

    public void setAvg5Volume(int avg5Volume) {
        this.avg5Volume = avg5Volume;
    }

    public int getAvg10Volume() {
        return avg10Volume;
    }

    public void setAvg10Volume(int avg10Volume) {
        this.avg10Volume = avg10Volume;
    }

    public int getAvg20Volume() {
        return avg20Volume;
    }

    public void setAvg20Volume(int avg20Volume) {
        this.avg20Volume = avg20Volume;
    }

    public int getAvg30Volume() {
        return avg30Volume;
    }

    public void setAvg30Volume(int avg30Volume) {
        this.avg30Volume = avg30Volume;
    }

    public int getAvg60Volume() {
        return avg60Volume;
    }

    public void setAvg60Volume(int avg60Volume) {
        this.avg60Volume = avg60Volume;
    }

    public double getMa5() {
        return ma5;
    }

    public void setMa5(double ma5) {
        this.ma5 = ma5;
    }

    public double getMa10() {
        return ma10;
    }

    public void setMa10(double ma10) {
        this.ma10 = ma10;
    }

    public double getMa20() {
        return ma20;
    }

    public void setMa20(double ma20) {
        this.ma20 = ma20;
    }

    public double getMa30() {
        return ma30;
    }

    public void setMa30(double ma30) {
        this.ma30 = ma30;
    }

    public double getMa60() {
        return ma60;
    }

    public void setMa60(double ma60) {
        this.ma60 = ma60;
    }

    public int getPassMaNum() {
        return passMaNum;
    }

    public void setPassMaNum(int passMaNum) {
        this.passMaNum = passMaNum;
    }

    @Override
    public String toString() {
        return "StockDailyPriceCandidateModel{" +
                "ticker='" + ticker + '\'' +
                ", date='" + date + '\'' +
                ", passMaNum='" + passMaNum + '\'' +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", range=" + range +
                ", volume=" + volume +
                ", avg5Volume=" + avg5Volume +
                ", avg10Volume=" + avg10Volume +
                ", avg20Volume=" + avg20Volume +
                ", avg30Volume=" + avg30Volume +
                ", avg60Volume=" + avg60Volume +
                ", ma5=" + ma5 +
                ", ma10=" + ma10 +
                ", ma20=" + ma20 +
                ", ma30=" + ma30 +
                ", ma60=" + ma60 +
                '}';
    }
}
