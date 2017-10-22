/*
 * RetailSaleUnitsConverter
 *
 * Version 1.0
 *
 * 1:30am 6/10/17
 *
 * @author Carlos Alberto López Solis
 */
package Converters;

import Controllers.RetailSaleUnitsController;
import Entity.RetailSaleUnits;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=RetailSaleUnits.class)
public class RetailSaleUnitsConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        RetailSaleUnitsController rc=context.getApplication().evaluateExpressionGet(context, "#{retailSaleUnitsController}", RetailSaleUnitsController.class);
        return rc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
         RetailSaleUnits retailSaleUnits = (RetailSaleUnits)value;
        return retailSaleUnits.getRetailSUId().toString();
    }
    
    
}
    
    
