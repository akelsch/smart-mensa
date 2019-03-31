package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.repository.DishRepository;
import de.stl.saar.internetentw1.util.FlashUtils;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.DragDropEvent;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class OrderView implements Serializable {

    @Getter
    private List<Dish> dishes;

    @Getter
    @Setter
    private List<Dish> droppedDishes;

    private final DishRepository dishRepository;

    @Inject
    public OrderView(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @PostConstruct
    public void init() {
        dishes = new ArrayList<>();
        droppedDishes = new ArrayList<>();
        dishRepository.findAll().forEach(dishes::add);
    }

    /**
     * Löscht ein Gericht aus der Auswahlliste und fügt es wieder dem Grid hinzu.
     *
     * @param dish Das Gericht, das gelöscht werden soll
     */
    public void deleteDroppedDish(Dish dish) {
        droppedDishes.remove(dish);
        dishes.add(dish);
    }

    /**
     * Berechnet die Summe der Preise aller ausgewählten Gerichte.
     *
     * @return Die Summe der Preise aller ausgewählten Gerichte
     */
    public double getTotal() {
        return droppedDishes.stream()
                .mapToDouble(Dish::getPrice)
                .sum();
    }

    /**
     * Leitet den Benutzer weiter zur Lieferdaten Seite und gibt dieser die
     * Liste der ausgewählten Gerichte mit.
     *
     * @return Ein Redirect auf {@code delivery.xhtml}
     */
    public String proceedToDelivery() {
        FlashUtils.putObject("orderedDishes", droppedDishes);
        return "delivery?faces-redirect=true";
    }

    /**
     * Fügt ein Gericht der Auswahlliste hinzu und entfernt es aus dem Grid.
     */
    public void onDishDrop(DragDropEvent event) {
        Dish dish = (Dish) event.getData();

        droppedDishes.add(dish);
        dishes.remove(dish);
    }
}
