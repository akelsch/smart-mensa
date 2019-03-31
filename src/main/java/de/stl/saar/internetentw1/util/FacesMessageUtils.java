package de.stl.saar.internetentw1.util;

import lombok.experimental.UtilityClass;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

/**
 * Werkzeugklasse mit Methoden zum Arbeiten mit {@link FacesMessage}.
 */
@UtilityClass
public final class FacesMessageUtils {

    // TODO i18n
    private static final String INFO_SEVERITY_SUMMARY = "Info";
    private static final String ERROR_SEVERITY_SUMMARY = "Fehler";

    /**
     * Fügt dem aktuellen JSF-Kontext eine globale Nachricht mit dem Level
     * "Info" hinzu.
     *
     * @param message Der Inhalt der Nachricht
     */
    public static void addGlobalInfoMessage(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(SEVERITY_INFO, INFO_SEVERITY_SUMMARY, message));
    }

    /**
     * Fügt dem aktuellen JSF-Kontext eine globale Nachricht mit dem Level
     * "Error" hinzu.
     *
     * @param message Der Inhalt der Nachricht
     */
    public static void addGlobalErrorMessage(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(SEVERITY_ERROR, ERROR_SEVERITY_SUMMARY, message));
    }
}
