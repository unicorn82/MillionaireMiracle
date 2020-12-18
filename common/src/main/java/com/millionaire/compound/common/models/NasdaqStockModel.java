package com.millionaire.compound.common.models;

public class NasdaqStockModel {

    private int id;
    private String ticker;
    private String company;
    private String marketCap;
    private String marketCapGroup;
    private String sectorName;
    private String sector;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String category;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getMarketCapGroup() {
        return marketCapGroup;
    }

    public void setMarketCapGroup(String marketCapGroup) {
        this.marketCapGroup = marketCapGroup;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "NasdaqStockModel{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", company='" + company + '\'' +
                ", marketCap='" + marketCap + '\'' +
                ", marketCapGroup='" + marketCapGroup + '\'' +
                ", sectorName='" + sectorName + '\'' +
                ", sector='" + sector + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
