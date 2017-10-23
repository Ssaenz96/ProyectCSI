/*

 */
package Controllers;

import Entity.Groups;
import Facade.GroupsFacade;
import LoginPackage.LoginTest;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * GroupsController Version 2 Fecha 06/10/2017
 *
 * @author Irvin Omar Hernandez Hernandez --------------------------------------
 * Version 3 Fecha 10/10/2017
 * @author Sergio Galvan -------------------------------------- Version 4 Fecha
 * 16/10/2017
 * @author Irvin Omar Hernandez Hernandez
 *
 *
 *  * @author Gerardo Cardenas Clase: GroupsController Version 1.0 Fecha:
 * 13/10/2017 Copyright
 *
 * Alternative password y password y alternativeUsername
 * 
 * @author Thania Lopez: password no debe ser igual al user y alternative pasword no debe ser igual al alternativeuser
 * @author Luis Garcia 
 * 13/10/2017 Copyright
 * Se agregó la interactividad con LoginTest
 */
@Named(value = "groupsController")
@SessionScoped
public class GroupsController implements Serializable {

    @EJB
    private GroupsFacade groupsFacade;
    private static Groups groups = new Groups();
    private boolean flagUpdate;
    private Groups code;

    private LoginTest login1 = new LoginTest();
    //--------------------------------------------------------------------------
    public String regresaUsuario(){
       return login1.getUsuario();
    }
    
    public Groups findById(Long id) {
        return groupsFacade.findById(id);
    }
    
    public String Inicializar(){
        groups = new Groups();
        return "login";
    }
    
    public String login(){
        login1.setUsuario(groups.getUserName());
        return groupsFacade.login(groups.getUserName(), groups.getPassword());
    }

            
    public List<Groups> findAll() {
        return groupsFacade.findAll();
    }

    public boolean editUsername() {
        return !groupsFacade.editUserName(groups, groups.getGroupId()).isEmpty();
    }

    public boolean editAlternativeUsername() {
        return !groupsFacade.editAlternativeUsername(groups, groups.getGroupId()).isEmpty();
    }

    public String insert() {
        try {
            code = groupsFacade.findByCode(groups);

            if (!(code == null)) {
                throw new Exception("GroupCode already exists");
            }

            if (!(groups.getUserName() != null || groups.getAlternateUserName() != null)) {
                
                //Se hace esta funcion para detectar cuantos usuarios hay bajo el mismo nombre
                if (!(groupsFacade.findByUserName(groups.getUserName()).isEmpty())) {
                    throw new Exception("Username already exists");
                }

                //Se hace para validar que alternative username no sea igual a username
                if (groups.getAlternateUserName().equals(groups.getUserName())) {
                    throw new Exception("AlternativeUsername and Username must be different");
                }

                //Se hace para validar que el alternative username no sea igual a ningun username de la tabla
                if (!(groupsFacade.findByUserName(groups.getAlternateUserName()).isEmpty())) {
                    throw new Exception("AlternativeUsername already exists");
                }

                //Esta validacion es para vericar que password no sea igual que alternativePassword
                if (groups.getAlternatePassword().equals(groups.getPassword())) {
                    throw new Exception("AlternativePassword and Password must be different");
                }

                //validar que password y usuario no sea igual
                if (groups.getPassword().equals(groups.getUserName())) {
                    throw new Exception("Password and UserName must be different");
                }

                //validar que AlternatePassword y AlternateUserName no sea igual
                if (groups.getAlternatePassword().equals(groups.getAlternateUserName())) {
                    throw new Exception("AlternatePassword and AlternateUserName must be different");
                }
            }

            //Add the "inserted" message----------------------------------------
            addMessage("Group Code", "inserted");

            return refresh(groupsFacade.insert(groups));
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("GroupsForm", msg);
            return "GroupsForm";
        }

    }

