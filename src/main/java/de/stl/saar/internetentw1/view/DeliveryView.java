package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.model.Room;
import de.stl.saar.internetentw1.util.FacesMessageUtils;
import de.stl.saar.internetentw1.util.FlashUtils;
import de.stl.saar.internetentw1.util.ResourceBundleUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.text.MessageFormat;
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
        orderedDishes = FlashUtils.getList("orderedDishes", Dish.class);
    }

    /**
     * Überprüft, ob der Benutzer vorher eine Bestellung getätigt hat. Falls er
     * das nicht getan hat, wird er zurück zur Speisekarte weitergeleitet.
     *
     * @return Eine Weiterleitung zu {@code order.xhtml} oder nichts
     */
    public String checkIfHasOrdered() {
        return orderedDishes.isEmpty() ? "order" : "";
    }

    /**
     * Schaltet die Felder des Lieferdaten Formulars ab und informiert den Benutzer
     * mit einer Meldung über die erfolgreiche Bestellung.
     */
    public void order() {
        isDisabled = true;

        String message = MessageFormat.format(ResourceBundleUtils.getMessage("orderInfo"), room);
        FacesMessageUtils.addGlobalInfoMessage(message);
    }
}
