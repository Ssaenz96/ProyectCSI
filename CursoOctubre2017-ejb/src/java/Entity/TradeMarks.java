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
public class TradeMarks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tradeMarkId;
    
    @Column(name = "TradeMarkCode", length = 6)
    private String tradeMarkCode;
    
    @Column(name = "Description", length = 35)
    private String description;
    
    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    @OneToMany(mappedBy = "tradeMarks", cascade = CascadeType.REMOVE)
    private List<Products> listProducts;

    public Long getTradeMarkId() {
        return tradeMarkId;
    }

    public void setTradeMarkId(Long tradeMarkId) {
        this.tradeMarkId = tradeMarkId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tradeMarkId != null ? tradeMarkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the tradeMarkId fields are not set
        if (!(object instanceof TradeMarks)) {
            return false;
        }
        TradeMarks other = (TradeMarks) object;
        if ((this.tradeMarkId == null 
                && other.tradeMarkId != null) 
                || (this.tradeMarkId != null 
                && !this.tradeMarkId.equals(other.tradeMarkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.TradeMarks[ id=" + tradeMarkId + " ]";
    }

    /**
     * @return the tradeMarkCode
     */
    public String getTradeMarkCode() {
        return tradeMarkCode;
    }

    /**
     * @param tradeMarkCode the tradeMarkCode to set
     */
    public void setTradeMarkCode(String tradeMarkCode) {
        this.tradeMarkCode = tradeMarkCode;
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
