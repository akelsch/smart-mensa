package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.model.Room;
import de.stl.saar.internetentw1.util.FacesContextUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class DeliveryView implements Serializable {

    @Getter
    @Setter
    private Room room;

    @Getter
    @Setter
    private String name;

    @Getter
    private boolean isDisabled;

    private List<Dish> orderedDishes;

    @PostConstruct
    public void init() {
        isDisabled = false;
        orderedDishes = FacesContextUtils.getFlashObjects("orderedDishes", Dish.class);
    }

    public void order() {
        isDisabled = true;
        FacesContextUtils.addGlobalInfoMessage("Ihre Bestellung ist auf dem Weg zu Raum " + room);
    }

    public String checkIfHasOrdered() {
        return orderedDishes.isEmpty() ? "order" : "";
    }
}
