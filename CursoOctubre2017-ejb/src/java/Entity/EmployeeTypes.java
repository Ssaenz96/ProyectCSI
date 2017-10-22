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
public class EmployeeTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeTypeId;
        
    @Column(name = "EmpTypeCode", length = 2, nullable = false)
    private String empTypeCode;
    
    @Column(name = "Description", length = 35, nullable = false)
    private String description;
    
    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    //RELACIONES CON LAS DEMAS TABLAS
    @OneToMany(mappedBy = "employeeTypes", cascade = CascadeType.REMOVE)
    private List<Sellers> listSellers;

    public Long getEmployeeTypeId() {
        return employeeTypeId;
    }

    public void setEmployeeTypeId(Long employeeTypeId) {
        this.employeeTypeId = employeeTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeTypeId != null ? employeeTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the employeeTypeId fields are not set
        if (!(object instanceof EmployeeTypes)) {
            return false;
        }
        EmployeeTypes other = (EmployeeTypes) object;
        if ((this.employeeTypeId == null && other.employeeTypeId != null) || (this.employeeTypeId != null && !this.employeeTypeId.equals(other.employeeTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.EmployeeTypes[ id=" + employeeTypeId + " ]";
    }

    /**
     * @return the empTypeCode
     */
    public String getEmpTypeCode() {
        return empTypeCode;
    }

    /**
     * @param empTypeCode the empTypeCode to set
     */
    public void setEmpTypeCode(String empTypeCode) {
        this.empTypeCode = empTypeCode;
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

    /**
     * @return the listSellers
     */
    public List<Sellers> getListSellers() {
        return listSellers;
    }

    /**
     * @param listSellers the listSellers to set
     */
    public void setListSellers(List<Sellers> listSellers) {
        this.listSellers = listSellers;
    }
    
}
