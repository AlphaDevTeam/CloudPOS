
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Enums.JOBStatus;
import com.AlphaDevs.cloud.web.Enums.PropertieType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mihindu Karunarathne
 */
@ManagedBean
@ViewScoped
public class JobStatusHandler {

    /**
     * Creates a new instance of PropertieTypesHandler
     */
    public JobStatusHandler() {
    }
    
    public JOBStatus[] getJobStatusList(){
        return JOBStatus.values();
    }
    
}
