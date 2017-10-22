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
public class GSaleStructureLevels implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long GSaleStructLevelId;

    @Column(name = "GSaleStructLevel", length = 2, nullable = false)
    private String GSaleStructLevel;

    @Column(name = "Description", length = 35, nullable = false)
    private String description;

    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;

    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;

    //RELACION CON LAS DEMAS TABLAS
    @OneToMany(mappedBy = "GSaleStructureLevels", cascade = CascadeType.REMOVE)
    private List<GSaleStructures> listGSaleStructures;


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (GSaleStructLevelId != null ? GSaleStructLevelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the gSaleStructLevelId fields are not set
        if (!(object instanceof GSaleStructureLevels)) {
            return false;
        }
        GSaleStructureLevels other = (GSaleStructureLevels) object;
        if ((this.GSaleStructLevelId == null && other.GSaleStructLevelId != null) || (this.GSaleStructLevelId != null && !this.GSaleStructLevelId.equals(other.GSaleStructLevelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.GSaleStructureLevels[ id=" + GSaleStructLevelId + " ]";
    }

    public String getGSaleStructLevel() {
        return GSaleStructLevel;    
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
     * @return the listGSaleStructures
     */
    public List<GSaleStructures> getListGSaleStructures() {
        return listGSaleStructures;
    }

    /**
     * @param listGSaleStructures the listGSaleStructures to set
     */
    public void setListGSaleStructures(List<GSaleStructures> listGSaleStructures) {
        this.listGSaleStructures = listGSaleStructures;
    }

    public Long getGSaleStructLevelId() {
        return GSaleStructLevelId;
    }

    public void setGSaleStructLevelId(Long GSaleStructLevelId) {
        this.GSaleStructLevelId = GSaleStructLevelId;
    }

    public void setGSaleStructLevel(String GSaleStructLevel) {
        this.GSaleStructLevel = GSaleStructLevel;
    }
    

}
