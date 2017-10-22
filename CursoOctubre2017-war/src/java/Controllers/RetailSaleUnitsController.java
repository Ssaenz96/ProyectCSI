/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.RetailSaleUnits;
import Facade.RetailSaleUnitsFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Gerardo Cardenas
 * Clase: RetailSaleUnitsController
 * Version 1.0
 * Fecha: 06/10/2017
 * Copyrigth
 *Version 1.1 Carlos A 10/10/2017
 */
@Named(value = "retailSaleUnitsController")
@SessionScoped
public class RetailSaleUnitsController implements Serializable {
    @EJB
    private RetailSaleUnitsFacade retailSaleUnitsFacade;
    private RetailSaleUnits retailSaleUnits;
    private boolean flagUpdate;
    
    public String prepareInsert(){
        this.setRetailSaleUnits(new RetailSaleUnits());//se inicializa un nuevo objeto de retailSaleUnits
        setFlagUpdate(false);
        return "RetailSaleUnitsForm";
    }
    public String insert(){
        try{
            
            if(findByRetailSUCode(retailSaleUnits.getRetailSUCode()) != null){
                throw new Exception("RetailSUCode already exists");
            }
            
            //Add the "inserted" message----------------------------------------
            String page = retailSaleUnitsFacade.insert(getRetailSaleUnits());
            addMessage("RetailSU Code", "inserted");
            
            return refresh(page);  
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("RetailSaleUnitsForm", msg);
            return "RetailSaleUnitsForm";
        }
    }
    
    public String prepareUpdate(RetailSaleUnits retailSaleUnits){
        setRetailSaleUnits(retailSaleUnits);
        setFlagUpdate(true);//el rendered te dice si se va a mostrar o no dependiendo de su valor booleano (true=mostrar,false=no mostrar)
        return "RetailSaleUnitsForm";
    }
    
    public String update(){      
        try{
            
            if(edit()){
                throw new Exception("RetailSUCode already exists");
            }
            
            //Add the "updated" message-----------------------------------------
            String page = retailSaleUnitsFacade.update(getRetailSaleUnits());
            addMessage("RetailSU Code", "updated");
            
            return refresh(page);  
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("RetailSaleUnitsForm", msg);
            return "RetailSaleUnitsForm";
        }
    }
    
    public String delete(RetailSaleUnits retailSaleUnits){
        try{
            this.setRetailSaleUnits(retailSaleUnits);
            //Add the "deleted" message-----------------------------------------
            String page = retailSaleUnitsFacade.delete(getRetailSaleUnits());
            addMessage("RetailSU Code", "deleted");
            
            return page;
        }catch(Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "RetailSaleUnits no se pudo borrar correctamente", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("RetailSaleUnitsList", msg);//clientId= pantalla donde queremos que muestre el error 
            return "RetailSaleUnitsList";
        }
    }
    
    public String prepareDelete(RetailSaleUnits retailSaleUnits){
        this.setRetailSaleUnits(retailSaleUnits);
        return "RetailSaleUnitsList";
        
    }
    
    public boolean deleteFlagCheck(RetailSaleUnits retailSaleUnits){
        try{
            return this.getRetailSaleUnits().equals(retailSaleUnits);
            
        }catch(Exception e){
            return false;
        }
        
    }
     public String refresh(String direccion){
         this.setRetailSaleUnits(null);
         return direccion;
     }

     public String cancelDelete(){
         return refresh("RetailSaleUnitsList");
     }
     
     public String volver(){
         return cancelDelete();
     }
     
     public String volverHome(){
         return refresh("/index?faces-redirect=true");
         
     }
    
    //--------------------------------------------------------------------------
    
    public List<RetailSaleUnits> findAll(){
        return retailSaleUnitsFacade.findAll();
    }
    
    public RetailSaleUnits findById(Long id){
        return retailSaleUnitsFacade.findById(id);
    }
    
    public RetailSaleUnits findByRetailSUCode(String code){
        return retailSaleUnitsFacade.findByRetailSUCode(code);
    }
    
    public boolean edit(){
        return !retailSaleUnitsFacade.edit(retailSaleUnits, retailSaleUnits.getRetailSUId()).isEmpty();
    }
    
    //----------------Get & Set-------------------------------------------------

    public RetailSaleUnitsFacade getRetailSaleUnitsFacade() {
        return retailSaleUnitsFacade;
    }

    public void setRetailSaleUnitsFacade(RetailSaleUnitsFacade retailSaleUnitsFacade) {
        this.retailSaleUnitsFacade = retailSaleUnitsFacade;
    } 

    /**
     * @return the retailSaleUnits
     */
    public RetailSaleUnits getRetailSaleUnits() {
        return retailSaleUnits;
    }

    /**
     * @param retailSaleUnits the retailSaleUnits to set
     */
    public void setRetailSaleUnits(RetailSaleUnits retailSaleUnits) {
        this.retailSaleUnits = retailSaleUnits;
    }

    /**
     * @return the flagUpdate
     */
    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    /**
     * @param flagUpdate the flagUpdate to set
     */
    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }
    
    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {
        
        String message = String.format("%s: \"%s\" has been %s."
                ,atribute,this.retailSaleUnits.getRetailSUCode(), action.toLowerCase());
        
        FacesContext.getCurrentInstance().addMessage(
                    "RetailSaleUnitsList", new FacesMessage(FacesMessage.SEVERITY_INFO
                            ,message, ""));
    }
}
