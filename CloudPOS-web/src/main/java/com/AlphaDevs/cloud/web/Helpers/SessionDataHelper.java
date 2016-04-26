package com.AlphaDevs.cloud.web.Helpers;

import com.AlphaDevs.cloud.web.Entities.Company;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.Systems;
import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Enums.UserLevel;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

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
public class SessionDataHelper {

    public static SessionData getSessionData() {
        FacesContext context = FacesContext.getCurrentInstance();
        SessionData sessionObject = (SessionData) context.getExternalContext().getSessionMap().get("SessionDataObject");
        return sessionObject;
    }

    public static void setSessionData(SessionData sessionData) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("SessionDataObject", sessionData);
    }

    public static void invalidateSessionData() {
        if (FacesContext.getCurrentInstance() != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().clear();
            context.getExternalContext().invalidateSession();
        }
    }

    public static void addToSessionMap(String key, Object object) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put(key, object);
    }

    public static Map<String, Object> getSessionMap() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getSessionMap();
    }

    public static Company getLoggedCompany(boolean isResolveFromUser) {
        Company loggedCompany = null;
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        if (isResolveFromUser) {
            if (sessionMap != null && sessionMap.containsKey(AlphaConstant.SESSION_USER) && sessionMap.get(AlphaConstant.SESSION_USER) != null) {
                UserX currentUser = (UserX) sessionMap.get(AlphaConstant.SESSION_USER);
                if (currentUser != null && currentUser.getAssociatedCompany() != null) {
                    loggedCompany = currentUser.getAssociatedCompany();
                }
            }
        } else {
            if (sessionMap != null && sessionMap.containsKey(AlphaConstant.SESSION_COMPANY) && sessionMap.get(AlphaConstant.SESSION_COMPANY) != null) {
                loggedCompany = (Company) sessionMap.get(AlphaConstant.SESSION_COMPANY);
            }
        }

        return loggedCompany;
    }

    //Fix Me
    public static Location getLoggedLocation() {
        Location loggedLocation = null;
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        if (sessionMap != null && sessionMap.containsKey(AlphaConstant.SESSION_LOCATION) && sessionMap.get(AlphaConstant.SESSION_LOCATION) != null) {
            loggedLocation = (Location) sessionMap.get(AlphaConstant.SESSION_LOCATION);
        }
        return loggedLocation;
    }

    public static Systems getSystems() {
        Systems loggedSystems = null;
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        if (sessionMap != null && sessionMap.containsKey(AlphaConstant.SESSION_HEADR) && sessionMap.get(AlphaConstant.SESSION_HEADR) != null) {
            List<Systems> listOfSystems = (List<Systems>) sessionMap.get(AlphaConstant.SESSION_HEADR);
            loggedSystems = listOfSystems.get(0);
        }
        return loggedSystems;
    }

    public static UserX getDummyUser() {
        return new UserX("System", "123", UserLevel.DUMMY, null, new Company("Dummy", "Dummy"));
    }

    public static Flash getFlash() {
        if (getExternalContext() != null && getExternalContext().getFlash() != null) {
            return FacesContext.getCurrentInstance().getExternalContext().getFlash();
        } else {
            return null;
        }
    }

    public static Object getFlash(String stringValue) {
        if (getFlash() != null) {
            return getFlash().get(stringValue);
        } else {
            return null;
        }
    }
    
    public static Object setFlash(String stringValue, Object object) {
        if (getFlash() != null) {
            return getFlash().put(stringValue,object);
        } else {
            return null;
        }
    }

    public static ExternalContext getExternalContext() {
        if (FacesContext.getCurrentInstance() != null && FacesContext.getCurrentInstance().getExternalContext() != null) {
            return FacesContext.getCurrentInstance().getExternalContext();
        } else {
            return null;
        }
    }

}
