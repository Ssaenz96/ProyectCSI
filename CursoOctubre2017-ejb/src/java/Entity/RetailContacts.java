/* Fecha 05/10/2017
 * @author Irvin Omar Hernandez Hernandez 
 * Entidad sin Relaciones  
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
 * @author Usuario
 */
@Entity
@Table(schema = "GARCOMEX")
public class RetailContacts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long retailContactId;
    
    @Column(name = "LastUpdateBI")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateBI;
    
    //RELACION CON LAS DEMAS TABLAS
    @JoinColumn(name = "RetailId", nullable = false, referencedColumnName = "retailId")
    @ManyToOne(optional = false)
    private Retails retails;
    
    @JoinColumn(name = "ContactId", nullable = false, referencedColumnName = "contactId")
    @ManyToOne(optional = false)
    private Contacts contacts;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getRetailContactId() != null ? getRetailContactId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RetailContacts)) {
            return false;
        }
        RetailContacts other = (RetailContacts) object;
        if ((this.getRetailContactId() == null && other.getRetailContactId() != null) || (this.getRetailContactId() != null && !this.retailContactId.equals(other.retailContactId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.RetailContacts[ id=" + getRetailContactId() + " ]";
    }

    /**
     * @return the retailContactId
     */
    public Long getRetailContactId() {
        return retailContactId;
    }

    /**
     * @param retailContactId the retailContactId to set
     */
    public void setRetailContactId(Long retailContactId) {
        this.retailContactId = retailContactId;
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
