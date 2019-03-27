package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.repository.UserRepository;
import de.stl.saar.internetentw1.util.FacesContextUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Optional;

@ManagedBean
@ViewScoped
public class UserView implements Serializable {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String role;

    private final UserRepository userRepository;

    private long id;

    @Inject
    public UserView(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        username = FacesContextUtils.getRequestParameterValue("userName");
        Optional<User> optionalUser = userRepository.findByUsername(username);
        optionalUser.ifPresent(user -> {
            password = user.getPassword();
            role = user.getRole().name();
            id = user.getId();
        });
    }

    public String saveUser() {
        User user = new User(username, password, Role.valueOf(role));
        user.setId(id);
        userRepository.save(user);
        return "manage_users";
    }
}
