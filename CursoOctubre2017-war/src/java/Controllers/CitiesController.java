/*
* CitiesController.java
*
* Version 1.0
*
* Fecha 06/10/2017
*
* Jesús de la Cruz
* Editado: Eduerdo Alejandro Martínez Melo (métodos: prepareInsert, insert,
  prepareUpdate, update, prepareDelete, delete, deleteFlagCheck, refresh,
  cancelDelete, volver y volverHome) 11/10/2017
 */
package Controllers;

import Entity.Cities;
import Facade.CitiesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "citiesController")
@SessionScoped
public class CitiesController implements Serializable {

    @EJB
    private CitiesFacade citiesFacade;
    private final String pageList = "CitiesList";
    private final String pageForm = "CitiesForm";
    private Cities cities;
    private boolean flagUpdate;

    public Cities getCities() {
        return cities;
    }

    public void setCities(Cities cities) {
        this.cities = cities;
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }

    public List<Cities> findAll() {
        return citiesFacade.findAll();
    }

    public Cities findById(Long id) {
        return citiesFacade.findById(id);
    }

    public String prepareInsert() {
        this.setCities(new Cities());
        this.setFlagUpdate(false);
        return pageForm;
    }

    public String insert() {
        try {
            //Regla de negocio: Population >0
            if (cities.getPopulation() <= 0) {
                throw new Exception("The population is not correct");
            }

            if (!(cities.getLastUpdateBi() == null || cities.getStates().getLastUpdateBI() == null)) {
                if (!getCities().getStates().getLastUpdateBI().before(getCities().getLastUpdateBi())) {
                    throw new Exception("The Last Update Bi of Cities must be after " + getCities().getStates().getLastUpdateBI().toString());
                }
            }
            if (!(cities.getLastUpdateBi() == null || cities.getMetropolitanZones().getLastUpdateBI() == null)) {
                if (!getCities().getMetropolitanZones().getLastUpdateBI().before(getCities().getLastUpdateBi())) {
                    throw new Exception("The Last Update Bi of Cities must be after " + getCities().getMetropolitanZones().getLastUpdateBI().toString());
                }
            }
            
            //Add the "inserted" message----------------------------------------
            String x = citiesFacade.insert(getCities());
            addMessage("Cities Code", "inserted");
            return refresh(x);

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("CitiesForm", msg);
        }
        return "CitiesForm";
    }

    public String prepareUpdate(Cities cities) {
        this.setCities(cities);
        this.setFlagUpdate(true);
        return pageForm;
    }

    public String update() {
        try {
            //Regla de negocio: Population >0
            if (cities.getPopulation() <= 0) {
                throw new Exception("The population is not correct");
            }
            
            if (!(cities.getLastUpdateBi() == null || cities.getStates().getLastUpdateBI() == null)) {
                if (!getCities().getStates().getLastUpdateBI().before(getCities().getLastUpdateBi())) {
                    throw new Exception("The Last Update Bi of Cities must be after " + getCities().getStates().getLastUpdateBI().toString());
                }
            }
            if (!(cities.getLastUpdateBi() == null || cities.getMetropolitanZones().getLastUpdateBI() == null)) {
                if (!getCities().getMetropolitanZones().getLastUpdateBI().before(getCities().getLastUpdateBi())) {
                    throw new Exception("The Last Update Bi of Cities must be after " + getCities().getMetropolitanZones().getLastUpdateBI().toString());
                }
            }

            //Add the "updated" message-----------------------------------------
            String x = citiesFacade.update(getCities());
            addMessage("Cities Code", "updated");
            return refresh(x);

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("CitiesForm", msg);
        }
        return "CitiesForm";
    }

    public String prepareDelete(Cities cities) {
        this.setCities(cities);
        return pageList;
    }

    public String delete(Cities c) {
        this.setCities(c);
        try {
            //Add the "deleted" message-----------------------------------------
            String x = citiesFacade.delete(getCities());
            addMessage("Cities Code", "deleted");
            return x;
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "City has not been deleted correctly", "");
            FacesContext.getCurrentInstance().addMessage(pageForm, msg);
            return pageForm;
        }
    }

    public boolean deleteFlagCheck(Cities cities) {
        try {
            return this.getCities().equals(cities);
        } catch (Exception e) {
            return false;
        }
    }

    public String refresh(String direccion) {
        this.setCities(null);
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

        String message = String.format("%s: \"%s\" has been %s.",
                atribute, this.cities.getCityCode(), action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "CitiesList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        message, ""));
    }
}
