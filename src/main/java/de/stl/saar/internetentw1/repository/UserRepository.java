package de.stl.saar.internetentw1.repository;

import de.stl.saar.internetentw1.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring Data Repository für User Objekte.
 *
 * @see User
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
