package de.stl.saar.internetentw1.converter;

import de.stl.saar.internetentw1.model.Room;
import de.stl.saar.internetentw1.util.ResourceBundleUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Converter, der einen String zu einem {@link Room} konvertiert.
 * <p>
 * Der Converter setzt einen als Integer parsbaren String der Länge Vier voraus.
 */
@FacesConverter(forClass = Room.class)
public class RoomConverter implements Converter<Room> {

    @Override
    public Room getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        if (value.length() != 4) {
            throw new ConverterException(new FacesMessage(ResourceBundleUtils.getMessage("roomNumberError")));
        }

        return new Room(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Room value) {
        return value.toString();
    }
}
