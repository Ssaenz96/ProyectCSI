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
public class RetailSaleUnits implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long retailSUId;
    
    @Column(name = "RetailSUCode", length = 2, nullable = false)
    private String retailSUCode;
    
    @Column(name = "Description", length = 35, nullable = false)
    private String description;
    
    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;

    @OneToMany(mappedBy = "retailSaleUnits", cascade = CascadeType.REMOVE)
    private List<Products> listProducts;
//     
    public Long getRetailSUId() {
        return retailSUId;
    }

    public void setRetailSUId(Long retailSUId) {
        this.retailSUId = retailSUId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (retailSUId != null ? retailSUId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the retailSUId fields are not set
        if (!(object instanceof RetailSaleUnits)) {
            return false;
        }
        RetailSaleUnits other = (RetailSaleUnits) object;
        if ((this.retailSUId == null 
                && other.retailSUId != null) 
                || (this.retailSUId != null 
                && !this.retailSUId.equals(other.retailSUId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.RetailSaleUnits[ id=" + retailSUId + " ]";
    }

    /**
     * @return the retailSUCode
     */
    public String getRetailSUCode() {
        return retailSUCode;
    }

    /**
     * @param retailSUCode the retailSUCode to set
     */
    public void setRetailSUCode(String retailSUCode) {
        this.retailSUCode = retailSUCode;
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
     * @return the listProducts
     */
    public List<Products> getListProducts() {
        return listProducts;
    }

    /**
     * @param listProducts the listProducts to set
     */
    public void setListProducts(List<Products> listProducts) {
        this.listProducts = listProducts;
    }
    
}
