/*
* EmployeeTypesControllerController.java
*
* Version 1.0
*
* Fecha 06/10/2017
*
* Jesús de la Cruz
* Editado: Jesús(métodos: prepareInsert, insert,
  prepareUpdate, update, prepareDelete, delete, deleteFlagCheck, refresh,
  cancelDelete, volver y volverHome) 11/10/2017
 */
package Controllers;

import Entity.EmployeeTypes;
import Facade.EmployeeTypesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


@Named(value = "employeeTypesController")
@SessionScoped
public class EmployeeTypesController implements Serializable {

    @EJB
    private EmployeeTypesFacade employeeTypesFacade;
    private EmployeeTypes employeeTypes;
    private boolean flagUpdate;
    
    public List<EmployeeTypes> findAll(){
        return employeeTypesFacade.findAll();
    }
    
    public EmployeeTypes findById(Long id){
        return employeeTypesFacade.findById(id);
    }

  public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }

    public String prepareInsert() {
        this.setEmployeeTypes(new EmployeeTypes());
        setFlagUpdate(false);
        return "EmployeeTypesForm";
    }

    public String prepareUpdate(EmployeeTypes objEmployeeTypes) {
        setEmployeeTypes(objEmployeeTypes);
        setFlagUpdate(true);
        return "EmployeeTypesForm";
    }
    
    public String insert() {
        try{
            
            //Add the "inserted" message-----------------------------------------
            String x = employeeTypesFacade.insert(employeeTypes);
            addMessage("Employee type Code", "inserted");
            
            return refresh(x); 
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("EmployeeTypesList", msg);
            return "EmployeeTypesList";
        }
    }
    
    public String update(){
        try{
            
            //Add the "updated" message-----------------------------------------
            String x = employeeTypesFacade.update(employeeTypes);
            addMessage("Employee type Code", "updated");
            
            return refresh(x);  
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("EmployeeTypesList", msg);
            return "EmployeeTypesList";
        }
    }
    
    //--- Métodos para botones Eliminar---------------------------------------
   public String delete(EmployeeTypes employeeTypes){
        try{
            this.setEmployeeTypes(employeeTypes);
            //Add the "deleted" message-----------------------------------------
            addMessage("Employee type Code", "deleted");
            
            return employeeTypesFacade.delete(employeeTypes);
        }catch(Exception E){
//Severity = color de mensaje, summary =  mensaje, detail= no se utiliza mucho
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "This Employee Type has not been deleted correctly", "");
            FacesContext.getCurrentInstance().addMessage("EmployeeTypesList", msg);
            return "EmployeeTypesList";      
        }
   }
   
   public String prepareDelete(EmployeeTypes objEmployeeTypes){
       this.employeeTypes= objEmployeeTypes;
       return "EmployeeTypesList";
   }
   
   public boolean deleteFlagCheck(EmployeeTypes objEmployeeTypes){
       try {
           return this.employeeTypes.equals(objEmployeeTypes);
       } catch (Exception e) {
           
           return false;
       }
   }
   
   public String refresh (String direccion){
       this.employeeTypes=null;
       return direccion;
   }
   
   public String cancelDelete(){
       return refresh("EmployeeTypesList");
   }
   
   public String volver(){
       return cancelDelete();
   }
   
   public String volverHome(){
       return refresh("/index?faces-redirect=true");
   }
    //-------------------------------------------------------------------------

    public EmployeeTypesFacade getEmployeeTypesFacade() {
        return employeeTypesFacade;
    }

   
    public void setEmployeeTypesFacade(EmployeeTypesFacade employeeTypesFacade)
    {
        this.employeeTypesFacade = employeeTypesFacade;
    }

    public EmployeeTypes getEmployeeTypes() {
        return employeeTypes;
    }

    public void setEmployeeTypes(EmployeeTypes employeeTypes) {
        this.employeeTypes = employeeTypes;
    }

    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {
        
        String message = String.format("%s: \"%s\" has been %s."
                ,atribute,this.employeeTypes.getEmpTypeCode(), action.toLowerCase());
        
        FacesContext.getCurrentInstance().addMessage(
                    "EmployeeTypesList", new FacesMessage(FacesMessage.SEVERITY_INFO
                            ,message, ""));
    }
}
