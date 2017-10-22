/*
 * Purchase Orders Entity
 *
 * Version 1.0
 *
 * 10:02am 6/10/17
 *
 * Luis. All Rights Reserved.
 * 
 * NOTAS:
 * Faltan relaciones, agregar POStatusId de PurchaseOrderStatus
 * StoreId de Stores

Gerardo Cardenas 
11/10/2017
Se modifican los getter y setters de PO NUMBER
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
 * @author Usuario
 */
@Entity
@Table(schema = "GARCOMEX")
public class PurchaseOrders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long POId;
    
    @Column(name = "PONumber", length = 15, nullable = false)
    private String PONumber;
    
    @Column(name = "DeliveryPlace", nullable = false)
    private int deliveryPlace;
    
    @Column(name = "PODate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date PODate;
    
    @Column(name = "CommDeliveryDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date commDeliveryDate;
    
    @Column(name = "DeliveryDate")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    
    @Column(name = "ClousureDate")
    @Temporal(TemporalType.DATE)
    private Date clousureDate;
    
    @Column(name = "CancelDate")
    @Temporal(TemporalType.DATE)
    private Date cancelDate;
    
    @Column(name = "ClousureCancelBy", length = 50)
    private String clousureCancelBy;
    
    @Column(name = "TotalRequestedUnits", precision = 17, scale = 3, nullable = false)
    private int totalRequestedUnits;
    
    @Column(name = "TotalRequestedValue", precision = 16, scale = 2)
    private int totalRequestedValue;

    @Column(name = "TotalDeliveredUnits",precision = 17, scale = 3)
    private int totalDeliveredUnits;
    
    @Column(name = "TotalDeliveredValue", precision = 16, scale = 2)
    private int totalDeliveredValue;
    
    @Column(name = "TradeCompanyId", nullable = false)
    private int tradeCompanyId;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    @JoinColumn(name = "POStatusId", nullable = false, referencedColumnName = "pOStatusId")
    @ManyToOne(optional = false)
    private PurchaseOrderStatus purchaseOrderStatus;
    
    @JoinColumn(name = "StoreId", nullable = false, referencedColumnName = "storeId")
    @ManyToOne(optional = false)
    private Stores stores;
    
    @OneToMany(mappedBy = "purchaseOrders",cascade = CascadeType.REMOVE)
    private List<PurchaseOrderDetails> purchaseOrderDetails;
    
    @OneToMany(mappedBy = "purchaseOrders",cascade = CascadeType.REMOVE)
    private List<RetailProducts> listRetailProducts;
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (POId != null ? POId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrders)) {
            return false;
        }
        PurchaseOrders other = (PurchaseOrders) object;
        if ((this.POId == null && other.POId != null) || (this.POId != null && !this.POId.equals(other.POId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.PurchaseOrders[ id=" + POId + " ]";
    }

    public String getPONumber() {
        return PONumber;
    }

    public void setPONumber(String PONumber) {
        this.PONumber = PONumber;
    }

    public int getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(int deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public Date getPODate() {
        return PODate;
    }

    public void setPODate(Date PODate) {
        this.PODate = PODate;
    }

    public Date getCommDeliveryDate() {
        return commDeliveryDate;
    }

    public void setCommDeliveryDate(Date commDeliveryDate) {
        this.commDeliveryDate = commDeliveryDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getClousureDate() {
        return clousureDate;
    }

    public void setClousureDate(Date clousureDate) {
        this.clousureDate = clousureDate;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getClousureCancelBy() {
        return clousureCancelBy;
    }

    public void setClousureCancelBy(String clousureCancelBy) {
        this.clousureCancelBy = clousureCancelBy;
    }

    public int getTotalRequestedUnits() {
        return totalRequestedUnits;
    }

    public void setTotalRequestedUnits(int totalRequestedUnits) {
        this.totalRequestedUnits = totalRequestedUnits;
    }

    public int getTotalRequestedValue() {
        return totalRequestedValue;
    }

    public void setTotalRequestedValue(int totalRequestedValue) {
        this.totalRequestedValue = totalRequestedValue;
    }

    public int getTotalDeliveredUnits() {
        return totalDeliveredUnits;
    }

    public void setTotalDeliveredUnits(int totalDeliveredUnits) {
        this.totalDeliveredUnits = totalDeliveredUnits;
    }

    public int getTotalDeliveredValue() {
        return totalDeliveredValue;
    }

    public void setTotalDeliveredValue(int totalDeliveredValue) {
        this.totalDeliveredValue = totalDeliveredValue;
    }

    public int getTradeCompanyId() {
        return tradeCompanyId;
    }

    public void setTradeCompanyId(int tradeCompanyId) {
        this.tradeCompanyId = tradeCompanyId;
    }

    public Date getLastUpdateBI() {
        return lastUpdateBI;
    }

    public void setLastUpdateBI(Date lastUpdateBI) {
        this.lastUpdateBI = lastUpdateBI;
    }

    public PurchaseOrderStatus getPurchaseOrderStatus() {
        return purchaseOrderStatus;
    }

    public void setPurchaseOrderStatus(PurchaseOrderStatus purchaseOrderStatus) {
        this.purchaseOrderStatus = purchaseOrderStatus;
    }

    public Stores getStores() {
        return stores;
    }

    public void setStores(Stores stores) {
        this.stores = stores;
    }

    public List<PurchaseOrderDetails> getPurchaseOrderDetails() {
        return purchaseOrderDetails;
    }

    public void setPurchaseOrderDetails(List<PurchaseOrderDetails> purchaseOrderDetails) {
        this.purchaseOrderDetails = purchaseOrderDetails;
    }

    public List<RetailProducts> getListRetailProducts() {
        return listRetailProducts;
    }

    public void setListRetailProducts(List<RetailProducts> listRetailProducts) {
        this.listRetailProducts = listRetailProducts;
    }

    public Long getPOId() {
        return POId;
    }

    public void setPOId(Long POId) {
        this.POId = POId;
    }
    
}
