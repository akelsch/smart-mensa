package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Category;
import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.repository.DishRepository;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

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

    @Inject
    public DishView(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public String saveDish() {
        dishRepository.save(new Dish(name,price,Category.valueOf(category),""));
        return "manage_dishes";
    }
}
