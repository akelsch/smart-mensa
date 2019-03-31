package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.util.FlashUtils;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class MenuView implements Serializable {

    public String changeOwnProfile(User user) {
        FlashUtils.putObject("user", user);
        FlashUtils.putObject("isOwnProfile", true);
        return "create_user?faces-redirect=true";
    }
}
