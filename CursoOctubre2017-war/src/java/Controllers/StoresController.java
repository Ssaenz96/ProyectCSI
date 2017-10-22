/*
* Nombre de la clase: StoresController
*
* Información de la versión: version 1
*
* Fecha: 06/10/2017
*
* Copyright: Eduardo Alejandro Martínez Melo

*Edito: Gerardo Cardenas
*Se agrega el el mensaje de java script de eliminar al padre
19/10/2017
 */
package Controllers;

import Entity.Stores;
import Facade.StoresFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "storesController")
@SessionScoped
public class StoresController implements Serializable {

    @EJB
    private StoresFacade storesFacade;
    private Stores stores;
    private boolean flagUpdate;
  
    public List<Stores> findAll() {
        return storesFacade.findAll();
    }

    public Stores findById(Long id) {
        return storesFacade.findById(id);
    }
    
    public Stores findByStoreCode(String code){
        return storesFacade.findByStoreCode(code);
    }
    
    public boolean edit(){
        return !storesFacade.edit(stores, stores.getStoreId()).isEmpty();
    }

    //FUNCIONES PARA LOS BOTONES DE LIST BY Thania López
    public String prepareInsert() {
        this.setStores(new Stores());
        setFlagUpdate(false);
        return "StoresForm";
    }

    public String prepareUpdate(Stores stores) {
        setStores(stores);
        setFlagUpdate(true);
        return "StoresForm";
    }

    public String insert() {
        try {
            //Regla de Negocio @author Thania López
            //Outside & Inside deben ser diferentes entre ellos
            //deben de ser mayores de 0
            //variables que almacenaran los datos de outside y inside en int
            int outside = 0;
            int inside = 0;

            outside = Integer.parseInt(stores.getOutsideNumber());
            inside = Integer.parseInt(stores.getInsideNumber());

            if (inside == outside) {
                throw new Exception("The outside number and the interior number are the same");
            }
            
            if(outside == 0){
                throw new Exception("OutsideNumber must be higher than 0");
            }
            
            if(inside == 0){
                throw new Exception("InsideNumber must be higher than 0");
            }
            
            if (!(stores.getEnableDate() == null || stores.getDisableDate() == null)) {
                if (!(stores.getDisableDate().after(stores.getEnableDate()))) {
                    throw new Exception("Disable Date must be after the enabled date");
                }
            }
            
            //checar que StoreCode no exista
            Stores storeCode = findByStoreCode(stores.getStoreCode());
            if (storeCode != null) {
                throw new Exception("StoreCode already exists");
            }       
            
            //***Propuesta de regla de negocio: no se puede asignar a una tienda un vendedor
            //con disabledate anterior a la fecha actual
            if(!(stores.getSellers().getDisableDate()==null)
                    && stores.getSellers().getDisableDate().before(new Date()))
                throw new Exception("The seller asigned is unavailable!");
            
            //Add the "inserted" message----------------------------------------
            addMessage("Store Code", "inserted");
            
            return refresh(storesFacade.insert(stores));
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("StoresForm", msg);
        }
        return "StoresForm";
    }

    public String update() {
        try{
            
            int outside = 0;
            int inside = 0;

            outside = Integer.parseInt(stores.getOutsideNumber());
            inside = Integer.parseInt(stores.getInsideNumber());

            if (inside == outside) {
                throw new Exception("The outside number and the interior number are the same");
            }
            
            if(outside == 0){
                throw new Exception("OutsideNumber must be higher than 0");
            }
            
            if(inside == 0){
                throw new Exception("InsideNumber must be higher than 0");
            }
            if(!(stores.getEnableDate().before(stores.getDisableDate()))){
                throw new Exception ("Disable Date must be after the Enabled Date");
            }
            
            //checar que StoreCode no exista
            if(edit()){
                throw new Exception("StoreCode already exists");
            }    
            
            //***Propuesta de regla de negocio: no se puede asignar a una tienda un vendedor
            //con disabledate anterior a la fecha actual
            if(!(stores.getSellers().getDisableDate()==null)
                    && stores.getSellers().getDisableDate().before(new Date()))
                throw new Exception("The seller asigned to this store is unavailable. Seller disable date must be before the current date.");
            
            //Add the "updated" message-----------------------------------------
            addMessage("Channel Code", "updated");
            
            return refresh(storesFacade.update(stores)); 
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("StoresForm", msg);
            return "StoresForm";
        }
    }

    //-------------------Funciones para el Delete----------------------------------- 
    public String delete(Stores stores) {
        try {
            this.setStores(stores);
            //Add the "deleted" message-----------------------------------------
            addMessage("Store Code", "deleted");
            
            return storesFacade.delete(getStores());
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La informacion no se puede borrar correctamente", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("StoresList", msg);//clientId= pantalla donde queremos que muestre el error 
            return "StoresList";
        }
    }

    public String prepareDelete(Stores stores) {
        this.setStores(stores);
        return "StoresList";
    }

    public boolean deleteFlagCheck(Stores stores) {
        try {
            return this.getStores().equals(stores);
        } catch (Exception e) {
            return false;
        }
    }

    public String refresh(String direccion) {
        this.setStores(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("StoresList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");
    }

    //Getters & Setters
    public StoresFacade getStoresFacade() {
        return storesFacade;
    }

    public void setStoresFacade(StoresFacade storesFacade) {
        this.storesFacade = storesFacade;
    }

    public Stores getStores() {
        return stores;
    }

    public void setStores(Stores stores) {
        this.stores = stores;
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
                ,atribute,this.stores.getStoreCode(), action.toLowerCase());
        
        FacesContext.getCurrentInstance().addMessage(
                    "StoresList", new FacesMessage(FacesMessage.SEVERITY_INFO
                            ,message, ""));
    }
}
