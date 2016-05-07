

package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.PurchaseReturnDetails;
import com.AlphaDevs.cloud.web.SessionBean.PurchaseReturnDetailsController;
import java.util.List;
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
public class PurchaseReturnDetailsHandler 
{
    @EJB
    private PurchaseReturnDetailsController purchaseReturnDetailsController;
    
    private PurchaseReturnDetails current;
    
    
    
    public PurchaseReturnDetailsHandler() 
    {
        if(current == null)
        {
            current = new PurchaseReturnDetails();
        }
    }

    public PurchaseReturnDetails getCurrent() {
        return current;
    }

    public void setCurrent(PurchaseReturnDetails current) {
        this.current = current;
    }
    
    public List<PurchaseReturnDetails> getList()
    {
        return purchaseReturnDetailsController.findAll();
    }
    

}
