package de.stl.saar.internetentw1.util;

import lombok.experimental.UtilityClass;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@UtilityClass
public final class FacesContextUtils {

    public static String getRequestParameterValue(String parameter) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getExternalContext().getRequestParameterMap().get(parameter);
    }

    public static void addGlobalErrorMessage(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", message));
    }
}
