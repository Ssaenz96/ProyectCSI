/**
 * @author Luis Amaury Urbina Molina
 * Clase: StoreProductsController
 * Version 1.0
 * Fecha: 06/10/2017
 * Copyrigth
 */
package Controllers;

import Entity.StoreProducts;
import Facade.StoreProductsFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Usuario
 */
@Named(value = "storeProductsController")
@SessionScoped
public class StoreProductsController implements Serializable {

    @EJB
    private StoreProductsFacade storeProductsFacade;
    private StoreProducts storeProducts;
    private boolean flag;

    public List<StoreProducts> findAll() {
        return getStoreProductsFacade().findAll();
    }

    public StoreProducts findById(Long id) {
        return getStoreProductsFacade().findById(id);
    }

    public String prepareInsert() {
        this.setStoreProducts(new StoreProducts());//se inicializa un nuevo objeto de pais
        setFlag(false);
        return "StoreProductsForm";
    }

    public String insert() {
        try {
            //****Propuesta de regla de negocio: Una tienda no puede tener productos repetidos
            if (!isUniqueProduct(pList -> true)) {
                throw new Exception(String.format("The product %s is already in the store %s!",
                        storeProducts.getProducts().getProductCode(),
                        storeProducts.getStores().getName()));
            }

            if (!(storeProducts.getEnableDate() == null || storeProducts.getProducts().getDisableDate() == null)) {
                if (!(storeProducts.getEnableDate().before(storeProducts.getDisableDate()))) {
                throw new Exception("Disable Date must be after the Enabled Date");
            }
            }


            if (!(storeProducts.getLastUpdateBI() == null || storeProducts.getStores().getLastUpdateBI() == null)) {
                if (!getStoreProducts().getStores().getLastUpdateBI().before(getStoreProducts().getLastUpdateBI())) {
                    throw new Exception("The Last Update Bi of StoresProducts must be after " + getStoreProducts().getStores().getLastUpdateBI().toString());
                }
            }

            if (!(storeProducts.getLastUpdateBI() == null || storeProducts.getProducts().getLastUpdateBI() == null)) {
                if (!getStoreProducts().getProducts().getLastUpdateBI().before(getStoreProducts().getLastUpdateBI())) {
                    throw new Exception("The Last Update Bi of StoresProducts must be after " + getStoreProducts().getProducts().getLastUpdateBI().toString());
                }
            }

            if (storeProducts.getMaximum() != 0 || storeProducts.getMinimum() != 0) {
                if (storeProducts.getMaximum() < storeProducts.getMinimum()) {
                    throw new Exception("The maximum number of products must be greater than the minimum");
                }
            }
            if (storeProducts.getMaximum() != 0 || storeProducts.getInventory() != 0) {
                if (storeProducts.getMaximum() < storeProducts.getInventory()) {
                    throw new Exception("The maximum number of products must be greater than the Inventory");
                }
            }
            if (storeProducts.getCustomerSalePrice() != 0 || storeProducts.getOfferPrice() != 0) {
                if (storeProducts.getCustomerSalePrice() < storeProducts.getOfferPrice()) {
                    throw new Exception("The customer sale price of product must be greater than the offer price");
                }
            }
            if (!(storeProducts.getLastCSPChangeDate() == null || storeProducts.getEnableDate() == null)) {
                if (!(storeProducts.getLastCSPChangeDate().after(storeProducts.getEnableDate())
                        || storeProducts.getLastCSPChangeDate().equals(storeProducts.getEnableDate()))) {
                    throw new Exception("Last CSP Change Date must be after or equal Enable Date");
                }
            }
            if (!(storeProducts.getLastChangeDate() == null || storeProducts.getEnableDate() == null)) {
                if (!(storeProducts.getLastChangeDate().after(storeProducts.getEnableDate()) || storeProducts.getLastChangeDate().equals(storeProducts.getEnableDate()))) {
                    throw new Exception("Last Change Date must be after or equal Enable Date");
                }
            }
            if (!(storeProducts.getLastOfferDate() == null || storeProducts.getEnableDate() == null)) {
                if (!(storeProducts.getLastOfferDate().after(storeProducts.getEnableDate()) || storeProducts.getLastOfferDate().equals(storeProducts.getEnableDate()))) {
                    throw new Exception("Last Offer Date must be after or equal Enable Date");
                }
            }
            if (!(storeProducts.getLastInventoryUpdate() == null || storeProducts.getEnableDate() == null)) {
                if (!(storeProducts.getLastInventoryUpdate().after(storeProducts.getEnableDate()) || storeProducts.getLastInventoryUpdate().equals(storeProducts.getEnableDate()))) {
                    throw new Exception("Last Inventory Update must be after or equal Enable Date");
                }
            }
//            if(validatorProduct() && validatorStore()){
//                throw new Exception("This product already exists in this store");
//            }

            //Add the "inserted" message----------------------------------------
            addMessage("StoreProduct", "inserted");

            return refresh(storeProductsFacade.insert(getStoreProducts()));
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("StoreProductsForm", msg);
            return "StoreProductsForm";
        }
    }

