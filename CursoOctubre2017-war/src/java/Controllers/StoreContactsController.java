/*
* Nombre de la clase: StoreContactsController
*
* Información de la versión: version 1
*
* Fecha: 06/10/2017
*
* Copyright: Eduardo Alejandro Martínez Melo
 */

 /*
* Nombre de la clase: StoreContactsController
* Información de la versión: V. 3.0 (Reglas de negocio)
* Fecha: 16/10/2017
* Copyright: Jesús Rey de la Cruz Arriaga
 */
 /*
* Nombre de la clase: StoreContactsController
* Información de la versión: V. 4.0 (Error en el lastUpdateBI)
* Fecha: 19/10/2017
* Copyright: Irvin Omar Hernandez Hernandez
 */
package Controllers;

import Entity.StoreContacts;
import Facade.StoreContactsFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "storeContactsController")
@SessionScoped
public class StoreContactsController implements Serializable {

    @EJB
    private StoreContactsFacade storeContactsFacade;
    private StoreContacts storeContacts;
    private boolean flag;

    public List<StoreContacts> findAll() {
        return getStoreContactsFacade().findAll();
    }

    public StoreContacts findById(Long id) {
        return getStoreContactsFacade().findById(id);
    }
    //******Propuesta Regla de Negocio: No debe haber StoreContacts repetidos

    public boolean empalmeStoreContact() {
        return !storeContactsFacade.findByStoreAndContact(storeContacts.getContacts()
                .getContactId())
                .isEmpty();
    }

    public boolean empalmeStoreContactEdit() {
        return !storeContactsFacade.findByStoreAndContactAndID(storeContacts.getContacts()
                .getContactId(), storeContacts.getStoreContacsId())
                .isEmpty();
    }
    //********************************************************************** 

    public String prepareInsert() {
        this.setStoreContacts(new StoreContacts());
        setFlag(false);
        return "StoreContactsForm";
    }

    public String insert() {
        try {
            //******Propuesta Regla de Negocio: No debe haber StoreContacts repetidos
            if (empalmeStoreContact()) {
                throw new Exception("YOU CAN NOT ENTER A REPEATED STORE CONTACT");
            }
            if (!(storeContacts.getLastUpdateBI() == null || storeContacts.getStores().getLastUpdateBI() == null)) {
                if (!storeContacts.getStores().getLastUpdateBI().before(storeContacts.getLastUpdateBI())) {

                    throw new Exception("The Last Update Bi of RetailProducts must be after " + storeContacts.getStores().getLastUpdateBI().toString());
                }
            }

            if (!(getStoreContacts().getLastUpdateBI() == null || getStoreContacts().getContacts().getLastUpdateBI() == null)) {
                if (!getStoreContacts().getContacts().getLastUpdateBI().before(getStoreContacts().getLastUpdateBI())) {

                    throw new Exception("The Last Update Bi of StoresContacts must be after " + getStoreContacts().getContacts().getLastUpdateBI().toString());
                }
            }
            //****************************************************************
            //Add the "inserted" message----------------------------------------
            addMessage("Store Contact", "inserted");
            return refresh(storeContactsFacade.insert(getStoreContacts()));
        } catch (Exception e) {
            FacesMessage msq = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("StoreContactsForm",
                    msq);
            return "StoreContactsForm";
        }

    }

    public String prepareUpdate(StoreContacts storeContacts) {
        setStoreContacts(storeContacts);
        setFlag(true);
        return "StoreContactsForm";
    }

    public String update() {
        try {
            //******Propuesta Regla de Negocio: No debe haber StoreContacts repetidos
            if (empalmeStoreContactEdit()) {
                throw new Exception("YOU CAN NOT ENTER A REPEATED STORE CONTACT");
            }
            //****************************************************************
            if (!(storeContacts.getLastUpdateBI() == null || storeContacts.getStores().getLastUpdateBI() == null)) {
                if (!storeContacts.getStores().getLastUpdateBI().before(storeContacts.getLastUpdateBI())) {

                    throw new Exception("The Last Update Bi of RetailProducts must be after " + storeContacts.getStores().getLastUpdateBI().toString());
                }
            }

            if (!(getStoreContacts().getLastUpdateBI() == null || getStoreContacts().getContacts().getLastUpdateBI() == null)) {
                if (!getStoreContacts().getContacts().getLastUpdateBI().before(getStoreContacts().getLastUpdateBI())) {

                    throw new Exception("The Last Update Bi of StoresContacts must be after " + getStoreContacts().getContacts().getLastUpdateBI().toString());
                }
            }
            //Add the "updated" message-----------------------------------------
            addMessage("Store Contact", "updated");
            return refresh(storeContactsFacade.update(getStoreContacts()));
        } catch (Exception e) {
            FacesMessage msq = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("StoreContactsForm",
                    msq);
            return "StoreContactsForm";
        }
    }

    public String delete() {
        try {
            //Add the "deleted" message-----------------------------------------
            addMessage("Store Contact", "deleted");
            return storeContactsFacade.delete(getStoreContacts());
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Store Contact can't be delete", "");
            FacesContext.getCurrentInstance().addMessage("StoreContactsList",
                    msg);
            return "StoreContactsList";
        }
    }

    public String prepareDelete(StoreContacts storeContacts) {
        this.setStoreContacts(storeContacts);
        return "StoreContactsList";
    }

    public boolean deleteFlagCheck(StoreContacts storeContacts) {
        try {
            return this.getStoreContacts().equals(storeContacts);
        } catch (Exception e) {
            return false;
        }

    }

    public String refresh(String direccion) {
        this.setStoreContacts(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("StoreContactsList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");
    }

    /**
     * @return the storeContactsFacade
     */
    public StoreContactsFacade getStoreContactsFacade() {
        return storeContactsFacade;
    }

    /**
     * @param storeContactsFacade the storeContactsFacade to set
     */
    public void setStoreContactsFacade(StoreContactsFacade storeContactsFacade) {
        this.storeContactsFacade = storeContactsFacade;
    }

    /**
     * @return the storeContacts
     */
    public StoreContacts getStoreContacts() {
        return storeContacts;
    }

    /**
     * @param storeContacts the storeContacts to set
     */
    public void setStoreContacts(StoreContacts storeContacts) {
        this.storeContacts = storeContacts;
    }

    /**
     * @return the flag
     */
    public boolean isFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    //-----Regla de Negocio 6---------------------------------------------------

    private void addMessage(String atribute, String action) {

        String message = String.format("%s: \"%s\" has been %s.",
                 atribute, this.storeContacts.getContacts().getName(), action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "StoreContactsList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                         message, ""));
    }
}
