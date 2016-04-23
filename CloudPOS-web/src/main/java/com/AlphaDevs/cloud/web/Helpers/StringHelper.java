
package com.AlphaDevs.cloud.web.Helpers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@RequestScoped
public class StringHelper {

    public StringHelper() {
    }
    
    public String convertToUppercase(String stringValue){
        return stringValue.toUpperCase();
    }
}
