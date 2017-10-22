/*
* Nombre de la clase: TaxesController
*
* Información de la versión: version 1
*
* Fecha: 06/10/2017
*
* Copyright: Eduardo Alejandro Martínez Melo
* ------------------------------------------
* Informacion de la version: version 2
*
* Fecha: 10/10/2017
*
* Detalle: Creacion de funciones Insert, Update y Delete
*
* Autor: Sergio Galvan
* ------------------------------------------
* Informacion de la version: version 3
*
* Fecha: 13/10/2017
*
* Detalle: Regla de negocio, taxcode unico
*
* Autor: Luis Garcia
*/

package Controllers;

import Entity.Taxes;
import Facade.TaxesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "taxesController")
@SessionScoped
public class TaxesController implements Serializable {

    @EJB
    private TaxesFacade taxesFacade;
    private Taxes taxes;
    private boolean flagUpdate;

    //--------------------------------------------------------------------------
    public List<Taxes> findAll() {
        return taxesFacade.findAll();
    } 
    
    public Taxes findByTaxCode(String code){
        return taxesFacade.findByTaxCode(code);
    }
    
    public Taxes findById(Long id) {
        return taxesFacade.findById(id);
    }
    
    public boolean edit(){
        return !taxesFacade.edit(taxes, taxes.getTaxId()).isEmpty();
    }
    
    //--------------------------------------------------------------------------
    public String insert(){
        try {
            //checa que TaxCode no lo esten utilizando
            if(findByTaxCode(taxes.getTaxCode())!= null){
                throw new Exception("Taxcode already exists");
            }
            
            //Add the "inserted" message----------------------------------------
            String page = taxesFacade.insert(getTaxes());
            addMessage("Tax Code", "inserted");
            
            return refresh(page);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("TaxesForm", msg);
            return "TaxesForm";
        }
    }
    
    public String update(){
        try {
 
            //checar que StoreCode no exista
            if(edit()){
                throw new Exception("TaxCode already exists");
            }  
            
            //Add the "updated" message-----------------------------------------
            String page = taxesFacade.update(getTaxes());
            addMessage("Tax Code", "updated");
            
            return refresh(page);
            
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR
                    ,e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("TaxesForm", msg);
            return "TaxesForm";
        }
    }
    
    public String delete(Taxes taxes) {
        try {
            this.setTaxes(taxes);
            //Add the "deleted" message-----------------------------------------
            String page = taxesFacade.delete(getTaxes());
            addMessage("Tax Code", "deleted");
            
            return page;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The filed Taxes could not delete correctly", "");
            FacesContext.getCurrentInstance().addMessage("TaxesList", msg);
            return "TaxesList";
        }
    }

    public String prepareInsert(){
        this.setTaxes(new Taxes());
        setFlagUpdate(false);
        return "TaxesForm";
    }
    
    public String prepareUpdate(Taxes taxes){
        setTaxes(taxes);
        setFlagUpdate(true);
        return "TaxesForm";
    }
    
    public String prepareDelete(Taxes taxes) {
        this.setTaxes(taxes);
        return "TaxesList";
    }
    
    public boolean deleteFlagCheck(Taxes taxes) {
        try {
            return this.getTaxes().equals(taxes);
        } catch (Exception e) {
            return false;
        }
    }
    
    public String refresh(String direccion){
        this.setTaxes(null);
        return direccion;
    }
    
    public String cancelDelete(){
        return refresh("TaxesList");
    }
    
    public String volver(){
        return cancelDelete();
    }
    
    public String volverHome(){
        return refresh("/index?faces-redirect=true");
    }
    
    //---------------------get & set--------------------------------------------
    public Taxes getTaxes() {
        return taxes;
    }

    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }
    
    public TaxesFacade getTaxesFacade() {
        return taxesFacade;
    }

    public void setTaxesFacade(TaxesFacade taxesFacade) {
        this.taxesFacade = taxesFacade;
    }
    
    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {
        
        String message = String.format("%s: \"%s\" has been %s."
                ,atribute,this.taxes.getTaxCode(), action.toLowerCase());
        
        FacesContext.getCurrentInstance().addMessage(
                    "TaxesList", new FacesMessage(FacesMessage.SEVERITY_INFO
                            ,message, ""));
    }
}
