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
    private long volume;
    private long avg5Volume;
    private long avg10Volume;
    private long avg20Volume;
    private long avg30Volume;
    private long avg60Volume;
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

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public long getAvg5Volume() {
        return avg5Volume;
    }

    public void setAvg5Volume(long avg5Volume) {
        this.avg5Volume = avg5Volume;
    }

    public long getAvg10Volume() {
        return avg10Volume;
    }

    public void setAvg10Volume(long avg10Volume) {
        this.avg10Volume = avg10Volume;
    }

    public long getAvg20Volume() {
        return avg20Volume;
    }

    public void setAvg20Volume(long avg20Volume) {
        this.avg20Volume = avg20Volume;
    }

    public long getAvg30Volume() {
        return avg30Volume;
    }

    public void setAvg30Volume(long avg30Volume) {
        this.avg30Volume = avg30Volume;
    }

    public long getAvg60Volume() {
        return avg60Volume;
    }

    public void setAvg60Volume(long avg60Volume) {
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
