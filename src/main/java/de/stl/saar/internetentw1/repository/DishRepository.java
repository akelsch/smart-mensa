package de.stl.saar.internetentw1.repository;

import de.stl.saar.internetentw1.model.Dish;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Spring Data Repository für Dish Objekte.
 *
 * @see Dish
 */
public interface DishRepository extends CrudRepository<Dish, Long> {

    Optional<Dish> findByName(String name);
}
