/*
* Nombre de la clase: Taxes
*
* Información de la versión: version 3
*
* Fecha: 05/10/2017
*
* Copyright: Eduardo Alejandro Martínez Melo
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
import javax.validation.constraints.Past;

@Entity
@Table(schema = "GARCOMEX")
public class Taxes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taxId;

    @Column(name = "TaxCode", length = 1, nullable = false)
    private String taxCode;
    
    @Column(name = "TaxPercent", precision = 5, scale = 2, nullable = false)
    private double taxPercent;
    
    @Column(name = "Description", length = 35)
    private String description;
    
    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    @OneToMany(mappedBy = "taxes", cascade = CascadeType.REMOVE)
    private List<Products> listProducts;
    
    public Long getTaxId() {
        return taxId;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public double getTaxPercent() {
        return taxPercent;
    }

    public String getDescription() {
        return description;
    }

    public int getTradeCompanyId() {
        return tradeCompanyId;
    }

    public Date getLastUpdateBI() {
        return lastUpdateBI;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public void setTaxPercent(double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTradeCompanyId(int tradeCompanyId) {
        this.tradeCompanyId = tradeCompanyId;
    }

    public void setLastUpdateBI(Date lastUpdateBI) {
        this.lastUpdateBI = lastUpdateBI;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxId != null ? taxId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Taxes)) {
            return false;
        }
        Taxes other = (Taxes) object;
        if ((this.taxId == null 
                && other.taxId != null) 
                || (this.taxId != null 
                && !this.taxId.equals(other.taxId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Taxes[ id=" + taxId + " ]";
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
