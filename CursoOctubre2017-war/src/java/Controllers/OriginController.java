
package Controllers;

import Entity.Origin;
import Facade.OriginFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * OriginController Version 2 Fecha 06/10/2017
 *
 * @author Irvin Omar Hernandez Hernandez
 */

/*
* Nombre de la clase: OriginController
* Información de la versión: V. 3.0 (Reglas de negocio)
* Fecha: 16/10/2017
* Copyright: Jesús Rey de la Cruz Arriaga
*/
@Named(value = "originController")
@SessionScoped
public class OriginController implements Serializable {

    @EJB
    private OriginFacade originFacade;

    private Origin origin;
    private boolean flagUpdate;

    public String prepareInsert() {
        this.setOrigin(new Origin());//se inicializa un nuevo objeto de origin
        setFlagUpdate(false);
        return "OriginForm";
    }

    public String insert() {
        try {
            //Add the "inserted" message---------------------------------------
            String x = originFacade.insert(origin);
            addMessage("Origin Code", "inserted");
            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("OriginForm", msg);
            return "OriginForm";
        }
    }

    public String prepareUpdate(Origin origin) {
        setOrigin(origin);
        setFlagUpdate(true);//el rendered te dice si se va a mostrar o no dependiendo de su valor booleano (true=mostrar,false=no mostrar)
        return "OriginForm";
    }

    public String update() {
        try {
            //Add the "updated" message-----------------------------------------
            String x = originFacade.update(origin);
            addMessage("Origin Code", "updated");
            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("OriginForm", msg);
            return "OriginForm";
        }

    }

    public String delete(Origin origin) {
        try {
            this.setOrigin(origin);
            //Add the "deleted" message-----------------------------------------
            String x = originFacade.delete(getOrigin());
            addMessage("Origin Code", "deleted");
            return x;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Origin no se pudo borrar correctamente", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("OriginList", msg);//clientId= pantalla donde queremos que muestre el error 
            return "OriginList";
        }
    }

    public String prepareDelete(Origin origin) {
        this.setOrigin(origin);
        return "OriginList";

    }

    public boolean deleteFlagCheck(Origin origin) {
        try {
            return this.getOrigin().equals(origin);

        } catch (Exception e) {
            return false;
        }

    }

    public String refresh(String direccion) {
        this.setOrigin(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("OriginList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");

    }

    //--------------------------------------------------------------------------
    public Origin findById(Long Id) {
        return originFacade.findById(Id);
    }

    public List<Origin> findAll() {
        return originFacade.findAll();
    }

    //----------------Get & Set-------------------------------------------------
    /**
     * @return the originFacade
     */
    public OriginFacade getOriginFacade() {
        return originFacade;
    }

    /**
     * @param originFacade the originFacade to set
     */
    public void setOriginFacade(OriginFacade originFacade) {
        this.originFacade = originFacade;
    }

    /**
     * @return the origin
     */
    public Origin getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(Origin origin) {
        this.origin = origin;
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
                atribute, this.origin.getOriginCode(), action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "OriginList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        message, ""));
    }

}
