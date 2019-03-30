package de.stl.saar.internetentw1.validator;

import de.stl.saar.internetentw1.model.Room;
import de.stl.saar.internetentw1.util.FacesContextUtils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;

@FacesValidator("roomValidator")
public class RoomValidator implements Validator<Room> {
    @Override
    public void validate(FacesContext context, UIComponent component, Room room) {
        if (room.getBuilding() < 1 || room.getBuilding() > 8) {
            FacesContextUtils.addErrorMessage(FacesContextUtils.getMessage("buildingError"), component);
        }
        if (room.getFloor() < 0 || room.getFloor() > 2) {
            FacesContextUtils.addErrorMessage(FacesContextUtils.getMessage("floorError"), component);
        }
        if (room.getRoom() < 1 || room.getRoom() > 20) {
            FacesContextUtils.addErrorMessage(FacesContextUtils.getMessage("roomError"), component);
        }
    }
}
