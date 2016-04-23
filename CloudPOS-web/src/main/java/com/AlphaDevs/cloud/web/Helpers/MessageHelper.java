package com.AlphaDevs.cloud.web.Helpers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * Alpha Development Team ( www.AlphaDevs.com )
 *
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @since 2012/06/16
 * @see
 *
 */
public class MessageHelper {

    public static void addSuccessMessage(String msg) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static void addErrorMessage(String msgTile, String msgError) {
        if (FacesContext.getCurrentInstance() != null && FacesContext.getCurrentInstance().getExternalContext() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgTile, msgError);
            FacesContext.getCurrentInstance().addMessage("ErrMsg", facesMsg);
        }
    }
}
