/*
 * ProgramVisitsController
 *
 * Version 1.0
 * 9:30am 6/10/17
 * @author Carlos Alberto López Solis
 * Editado: Jesús Rey de la Cruz (métodos: prepareInsert, insert,
   prepareUpdate, update, prepareDelete, delete, deleteFlagCheck, refresh,
   cancelDelete, volver y volverHome) 11/10/2017
 */
package Controllers;

import Entity.ProgramVisits;
import Facade.ProgramVisitsFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "programVisitsController")
@SessionScoped
public class ProgramVisitsController implements Serializable {

    @EJB
    private ProgramVisitsFacade programVisitsFacade;
    private ProgramVisits programVisits;
    private boolean flagUpdate;

    public List<ProgramVisits> findAll() {
        return programVisitsFacade.findAll();
    }

    public ProgramVisits findById(Long id) {
        return programVisitsFacade.findById(id);
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }

    public String prepareInsert() {
        this.setProgramVisits(new ProgramVisits());
        setFlagUpdate(false);
        return "ProgramVisitsForm";
    }

    public String prepareUpdate(ProgramVisits objProgramVisits) {
        setProgramVisits(objProgramVisits);
        setFlagUpdate(true);
        return "ProgramVisitsForm";
    }

    public String insert() {
        try{
            
            //Add the "inserted" message----------------------------------------
            addMessage("Program visit Code", "inserted");
            
            return refresh(programVisitsFacade.insert(programVisits));  
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("ProgramVisitsForm", msg);
            return "ProgramVisitsForm";
        }
    }

    public String update() {
        try{
            
            //Add the "updated" message-----------------------------------------
            addMessage("Program visit Code", "updated");
            
            return refresh(programVisitsFacade.update(programVisits)); 
        }catch(Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("ProgramVisitsForm", msg);
            return "ProgramVisitsForm";
        }
    }

//--- Métodos para botones Eliminar---------------------------------------
    public String delete(ProgramVisits pv) {
        this.setProgramVisits(pv);
        try {
            //Add the "deleted" message-----------------------------------------
            addMessage("Program visit Code", "deleted");
            
            return programVisitsFacade.delete(programVisits);
        } catch (Exception E) {
//Severity = color de mensaje, summary =  mensaje, detail= no se utiliza mucho
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "This Program Visit has not been deleted correctly", "");
            FacesContext.getCurrentInstance().addMessage("ProgramVisitsList",
                    msg);
            return "ProgramVisitsList";
        }
    }

    public String prepareDelete(ProgramVisits objProgramVisits) {
        this.programVisits = objProgramVisits;
        return "ProgramVisitsList";
    }

    public boolean deleteFlagCheck(ProgramVisits objProgramVisits) {
        try {
            return this.programVisits.equals(objProgramVisits);
        } catch (Exception e) {

            return false;
        }
    }

    public String refresh(String direccion) {
        this.programVisits = null;
        return direccion;
    }

    public String cancelDelete() {
        return refresh("ProgramVisitsList");
    }

    public String volver() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");
    }
//-----------------------------------------------------------------------------

    public ProgramVisitsFacade getProgramVisitsFacade() {
        return programVisitsFacade;
    }

    public void setProgramVisitsFacade(ProgramVisitsFacade programVisitsFacade) 
    {
        this.programVisitsFacade = programVisitsFacade;
    }

    public ProgramVisits getProgramVisits() {
        return programVisits;
    }

    public void setProgramVisits(ProgramVisits programVisits) {
        this.programVisits = programVisits;
    }

    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {
        
        String message = String.format("%s: \"%s\" has been %s."
                ,atribute,this.programVisits.getProfVisitCode(), action.toLowerCase());
        
        FacesContext.getCurrentInstance().addMessage(
                    "ProgramVisitsList", new FacesMessage(FacesMessage.SEVERITY_INFO
                            ,message, ""));
    }
}