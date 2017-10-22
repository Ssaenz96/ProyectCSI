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

import Controllers.ProductPackagesController;
import Entity.ProductPackages;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = ProductPackages.class)
public class ProductPackagesConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ProductPackagesController pp = context.getApplication().evaluateExpressionGet(context, "#{productPackagesController}", ProductPackagesController.class);
        return pp.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ProductPackages productPackages = (ProductPackages)value;
        return productPackages.getProductPackId().toString();
    }
    
}
