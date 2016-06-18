

package com.AlphaDevs.cloud.web.Convertors;

import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Job;
import com.AlphaDevs.cloud.web.SessionBean.ItemsController;
import com.AlphaDevs.cloud.web.SessionBean.JobController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@RequestScoped
public class JobConvertor implements Converter
{
    @EJB
    private JobController jobController;
    
    public JobConvertor() {
    }

    public JobController getJobController() {
        return jobController;
    }

    public void setJobController(JobController jobController) {
        this.jobController = jobController;
    }
    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
       if(value.isEmpty()){
            return new Job();
        }else{
            return getJobController().find(Long.valueOf(value));
        }
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
       
        if(value == null || "null".equals(value.toString()) || (value instanceof Job) ){
            return "";
        }else{
            if(value instanceof Job  && ((Job)value).getId() != null){
                return ((Job) value).getId().toString();
            }
        }
        return "";
    }
    
    
}
