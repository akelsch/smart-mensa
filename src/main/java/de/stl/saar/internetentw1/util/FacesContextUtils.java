package de.stl.saar.internetentw1.util;

import lombok.experimental.UtilityClass;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Werkzeugklasse mit Methoden zum Arbeiten mit {@link FacesContext}.
 */
@UtilityClass
public final class FacesContextUtils {

    /**
     * Fügt dem aktuellen JSF-Kontext eine globale Nachricht mit dem Level
     * "Error" hinzu.
     *
     * @param message Der Inhalt der Nachricht
     */
    public static void addGlobalErrorMessage(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", message));
    }

    /**
     * Fügt dem aktuellen JSF-Kontext eine globale Nachricht mit dem Level
     * "Info" hinzu.
     *
     * @param message Der Inhalt der Nachricht
     */
    public static void addGlobalInfoMessage(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
    }

    /**
     * Fügt dem Flash ein Objekt an der Stelle {@code key} hinzu.
     * <p>
     * Flash ist eine Map Implementierung und erlaubt es Objekte zwischen zwei
     * {@code ViewScoped} Beans nach einem Redirect auszutauschen.
     *
     * @param key   Der Schlüssel des zu speichernden Objekts
     * @param value Der Wert des zu speichernden Objekts
     * @param <T>   Der Typ des zu speichernden Objekts
     */
    public static <T> void putFlashObject(String key, T value) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.put(key, value);
    }

    /**
     * Liefert ein Objekt aus dem Flash an der Stelle {@code key}.
     *
     * @param key   Der Schlüssel des gesuchten Objekts
     * @param clazz Der Typ des gesuchten Objekts als {@link Class} zum casten
     * @param <T>   Der Typ des gesuchten Objekts
     * @return Das gesuchte Objekt in einem {@link Optional} verpackt oder ein
     * leeres {@link Optional}, falls das Objekt nicht existiert oder vom
     * falschen Typ ist
     */
    public static <T> Optional<T> getFlashObject(String key, Class<T> clazz) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();

        // Notwendig, da es sonst zu einer NullPointerException kommt
        if (flash.containsKey(key)) {
            return Optional.of(flash.get(key))
                    .filter(clazz::isInstance)
                    .map(clazz::cast);
        }

        return Optional.empty();
    }

    /**
     * Bewahrt {@link FacesMessage} nach Redirects auf.
     */
    public static void keepMessages() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
    }

    /**
     * Startet einen Redirect zu der angegebenen Seite.
     *
     * @param destination Die Ziel XHTML des Redirects
     * @throws IOException wenn der Redirect fehlschlägt
     */
    public static void redirectTo(String destination) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect(destination);
    }

    public static void setLocale(Locale locale){
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public static String getMessage(String key){
        FacesContext facesContext =FacesContext.getCurrentInstance();
        ResourceBundle resourceBundle = FacesContext.getCurrentInstance().getApplication()
                .getResourceBundle(facesContext, "msg");
        return resourceBundle.getString(key);
    }
}
