package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.repository.UserRepository;
import de.stl.saar.internetentw1.util.FlashUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class ManageUsersView implements Serializable {

    @Getter
    private Iterable<User> users;

    @Getter
    @Setter
    private User selectedUser;

    private final UserRepository userRepository;

    @Inject
    public ManageUsersView(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        users = userRepository.findAll();
    }

    /**
     * Leitet auf die Seite zum Ändern eines Benutzers um, falls ein Benutzer in
     * der PrimeFaces DataTable ausgewählt wurde.
     *
     * @return Ein Redirect auf {@code create_user.xhtml}, falls in der Tabelle
     * eine Auswahl getroffen wurde
     */
    public String changeSelectedUser() {
        if (selectedUser != null) {
            FlashUtils.putObject("user", selectedUser);
            return "create_user?faces-redirect=true";
        }

        return "";
    }

    /**
     * Löscht einen Benutzer aus der Datenbank, falls in der PrimeFaces DataTable
     * eine Auswahl getroffen wurde.
     */
    public void deleteSelectedUser() {
        if (selectedUser != null) {
            userRepository.delete(selectedUser);
            users = userRepository.findAll();
        }
    }
}
