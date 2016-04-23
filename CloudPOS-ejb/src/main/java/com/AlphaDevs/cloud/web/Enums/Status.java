
package com.AlphaDevs.cloud.web.Enums;

import java.util.List;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */
public enum Status {
    
    PENDING("Pending"),
    ACTIVE("Active"),
    DECLINE("Decline"),
    UNDER_REVIEW("Under Review");
  
    private String status;
    private Status(String status){
        this.status = status;
    }
    
    public String getStatus(){
        return this.status;
    }
}
