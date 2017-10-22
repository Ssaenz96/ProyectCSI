/*
* Products.java
*
* version 2
*
* Fecha 05/10/2017
*
* Jesús de la Cruz
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

/**---
 *
 * @author Usuario
 */
@Entity
@Table(schema = "GARCOMEX")
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "ProductStructureId", nullable = false)
    private int prodStructureId;

    @Column(name = "ProductCode", length = 15, nullable = false)
    private String productCode;

    @Column(name = "ProductBarcode", length = 13, nullable = false)
    private String productBarcode;

    @Column(name = "Description", length = 70, nullable = false)
    private String description;

    @Column(name = "ProductionCost", precision = 7, scale = 2, nullable = false)
    private double productionCost;

    @Column(name = "ConvertFactorPackage", precision = 9, scale = 8)
    private double convertFactorPackage;

    @Column(name = "PackageQuantity", precision = 4, scale = 0)
    private double packageQuantity;

    @Column(name = "CovertFactor", precision = 9, scale = 8)
    private double convertFactor;

    @Column(name = "ConvertFactorUnit")
    private int convertFactorUnit;

    @Column(name = "EnableDate")
    @Temporal(TemporalType.DATE)
    private Date enableDate;

    @Column(name = "EnabledBy", length = 50)
    private String enabledBy;

    @Column(name = "LastChangeDate")
    @Temporal(TemporalType.DATE)
    private Date lastChangeDate;

    @Column(name = "LastChangedBy", length = 50)
    private String lastChangedBy;

    @Column(name = "DisableDate")
    @Temporal(TemporalType.DATE)
    private Date disableDate;

    @Column(name = "DisabledBy", length = 50)
    private String disabledBy;

    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;

    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    //RELACIONES MANY TO ONE
    @JoinColumn(name = "TradeMarkId", referencedColumnName = "tradeMarkId")
    @ManyToOne(optional = false)
    private TradeMarks tradeMarks;
    
    @JoinColumn(name = "ProductPackId", nullable = false, referencedColumnName = "productPackId")
    @ManyToOne(optional = false)
    private ProductPackages productPackages;
    
    @JoinColumn(name = "CustomerSUId", nullable = false, referencedColumnName = "customerSaleUnitId")
    @ManyToOne(optional = false)
    private FinalCustomerSaleUnits finalCustomerSaleUnits;
    
    @JoinColumn(name = "RetailSUId", nullable = false, referencedColumnName = "retailSUId")
    @ManyToOne(optional = false)
    private RetailSaleUnits retailSaleUnits;
    
    @JoinColumn(name = "ProdCategoryId",  referencedColumnName = "prodCategoryId")
    @ManyToOne(optional = false)
    private ProductCategories productCategories;

    @JoinColumn(name = "TaxId", nullable = false, referencedColumnName = "taxId")
    @ManyToOne(optional = false)
    private Taxes taxes;
    
    @JoinColumn(name = "OriginId", nullable = false, referencedColumnName = "originId")
    @ManyToOne(optional = false)
    private Origin origin;


    //RELACIONES ONE TO MANY
    
    @OneToMany(mappedBy = "products",cascade = CascadeType.REMOVE)
    private List<RetailProducts> listRetailsProducts;
    
    @OneToMany(mappedBy = "products", cascade = CascadeType.REMOVE)
    private List<StoreProducts> listStoreProducts;
    
    @OneToMany(mappedBy = "products", cascade = CascadeType.REMOVE)
    private List<PurchaseOrderDetails> listPurchaseOrderDetails;
    
    @OneToMany(mappedBy = "products", cascade = CascadeType.REMOVE)
    private List<RetailProducts> listRetailProducts;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getProductId() != null ? getProductId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.getProductId() == null
                && other.getProductId() != null)
                || (this.getProductId() != null
                && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Products[ id=" + getProductId() + " ]";
    }

    /**
     * @return the productId
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return the prodStructureId
     */
    public int getProdStructureId() {
        return prodStructureId;
    }

    /**
     * @param prodStructureId the prodStructureId to set
     */
    public void setProdStructureId(int prodStructureId) {
        this.prodStructureId = prodStructureId;
    }

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the productBarcode
     */
    public String getProductBarcode() {
        return productBarcode;
    }

    /**
     * @param productBarcode the productBarcode to set
     */
    public void setProductBarcode(String productBarcode) {
        this.productBarcode = productBarcode;
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
     * @return the productionCost
     */
    public double getProductionCost() {
        return productionCost;
    }

    /**
     * @param productionCost the productionCost to set
     */
    public void setProductionCost(double productionCost) {
        this.productionCost = productionCost;
    }

    /**
     * @return the convertFactorPackage
     */
    public double getConvertFactorPackage() {
        return convertFactorPackage;
    }

    /**
     * @param convertFactorPackage the convertFactorPackage to set
     */
    public void setConvertFactorPackage(double convertFactorPackage) {
        this.convertFactorPackage = convertFactorPackage;
    }

    /**
     * @return the packageQuantity
     */
    public double getPackageQuantity() {
        return packageQuantity;
    }

    /**
     * @param packageQuantity the packageQuantity to set
     */
    public void setPackageQuantity(double packageQuantity) {
        this.packageQuantity = packageQuantity;
    }

    /**
     * @return the convertFactor
     */
    public double getConvertFactor() {
        return convertFactor;
    }

    /**
     * @param convertFactor the convertFactor to set
     */
    public void setConvertFactor(double convertFactor) {
        this.convertFactor = convertFactor;
    }

    /**
     * @return the convertFactorUnit
     */
    public int getConvertFactorUnit() {
        return convertFactorUnit;
    }

    /**
     * @param convertFactorUnit the convertFactorUnit to set
     */
    public void setConvertFactorUnit(int convertFactorUnit) {
        this.convertFactorUnit = convertFactorUnit;
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

    public List<RetailProducts> getListRetailsProducts() {
        return listRetailsProducts;
    }

    public void setListRetailsProducts(List<RetailProducts> listRetailsProducts) {
        this.listRetailsProducts = listRetailsProducts;
    }

    /**
     * @return the tradeMarks
     */
    public TradeMarks getTradeMarks() {
        return tradeMarks;
    }

    /**
     * @param tradeMarks the tradeMarks to set
     */
    public void setTradeMarks(TradeMarks tradeMarks) {
        this.tradeMarks = tradeMarks;
    }

    /**
     * @return the productPackages
     */
    public ProductPackages getProductPackages() {
        return productPackages;
    }

    /**
     * @param productPackages the productPackages to set
     */
    public void setProductPackages(ProductPackages productPackages) {
        this.productPackages = productPackages;
    }

    /**
     * @return the finalCustomerSaleUnits
     */
    public FinalCustomerSaleUnits getFinalCustomerSaleUnits() {
        return finalCustomerSaleUnits;
    }

    /**
     * @param finalCustomerSaleUnits the finalCustomerSaleUnits to set
     */
    public void setFinalCustomerSaleUnits(FinalCustomerSaleUnits finalCustomerSaleUnits) {
        this.finalCustomerSaleUnits = finalCustomerSaleUnits;
    }

    /**
     * @return the retailSaleUnits
     */
    public RetailSaleUnits getRetailSaleUnits() {
        return retailSaleUnits;
    }

    /**
     * @param retailSaleUnits the retailSaleUnits to set
     */
    public void setRetailSaleUnits(RetailSaleUnits retailSaleUnits) {
        this.retailSaleUnits = retailSaleUnits;
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
     * @return the listPurchaseOrderDetails
     */
    public List<PurchaseOrderDetails> getListPurchaseOrderDetails() {
        return listPurchaseOrderDetails;
    }

    /**
     * @param listPurchaseOrderDetails the listPurchaseOrderDetails to set
     */
    public void setListPurchaseOrderDetails(List<PurchaseOrderDetails> listPurchaseOrderDetails) {
        this.listPurchaseOrderDetails = listPurchaseOrderDetails;
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

    /**
     * @return the productCategories
     */
    public ProductCategories getProductCategories() {
        return productCategories;
    }

    /**
     * @param productCategories the productCategories to set
     */
    public void setProductCategories(ProductCategories productCategories) {
        this.productCategories = productCategories;
    }

    /**
     * @return the taxes
     */
    public Taxes getTaxes() {
        return taxes;
    }

    /**
     * @param taxes the taxes to set
     */
    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }

    /**
     * @return the origin
     */
    public Origin getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(Origin origin) {
        this.origin = origin;
    }
}
