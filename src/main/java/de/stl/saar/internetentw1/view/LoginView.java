package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.repository.UserRepository;
import de.stl.saar.internetentw1.util.FacesContextUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import java.io.IOException;
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

    public void clearForm(ComponentSystemEvent event) {
        username = "";
        password = "";
        user = null;
    }

    public String login() {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            this.user = user.get();
            return "menu?faces-redirect=true";
        }

        FacesContextUtils.addGlobalErrorMessage("Authentifizierung fehlgeschlagen!");

        return "";
    }

    public void checkIfLoggedIn() throws IOException {
        if (user == null) {
            FacesContextUtils.keepMessages();
            FacesContextUtils.addGlobalErrorMessage("Nicht eingeloggt oder Session abgelaufen!");
            FacesContextUtils.redirectTo("index.xhtml");
        }
    }

    public void checkIfAdmin() throws IOException {
        if (user.getRole() != Role.ADMIN) {
            FacesContextUtils.keepMessages();
            FacesContextUtils.addGlobalInfoMessage("Dieser Bereich ist nur für Admins ;)");
            FacesContextUtils.redirectTo("menu.xhtml");
        }
    }
}
