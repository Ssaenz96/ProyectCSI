/*
* Nombre de la clase: StoreRanksController
*
* Información de la versión: version 1
*
* Fecha: 06/10/2017
*
* Copyright: Eduardo Alejandro Martínez Melo
 * Editado: Jesús Rey de la Cruz (métodos: prepareInsert, insert,
   prepareUpdate, update, prepareDelete, delete, deleteFlagCheck, refresh,
   cancelDelete, volver y volverHome) 11/10/2017

*Edito: Gerardo Cardenas
*Se agrega el el mensaje de java script de eliminar al padre
19/10/2017
 */
package Controllers;

import Entity.StoreRanks;
import Facade.StoreRanksFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "storeRanksController")
@SessionScoped
public class StoreRanksController implements Serializable {

    @EJB
    private StoreRanksFacade storeRanksFacade;
    private StoreRanks storeRanks;
    private boolean flagUpdate;

    public List<StoreRanks> findAll() {
        return storeRanksFacade.findAll();
    }

    public StoreRanks findById(Long id) {
        return storeRanksFacade.findById(id);
    }
    
    public StoreRanks findByStoreRankCode(String code){
        return storeRanksFacade.findByStoreRankCode(code);
    }
    
    public boolean edit(){
        return !storeRanksFacade.edit(storeRanks, storeRanks.getStoreRankId()).isEmpty();
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }

    public String prepareInsert() {
        this.setStoreRanks(new StoreRanks());
        setFlagUpdate(false);
        return "StoreRanksForm";
    }

    public String prepareUpdate(StoreRanks objStoreRanks) {
        setStoreRanks(objStoreRanks);
        setFlagUpdate(true);
        return "StoreRanksForm";
    }
    
    public String insert() {
        try {
            
             //checar que StoreCode no exista
            StoreRanks code = findByStoreRankCode(storeRanks.getStoreRankCode());
            if (code != null) {
                throw new Exception("StoreRankCode already exists");
            }       
            
            //Add the "inserted" message----------------------------------------
            String x = storeRanksFacade.insert(storeRanks);
            addMessage("StoreRank Code", "inserted");
            
            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("StoreRanksForm", msg);
        }
        
        return "StoreRanksForm";
        
    }
    
    public String update(){
        try {
            
            if(edit()){
                throw new Exception("StoreRankCode already exists");
            }
            
            //Add the "updated" message-----------------------------------------
            String x = storeRanksFacade.update(storeRanks);
            addMessage("StoreRank Code", "updated");
            
            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("StoreRanksForm", msg);
        }
        return "StoreRanksForm";
    }


     //--- Métodos para botones Eliminar---------------------------------------
   public String delete(StoreRanks sr){
       this.setStoreRanks(sr);
        try{
            //Add the "deleted" message-----------------------------------------
            addMessage("StoreRank Code", "deleted");
            
            return storeRanksFacade.delete(storeRanks);
        }catch(Exception E){
//Severity = color de mensaje, summary =  mensaje, detail= no se utiliza mucho
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "This Store Rank has not been deleted correctly", "");
            FacesContext.getCurrentInstance().addMessage("StoreRanksList", msg);
            return "StoreRanksList";      
        }
   }
   
   
   
   public String prepareDelete(StoreRanks objStoreRanks){
       this.storeRanks= objStoreRanks;
       return "StoreRanksList";
   }
   
   public boolean deleteFlagCheck(StoreRanks objStoreRanks){
       try {
           return this.storeRanks.equals(objStoreRanks);
       } catch (Exception e) {
           return false;
       }
   }
   
   public String refresh (String direccion){
       this.storeRanks=null;
       return direccion;
   }
   
   public String cancelDelete(){
       return refresh("StoreRanksList");
   }
   
   public String volver(){
       return cancelDelete();
   }
   
   public String volverHome(){
       return refresh("/index?faces-redirect=true");
   }
    //-------------------------------------------------------------------------
   
   
    public StoreRanks getStoreRanks() {
        return storeRanks;
    }

    public void setStoreRanks(StoreRanks storeRanks) {
        this.storeRanks = storeRanks;
    }

    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {
        
        String message = String.format("%s: \"%s\" has been %s."
                ,atribute,this.storeRanks.getStoreRankCode(), action.toLowerCase());
        
        FacesContext.getCurrentInstance().addMessage(
                    "StoreRanksList", new FacesMessage(FacesMessage.SEVERITY_INFO
                            ,message, ""));
    }
}
