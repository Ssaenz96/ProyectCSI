/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class Groups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
    
    @Column(name = "GroupCode", length = 2, nullable = false)
    private String groupCode;
    
    @Column(name = "Description", length = 35, nullable = false)
    private String description;
    
    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;
    
    @Column(name = "DownloadFile")
    private byte download;
    
    @Column(name = "UserName", length = 50)
    private String userName;
    
    @Column(name = "Password", length = 64)
    private String password;
    
    @Column(name = "Status")
    private String status;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    @Column(name = "AlternateUserName", length = 50)
    private String alternateUserName;
    
    @Column(name = "AlternatePassword", length = 64)
    private String alternatePassword;
    
    //RELACION CON LAS DEMAS TABLAS
    @OneToMany(mappedBy = "Groups", cascade = CascadeType.REMOVE)
    private List<Retails> listRetails;
    
    @OneToMany(mappedBy = "Groups", cascade = CascadeType.REMOVE)
    private List<Tablas> listTablas;
    
    //@JoinColumn(name = "TablasId", referencedColumnName = "tablasId")
    //@ManyToOne(optional = false)
    //private Tablas tablas;
    
    
    public Groups(){
        userName = null;
        password = null;
    }
    
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupId != null ? groupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the groupId fields are not set
        if (!(object instanceof Groups)) {
            return false;
        }
        Groups other = (Groups) object;
        if ((this.groupId == null && other.groupId != null) || (this.groupId != null && !this.groupId.equals(other.groupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Groups[ id=" + groupId + " ]";
    }

    /**
     * @return the groupCode
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * @param groupCode the groupCode to set
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
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
     * @return the download
     */
    public byte getDownload() {
        return download;
    }

    /**
     * @param download the download to set
     */
    public void setDownload(byte download) {
        this.download = download;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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

    /**
     * @return the alternateUserName
     */
    public String getAlternateUserName() {
        return alternateUserName;
    }

    /**
     * @param alternateUserName the alternateUserName to set
     */
    public void setAlternateUserName(String alternateUserName) {
        this.alternateUserName = alternateUserName;
    }

    /**
     * @return the alternatePassword
     */
    public String getAlternatePassword() {
        return alternatePassword;
    }

    /**
     * @param alternatePassword the alternatePassword to set
     */
    public void setAlternatePassword(String alternatePassword) {
        this.alternatePassword = alternatePassword;
    }

    /**
     * @return the status
     */
    public String isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public List<Tablas> getListTablas() {
        return listTablas;
    }

    public void setListTablas(List<Tablas> listTablas) {
        this.listTablas = listTablas;
    }

    
    
}
