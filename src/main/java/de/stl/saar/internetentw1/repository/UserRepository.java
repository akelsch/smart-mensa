package de.stl.saar.internetentw1.repository;

import de.stl.saar.internetentw1.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Spring Data Repository für User Objekte.
 *
 * @see User
 */
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
