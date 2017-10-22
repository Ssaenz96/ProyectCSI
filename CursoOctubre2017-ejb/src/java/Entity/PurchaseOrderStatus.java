/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

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
public class PurchaseOrderStatus implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pOStatusId;
    
    @Column(name = "POStatusCode", length = 2, nullable = false)
    private String POStatusCode;
    
    @Column(name = "Description", length = 35, nullable = false)
    private String description;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    @OneToMany(mappedBy = "purchaseOrderStatus",cascade = CascadeType.REMOVE)
    private List<PurchaseOrders> purchaseOrders;
    
    public Long getpOStatusId() {
        return pOStatusId;
    }

    public void setpOStatusId(Long pOStatusId) {
        this.pOStatusId = pOStatusId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pOStatusId != null ? pOStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the pOStatusId fields are not set
        if (!(object instanceof PurchaseOrderStatus)) {
            return false;
        }
        PurchaseOrderStatus other = (PurchaseOrderStatus) object;
        if ((this.pOStatusId == null && other.pOStatusId != null) || (this.pOStatusId != null && !this.pOStatusId.equals(other.pOStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.PurchaseOrderStatus[ id=" + pOStatusId + " ]";
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
    public List<PurchaseOrders> getPurchaseOrders() {
        return purchaseOrders;
    }

    /**
     * @param purchaseOrders the purchaseOrders to set
     */
    public void setPurchaseOrders(List<PurchaseOrders> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public String getPOStatusCode() {
        return POStatusCode;
    }

    public void setPOStatusCode(String POStatusCode) {
        this.POStatusCode = POStatusCode;
    }


   
}
