/*
* Nombre de la clase: ProductPackagesConverter
*
* Información de la versión: version 1
*
* Fecha: 06/10/2017
*
* Autor: Sergio Galvan
* Copyright
*/
package Converters;

import Controllers.TaxesController;
import Entity.Taxes;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(forClass = Taxes.class)
public class TaxesConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TaxesController tc = context.getApplication().evaluateExpressionGet(context, "#{taxesController}", TaxesController.class);
        return tc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Taxes taxes = (Taxes)value;
        return taxes.getTaxId().toString();
    }
    
}
