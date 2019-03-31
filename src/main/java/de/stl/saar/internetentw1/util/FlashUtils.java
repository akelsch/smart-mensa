package de.stl.saar.internetentw1.util;

import lombok.experimental.UtilityClass;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import java.util.*;

/**
 * Werkzeugklasse mit Methoden zum Arbeiten mit {@link Flash}.
 * <p>
 * Flash ist eine {@link Map} Implementierung und erlaubt es Objekte zwischen
 * zwei {@code ViewScoped} Beans nach einem Redirect auszutauschen.
 */
@UtilityClass
public final class FlashUtils {

    /**
     * Liefert ein Objekt aus dem Flash an der Stelle {@code key}.
     *
     * @param key   Der Schlüssel des gesuchten Objekts
     * @param clazz Der Typ des gesuchten Objekts als {@link Class} zum casten
     * @param <T>   Der Typ des gesuchten Objekts
     * @return Das gesuchte Objekt in einem Optional verpackt oder ein leeres
     * Optional, falls das Objekt nicht existiert oder vom falschen Typ ist
     */
    public static <T> Optional<T> getObject(String key, Class<T> clazz) {
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
     * Ähnlich {@link #getObject(String, Class)}, liefert jedoch eine Liste
     * für eine im Flash gespeicherte Collection des gegebenen Typs.
     *
     * @param key   Der Schlüssel, an dem die gesuchte Collection gespeichert ist
     * @param clazz Der Typ der Elemente der gesuchten Collection als {@link Class} zum casten
     * @param <T>   Der Typ der Elemente der gesuchten Collection
     * @return Eine Liste mit allen Elementen der gesuchten Collection oder eine leere Liste
     */
    public static <T> List<T> getList(String key, Class<T> clazz) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        List<T> list = new ArrayList<>();

        if (flash.containsKey(key)) {
            Object o = flash.get(key);
            if (o instanceof Collection<?>) {
                Collection<?> c = (Collection<?>) o;
                c.stream()
                        .filter(clazz::isInstance)
                        .map(clazz::cast)
                        .forEach(list::add);
            }
        }

        return list;
    }

    /**
     * Fügt dem Flash ein Objekt an der Stelle {@code key} hinzu.
     *
     * @param key   Der Schlüssel des zu speichernden Objekts
     * @param value Der Wert des zu speichernden Objekts
     * @param <T>   Der Typ des zu speichernden Objekts
     */
    public static <T> void putObject(String key, T value) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.put(key, value);
    }
}