    public String prepareUpdate(StoreProducts storeProducts) {
        setStoreProducts(storeProducts);
        setFlag(true);//el rendered te dice si se va a mostrar o no dependiendo de su valor booleano (true=mostrar,false=no mostrar)
        return "StoreProductsForm";
    }

    public String update() {
        try {
            //****Propuesta de regla de negocio: Una tienda no puede tener productos repetidos
            if (!isUniqueProduct(pList -> !pList.getStoreProductId().equals(storeProducts.getStoreProductId()))) {
                throw new Exception(String.format("The product %s is already in the store %s!",
                        storeProducts.getProducts().getProductCode(),
                        storeProducts.getStores().getName()));
            }

            if (!(storeProducts.getEnableDate() == null || storeProducts.getProducts().getDisableDate() == null)) {
                if (!(storeProducts.getEnableDate().before(storeProducts.getDisableDate()))) {
                throw new Exception("Disable Date must be after the Enabled Date");
            }
            }
            if (!(storeProducts.getLastUpdateBI() == null || storeProducts.getStores().getLastUpdateBI() == null)) {
                if (!getStoreProducts().getStores().getLastUpdateBI().before(getStoreProducts().getLastUpdateBI())) {
                    throw new Exception("The Last Update Bi of StoresProducts must be after " + getStoreProducts().getStores().getLastUpdateBI().toString());
                }
            }

            if (!(storeProducts.getLastUpdateBI() == null || storeProducts.getProducts().getLastUpdateBI() == null)) {
                if (!getStoreProducts().getProducts().getLastUpdateBI().before(getStoreProducts().getLastUpdateBI())) {
                    throw new Exception("The Last Update Bi of StoresProducts must be after " + getStoreProducts().getProducts().getLastUpdateBI().toString());
                }
            }
            if (storeProducts.getMaximum() != 0 || storeProducts.getMinimum() != 0) {
                if (storeProducts.getMaximum() < storeProducts.getMinimum()) {
                    throw new Exception("The maximum number of products must be greater than the minimum");
                }
            }
            if (storeProducts.getMaximum() != 0 || storeProducts.getInventory() != 0) {
                if (storeProducts.getMaximum() < storeProducts.getInventory()) {
                    throw new Exception("The maximum number of products must be greater than the Inventory");
                }
            }
            if (storeProducts.getCustomerSalePrice() != 0 || storeProducts.getOfferPrice() != 0) {
                if (storeProducts.getCustomerSalePrice() < storeProducts.getOfferPrice()) {
                    throw new Exception("The customer sale price of product must be greater than the offer price");
                }
            }
            if (!(storeProducts.getLastCSPChangeDate() == null || storeProducts.getEnableDate() == null)) {
                if (!(storeProducts.getLastCSPChangeDate().after(storeProducts.getEnableDate())
                        || storeProducts.getLastCSPChangeDate().equals(storeProducts.getEnableDate()))) {
                    throw new Exception("Last CSP Change Date must be after or equal Enable Date");
                }
            }
            if (!(storeProducts.getLastChangeDate() == null || storeProducts.getEnableDate() == null)) {
                if (!(storeProducts.getLastChangeDate().after(storeProducts.getEnableDate()) || storeProducts.getLastChangeDate().equals(storeProducts.getEnableDate()))) {
                    throw new Exception("Last Change Date must be after or equal Enable Date");
                }
            }
            if (!(storeProducts.getLastOfferDate() == null || storeProducts.getEnableDate() == null)) {
                if (!(storeProducts.getLastOfferDate().after(storeProducts.getEnableDate()) || storeProducts.getLastOfferDate().equals(storeProducts.getEnableDate()))) {
                    throw new Exception("Last Offer Date must be after or equal Enable Date");
                }
            }
            if (!(storeProducts.getLastInventoryUpdate() == null || storeProducts.getEnableDate() == null)) {
                if (!(storeProducts.getLastInventoryUpdate().after(storeProducts.getEnableDate()) || storeProducts.getLastInventoryUpdate().equals(storeProducts.getEnableDate()))) {
                    throw new Exception("Last Inventory Update must be after or equal Enable Date");
                }
            }
//            if(validatorProduct() && validatorStore()){
//                throw new Exception("This product already exists in this store");
//            }
            //Add the "updated" message-----------------------------------------
            addMessage("StoreProduct", "updated");

            return refresh(storeProductsFacade.update(getStoreProducts()));
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("StoreProductsForm", msg);
            return "StoreProductsForm";
        }
    }

