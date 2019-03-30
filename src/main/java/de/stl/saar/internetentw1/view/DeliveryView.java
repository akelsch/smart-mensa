package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Room;
import de.stl.saar.internetentw1.util.FacesContextUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class DeliveryView implements Serializable {

    @Getter
    @Setter
    private Room room;

    @Getter
    @Setter
    private String name;

    public void order() {
        FacesContextUtils.addGlobalInfoMessage("Ihre Bestellung ist auf dem Weg zu " + name + " in Raum " + room);
    }
}
