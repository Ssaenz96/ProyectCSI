/*
* Nombre de la clase: ChannelsConverter
*
* Información de la versión: version 3
*
* Fecha: 05/10/2017
*
* Copyright: Eduardo Alejandro Martínez Melo
*/

package Converters;

import Controllers.ChannelsController;
import Entity.Channels;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

@FacesConverter(forClass = Channels.class)
public class ChannelsConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ChannelsController cc = context.getApplication()
                .evaluateExpressionGet(context, "#{channelsController}"
                        , ChannelsController.class);
        return cc.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Channels c = (Channels) value;
        return c.getChannelId().toString();
    }
    
}
