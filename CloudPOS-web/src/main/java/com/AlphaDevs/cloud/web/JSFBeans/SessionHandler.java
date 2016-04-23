
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Extra.GenaricKeyValue;
import com.AlphaDevs.cloud.web.Extra.Gravatar;
import com.AlphaDevs.cloud.web.Extra.GravatarDefaultImage;
import com.AlphaDevs.cloud.web.Extra.GravatarRating;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Kumuditha_2
 */
@ManagedBean
@SessionScoped
public class SessionHandler {

    private UserX sessionUser;
    private String url;
    private Map<String, Object> sessionMap;
    @PostConstruct
    public void init() {
        sessionMap = SessionDataHelper.getSessionMap();
        if (sessionMap != null && sessionMap.containsKey(AlphaConstant.SESSION_USER) && sessionMap.get(AlphaConstant.SESSION_USER) != null) {
            setSessionUser((UserX) sessionMap.get(AlphaConstant.SESSION_USER));
//            String email = "mihindu@alphadevs.com";
//            String hash = MD5Util.md5Hex(email);
//            System.out.println("hash : " + hash);
        }
    }
    
    public String getDefaultDateFormat(){
        return SessionDataHelper.getSystems() != null ? SessionDataHelper.getSystems().getDateTimeformat() : AlphaConstant.yyyy_MM_dd;
    }

//    public void loadLoggedUserImage() {
//        if (getSessionUser() != null && getSessionUser().getImgUrl() != null) {
//            setUrl(getSessionUser().getImgUrl());
//            // return getSessionUser().getImgUrl();
//        } else {
//            setUrl("Images/propic.jpg");
//            // return "Images/propic.jpg";
//        }
//
//    }

    public UserX getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(UserX sessionUser) {
        this.sessionUser = sessionUser;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        if(getSessionUser() != null && getSessionUser().getImgUrl() != null && !getSessionUser().getImgUrl().isEmpty()){
            System.out.println("get URL : " + getSessionUser().getImgUrl());
            return getSessionUser().getImgUrl();
        }else if(getSessionUser() != null){
            Gravatar gravatar = new Gravatar();
            gravatar.setSize(100);
            gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
            gravatar.setDefaultImage(GravatarDefaultImage.WAVATAR);
            String gravatarImgUrl = gravatar.getUrl(getSessionUser().getUserName());
            //byte[] jpg = gravatar.download(getSessionUser().getUserName());   
            return gravatarImgUrl;
        }else{
            return "";
        }
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    
    public String userLogout(){
        SessionDataHelper.invalidateSessionData();
        return "Login";
    }
    
    public List<GenaricKeyValue> getProcessedSessionData(){
        List<GenaricKeyValue> processedSessionData = new ArrayList<GenaricKeyValue>();
        for (String value : SessionDataHelper.getSessionMap().keySet()) {
            GenaricKeyValue entry = new GenaricKeyValue(value, SessionDataHelper.getSessionMap().get(value).toString());
            processedSessionData.add(entry);
        }
        return  processedSessionData;
    }
}
