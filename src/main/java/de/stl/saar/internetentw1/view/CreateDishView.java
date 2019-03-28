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

@ManagedBean
@ViewScoped
public class CreateDishView implements Serializable {

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
        Object o = FacesContextUtils.getFlashObject("dish");
        if (o instanceof Dish) {
            Dish dish = (Dish) o;
            id = dish.getId();
            name = dish.getName();
            price = dish.getPrice();
            category = dish.getCategory();
        }
    }

    public String saveDish() {
        Dish dish = new Dish(name, price, category);
        dishRepository.save(dish.withId(id));

        return "manage_dishes";
    }
}
