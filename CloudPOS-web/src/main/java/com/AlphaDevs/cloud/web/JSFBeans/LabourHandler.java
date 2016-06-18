
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Bank;
import com.AlphaDevs.cloud.web.Entities.Labour;
import com.AlphaDevs.cloud.web.Enums.ViewMode;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.SessionBean.BankController;
import com.AlphaDevs.cloud.web.SessionBean.LabourController;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;

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
public class LabourHandler extends GenericHandler<Labour>{
    @EJB
    private LabourController labourController;
    
    @PostConstruct
    private void init(){
        setViewMode(ViewMode.CREATE);
    }
    public LabourHandler() {
       super(new Labour());
    }

    private LabourController getLabourController() {
        return labourController;
    }

    private void setLabourController(LabourController labourController) {
        this.labourController = labourController;
    }

    @Override
    public List<Labour> getListCurrent() {
        return super.getListCurrent(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Labour getSelectedCurrent() {
        return super.getSelectedCurrent(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Labour getCurrent() {
        return super.getCurrent(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getListCurrent(List<Labour> currentList) {
        super.getListCurrent(currentList); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSelectedCurrent(Labour current) {
        super.setSelectedCurrent(current); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCurrent(Labour current) {
        super.setCurrent(current); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String persistObject() {
        getLabourController().create(getCurrent());
        return getViewMode().name();
    }
    
}
