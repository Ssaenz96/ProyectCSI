/**
 * @author Luis Amaury Urbina Molina
 * Clase: Stores
 * Version 1.0
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
public class Stores implements Serializable {

    /**
     * @return the demostratorId
     */
    public int getDemostratorId() {
        return demostratorId;
    }

    /**
     * @param demostratorId the demostratorId to set
     */
    public void setDemostratorId(int demostratorId) {
        this.demostratorId = demostratorId;
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
     * @return the retails
     */
    public Retails getRetails() {
        return retails;
    }

    /**
     * @param retails the retails to set
     */
    public void setRetails(Retails retails) {
        this.retails = retails;
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
     * @return the storeRanks
     */
    public StoreRanks getStoreRanks() {
        return storeRanks;
    }

    /**
     * @param storeRanks the storeRanks to set
     */
    public void setStoreRanks(StoreRanks storeRanks) {
        this.storeRanks = storeRanks;
    }

    /**
     * @return the sellers
     */
    public Sellers getSellers() {
        return sellers;
    }

    /**
     * @param sellers the sellers to set
     */
    public void setSellers(Sellers sellers) {
        this.sellers = sellers;
    }

    /**
     * @return the programVisits
     */
    public ProgramVisits getProgramVisits() {
        return programVisits;
    }

    /**
     * @param programVisits the programVisits to set
     */
    public void setProgramVisits(ProgramVisits programVisits) {
        this.programVisits = programVisits;
    }

    public GSaleStructures getGSaleStructures() {
        return GSaleStructures;
    }

    public void setGSaleStructures(GSaleStructures GSaleStructures) {
        this.GSaleStructures = GSaleStructures;
    }

  

    /**
     * @return the listStoreProducts
     */
    public List<StoreProducts> getListStoreProducts() {
        return listStoreProducts;
    }

    /**
     * @param listStoreProducts the listStoreProducts to set
     */
    public void setListStoreProducts(List<StoreProducts> listStoreProducts) {
        this.listStoreProducts = listStoreProducts;
    }

    /**
     * @return the listStoreContacts
     */
    public List<StoreContacts> getListStoreContacts() {
        return listStoreContacts;
    }

    /**
     * @param listStoreContacts the listStoreContacts to set
     */
    public void setListStoreContacts(List<StoreContacts> listStoreContacts) {
        this.listStoreContacts = listStoreContacts;
    }

    /**
     * @return the listPurchaseOrders
     */
    public List<PurchaseOrders> getListPurchaseOrders() {
        return listPurchaseOrders;
    }

    /**
     * @param listPurchaseOrders the listPurchaseOrders to set
     */
    public void setListPurchaseOrders(List<PurchaseOrders> listPurchaseOrders) {
        this.listPurchaseOrders = listPurchaseOrders;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;
    
    @Column(name = "StoreCode",length = 4, nullable = false)
    private String storeCode;
    
    @Column (name = "Name", length = 35,nullable = false)
    private String name;
    
    @Column(name = "Street",length = 70,nullable = false)
    private String street;
    
    @Column (name = "OutsideNumber",length = 6)
    private String outsideNumber;
    
    @Column (name = "InsideNumber",length = 6)
    private String insideNumber;
    
    @Column(name = "Neighborhood",length = 30,nullable = false)
    private String neighborhood;
    
    @Column(name = "PostalCode",length = 8,nullable = false)
    private String postalCode;
    
    @Column(name = "EnableDate")
    @Temporal (TemporalType.DATE)
    private Date enableDate;
    
    @Column(name = "EnabledBy", length = 50)
    private String enabledBy;
    
    @Column(name ="DisableDate")
    @Temporal(TemporalType.DATE)
    private Date disableDate;
    
    @Column(name = "DisabledBy", length = 50)
    private String disabledBy;
    
    @Column(name ="LastChangeDate")
    @Temporal(TemporalType.DATE)
    private Date lastChangeDate;
    
    @Column(name = "ChangedBy", length = 50)
    private String changedBy;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    //Campos sin entidades existentes
    @Column(name = "DemostratorId")
    private int demostratorId;
    
    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;
    
    //Relaciones con entidades hijas
    @JoinColumn(name = "RetailId",nullable = false,referencedColumnName = "retailId")
    @ManyToOne(optional = false)
    private Retails retails;
    
    @JoinColumn(name = "CityId",nullable = false,referencedColumnName = "cityId")
    @ManyToOne(optional = false)
    private Cities cities;
    
    @JoinColumn(name = "StoreRankId",nullable = false,referencedColumnName = "storeRankId")
    @ManyToOne(optional = false)
    private StoreRanks storeRanks;
    
    @JoinColumn(name = "SellerId",nullable = false , referencedColumnName = "sellerId")
    @ManyToOne(optional = false)
    private Sellers sellers;
    
    @JoinColumn(name = "ProgVisitId",nullable = false,referencedColumnName = "progVisitId")
    @ManyToOne(optional = false)
    private ProgramVisits programVisits;
    
    @JoinColumn(name = "SaleStructId",nullable = false,referencedColumnName = "GSaleStructId")
    @ManyToOne(optional = false)
    private GSaleStructures GSaleStructures;
    
    @OneToMany(mappedBy = "stores",cascade = CascadeType.REMOVE)
    private List<StoreProducts> listStoreProducts;
    
    @OneToMany(mappedBy = "stores",cascade = CascadeType.REMOVE)
    private List<StoreContacts> listStoreContacts;
    
    @OneToMany(mappedBy = "stores",cascade = CascadeType.REMOVE)
    private List<PurchaseOrders> listPurchaseOrders;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getStoreId() != null ? getStoreId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stores)) {
            return false;
        }
        Stores other = (Stores) object;
        if ((this.getStoreId() == null && other.getStoreId() != null) || (this.getStoreId() != null && !this.storeId.equals(other.storeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Stores[ id=" + getStoreId() + " ]";
    }

    /**
     * @return the storeId
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * @param storeId the storeId to set
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    /**
     * @return the storeCode
     */
    public String getStoreCode() {
        return storeCode;
    }

    /**
     * @param storeCode the storeCode to set
     */
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
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
     * @return the outsideNumber
     */
    public String getOutsideNumber() {
        return outsideNumber;
    }

    /**
     * @param outsideNumber the outsideNumber to set
     */
    public void setOutsideNumber(String outsideNumber) {
        this.outsideNumber = outsideNumber;
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
     * @return the changedBy
     */
    public String getChangedBy() {
        return changedBy;
    }

    /**
     * @param changedBy the changedBy to set
     */
    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
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
