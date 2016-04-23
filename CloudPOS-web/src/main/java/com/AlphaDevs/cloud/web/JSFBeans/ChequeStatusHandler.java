
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Enums.ChequeStatus;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mihindu Karunarathne
 */
@ManagedBean
@ViewScoped
public class ChequeStatusHandler {


    public ChequeStatusHandler() {
    }
    
    
    public ChequeStatus[] getChequeStatus(){
        return ChequeStatus.values();
    }
}
