/*
* PositionsConverter.java
*
* Version 1.0
*
* Fecha 06/10/2017
*
* Jesús de la Cruz
 */
package Converters;

import Controllers.PositionsController;
import Entity.Positions;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Positions.class)
public class PositionsConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, 
            String value) {
        PositionsController pc = context.getApplication()
                .evaluateExpressionGet(context, "#{positionsController}", 
                        PositionsController.class);
        return pc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, 
            Object value) {
        Positions p = (Positions) value;
        return p.getPositionId().toString();
    }
    
    
}
