/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Controllers.GroupsController;
import Entity.Groups;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * GroupsConverter 
 * Version 2 
 * Fecha 06/10/2017
 * @author Thania López
 */

@FacesConverter(forClass = Groups.class)
public class GroupsConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       GroupsController gc = context.getApplication().evaluateExpressionGet(context,"#{groupsController}", GroupsController.class);
        return gc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Groups groups = (Groups) value;
        return groups.getGroupId().toString();
    }
    
}
