/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Controllers.PurchaseOrderStatusController;
import Entity.PurchaseOrderStatus;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *PurchaseOrderStatusConverter
 * @author Luis Amaury
 * 06/10/2017
 * Version 1.0
 * 
 */

@FacesConverter(forClass = PurchaseOrderStatus.class)
public class PurchaseOrderStatusConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        PurchaseOrderStatusController pc = context.getApplication().evaluateExpressionGet(context, "#{purchaseOrderStatusController}", PurchaseOrderStatusController.class);
        return pc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        PurchaseOrderStatus purchaseOrderStatus = (PurchaseOrderStatus) value;//Hacer cast -> (PurchaseOrderStatus) ya que solo recibe el objeto pero no se especifica de que
        return purchaseOrderStatus.getpOStatusId().toString();
    }
    
}
