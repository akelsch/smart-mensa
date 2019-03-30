package de.stl.saar.internetentw1.view;

import de.stl.saar.internetentw1.util.FacesContextUtils;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@ManagedBean
@SessionScoped
public class I18nView implements Serializable {

    @Getter
    @Setter
    private String activeLocale;

    @Getter
    private Map<String, Locale> availableLocales;

    @PostConstruct
    public void init() {
        activeLocale = FacesContextUtils.getDefaultLocale().getLanguage();
        availableLocales = new LinkedHashMap<>();
        availableLocales.put("Deutsch", Locale.GERMAN);
        availableLocales.put("English", Locale.ENGLISH);
    }

    /**
     * Ändert die Sprache der JSF View bei Änderung im XHTML.
     *
     * @param event Das Event, dass beim Ändern der Sprache ausgelöst wird
     */
    public void onLocaleChange(ValueChangeEvent event) {
        Locale newLocale = new Locale(event.getNewValue().toString());

        if (availableLocales.containsValue(newLocale)) {
            FacesContextUtils.setLocale(newLocale);
        }
    }
}
