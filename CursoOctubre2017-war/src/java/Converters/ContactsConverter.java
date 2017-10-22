/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Controllers.ContactsController;
import Entity.Contacts;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Gerardo Cardenas
 * Clase: ContactsConverter
 * Version 1.0
 * Fecha: 06/10/2017
 * Copyrigth
 */
@FacesConverter(forClass = Contacts.class )
public class ContactsConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ContactsController cc = context.getApplication().evaluateExpressionGet(context, "#{contactsController}", ContactsController.class);
        return cc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Contacts contacts = (Contacts) value;
        return contacts.getContactId().toString();
    }
    
}
