package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.repository.UserRepository;
import de.stl.saar.internetentw1.util.FacesContextUtils;
import lombok.Getter;

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

    private final UserRepository userRepository;

    @Inject
    public ManageUsersView(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        users = userRepository.findAll();
    }

    public void deleteSelectedUser() {
        Long id = Long.parseLong(FacesContextUtils.getRequestParameterValue("userId"));
        userRepository.deleteById(id);
        users = userRepository.findAll();
    }
}
