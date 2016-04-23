
package com.AlphaDevs.cloud.web.Helpers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

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
public class DialogHelper {

    public DialogHelper() {
        
    }

    public void paymentDetailsWindow() {  
        RequestContext.getCurrentInstance().openDialog("selectCar");  
    }  
  
    public void onPayemntChosen(SelectEvent event) {  
        MessageHelper.addSuccessMessage("Done ");
    }  
    
}
