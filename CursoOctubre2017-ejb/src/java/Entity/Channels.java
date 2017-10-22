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
public class Channels implements Serializable {

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
    private Long channelId;
    
    @Column(name = "ChannelCode", length = 2, nullable = false)
    private String channelCode;
    
    @Column(name = "Description", length = 35, nullable = false)
    private String description;
    
    @Column(name = "TradeCompanyId")
    private int tradeCompanyId;
    
    @Column(name = "UpdatedDate")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    //RELACION CON LAS DEMAS TABLAS
    @OneToMany(mappedBy = "channels", cascade = CascadeType.REMOVE)
    private List<Retails> listRetails;


    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (channelId != null ? channelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the channelId fields are not set
        if (!(object instanceof Channels)) {
            return false;
        }
        Channels other = (Channels) object;
        if ((this.channelId == null && other.channelId != null) || (this.channelId != null && !this.channelId.equals(other.channelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Channels[ id=" + channelId + " ]";
    }

    /**
     * @return the channelCode
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * @param channelCode the channelCode to set
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
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
     * @return the updateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
