
package com.AlphaDevs.cloud.web.Helpers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * Alpha Development Team ( www.AlphaDevs.com )
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @since 2012/06/16
 * @see 
 * 
 */
@ManagedBean
@RequestScoped
public class RedirectHelper {

   
    public RedirectHelper() {
    }
    
    public String RedirectToHome(){
        return "Home";
    }
}
