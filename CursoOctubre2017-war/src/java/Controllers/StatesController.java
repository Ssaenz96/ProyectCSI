/*
* Nombre de la clase: StatesController
*
* Información de la versión: version 1
*
* Fecha: 06/10/2017
*
* Copyright: Eduardo Alejandro Martínez Melo
 */
/**
 * @author Luis Gerardo García
 * Clase: StatesControllers
 * Version 2.0 (Se agregaron los metodos de add, delete, edit, etc.)
 * Fecha: 11/10/2017
 * Copyrigth
 */

/*
* Nombre de la clase: StatesController
* Información de la versión: V. 3.0 (Reglas de negocio)
* Fecha: 16/10/2017
* Copyright: Jesús Rey de la Cruz Arriaga
 */
package Controllers;

import Entity.States;
import Facade.StatesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "statesController")
@SessionScoped
public class StatesController implements Serializable {

    @EJB
    private StatesFacade statesFacade;
    private States states;
    private boolean flagUpdate;

    public List<States> findAll() {
        return statesFacade.findAll();
    }

    public States findById(Long id) {
        return statesFacade.findById(id);
    }
//******Propuesta Regla de Negocio: No debe haber Estados repetidos

    public boolean coincidenciaCode() {
        return !statesFacade.findByCode(states.getStateCode()).isEmpty();
    }

    public boolean coincidenciaName() {
        return !statesFacade.findByName(states.getName()).isEmpty();
    }

    public boolean coincidenciaCodeEdit() {
        return !statesFacade.findByCodeEdit(states.getStateCode(), states.getStateId()).isEmpty();
    }

    public boolean coincidenciaNameEdit() {
        return !statesFacade.findByNameEdit(states.getName(), states.getStateId()).isEmpty();
    }
//****************************************************************

    private boolean validarLastUpdate() {
        if (states.getLastUpdateBI().after(states.getRegion().getLastUpdateBI())) {
            return true;
        } else {
            return false;
        }
    }

    public String prepareInsert() {
        this.setStates(new States());
        setFlagUpdate(false);
        return "StatesForm";
    }

    public String insert() {
        try {
            //******Propuesta Regla de Negocio: No debe haber Estados repetidos
            if (coincidenciaCode()) {
                throw new Exception("YOU CAN NOT ENTER A REPEATED STATECODE");
            }
            if (coincidenciaName()) {
                throw new Exception("YOU CAN NOT ENTER A REPEATED STATE");
            }
            //******Propuesta Regla de Negocio: No más de 32 estados
            if (statesFacade.findAll().size() > 31) {
                throw new Exception("STATES LIMIT REACHED (32)");
            }
            //***************************************************************
            if(!validarLastUpdate()){
                throw new Exception("The date of the States table must be earlier than the date of regions");
            }
            //Add the "deleted" message----------------------------------------
            String x = statesFacade.insert(states);
            addMessage("State", "inserted");
            return refresh(x);
        } catch (Exception e) {
            FacesMessage msq = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("StatesForm", msq);
            return "StatesForm";
        }
    }

    public String prepareUpdate(States states) {
        this.setStates(states);
        setFlagUpdate(true);//el rendered te dice si se va a mostrar o no dependiendo de su valor booleano (true=mostrar,false=no mostrar)
        return "StatesForm";
    }

    public String update() {
        try {
            //******Propuesta Regla de Negocio: No debe haber Estados repetidos
            if (coincidenciaCodeEdit()) {
                throw new Exception("YOU CAN NOT ENTER A REPEATED STATECODE");
            }
            if (coincidenciaNameEdit()) {
                throw new Exception("YOU CAN NOT ENTER A REPEATED STATE");
            }
            if(!validarLastUpdate()){
                throw new Exception("The date of the States table must be earlier than the date of regions");
            }
//Add the "deleted" message-----------------------------------------
            String x = statesFacade.update(states);
            addMessage("State", "updated");
            return refresh(x);
        } catch (Exception e) {
            FacesMessage msq = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("StatesForm", msq);
            return "StatesForm";
        }
    }

    public String delete(States states) {
        try {
            this.setStates(states);
            //Add the "deleted" message-----------------------------------------
            String x = statesFacade.delete(getStates());
            addMessage("State", "deleted");
            return x;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "States no se pudo borrar correctamente", "");//el detail es agregar algo mas al mensaje
            FacesContext.getCurrentInstance().addMessage("StatesList", msg);//clientId= pantalla donde queremos que muestre el error 
            return "StatesList";
        }
    }

    public String prepareDelete(States states) {
        this.setStates(states);
        return "StatesList";

    }

    public boolean deleteFlagCheck(States states) {
        try {
            return this.getStates().equals(states);

        } catch (Exception e) {
            return false;
        }

    }

    public String refresh(String direccion) {
        this.setStates(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("StatesList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");

    }

    public StatesFacade getStatesFacade() {
        return statesFacade;
    }

    public void setStatesFacade(StatesFacade statesFacade) {
        this.statesFacade = statesFacade;
    }

    public States getStates() {
        return states;
    }

    public void setStates(States states) {
        this.states = states;
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }

    //-----Regla de Negocio 6--------------------------------------------------
    private void addMessage(String atribute, String action) {

        String message = String.format("%s: \"%s\" has been %s.",
                atribute, this.states.getName(), action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "StatesList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        message, ""));
    }
}