    public String update() {
        try {
            code = groupsFacade.findByCode(groups);

            if (!(groups.getUserName() != null || groups.getAlternateUserName() != null)) {

                //Se hace esta funcion para detectar cuantos usuarios hay bajo el mismo nombre
                if (editUsername()) {
                    throw new Exception("UserName already exists");
                }

                //Se hace para validar que alternative username no sea igual a username
                if (groups.getAlternateUserName().equals(groups.getUserName())) {
                    throw new Exception("AlternativeUsername and Username must be different");
                }

                //Se hace para validar que el alternative username no sea igual a ningun username de la tabla
                if (editAlternativeUsername()) {
                    throw new Exception("AlternativeUsername already exists");
                }

                //Esta validacion es para vericar que password no sea igual que alternativePassword
                if (groups.getAlternatePassword().equals(groups.getPassword())) {
                    throw new Exception("AlternativePassword and Password must be different");
                }

                //validar que password y usuario no sea igual
                if (groups.getPassword().equals(groups.getUserName())) {
                    throw new Exception("Password and UserName must be different");
                }

                //validar que AlternatePassword y AlternateUserName no sea igual
                if (groups.getAlternatePassword().equals(groups.getAlternateUserName())) {
                    throw new Exception("AlternatePassword and AlternateUserName must be different");
                }
            }

            //Add the "updated" message-----------------------------------------
            addMessage("Group Code", "updated");

            return refresh(groupsFacade.update(groups));
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage("GroupsForm", msg);
            return "GroupsForm";
        }
    }

    public String delete(Groups groups) {
        try {
            this.setGroups(groups);
            //Add the "deleted" message-----------------------------------------
            addMessage("Group Code", "deleted");

            return groupsFacade.delete(getGroups());
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The field Groups could not delete correctly", "");
            FacesContext.getCurrentInstance().addMessage("GroupsList", msg);
            return "GroupsList";
        }
    }

    public String prepareInsert() {
        this.setGroups(new Groups());
        setFlagUpdate(false);
        return "GroupsForm";
    }

    public String preapreUpdate(Groups groups) {
        setGroups(groups);
        setFlagUpdate(true);
        return "GroupsForm";
    }

    public String prepareDelete(Groups groups) {
        this.setGroups(groups);
        return "GroupsList";
    }

    public boolean deleteFlagCheck(Groups groups) {
        try {
            return this.getGroups().equals(groups);
        } catch (Exception e) {
            return false;
        }
    }

    public String refresh(String direccion) {
        this.setGroups(null);
        return direccion;
    }

    public String cancelDelete() {
        return refresh("GroupsList");
    }

    public String vovler() {
        return cancelDelete();
    }

    public String volverHome() {
        return refresh("/index?faces-redirect=true");
    }

    //---------------------get & set--------------------------------------------
    /**
     * @return the groupsFacade
     */
    public GroupsFacade getGroupsFacade() {
        return groupsFacade;
    }

    /**
     * @param groupsFacade the groupsFacade to set
     */
    public void setGroupsFacade(GroupsFacade groupsFacade) {
        this.groupsFacade = groupsFacade;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public boolean isFlagUpdate() {
        return flagUpdate;
    }

    public void setFlagUpdate(boolean flagUpdate) {
        this.flagUpdate = flagUpdate;
    }

    public Groups getCode() {
        return code;
    }

    public void setCode(Groups code) {
        this.code = code;
    }

    //--------------------------------------------------------------------------
    //-----Regla de Negocio 6---------------------------------------------------
    private void addMessage(String atribute, String action) {

        String message = String.format("%s: \"%s\" has been %s.",
                atribute, this.groups.getGroupCode(), action.toLowerCase());

        FacesContext.getCurrentInstance().addMessage(
                "GroupsList", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        message, ""));
    }
    public String corregirBug(){
       cancelDelete();
       return "if(!confirm('are you sure')){ return false}\" action=\"#{groupsController.delete()}";
    }
    public String prepareLogin(){
        setGroups(new Groups());
        return "login";
    }
}

