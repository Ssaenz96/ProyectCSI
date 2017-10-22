/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Controllers.RetailsController;
import Entity.Retails;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * RetailsConverter
 * Version 2
 * Fecha 06/10/2017
 * @author Irvin Omar Hernandez Hernandez 
 */
@FacesConverter(forClass = Retails.class)
public class RetailsConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       RetailsController rc = context.getApplication().evaluateExpressionGet(context, "#{retailsController}", RetailsController.class);
        return rc.findById(Long.parseLong(value)); 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Retails retails = (Retails)value;
        return retails.getRetailId().toString();
    }
    
}
