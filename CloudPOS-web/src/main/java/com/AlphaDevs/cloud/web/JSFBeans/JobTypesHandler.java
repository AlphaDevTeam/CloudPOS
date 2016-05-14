
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.JobTypes;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.SessionBean.JobTypesController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author AlphaDevs
 */
@Named(value = "jobTypesHandler")
@ManagedBean
@ViewScoped
public class JobTypesHandler {
    @EJB
    private LoggerController loggerController;
    @EJB
    private JobTypesController jobTypesController;
    private JobTypes current;

    @PostConstruct
    public void init() {
        if (current == null) {
            current = new JobTypes();
        }
    }
    public JobTypesHandler() {
    }

    public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
    }

    public JobTypesController getJobTypesController() {
        return jobTypesController;
    }

    public void setJobTypesController(JobTypesController jobTypesController) {
        this.jobTypesController = jobTypesController;
    }

    public JobTypes getCurrent() {
        return current;
    }

    public void setCurrent(JobTypes current) {
        this.current = current;
    }
    
    public List<JobTypes> getListAll(){
        return getJobTypesController().findAll();
    }
    
    public String saveObject(){
        Logger logger = EntityHelper.createLogger("Create Job Type", getCurrent().getJobTypeCode(), TransactionTypes.JOB_TYPE);
        getCurrent().setLogger(logger);
        getLoggerController().create(logger);
        getJobTypesController().create(getCurrent());
        return "Home";
    } 
}
