/*
* Nombre de la clase: CitiesConverter
*
* Información de la versión: version 3
*
* Fecha: 05/10/2017
*
* Copyright: Eduardo Alejandro Martínez Melo
*/

package Converters;

import Controllers.CitiesController;
import Entity.Cities;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Cities.class)
public class CitiesConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        CitiesController cc = context.getApplication()
                .evaluateExpressionGet(context, "#{citiesController}"
                        , CitiesController.class);
        return cc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Cities c = (Cities) value;
        return c.getCityId().toString();
    }
    
}
