

package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.ContactInfo;
import com.AlphaDevs.cloud.web.SessionBean.ContactInfoController;
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
public class ContactInfoHandler 
{
    @EJB
    private ContactInfoController contactInfoController;
    
    private ContactInfo current;
    
    /** Creates a new instance of ContactInfoHandler */
    public ContactInfoHandler() {
    }

    public ContactInfoController getContactInfoController() {
        return contactInfoController;
    }

    public void setContactInfoController(ContactInfoController contactInfoController) {
        this.contactInfoController = contactInfoController;
    }

    public ContactInfo getCurrent() {
        return current;
    }

    public void setCurrent(ContactInfo current) {
        this.current = current;
    }
    

}
