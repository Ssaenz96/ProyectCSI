/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Controllers.StoresController;
import Entity.Stores;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * StoresConverter 
 * Version 2 
 * Fecha 06/10/2017
 * @author Thania López
 */

@FacesConverter(forClass = Stores.class)
public class StoresConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        StoresController sc = context.getApplication().evaluateExpressionGet(context,"#{storesController}", StoresController.class);
        return sc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Stores stores = (Stores) value;
        return stores.getStoreId().toString();
    }
    
}
