package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Company;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.SystemNumbers;
import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.SystemNumbersController;
import java.util.List;
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
public class SystemNumbersHandler {

    @EJB
    private SystemNumbersController systemNumbersController;

    private SystemNumbers current;
    private boolean editMode;
    private final String flashString = "systemNumbers";
    private final String isUpdateMode = "isUpdateMode";

    @PostConstruct
    public void init() {

        if (SessionDataHelper.getFlash(flashString) != null) {
            setCurrent((SystemNumbers) FacesContext.getCurrentInstance().getExternalContext().getFlash().get(flashString));
            setEditMode((boolean) SessionDataHelper.getFlash(isUpdateMode));
            System.out.println("@PostConstruct : SystemNumbersHandler" + getCurrent() + " - " + isEditMode());
        } else {
            setCurrent(new SystemNumbers());
            setEditMode(false);
        }

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

    public List<SystemNumbers> getList() {
        return systemNumbersController.findAll();
    }

    public String createSystemNumber() {
        getSystemNumbersController().create(getCurrent());
        return "Home";
    }

    public String editSystemNumber() {
        getSystemNumbersController().edit(getCurrent());
        return "Home";
    }

    public String preapareList(SystemNumbers systemNumbers) {
        setCurrent(systemNumbers);
        SessionDataHelper.setFlash(flashString, systemNumbers);
        SessionDataHelper.setFlash(isUpdateMode, true);
        return "Update";
    }

    public List<SystemNumbers> getSpecific(Location relatedLocation, Company relatedCompany, Document relatedDocumnet) {
        return getSystemNumbersController().findSpecific(relatedCompany, relatedLocation, relatedDocumnet);
    }

}
