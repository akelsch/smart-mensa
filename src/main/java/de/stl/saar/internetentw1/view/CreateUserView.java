package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.repository.UserRepository;
import de.stl.saar.internetentw1.util.FacesMessageUtils;
import de.stl.saar.internetentw1.util.FlashUtils;
import de.stl.saar.internetentw1.util.ResourceBundleUtils;
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
    private String oldPassword;

    @Getter
    @Setter
    private String newPassword;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private Role role;

    @Getter
    @Setter
    private boolean resetPassword;

    @Getter
    private boolean isAdmin;

    @Getter
    private boolean isHimself;

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
            oldPassword = u.getPassword();
            newPassword = oldPassword;
            email = u.getEmail();
            role = u.getRole();
        });
    }

    /**
     * Setzt beim Laden der Seite Attribute zur Bestimmung, ob ein Benutzer ein
     * Administrator ist und ob er er selbst ist, um entsprechende Felder anzuzeigen
     * bzw. zu verstecken und wieder zur richtigen Seite zu leiten.
     *
     * @param user Der eingeloggte Benutzer aus LoginView
     */
    public void onload(User user) {
        isAdmin = user.getRole() == Role.ADMIN;
        isHimself = user.getId() == id;
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
        resetPassword = true;
        newPassword = RandomStringUtils.randomAlphanumeric(10);
    }

    /**
     * Speichert einen Benutzer mit den ausgefüllten Input-Feldern in der Datenbank.
     * <p>
     * Existiert die ID des Benutzers bereits, erfolgt ein Update. Ansonsten
     * erhält der Benutzer eine neue ID.
     * <p>
     * Schaltet außerdem die "Passwort beim nächsten Login ändern" Funktion ab,
     * sobald eine Änderung am Passwort Feld vorgenommen wurde.
     *
     * @return Ein Redirect zurück auf die tabellarische Übersicht der Benutzer oder
     * ein Redirect ins Hauptmenü, falls man sein eigenes Profil bearbeitet hat
     */
    public String saveUser() {
        boolean passwordChanged = oldPassword != null && !newPassword.equals(oldPassword);
        if (passwordChanged) {
            resetPassword = false;
        }

        // Speichern
        User user = new User(id, username, newPassword, email, role, resetPassword);
        userRepository.save(user);

        // Weiterleiten
        if (isHimself && passwordChanged) {
            FacesMessageUtils.keepMessagesAfterRedirect();
            FacesMessageUtils.addGlobalInfoMessage(ResourceBundleUtils.getMessage("passwordChangedInfo"));
            return "index?faces-redirect=true";
        }

        if (!isHimself) {
            return "manage_users?faces-redirect=true";
        }

        return "menu?faces-redirect=true";
    }
}
