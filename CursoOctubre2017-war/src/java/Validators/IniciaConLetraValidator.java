/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Usuario
 */
@FacesValidator(value = "iniciaConLetraValidator")
public class IniciaConLetraValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value != null){
            if(value instanceof String){
                String valor = (String)value;
                if(!valor.isEmpty()){
                    if(!valor.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ].*")){
                        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Product category code must start with a letter", ""));
                    }
                }
            }
        }
    }
}
