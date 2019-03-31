package de.stl.saar.internetentw1.validator;

import de.stl.saar.internetentw1.model.Room;
import de.stl.saar.internetentw1.util.ResourceBundleUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator, der folgende Kriterien für ein {@link Room} Objekt prüft:
 * <ol>
 * <li>Gebäude-Nummer zwischen 1 und 8</li>
 * <li>Etagen-Nummer zwischen 0 und 2</li>
 * <li>Raum-Nummer zwischen 1 und 20</li>
 * </ol>
 */
@FacesValidator
public class RoomValidator implements Validator<Room> {

    @Override
    public void validate(FacesContext context, UIComponent component, Room value) throws ValidatorException {
        int building = value.getBuilding();
        int floor = value.getFloor();
        int room = value.getRoom();

        if (building < 1 || building > 8) {
            throw new ValidatorException(new FacesMessage(ResourceBundleUtils.getMessage("buildingError")));
        }

        if (floor < 0 || floor > 2) {
            throw new ValidatorException(new FacesMessage(ResourceBundleUtils.getMessage("floorError")));
        }

        if (room < 1 || room > 20) {
            throw new ValidatorException(new FacesMessage(ResourceBundleUtils.getMessage("roomError")));
        }
    }
}
