/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.GSaleStructures;
import Facade.GSaleStructuresFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * GSaleStructuresController
 * Version 2
 * Fecha 06/10/2017
 * @author Irvin Omar Hernandez Hernandez  
 */

/**
 * GSaleStructuresController
 * Version 2
 * Fecha 10/10/2017
 * @author Irvin Omar Hernandez Hernandez 
 * funciones botones list
 */
@Named(value = "gSaleStructuresController")
@SessionScoped
public class GSaleStructuresController implements Serializable {
    @EJB
    private GSaleStructuresFacade GSaleStructuresFacade;
    private GSaleStructures GSaleStructures;
    private boolean flagUpdate;

    //-----------------get y set------------------------------------------------

    public GSaleStructuresFacade getGSaleStructuresFacade() {
        return GSaleStructuresFacade;
    }

    public void setGSaleStructuresFacade(GSaleStructuresFacade GSaleStructuresFacade) {
        this.GSaleStructuresFacade = GSaleStructuresFacade;
    }

    public GSaleStructures getGSaleStructures() {
        return GSaleStructures;
    }

    public void setGSaleStructures(GSaleStructures GSaleStructures) {
        this.GSaleStructures = GSaleStructures;
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }


    
    
    
    
    //--------------------------------------------------------------------------
    public GSaleStructures findById(Long id){
        return GSaleStructuresFacade.findById(id);
    }
    
    public List<GSaleStructures> findAll(){
        return GSaleStructuresFacade.findAll();
    }
    
    //--------------------------------------------------------------------------
    public String prepareInsert() {
        this.setGSaleStructures(new GSaleStructures());
        setFlagUpdate(false);
        return "GSaleStructuresForm";
    }

    public String prepareUpdate(GSaleStructures gSaleStructures) {
        setGSaleStructures(gSaleStructures);
        setFlagUpdate(true);
        return "GSaleStructuresForm";
    }

    public String insert() {
        try{
            if (!(GSaleStructures.getDisableDate()== null || GSaleStructures.getEnableDate()== null)) {
                if (!(GSaleStructures.getDisableDate().after(GSaleStructures.getEnableDate()))) {
                    throw new Exception("Disable must be after the enabled date");
                }
            }

            //Add the "inserted" message----------------------------------------
            addMessage("GSale Structure Code", "inserted");

            
            return refresh(GSaleStructuresFacade.insert(GSaleStructures));  
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("GSaleStructuresForm", msg);
            return "GSaleStructuresForm";
        }
    }

    public String update() {
        try{    
             if (!(GSaleStructures.getDisableDate()== null || GSaleStructures.getEnableDate()== null)) {
                if (!(GSaleStructures.getDisableDate().after(GSaleStructures.getEnableDate()))) {
                    throw new Exception("Disable must be after the enabled date");
                }
            }
//            if(!(GSaleStructures.getEnableDate().before(GSaleStructures.getDisableDate()))){
//                throw new Exception ("Disable Date must be after the Enabled Date");
//            }
            //Add the "updated" message-----------------------------------------
            addMessage("GSale Structure Code", "updated");
            
            
            return refresh(GSaleStructuresFacade.update(GSaleStructures));  
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("GSaleStructuresForm", msg);
            return "GSaleStructuresForm";
        }        
    }

    //-------------------Funciones para el Delete----------------------------------- 
    public String delete(GSaleStructures gss){
        this.setGSaleStructures(gss);
        try{
            //Add the "deleted" message-----------------------------------------
            addMessage("GSale Structure Code", "deleted");
            
            return GSaleStructuresFacade.delete(GSaleStructures);
        }catch(Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La informacion no se puede borrar correctamente", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("GSaleStructuresList", msg);//clientId= pantalla donde queremos que muestre el error 
            return "GSaleStructuresList";
        }
    }


    public String prepareDelete(GSaleStructures gSaleStructures) {
        this.setGSaleStructures(gSaleStructures);
        return "GSaleStructuresList";
    }

    public boolean deleteFlagCheck(GSaleStructures gSaleStructures) {
        try {
            return this.getGSaleStructures().equals(gSaleStructures);
        } catch (Exception e) {
            return false;
        }
    }

    public String refresh(String direccion) {
        this.setGSaleStructures(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("GSaleStructuresList");
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
                ,atribute,this.GSaleStructures.getGSaleStructCode(), action.toLowerCase());
        
        FacesContext.getCurrentInstance().addMessage(
                    "GSaleStructuresList", new FacesMessage(FacesMessage.SEVERITY_INFO
                            ,message, ""));
    }
}
