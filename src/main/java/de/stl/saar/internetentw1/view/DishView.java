package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Category;
import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.repository.DishRepository;
import de.stl.saar.internetentw1.util.FacesContextUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Optional;

@ManagedBean
@ViewScoped
public class DishView implements Serializable {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private double price;

    @Getter
    @Setter
    private String category;

    private final DishRepository dishRepository;

    private long id;

    @Inject
    public DishView(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @PostConstruct
    public void init() {
        name = FacesContextUtils.getRequestParameterValue("dishName");
        Optional<Dish> optionalDish = dishRepository.findByName(name);
        optionalDish.ifPresent(dish -> {
            price = dish.getPrice();
            category = dish.getCategory().getValue();
            id = dish.getId();
        });
    }

    public String saveDish() {
        Dish dish = new Dish(name, price, Category.valueOf(category), "");
        dish.setId(id);
        dishRepository.save(dish);
        return "manage_dishes";
    }
}
