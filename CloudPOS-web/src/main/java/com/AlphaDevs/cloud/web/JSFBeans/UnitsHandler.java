package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.Units;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import com.AlphaDevs.cloud.web.SessionBean.UnitsController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne Alpha Development Team (Pvt) Ltd
 *
 */
@ManagedBean
@ViewScoped
public class UnitsHandler {

    @EJB
    private LoggerController loggerController;
    @EJB
    private UnitsController unitsController;

    private String RDStatus;
    private Units current;

    public UnitsHandler() {
        if (current == null) {
            current = new Units();
        }
    }

    public UnitsController getUnitsController() {
        return unitsController;
    }

    public void setUnitsController(UnitsController unitsController) {
        this.unitsController = unitsController;
    }

    public Units getCurrent() {
        return current;
    }

    public void setCurrent(Units current) {
        this.current = current;
    }

    public List<Units> getList() {
        return unitsController.findAll();
    }

    public String persistUOM() {
        Logger Log = EntityHelper.createLogger("Create UOM", current.getUnitCode(), TransactionTypes.UOM);
        loggerController.create(Log);
        current.setLogger(Log);
        unitsController.create(current);
        
        if (getRDStatus() != null) {
            System.out.println(getRDStatus());
            return getRDStatus();
        } else {

            return "Home";
        }

    }

    /**
     * @return the RDStatus
     */
    public String getRDStatus() {
        return RDStatus;
    }

    /**
     * @param RDStatus the RDStatus to set
     */
    public void setRDStatus(String RDStatus) {
        System.out.println(RDStatus);
        this.RDStatus = RDStatus;
        System.out.println(this.RDStatus);
    }

}
