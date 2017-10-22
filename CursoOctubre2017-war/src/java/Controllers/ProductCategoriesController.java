/*
 * ProductCategoriesController
 *
 * Version 1.0
 *
 * 9:30am 6/10/17
 *
 * @author Carlos Alberto López Solis

*Edito: Gerardo Cardenas
*Se agrega el el mensaje de java script de eliminar al padre
19/10/2017
 */
package Controllers;

import Entity.ProductCategories;
import Facade.ProductCategoriesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "productCategoriesController")
@SessionScoped
public class ProductCategoriesController implements Serializable {

    @EJB
    private ProductCategoriesFacade productCategoriesFacade;
    private ProductCategories productCategories;
    private boolean flagUpdate;
    private ProductCategories code;
    
    public String prepareInsert(){
        this.setProductCategories(new ProductCategories());//se inicializa un nuevo objeto de productCategories
        setFlagUpdate(false);
        return "ProductCategoriesForm";
    }
    public String insert(){
        try{
            
            //Add the "inserted" message----------------------------------------
            String page = productCategoriesFacade.insert(getProductCategories());
            addMessage("ProductCategory Code", "Inserted");
   
            return refresh(page);  
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("ProductCategoriesForm", msg);
            return "ProductCategoriesForm";
        } 
    }
   
    public String prepareUpdate(ProductCategories productCategories){
        setProductCategories(productCategories);
        setFlagUpdate(true);//el rendered te dice si se va a mostrar o no dependiendo de su valor booleano (true=mostrar,false=no mostrar)
        return "ProductCategoriesForm";
    }
    
    public String update(){
        try{
            
            if (edit()) {
                throw new Exception("ProductCategories already exists");
            }
            
            //Add the "updated" message-----------------------------------------
            String page = productCategoriesFacade.update(getProductCategories());
            addMessage("ProductCategory Code", "updated");
            
            return refresh(page);   
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("ProductCategoriesForm", msg);
            return "ProductCategoriesForm";
        } 
    }
    
    public String delete(ProductCategories productCategories){
        try{
            this.setProductCategories(productCategories);
            //Add the "deleted" message-----------------------------------------
            String page = productCategoriesFacade.delete(getProductCategories());
            addMessage("ProductCategory Code", "deleted");
            
            return page;
        }catch(Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ProductCategories no se pudo borrar correctamente", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("ProductCategoriesList", msg);//clientId= pantalla donde queremos que muestre el error 
            return "ProductCategoriesList";
        }
    }
    
    public String prepareDelete(ProductCategories productCategories){
        this.setProductCategories(productCategories);
        return "ProductCategoriesList";
        
    }
    
    public boolean deleteFlagCheck(ProductCategories productCategories){
        try{
            return this.getProductCategories().equals(productCategories);
            
        }catch(Exception e){
            return false;
        }
        
    }
     public String refresh(String direccion){
         this.setProductCategories(null);
         return direccion;
     }

     public String cancelDelete(){
         return refresh("ProductCategoriesList");
     }
     
     public String volver(){
         return cancelDelete();
     }
     
     public String volverHome(){
         return refresh("/index?faces-redirect=true");
         
     }
    
    public List<ProductCategories> findAll(){
        return productCategoriesFacade.findAll();
    }
    
    public ProductCategories findById(Long id){
        return productCategoriesFacade.findById(id);
    }
    
    public boolean edit(){
                return !productCategoriesFacade.edit(productCategories, productCategories.getProdCategoryId()).isEmpty();
            }
    //--------------------------------------------------------------------------

    /**
     * @return the productCategories
     */
    public ProductCategories getProductCategories() {
        return productCategories;
    }

    /**
     * @param productCategories the productCategories to set
     */
    public void setProductCategories(ProductCategories productCategories) {
        this.productCategories = productCategories;
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

    public ProductCategories getCode() {
        return code;
    }

    public void setCode(ProductCategories code) {
        this.code = code;
    }
    
    
    
    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {
        
        String message = String.format("%s: \"%s\" has been %s."
                ,atribute,this.productCategories.getProdCategoryCode(), action.toLowerCase());
        
        FacesContext.getCurrentInstance().addMessage(
                    "ProductCategoriesList", new FacesMessage(FacesMessage.SEVERITY_INFO
                            ,message, ""));
    }
}
