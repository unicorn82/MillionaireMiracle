package com.millionaire.compound.hibernate.entity.basic;// default package
// Generated Dec 17, 2020, 4:20:57 PM by Hibernate Tools 4.0.0.Final


import javax.persistence.*;

/**
 * MiracleStock generated by hbm2java
 */
@Entity
@Table(name="miracle_stock"
    ,schema="public"
)
public class MiracleStock implements java.io.Serializable {


     private int id;
     private String ticker;
     private String company;
     private String marketCap;
     private String marketCapGroup;
     private String sectorName;
     private String sector;
     private String category;

    public MiracleStock() {
    }

	
    public MiracleStock(int id, String ticker, String company, String marketCap, String marketCapGroup, String sectorName, String sector) {
        this.id = id;
        this.ticker = ticker;
        this.company = company;
        this.marketCap = marketCap;
        this.marketCapGroup = marketCapGroup;
        this.sectorName = sectorName;
        this.sector = sector;
    }
    public MiracleStock(int id, String ticker, String company, String marketCap, String marketCapGroup, String sectorName, String sector, String category) {
       this.id = id;
       this.ticker = ticker;
       this.company = company;
       this.marketCap = marketCap;
       this.marketCapGroup = marketCapGroup;
       this.sectorName = sectorName;
       this.sector = sector;
       this.category = category;
    }
   
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "miracle_stock_seq")
     @SequenceGenerator(name = "miracle_stock_seq", sequenceName = "miracle_stock_seq", allocationSize = 1)
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="ticker", nullable=false)
    public String getTicker() {
        return this.ticker;
    }
    
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    
    @Column(name="company", nullable=false)
    public String getCompany() {
        return this.company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }

    
    @Column(name="market_cap", nullable=false)
    public String getMarketCap() {
        return this.marketCap;
    }
    
    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    
    @Column(name="market_cap_group", nullable=false)
    public String getMarketCapGroup() {
        return this.marketCapGroup;
    }
    
    public void setMarketCapGroup(String marketCapGroup) {
        this.marketCapGroup = marketCapGroup;
    }

    
    @Column(name="sector_name", nullable=false)
    public String getSectorName() {
        return this.sectorName;
    }
    
    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    
    @Column(name="sector", nullable=false)
    public String getSector() {
        return this.sector;
    }
    
    public void setSector(String sector) {
        this.sector = sector;
    }

    
    @Column(name="category")
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }




}


