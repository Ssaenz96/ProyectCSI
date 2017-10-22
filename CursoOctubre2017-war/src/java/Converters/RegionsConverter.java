/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Controllers.RegionsController;
import Entity.Regions;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Regions.class)

/*
 * RegionsConverter
 *
 * Version 1.0
 *
 * 13:36 pm 6/10/17
 *
 * Luis García.
 * 
 */

public class RegionsConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        RegionsController pc = context.getApplication().evaluateExpressionGet(context, "#{ regionsController }", RegionsController.class);
        return pc.findById(Long.parseLong(value));    
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Regions regions = (Regions) value;
        return regions.getRegionId().toString();
    }
    
}
