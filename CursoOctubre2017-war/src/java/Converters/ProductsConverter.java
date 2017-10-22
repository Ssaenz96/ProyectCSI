/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Controllers.ProductsController;
import Entity.Products;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Usuario7
 */
@FacesConverter(forClass=Products.class)
public class ProductsConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ProductsController rc=context.getApplication().evaluateExpressionGet(context, "#{productsController}", ProductsController.class);
        return rc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Products products = (Products)value;
        return products.getProductId().toString();
    }
    
}
