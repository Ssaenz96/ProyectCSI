/*
* ChannelsController.java
*
* Version 1.0
*
* Fecha 06/10/2017
*
* Jesús de la Cruz
* Editado: Eduerdo Alejandro Martínez Melo (métodos: prepareInsert, insert,
  prepareUpdate, update, prepareDelete, delete, deleteFlagCheck, refresh,
  cancelDelete, volver y volverHome) 11/10/2017
* 
 */
package Controllers;

import Entity.Channels;
import Facade.ChannelsFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


@Named(value = "channelsController")
@SessionScoped
public class ChannelsController implements Serializable {

    @EJB
    private ChannelsFacade channelsFacade;
    private final String pageForm = "ChannelsForm";
    private final String pageList = "ChannelsList";
    private Channels channels;
    private boolean flagUpdate;

    public Channels getChannels() {
        return channels;
    }

    public void setChannels(Channels channels) {
        this.channels = channels;
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }
    
    public List<Channels> findAll(){
       return channelsFacade.findAll();
   }
    
    public Channels findById(Long id){
       return channelsFacade.findById(id);
   }
    
   public String prepareInsert() {
       this.setChannels(new Channels());
       setFlagUpdate(false);
       return pageForm;
   } 
   
   public String insert() {
        try{
            //Add the "inserted" message----------------------------------------
            String x = channelsFacade.insert(channels);
            addMessage("Channel Code", "inserted");
            return refresh(x);   
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("ChannelsForm", msg);
            return "ChannelsForm";
        }
   }
   
   public String prepareUpdate(Channels channels) {
       setChannels(channels);
       setFlagUpdate(true);
       return pageForm;
   }
   
   public String update() {
        try{
            //Add the "updated" message-----------------------------------------
            String x = channelsFacade.update(channels);
            addMessage("Channel Code", "updated");
            return refresh(x);   
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("ChannelsForm", msg);
            return "ChannelsForm";
        }
   }
   
   public String prepareDelete(Channels channels) {
       this.setChannels(channels);
       return pageList;
   }
   
   public String delete(Channels channels) {
       try {
           this.setChannels(channels);
           //Add the "deleted" message------------------------------------------
            String x = channelsFacade.delete(getChannels());
            addMessage("Channel Code", "deleted");
            return x;
       } catch (Exception e) {
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Channel has not been deleted correctly", "");
           FacesContext.getCurrentInstance().addMessage(pageList, msg);
           return pageList;
       }
   }
   
   public boolean deleteFlagCheck(Channels channels) {
       try {
           return this.getChannels().equals(channels);
       } catch (Exception e) {
           return false;
       }
   }
   
   public String refresh(String direccion) {
      this.setChannels(null);
      return direccion;
   }
   
   public String cancelDelete() {
       return refresh(pageList);
   }
   
   public String volver() {
       return cancelDelete();
   }
   
   public String volverHome() {
       return refresh("/index?faces-redirect=true");
   }
   
    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {
        
        String message = String.format("%s: \"%s\" has been %s."
                ,atribute,this.channels.getChannelCode(), action.toLowerCase());
        
        FacesContext.getCurrentInstance().addMessage(
                    "ChannelsList", new FacesMessage(FacesMessage.SEVERITY_INFO
                            ,message, ""));
    }
}
