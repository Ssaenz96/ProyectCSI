/*
 * ProductsController
 *
 * Version 1.0
 *
 * 9:30am 6/10/17
 *
 * @author Carlos Alberto López Solis
 */
/*******************************************************************************
 * ProductsController
 *
 * Version 2.0
 *
 * 19/10/17
 *
 * @author Irvin Omar Hernandez Hernandez
 * 
 * regla de negocios #3
 */
package Controllers;

import Entity.Products;
import Facade.ProductsFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "productsController")
@SessionScoped
public class ProductsController implements Serializable {

    @EJB
    private ProductsFacade productFacade;

    private Products products;
    private boolean flagUpdate;

    public String prepareInsert() {
        this.setProducts(new Products());//se inicializa un nuevo objeto de producto
        setFlagUpdate(false);
        return "ProductsForm";
    }

    public String insert() {
        try {

            if (products.getEnableDate() == null) {
                if (!(products.getLastChangeDate() == null)) {
                    throw new Exception("Enable Date don't be allow to add a LastChangeDate ");
                }
                if (!(products.getDisableDate() == null)) {
                    throw new Exception("Enable Date don't be allow to add a DisableDate ");
                }
            }

            if (!(products.getDisableDate() == null)) {
                if (!(products.getEnableDate().before(products.getDisableDate()))) {
                    throw new Exception("Disable Date must be after the Enabled Date");
                }
            }
            if (!(products.getLastChangeDate() == null)) {
                if (!((products.getEnableDate().before(products.getLastChangeDate()))
                        || products.getEnableDate().equals(products.getLastChangeDate()))) {
                    throw new Exception("LastChangeDate must be after the Enabled Date");
                }
            }

            //Add the "inserted" message----------------------------------------
            String page = productFacade.insert(getProducts());
            addMessage("Product Code", "inserted");

            return refresh(page);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("ProductsForm", msg);
            return "ProductsForm";
        }
    }

    public String prepareUpdate(Products products) {
        setProducts(products);
        setFlagUpdate(true);//el rendered te dice si se va a mostrar o no dependiendo de su valor booleano (true=mostrar,false=no mostrar)
        return "ProductsForm";
    }

    public String update() {
        try {
            if (products.getEnableDate() == null) {
                if (!(products.getLastChangeDate() == null)) {
                    throw new Exception("Enable Date don't be allow to add a LastChangeDate ");
                }
                if (!(products.getDisableDate() == null)) {
                    throw new Exception("Enable Date don't be allow to add a DisableDate ");
                }
            }

            if (!(products.getDisableDate() == null)) {
                if (!(products.getEnableDate().before(products.getDisableDate()))) {
                    throw new Exception("Disable Date must be after the Enabled Date");
                }
            }
            if (!(products.getLastChangeDate() == null)) {
                if (!((products.getEnableDate().before(products.getLastChangeDate()))
                        || products.getEnableDate().equals(products.getLastChangeDate()))) {
                    throw new Exception("LastChangeDate must be after the Enabled Date");
                }
            }
            //Add the "updated" message-----------------------------------------
            String page = productFacade.update(getProducts());
            addMessage("Product Code", "updated");

            return refresh(page);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("ProductsForm", msg);
            return "ProductsForm";
        }
    }

    public String delete(Products p) {
        this.setProducts(p);
        try {
            //Add the "deleted" message-----------------------------------------
            String page = productFacade.delete(getProducts());
            addMessage("Product Code", "deleted");

            return page;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Product has not been deleted correctly", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("ProductsList", msg);//clientId= pantalla donde queremos que muestre el error 
            return "ProductsList";
        }
    }

    public String prepareDelete(Products products) {
        this.setProducts(products);
        return "ProductsList";

    }

    public boolean deleteFlagCheck(Products products) {
        try {
            return this.getProducts().equals(products);

        } catch (Exception e) {
            return false;
        }

    }

    public String refresh(String direccion) {
        this.setProducts(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("ProductsList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");

    }

    //--------------------------------------------------------------------------
    public List<Products> findAll() {
        return getProductFacade().findAll();
    }

    public Products findById(Long id) {
        return getProductFacade().findById(id);
    }

    //----------------Get & Set-------------------------------------------------
    /**
     * @return the productFacade
     */
    public ProductsFacade getProductFacade() {
        return productFacade;
    }

    /**
     * @param productFacade the productFacade to set
     */
    public void setProductFacade(ProductsFacade productFacade) {
        this.productFacade = productFacade;
    }

    /**
     * @return the products
     */
    public Products getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(Products products) {
        this.products = products;
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
                atribute, this.products.getProductCode(), action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "ProductsList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        message, ""));
    }
}
