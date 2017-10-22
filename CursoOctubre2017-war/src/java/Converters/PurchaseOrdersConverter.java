/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Controllers.PurchaseOrdersController;
import Entity.PurchaseOrders;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = PurchaseOrders.class)

/*
 * PurchaseOrdersConverter
 *
 * Version 1.0
 *
 * 13:36 pm 6/10/17
 *
 * Luis García.
 * 
 */

public class PurchaseOrdersConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        PurchaseOrdersController pc = context.getApplication().evaluateExpressionGet(context, "#{ purchaseOrdersController }", PurchaseOrdersController.class);
        return pc.findById(Long.parseLong(value));    
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        PurchaseOrders purchaseOrders = (PurchaseOrders) value;
        return purchaseOrders.getPOId().toString();
    }
    
}
