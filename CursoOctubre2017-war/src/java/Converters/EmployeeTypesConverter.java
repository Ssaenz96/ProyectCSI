/*
* EmployeeTypesConverter.java
*
* Version 1.0
*
* Fecha 06/10/2017
*
* Jesús de la Cruz
 */
package Converters;

import Controllers.EmployeeTypesController;
import Entity.EmployeeTypes;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = EmployeeTypes.class)
public class EmployeeTypesConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        EmployeeTypesController etc = context.getApplication()
                .evaluateExpressionGet(context, "#{employeeTypesController}",
                        EmployeeTypesController.class);
        return etc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        EmployeeTypes et = (EmployeeTypes) value;
        return et.getEmployeeTypeId().toString();
    }
    
}
