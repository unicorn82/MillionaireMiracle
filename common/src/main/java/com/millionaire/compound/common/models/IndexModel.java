package com.millionaire.compound.common.models;

public class IndexModel{
    private int id;
    private String ticker;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "IndexModel{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
