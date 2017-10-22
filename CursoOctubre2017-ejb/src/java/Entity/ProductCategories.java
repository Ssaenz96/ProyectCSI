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
public class ProductCategories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodCategoryId;

    @Column(name = "ProdCategoryCode", length = 3, nullable = false)
    private String prodCategoryCode;

    @Column(name = "Description", length = 35, nullable = false)
    private String description;

    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;

    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;

    @OneToMany(mappedBy = "productCategories", cascade = CascadeType.REMOVE)
    private List<Products> listProducts;

    public Long getProdCategoryId() {
        return prodCategoryId;
    }

    public void setProdCategoryId(Long prodCategoryId) {
        this.prodCategoryId = prodCategoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prodCategoryId != null ? prodCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the prodCategoryId fields are not set
        if (!(object instanceof ProductCategories)) {
            return false;
        }
        ProductCategories other = (ProductCategories) object;
        if ((this.prodCategoryId == null 
                && other.prodCategoryId != null) 
                || (this.prodCategoryId != null 
                && !this.prodCategoryId.equals(other.prodCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ProductCategories[ id=" + prodCategoryId + " ]";
    }

    /**
     * @return the prodCategoryCode
     */
    public String getProdCategoryCode() {
        return prodCategoryCode;
    }

    /**
     * @param prodCategoryCode the prodCategoryCode to set
     */
    public void setProdCategoryCode(String prodCategoryCode) {
        this.prodCategoryCode = prodCategoryCode;
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
