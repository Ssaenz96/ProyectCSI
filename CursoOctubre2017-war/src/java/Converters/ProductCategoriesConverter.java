/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Controllers.ProductCategoriesController;
import Entity.ProductCategories;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *ProductCategoriesConverter
 * @author Luis Amaury
 * 06/10/2017
 * Version 1.0
 * 
 */
@FacesConverter(forClass = ProductCategories.class)
public class ProductCategoriesConverter implements Converter{
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ProductCategoriesController pc = context.getApplication().evaluateExpressionGet(context, "#{productCategoriesController}", ProductCategoriesController.class);
        return pc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ProductCategories purchaseOrderStatus = (ProductCategories) value;//Hacer cast -> (ProductCategories) ya que solo recibe el objeto pero no se especifica de que
        return purchaseOrderStatus.getProdCategoryId().toString();
    }
}
