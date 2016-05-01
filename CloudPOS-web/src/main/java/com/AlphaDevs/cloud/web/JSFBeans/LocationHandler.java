package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.CashBookBalance;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Enums.BillStatus;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.CashBookBalanceController;
import com.AlphaDevs.cloud.web.SessionBean.LocationController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 *
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@ManagedBean
@SessionScoped
public class LocationHandler {

    @EJB
    private CashBookBalanceController cashBookBalanceController;
    @EJB
    private LoggerController loggerController;
    @EJB
    private LocationController locationController;

    private String RDStatus;
    private Location current;

    @PostConstruct
    public void init() {
        if (current == null) {
            current = new Location();
        }
    }

    public LocationHandler() {

    }

    public CashBookBalanceController getCashBookBalanceController() {
        return cashBookBalanceController;
    }

    public void setCashBookBalanceController(CashBookBalanceController cashBookBalanceController) {
        this.cashBookBalanceController = cashBookBalanceController;
    }

    public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
    }

    public LocationController getLocationController() {
        return locationController;
    }

    public void setLocationController(LocationController locationController) {
        this.locationController = locationController;
    }

    public List<Location> getListAll() {
        return locationController.findAll();
    }

    public List<Location> getLocationList() {
        return getLocationController().findLocations(SessionDataHelper.getLoggedCompany(true));
    }

    public Location getCurrent() {
        return current;
    }

    public void setCurrent(Location current) {
        this.current = current;
    }

    public String persistLocation() {
        Logger Log = EntityHelper.createLogger("Create Location", getCurrent().getCode(), TransactionTypes.LOCATION);
        getLoggerController().create(Log);
        getCurrent().setLogger(Log);

        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX logUser = (UserX) sessionMap.get(AlphaConstant.SESSION_USER);

        if (getRDStatus() != null) {
            System.out.println(RDStatus);
            getCurrent().setRelatedCompany(logUser.getAssociatedCompany());
            locationController.create(current);

            MessageHelper.addSuccessMessage("New Location Added!");
            CashBookBalance BalanceTax = new CashBookBalance(current, 0, 0, BillStatus.TAX);
            CashBookBalance BalanceNonTax = new CashBookBalance(current, 0, 0, BillStatus.NON_TAX);

            getCashBookBalanceController().create(BalanceTax);
            getCashBookBalanceController().create(BalanceNonTax);
            current = new Location();
            return getRDStatus();
        } else {
            getCurrent().setRelatedCompany(logUser.getAssociatedCompany());
            locationController.create(current);

            MessageHelper.addSuccessMessage("New Location Added!");
            CashBookBalance BalanceTax = new CashBookBalance(current, 0, 0, BillStatus.TAX);
            CashBookBalance BalanceNonTax = new CashBookBalance(current, 0, 0, BillStatus.NON_TAX);

            cashBookBalanceController.create(BalanceTax);
            cashBookBalanceController.create(BalanceNonTax);
            current = new Location();
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
        this.RDStatus = RDStatus;
    }

}
