/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.FinalCustomerSaleUnits;
import Facade.FinalCustomerSaleUnitsFacade;
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
@Named(value = "finalCustomerSaleUnitsController")
@SessionScoped
public class FinalCustomerSaleUnitsController implements Serializable {

    @EJB
    private FinalCustomerSaleUnitsFacade finalCustomerSaleUnitsFacade;
    private FinalCustomerSaleUnits finalCustomerSaleUnits;
    private boolean flagUpdate;
    
    //--------------------------------------------------------------------------
    public List<FinalCustomerSaleUnits> findAll(){
        return finalCustomerSaleUnitsFacade.findAll();
    }
    
    public FinalCustomerSaleUnits findById(Long id){
        return finalCustomerSaleUnitsFacade.findById(id);
    }

    //------------------get y set-----------------------------------------------

    public FinalCustomerSaleUnitsFacade getFinalCustomerSaleUnitsFacade() {
        return finalCustomerSaleUnitsFacade;
    }

    public void setFinalCustomerSaleUnitsFacade(FinalCustomerSaleUnitsFacade finalCustomerSaleUnitsFacade) {
        this.finalCustomerSaleUnitsFacade = finalCustomerSaleUnitsFacade;
    }

    public FinalCustomerSaleUnits getFinalCustomerSaleUnits() {
        return finalCustomerSaleUnits;
    }

    public void setFinalCustomerSaleUnits(FinalCustomerSaleUnits finalCustomerSaleUnits) {
        this.finalCustomerSaleUnits = finalCustomerSaleUnits;
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }

    
    
    //--------------------------------------------------------------------------
    public String prepareInsert() {
        this.setFinalCustomerSaleUnits(new FinalCustomerSaleUnits());
        setFlagUpdate(false);
        return "FinalCustomerSaleUnitsForm";
    }

    public String prepareUpdate(FinalCustomerSaleUnits finalCustomerSaleUnits) {
        setFinalCustomerSaleUnits(finalCustomerSaleUnits);
        setFlagUpdate(true);
        return "FinalCustomerSaleUnitsForm";
    }

    public String insert() {
        try{
            
            //Add the "inserted" message----------------------------------------
            String page = finalCustomerSaleUnitsFacade.insert(finalCustomerSaleUnits);
            addMessage("Customer SU Code", "inserted");
            
            return refresh(page);   
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("FinalCustomerSaleUnitsForm", msg);
            return "FinalCustomerSaleUnitsForm";
        }     
    }

    public String update() {
        try{

            //Add the "updated" message-----------------------------------------
            String page = finalCustomerSaleUnitsFacade.update(finalCustomerSaleUnits);
            addMessage("Customer SU Code", "updated");
            
            return refresh(page);  
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("FinalCustomerSaleUnitsForm", msg);
            return "FinalCustomerSaleUnitsForm";
        }  
    }

    //-------------------Funciones para el Delete----------------------------------- 
    public String delete(FinalCustomerSaleUnits finalCustomerSaleUnits){
        try{
        this.setFinalCustomerSaleUnits(finalCustomerSaleUnits);
            //Add the "deleted" message-----------------------------------------
            String page = finalCustomerSaleUnitsFacade.delete(getFinalCustomerSaleUnits());
            addMessage("Customer SU Code", "deleted");
            
            return page;
        }catch(Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La informacion no se puede borrar correctamente", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("FinalCustomerSaleUnitsList", msg);//clientId= pantalla donde queremos que muestre el error 
            return "FinalCustomerSaleUnitsList";
        }
    }


    public String prepareDelete(FinalCustomerSaleUnits finalCustomerSaleUnits) {
        this.setFinalCustomerSaleUnits(finalCustomerSaleUnits);
        return "FinalCustomerSaleUnitsList";
    }

    public boolean deleteFlagCheck(FinalCustomerSaleUnits finalCustomerSaleUnits) {
        try {
            return this.getFinalCustomerSaleUnits().equals(finalCustomerSaleUnits);
        } catch (Exception e) {
            return false;
        }
    }

    public String refresh(String direccion) {
        this.setFinalCustomerSaleUnits(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("FinalCustomerSaleUnitsList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");
    }
    
    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {
        
        String message = String.format("%s: \"%s\" has been %s."
                ,atribute,this.finalCustomerSaleUnits.getCustomerSUCode(), action.toLowerCase());
        
        FacesContext.getCurrentInstance().addMessage(
                    "FinalCustomerSaleUnitsList", new FacesMessage(FacesMessage.SEVERITY_INFO
                            ,message, ""));
    }
}
