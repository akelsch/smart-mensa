package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.repository.DishRepository;
import de.stl.saar.internetentw1.util.FlashUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class ManageDishesView implements Serializable {

    @Getter
    private Iterable<Dish> dishes;

    @Getter
    @Setter
    private Dish selectedDish;

    private final DishRepository dishRepository;

    @Inject
    public ManageDishesView(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @PostConstruct
    public void init() {
        dishes = dishRepository.findAll();
    }

    /**
     * Leitet auf die Seite zum Ändern eines Gerichts um, falls ein Gericht in
     * der PrimeFaces DataTable ausgewählt wurde.
     *
     * @return Ein Redirect auf {@code create_dish.xhtml}, falls in der Tabelle
     * eine Auswahl getroffen wurde
     */
    public String changeSelectedDish() {
        if (selectedDish != null) {
            FlashUtils.putObject("dish", selectedDish);
            return "create_dish?faces-redirect=true";
        }

        return "";
    }

    /**
     * Löscht ein Gericht aus der Datenbank, falls in der PrimeFaces DataTable
     * eine Auswahl getroffen wurde.
     */
    public void deleteSelectedDish() {
        if (selectedDish != null) {
            dishRepository.delete(selectedDish);
            dishes = dishRepository.findAll();
        }
    }
}
