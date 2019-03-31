package de.stl.saar.internetentw1.util;

import lombok.experimental.UtilityClass;

import javax.faces.context.FacesContext;
import java.util.Locale;

/**
 * Werkzeugklasse mit Methoden zum Arbeiten mit {@link Locale}.
 */
@UtilityClass
public final class LocaleUtils {

    /**
     * Liefert {@code default-locale} aus {@code faces-config.xml}.
     *
     * @return Die Standardsprache als {@link Locale}
     */
    public static Locale getDefaultLocale() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getDefaultLocale();
    }

    /**
     * Setzt die JSF-View Sprache auf die angegebene {@link Locale}.
     *
     * @param locale Die neue Sprache
     */
    public static void setLocale(Locale locale) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getViewRoot().setLocale(locale);
    }
}
