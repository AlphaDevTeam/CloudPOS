

package com.AlphaDevs.cloud.web.Helpers;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

public class SessionPhaseListner implements PhaseListener
{

    @Override
    public void afterPhase(PhaseEvent event) 
    {
        System.out.println("afterPhase");
    }

    @Override
    public void beforePhase(PhaseEvent event) 
    {
        System.out.println("*************** beforePhase *************");
        FacesContext context = event.getFacesContext();
        ExternalContext ext = context.getExternalContext();
        HttpSession session = (HttpSession) ext.getSession(false);
        boolean newSession = (session == null) || (session.isNew());
        boolean postback = !ext.getRequestParameterMap().isEmpty();
        boolean timedout = postback && newSession;
        System.out.println("beforePhase with : " + timedout);
    }

    @Override
    public PhaseId getPhaseId() 
    {
        return PhaseId.RESTORE_VIEW;
    }
    

}
