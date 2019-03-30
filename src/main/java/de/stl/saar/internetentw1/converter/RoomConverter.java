package de.stl.saar.internetentw1.converter;

import de.stl.saar.internetentw1.model.Room;
import de.stl.saar.internetentw1.util.FacesContextUtils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(forClass = Room.class)
public class RoomConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.length() != 4) {
            FacesContextUtils.addErrorMessage(FacesContextUtils.getMessage("roomNumberError"), component);
            return null;
        }
        Room room = new Room();
        room.setBuilding(Integer.parseInt(value.substring(0, 1)));
        room.setFloor(Integer.parseInt(value.substring(1, 2)));
        room.setRoom(Integer.parseInt(value.substring(2, 4)));
        return room;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
}
