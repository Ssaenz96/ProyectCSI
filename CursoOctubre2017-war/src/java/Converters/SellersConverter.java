/*
 */
package Converters;

import Controllers.SellersController;
import Entity.Sellers;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Gerardo Cardenas
 * Clase: SellersConverter
 * Version 1.0
 * Fecha: 06/10/2017
 * Copyrigth
 */
@FacesConverter(forClass = Sellers.class)
public class SellersConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        SellersController sc = context.getApplication().evaluateExpressionGet(context,"#{sellersController}", SellersController.class);
        return sc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Sellers sellers = (Sellers) value;
        return  sellers.getSellerId().toString();
    }
    
}
