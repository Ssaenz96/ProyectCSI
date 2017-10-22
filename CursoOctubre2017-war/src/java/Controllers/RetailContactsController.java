/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.RetailContacts;
import Facade.RetailContactsFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Usuario7
 */

/**
 *RetailContactsController
 * @author Irvin Omar Hernandez Hernandez
 * regla de negocios #3
 * 19/10/2017
 */

@Named(value = "retailContactsController")
@SessionScoped
public class RetailContactsController implements Serializable {

    @EJB
    private RetailContactsFacade retailContactsFacade;
    private RetailContacts retailContacts;
    private boolean flag;

    public List<RetailContacts> findAll() {
        return getRetailContactsFacade().findAll();
    }

    public RetailContacts findById(Long id) {
        return getRetailContactsFacade().findById(id);
    }

    public String prepareInsert() {
        this.setRetailContacts(new RetailContacts());//se inicializa un nuevo objeto de pais
        setFlag(false);
        return "RetailContactsForm";
    }

    public String insert() {
        try {
            if (!(retailContacts.getLastUpdateBI() == null || retailContacts.getRetails().getLastUpdateBi() == null)) {
                if (!getRetailContacts().getRetails().getLastUpdateBi().before(getRetailContacts().getLastUpdateBI())) {
                    throw new Exception("The Last Update Bi of RetailContacts must be after " + getRetailContacts().getRetails().getLastUpdateBi().toString());
                }
            }

            if (!(retailContacts.getLastUpdateBI() == null || retailContacts.getContacts().getLastUpdateBI() == null)) {
                if (!getRetailContacts().getContacts().getLastUpdateBI().before(getRetailContacts().getLastUpdateBI())) {
                    throw new Exception("The Last Update Bi of RetailContacts must be after " + getRetailContacts().getContacts().getLastUpdateBI().toString());
                }
            }

            //Add the "inserted" message----------------------------------------
            String x = retailContactsFacade.insert(getRetailContacts());
            addMessage("RetailContact Code", "inserted");

            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("RetailContactsList", msg);
            return "RetailContactsList";
        }
    }

    public String prepareUpdate(RetailContacts retailContacts) {
        setRetailContacts(retailContacts);
        setFlag(true);//el rendered te dice si se va a mostrar o no dependiendo de su valor booleano (true=mostrar,false=no mostrar)
        return "RetailContactsForm";
    }

    public String update() {
        try {
            if (!(retailContacts.getLastUpdateBI() == null || retailContacts.getRetails().getLastUpdateBi() == null)) {
                if (!getRetailContacts().getRetails().getLastUpdateBi().before(getRetailContacts().getLastUpdateBI())) {
                    throw new Exception("The Last Update Bi of RetailContacts must be after " + getRetailContacts().getRetails().getLastUpdateBi().toString());
                }
            }

            if (!(retailContacts.getLastUpdateBI() == null || retailContacts.getContacts().getLastUpdateBI() == null)) {
                if (!getRetailContacts().getContacts().getLastUpdateBI().before(getRetailContacts().getLastUpdateBI())) {
                    throw new Exception("The Last Update Bi of RetailContacts must be after " + getRetailContacts().getContacts().getLastUpdateBI().toString());
                }
            }
            //Add the "updated" message-----------------------------------------
            String x = retailContactsFacade.update(getRetailContacts());
            addMessage("RetailContact Code", "updated");

            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("RetailContactsForm", msg);
            return "RetailContactsForm";
        }
    }

    public String delete(RetailContacts rC) {
        this.setRetailContacts(rC);
        try {
            //Add the "deleted" message-----------------------------------------
            addMessage("RetailContact Code", "deleted");

            return retailContactsFacade.delete(getRetailContacts());
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Retail Contact can't be delete", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("RetailContactsList", msg);//clientId= pantalla donde queremos que muestre el error 
            return "RetailContactsList";
        }
    }

    public String prepareDelete(RetailContacts retailContacts) {
        this.setRetailContacts(retailContacts);
        return "RetailContactsList";
    }

    public boolean deleteFlagCheck(RetailContacts retailContacts) {
        try {
            return this.getRetailContacts().equals(retailContacts);
        } catch (Exception e) {
            return false;
        }

    }

    public String refresh(String direccion) {
        this.setRetailContacts(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("RetailContactsList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");
    }

    /**
     * @return the retailContactsFacade
     */
    public RetailContactsFacade getRetailContactsFacade() {
        return retailContactsFacade;
    }

    /**
     * @param retailContactsFacade the retailContactsFacade to set
     */
    public void setRetailContactsFacade(RetailContactsFacade retailContactsFacade) {
        this.retailContactsFacade = retailContactsFacade;
    }

    /**
     * @return the retailContacts
     */
    public RetailContacts getRetailContacts() {
        return retailContacts;
    }

    /**
     * @param retailContacts the retailContacts to set
     */
    public void setRetailContacts(RetailContacts retailContacts) {
        this.retailContacts = retailContacts;
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

        String submessage = String.format("contact: %S in the retail: %s",
                this.retailContacts.getRetails().getRetailCode(),
                this.retailContacts.getContacts().getName());

        String message = String.format("%s: \"%s\" has been %s.",
                atribute, submessage, action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "RetailContactsList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        message, ""));
    }
}
