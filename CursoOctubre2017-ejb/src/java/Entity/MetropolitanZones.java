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
public class MetropolitanZones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metroZoneId;
    
    @Column(name = "MetroZoneCode", length = 2, nullable = false)
    private String metroZoneCode;
    
    @Column(name = "Name", length = 50, nullable = false)
    private String name;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    //RELACION CON LAS DEMAS TABLAS
    @OneToMany(mappedBy = "metropolitanZones", cascade = CascadeType.REMOVE)
    private List<Cities> listCities;
    
    
    public Long getMetroZoneId() {
        return metroZoneId;
    }

    public void setMetroZoneId(Long metroZoneId) {
        this.metroZoneId = metroZoneId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (metroZoneId != null ? metroZoneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the metroZoneId fields are not set
        if (!(object instanceof MetropolitanZones)) {
            return false;
        }
        MetropolitanZones other = (MetropolitanZones) object;
        if ((this.metroZoneId == null && other.metroZoneId != null) || (this.metroZoneId != null && !this.metroZoneId.equals(other.metroZoneId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.MetropolitanZones[ id=" + metroZoneId + " ]";
    }

    /**
     * @return the metroZoneCode
     */
    public String getMetroZoneCode() {
        return metroZoneCode;
    }

    /**
     * @param metroZoneCode the metroZoneCode to set
     */
    public void setMetroZoneCode(String metroZoneCode) {
        this.metroZoneCode = metroZoneCode;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the listCities
     */
    public List<Cities> getListCities() {
        return listCities;
    }

    /**
     * @param listCities the listCities to set
     */
    public void setListCities(List<Cities> listCities) {
        this.listCities = listCities;
    }
    
}
