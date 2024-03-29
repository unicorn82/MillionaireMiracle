package com.millionaire.compound.hibernate.entity.basic;// default package
// Generated Feb 21, 2021, 10:51:53 PM by Hibernate Tools 4.0.0.Final


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * MiracleHistory generated by hbm2java
 */
@Entity
@Table(name="miracle_history"
    ,schema="public"
)
public class MiracleHistory implements java.io.Serializable {


     private int id;
     private String ticker;
     private BigDecimal buyPrice;
     private Date buyDate;
     private BigDecimal sellPrice;
     private Date updateDate;
     private BigDecimal earning;
     private Boolean isClosed;

    public MiracleHistory() {
    }

	
    public MiracleHistory(int id, String ticker) {
        this.id = id;
        this.ticker = ticker;
    }
    public MiracleHistory(int id, String ticker, BigDecimal buyPrice, Date buyDate, BigDecimal sellPrice, Date updateDate, BigDecimal earning, Boolean isClosed) {
       this.id = id;
       this.ticker = ticker;
       this.buyPrice = buyPrice;
       this.buyDate = buyDate;
       this.sellPrice = sellPrice;
       this.updateDate = updateDate;
       this.earning = earning;
       this.isClosed = isClosed;
    }
   
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    
    @Column(name="buy_price", precision=131089, scale=0)
    public BigDecimal getBuyPrice() {
        return this.buyPrice;
    }
    
    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="buy_date", length=13)
    public Date getBuyDate() {
        return this.buyDate;
    }
    
    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    
    @Column(name="sell_price", precision=131089, scale=0)
    public BigDecimal getSellPrice() {
        return this.sellPrice;
    }
    
    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="update_date", length=13)
    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    
    @Column(name="earning", precision=131089, scale=0)
    public BigDecimal getEarning() {
        return this.earning;
    }
    
    public void setEarning(BigDecimal earning) {
        this.earning = earning;
    }

    
    @Column(name="is_closed")
    public Boolean getIsClosed() {
        return this.isClosed;
    }
    
    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }




}


