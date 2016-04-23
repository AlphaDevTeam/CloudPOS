
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Job;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.Stock;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.SessionBean.JobController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
@RequestScoped
public class JobHandler extends SuperHandler{
    @EJB
    private JobController jobController;
    
    
    private Job current;
    
    public JobHandler() {
        if(current == null){
            current = new Job();
        }
    }

    public JobController getJobController() {
        return jobController;
    }

    public void setJobController(JobController jobController) {
        this.jobController = jobController;
    }

    public Job getCurrent() {
        return current;
    }

    public void setCurrent(Job current) {
        this.current = current;
    }
    
    public List<Job> getList(){
        return jobController.findAll();
    }
    
    public String createJob(){
        
        Logger Log = EntityHelper.createLogger("Create Job", current.getJobNumber(), TransactionTypes.JOB);
        loggerController.create(Log);
        current.setLogger(Log);
        jobController.create(current);
        current = new Job();
        return "Home";
    }
}
