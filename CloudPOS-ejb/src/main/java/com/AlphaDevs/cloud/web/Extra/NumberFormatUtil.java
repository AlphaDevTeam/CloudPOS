
package com.AlphaDevs.cloud.web.Extra;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

public class NumberFormatUtil {
    
    private static BigDecimal round(BigDecimal value, int places,RoundingMode roundingMode) {
        BigDecimal returnValue = BigDecimal.ZERO;
        if (places < 0) throw new IllegalArgumentException();
        if(roundingMode != null && value != null){
            returnValue = value.setScale(places, roundingMode);
        }
        return returnValue;
    }
    
    public static BigDecimal formatSystemNumbers(BigDecimal value) {        
        return round(value,0, RoundingMode.UNNECESSARY);
    }
    
    public static BigDecimal roundToTwo(BigDecimal value) {        
        return round(value,2, RoundingMode.HALF_UP);
    }

    
}