    public String delete() {
        try {
            //Add the "deleted" message-----------------------------------------
            addMessage("StoreProduct", "deleted");

            return storeProductsFacade.delete(getStoreProducts());
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Store Products can't be delete", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("StoreProductsList", msg);//clientId= pantalla donde queremos que muestre el error 
            return "StoreProductsList";
        }
    }

    public String prepareDelete(StoreProducts storeProducts) {
        this.setStoreProducts(storeProducts);
        return "StoreProductsList";
    }

    public boolean deleteFlagCheck(StoreProducts storeProducts) {
        try {
            return this.getStoreProducts().equals(storeProducts);
        } catch (Exception e) {
            return false;
        }
    }

    public String refresh(String direccion) {
        this.setStoreProducts(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("StoreProductsList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");
    }

    /**
     * @return the storeProductsFacade
     */
    public StoreProductsFacade getStoreProductsFacade() {
        return storeProductsFacade;
    }

    /**
     * @param storeProductsFacade the storeProductsFacade to set
     */
    public void setStoreProductsFacade(StoreProductsFacade storeProductsFacade) {
        this.storeProductsFacade = storeProductsFacade;
    }

    /**
     * @return the storeProducts
     */
    public StoreProducts getStoreProducts() {
        return storeProducts;
    }

    /**
     * @param storeProducts the storeProducts to set
     */
    public void setStoreProducts(StoreProducts storeProducts) {
        this.storeProducts = storeProducts;
    }

    /**
     * @return the flag
     */
    public boolean isFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {

        String submessage = String.format("product: %S in the store: %s",
                this.storeProducts.getProducts().getProductCode(),
                this.storeProducts.getStores().getStoreCode());

        String message = String.format("%s: \"%s\" has been %s.",
                atribute, submessage, action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "StoreProductsList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        message, ""));
    }

    //***Regla de negocio: Una tienda no puede tener productor repetidos.
    private boolean isUniqueProduct(Predicate<? super StoreProducts> condition) {
        //El metodo compara el codigo de tienda y producto del objeto actual
        //con el codigo de tienda y producto de todos los elementos en la BD.
        //Si encuentra al menos uno devuelve false
        return !storeProductsFacade.findAll().stream()
                .filter(condition)
                .anyMatch(pList -> pList.getProducts().getProductCode()
                .equals(storeProducts.getProducts().getProductCode())
                && pList.getStores().getStoreCode()
                        .equals(storeProducts.getStores().getStoreCode()));

    }
}
