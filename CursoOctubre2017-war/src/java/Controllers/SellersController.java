/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Sellers;
import Facade.SellersFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * SellersController Version 2 Fecha 06/10/2017
 *
 * @author Irvin Omar Hernandez Hernandez
 */
@Named(value = "sellersController")
@SessionScoped
public class SellersController implements Serializable {

    @EJB
    private SellersFacade sellersFacade;
    private Sellers sellers;
    private boolean flagUpdate;

    //--------------------------------------------------------------------------
    public Sellers findById(Long Id) {
        return sellersFacade.findById(Id);
    }

    public List<Sellers> findAll() {
        return sellersFacade.findAll();
    }

    public Sellers findByPayRollNumber(String nomina) {
        return sellersFacade.findByPayRollNumber(nomina);
    }
    
    public boolean edit(){
        return !sellersFacade.edit(sellers, sellers.getSellerId()).isEmpty();
    }

    public String prepareInsert() {
        setSellers(new Sellers());
        setFlagUpdate(false);
        return "SellersForm";
    }

    public String refresh(String direccion) {
        this.setSellers(null);
        return direccion;
    }

    public String insert() {
        try {
            Sellers nomina = findByPayRollNumber(sellers.getPayRollNumber());

            if (nomina != null) {
                throw new Exception("PayRoll Number already exists");
            }
            
            if (!(sellers.getEnableDate() == null || sellers.getDisableDate() == null)) {
                if (!(sellers.getDisableDate().after(sellers.getEnableDate()))) {
                    throw new Exception("Disable Date must be after the enabled date");
                }
            }

            //Add the "inserted" message----------------------------------------
            String x = sellersFacade.insert(sellers);
            addMessage("Seller", "Inserted");

            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("SellersForm", msg);
        }
        return "SellersForm";
    }

    public String prepareUpdate(Sellers sellers) {
        setSellers(sellers);
        setFlagUpdate(true);
        return "SellersForm";
    }

    public String update() {
        try {
            if(edit()){
                throw new Exception("PayRoll Number already exists");
            } 

            if (!(sellers.getEnableDate() == null || sellers.getDisableDate() == null)) {
                if (!(sellers.getDisableDate().after(sellers.getEnableDate()))) {
                    throw new Exception("Disable Date must be after the enabled date");
                }
            }
                                 
            //***Propuesta de regla de negocio: no se puede asignar a una tienda un vendedor
            //con disabledate anterior a la fecha actual
            if(sellers.getDisableDate().before(new Date()) && !sellers.getListStores().isEmpty())
                throw new Exception("This seller has stores asigned. Stores must be assigned to other sellers!");

            //Add the "updated" message-----------------------------------------
            String x = sellersFacade.update(sellers);
            addMessage("Seller", "updated");

            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("SellersForm", msg);
            return "SellersForm";
        }
    }

    public String delete(Sellers s) {
        this.setSellers(s);
        try {
            //Add the "deleted" message-----------------------------------------
            addMessage("Seller", "deleted");

            return sellersFacade.delete(getSellers());
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sellers could not be deleted", "");
            FacesContext.getCurrentInstance().addMessage("SellersList", msg);
            return "SellersList";
        }
    }

    public String prepareDelete(Sellers sellers) {
        this.setSellers(sellers);
        return "SellersList";
    }

    public boolean deleteFlagCheck(Sellers sellers) {
        try {
            return this.getSellers().equals(sellers);
        } catch (Exception e) {
            return false;
        }
    }

    public String cancelDelete() {
        return refresh("SellersList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");
    }

    //-------------Get & Set----------------------------------------------------
    public Sellers getSellers() {
        return sellers;
    }

    public void setSellers(Sellers sellers) {
        this.sellers = sellers;
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }

    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {

        String message = String.format("%s: \"%s\" has been %s.",
                 atribute, this.sellers.getName(), action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "SellersList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                         message, ""));
    }
}
