package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.repository.UserRepository;
import de.stl.saar.internetentw1.util.FacesMessageUtils;
import de.stl.saar.internetentw1.util.FlashUtils;
import de.stl.saar.internetentw1.util.ResourceBundleUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Optional;

@ManagedBean
@SessionScoped
public class LoginView implements Serializable {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    private User user;

    private final UserRepository userRepository;

    @Inject
    public LoginView(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Löscht beim Laden der Seite alle Login-Formular Felder und loggt den
     * Benutzer wieder aus.
     */
    public void onload() {
        username = "";
        password = "";
        user = null;
    }

    /**
     * Loggt einen Benutzer ein, falls er in der Datenbank existiert und das
     * Passwort mit dem im Formular eingegebenen Passwort übereinstimmt.
     *
     * @return Eine Weiterleitung ins Hauptmenü oder eine Fehlermeldung
     */
    public String login() {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            this.user = user.get();
            return "menu?faces-redirect=true";
        }

        FacesMessageUtils.addGlobalErrorMessage(ResourceBundleUtils.getMessage("authenticationError"));

        return "";
    }

    /**
     * Überprüft, ob der Benutzer eingeloggt ist. Falls nicht, wird er auf die
     * Startseite weitergeleitet und erhält dort eine Fehlermeldung.
     *
     * @return Eine Weiterleitung zu {@code index.xhtml} oder nichts
     */
    public String checkIfLoggedIn() {
        if (user == null) {
            FacesMessageUtils.addGlobalErrorMessage(ResourceBundleUtils.getMessage("sessionError"));
            return "index";
        }

        return "";
    }

    /**
     * Überprüft, ob ein eingeloggter Benutzer sein Passwort ändern muss. Falls
     * ja, wird er auf die Seite zum Ändern des Profils weitergeleitet und erhält
     * dort eine Fehlermeldung.
     *
     * @return Eine Weiterleitung zu {@code create_user.xhtml} oder nichts
     */
    public String checkIfHasToChangePassword() {
        if (user.isResetPassword()) {
            FlashUtils.putObject("user", user);
            FacesMessageUtils.addGlobalErrorMessage("Passwort wechseln biddi!");
            return "create_user";
        }

        return "";
    }

    /**
     * Überprüft, ob ein eingeloggter Benutzer ein Administrator ist. Falls nicht,
     * wird er ins Hauptmenü weitergeleitet und erhält dort eine Fehlermeldung.
     *
     * @return Eine Weiterleitung zu {@code menu.xhtml} oder nichts
     */
    public String checkIfAdmin() {
        if (user.getRole() != Role.ADMIN) {
            FacesMessageUtils.addGlobalInfoMessage(ResourceBundleUtils.getMessage("adminError"));
            return "menu";
        }

        return "";
    }
}
