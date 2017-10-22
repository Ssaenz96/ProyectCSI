/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Facade.RetailProductsFacade;
import Entity.RetailProducts;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * RetailProducts Version 2 Fecha 06/10/2017
 *
 * @author Thania López
 */
/**
 * RetailProducts Version 2 Fecha 13/10/2017
 *
 * @author Irvin Omar Hernandez Hernandez
 */
/**
 * RetailProducts Version 4 y 5 (correccion error en update insert) Fecha 16/10/2017
 *
 * @author Luis Garcia
 */
/**
 * RetailProducts Version 2 Fecha 16/10/2017 corregido error edit
 *
 * @author Irvin Omar Hernandez Hernandez
 */
@Named(value = "RetailProductsController")
@SessionScoped
public class RetailProductsController implements Serializable {

    @EJB
    private RetailProductsFacade retailProductsFacade;
    private RetailProducts retailProducts;
    private boolean flagUpdate;
    private RetailProducts code;

    public boolean edit() {
        return !retailProductsFacade.edit(retailProducts, retailProducts.getRetailProductId()).isEmpty();
    }

    public RetailProducts findById(Long id) {
        return getRetailProductsFacade().findById(id);
    }

    public List<RetailProducts> findAll() {
        return getRetailProductsFacade().findAll();
    }

    //FUNCIONES PARA LOS BOTONES DE LIST BY Thania López
    public String prepareInsert() {
        this.setRetailProducts(new RetailProducts());
        setFlagUpdate(false);
        return "RetailProductsForm";
    }

    public String prepareUpdate(RetailProducts retailProducts) {
        setRetailProducts(retailProducts);
        setFlagUpdate(true);
        return "RetailProductsForm";
    }

    public String insert() {
        try {
            if (!(retailProducts.getSalePriceChangeDate() == null || retailProducts.getEnableDate() == null)) {
                if (!(retailProducts.getSalePriceChangeDate().after(retailProducts.getEnableDate()))) {
                    throw new Exception("Sale price change must be after the enabled date");
                }
            }
            
            if(!(retailProducts.getStartPrice() == null)){
                if (!(retailProducts.getStartPrice() > 0)) {
                throw new Exception("Start Price must have a value higher than 0");
                }  
            }

            if (!(retailProducts.getRetailSalePrice() > 0)) {
                throw new Exception("Retail sale price must have a value higher than 0");
            }
            if(!(retailProducts.getEnableDate() == null || retailProducts.getDisableDate() == null)){
                if (!(retailProducts.getEnableDate().before(retailProducts.getDisableDate()))) {
                throw new Exception("Disable Date must be after the Enabled Date");
                }
            }
            
            if(!(retailProducts.getLastUpdateBI() == null || retailProducts.getPurchaseOrders().getLastUpdateBI() == null)){
                if(!getRetailProducts().getPurchaseOrders().getLastUpdateBI().before(getRetailProducts().getLastUpdateBI())){
                
                    throw new Exception("The Last Update Bi of RetailProducts must be after " + getRetailProducts().getPurchaseOrders().getLastUpdateBI().toString() );
                }
            }
           
            if(!(retailProducts.getLastUpdateBI() == null || retailProducts.getRetails().getLastUpdateBi() == null)){
                if(!getRetailProducts().getRetails().getLastUpdateBi().before(getRetailProducts().getLastUpdateBI())){                
                    throw new Exception("The Last Update Bi of RetailProducts must be after " + getRetailProducts().getRetails().getLastUpdateBi().toString() );
                }
            }

            
            if(!(retailProducts.getLastUpdateBI() == null || retailProducts.getProducts().getLastUpdateBI() == null)){
                if(!getRetailProducts().getProducts().getLastUpdateBI().before(getRetailProducts().getLastUpdateBI())){
                    throw new Exception("The Last Update Bi of RetailProducts must be after " + getRetailProducts().getProducts().getLastUpdateBI().toString() );
                }
            }
            
            //IOHH
            //validar que no exista el mismo SKUCode
            //el SKUCode no se debe repertir 
            code = retailProductsFacade.findByCode(retailProducts);

            if (!(code == null)) {
                throw new Exception("SKUCode already exists");
            }
            
            if(!(retailProducts.getStartPrice() == null)){
                if (!(retailProducts.getRetailSalePrice() <= retailProducts.getStartPrice())) {
                    throw new Exception("StartPrice must be greater than or equal to RetailSalePrice");
                }
            }
            
            //Add the "inserted" message----------------------------------------
            String x = retailProductsFacade.insert(retailProducts);
            addMessage("RetailProduct Code", "inserted");
            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("RetailProductsForm", msg);
            return "RetailProductsForm";
        }
    }

