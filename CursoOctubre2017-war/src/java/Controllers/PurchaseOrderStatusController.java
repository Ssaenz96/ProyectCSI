/*
 * PurchaseOrderStatusController
 *
 * Version 1.0
 *
 * 9:30am 6/10/17
 *
 * @author Carlos Alberto López Solis
 */
package Controllers;

import Entity.PurchaseOrderStatus;
import Facade.PurchaseOrderStatusFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "purchaseOrderStatusController")
@SessionScoped
public class PurchaseOrderStatusController implements Serializable {

    @EJB
    private PurchaseOrderStatusFacade purchaseOrderStatusFacade;
    private PurchaseOrderStatus purchaseOrderStatus;
    private boolean flagUpdate;

    public String prepareInsert() {
        this.setPurchaseOrderStatus(new PurchaseOrderStatus());//se inicializa un nuevo objeto de purchaseOrderStatus
        setFlagUpdate(false);
        return "PurchaseOrderStatusForm";
    }

    public String insert() {
        try {
            //Add the "inserted" message---------------------------------------
            String x = purchaseOrderStatusFacade.insert(purchaseOrderStatus);
            addMessage("Purchase Order Status", "inserted");
            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("PurchaseOrderStatusForm", msg);
            return "PurchaseOrderStatusForm";
        }
        
    }

    public String prepareUpdate(PurchaseOrderStatus purchaseOrderStatus) {
        setPurchaseOrderStatus(purchaseOrderStatus);
        setFlagUpdate(true);//el rendered te dice si se va a mostrar o no dependiendo de su valor booleano (true=mostrar,false=no mostrar)
        return "PurchaseOrderStatusForm";
    }

    public String update() {
        try {
             //Add the "updated" message---------------------------------------
             String x = purchaseOrderStatusFacade.update(purchaseOrderStatus);
            addMessage("Purchase Order Status", "updated");
            return refresh(x);

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("PurchaseOrderStatusForm", msg);
            return "PurchaseOrderStatusForm";
        }
        
    }

    public String delete(PurchaseOrderStatus purchaseOrderStatus) {
        try {
            this.setPurchaseOrderStatus(purchaseOrderStatus);
            //Add the "deleted" message-----------------------------------------
            String x = purchaseOrderStatusFacade.delete(getPurchaseOrderStatus());
            addMessage("Purchase Order Status", "deleted");
            return x;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "PurchaseOrderStatus no se pudo borrar correctamente", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("PurchaseOrderStatusList", msg);//clientId= pantalla donde queremos que muestre el error 
            return "PurchaseOrderStatusList";
        }
    }

    public String prepareDelete(PurchaseOrderStatus purchaseOrderStatus) {
        this.setPurchaseOrderStatus(purchaseOrderStatus);
        return "PurchaseOrderStatusList";

    }

    public boolean deleteFlagCheck(PurchaseOrderStatus purchaseOrderStatus) {
        try {
            return this.getPurchaseOrderStatus().equals(purchaseOrderStatus);

        } catch (Exception e) {
            return false;
        }

    }

    public String refresh(String direccion) {
        this.setPurchaseOrderStatus(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("PurchaseOrderStatusList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");

    }

    public List<PurchaseOrderStatus> findAll() {
        return purchaseOrderStatusFacade.findAll();
    }

    public PurchaseOrderStatus findById(Long id) {
        return purchaseOrderStatusFacade.findById(id);
    }

    /**
     * @return the purchaseOrderStatus
     */
    public PurchaseOrderStatus getPurchaseOrderStatus() {
        return purchaseOrderStatus;
    }

    /**
     * @param purchaseOrderStatus the purchaseOrderStatus to set
     */
    public void setPurchaseOrderStatus(PurchaseOrderStatus purchaseOrderStatus) {
        this.purchaseOrderStatus = purchaseOrderStatus;
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

        String message = String.format("%s: \"%s\" has been %s.",
                atribute, this.purchaseOrderStatus.getPOStatusCode(), action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "PurchaseOrderStatusList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        message, ""));
    }

}
