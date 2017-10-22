/*
 * Tabla #19

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
 * @author Saul Saenz
 */
@Entity
@Table(schema = "GARCOMEX")
public class Regions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regionId;

    @Column(name = "RegionCode", length = 5, nullable = false)
    private String regionCode;
    
    @Column(name = "Description", length = 50, nullable = false)
    private String description;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    @OneToMany(mappedBy = "region", cascade = CascadeType.REMOVE)
    private List<States> listStates;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getRegionId() != null ? getRegionId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regions)) {
            return false;
        }
        Regions other = (Regions) object;
        if ((this.getRegionId() == null && other.getRegionId() != null) || (this.getRegionId() != null && !this.regionId.equals(other.regionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Regions[ id=" + getRegionId() + " ]";
    }

    /**
     * @return the regionId
     */
    public Long getRegionId() {
        return regionId;
    }

    /**
     * @param regionId the regionId to set
     */
    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    /**
     * @return the regionCode
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * @param regionCode the regionCode to set
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
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
     * @return the listStates
     */
    public List<States> getListStates() {
        return listStates;
    }

    /**
     * @param listStates the listStates to set
     */
    public void setListStates(List<States> listStates) {
        this.listStates = listStates;
    }
    
}
