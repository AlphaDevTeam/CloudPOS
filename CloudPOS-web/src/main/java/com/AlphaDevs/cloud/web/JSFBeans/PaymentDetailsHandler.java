

package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.PaymentDetails;
import com.AlphaDevs.cloud.web.SessionBean.PaymentDetailsController;
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
public class PaymentDetailsHandler {
    @EJB
    private PaymentDetailsController paymentDetailsController;
    
    private PaymentDetails current;
    
            
    public PaymentDetailsHandler() {
        if(current == null){
            current = new PaymentDetails();
        }
    }

    public PaymentDetailsController getPaymentDetailsController() {
        return paymentDetailsController;
    }

    public void setPaymentDetailsController(PaymentDetailsController paymentDetailsController) {
        this.paymentDetailsController = paymentDetailsController;
    }

    public PaymentDetails getCurrent() {
        return current;
    }

    public void setCurrent(PaymentDetails current) {
        this.current = current;
    }
    
    public List<PaymentDetails> getList(){
        return getPaymentDetailsController().findAll();
    }

}
