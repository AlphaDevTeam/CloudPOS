

package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Address;
import com.AlphaDevs.cloud.web.SessionBean.AddressController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@SessionScoped
public class AddressHandler 
{
    @EJB
    private AddressController addressController;
    
    private Address current;

    public AddressHandler() {
    }

    public AddressController getAddressController() {
        return addressController;
    }

    public void setAddressController(AddressController addressController) {
        this.addressController = addressController;
    }

    public Address getCurrent() {
        return current;
    }

    public void setCurrent(Address current) {
        this.current = current;
    }

}
