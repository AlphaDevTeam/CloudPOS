
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

public enum AdjestmentTypes {
    
    OPENING_STOCK("Opening Stock"),
    STOCK_ADJ("Stock Adjestment"),
    TESTING("Testing");
    
    private String fieldName;
    private AdjestmentTypes(String feild){
        fieldName = feild;
    }
    
    public String getDocumentName(){
        return fieldName;
    }
    
    public ArrayList<AdjestmentTypes> getList(){
       return new ArrayList<AdjestmentTypes>(Arrays.asList(AdjestmentTypes.values()));
    }
    
}
