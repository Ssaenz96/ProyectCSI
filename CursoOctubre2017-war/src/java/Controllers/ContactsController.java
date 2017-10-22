/*
* ContactsController.java
*
* Version 1.0
*
* Fecha 06/10/2017
*
* Jesús de la Cruz

*Edito: Gerardo Cardenas
*Se agrega el el mensaje de java script de eliminar al padre
 */
package Controllers;

import Entity.Contacts;
import Facade.ContactsFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Usuario
 */
@Named(value = "contactsController")
@SessionScoped
public class ContactsController implements Serializable {

    @EJB
    private ContactsFacade contactsFacade;
    private Contacts contacts;
    private boolean flagUpdate;

    public List<Contacts> findAll() {
        return contactsFacade.findAll();
    }

    public Contacts findById(Long id) {
        return contactsFacade.findById(id);
    }

    public String prepareInsert() {
        this.setContacts(new Contacts());
        setFlagUpdate(false);
        return "ContactsForm";
    }

    public String refresh(String direccion) {
        this.setContacts(contacts);
        return direccion;
    }

    public String insert() {
        try {
            //***Propuesta de regla de negocio: al menos dos forma de contacto
            if(trim(contacts.getEmail()) && trim(contacts.getFacebook()) && trim(contacts.getTwitter())
                    && trim(contacts.getCellphone()) && trim(contacts.getSkype())) {
                throw new Exception("We need another way of contact.");
            }
            
            //Add the "inserted" message----------------------------------------
            String x = contactsFacade.insert(getContacts());
            addMessage("Contact Code", "inserted");
            
            return refresh(x);
        } catch (Exception e) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("ContactsForm", msg);
        }
        return "ContactsForm";
    }

    public String prepareUpdate(Contacts contacts) {
        setContacts(contacts);
        setFlagUpdate(true);
        return "ContactsForm";
    }

    private boolean trim(String formaContacto) {
        return formaContacto.replace(" ", "").equals("");
    }
    
    public String update() {
        try {
            //***Propuesta de regla de negocio: al menos dos forma de contacto
            if(trim(contacts.getEmail()) && trim(contacts.getFacebook()) && trim(contacts.getTwitter())
                    && trim(contacts.getCellphone()) && trim(contacts.getSkype())) {
                throw new Exception("We need another way of contact.");
            }
            
            //Add the "updated" message-----------------------------------------
            String x = contactsFacade.update(getContacts());
            addMessage("Contact Code", "updated");
            
            return refresh(x);
        } catch (Exception e) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("ContactsForm", msg);
        }
        return "ContactsForm";
    }

    public String delete(Contacts contacts) {
        try {
            this.setContacts(contacts);
            //Add the "deleted" message-----------------------------------------
            addMessage("Contact Code", "deleted");
            
            return contactsFacade.delete(getContacts());
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contacts no se pudo borrar correctamente", "");
            FacesContext.getCurrentInstance().addMessage("ContactsList", msg);
            return "ContactsList";
        }
    }

    public String prepareDelete(Contacts contacts) {
        this.setContacts(contacts);
        return "ContactsList";
    }

    public boolean deleteFlagCheck(Contacts contacts) {
        try {
            return this.getContacts().equals(contacts);
        } catch (Exception e) {
            return false;
        }
    }

    public String cancelDelete() {
        return refresh("ContactsList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");
    }

    /**
     * @return the contactsFacade
     */
    public ContactsFacade getContactsFacade() {
        return contactsFacade;
    }

    /**
     * @param contactsFacade the contactsFacade to set
     */
    public void setContactsFacade(ContactsFacade contactsFacade) {
        this.contactsFacade = contactsFacade;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }
    
    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {
        
        String message = String.format("%s: \"%s\" has been %s."
                ,atribute,this.contacts.getName(), action.toLowerCase());
        
        FacesContext.getCurrentInstance().addMessage(
                    "ContactsList", new FacesMessage(FacesMessage.SEVERITY_INFO
                            ,message, ""));
    }
}
