/*
 * Tabla #24
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
 * @author Saul Saenz
 * @date 06-10-2017
 * @version 1.0
 */
/**
 * @author Luis Garcia
 * @date 19-10-2017
 * @version 2.0 (doubles to Doubles)
 */
@Entity
@Table(schema = "GARCOMEX")
public class PurchaseOrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pODetailId;
    
    @Column(name = "RequestedUnits", precision = 13, scale = 3, nullable = false)
    private Double requestedUnits;
    
    @Column(name = "RequestedValue", precision = 12, scale = 2)
    private Double requestedValue;
    
    @Column(name = "DeliveredUnits", precision = 13, scale = 3)
    private Double deliveredUnits;
    
    @Column(name = "DeliveredValue", precision = 12, scale = 2)
    private Double deliveredValue;
    
    @Column(name = "DeliveryRealDate")
    @Temporal(TemporalType.DATE)
    private Date deliveryRealDate;
    
    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;
    
    @Column(name = "UnitPrice", precision = 8, scale = 2)
    private Double unitPrice;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    //RELACIONES CON OTRAS TABLAS
    @JoinColumn(name = "POId", nullable = false, referencedColumnName = "pOId")
    @ManyToOne(optional = false)
    private PurchaseOrders purchaseOrders;
    
    @JoinColumn(name = "ProductId", nullable = false, referencedColumnName = "productId")
    @ManyToOne(optional = false)
    private Products products;
    
    public Long getpODetailId() {
        return pODetailId;
    }

    public void setpODetailId(Long pODetailId) {
        this.pODetailId = pODetailId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pODetailId != null ? pODetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the pODetailId fields are not set
        if (!(object instanceof PurchaseOrderDetails)) {
            return false;
        }
        PurchaseOrderDetails other = (PurchaseOrderDetails) object;
        if ((this.pODetailId == null && other.pODetailId != null) || (this.pODetailId != null && !this.pODetailId.equals(other.pODetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.PurchaseOrderDetails[ id=" + pODetailId + " ]";
    }

    /**
     * @return the requestedUnits
     */
    public Double getRequestedUnits() {
        return requestedUnits;
    }

    /**
     * @param requestedUnits the requestedUnits to set
     */
    public void setRequestedUnits(Double requestedUnits) {
        this.requestedUnits = requestedUnits;
    }

    /**
     * @return the requestedValue
     */
    public Double getRequestedValue() {
        return requestedValue;
    }

    /**
     * @param requestedValue the requestedValue to set
     */
    public void setRequestedValue(Double requestedValue) {
        this.requestedValue = requestedValue;
    }

    /**
     * @return the deliveredUnits
     */
    public Double getDeliveredUnits() {
        return deliveredUnits;
    }

    /**
     * @param deliveredUnits the deliveredUnits to set
     */
    public void setDeliveredUnits(Double deliveredUnits) {
        this.deliveredUnits = deliveredUnits;
    }

    /**
     * @return the deliveredValue
     */
    public Double getDeliveredValue() {
        return deliveredValue;
    }

    /**
     * @param deliveredValue the deliveredValue to set
     */
    public void setDeliveredValue(Double deliveredValue) {
        this.deliveredValue = deliveredValue;
    }

    /**
     * @return the deliveryRealDate
     */
    public Date getDeliveryRealDate() {
        return deliveryRealDate;
    }

    /**
     * @param deliveryRealDate the deliveryRealDate to set
     */
    public void setDeliveryRealDate(Date deliveryRealDate) {
        this.deliveryRealDate = deliveryRealDate;
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
     * @return the unitPrice
     */
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
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
