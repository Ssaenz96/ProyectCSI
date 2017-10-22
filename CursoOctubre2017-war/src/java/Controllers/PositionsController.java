
package Controllers;

import Entity.Positions;
import Facade.PositionsFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * PositionsController Version 2 Fecha 06/10/2017
 *
 * @author Irvin Omar Hernandez Hernandez
 * Editado: Jesús Rey de la Cruz (métodos: prepareInsert, insert,
   prepareUpdate, update, prepareDelete, delete, deleteFlagCheck, refresh,
   cancelDelete, volver y volverHome) 11/10/2017
   * 
   * Editado: Gerardo Cardenas
   * Se hace una confirmacion al eliminar un registro de la tabla padre.
   * 
 */
@Named(value = "positionsController")
@SessionScoped
public class PositionsController implements Serializable {

    @EJB

    private PositionsFacade positionsFacade;
    private Positions positions;
    private boolean flagUpdate;
    //--------------------------------------------------------------------------

    public Positions findById(Long id) {
        return positionsFacade.findById(id);
    }

    public List<Positions> findAll() {
        return positionsFacade.findAll();
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }

    public String prepareInsert() {
        this.setPositions(new Positions());
        setFlagUpdate(false);
        return "PositionsForm";
    }

    public String prepareUpdate(Positions objPositions) {
        setPositions(objPositions);
        setFlagUpdate(true);
        return "PositionsForm";
    }

    public String insert() {
        try{
            
            //Add the "inserted" message----------------------------------------
            String x = positionsFacade.insert(positions);
            addMessage("Positons Code", "inserted");
            
            return refresh(x);   
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("PositionsList", msg);
            return "PositionsList";
        }
    }

    public String update() {
        try{
            
            //Add the "updated" message-----------------------------------------
            String x = positionsFacade.update(positions);
            addMessage("Positons Code", "updated");
            
            return refresh(x);   
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("PositionsList", msg);
            return "PositionsList";
        }
    }

    //--- Métodos para botones Eliminar---------------------------------------
    public String delete(Positions p) {
        try {
            this.setPositions(p);
            //Add the "deleted" message-----------------------------------------
            addMessage("Positons Code", "deleted");
            
            return positionsFacade.delete(positions);
        } catch (Exception E) {
            //Severity = color de mensaje, summary =  mensaje, detail= no se utiliza mucho
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "This Position has not been deleted correctly", "");
            FacesContext.getCurrentInstance().addMessage("PositionsList", msg);
            return "PositionsList";
        }
    }

    public String prepareDelete(Positions objPositions) {
        this.positions = objPositions;
        return "PositionsList";
    }

    public boolean deleteFlagCheck(Positions objPositions) {
        try {
            return this.positions.equals(objPositions);
        } catch (Exception e) {
            return false;
        }
    }

    public String refresh(String direccion) {
        this.positions = null;
        return direccion;
    }

    public String cancelDelete() {
        return refresh("PositionsList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");
    }
    //-------------------------------------------------------------------------

    //---------------Get & Set-------------------------------------------------
    /**
     * @return the positionsFacade
     */
    public PositionsFacade getPositionsFacade() {
        return positionsFacade;
    }

    /**
     * @param positionsFacade the positionsFacade to set
     */
    public void setPositionsFacade(PositionsFacade positionsFacade) {
        this.positionsFacade = positionsFacade;
    }

    public Positions getPositions() {
        return positions;
    }

    public void setPositions(Positions positions) {
        this.positions = positions;
    }
    
    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {
        
        String message = String.format("%s: \"%s\" has been %s."
                ,atribute,this.positions.getPositionCode(), action.toLowerCase());
        
        FacesContext.getCurrentInstance().addMessage(
                    "PositionsList", new FacesMessage(FacesMessage.SEVERITY_INFO
                            ,message, ""));
    }
}
