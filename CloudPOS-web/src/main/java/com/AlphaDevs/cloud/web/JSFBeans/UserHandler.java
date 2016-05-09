package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.AccessRequest;
import com.AlphaDevs.cloud.web.Entities.CashBookBalance;
import com.AlphaDevs.cloud.web.Entities.Company;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.Systems;
import com.AlphaDevs.cloud.web.Entities.Terminal;
import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Enums.UserLevel;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.CashBookBalanceController;
import com.AlphaDevs.cloud.web.SessionBean.CompanyController;
import com.AlphaDevs.cloud.web.SessionBean.LocationController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import com.AlphaDevs.cloud.web.SessionBean.SystemsController;
import com.AlphaDevs.cloud.web.SessionBean.TerminalController;
import com.AlphaDevs.cloud.web.SessionBean.UserController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mihindu Gajaba Karunarathne Alpha Development Team (Pvt) Ltd
 *
 */
@ManagedBean
@ViewScoped
public class UserHandler {

    @EJB
    private CashBookBalanceController cashBookBalanceController;
    @EJB
    private CompanyController companyController;
    @EJB
    private SystemsController systemsController;
    @EJB
    private LocationController locationController;
    @EJB
    private LoggerController loggerController;
    @EJB
    private TerminalController terminalController;
    @EJB
    private UserController userController;

    private UserX current;

    @PostConstruct
    public void init() {
        if (current == null) {
            current = new UserX();
        }
    }

    public UserHandler() {
    }

    public CashBookBalanceController getCashBookBalanceController() {
        return cashBookBalanceController;
    }

    public void setCashBookBalanceController(CashBookBalanceController cashBookBalanceController) {
        this.cashBookBalanceController = cashBookBalanceController;
    }
    
    public String prePop() {
        getCurrent().setUserName("PreMihinduchanged");
        System.out.println("Pre : " + getCurrent().getUserName());
        return "done pre";
    }

    public String postPop() {
        getCurrent().setUserName("Mihindu");
        System.out.println("Post : " + getCurrent().getUserName());
        return "done post";
    }

    public CompanyController getCompanyController() {
        return companyController;
    }

    public void setCompanyController(CompanyController companyController) {
        this.companyController = companyController;
    }

    public UserHandler(UserX current) {
        this.current = current;
    }

    public UserX getCurrent() {
        return current;
    }

    public void setCurrent(UserX current) {
        this.current = current;
    }

    public TerminalController getTerminalController() {
        return terminalController;
    }

    public void setTerminalController(TerminalController terminalController) {
        this.terminalController = terminalController;
    }

    public UserController getUserController() {
        return userController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public SystemsController getSystemsController() {
        return systemsController;
    }

    public void setSystemsController(SystemsController systemsController) {
        this.systemsController = systemsController;
    }

    public LocationController getLocationController() {
        return locationController;
    }

    public void setLocationController(LocationController locationController) {
        this.locationController = locationController;
    }

    public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
    }

    public String demoLogin() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            Logger log = new Logger("Demo Request", dateFormat.parse(dateFormat.format(cal.getTime())), TransactionTypes.OTHER, InfoGrabber.getTerminalString());
            getLoggerController().create(log);
            Company company = new Company("Demo", "Demo");
            UserX user = new UserX("Demo", "Demo", UserLevel.DEMO, log, company);
            Terminal terminal = new Terminal("Demo Terminal", "Demo Terminal", InfoGrabber.getTerminalString(), log);
            Location location = new Location("Demo Location", "DEMO", log);
            Systems sys = new Systems("Demo Header", true, true, SessionDataHelper.getSystems() != null ? SessionDataHelper.getSystems().getDateTimeformat() : AlphaConstant.yyyy_MM_dd, company, location);

