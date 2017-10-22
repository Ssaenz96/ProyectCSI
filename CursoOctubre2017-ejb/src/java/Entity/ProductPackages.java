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
public class ProductPackages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productPackId;
    
    @Column(name = "ProductPackCode", length = 2, nullable = false)
    private String productPackCode;
    
    @Column(name = "Description", length = 35)
    private String description;
    
    @Column(name = "Quantity", precision = 7, scale = 3)
    private double quantity;
    
    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
//    
    @OneToMany(mappedBy = "productPackages", cascade = CascadeType.REMOVE)
    private List<Products> listProducts;

    public Long getProductPackId() {
        return productPackId;
    }

    public void setProductPackId(Long productPackId) {
        this.productPackId = productPackId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productPackId != null ? productPackId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the productPackId fields are not set
        if (!(object instanceof ProductPackages)) {
            return false;
        }
        ProductPackages other = (ProductPackages) object;
        if ((this.productPackId == null 
                && other.productPackId != null) 
                || (this.productPackId != null 
                && !this.productPackId.equals(other.productPackId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ProductPackages[ id=" + productPackId + " ]";
    }

    /**
     * @return the productPackCode
     */
    public String getProductPackCode() {
        return productPackCode;
    }

    /**
     * @param productPackCode the productPackCode to set
     */
    public void setProductPackCode(String productPackCode) {
        this.productPackCode = productPackCode;
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
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
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
