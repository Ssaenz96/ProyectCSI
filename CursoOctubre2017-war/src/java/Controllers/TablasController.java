/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Tablas;
import Facade.TablasFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Usuario6
 */
@Named(value = "tablasController")
@SessionScoped
public class TablasController implements Serializable {

    @EJB
    private TablasFacade tablasFacade;
    private final String pageList = "TablasList";
    private final String pageForm = "TablasForm";
    private Tablas tablas = new Tablas();
    private boolean flagUpdate;
    
    public String prepareInsert() {
        this.setTablas(new Tablas());
        this.setFlagUpdate(false);
        return pageForm;
    }
    
    public String prepareUpdate(Tablas tablas) {
        this.setTablas(tablas);
        this.setFlagUpdate(true);
        return pageForm;
    }
    
    public String prepareDelete(Tablas tablas) {
        this.setTablas(tablas);
        return pageList;
    }
    //--------------------------------------------------------
    
    public String insert() {
        try{
            //Add the "inserted" message----------------------------------------
            String x = tablasFacade.insert(tablas);
            addMessage("Table", "inserted");
            return refresh(x);   
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("TablasForm", msg);
            return "TablasForm";
        }
   }
    
    public String update() {
        try{
            //Add the "updated" message-----------------------------------------
            String x = tablasFacade.update(tablas);
            addMessage("Table", "updated");
            return refresh(x);   
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("TablasForm", msg);
            return "TablasForm";
        }
   }
    
    public String delete(Tablas tablas) {
       try {
           this.setTablas(tablas);
           //Add the "deleted" message------------------------------------------
            String x = tablasFacade.delete(getTablas());
            addMessage("Table", "deleted");
            return x;
       } catch (Exception e) {
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "New record has not been deleted correctly", "");
           FacesContext.getCurrentInstance().addMessage(pageList, msg);
           return pageList;
       }
   }
    
    public boolean deleteFlagCheck(Tablas tablas) {
       try {
           return this.getTablas().equals(tablas);
       } catch (Exception e) {
           return false;
       }
   }
   
   public String refresh(String direccion) {
      this.setTablas(null);
      return direccion;
   }
   
   public String cancelDelete() {
       return refresh(pageList);
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
                ,atribute,this.tablas.getTablasId(), action.toLowerCase());
        
        FacesContext.getCurrentInstance().addMessage(
                    "TablasList", new FacesMessage(FacesMessage.SEVERITY_INFO
                            ,message, ""));
    }
   

    public TablasFacade getTablasFacade() {
        return tablasFacade;
    }

    public void setTablasFacade(TablasFacade tablasFacade) {
        this.tablasFacade = tablasFacade;
    }

    public Tablas getTablas() {
        return tablas;
    }

    public void setTablas(Tablas tablas) {
        this.tablas = tablas;
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }
    
    
    
}
