
package Controllers;

import Entity.GSaleStructureLevels;
import Facade.GSaleStructureLevelsFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * GSaleStructureLevelsController
 * Version 2
 * Fecha 06/10/2017
 * @author Irvin Omar Hernandez Hernandez  
 */

/*
* Nombre de la clase: GSaleStructureLevelsController
* Información de la versión: V. 3.0 (Reglas de negocio)
* Fecha: 16/10/2017
* Copyright: Jesús Rey de la Cruz Arriaga
*/

@Named(value = "gSaleStructureLevelsController")
@SessionScoped
public class GSaleStructureLevelsController implements Serializable {

    @EJB
    private GSaleStructureLevelsFacade gSaleStructureLevelsFacade;
    private GSaleStructureLevels GSaleStructureLevels;
    private boolean flagUpdate;
    
    //--------------------------------------------------------------------------
    public GSaleStructureLevels findById(Long Id){
        return gSaleStructureLevelsFacade.findById(Id);
    }
    
    public List<GSaleStructureLevels> findAll(){
        return gSaleStructureLevelsFacade.findAll();
    }
    
    public String prepareInsert(){
        setGSaleStructureLevels(new GSaleStructureLevels());
        setFlagUpdate(false);
        return "GSaleStructureLevelsForm";
    }
    public String refresh(String direccion){
        this.setGSaleStructureLevels(GSaleStructureLevels);
        return direccion;
    }
    public String insert(){
        try {
            //****Propuesta Regla de Negocio: No más de 10 GSaleStructureLevels
            if(gSaleStructureLevelsFacade.findAll().size()>9){
                throw new Exception("LEVELS LIMIT REACHED");
            }
            
            //Add the "inserted" message----------------------------------------
            String x = gSaleStructureLevelsFacade.
                    insert(getGSaleStructureLevels());
            addMessage("GSaleStructureLevel", "inserted");
            return refresh(x);
        } catch (Exception e) {
            FacesMessage msq = new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    e.getMessage(), "");
            FacesContext.getCurrentInstance().
                    addMessage("GSaleStructureLevelsForm", msq);
            return "GSaleStructureLevelsForm";
        }
        
    }
    
    public String prepareUpdate(GSaleStructureLevels gSaleStructureLevels){
        setGSaleStructureLevels(gSaleStructureLevels);
        setFlagUpdate(true);
        return "GSaleStructureLevelsForm";
    }
    public String update(){
        try {
            //Add the "updated" message-----------------------------------------
            String x = gSaleStructureLevelsFacade.
                update(getGSaleStructureLevels());
            addMessage("GSaleStructureLevel", "updated");
            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("ChannelsList", msg);
            return "GSaleStructureLevelsList";
        }
        
    }
    public String delete(GSaleStructureLevels gSaleStructureLevels){
        try {
            this.setGSaleStructureLevels(gSaleStructureLevels);
            //Add the "deleted" message-----------------------------------------
            addMessage("GSaleStructureLevel", "deleted");
            return gSaleStructureLevelsFacade.delete(getGSaleStructureLevels());
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "GSaleStructureLevel could not be deleted", "");
            FacesContext.getCurrentInstance()
                    .addMessage("GSaleStructureLevelsList", msg);
            return "GSaleStructureLevelsList";
        }
    }
    public String prepareDelete(GSaleStructureLevels gSaleStructureLevels){
        this.setGSaleStructureLevels(gSaleStructureLevels);
        return "GSaleStructureLevelsList";
    }
    public boolean deleteFlagCheck(GSaleStructureLevels gSaleStructureLevels){
        try {
            return this.getGSaleStructureLevels().equals(gSaleStructureLevels);
        } catch (Exception e) {
            return false;
        }
    }
    public String cancelDelete(){
        return refresh("GSaleStructureLevelsList");
    }
    public String volver(){
        return cancelDelete();
    }
    public String volverHome(){
        return refresh("/index?faces-redirect=true");
    }

    public GSaleStructureLevelsFacade getgSaleStructureLevelsFacade() {
        return gSaleStructureLevelsFacade;
    }

    public void setgSaleStructureLevelsFacade(GSaleStructureLevelsFacade 
            gSaleStructureLevelsFacade) {
        this.gSaleStructureLevelsFacade = gSaleStructureLevelsFacade;
    }

    public GSaleStructureLevels getGSaleStructureLevels() {
        return GSaleStructureLevels;
    }

    public void setGSaleStructureLevels(GSaleStructureLevels
            GSaleStructureLevels) {
        this.GSaleStructureLevels = GSaleStructureLevels;
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
                ,atribute,this.GSaleStructureLevels.getGSaleStructLevel(), action.toLowerCase());
        
        FacesContext.getCurrentInstance().addMessage(
                    "GSaleStructureLevelsList", new FacesMessage(FacesMessage.SEVERITY_INFO
                            ,message, ""));
    }
}
