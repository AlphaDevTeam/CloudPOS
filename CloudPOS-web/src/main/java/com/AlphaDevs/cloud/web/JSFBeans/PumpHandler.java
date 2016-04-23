
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.Pump;
import com.AlphaDevs.cloud.web.Entities.Units;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.SessionBean.ItemsController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import com.AlphaDevs.cloud.web.SessionBean.PumpController;
import com.AlphaDevs.cloud.web.SessionBean.UnitsController;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

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
@SessionScoped
public class PumpHandler {
    @EJB
    private LoggerController loggerController;
    
    @EJB
    private PumpController pumpController;
    
    private Pump current;
    
    public PumpHandler() {
        if (current == null){
            current = new Pump();
        }
    }
    
    public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
    }

    
    public PumpController getPumpController() {
        return pumpController;
    }

    public void setPumpController(PumpController pumpController) {
        this.pumpController = pumpController;
    }

    public Pump getCurrent() {
        return current;
    }

    public void setCurrent(Pump current) {
        this.current = current;
    }
    
    public List<Pump> getList(){
        return getPumpController().findAll();
    }
    
    public List<Pump> getPumpListAccordingToLocation(){
        if(getCurrent() != null && getCurrent().getRelatedLocation() != null ){
            return getPumpController().findReadingByPump(getCurrent().getRelatedLocation());
        }else{
            return new ArrayList<Pump>();
        }
        
    }
    
    public String savePump(){
        Logger Log = EntityHelper.createLogger("Created Pump", current.getPumpCode(), TransactionTypes.PUMP);
        loggerController.create(Log);
        current.setLogger(Log);
        MessageHelper.addSuccessMessage("Pump Added!");
        getPumpController().create(current);
        return "Home";
    }
    
    public void savePumpAndStay(){
        Logger Log = EntityHelper.createLogger("Created Pump", current.getPumpCode(), TransactionTypes.PUMP);
        loggerController.create(Log);
        current.setLogger(Log);
        MessageHelper.addSuccessMessage("Pump Added!");
        getPumpController().create(current);
        setCurrent(new Pump());
    }
    
    public String createPumpCreateLink(){
        return "CreatePump";
    }
    public String createPumpListLink(){
        return "ListPump";
    }
    

}
