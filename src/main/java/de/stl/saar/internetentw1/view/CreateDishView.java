package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Category;
import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.repository.DishRepository;
import de.stl.saar.internetentw1.util.FacesContextUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Optional;

@ManagedBean
@ViewScoped
public class CreateDishView implements Serializable {

    @Getter
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private double price;

    @Getter
    @Setter
    private Category category;

    private final DishRepository dishRepository;

    @Inject
    public CreateDishView(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @PostConstruct
    public void init() {
        Optional<Dish> dish = FacesContextUtils.getFlashObject("dish", Dish.class);
        dish.ifPresent(d -> {
            id = d.getId();
            name = d.getName();
            price = d.getPrice();
            category = d.getCategory();
        });
    }

    /**
     * Hilfsmethode, die alle Kategorien aus dem {@link Category} Enum liefert
     * (nützlich für eine Auswahlliste im XHTML).
     *
     * @return Ein Array mit allen möglichen Werten aus dem Enum
     */
    public Category[] getCategories() {
        return Category.values();
    }

    /**
     * Speichert ein Gericht mit den ausgefüllten Input-Feldern in der Datenbank.
     * <p>
     * Existiert die ID des Gerichts bereits, erfolgt ein Update. Ansonsten
     * erhält das Gericht eine neue ID.
     *
     * @return Ein Redirect zurück auf die tabellarische Übersicht der Gerichte
     */
    public String saveDish() {
        Dish dish = new Dish(name, price, category);
        dishRepository.save(dish.withId(id));

        return "manage_dishes?faces-redirect=true";
    }
}
