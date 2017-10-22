/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Controllers.OriginController;
import Entity.Origin;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *OriginConverter
 * @author Luis Amaury
 * 06/10/2017
 * Version 1.0
 * 
 */

@FacesConverter(forClass = Origin.class)
public class OriginConverter implements Converter{
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        OriginController pc = context.getApplication().evaluateExpressionGet(context, "#{originController}", OriginController.class);
        return pc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Origin purchaseOrderStatus = (Origin) value;//Hacer cast -> (Origin) ya que solo recibe el objeto pero no se especifica de que
        return purchaseOrderStatus.getOriginId().toString();
    }
}
