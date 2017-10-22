/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio Galvan
 * Clase: StoreContacts
 * Verion 2.0
 * Fecha: 05/10/2017
 * Copyrigth
 * 
 */
@Entity
@Table(schema = "GARCOMEX")
public class StoreContacts implements Serializable {

    /**
     * @return the stores
     */
    public Stores getStores() {
        return stores;
    }

    /**
     * @param stores the stores to set
     */
    public void setStores(Stores stores) {
        this.stores = stores;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeContacsId;
    
    
    //RELACIONES
    
    @JoinColumn(name = "StoreId", nullable = false, referencedColumnName = "storeId")
    @ManyToOne(optional = false)
    private Stores stores;
    
    @JoinColumn(name = "ContactId", nullable = false, referencedColumnName = "contactId")
    @ManyToOne(optional = false)
    private Contacts contacts;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    public Long getStoreContacsId() {
        return storeContacsId;
    }

    public void setStoreContacsId(Long storeContacsId) {
        this.storeContacsId = storeContacsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeContacsId != null ? storeContacsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the storeContacsId fields are not set
        if (!(object instanceof StoreContacts)) {
            return false;
        }
        StoreContacts other = (StoreContacts) object;
        if ((this.storeContacsId == null && other.storeContacsId != null) || (this.storeContacsId != null && !this.storeContacsId.equals(other.storeContacsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.StoreContacts[ id=" + storeContacsId + " ]";
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
     * @return the contacts
     */
    public Contacts getContacts() {
        return contacts;
    }

    /**
     * @param contacts the contacts to set
     */
    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }
    
}
