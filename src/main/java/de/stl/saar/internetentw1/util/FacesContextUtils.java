package de.stl.saar.internetentw1.util;

import lombok.experimental.UtilityClass;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

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

    public static void putFlashObject(String key, Object value) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.put(key, value);
    }

    public static Object getFlashObject(String key) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();

        if (flash.containsKey(key))
            return flash.get(key);

        return null;
    }

    public static String getRequestParameterValue(String parameter) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getExternalContext().getRequestParameterMap().get(parameter);
    }
}
