/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Controllers.ProgramVisitsController;
import Entity.ProgramVisits;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Usuario
 */
@FacesConverter(forClass = ProgramVisits.class)
public class ProgramVisitsConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ProgramVisitsController pVC = context.getApplication().evaluateExpressionGet(context, "#{programVisitsController}", ProgramVisitsController.class);
        return pVC.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ProgramVisits programVisits = (ProgramVisits) value;
        return programVisits.getProgVisitId().toString();
    }
    
}
