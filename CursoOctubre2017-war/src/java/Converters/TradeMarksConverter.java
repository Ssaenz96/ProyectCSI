/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Controllers.TradeMarksController;
import Entity.TradeMarks;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Usuario7
 */
@FacesConverter(forClass = TradeMarks.class)
public class TradeMarksConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        TradeMarksController tc =context.getApplication().evaluateExpressionGet(context, "#{tradeMarksController}", TradeMarksController.class);
        return tc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        TradeMarks tradeMarks = (TradeMarks)value;
        return tradeMarks.getTradeMarkId().toString();
    }
    

    
}
