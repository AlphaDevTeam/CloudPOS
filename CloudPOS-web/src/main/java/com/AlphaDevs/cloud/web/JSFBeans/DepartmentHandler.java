package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Department;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Enums.PageMode;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.SessionBean.DepartmentController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author AlphaDevs
 */
@ViewScoped
@ManagedBean
public class DepartmentHandler {
    @EJB
    private LoggerController loggerController;
    @EJB
    private DepartmentController departmentController;
    private Department current;
    private PageMode pageMode;

    public DepartmentHandler() {
    }

    @PostConstruct
    public void init() {
        if (current == null) {
            current = new Department();
        }
    }

    public DepartmentController getDepartmentController() {
        return departmentController;
    }

    public void setDepartmentController(DepartmentController departmentController) {
        this.departmentController = departmentController;
    }

    public Department getCurrent() {
        return current;
    }

    public PageMode getPageMode() {
        return pageMode;
    }

    public void setPageMode(PageMode pageMode) {
        this.pageMode = pageMode;
    }

    public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
    }
    
    public void setCurrent(Department current) {
        this.current = current;
    }
    
    public List<Department> getListAll(){
        return getDepartmentController().findAll();
    }

    public String createDepartment() {
        //Creating Logger
        Logger log = EntityHelper.createLogger("Create Department", getCurrent().getDepartmentCode(), TransactionTypes.DEPARTMENT);
        getLoggerController().create(log);
        getCurrent().setRelatedLogger(log);
        getDepartmentController().create(getCurrent());
        return "Home";
    }
    
    public String updateDepartment() {
        //Creating Logger
        Logger log = EntityHelper.createLogger("Department Update", getCurrent().getDepartmentCode(), TransactionTypes.DEPARTMENT);
        getLoggerController().create(log);
        getCurrent().setRelatedLogger(log);
        getDepartmentController().edit(getCurrent());
        return "Home";
    }

}
