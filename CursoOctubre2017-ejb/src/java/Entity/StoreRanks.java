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
public class StoreRanks implements Serializable {

    /**
     * @return the listStores
     */
    public List<Stores> getListStores() {
        return listStores;
    }

    /**
     * @param listStores the listStores to set
     */
    public void setListStores(List<Stores> listStores) {
        this.listStores = listStores;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeRankId;
    
    @Column(name="StoreRankCode", length = 2, nullable = false)
    private String storeRankCode;
    
    @Column(name="Description", length = 35, nullable = false)
    private String description;
    
    @Column(name="TradeCompanyId")
    private int tradeCompanyId;
    
    @Column(name="LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    @OneToMany(mappedBy = "storeRanks", cascade = CascadeType.REMOVE)
    private List<Stores> listStores;


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getStoreRankId() != null ? getStoreRankId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreRanks)) {
            return false;
        }
        StoreRanks other = (StoreRanks) object;
        if ((this.getStoreRankId() == null && other.getStoreRankId() != null) || (this.getStoreRankId() != null && !this.storeRankId.equals(other.storeRankId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.NewEntity[ id=" + getStoreRankId() + " ]";
    }

    /**
     * @return the storeRankId
     */
    public Long getStoreRankId() {
        return storeRankId;
    }

    /**
     * @param storeRankId the storeRankId to set
     */
    public void setStoreRankId(Long storeRankId) {
        this.storeRankId = storeRankId;
    }

    /**
     * @return the storeRankCode
     */
    public String getStoreRankCode() {
        return storeRankCode;
    }

    /**
     * @param storeRankCode the storeRankCode to set
     */
    public void setStoreRankCode(String storeRankCode) {
        this.storeRankCode = storeRankCode;
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
    
}
