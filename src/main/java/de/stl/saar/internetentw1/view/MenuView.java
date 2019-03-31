package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.util.FlashUtils;
import lombok.Getter;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Optional;

@ManagedBean
@SessionScoped
public class MenuView implements Serializable {

    @Getter
    private User user;

    @PostConstruct
    public void init() {
        Optional<User> user = FlashUtils.getObject("user", User.class);
        user.ifPresent(u -> this.user = u);
    }

    public String changeOwnProfile() {
        if (user != null) {
            FlashUtils.putObject("user", user);
            FlashUtils.putObject("isOwnProfile", true);
            return "create_user?faces-redirect=true";
        }

        return "";
    }
}
