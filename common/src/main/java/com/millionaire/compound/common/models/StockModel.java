package com.millionaire.compound.common.models;

import java.math.BigDecimal;

public class StockModel {

    private int id;
    private String ticker;
    private String category;
    private String company;
    private String companyName;
    private double avg3mVolumn;
    private double yearRange;
    private String w52Range;
    private double marketCap;
    private double cirMarketCap;
    private double pe;
    private double earning;
    private double eps;
    private double forwardDividend;
    private double yield;
    private double beta;
    private String nextReportDate;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getAvg3mVolumn() {
        return avg3mVolumn;
    }

    public void setAvg3mVolumn(double avg3mVolumn) {
        this.avg3mVolumn = avg3mVolumn;
    }

    public double getYearRange() {
        return yearRange;
    }

    public void setYearRange(double yearRange) {
        this.yearRange = yearRange;
    }

    public String getW52Range() {
        return w52Range;
    }

    public void setW52Range(String w52Range) {
        this.w52Range = w52Range;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public double getCirMarketCap() {
        return cirMarketCap;
    }

    public void setCirMarketCap(double cirMarketCap) {
        this.cirMarketCap = cirMarketCap;
    }

    public double getPe() {
        return pe;
    }

    public void setPe(double pe) {
        this.pe = pe;
    }

    public double getEarning() {
        return earning;
    }

    public void setEarning(double earning) {
        this.earning = earning;
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public double getForwardDividend() {
        return forwardDividend;
    }

    public void setForwardDividend(double forwardDividend) {
        this.forwardDividend = forwardDividend;
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

    @Override
    public String toString() {
        return "StockModel{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", category='" + category + '\'' +
                ", company='" + company + '\'' +
                ", companyName='" + companyName + '\'' +
                ", avg3mVolumn=" + avg3mVolumn +
                ", yearRange=" + yearRange +
                ", w52Range='" + w52Range + '\'' +
                ", marketCap=" + marketCap +
                ", cirMarketCap=" + cirMarketCap +
                ", pe=" + pe +
                ", earning=" + earning +
                ", eps=" + eps +
                ", forwardDividend=" + forwardDividend +
                ", yield=" + yield +
                ", beta=" + beta +
                ", nextReportDate='" + nextReportDate + '\'' +
                '}';
    }
}
