/*
* StoreRanksConverter.java
*
* Version 1.0
*
* Fecha 06/10/2017
*
* Jesús de la Cruz
 */
package Converters;

import Controllers.StoreRanksController;
import Entity.StoreRanks;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = StoreRanks.class)
public class StoreRanksConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, 
            String value) {
        StoreRanksController src = context.getApplication()
                .evaluateExpressionGet(context, "#{storeRanksController}",
                        StoreRanksController.class);
        return src.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, 
            Object value) {
        StoreRanks sr = (StoreRanks) value;
        return sr.getStoreRankId().toString();
    }
    
}
