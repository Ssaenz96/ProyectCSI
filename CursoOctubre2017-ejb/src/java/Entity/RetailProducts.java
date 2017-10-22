/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Gerardo Cardenas
 * Clase: RetailProducts
 * Version 1.0
 * Fecha: 06/10/2017
 * Copyrigth
 */
/**
 * @author luis Garcia (doubles a Doubles)
 * Clase: RetailProducts
 * Version 2.0
 * Fecha: 06/10/2017
 * Copyrigth
 */
@Entity
@Table(schema = "GARCOMEX")
public class RetailProducts implements Serializable {

    /**
     * @return the retailProductId
     */
    public Long getRetailProductId() {
        return retailProductId;
    }

    /**
     * @param retailProductId the retailProductId to set
     */
    public void setRetailProductId(Long retailProductId) {
        this.retailProductId = retailProductId;
    }

    /**
     * @return the retails
     */
    public Retails getRetails() {
        return retails;
    }

    /**
     * @param retails the retails to set
     */
    public void setRetails(Retails retails) {
        this.retails = retails;
    }

    /**
     * @return the products
     */
    public Products getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(Products products) {
        this.products = products;
    }

    /**
     * @return the skuCode
     */
    public String getSkuCode() {
        return skuCode;
    }

    /**
     * @param skuCode the skuCode to set
     */
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    /**
     * @return the retailSalePrice
     */
    public Double getRetailSalePrice() {
        return retailSalePrice;
    }

    /**
     * @param retailSalePrice the retailSalePrice to set
     */
    public void setRetailSalePrice(Double retailSalePrice) {
        this.retailSalePrice = retailSalePrice;
    }

    /**
     * @return the startPrice
     */
    public Double getStartPrice() {
        return startPrice;
    }

    /**
     * @param startPrice the startPrice to set
     */
    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    /**
     * @return the enableDate
     */
    public Date getEnableDate() {
        return enableDate;
    }

    /**
     * @param enableDate the enableDate to set
     */
    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    /**
     * @return the enabledBy
     */
    public String getEnabledBy() {
        return enabledBy;
    }

    /**
     * @param enabledBy the enabledBy to set
     */
    public void setEnabledBy(String enabledBy) {
        this.enabledBy = enabledBy;
    }

    /**
     * @return the salePriceChangeDate
     */
    public Date getSalePriceChangeDate() {
        return salePriceChangeDate;
    }

    /**
     * @param salePriceChangeDate the salePriceChangeDate to set
     */
    public void setSalePriceChangeDate(Date salePriceChangeDate) {
        this.salePriceChangeDate = salePriceChangeDate;
    }

    /**
     * @return the priceChangeBy
     */
    public String getPriceChangeBy() {
        return priceChangeBy;
    }

    /**
     * @param priceChangeBy the priceChangeBy to set
     */
    public void setPriceChangeBy(String priceChangeBy) {
        this.priceChangeBy = priceChangeBy;
    }

    /**
     * @return the disableDate
     */
    public Date getDisableDate() {
        return disableDate;
    }

    /**
     * @param disableDate the disableDate to set
     */
    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    /**
     * @return the disabledBy
     */
    public String getDisabledBy() {
        return disabledBy;
    }

    /**
     * @param disabledBy the disabledBy to set
     */
    public void setDisabledBy(String disabledBy) {
        this.disabledBy = disabledBy;
    }

    /**
     * @return the purchaseOrders
     */
    public PurchaseOrders getPurchaseOrders() {
        return purchaseOrders;
    }

    /**
     * @param purchaseOrders the purchaseOrders to set
     */
    public void setPurchaseOrders(PurchaseOrders purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    /**
     * @return the tradeCompanyId
     */
    public int getTradeCompanyId() {
        return tradeCompanyId;
    }

    /**
     * @param tradeCompanyId the tradeCompanyId to set
     */
    public void setTradeCompanyId(int tradeCompanyId) {
        this.tradeCompanyId = tradeCompanyId;
    }

    /**
     * @return the lastUpdateBI
     */
    public Date getLastUpdateBI() {
        return lastUpdateBI;
    }

    /**
     * @param lastUpdateBI the lastUpdateBI to set
     */
    public void setLastUpdateBI(Date lastUpdateBI) {
        this.lastUpdateBI = lastUpdateBI;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long retailProductId;

    @JoinColumn(name = "RetailId",referencedColumnName = "retailId")
    @ManyToOne(optional = false)
    private Retails retails;
    
    @JoinColumn(name = "ProductId",referencedColumnName = "productId")
    @ManyToOne(optional = false)
    private Products products;
    
    @Column(name="SKUCode",length = 8,nullable = false)
    private String skuCode;
    
    @Column(name = "RetailSalePrice",scale = 7,precision = 2, nullable = false)
    private Double retailSalePrice;
    
    @Column(name = "startPrice",scale = 7,precision = 2)
    private Double startPrice;
    
    @Column(name="EnableDate")
    @Temporal(TemporalType.DATE)
    private Date enableDate;
    
    @Column(name="EnabledBy",length = 50)
    private String enabledBy;
    
    @Column(name="SalePriceChangeDate")
    @Temporal(TemporalType.DATE)
    private Date salePriceChangeDate;
    
    @Column(name="PriceChangeBy",length = 50)
    private String priceChangeBy;
    
    @Column(name="DisableDate")
    @Temporal(TemporalType.DATE)
    private Date disableDate;
    
    @Column(name="disabledBy",length = 50)
    private String disabledBy;
    
    //POId
    @JoinColumn(name = "POId",referencedColumnName = "pOId", nullable = true)
    @ManyToOne(optional = false)
    private PurchaseOrders purchaseOrders;
    
    @Column(name="TradeCompanyId")
    private int tradeCompanyId;
    
    @Column(name="LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getRetailProductId() != null ? getRetailProductId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RetailProducts)) {
            return false;
        }
        RetailProducts other = (RetailProducts) object;
        if ((this.getRetailProductId() == null && other.getRetailProductId() != null) || (this.getRetailProductId() != null && !this.retailProductId.equals(other.retailProductId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.RetailProducts[ id=" + getRetailProductId() + " ]";
    }
    
}
