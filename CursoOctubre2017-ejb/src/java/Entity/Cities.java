/**
 * @author Thania López
 * Clase: Cities
 * Version 2.0
 * Fecha: 05/10/2017
 * Copyrigth
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
public class Cities implements Serializable {

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

    /**
     * @return the listRetails
     */
    public List<Retails> getListRetails() {
        return listRetails;
    }

    /**
     * @param listRetails the listRetails to set
     */
    public void setListRetails(List<Retails> listRetails) {
        this.listRetails = listRetails;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;
    
    @Column(name = "CityCode", length = 3, nullable = false)
    private String cityCode;
    
    @Column(name = "Name", length = 40, nullable = false)
    private String name;
    
    @Column(name = "population", precision = 8, scale = 0)
    private double population;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "LastUpdateBi")
    private Date lastUpdateBi;
    
    //RELACION CON LAS DEMAS TABLAS
    @JoinColumn(name = "StateId",nullable = false, referencedColumnName = "stateId")
    @ManyToOne(optional = false)
    private States states;
    
    @JoinColumn(name = "MetroZoneId",nullable = false, referencedColumnName = "metroZoneId")
    @ManyToOne(optional = false)
    private MetropolitanZones metropolitanZones;
    
    @OneToMany(mappedBy = "cities", cascade = CascadeType.REMOVE)
    private List<Retails> listRetails;
    
    @OneToMany(mappedBy = "cities", cascade = CascadeType.REMOVE)
    private List<Stores> listStores;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getCityId() != null ? getCityId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cities)) {
            return false;
        }
        Cities other = (Cities) object;
        if ((this.getCityId() == null && other.getCityId() != null) || (this.getCityId() != null && !this.cityId.equals(other.cityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Cities[ id=" + getCityId() + " ]";
    }

    /**
     * @return the cityId
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * @param cityId the cityId to set
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     * @return the cityCode
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * @param cityCode the cityCode to set
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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
     * @return the population
     */
    public double getPopulation() {
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(double population) {
        this.population = population;
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

    /**
     * @return the states
     */
    public States getStates() {
        return states;
    }

    /**
     * @param states the states to set
     */
    public void setStates(States states) {
        this.states = states;
    }

    /**
     * @return the metropolitanZones
     */
    public MetropolitanZones getMetropolitanZones() {
        return metropolitanZones;
    }

    /**
     * @param metropolitanZones the metropolitanZones to set
     */
    public void setMetropolitanZones(MetropolitanZones metropolitanZones) {
        this.metropolitanZones = metropolitanZones;
    }
    
}
