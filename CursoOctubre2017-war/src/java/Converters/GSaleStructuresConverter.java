/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Controllers.GSaleStructuresController;
import Entity.GSaleStructures;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * GSaleStructuresConverter
 * Version 2
 * Fecha 06/10/2017
 * @author Irvin Omar Hernandez Hernandez 
 */
@FacesConverter(forClass = GSaleStructures.class)
public class GSaleStructuresConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        GSaleStructuresController gc = context.getApplication().evaluateExpressionGet(context, "#{gSaleStructuresController}", GSaleStructuresController.class);
        return gc.findById(Long.parseLong(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        GSaleStructures gSaleStructures = (GSaleStructures)value;
        return gSaleStructures.getGSaleStructId().toString();

    }
    
}
