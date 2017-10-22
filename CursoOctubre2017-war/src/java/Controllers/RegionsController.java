/*
* Nombre de la clase: RegionsController
*
* Información de la versión: version 1
*
* Fecha: 06/10/2017
*
* Copyright: Eduardo Alejandro Martínez Melo
 */
/**
 * @author Luis Gerardo García
 * Clase: RegionsControllers
 * Version 2.0 (Se agregaron los metodos de add, delete, edit, etc.)
 * Fecha: 11/10/2017
 * Copyrigth
 *
 * Carlos Alberto López Solis
 * Version 2.1 (Se agregaron las reglas de negocio y se arreglaron los bugs)
 * Fecha: 16/10/2017
 * 
 * *Edito: Gerardo Cardenas
*Se agrega el el mensaje de java script de eliminar al padre
* 19/10/2017
 */
package Controllers;

import Entity.Regions;
import Facade.RegionsFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "regionsController")
@SessionScoped
public class RegionsController implements Serializable {

    @EJB
    private RegionsFacade regionsFacade;
    private Regions regions;
    private boolean flagUpdate;
    private String code;

    public List<Regions> findAll() {
        return regionsFacade.findAll();

    }

    public Regions findById(Long id) {
        return regionsFacade.findById(id);

    }

    public String prepareInsert() {
        this.setRegions(new Regions());
        setFlagUpdate(false);
        return "RegionsForm";
    }

    public String insert() {
        try {
            if (!(validarLongitud() && validarCodigo() && validarCodigoUnico())) {
                throw new Exception("Invalid code");
            }
            //Add the "inserted" message---------------------------------------
            String x = regionsFacade.insert(regions);
            addMessage("Region Code", "inserted");
            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("RegionsForm", msg);
            return "RegionsForm";
        }
    }

    public String prepareUpdate(Regions regions) {
        this.setRegions(regions);
        setFlagUpdate(true);//el rendered te dice si se va a mostrar o no dependiendo de su valor booleano (true=mostrar,false=no mostrar)
        code = getRegions().getRegionCode();
        return "RegionsForm";
    }

    public String update() {
        try {
            if (!(validarLongitud() && validarCodigo() && validarCodeUpdate())) {
                throw new Exception("Invalid code");
            }

            //Add the "updated" message----------------------------------------
            String x = regionsFacade.update(regions);
            addMessage("Region Code", "updated");
            return refresh(x);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("RegionsForm", msg);
            return "RegionsForm";
        }
    }

    public String delete(Regions regions) {
        try {
            this.setRegions(regions);
            //Add the "deleted" message----------------------------------------
            String x = regionsFacade.delete(getRegions());
            addMessage("Region Code", "deleted");
            return x;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Regions no se pudo borrar correctamente", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("RegionsList", msg);//clientId= pantalla donde queremos que muestre el error 
            setFlagUpdate(false);
            return "RegionsList";
        }
    }

    public String prepareDelete(Regions regions) {
        this.setRegions(regions);
        return "RegionsList";

    }

    public boolean deleteFlagCheck(Regions regions) {
        try {
            return this.getRegions().equals(regions);

        } catch (Exception e) {
            return false;
        }

    }

    public String refresh(String direccion) {
        this.setRegions(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("RegionsList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");

    }

    public RegionsFacade getRegionsFacade() {
        return regionsFacade;
    }

    public void setRegionsFacade(RegionsFacade regionsFacade) {
        this.regionsFacade = regionsFacade;
    }

    public Regions getRegions() {
        return regions;
    }

    public void setRegions(Regions regions) {
        this.regions = regions;
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }

    public boolean validarCodigo() {//Las posiciones impares deben ser letras y las pares números
        int posicion;
        boolean flag = true;
        int i = 0;
        while (i < 5) {
            posicion = (int) getRegions().getRegionCode().charAt(i);
            if (i % 2 == 0) {
                if (posicion > 64 && posicion < 123) {
                    flag = true;
                } else {
                    i += 5;
                    flag = false;
                }
            } else {
                if (posicion > 47 && posicion < 58) {
                    flag = true;
                } else {
                    i += 5;
                    flag = false;
                }
            }
            i++;
        }
        if (!flag) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Region code even characters must be numbers (Slots 1, 3 and 5), odd ones must be letters (slots 2 and 4). EXAMPLE. A1A1A", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("RegionsForm", msg);//clientId= pantalla donde queremos que muestre el error 
        }
        return flag;

    }

    public boolean validarLongitud() {
        if (getRegions().getRegionCode().length() == 5) {
            return true;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Region code must consist of only 5 characters", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("RegionsForm", msg);//clientId= pantalla donde queremos que muestre el error 
            return false;
        }
    }

    public boolean validarCodigoUnico() {
        if (getRegionsFacade().findByCode(getRegions().getRegionCode()).isEmpty()) {
            return true;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Region code must be unique", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("RegionsForm", msg);//clientId= pantalla donde queremos que muestre el error 
            return false;
        }
    }

    public boolean validarCodeUpdate() {
        if (code.equals(getRegions().getRegionCode())) {
            return true;
        } else {
            return validarCodigoUnico();
        }
    }

    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {

        String message = String.format("%s: \"%s\" has been %s.",
                atribute, this.regions.getRegionCode(), action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "RegionsList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        message, ""));
    }

}
