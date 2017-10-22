/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Controllers.MetropolitanZonesController;
import Entity.MetropolitanZones;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Saul Saenz
 * @date 06-10-2017
 * @version 1.0
 */
@FacesConverter(forClass = MetropolitanZones.class)
public class MetropolitanZonesConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        MetropolitanZonesController mZC = context.getApplication().evaluateExpressionGet(context, "#{metropolitanZonesController}", MetropolitanZonesController.class);
        return mZC.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        MetropolitanZones metropolitanZones = (MetropolitanZones) value;
        return metropolitanZones.getMetroZoneId().toString();
    }
    
}
