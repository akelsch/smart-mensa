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
public class I18nBean implements Serializable {

    @Getter
    @Setter
    private String localeCode;

    @Getter
    private Map<String, Locale> locales;

    @PostConstruct
    public void init() {
        locales = new LinkedHashMap<>();
        locales.put("Deutsch", Locale.GERMAN);
        locales.put("English", Locale.ENGLISH);
        localeCode = Locale.GERMAN.getLanguage();
    }

    public void onLocaleCodeChange(ValueChangeEvent valueChangeEvent) {
        String newLocaleValue = valueChangeEvent.getNewValue().toString();

        for (Map.Entry<String, Locale> entry : locales.entrySet()) {

            if (entry.getValue().getLanguage().equals(newLocaleValue)) {
                FacesContextUtils.setLocale(entry.getValue());
            }
        }
    }
}
