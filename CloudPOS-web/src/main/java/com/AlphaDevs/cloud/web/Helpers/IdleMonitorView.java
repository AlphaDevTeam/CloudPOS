package com.AlphaDevs.cloud.web.Helpers;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
 
@ManagedBean
public class IdleMonitorView {
     
    public void onIdle() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                                        "No Activity Detected.", "Session will timeout soon"));
    }
 
    public void onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                        "Welcome Back", "Well, that's a long coffee break!"));
    }
    
    public void onIdleLong() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
                                        "Are you there ????.", "You will be logged out in 10 seconds"));
    }
    
    public void onIdleRedirrect() throws IOException {
//        FacesContext fc = FacesContext.getCurrentInstance();
//        NavigationHandler nh = fc.getApplication().getNavigationHandler();
//        nh.handleNavigation(fc, null, "/Home.xhtml?faces-redirect=true");
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/UI/Home.xhtml");
    }
}