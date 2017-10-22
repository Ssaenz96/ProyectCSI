/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Retails;
import Facade.RetailsFacade;
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
/*******************************************************************************
 * RetailsController Version 2 Fecha 10/10/2017
 * @author Irvin Omar Hernandez Hernandez get, set y funciones List
 */
/*******************************************************************************
Nombre de la clase: RetailsController
* Información de la versión: V. 3 (Error en el lastUpdateBI)
* Fecha: 19/10/2017
* Copyright: Irvin Omar Hernandez Hernandez
 */

@Named(value = "retailsController")
@SessionScoped
public class RetailsController implements Serializable {

    @EJB
    private RetailsFacade retailsFacade;
    private Retails retails;
    private boolean flagUpdate;

    //--------------------------------------------------------------------------
    public List<Retails> findAll() {
        return retailsFacade.findAll();
    }

    public Retails findById(Long id) {
        return retailsFacade.findById(id);
    }

    //----------------------------get y set-------------------------------------
    public RetailsFacade getRetailsFacade() {
        return retailsFacade;
    }

    public void setRetailsFacade(RetailsFacade retailsFacade) {
        this.retailsFacade = retailsFacade;
    }

    public Retails getRetails() {
        return retails;
    }

    public void setRetails(Retails retails) {
        this.retails = retails;
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }

    //--------------------------------------------------------------------------
    public String prepareInsert() {
        this.setRetails(new Retails());
        setFlagUpdate(false);
        return "RetailsForm";
    }

    public String prepareUpdate(Retails retails) {
        setRetails(retails);
        setFlagUpdate(true);
        return "RetailsForm";
    }

    public String insert() {
        try {
            //Regla de negocio: SaleChargePercent >= 5.0%
            if (retails.getSaleChargePercent() < 5) {
                throw new Exception("SaleChargePercent must be 5.0% or higher");
            }
            if (!(retails.getEnableDate() == null || retails.getDisableDate() == null)) {
                if (!(retails.getDisableDate().after(retails.getEnableDate()))) {
                    throw new Exception("Disable Date must be after the enabled date");
                }
            }

            if (!(retails.getLastUpdateBi() == null || retails.getChannels().getLastUpdateBI() == null)) {
                if (!getRetails().getChannels().getLastUpdateBI().before(getRetails().getLastUpdateBi())) {

                    throw new Exception("The Last Update Bi of Retails must be after " + getRetails().getChannels().getLastUpdateBI().toString());
                }
            }

            if (!(retails.getLastUpdateBi() == null || retails.getGroups().getLastUpdateBI() == null)) {
                if (!getRetails().getGroups().getLastUpdateBI().before(getRetails().getLastUpdateBi())) {
                    throw new Exception("The Last Update Bi of Retails must be after " + getRetails().getGroups().getLastUpdateBI().toString());
                }
            }

            if (!(retails.getLastUpdateBi() == null || retails.getCities().getLastUpdateBi() == null)) {
                if (!getRetails().getCities().getLastUpdateBi().before(getRetails().getLastUpdateBi())) {
                    throw new Exception("The Last Update Bi of Retails must be after " + getRetails().getCities().getLastUpdateBi().toString());
                }
            }

            //Add the "inserted" message----------------------------------------
            addMessage("Retails Code", "inserted");

            return refresh(retailsFacade.insert(retails));
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("RetailsForm", msg);
        }
        return "RetailsForm";
    }

    public String update() {
        try {
            //Regla de negocio: SaleChargePercent >= 5.0%
            if (retails.getSaleChargePercent() < 5) {
                throw new Exception("SaleChargePercent must be 5.0% or higher");
            }
            if (!(retails.getEnableDate() == null || retails.getDisableDate() == null)) {
                if (!(retails.getDisableDate().after(retails.getEnableDate()))) {
                    throw new Exception("Disable Date must be after the enabled date");
                }
            }
            if (!(retails.getLastUpdateBi() == null || retails.getChannels().getLastUpdateBI() == null)) {
                if (!getRetails().getChannels().getLastUpdateBI().before(getRetails().getLastUpdateBi())) {

                    throw new Exception("The Last Update Bi of Retails must be after " + getRetails().getChannels().getLastUpdateBI().toString());
                }
            }

            if (!(retails.getLastUpdateBi() == null || retails.getGroups().getLastUpdateBI() == null)) {
                if (!getRetails().getGroups().getLastUpdateBI().before(getRetails().getLastUpdateBi())) {
                    throw new Exception("The Last Update Bi of Retails must be after " + getRetails().getGroups().getLastUpdateBI().toString());
                }
            }

            if (!(retails.getLastUpdateBi() == null || retails.getCities().getLastUpdateBi() == null)) {
                if (!getRetails().getCities().getLastUpdateBi().before(getRetails().getLastUpdateBi())) {
                    throw new Exception("The Last Update Bi of Retails must be after " + getRetails().getCities().getLastUpdateBi().toString());
                }
            }

            //Add the "updated" message-----------------------------------------
            addMessage("Retails Code", "updated");

            return refresh(retailsFacade.update(retails));
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("RetailsForm", msg);
        }
        return "RetailsForm";
    }

    //-------------------Funciones para el Delete----------------------------------- 
    public String delete(Retails retails) {
        try {
            this.setRetails(retails);
            //Add the "deleted" message-----------------------------------------
            addMessage("Retails Code", "deleted");

            return retailsFacade.delete(getRetails());
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La informacion no se puede borrar correctamente", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("RetailsList", msg);//clientId= pantalla donde queremos que muestre el error 
            return "RetailsList";
        }
    }

    public String prepareDelete(Retails retails) {
        this.setRetails(retails);
        return "RetailsList";
    }

    public boolean deleteFlagCheck(Retails retails) {
        try {
            return this.getRetails().equals(retails);
        } catch (Exception e) {
            return false;
        }
    }

    public String refresh(String direccion) {
        this.setRetails(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("RetailsList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");
    }

    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {

        String message = String.format("%s: \"%s\" has been %s.",
                atribute, this.retails.getRetailCode(), action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "RetailsList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        message, ""));
    }

}
