

package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.GRNPaymentDetails;
import com.AlphaDevs.cloud.web.SessionBean.GRNPaymentDetailsController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@ViewScoped
public class GRNPaymentDetailsHandler 
{
    @EJB
    private GRNPaymentDetailsController gRNPaymentDetailsController;
    
    private GRNPaymentDetails current;

    /** Creates a new instance of GRNPaymentDetailsHandler */
    public GRNPaymentDetailsHandler() 
    {
        if(current == null)
        {
            current = new GRNPaymentDetails();
        }
    }

    public GRNPaymentDetails getCurrent() {
        return current;
    }

    public void setCurrent(GRNPaymentDetails current) {
        this.current = current;
    }
    
    public String persistGRNPaymentDetails()
    {
        gRNPaymentDetailsController.create(current);
        return "CreateGRN";
    }

}
