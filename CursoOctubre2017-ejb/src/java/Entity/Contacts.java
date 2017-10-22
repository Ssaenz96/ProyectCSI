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
public class Contacts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;
    
    @Column(name = "Name", length = 40, nullable = false)
    private String name;
    
    @Column(name = "Position", length = 35, nullable = false)
    private String position;
    
    @Column(name = "PhoneNumber", length = 15, nullable = false)
    private String phoneNumber;
    
    @Column(name = "Extension", length = 5)
    private String extension;
    
    @Column(name = "Cellphone", length = 15)
    private String cellphone;
    
    @Column(name = "Email", length = 40)
    private String email;
    
    @Column(name = "Twitter", length = 20)
    private String twitter;
    
    @Column(name = "Facebook", length = 20)
    private String facebook;
    
    @Column(name = "Skype", length = 15)
    private String skype;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    //RELACION CON LAS DEMAS TABLAS
    @OneToMany(mappedBy = "contacts", cascade = CascadeType.REMOVE)
    private List<RetailContacts> listRetailContacts;
    
    @OneToMany(mappedBy = "contacts", cascade = CascadeType.REMOVE)
    private List<StoreContacts> listStoreContacts;
    
    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactId != null ? contactId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the contactId fields are not set
        if (!(object instanceof Contacts)) {
            return false;
        }
        Contacts other = (Contacts) object;
        if ((this.contactId == null && other.contactId != null) || (this.contactId != null && !this.contactId.equals(other.contactId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Contacts[ id=" + contactId + " ]";
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
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * @param extension the extension to set
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * @return the cellphone
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * @param cellphone the cellphone to set
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the twitter
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     * @param twitter the twitter to set
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    /**
     * @return the facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     * @param facebook the facebook to set
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     * @return the skype
     */
    public String getSkype() {
        return skype;
    }

    /**
     * @param skype the skype to set
     */
    public void setSkype(String skype) {
        this.skype = skype;
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
    
}
