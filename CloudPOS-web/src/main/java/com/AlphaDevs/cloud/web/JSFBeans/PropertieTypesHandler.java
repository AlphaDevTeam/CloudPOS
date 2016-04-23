
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Enums.PropertieType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mihindu Karunarathne
 */
@ManagedBean
@ViewScoped
public class PropertieTypesHandler {

    /**
     * Creates a new instance of PropertieTypesHandler
     */
    public PropertieTypesHandler() {
    }
    
    public PropertieType[] getPropertieTypes(){
        return PropertieType.values();
    }
    
}
