
package com.AlphaDevs.cloud.web.Enums;

import java.util.ArrayList;
import java.util.Arrays;
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

public enum TransferTypes {
    
    TRANSFER("Stick Transfer");
    
    private final String typeName;
    private TransferTypes(String type){
        typeName = type;
    }
    
    public String getType(){
        return typeName;
    }
    
    public TransferTypes[] getList(){
       return TransferTypes.values();
    }
    
}
