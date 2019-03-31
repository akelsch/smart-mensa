package de.stl.saar.internetentw1.repository;

import de.stl.saar.internetentw1.model.Dish;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring Data Repository für Dish Objekte.
 *
 * @see Dish
 */
public interface DishRepository extends CrudRepository<Dish, Long> {
}
