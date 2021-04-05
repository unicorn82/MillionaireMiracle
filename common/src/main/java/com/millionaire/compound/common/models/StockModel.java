package com.millionaire.compound.common.models;

public class StockModel {


    private int id;
    private String ticker;
    private String category;
    private String sector;
    private String companyName;
    private String industry;
    private double oneYrTarget;
    private double forwardPE1Yr;
    private double marketCap;
    private double PE;
    private double earning;
    private double EPS;
    private double dividend;
    private double yield;
    private double beta;
    private String nextReportDate;
    private String url;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public double getEarning() {
        return earning;
    }

    public void setEarning(double earning) {
        this.earning = earning;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public String getNextReportDate() {
        return nextReportDate;
    }

    public void setNextReportDate(String nextReportDate) {
        this.nextReportDate = nextReportDate;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public double getOneYrTarget() {
        return oneYrTarget;
    }

    public void setOneYrTarget(double oneYrTarget) {
        this.oneYrTarget = oneYrTarget;
    }

    public double getForwardPE1Yr() {
        return forwardPE1Yr;
    }

    public void setForwardPE1Yr(double forwardPE1Yr) {
        this.forwardPE1Yr = forwardPE1Yr;
    }

    public double getPE() {
        return PE;
    }

    public void setPE(double PE) {
        this.PE = PE;
    }

    public double getEPS() {
        return EPS;
    }

    public void setEPS(double EPS) {
        this.EPS = EPS;
    }

    public double getDividend() {
        return dividend;
    }

    public void setDividend(double dividend) {
        this.dividend = dividend;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "StockModel{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", category='" + category + '\'' +
                ", sector='" + sector + '\'' +
                ", companyName='" + companyName + '\'' +
                ", industry='" + industry + '\'' +
                ", oneYrTarget=" + oneYrTarget +
                ", forwardPE1Yr=" + forwardPE1Yr +
                ", marketCap=" + marketCap +
                ", PE=" + PE +
                ", earning=" + earning +
                ", EPS=" + EPS +
                ", dividend=" + dividend +
                ", yield=" + yield +
                ", beta=" + beta +
                ", nextReportDate='" + nextReportDate + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
