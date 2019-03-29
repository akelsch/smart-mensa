package de.stl.saar.internetentw1.view;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.Locale;

@ManagedBean
@SessionScoped
public class I18nBean implements Serializable {

    @Getter
    @Setter
    private String localeCode;

    @PostConstruct
    public void initialize() {
        localeCode = Locale.GERMAN.getLanguage();
    }

    public void onLocaleCodeChange(ValueChangeEvent valueChangeEvent) {
    }
}
