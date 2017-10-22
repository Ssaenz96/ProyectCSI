/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;


import Controllers.GSaleStructureLevelsController;
import Entity.GSaleStructureLevels;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Gerardo Cardenas
 * Clase: GSaleStructureLevelsConverter
 * Version 1.0
 * Fecha: 06/10/2017
 * Copyrigth
 */
@FacesConverter(forClass =GSaleStructureLevels.class )
public class GSaleStructureLevelsConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        GSaleStructureLevelsController gslc = context.getApplication().evaluateExpressionGet(context, "#{gSaleStructureLevelsController}", GSaleStructureLevelsController.class);
        return gslc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        GSaleStructureLevels gs = (GSaleStructureLevels)value;
        return gs.getGSaleStructLevelId().toString();
    }
    
}
