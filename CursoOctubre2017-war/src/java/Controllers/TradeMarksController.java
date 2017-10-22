/*
* Nombre de la clase: TradeMarksController
*
* Información de la versión: version 1
*Version 1.1 Carlos A 10/10/2017
*
* Fecha: 06/10/2017
*
* Copyright: Eduardo Alejandro Martínez Melo
*/

package Controllers;

import Entity.TradeMarks;
import Facade.TradeMarksFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "tradeMarksController")
@SessionScoped
public class TradeMarksController implements Serializable {

    @EJB
    private TradeMarksFacade tradeMarksFacade;
    private TradeMarks tradeMarks;
    private boolean flagUpdate;
    
    public String prepareInsert(){
        this.setTradeMarks(new TradeMarks());//se inicializa un nuevo objeto de tradeMarks
        setFlagUpdate(false);
        return "TradeMarksForm";
    }
    public String insert(){
        try {
            
             //checar que StoreCode no exista
            TradeMarks TMCode = findByTradeMarksCode(tradeMarks.getTradeMarkCode());
            if (TMCode != null) {
                throw new Exception("TradeMarksCode already exists");
            }       
            
            //Add the "inserted" message----------------------------------------
            String page = tradeMarksFacade.insert(getTradeMarks());
            addMessage("TradeMark Code", "inserted");
            
            return refresh(page);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("TradeMarksForm", msg);
        }
        
        return "TradeMarksForm";
    }
    
    public String prepareUpdate(TradeMarks tradeMarks){
        setTradeMarks(tradeMarks);
        setFlagUpdate(true);//el rendered te dice si se va a mostrar o no dependiendo de su valor booleano (true=mostrar,false=no mostrar)
        return "TradeMarksForm";
    }
    
    public String update(){
        try {
            
            if(edit()){
                throw new Exception("TradeMarksCode already exists");
            }
            
            //Add the "updated" message-----------------------------------------
            String page = tradeMarksFacade.update(getTradeMarks());
            addMessage("TradeMark Code", "updated");
            
            return refresh(page);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("TradeMarksForm", msg);
        }
        
        return "TradeMarksForm";
    }
    
    public String delete(TradeMarks tradeMarks){
        try{
            this.setTradeMarks(tradeMarks);
            //Add the "deleted" message-----------------------------------------
            String page = tradeMarksFacade.delete(getTradeMarks());
            addMessage("TradeMark Code", "deleted");
            
            return page;
        }catch(Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "TradeMarks no se pudo borrar correctamente", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("TradeMarksList", msg);//clientId= pantalla donde queremos que muestre el error 
            return "TradeMarksList";
        }
    }
    
    public String prepareDelete(TradeMarks tradeMarks){
        this.setTradeMarks(tradeMarks);
        return "TradeMarksList";
        
    }
    
    public boolean deleteFlagCheck(TradeMarks tradeMarks){
        try{
            return this.getTradeMarks().equals(tradeMarks);
            
        }catch(Exception e){
            return false;
        }
        
    }
     public String refresh(String direccion){
         this.setTradeMarks(null);
         return direccion;
     }

     public String cancelDelete(){
         return refresh("TradeMarksList");
     }
     
     public String volver(){
         return cancelDelete();
     }
     
     public String volverHome(){
         return refresh("/index?faces-redirect=true");
         
     }
    
    //--------------------------------------------------------------------------
    
    public List<TradeMarks> findAll() {
        return getTradeMarksFacade().findAll();
    } 
    
    public TradeMarks findById(Long id) {
        return getTradeMarksFacade().findById(id);
    }

    public TradeMarks findByTradeMarksCode (String code){
        return tradeMarksFacade.findByTradeMarksCode(code);
    }
    
    public boolean edit(){
        return !tradeMarksFacade.edit(tradeMarks, tradeMarks.getTradeMarkId()).isEmpty();
    }
    //----------------Get & Set-------------------------------------------------

    

    /**
     * @return the tradeMarksFacade
     */
    public TradeMarksFacade getTradeMarksFacade() {
        return tradeMarksFacade;
    }

    /**
     * @param tradeMarksFacade the tradeMarksFacade to set
     */
    public void setTradeMarksFacade(TradeMarksFacade tradeMarksFacade) {
        this.tradeMarksFacade = tradeMarksFacade;
    }

    /**
     * @return the tradeMarks
     */
    public TradeMarks getTradeMarks() {
        return tradeMarks;
    }

    /**
     * @param tradeMarks the tradeMarks to set
     */
    public void setTradeMarks(TradeMarks tradeMarks) {
        this.tradeMarks = tradeMarks;
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
        
        String message = String.format("%s: \"%s\" has been %s."
                ,atribute,this.tradeMarks.getTradeMarkCode(), action.toLowerCase());
        
        FacesContext.getCurrentInstance().addMessage(
                    "TradeMarksList", new FacesMessage(FacesMessage.SEVERITY_INFO
                            ,message, ""));
    }
}