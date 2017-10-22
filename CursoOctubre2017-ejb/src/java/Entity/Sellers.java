/*
 * Tabla #4
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
 * @author Saul Saenz
 * @date 05-10-2017
 * @version 1.0
 */
@Entity
@Table(schema = "GARCOMEX")
public class Sellers implements Serializable {

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
    private Long sellerId;
    
    @Column(name = "PayRollNumber", length = 10, nullable = false)
    private String payRollNumber;
    
    @Column(name = "Name", length = 35, nullable = false)
    private String name;
    
    @Column(name = "EnableDate")
    @Temporal(TemporalType.DATE)
    private Date enableDate;
    
    @Column(name = "EnabledBy", length = 50)
    private String enableBy;
    
    @Column(name = "LastChangeDate")
    @Temporal(TemporalType.DATE)
    private Date lastChangeDate;
    
    @Column(name = "LastChangeBy", length = 50)
    private String lastChangeBy;
    
    @Column(name = "DisableDate")
    @Temporal(TemporalType.DATE)
    private Date disableDate;
    
    @Column(name = "DisabledBy")
    private String disabledBy;
    
    @Column(name = "SellerIdUp")
    private int sellerIdUp;
    
    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    //RELACIONES CON LAS DEMAS TABLAS
    @JoinColumn(name = "GSaleStructId", nullable = false, referencedColumnName = "GSaleStructId")
    @ManyToOne(optional = false)
    private GSaleStructures GSaleStructures;
    
    @JoinColumn(name = "PositionId", nullable = false, referencedColumnName = "positionId")
    @ManyToOne(optional = false)
    private Positions positions;
    
    @JoinColumn(name = "EmpTypeId", nullable = false, referencedColumnName = "employeeTypeId")
    @ManyToOne(optional = false)
    private EmployeeTypes employeeTypes;
    
    @OneToMany(mappedBy = "sellers", cascade = CascadeType.REMOVE)
    private List<Stores> listStores;
    
    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sellerId != null ? sellerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the sellerId fields are not set
        if (!(object instanceof Sellers)) {
            return false;
        }
        Sellers other = (Sellers) object;
        if ((this.sellerId == null && other.sellerId != null) || (this.sellerId != null && !this.sellerId.equals(other.sellerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Sellers[ id=" + sellerId + " ]";
    }

    /**
     * @return the payRollNumber
     */
    public String getPayRollNumber() {
        return payRollNumber;
    }

    /**
     * @param payRollNumber the payRollNumber to set
     */
    public void setPayRollNumber(String payRollNumber) {
        this.payRollNumber = payRollNumber;
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
     * @return the positions
     */
    public Positions getPositions() {
        return positions;
    }

    /**
     * @param positions the positions to set
     */
    public void setPositions(Positions positions) {
        this.positions = positions;
    }

    /**
     * @return the employeeTypes
     */
    public EmployeeTypes getEmployeeTypes() {
        return employeeTypes;
    }

    /**
     * @param employeeTypes the employeeTypes to set
     */
    public void setEmployeeTypes(EmployeeTypes employeeTypes) {
        this.employeeTypes = employeeTypes;
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
     * @return the enableBy
     */
    public String getEnableBy() {
        return enableBy;
    }

    /**
     * @param enableBy the enableBy to set
     */
    public void setEnableBy(String enableBy) {
        this.enableBy = enableBy;
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
     * @return the lastChangeBy
     */
    public String getLastChangeBy() {
        return lastChangeBy;
    }

    /**
     * @param lastChangeBy the lastChangeBy to set
     */
    public void setLastChangeBy(String lastChangeBy) {
        this.lastChangeBy = lastChangeBy;
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
     * @return the sellerIdUp
     */
    public int getSellerIdUp() {
        return sellerIdUp;
    }

    /**
     * @param sellerIdUp the sellerIdUp to set
     */
    public void setSellerIdUp(int sellerIdUp) {
        this.sellerIdUp = sellerIdUp;
    }

    public GSaleStructures getGSaleStructures() {
        return GSaleStructures;
    }

    /**
     * @return the gSaleStructures
     */
    public void setGSaleStructures(GSaleStructures GSaleStructures) {
        this.GSaleStructures = GSaleStructures;
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
    
}
