/*
 * Tabla #12
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Saul Saenz
 * @date 05-10-2017
 * @version 1.0
 */
@Entity
@Table(schema = "GARCOMEX")
public class StoreProducts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeProductId;
    
    @Column(name = "CustomerSalePrice", precision = 7, scale = 2,nullable = false)
    private double customerSalePrice;
    
    @Column(name = "OfferPrice", precision = 7, scale = 2)
    private double offerPrice;
    
    @Column(name = "Maximum", precision = 5, scale = 0)
    private double maximum;
    
    @Column(name = "Minimum", precision = 5, scale = 0)
    private double minimum;
    
    @Column(name = "RecorderPoint", precision = 5, scale = 0, nullable = false)
    private double recorderPoint;
    
    @Column(name = "Inventory", precision = 13, scale = 3)
    private double inventory;
    
    @Column(name = "LastInventoryUpdate")
    @Temporal(TemporalType.DATE)
    private Date lastInventoryUpdate;
    
    @Column(name = "EnableDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date enableDate;
    
    @Column(name = "EnableBy", length = 50, nullable = false)
    private String enableBy;
    
    @Column(name = "LastChangeDate")
    @Temporal(TemporalType.DATE)
    private Date lastChangeDate;
    
    @Column(name = "LastChangeBy", length = 50)
    private String lastChangeBy;
    
    @Column(name = "LastCSPChangeDate")
    @Temporal(TemporalType.DATE)
    private Date lastCSPChangeDate;
    
    @Column(name = "LastCSPChangeBy", length = 50)
    private String lastCSPChangeBy;
    
    @Column(name = "LastOfferDate")
    @Temporal(TemporalType.DATE)
    private Date lastOfferDate;
    
    @Column(name = "LastOfferChangedBy", length = 50)
    private String lastOfferChangedBy;
    
    @Column(name = "DisableDate")
    @Temporal(TemporalType.DATE)
    private Date disableDate;
    
    @Column(name = "DisableBy", length = 50)
    private String disableBy;
    
    @Column(name = "Status")
    private byte status;
    
    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    @JoinColumn(name = "StoreId", nullable = false, referencedColumnName = "storeId")
    @ManyToOne(optional = false)
    private Stores stores;
    
    @JoinColumn(name = "ProductId", nullable = false, referencedColumnName = "productId")
    @ManyToOne(optional = false)
    private Products products;
    
    public Long getStoreProductId() {
        return storeProductId;
    }

    public void setStoreProductId(Long storeProductId) {
        this.storeProductId = storeProductId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeProductId != null ? storeProductId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the storeProductId fields are not set
        if (!(object instanceof StoreProducts)) {
            return false;
        }
        StoreProducts other = (StoreProducts) object;
        if ((this.storeProductId == null && other.storeProductId != null) || (this.storeProductId != null && !this.storeProductId.equals(other.storeProductId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.StoreProducts[ id=" + storeProductId + " ]";
    }

    /**
     * @return the customerSalePrice
     */
    public double getCustomerSalePrice() {
        return customerSalePrice;
    }

    /**
     * @param customerSalePrice the customerSalePrice to set
     */
    public void setCustomerSalePrice(double customerSalePrice) {
        this.customerSalePrice = customerSalePrice;
    }

    /**
     * @return the offerPrice
     */
    public double getOfferPrice() {
        return offerPrice;
    }

    /**
     * @param offerPrice the offerPrice to set
     */
    public void setOfferPrice(double offerPrice) {
        this.offerPrice = offerPrice;
    }

    /**
     * @return the maximum
     */
    public double getMaximum() {
        return maximum;
    }

    /**
     * @param maximum the maximum to set
     */
    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    /**
     * @return the minimum
     */
    public double getMinimum() {
        return minimum;
    }

    /**
     * @param minimum the minimum to set
     */
    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    /**
     * @return the recorderPoint
     */
    public double getRecorderPoint() {
        return recorderPoint;
    }

    /**
     * @param recorderPoint the recorderPoint to set
     */
    public void setRecorderPoint(double recorderPoint) {
        this.recorderPoint = recorderPoint;
    }

    /**
     * @return the inventory
     */
    public double getInventory() {
        return inventory;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(double inventory) {
        this.inventory = inventory;
    }

    /**
     * @return the lastInventoryUpdate
     */
    public Date getLastInventoryUpdate() {
        return lastInventoryUpdate;
    }

    /**
     * @param lastInventoryUpdate the lastInventoryUpdate to set
     */
    public void setLastInventoryUpdate(Date lastInventoryUpdate) {
        this.lastInventoryUpdate = lastInventoryUpdate;
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
     * @return the enableBy
     */
    public String getEnableBy() {
        return enableBy;
    }

    /**
     * @param enableBy the enableBy to set
     */
    public void setEnableBy(String enableBy) {
        this.enableBy = enableBy;
    }

    /**
     * @return the lastChangeDate
     */
    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    /**
     * @param lastChangeDate the lastChangeDate to set
     */
    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    /**
     * @return the lastChangeBy
     */
    public String getLastChangeBy() {
        return lastChangeBy;
    }

    /**
     * @param lastChangeBy the lastChangeBy to set
     */
    public void setLastChangeBy(String lastChangeBy) {
        this.lastChangeBy = lastChangeBy;
    }

    /**
     * @return the lastCSPChangeDate
     */
    public Date getLastCSPChangeDate() {
        return lastCSPChangeDate;
    }

    /**
     * @param lastCSPChangeDate the lastCSPChangeDate to set
     */
    public void setLastCSPChangeDate(Date lastCSPChangeDate) {
        this.lastCSPChangeDate = lastCSPChangeDate;
    }

    /**
     * @return the lastCSPChangeBy
     */
    public String getLastCSPChangeBy() {
        return lastCSPChangeBy;
    }

    /**
     * @param lastCSPChangeBy the lastCSPChangeBy to set
     */
    public void setLastCSPChangeBy(String lastCSPChangeBy) {
        this.lastCSPChangeBy = lastCSPChangeBy;
    }

    /**
     * @return the lastOfferDate
     */
    public Date getLastOfferDate() {
        return lastOfferDate;
    }

    /**
     * @param lastOfferDate the lastOfferDate to set
     */
    public void setLastOfferDate(Date lastOfferDate) {
        this.lastOfferDate = lastOfferDate;
    }

    /**
     * @return the lastOfferChangedBy
     */
    public String getLastOfferChangedBy() {
        return lastOfferChangedBy;
    }

    /**
     * @param lastOfferChangedBy the lastOfferChangedBy to set
     */
    public void setLastOfferChangedBy(String lastOfferChangedBy) {
        this.lastOfferChangedBy = lastOfferChangedBy;
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
     * @return the disableBy
     */
    public String getDisableBy() {
        return disableBy;
    }

    /**
     * @param disableBy the disableBy to set
     */
    public void setDisableBy(String disableBy) {
        this.disableBy = disableBy;
    }

    /**
     * @return the status
     */
    public byte getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(byte status) {
        this.status = status;
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

    /**
     * @return the stores
     */
    public Stores getStores() {
        return stores;
    }

    /**
     * @param stores the stores to set
     */
    public void setStores(Stores stores) {
        this.stores = stores;
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
    
}
