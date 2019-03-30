package de.stl.saar.internetentw1.converter;

import de.stl.saar.internetentw1.model.Room;
import de.stl.saar.internetentw1.util.FacesContextUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Room.class)
public class RoomConverter implements Converter<Room> {

    @Override
    public Room getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        if (value.length() != 4) {
            throw new ConverterException(new FacesMessage(FacesContextUtils.getMessage("roomNumberError")));
        }

        return new Room(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Room value) {
        return value.toString();
    }
}
