package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.Terminal;
import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import com.AlphaDevs.cloud.web.SessionBean.TerminalController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mihindu Gajaba Karunarathne Alpha Development Team (Pvt) Ltd
 *
 */
@ManagedBean
@RequestScoped
public class TerminalHandler {

    @EJB
    private LoggerController loggerController;
    
    @EJB
    private TerminalController terminalController;

    private Terminal current;

    /**
     * Creates a new instance of TerminalHandler
     */
    public TerminalHandler() {
        if (current == null) {
            current = new Terminal();
        }
    }

    public Terminal getCurrent() {
        return current;
    }

    public void setCurrent(Terminal current) {
        this.current = current;
    }

    public String validateTerminal() {
        Terminal term = new Terminal("", "", InfoGrabber.getTerminalString());
        List<Terminal> LoggedTerminal;
        LoggedTerminal = terminalController.ValidateTerminal(term);
        if (!LoggedTerminal.isEmpty()) {
            MessageHelper.addSuccessMessage("Welcome");
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("Terminal", LoggedTerminal.get(0));
            return "";
        } else {
            MessageHelper.addErrorMessage("UnAuthorized", "UnAuthorized Access");
            return "Login";
        }

    }

    public void validateTerminalSession() {
        Terminal term = new Terminal("", "", InfoGrabber.getTerminalString());
        List<Terminal> LoggedTerminal;
        LoggedTerminal = terminalController.ValidateTerminal(term);
        if (!LoggedTerminal.isEmpty()) {
            MessageHelper.addSuccessMessage("Welcome");
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("Terminal", LoggedTerminal.get(0));

        } else {
            MessageHelper.addErrorMessage("UnAuthorized", "UnAuthorized Access");
        }

    }

    public String persisitTerminal() {
        
        Logger log = EntityHelper.createLogger("Create Terminal", current.getTerminalCode(), TransactionTypes.TERMINAL);
        
        loggerController.create(log);
        current.setLogger(log);
        terminalController.create(current);
        return "Home";

    }
     public List<Terminal> getAllTerminals(){
        return terminalController.findAll();
     
     }

}
