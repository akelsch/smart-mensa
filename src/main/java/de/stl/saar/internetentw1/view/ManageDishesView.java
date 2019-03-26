package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.repository.DishRepository;
import lombok.Getter;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class ManageDishesView implements Serializable {

    @Getter
    private Iterable<Dish> dishes;

    private final DishRepository dishRepository;

    @Inject
    public ManageDishesView(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @PostConstruct
    public void init() {
        dishes = dishRepository.findAll();
    }

    public void deleteSelectedDish() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Long id = Long.parseLong(facesContext.getExternalContext().getRequestParameterMap().get("dishId"));
        dishRepository.deleteById(id);
        dishes = dishRepository.findAll();
    }
}
