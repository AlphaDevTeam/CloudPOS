
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Company;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.SystemNumbers;
import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.Extra.NumberFormatUtil;
import com.AlphaDevs.cloud.web.SessionBean.SystemNumbersController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@RequestScoped
public class SystemNumbersHandler extends SuperHandler{
    
    @EJB
    private SystemNumbersController systemNumbersController;
    
    private SystemNumbers current;
    
    public SystemNumbersHandler() {
        if(current == null ){
            current = new SystemNumbers();
        }
    }

    public SystemNumbersController getSystemNumbersController() {
        return systemNumbersController;
    }

    public void setSystemNumbersController(SystemNumbersController systemNumbersController) {
        this.systemNumbersController = systemNumbersController;
    }

    public SystemNumbers getCurrent() {
        return current;
    }

    public void setCurrent(SystemNumbers current) {
        this.current = current;
    }
    
    public List<SystemNumbers> getList(){
        return systemNumbersController.findAll();
    }
    
    public String createSystemNumber(){
        getSystemNumbersController().create(getCurrent());
        return "Home";
    }
    
    public List<SystemNumbers> getSpecific(Location relatedLocation , Company relatedCompany,Document relatedDocumnet){
        return getSystemNumbersController().findSpecific(relatedCompany, relatedLocation,relatedDocumnet);
    }
    
}
