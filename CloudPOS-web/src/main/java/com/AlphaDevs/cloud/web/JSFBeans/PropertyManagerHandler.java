
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.PropertyManager;
import com.AlphaDevs.cloud.web.SessionBean.PropertyManagerController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@ManagedBean
@ViewScoped
public class PropertyManagerHandler {
    
    @EJB
    private PropertyManagerController propertyManagerController;
    private PropertyManager current;
    
    @PostConstruct
    public void init(){
        if(current == null){
            current = new PropertyManager();
        }
    }
    public PropertyManagerHandler() {
        
    }
    public List<PropertyManager> getAll(){
        return propertyManagerController.findAll();
    }

    public PropertyManager getCurrent() {
        return current;
    }

    public void setCurrent(PropertyManager current) {
        this.current = current;
    }

    public PropertyManagerController getPropertyManagerController() {
        return propertyManagerController;
    }

    public void setPropertyManagerController(PropertyManagerController propertyManagerController) {
        this.propertyManagerController = propertyManagerController;
    }
    
    
    public String saveProperties(){
        System.out.println("Saving.");
        getPropertyManagerController().create(getCurrent());
        setCurrent(new PropertyManager());
        return "Home";
    }
    
    public String getPropertyManagerHandlerStatus(){
        if(propertyManagerController != null){
            return "Active";
        }else{
            return "Inactive";
        }
        
    }
    
}
