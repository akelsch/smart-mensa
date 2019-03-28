package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.repository.UserRepository;
import de.stl.saar.internetentw1.util.FacesContextUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Optional;

@ManagedBean
@ViewScoped
public class CreateUserView implements Serializable {

    @Getter
    private long id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private Role role;

    private final UserRepository userRepository;

    @Inject
    public CreateUserView(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        Optional<User> user = FacesContextUtils.getFlashObject("user", User.class);
        user.ifPresent(u -> {
            id = u.getId();
            username = u.getUsername();
            password = u.getPassword();
            email = u.getEmail();
            role = u.getRole();
        });
    }

    /**
     * Hilfsmethode, die alle Rollen aus dem {@link Role} Enum liefert
     * (nützlich für eine Auswahlliste im XHTML).
     *
     * @return Ein Array mit allen möglichen Werten aus dem Enum
     */
    public Role[] getRoles() {
        return Role.values();
    }

    /**
     * Speichert einen Benutzer mit den ausgefüllten Input-Feldern in der Datenbank.
     * <p>
     * Existiert die ID des Benutzers bereits, erfolgt ein Update. Ansonsten
     * erhält der Benutzer eine neue ID.
     *
     * @return Ein Redirect zurück auf die tabellarische Übersicht der Benutzer
     */
    public String saveUser() {
        User user = new User(username, password, email, role);
        userRepository.save(user.withId(id));

        return "manage_users?faces-redirect=true";
    }

    public void newPassword() {
        password = RandomStringUtils.randomAlphanumeric(10);
    }
}
