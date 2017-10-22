/*
* Nombre de la clase: GSaleStructures
*
* Información de la versión: version 2
*
* Fecha: 5/10/2017
*
* Copyright Thania López
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
public class GSaleStructures implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long GSaleStructId;
    
    @Column(name = "GSaleStructCode", length = 3, nullable = false)
    private String GSaleStructCode;
    
    @Column(name = "OwnCode", length = 10)
    private String ownCode;

    @Column(name = "description", length = 35, nullable = false)
    private String description;
    
    @Column(name = "SaleGeoStructIdUp")
    private int saleGeoStructIdUp;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "EnableDate")
    private Date enableDate;
    
    @Column(name = "EnabledBy", length = 50)
    private String enabledBy;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DisableDate")
    private Date disableDate;
    
    @Column(name = "DisabledBy", length = 50)
    private String disabledBy;
    
    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;
   
    @Temporal(TemporalType.DATE)
    @Column(name = "LastUpdateBi")
    private Date lastUpdateBi;
    
    //Relacion con las demas Tablas
    @JoinColumn(name = "GSaleStructLevelId", nullable = false, referencedColumnName = "GSaleStructLevelId")
    @ManyToOne(optional = false)
    private GSaleStructureLevels GSaleStructureLevels;
    
    @OneToMany(mappedBy = "GSaleStructures", cascade = CascadeType.REMOVE)
    private List<Sellers> listSellers;
    
    @OneToMany(mappedBy = "GSaleStructures", cascade = CascadeType.REMOVE)
    private List<Stores> listStores;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (GSaleStructId != null ? GSaleStructId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GSaleStructures)) {
            return false;
        }
        GSaleStructures other = (GSaleStructures) object;
        if ((this.GSaleStructId== null && other.GSaleStructId != null) || (this.GSaleStructId != null && !this.GSaleStructId.equals(other.GSaleStructId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.GSaleStructures[ id=" + GSaleStructId + " ]";
    }

    public Long getGSaleStructId() {
        return GSaleStructId;
    }

    public void setGSaleStructId(Long GSaleStructId) {
        this.GSaleStructId = GSaleStructId;
    }


    public String getGSaleStructCode() {
        return GSaleStructCode;
    }

    /**
     * @return the gSaleStructCode
     */
    public void setGSaleStructCode(String GSaleStructCode) {
        this.GSaleStructCode = GSaleStructCode;
    }

    /**
     * @return the ownCode
     */
    public String getOwnCode() {
        return ownCode;
    }

    /**
     * @param ownCode the ownCode to set
     */
    public void setOwnCode(String ownCode) {
        this.ownCode = ownCode;
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
     * @return the saleGeoStructIdUp
     */
    public int getSaleGeoStructIdUp() {
        return saleGeoStructIdUp;
    }

    /**
     * @param saleGeoStructIdUp the saleGeoStructIdUp to set|
     */
    public void setSaleGeoStructIdUp(int saleGeoStructIdUp) {
        this.saleGeoStructIdUp = saleGeoStructIdUp;
    }

    /**
     * @return the enableDate
     */
    public Date getEnableDate() {
        return enableDate;
    }

    /**
     * @param enableDate the enableDate to set
     */
    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    /**
     * @return the enabledBy
     */
    public String getEnabledBy() {
        return enabledBy;
    }

    /**
     * @param enabledBy the enabledBy to set
     */
    public void setEnabledBy(String enabledBy) {
        this.enabledBy = enabledBy;
    }

    /**
     * @return the disableDate
     */
    public Date getDisableDate() {
        return disableDate;
    }

    /**
     * @param disableDate the disableDate to set
     */
    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    /**
     * @return the disabledBy
     */
    public String getDisabledBy() {
        return disabledBy;
    }

    /**
     * @param disabledBy the disabledBy to set
     */
    public void setDisabledBy(String disabledBy) {
        this.disabledBy = disabledBy;
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
     * @return the lastUpdateBi
     */
    public Date getLastUpdateBi() {
        return lastUpdateBi;
    }

    /**
     * @param lastUpdateBi the lastUpdateBi to set
     */
    public void setLastUpdateBi(Date lastUpdateBi) {
        this.lastUpdateBi = lastUpdateBi;
    }

    public GSaleStructureLevels getGSaleStructureLevels() {
        return GSaleStructureLevels;
    }

    /**
     * @return the gSaleStructureLevels
     */
    public void setGSaleStructureLevels(GSaleStructureLevels GSaleStructureLevels) {
        this.GSaleStructureLevels = GSaleStructureLevels;
    }

    /**
     * @return the listSellers
     */
    public List<Sellers> getListSellers() {
        return listSellers;
    }

    /**
     * @param listSellers the listSellers to set
     */
    public void setListSellers(List<Sellers> listSellers) {
        this.listSellers = listSellers;
    }

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
    
}
