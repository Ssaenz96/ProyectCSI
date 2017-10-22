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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Gerardo Cardenas
 * Clase: States
 * Version 2.0
 * Fecha: 05/10/2017
 * Copyrigth
 */
/**
 * @author Luis Gerardo Garcia
 * Clase: States
 * Version 3.0 (Region id puede ser null)
 * Fecha: 05/10/2017
 * Copyrigth
 */
@Entity
@Table(schema ="GARCOMEX")
public class States implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stateId;
    
    @Column(name = "StateCode",length = 2,nullable = false)
    private String stateCode;
    
    @Column(name = "Name",length = 35,nullable = false)
    private String name;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    //RELACION CON LAS DEMAS TABLAS
    @JoinColumn(name = "RegionId", referencedColumnName = "regionId")
    @ManyToOne(optional = false)
    private Regions region;
    
    @OneToMany(mappedBy = "states", cascade = CascadeType.REMOVE)
    private List<Cities> listCities;

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastUpdateBI() {
        return lastUpdateBI;
    }

    public void setLastUpdateBI(Date lastUpdateBI) {
        this.lastUpdateBI = lastUpdateBI;
    }
    
    
    
    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stateId != null ? stateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof States)) {
            return false;
        }
        States other = (States) object;
        if ((this.stateId == null && other.stateId != null) || (this.stateId != null && !this.stateId.equals(other.stateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.States[ id=" + stateId + " ]";
    }

    /**
     * @return the region
     */
    public Regions getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(Regions region) {
        this.region = region;
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
