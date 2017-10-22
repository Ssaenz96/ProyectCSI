/*
 * ProductPackagesController
 *
 * Version 1.0
 *
 * 9:30am 6/10/17
 *
 * @author Carlos Alberto López Solis
 * ----------------------------------
 *
 * Version 2.0 
 *
 * 11/10/2017
 *
 * Insert, update y delete
 *
 * @author Sergio Galvan
--------------------------------------
 * Version 3.0
 *
 * 13/10/2017
 *
 * Quantity no puede ser menor a 0
 *
 * @author Gerardo Cardenas
-------------------------------------
/*
* Nombre de la clase: ProductPackagesController
* Información de la versión: V. 3.0 (Reglas de negocio)
* Fecha: 16/10/2017
* Copyright: Jesús Rey de la Cruz Arriaga
*/
 

package Controllers;

import Entity.ProductPackages;
import Facade.ProductPackagesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "productPackagesController")
@SessionScoped
public class ProductPackagesController implements Serializable {

    @EJB
    private ProductPackagesFacade productPackagesFacade;
    private ProductPackages productPackages;
    private boolean flagUpdate;

    //--------------------------------------------------------------------------
    public List<ProductPackages> findAll() {
        return productPackagesFacade.findAll();
    }

    public ProductPackages findById(Long id) {
        return productPackagesFacade.findById(id);
    }

    //--------------------------------------------------------------------------
    public String insert() {
        
        try {
            if (!validarQuantity()) {
            throw new Exception("The field ProductPackages can only contain numbers greater than 0");
        }
            //Add the "inserted" message-----------------------------------------
            String page = productPackagesFacade.insert(getProductPackages());
            addMessage("Product Package", "inserted");
            return refresh(page);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("ProductPackagesForm", msg);
            return "ProductPackagesForm";
        }
    }

    public String update() {

        try {
            if (!validarQuantity()) {
                throw new Exception("The field ProductPackages can only contain numbers greater than 0");
            }
             //Add the "updated" message-----------------------------------------
            String page = productPackagesFacade.update(getProductPackages());
            addMessage("Product Package", "updated");
            return refresh(page);
        } catch (Exception e) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("ProductPackagesForm", msg);
            return "ProductPackagesForm";
        }
    }

    public String delete(ProductPackages productPackages) {
        try {
            this.setProductPackages(productPackages);
            //Add the "deleted" message-----------------------------------------
            String page = productPackagesFacade.delete(getProductPackages());
            addMessage("Product Package", "deleted");
            return page;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The field ProductPackages could not delete correctly", "");
            FacesContext.getCurrentInstance().addMessage("ProductPackagesList", msg);
            return "ProductPackagesList";
        }
    }

    public String prepareInsert() {
        this.setProductPackages(new ProductPackages());
        setFlagUpdate(false);
        return "ProductPackagesForm";
    }

    public String preapreUpdate(ProductPackages productPackages) {
        setProductPackages(productPackages);
        setFlagUpdate(true);
        return "ProductPackagesForm";
    }

    public String prepareDelete(ProductPackages productPackages) {
        this.setProductPackages(productPackages);
        return "ProductPackagesList";
    }

    public boolean deleteFlagCheck(ProductPackages productPackages) {
        try {
            return this.getProductPackages().equals(productPackages);
        } catch (Exception e) {
            return false;
        }
    }

    public String refresh(String direccion) {
        this.setProductPackages(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("ProductPackagesList");
    }

    public String vovler() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");
    }

    //---------------------get & set--------------------------------------------
    public ProductPackagesFacade getProductPackagesFacade() {
        return productPackagesFacade;
    }

    public void setProductPackagesFacade(ProductPackagesFacade productPackagesFacade) {
        this.productPackagesFacade = productPackagesFacade;
    }

    public ProductPackages getProductPackages() {
        return productPackages;
    }

    public void setProductPackages(ProductPackages productPackages) {
        this.productPackages = productPackages;
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }

    private boolean validarQuantity() {
        if (productPackages.getQuantity() < 0) {
            return false;
        } else {
            return true;
        }
    }

    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {

        String message = String.format("%s: \"%s\" has been %s.",
                atribute, this.productPackages.getProductPackCode(), action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "ProductPackagesList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        message, ""));
    }

}
