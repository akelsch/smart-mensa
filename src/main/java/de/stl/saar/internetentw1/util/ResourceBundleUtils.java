package de.stl.saar.internetentw1.util;

import lombok.experimental.UtilityClass;

import javax.faces.context.FacesContext;
import java.util.ResourceBundle;

/**
 * Werkzeugklasse mit Methoden zum Arbeiten mit {@link ResourceBundle}.
 */
@UtilityClass
public final class ResourceBundleUtils {

    private static final String MESSAGES_RESOURCE_BUNDLE_NAME = "msg";

    /**
     * Liefert den Wert für das Property mit dem Schlüssel {@code key} aus dem
     * Resource Bundle {@code msg}.
     *
     * @param key Der Schlüssel für den gesuchten Wert
     * @return Der Wert des gesuchten Properties
     */
    public static String getMessage(String key) {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        return facesContext
                .getApplication()
                .getResourceBundle(facesContext, MESSAGES_RESOURCE_BUNDLE_NAME)
                .getString(key);
    }
}
