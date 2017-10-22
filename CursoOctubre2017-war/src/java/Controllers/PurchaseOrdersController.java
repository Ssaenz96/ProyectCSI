/**
 * @author Luis Amaury Urbina Molina
 * Clase: PurchaseOrderControllers
 * Version 1.0
 * Fecha: 06/10/2017
 * Copyrigth
 */
/**
 * @author Luis Gerardo García
 * Clase: PurchaseOrderControllers
 * Version 2.0 (Se agregaron los metodos de add, delete, edit, etc.)
 * Fecha: 11/10/2017
 * Copyrigth
 * *
 *  * @author Gerardo Cardenas
 * Clase: PurchaseOrderControllers
 * Version 3.0
 * Fecha: 11/10/2017
 * Copyrigth
 * CANCEL DATE NO PUEDE SER MENOR A LA PODATE
 */

/**
 *PurchaseOrdersController
 * @author Irvin Omar Hernandez Hernandez
 * regla de negocios #3
 * 19/10/2017
 */
package Controllers;

import Entity.PurchaseOrders;
import Facade.PurchaseOrdersFacade;
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
@Named(value = "purchaseOrdersController")
@SessionScoped
public class PurchaseOrdersController implements Serializable {

    @EJB
    private PurchaseOrdersFacade purchaseOrdersFacade;
    private PurchaseOrders purchaseOrders;
    private boolean flagUpdate;

    public List<PurchaseOrders> findAll() {
        return purchaseOrdersFacade.findAll();

    }

    public PurchaseOrders findById(Long id) {
        return purchaseOrdersFacade.findById(id);

    }

    public String prepareInsert() {
        this.setPurchaseOrders(new PurchaseOrders());
        setFlagUpdate(false);
        return "PurchaseOrdersForm";
    }

    public String insert() {
        try {
            if(!(purchaseOrders.getPODate() == null || purchaseOrders.getCancelDate() == null)){
                if (!(purchaseOrders.getPODate().before(purchaseOrders.getCancelDate()))) {
                throw new Exception("CANCEL DATE can not be less than PODATE ");
                } 
            }
            
            if(!(getPurchaseOrders().getStores().getLastUpdateBI() == null || getPurchaseOrders().getLastUpdateBI() == null)){
                if (!getPurchaseOrders().getStores().getLastUpdateBI().before(getPurchaseOrders().getLastUpdateBI())) {
                throw new Exception("The Last Update Bi of PurchaseOrders must be after " + getPurchaseOrders().getStores().getLastUpdateBI().toString());
                }
            }
            
            if(!(getPurchaseOrders().getPurchaseOrderStatus().getLastUpdateBI() == null || getPurchaseOrders().getLastUpdateBI() == null)){
                if (!getPurchaseOrders().getPurchaseOrderStatus().getLastUpdateBI().before(getPurchaseOrders().getLastUpdateBI())) {
                throw new Exception("The Last Update Bi of PurchaseOrders must be after " + getPurchaseOrders().getPurchaseOrderStatus().getLastUpdateBI().toString());
                }  
            }
            
            //Add the "inserted" message---------------------------------------
            String x = purchaseOrdersFacade.insert(purchaseOrders);
            addMessage("PurchaseOrder", "inserted");
            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(), "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("PurchaseOrdersForm", msg);//clientId= pantalla donde queremos que muestre el error 
            return "PurchaseOrdersForm";
        }
    }

    public String prepareUpdate(PurchaseOrders purchaseOrders) {
        this.setPurchaseOrders(purchaseOrders);
        setFlagUpdate(true);//el rendered te dice si se va a mostrar o no dependiendo de su valor booleano (true=mostrar,false=no mostrar)
        return "PurchaseOrdersForm";
    }

    public String update() {
        try {
            if(!(purchaseOrders.getPODate() == null || purchaseOrders.getCancelDate() == null)){
                if (!(purchaseOrders.getPODate().before(purchaseOrders.getCancelDate()))) {
                throw new Exception("CANCEL DATE can not be less than PODATE ");
                } 
            }

            if(!(getPurchaseOrders().getStores().getLastUpdateBI() == null || getPurchaseOrders().getLastUpdateBI() == null)){
                if (!getPurchaseOrders().getStores().getLastUpdateBI().before(getPurchaseOrders().getLastUpdateBI())) {
                throw new Exception("The Last Update Bi of PurchaseOrders must be after " + getPurchaseOrders().getStores().getLastUpdateBI().toString());
                }
            }
            
            if(!(getPurchaseOrders().getPurchaseOrderStatus().getLastUpdateBI() == null || getPurchaseOrders().getLastUpdateBI() == null)){
                if (!getPurchaseOrders().getPurchaseOrderStatus().getLastUpdateBI().before(getPurchaseOrders().getLastUpdateBI())) {
                throw new Exception("The Last Update Bi of PurchaseOrders must be after " + getPurchaseOrders().getPurchaseOrderStatus().getLastUpdateBI().toString());
                }  
            }

            //Add the "updated" message-----------------------------------------
            String x = purchaseOrdersFacade.update(purchaseOrders);
            addMessage("PurchaseOrder", "updated");
            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.toString(), "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("PurchaseOrdersForm", msg);//clientId= pantalla donde queremos que muestre el error 
            return "PurchaseOrdersForm";
        }
    }

    public String delete(PurchaseOrders purchaseOrders) {
        this.setPurchaseOrders(purchaseOrders);
        try {
            
            //Add the "deleted" message-----------------------------------------
            String x = purchaseOrdersFacade.delete(getPurchaseOrders());
            addMessage("PurchaseOrder number", "deleted");
            return x;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "PurchaseOrders no se pudo borrar correctamente", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("PurchaseOrdersList", msg);//clientId= pantalla donde queremos que muestre el error 
            return "PurchaseOrdersList";
        }
    }

    public String prepareDelete(PurchaseOrders purchaseOrders) {
        this.setPurchaseOrders(purchaseOrders);
        return "PurchaseOrdersList";

    }

    public boolean deleteFlagCheck(PurchaseOrders purchaseOrders) {
        try {
            return this.getPurchaseOrders().equals(purchaseOrders);

        } catch (Exception e) {
            return false;
        }

    }

    public String refresh(String direccion) {
        this.setPurchaseOrders(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("PurchaseOrdersList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");

    }

    public PurchaseOrdersFacade getPurchaseOrdersFacade() {
        return purchaseOrdersFacade;
    }

    public void setPurchaseOrdersFacade(PurchaseOrdersFacade purchaseOrdersFacade) {
        this.purchaseOrdersFacade = purchaseOrdersFacade;
    }

    public PurchaseOrders getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(PurchaseOrders purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
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
                 atribute, this.purchaseOrders.getPONumber(), action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "PurchaseOrdersList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                         message, ""));
    }
}
