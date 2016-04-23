
package com.AlphaDevs.cloud.web.Enums;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

public enum CreditCardReceiptStatus {
    
    RECEIVED("Received"),
    ISSUED("Issued"),
    REJECTED("Rejected");
    
    private String fieldName;
    private CreditCardReceiptStatus(String feild){
        fieldName = feild;
    }
    
    public String getReceiptStatus(){
        return fieldName;
    }
}
