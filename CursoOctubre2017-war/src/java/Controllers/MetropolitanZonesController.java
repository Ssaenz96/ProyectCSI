package Controllers;

import Entity.MetropolitanZones;
import Facade.MetropolitanZonesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * MetropolitanZonesController Version 2 Fecha 06/10/2017
 *
 * @author Irvin Omar Hernandez Hernandez Editado: Jesús Rey de la Cruz
 * (métodos: prepareInsert, insert, prepareUpdate, update, prepareDelete,
 * delete, deleteFlagCheck, refresh, cancelDelete, volver y volverHome)
 * 11/10/2017
 *
 * * Carlos Alberto López Solis Version 2.1 (Se agregaron las reglas de
 * negocio) Fecha: 16/10/2017
 */
@Named(value = "metropolitanZonesController")
@SessionScoped
public class MetropolitanZonesController implements Serializable {

    @EJB
    private MetropolitanZonesFacade metropolitanZonesFacade;
    private MetropolitanZones metropolitanZones;
    private boolean flagUpdate;

    //--------------------------------------------------------------------------
    public MetropolitanZones findById(Long id) {
        return metropolitanZonesFacade.findById(id);
    }

    public List<MetropolitanZones> findAll() {
        return metropolitanZonesFacade.findAll();
    }
//-----------------------------------------------------------------------------

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }

    public String prepareInsert() {
        this.setMetropolitanZones(new MetropolitanZones());
        setFlagUpdate(false);
        return "MetropolitanZonesForm";
    }

    public String prepareUpdate(MetropolitanZones objMetropolitanZones) {
        setMetropolitanZones(objMetropolitanZones);
        setFlagUpdate(true);
        return "MetropolitanZonesForm";
    }

    public String insert() {
        if (validarLongitud() && validarCodigo()) {
            //Add the "inserted" message---------------------------------------
            String x = metropolitanZonesFacade.insert(metropolitanZones);
            addMessage("MetroZone Code", "inserted");
            return refresh(x);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "MetroZoneCode must have a letter as its first value and a number as its second one", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("MetropolitanZonesForm", msg);//clientId= pantalla donde queremos que muestre el error 
            return "MetropolitanZonesForm";
        }
    }

    public String update() {
        if (validarLongitud() && validarCodigo()) {
            //Add the "updated" message----------------------------------------
            String x = metropolitanZonesFacade.update(metropolitanZones);
            addMessage("MetroZone Code", "updated");
            return refresh(x);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "MetroZoneCode must have a letter as its first value and a number as its second one", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("MetropolitanZonesForm", msg);//clientId= pantalla donde queremos que muestre el error 
            return "MetropolitanZonesForm";
        }
    }

//--- Métodos para botones Eliminar---------------------------------------
    public String delete(MetropolitanZones metropolitanZones) {
        try {
            this.setMetropolitanZones(metropolitanZones);
            //Add the "deleted" message-----------------------------------------
            String x = metropolitanZonesFacade.delete(getMetropolitanZones());
            addMessage("MetroZone Code", "deleted");
            return x;
        } catch (Exception E) {
//Severity = color de mensaje, summary =  mensaje, detail= no se utiliza mucho
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "This Metropolitan Zone has not been deleted correctly", "");
            FacesContext.getCurrentInstance().addMessage("MetropolitanZonesList",
                    msg);
            return "MetropolitanZonesList";
        }
    }

    public String prepareDelete(MetropolitanZones objMetropolitanZones) {
        this.metropolitanZones = objMetropolitanZones;
        return "MetropolitanZonesList";
    }

    public boolean deleteFlagCheck(MetropolitanZones objMetropolitanZones) {
        try {
            return this.metropolitanZones.equals(objMetropolitanZones);
        } catch (Exception e) {

            return false;
        }
    }

    public String refresh(String direccion) {
        this.metropolitanZones = null;
        return direccion;
    }

    public String cancelDelete() {
        return refresh("MetropolitanZonesList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");
    }
    //----------------Get & Set-------------------------------------------------

    public MetropolitanZonesFacade getMetropolitanZonesFacade() {
        return metropolitanZonesFacade;
    }

    public void setMetropolitanZonesFacade(MetropolitanZonesFacade metropolitanZonesFacade) {
        this.metropolitanZonesFacade = metropolitanZonesFacade;
    }

    public MetropolitanZones getMetropolitanZones() {
        return metropolitanZones;
    }

    public void setMetropolitanZones(MetropolitanZones metropolitanZones) {
        this.metropolitanZones = metropolitanZones;
    }

    public boolean validarCodigo() {//Primero debes ingresar una letra y despues un número
        int posicion;
        posicion = (int) getMetropolitanZones().getMetroZoneCode().charAt(0);
        if (posicion > 64 && posicion < 123) {
            posicion = (int) getMetropolitanZones().getMetroZoneCode().charAt(1);
            if (posicion > 47 && posicion < 58) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean validarLongitud() {
        if (getMetropolitanZones().getMetroZoneCode().length() == 2) {
            return true;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Metropolitan Zone code must consist of only 2 characters", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("MetropolitanZonesForm", msg);//clientId= pantalla donde queremos que muestre el error 
            return false;
        }
    }

    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {

        String message = String.format("%s: \"%s\" has been %s.",
                 atribute, this.metropolitanZones.getMetroZoneCode(), action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "MetropolitanZonesList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                         message, ""));
    }
}
