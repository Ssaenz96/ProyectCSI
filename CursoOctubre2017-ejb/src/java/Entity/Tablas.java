/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Usuario6
 */
@Entity
@Table(schema = "GARCOMEX")
public class Tablas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tablasId;
    
    @Column(name = "Channels")
    private boolean channels;
    
    @Column(name = "Cities")
    private boolean cities;
    
    @Column(name = "Contacts")
    private boolean contacts;
    
    @Column(name = "EmployeeTypes")
    private boolean employeeTypes;
    
    @Column(name = "FinalCustomerSaleUnits")
    private boolean finalCustomerSaleUnits;
    
    @Column(name = "GSaleStructureLevels")
    private boolean gSaleStructureLeveles;
    
    @Column(name = "GSaleStructure")
    private boolean gSaleStructure;
    
    @Column(name = "Groups")
    private boolean groups;
    
    @Column(name = "MetropolitanZones")
    private boolean metropolitanZones;
    
    @Column(name = "Origin")
    private boolean origin;
    
    @Column(name = "Positions")
    private boolean positions;
    
    @Column(name = "ProductCategories")
    private boolean productCategories;
    
    @Column(name = "ProductPackages")
    private boolean productPackages;
    
    @Column(name = "Products")
    private boolean products;
    
    @Column(name = "ProgramVisits")
    private boolean programVisits;
    
    @Column(name = "PurchaseOrderDetails")
    private boolean purchaseOrderDetails;
    
    @Column(name = "PurchaseOrderStatus")
    private boolean purchaseOrderStatus;
    
    @Column(name = "PurchaseOrders")
    private boolean purchaseOrders;
    
    @Column(name = "Regions")
    private boolean Regions;
    
    @Column(name = "RetailContacts")
    private boolean retailContacts;
    
    @Column(name = "RetailProducts")
    private boolean retailProducts;
    
    @Column(name = "RetailSaleUnits")
    private boolean retailSaleUnits;
    
    @Column(name = "Retails")
    private boolean retails;
    
    @Column(name = "Sellers")
    private boolean sellers;
    
    @Column(name = "States")
    private boolean states;
    
    @Column(name = "StoreContacts")
    private boolean storeContacts;
    
    @Column(name = "StoreProducts")
    private boolean storeProducts;
    
    @Column(name = "StoreRanks")
    private boolean storeRanks;
    
    @Column(name = "Stores")
    private boolean stores;
    
    @Column(name = "Taxes")
    private boolean taxes;
    
    @Column(name = "TradeMarks")
    private boolean tradeMarks;
    
    @OneToMany(mappedBy = "Tablas", cascade = CascadeType.REMOVE)
    private List<Groups> listGroups;
    

    public Long getTablasId() {
        return tablasId;
    }

    public void setTablasId(Long tablasId) {
        this.tablasId = tablasId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tablasId != null ? tablasId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablas)) {
            return false;
        }
        Tablas other = (Tablas) object;
        if ((this.tablasId == null && other.tablasId != null) || (this.tablasId != null && !this.tablasId.equals(other.tablasId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Tablas[ id=" + tablasId + " ]";
    }

    public boolean isChannels() {
        return channels;
    }

    public void setChannels(boolean channels) {
        this.channels = channels;
    }

    public boolean isCities() {
        return cities;
    }

    public void setCities(boolean cities) {
        this.cities = cities;
    }

    public boolean isContacts() {
        return contacts;
    }

    public void setContacts(boolean contacts) {
        this.contacts = contacts;
    }

    public boolean isEmployeeTypes() {
        return employeeTypes;
    }

    public void setEmployeeTypes(boolean employeeTypes) {
        this.employeeTypes = employeeTypes;
    }

    public boolean isFinalCustomerSaleUnits() {
        return finalCustomerSaleUnits;
    }

    public void setFinalCustomerSaleUnits(boolean finalCustomerSaleUnits) {
        this.finalCustomerSaleUnits = finalCustomerSaleUnits;
    }

    public boolean isgSaleStructureLeveles() {
        return gSaleStructureLeveles;
    }

    public void setgSaleStructureLeveles(boolean gSaleStructureLeveles) {
        this.gSaleStructureLeveles = gSaleStructureLeveles;
    }

    public boolean isGroups() {
        return groups;
    }

    public void setGroups(boolean groups) {
        this.groups = groups;
    }

    public boolean isMetropolitanZones() {
        return metropolitanZones;
    }

    public void setMetropolitanZones(boolean metropolitanZones) {
        this.metropolitanZones = metropolitanZones;
    }

    public boolean isOrigin() {
        return origin;
    }

    public void setOrigin(boolean origin) {
        this.origin = origin;
    }

    public boolean isPositions() {
        return positions;
    }

    public void setPositions(boolean positions) {
        this.positions = positions;
    }

    public boolean isProductCategories() {
        return productCategories;
    }

    public void setProductCategories(boolean productCategories) {
        this.productCategories = productCategories;
    }

    public boolean isProductPackages() {
        return productPackages;
    }

    public void setProductPackages(boolean productPackages) {
        this.productPackages = productPackages;
    }

    public boolean isProducts() {
        return products;
    }

    public void setProducts(boolean products) {
        this.products = products;
    }

    public boolean isProgramVisits() {
        return programVisits;
    }

    public void setProgramVisits(boolean programVisits) {
        this.programVisits = programVisits;
    }

    public boolean isPurchaseOrderDetails() {
        return purchaseOrderDetails;
    }

    public void setPurchaseOrderDetails(boolean purchaseOrderDetails) {
        this.purchaseOrderDetails = purchaseOrderDetails;
    }

    public boolean isPurchaseOrderStatus() {
        return purchaseOrderStatus;
    }

    public void setPurchaseOrderStatus(boolean purchaseOrderStatus) {
        this.purchaseOrderStatus = purchaseOrderStatus;
    }

    public boolean isPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(boolean purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public boolean isRegions() {
        return Regions;
    }

    public void setRegions(boolean Regions) {
        this.Regions = Regions;
    }

    public boolean isRetailContacts() {
        return retailContacts;
    }

    public void setRetailContacts(boolean retailContacts) {
        this.retailContacts = retailContacts;
    }

    public boolean isRetailProducts() {
        return retailProducts;
    }

    public void setRetailProducts(boolean retailProducts) {
        this.retailProducts = retailProducts;
    }

    public boolean isRetailSaleUnits() {
        return retailSaleUnits;
    }

    public void setRetailSaleUnits(boolean retailSaleUnits) {
        this.retailSaleUnits = retailSaleUnits;
    }

    public boolean isRetails() {
        return retails;
    }

    public void setRetails(boolean retails) {
        this.retails = retails;
    }

    public boolean isSellers() {
        return sellers;
    }

    public void setSellers(boolean sellers) {
        this.sellers = sellers;
    }

    public boolean isStates() {
        return states;
    }

    public void setStates(boolean states) {
        this.states = states;
    }

    public boolean isStoreContacts() {
        return storeContacts;
    }

    public void setStoreContacts(boolean storeContacts) {
        this.storeContacts = storeContacts;
    }

    public boolean isStoreProducts() {
        return storeProducts;
    }

    public void setStoreProducts(boolean storeProducts) {
        this.storeProducts = storeProducts;
    }

    public boolean isStoreRanks() {
        return storeRanks;
    }

    public void setStoreRanks(boolean storeRanks) {
        this.storeRanks = storeRanks;
    }

    public boolean isStores() {
        return stores;
    }

    public void setStores(boolean stores) {
        this.stores = stores;
    }

    public boolean isTaxes() {
        return taxes;
    }

    public void setTaxes(boolean taxes) {
        this.taxes = taxes;
    }

    public boolean isTradeMarks() {
        return tradeMarks;
    }

    public void setTradeMarks(boolean tradeMarks) {
        this.tradeMarks = tradeMarks;
    }

    public List<Groups> getListGroups() {
        return listGroups;
    }

    public void setListGroups(List<Groups> listGroups) {
        this.listGroups = listGroups;
    }

    public boolean isgSaleStructure() {
        return gSaleStructure;
    }

    public void setgSaleStructure(boolean gSaleStructure) {
        this.gSaleStructure = gSaleStructure;
    }
    
    
}
