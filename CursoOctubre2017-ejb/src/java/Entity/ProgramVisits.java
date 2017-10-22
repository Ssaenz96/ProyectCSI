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
public class ProgramVisits implements Serializable {

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
    private Long progVisitId;
    
    @Column(name = "ProgVisitCode", length = 2, nullable = false)
    private String profVisitCode;
    
    @Column(name = "Description", length = 35, nullable = false)
    private String description;
    
    @Column(name = "TradeCompanyBI")
    private int tradeCompanyBI;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    @OneToMany(mappedBy = "programVisits", cascade = CascadeType.REMOVE)
    private List<Stores> listStores;

    public Long getProgVisitId() {
        return progVisitId;
    }

    public void setProgVisitId(Long progVisitId) {
        this.progVisitId = progVisitId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (progVisitId != null ? progVisitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the progVisitId fields are not set
        if (!(object instanceof ProgramVisits)) {
            return false;
        }
        ProgramVisits other = (ProgramVisits) object;
        if ((this.progVisitId == null && other.progVisitId != null) || (this.progVisitId != null && !this.progVisitId.equals(other.progVisitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ProgramVisits[ id=" + progVisitId + " ]";
    }

    /**
     * @return the profVisitCode
     */
    public String getProfVisitCode() {
        return profVisitCode;
    }

    /**
     * @param profVisitCode the profVisitCode to set
     */
    public void setProfVisitCode(String profVisitCode) {
        this.profVisitCode = profVisitCode;
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
     * @return the tradeCompanyBI
     */
    public int getTradeCompanyBI() {
        return tradeCompanyBI;
    }

    /**
     * @param tradeCompanyBI the tradeCompanyBI to set
     */
    public void setTradeCompanyBI(int tradeCompanyBI) {
        this.tradeCompanyBI = tradeCompanyBI;
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
