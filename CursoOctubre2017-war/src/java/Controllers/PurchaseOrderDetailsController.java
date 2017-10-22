/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.PurchaseOrderDetails;
import Facade.PurchaseOrderDetailsFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/*
 * PurchaseOrderDetailsController
 *
 * Version 1.0
 *
 * 12:07 pm 6/10/17
 *
 * Luis. All Rights Reserved.
 * 
 * Version 2
 * 12:30 pm 13/10/2017
 * Sergio Galvan
 */

/*
* Nombre de la clase: PurchaseOrderDetailsController
* Información de la versión: V. 3.0 (Reglas de negocio)
* Fecha: 16/10/2017
* Copyright: Jesús Rey de la Cruz Arriaga
*/
/*
* Información de la versión: V. 4.0 (Verificacion de nulls en reglas de negocio)
* Fecha: 19/10/2017
* Copyright: Luis Garcia
*/
@Named(value = "purchaseOrderDetailsController")
@SessionScoped
public class PurchaseOrderDetailsController implements Serializable {

    @EJB
    private PurchaseOrderDetailsFacade purchaseOrderDetailsFacade;
    private PurchaseOrderDetails purchaseOrderDetails;
    private boolean flagUpdate;
    
            
    public List<PurchaseOrderDetails> findAll(){
       return getPurchaseOrderDetailsFacade().findAll();
   }
    
    public PurchaseOrderDetails findById(Long id){
       return getPurchaseOrderDetailsFacade().findById(id);
   }
    
    //FUNCIONES PARA LOS BOTONES DE LIST BY Thania López
     public String prepareInsert() {
        this.setPurchaseOrderDetails(new PurchaseOrderDetails());
        setFlagUpdate(false);
        return "PurchaseOrderDetailsForm";
    }

    public String prepareUpdate(PurchaseOrderDetails purchaseOrderDetails) {
        setPurchaseOrderDetails(purchaseOrderDetails);
        setFlagUpdate(true);
        return "PurchaseOrderDetailsForm";
    }

    public String insert() {
        try{
            //Regla de negocio: DeliveredUnits <= RequestedUnits
            if(!(purchaseOrderDetails.getDeliveredUnits() == null || purchaseOrderDetails.getRequestedUnits() == null)){
                if(purchaseOrderDetails.getDeliveredUnits() < purchaseOrderDetails.getRequestedUnits()){
                throw new Exception("DeliveredUnits must be less than RequestedUnits");
                }
            }
            
            if(!(getPurchaseOrderDetails().getPurchaseOrders().getLastUpdateBI() == null || getPurchaseOrderDetails().getLastUpdateBI() == null)){
                if(!getPurchaseOrderDetails().getPurchaseOrders().getLastUpdateBI().before(getPurchaseOrderDetails().getLastUpdateBI())){
                    throw new Exception("The Last Update Bi of PurchaseOrderDetails must be after " + getPurchaseOrderDetails().getPurchaseOrders().getLastUpdateBI().toString() );
                } 
            }
            
            if(!(getPurchaseOrderDetails().getProducts().getLastUpdateBI() == null || getPurchaseOrderDetails().getLastUpdateBI() == null)){
                if(!getPurchaseOrderDetails().getProducts().getLastUpdateBI().before(getPurchaseOrderDetails().getLastUpdateBI())){
                    throw new Exception("The Last Update Bi of PurchaseOrderDetails must be after " + getPurchaseOrderDetails().getProducts().getLastUpdateBI().toString() );
                }  
            }
             
            //Add the "inserted" message-----------------------------------------
            String page = purchaseOrderDetailsFacade.insert(purchaseOrderDetails);
            addMessage("Purchase Order Detail", "inserted");
            return refresh(page);
        }catch(Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("PurchaseOrderDetailsForm", msg);
        }
        return "PurchaseOrderDetailsForm";
    }

    public String update() {
        try{
            //Regla de negocio: DeliveredUnits <= RequestedUnits
            if(!(purchaseOrderDetails.getDeliveredUnits() == null || purchaseOrderDetails.getRequestedUnits() == null)){
                if(purchaseOrderDetails.getDeliveredUnits() < purchaseOrderDetails.getRequestedUnits()){
                throw new Exception("DeliveredUnits must be less than RequestedUnits");
                }
            }
            if(!(getPurchaseOrderDetails().getPurchaseOrders().getLastUpdateBI() == null || getPurchaseOrderDetails().getLastUpdateBI() == null)){
                if(!getPurchaseOrderDetails().getPurchaseOrders().getLastUpdateBI().before(getPurchaseOrderDetails().getLastUpdateBI())){
                    throw new Exception("The Last Update Bi of PurchaseOrderDetails must be after " + getPurchaseOrderDetails().getPurchaseOrders().getLastUpdateBI().toString() );
                } 
            }
            
            if(!(getPurchaseOrderDetails().getProducts().getLastUpdateBI() == null || getPurchaseOrderDetails().getLastUpdateBI() == null)){
                if(!getPurchaseOrderDetails().getProducts().getLastUpdateBI().before(getPurchaseOrderDetails().getLastUpdateBI())){
                    throw new Exception("The Last Update Bi of PurchaseOrderDetails must be after " + getPurchaseOrderDetails().getProducts().getLastUpdateBI().toString() );
                }  
            }
            //Add the "updated" message-----------------------------------------
            String page = purchaseOrderDetailsFacade.update(purchaseOrderDetails);
            addMessage("Purchase Order Detail", "updated");
            return refresh(page);
        }catch(Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("PurchaseOrderDetailsForm", msg);
        }
        return "PurchaseOrderDetailsForm";
    }

    public String delete(){
        try{
            //Add the "deleted" message-----------------------------------------
            String page = purchaseOrderDetailsFacade.delete(getPurchaseOrderDetails());
            addMessage("Purchase Order Detail", "deleted");
            return page;
        }catch(Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La informacion no se puede borrar correctamente", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("PurchaseOrderDetailsList", msg);//clientId= pantalla donde queremos que muestre el error 
            return "PurchaseOrderDetailsList";
        }
    }


    public String prepareDelete(PurchaseOrderDetails purchaseOrderDetails) {
        this.setPurchaseOrderDetails(purchaseOrderDetails);
        return "PurchaseOrderDetailsList";
    }

    public boolean deleteFlagCheck(PurchaseOrderDetails purchaseOrderDetails) {
        try {
            return this.getPurchaseOrderDetails().equals(purchaseOrderDetails);
        } catch (Exception e) {
            return false;
        }
    }

    public String refresh(String direccion) {
        this.setPurchaseOrderDetails(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("PurchaseOrderDetailsList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");
    }

    //Set´s & Get´s
    public PurchaseOrderDetailsFacade getPurchaseOrderDetailsFacade() {
        return purchaseOrderDetailsFacade;
    }

    public void setPurchaseOrderDetailsFacade(PurchaseOrderDetailsFacade purchaseOrderDetailsFacade) {
        this.purchaseOrderDetailsFacade = purchaseOrderDetailsFacade;
    }

    public PurchaseOrderDetails getPurchaseOrderDetails() {
        return purchaseOrderDetails;
    }

    public void setPurchaseOrderDetails(PurchaseOrderDetails purchaseOrderDetails) {
        this.purchaseOrderDetails = purchaseOrderDetails;
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }
    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {

        String message = String.format("%s: \"%s\" has been %s.",
                atribute, this.purchaseOrderDetails.getPurchaseOrders().getPONumber(), action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "PurchaseOrderDetailsList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        message, ""));
    }
}
