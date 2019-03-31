package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.util.FlashUtils;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class MenuView implements Serializable {

    /*
     * Hinweis an Herr Olbertz:
     *
     * Wir hätten an dieser und anderer Stelle gerne @ManagedProperty zum injecten
     * von LoginView benutzt, sodass man nicht User aus dem XHTML mitgeben muss.
     * Leider war das Attribut jedoch immer null, obwohl wir einen Setter hatten
     * und den richtigen Bean Namen benutzt haben.
     */

    /**
     * Leitet den Benutzer weiter zur Seite zum Bearbeiten des Profils und gibt
     * dieser den eingeloggten Benutzer mit.
     *
     * @param user Der eingeloggte Benutzer aus LoginView
     * @return Ein Redirect auf {@code create_user.xhtml}
     */
    public String changeOwnProfile(User user) {
        FlashUtils.putObject("user", user);
        return "create_user?faces-redirect=true";
    }
}
