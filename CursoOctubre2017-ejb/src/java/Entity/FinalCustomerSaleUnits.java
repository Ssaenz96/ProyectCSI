/*
* Nombre de la clase: FinalCustomerSaleUnits
*
* Información de la versión: version 2
*
* Fecha: 04/10/2017
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
public class FinalCustomerSaleUnits implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerSaleUnitId;

    @Column(name = "CustomerSUCode", length = 2, nullable = false)
    private String customerSUCode;
    
    @Column(name = "Description", length = 35, nullable = false)
    private String description;
    
    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;

    @OneToMany(mappedBy = "finalCustomerSaleUnits", cascade = CascadeType.REMOVE)
    private List<Products> listProducts;
    
    public Long getId() {
        return customerSaleUnitId;
    }

    public Long getCustomerSaleUnitId() {
        return customerSaleUnitId;
    }

    public String getCustomerSUCode() {
        return customerSUCode;
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

    public void setId(Long id) {
        this.customerSaleUnitId = id;
    }

    public void setCustomerSaleUnitId(Long customerSaleUnitId) {
        this.customerSaleUnitId = customerSaleUnitId;
    }

    public void setCustomerSUCode(String customerSUCode) {
        this.customerSUCode = customerSUCode;
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
        hash += (customerSaleUnitId != null ? customerSaleUnitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FinalCustomerSaleUnits)) {
            return false;
        }
        FinalCustomerSaleUnits other = (FinalCustomerSaleUnits) object;
        return !((this.customerSaleUnitId == null 
                && other.customerSaleUnitId != null) 
                || (this.customerSaleUnitId != null 
                && !this.customerSaleUnitId.equals(other.customerSaleUnitId)));
    }

    @Override
    public String toString() {
        return "Entity.FinalCustomerSaleUnits[ id=" + customerSaleUnitId + " ]";
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