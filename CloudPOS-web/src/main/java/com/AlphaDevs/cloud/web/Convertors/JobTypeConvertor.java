package com.AlphaDevs.cloud.web.Convertors;

import com.AlphaDevs.cloud.web.Entities.JobTypes;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.SessionBean.JobTypesController;
import com.AlphaDevs.cloud.web.SessionBean.LocationController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Mihindu Gajaba Karunarathne Alpha Development Team (Pvt) Ltd
 *
 */
@ManagedBean
@RequestScoped
public class JobTypeConvertor implements Converter {

    @EJB
    private JobTypesController jobTypesController;

    /**
     * Creates a new instance of LocationConvertor
     */
    public JobTypeConvertor() {
    }

    public JobTypesController getJobTypesController() {
        return jobTypesController;
    }

    public void setJobTypesController(JobTypesController jobTypesController) {
        this.jobTypesController = jobTypesController;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.isEmpty()) {
            return null;
        } else {
            return getJobTypesController().find(Long.valueOf(value));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || "null".equals(value.toString())) {
            return "";
        } else {
            return ((JobTypes) value).getId().toString();
        }

    }

}
