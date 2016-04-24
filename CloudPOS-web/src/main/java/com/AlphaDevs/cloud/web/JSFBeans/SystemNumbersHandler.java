
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Company;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.SystemNumbers;
import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.SessionBean.SystemNumbersController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@ViewScoped
public class SystemNumbersHandler {
    
    @EJB
    private SystemNumbersController systemNumbersController;
    
    private SystemNumbers current;
    private boolean editMode;
    
    @PostConstruct
    public void init(){
        System.out.println("@PostConstruct : SystemNumbersHandler");
        if(current == null ){
            current = new SystemNumbers();
        }
        setEditMode(false);
    }
    
    public SystemNumbersHandler() {
     
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
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
    public String editSystemNumber(){
        getSystemNumbersController().edit(getCurrent());
        return "Home";
    }
    
    public String preapareList(SystemNumbers systemNumbers) {
        setCurrent(systemNumbers);
        setEditMode(true);
        return "Update";        
    }
    
    public List<SystemNumbers> getSpecific(Location relatedLocation , Company relatedCompany,Document relatedDocumnet){
        return getSystemNumbersController().findSpecific(relatedCompany, relatedLocation,relatedDocumnet);
    }
    
}
