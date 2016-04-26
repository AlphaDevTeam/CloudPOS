
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Systems;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.SystemsController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */


@ManagedBean
@RequestScoped
public class SystemsHandler extends SuperHandler{
    
    @EJB
    private SystemsController systemsController;
    
    private Systems current;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Successful",  "Your message: " + message) );
        context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
    }
    @PostConstruct
    public void init() {
       SessionDataHelper.getSessionMap().get(AlphaConstant.SESSION_HEADR);
        if(SessionDataHelper.getSessionMap().get(AlphaConstant.SESSION_HEADR) != null){
            current = ((List<Systems>) SessionDataHelper.getSessionMap().get(AlphaConstant.SESSION_HEADR)).get(0);
        }else{
            current = new Systems();
        }
    }
  
    public SystemsController getSystemsController() {
        return systemsController;
    }

    public void setSystemsController(SystemsController systemsController) {
        this.systemsController = systemsController;
    }

    public Systems getCurrent() {
        return current;
    }

    public void setCurrent(Systems current) {
        this.current = current;
    }
    
    public List<Systems> getList(){
        return getSystemsController().findAll();
    }
    
    public String createSystem(){
        getSystemsController().create(current);
        return "Home";
    }
    
    
}