    public String update() {
        try {
            if (!(retailProducts.getSalePriceChangeDate() == null || retailProducts.getEnableDate() == null)) {
                if (!(retailProducts.getSalePriceChangeDate().after(retailProducts.getEnableDate()))) {
                    throw new Exception("Sale price change must be after the enabled date");
                }
            }
            if(!(retailProducts.getStartPrice() == null)){
                if (!(retailProducts.getStartPrice() > 0)) {
                throw new Exception("Start Price must have a value higher than 0");
                }  
            }

            if (!(retailProducts.getRetailSalePrice() > 0)) {
                throw new Exception("Retail sale price must have a value higher than 0");
            }
            if(!(retailProducts.getEnableDate() == null || retailProducts.getDisableDate() == null)){
                if (!(retailProducts.getEnableDate().before(retailProducts.getDisableDate()))) {
                throw new Exception("Disable Date must be after the Enabled Date");
                }
            }
            if(!(retailProducts.getLastUpdateBI() == null || retailProducts.getPurchaseOrders().getLastUpdateBI() == null)){
                if(!getRetailProducts().getPurchaseOrders().getLastUpdateBI().before(getRetailProducts().getLastUpdateBI())){
                
                    throw new Exception("The Last Update Bi of RetailProducts must be after " + getRetailProducts().getPurchaseOrders().getLastUpdateBI().toString() );
                }
            }
            
            if(!(retailProducts.getLastUpdateBI() == null || retailProducts.getRetails().getLastUpdateBi() == null)){
                if(!getRetailProducts().getRetails().getLastUpdateBi().before(getRetailProducts().getLastUpdateBI())){                
                    throw new Exception("The Last Update Bi of RetailProducts must be after " + getRetailProducts().getRetails().getLastUpdateBi().toString() );
                }
            }

            
            if(!(retailProducts.getLastUpdateBI() == null || retailProducts.getProducts().getLastUpdateBI() == null)){
                if(!getRetailProducts().getProducts().getLastUpdateBI().before(getRetailProducts().getLastUpdateBI())){
                    throw new Exception("The Last Update Bi of RetailProducts must be after " + getRetailProducts().getProducts().getLastUpdateBI().toString() );
                }
            }
            //IOHH
            //validar que no exista el mismo SKUCode
            //el SKUCode no se debe repertir 

            if (edit()) {
                throw new Exception("SKUCode already exists");
            }

            if(!(retailProducts.getStartPrice() == null)){
                if (!(retailProducts.getRetailSalePrice() <= retailProducts.getStartPrice())) {
                    throw new Exception("StartPrice must be greater than or equal to RetailSalePrice");
                }
            }

            //Add the "updated" message-----------------------------------------
            String x = retailProductsFacade.update(retailProducts);
            addMessage("RetailProduct Code", "updated");
            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("RetailProductsForm", msg);
            return "RetailProductsForm";
        }
    }

    //-------------------Funciones para el Delete----------------------------------- 
    public String delete() {
        try {
            //Add the "deleted" message-----------------------------------------
            String x = retailProductsFacade.delete(getRetailProducts());
            addMessage("RetailProduct", "deleted");
            return x;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La informacion no se puede borrar correctamente", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("RetailProductsList", msg);//clientId= pantalla donde queremos que muestre el error 
            setFlagUpdate(false);
            return "RetailProductsList";
        }
    }

    public String prepareDelete(RetailProducts retailProducts) {
        this.setRetailProducts(retailProducts);
        return "RetailProductsList";
    }

    public boolean deleteFlagCheck(RetailProducts retailProducts) {
        try {
            return this.getRetailProducts().equals(retailProducts);
        } catch (Exception e) {
            return false;
        }
    }

    public String refresh(String direccion) {
        this.setRetailProducts(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("RetailProductsList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");
    }

    //Getters y Setters
    public RetailProductsFacade getRetailProductsFacade() {
        return retailProductsFacade;
    }

    public void setRetailProductsFacade(RetailProductsFacade retailProductsFacade) {
        this.retailProductsFacade = retailProductsFacade;
    }

    public RetailProducts getRetailProducts() {
        return retailProducts;
    }

    public void setRetailProducts(RetailProducts retailProducts) {
        this.retailProducts = retailProducts;
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }

    public RetailProducts getCode() {
        return code;
    }

    public void setCode(RetailProducts code) {
        this.code = code;
    }

    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {

        String submessage = String.format("product: %S in the retail: %s",
                 this.retailProducts.getProducts().getProductCode(),
                 this.retailProducts.getRetails().getRetailCode());

        String message = String.format("%s: \"%s\" has been %s.",
                 atribute, submessage, action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "RetailProductsList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                         message, ""));
    }
}
