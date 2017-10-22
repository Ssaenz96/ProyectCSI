/*
 * PurchaseOrdersConverter
 *
 * Corrección Version 2.0 (Id.Identity y Last changed correcion de Time a Date)
 *
 * 1:00 pm 9/10/17
 *
 * Luis García.
 * 
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
public class Retails implements Serializable {

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
     * @return the listRetailProducts
     */
    public List<RetailProducts> getListRetailProducts() {
        return listRetailProducts;
    }

    /**
     * @param listRetailProducts the listRetailProducts to set
     */
    public void setListRetailProducts(List<RetailProducts> listRetailProducts) {
        this.listRetailProducts = listRetailProducts;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long retailId;

    @Column(name = "RetailCode", length = 3, nullable = false)
    private String retailCode;

    @Column(name = "Name", length = 35, nullable = false)
    private String name;

    @Column(name = "Street", length = 70, nullable = false)
    private String street;

    @Column(name = "OutSideNumber", length = 6, nullable = false)
    private String outSideNumber;

    @Column(name = "InsideNumber", length = 6)
    private String insideNumber;

    @Column(name = "Neighborhood", length = 30)
    private String neighborhood;

    @Column(name = "PostalCode", length = 8, nullable = false)
    private String postalCode;

    @Column(name = "SaleChargePercent", precision = 5, scale = 2, nullable = false)
    private double saleChargePercent;

    @Column(name = "SalePoints")
    private int salePoints;

    @Temporal(TemporalType.DATE)
    @Column(name = "EnableDate")
    private Date enableDate;

    @Column(name = "EnabledBy", length = 50)
    private String enabledBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "LastChangeDate")
    private Date lastChangeDate;

    @Column(name = "LastChangedBy", length = 50)
    private String lastChangedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "DisableDate")
    private Date disableDate;

    @Column(name = "DisabledBy", length = 50)
    private String DisabledBy;

    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;

    @Temporal(TemporalType.DATE)
    @Column(name = "LastUpdateBi")
    private Date lastUpdateBi;

    //RELACION CON LAS DEMAS TABLAS
    @JoinColumn(name = "ChannelId", nullable = false, referencedColumnName = "channelId")
    @ManyToOne(optional = false)
    private Channels channels;

    @JoinColumn(name = "CityId", nullable = false, referencedColumnName = "cityId")
    @ManyToOne(optional = false)
    private Cities cities;

    @JoinColumn(name = "GroupId", nullable = false, referencedColumnName = "groupId")
    @ManyToOne(optional = false)
    private Groups groups;
    
    @OneToMany(mappedBy = "retails", cascade = CascadeType.REMOVE)
    private List<RetailContacts> listRetailContacts;
    
    @OneToMany(mappedBy = "retails",cascade = CascadeType.REMOVE)
    private List<Stores> listStores;
    
    @OneToMany(mappedBy = "retails",cascade = CascadeType.REMOVE)
    private List<RetailProducts> listRetailProducts;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getRetailId() != null ? getRetailId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retails)) {
            return false;
        }
        Retails other = (Retails) object;
        if ((this.getRetailId() == null && other.getRetailId() != null) || (this.getRetailId() != null && !this.retailId.equals(other.retailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Retails[ id=" + getRetailId() + " ]";
    }

    //Get´s and Set´s
    /**
     * @return the retailId
     */
    public Long getRetailId() {
        return retailId;
    }

    /**
     * @param retailId the retailId to set
     */
    public void setRetailId(Long retailId) {
        this.retailId = retailId;
    }

    /**
     * @return the retailCode
     */
    public String getRetailCode() {
        return retailCode;
    }

    /**
     * @param retailCode the retailCode to set
     */
    public void setRetailCode(String retailCode) {
        this.retailCode = retailCode;
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
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the outSideNumber
     */
    public String getOutSideNumber() {
        return outSideNumber;
    }

    /**
     * @param outSideNumber the outSideNumber to set
     */
    public void setOutSideNumber(String outSideNumber) {
        this.outSideNumber = outSideNumber;
    }

    /**
     * @return the insideNumber
     */
    public String getInsideNumber() {
        return insideNumber;
    }

    /**
     * @param insideNumber the insideNumber to set
     */
    public void setInsideNumber(String insideNumber) {
        this.insideNumber = insideNumber;
    }

    /**
     * @return the neighborhood
     */
    public String getNeighborhood() {
        return neighborhood;
    }

    /**
     * @param neighborhood the neighborhood to set
     */
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the saleChargePercent
     */
    public double getSaleChargePercent() {
        return saleChargePercent;
    }

    /**
     * @param saleChargePercent the saleChargePercent to set
     */
    public void setSaleChargePercent(double saleChargePercent) {
        this.saleChargePercent = saleChargePercent;
    }

    /**
     * @return the salePoints
     */
    public int getSalePoints() {
        return salePoints;
    }

    /**
     * @param salePoints the salePoints to set
     */
    public void setSalePoints(int salePoints) {
        this.salePoints = salePoints;
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
     * @return the lastChangeDate
     */
    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    /**
     * @param lastChangeDate the lastChangeDate to set
     */
    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    /**
     * @return the lastChangedBy
     */
    public String getLastChangedBy() {
        return lastChangedBy;
    }

    /**
     * @param lastChangedBy the lastChangedBy to set
     */
    public void setLastChangedBy(String lastChangedBy) {
        this.lastChangedBy = lastChangedBy;
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
     * @return the DisabledBy
     */
    public String getDisabledBy() {
        return DisabledBy;
    }

    /**
     * @param DisabledBy the DisabledBy to set
     */
    public void setDisabledBy(String DisabledBy) {
        this.DisabledBy = DisabledBy;
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

    /**
     * @return the channels
     */
    public Channels getChannels() {
        return channels;
    }

    /**
     * @param channels the channels to set
     */
    public void setChannels(Channels channels) {
        this.channels = channels;
    }

    /**
     * @return the cities
     */
    public Cities getCities() {
        return cities;
    }

    /**
     * @param cities the cities to set
     */
    public void setCities(Cities cities) {
        this.cities = cities;
    }

    /**
     * @return the groups
     */
    public Groups getGroups() {
        return groups;
    }

    /**
     * @param groups the groups to set
     */
    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    /**
     * @return the listRetailContacts
     */
    public List<RetailContacts> getListRetailContacts() {
        return listRetailContacts;
    }

    /**
     * @param listRetailContacts the listRetailContacts to set
     */
    public void setListRetailContacts(List<RetailContacts> listRetailContacts) {
        this.listRetailContacts = listRetailContacts;
    }

}
