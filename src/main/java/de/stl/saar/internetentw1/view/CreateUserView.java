package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.repository.UserRepository;
import de.stl.saar.internetentw1.util.FlashUtils;
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

    @Getter
    private boolean isOwnProfile;

    private final UserRepository userRepository;

    @Inject
    public CreateUserView(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        Optional<User> user = FlashUtils.getObject("user", User.class);
        user.ifPresent(u -> {
            id = u.getId();
            username = u.getUsername();
            password = u.getPassword();
            email = u.getEmail();
            role = u.getRole();
        });

        isOwnProfile = FlashUtils.getObject("isOwnProfile", Boolean.class).orElse(false);
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
     * Generiert ein zufälliges Passwort der Länge 10 bestehend aus den Zeichen
     * a-z, A-Z und 0-9.
     */
    public void generateRandomPassword() {
        password = RandomStringUtils.randomAlphanumeric(10);
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

        if (isOwnProfile) {
            return "menu?faces-redirect=true";
        }
        return "manage_users?faces-redirect=true";
    }
}