            getCompanyController().create(company);
            getUserController().create(user);
            getTerminalController().create(terminal);
            getLocationController().create(location);
            getSystemsController().create(sys);
            MessageHelper.addSuccessMessage("Demo Account Requeted");
        } catch (ParseException ex) {
            MessageHelper.addErrorMessage("Demo Account Requeted Error", "Error");
            java.util.logging.Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Home";
    }

    public String demoLogin(AccessRequest request) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            Logger log = new Logger("Demo Request", dateFormat.parse(dateFormat.format(cal.getTime())), TransactionTypes.OTHER, request.getRequestNumber());
            getLoggerController().create(log);
            Company company = new Company(request.getCompanyName().toUpperCase(), request.getCompanyName());
            UserX user = new UserX(request.getContactEmail(), "Demo", UserLevel.DEMO, log, company);
            Terminal terminal = new Terminal("Demo Terminal (" + request.getRequestNumber() + ")", "Demo Terminal (" + request.getRequestNumber() + ")", InfoGrabber.getTerminalString(), log);
            Location location = new Location("Demo Location(" + request.getRequestNumber() + ")", "DEMO-" + request.getRequestNumber(), log, company);
            log.setTerminal(terminal);
            Systems sys = new Systems(request.getCompanyName().toUpperCase(), false, false, SessionDataHelper.getSystems() != null ? SessionDataHelper.getSystems().getDateTimeformat() : AlphaConstant.yyyy_MM_dd, company, location);
            getCompanyController().create(company);
            getUserController().create(user);
            getTerminalController().create(terminal);
            getLocationController().create(location);
            //Adding Location Balance 
            persistCashbookBalance(location);
            getSystemsController().create(sys);
            MessageHelper.addSuccessMessage("Demo Account Created - " + request.getRequestNumber());
        } catch (ParseException ex) {
            MessageHelper.addErrorMessage("Demo Account Request Error", "Error");
            java.util.logging.Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Home";
    }

    private void persistCashbookBalance(Location location) {
        List<CashBookBalance> locationCashbookBalances = EntityHelper.createCashbookBalances(location);
        for (CashBookBalance locationCashbookBalance : locationCashbookBalances) {
            getCashBookBalanceController().create(locationCashbookBalance);
        }
        MessageHelper.addSuccessMessage("New Location Added!");
    }

    public String validateUser() {
        List<UserX> LoggedUser;
        LoggedUser = userController.ValidateUser(current);

        if (!LoggedUser.isEmpty() && validateTerminal(LoggedUser.get(0)) && LoggedUser.get(0) != null) {
            MessageHelper.addSuccessMessage("Welcome " + LoggedUser.get(0).getUserName());
            FacesContext context = FacesContext.getCurrentInstance();
            SessionDataHelper.addToSessionMap(AlphaConstant.SESSION_USER, LoggedUser.get(0));
            //Set Session Info
            Location myLocation;
            myLocation = locationController.find(1L);

            SessionDataHelper.addToSessionMap(AlphaConstant.SESSION_LOCATION, myLocation);
            SessionDataHelper.addToSessionMap(AlphaConstant.SESSION_HEADR, getSystemsController().getSystemsHeaders(LoggedUser.get(0).getAssociatedCompany()));

            System.out.println("User Set & Redirecting.,,.");
            return "Home";
        } else {
            MessageHelper.addErrorMessage("User not Found", "User Not Found");
            return "Login";
        }

    }

    public boolean validateTerminal(UserX user) {
        Terminal term = new Terminal("System", "System", InfoGrabber.getTerminalString());
        List<Terminal> LoggedTerminal;
        LoggedTerminal = terminalController.ValidateTerminal(term);
        System.out.println("Trying to Login Using Terminal : " + term);
        System.out.println("Terminal String : " + InfoGrabber.getTerminalString());

        if (UserLevel.SUPERADMIN.equals(user.getUserLevel())) {

            if (LoggedTerminal.isEmpty()) {
                terminalController.create(term);
                LoggedTerminal.add(term);
            }
            SessionDataHelper.addToSessionMap(AlphaConstant.SESSION_TERMINAL, LoggedTerminal.get(0));
            return true;
        } else if (UserLevel.DEMO.equals(user.getUserLevel())) {
            if (LoggedTerminal.isEmpty()) {
                Terminal demoTerminal = new Terminal("DEMO-TERMINAL", "Demo Terminal (" + user.getUserName().toUpperCase() + ")", InfoGrabber.getTerminalString());
                terminalController.create(demoTerminal);
                LoggedTerminal.add(demoTerminal);
            }
            SessionDataHelper.addToSessionMap(AlphaConstant.SESSION_TERMINAL, LoggedTerminal.get(0));
            return true;
        } else if (!LoggedTerminal.isEmpty()) {
            SessionDataHelper.addToSessionMap(AlphaConstant.SESSION_TERMINAL, LoggedTerminal.get(0));
            return true;
        } else {
            MessageHelper.addErrorMessage("UnAuthorized", "UnAuthorized Access");
            return false;

        }

    }

    public List<UserX> getList() {
        return getUserController().findAll();
    }

    public UserLevel[] getUserLevelList() {
        return UserLevel.values();
    }

    public String persisitUser() {

        Logger Log = EntityHelper.createLogger("Create User", current.getPassWord(), TransactionTypes.USER);

        loggerController.create(Log);
        current.setLogger(Log);
        MessageHelper.addSuccessMessage("User Added!");
        userController.create(current);
        return "Home";

    }

    public String redirectToCompany() {
        return "ToRegisterNewCompany";
    }

    public List<UserX> getCompanyUsers() {

        List<UserX> relatedCompanyUserList = new ArrayList<UserX>();

        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        if (sessionMap != null && sessionMap.containsKey(AlphaConstant.SESSION_USER)
                && sessionMap.get(AlphaConstant.SESSION_USER) != null) {
            relatedCompanyUserList = userController.getLoggedAdminUsers((UserX) sessionMap.get(AlphaConstant.SESSION_USER));
        }

        return relatedCompanyUserList;
    }

}
