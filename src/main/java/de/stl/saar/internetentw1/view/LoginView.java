package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.repository.UserRepository;
import de.stl.saar.internetentw1.util.FacesMessageUtils;
import de.stl.saar.internetentw1.util.ResourceBundleUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
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

        FacesMessageUtils.addGlobalErrorMessage(ResourceBundleUtils.getMessage("authenticationError"));

        return "";
    }

    public String checkIfLoggedIn() {
        if (user == null) {
            FacesMessageUtils.addGlobalErrorMessage(ResourceBundleUtils.getMessage("sessionError"));
            return "index";
        }

        return "";
    }

    public String checkIfAdmin() {
        if (user.getRole() != Role.ADMIN) {
            FacesMessageUtils.addGlobalInfoMessage(ResourceBundleUtils.getMessage("adminError"));
            return "menu";
        }

        return "";
    }
}
