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

import Controllers.FinalCustomerSaleUnitsController;
import Entity.FinalCustomerSaleUnits;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(forClass = FinalCustomerSaleUnits.class)
public class FinalCustomerSaleUnitsConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        FinalCustomerSaleUnitsController fc = context.getApplication().evaluateExpressionGet(context, "#{finalCustomerSaleUnitsController}", FinalCustomerSaleUnitsController.class);
        return fc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       FinalCustomerSaleUnits finalCustomerSaleUnits = (FinalCustomerSaleUnits)value;
        return finalCustomerSaleUnits.getCustomerSaleUnitId().toString();
    }
    
}
