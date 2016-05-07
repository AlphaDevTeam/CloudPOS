

package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.CustomerBalance;
import com.AlphaDevs.cloud.web.Entities.Supplier;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.SessionBean.CustomerBalanceController;
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
public class CustomerBalanceHandler 
{
    @EJB
    private CustomerBalanceController customerBalanceController;
    
    private CustomerBalance current;

    /** Creates a new instance of CustomerBalanceHandler */
    public CustomerBalanceHandler() 
    {
        if(current == null)
        {
            current = new CustomerBalance();
        }
    }

    public CustomerBalance getCurrent() {
        return current;
    }

    public void setCurrent(CustomerBalance current) {
        this.current = current;
    }
    
    public double getCustomerBalance(Supplier supplier)
    {
        if(customerBalanceController.getCustomerBalance(supplier) == -1)
        {
           MessageHelper.addErrorMessage("Error ", "Supplier not Found"); 
           return -1;
        }
        else
        {
           return customerBalanceController.getCustomerBalance(supplier); 
        }
        
    }

}
