
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Enums.BillStatus;
import com.AlphaDevs.cloud.web.Enums.TransferTypes;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mihindu Karunarathne
 */
@ManagedBean
@ViewScoped
public class StockTransferTypeHandler {

    /**
     * Creates a new instance of BillStatusHandler
     */
    public StockTransferTypeHandler() {
    }
    
    public TransferTypes[] getTransferTypes(){
        return TransferTypes.values();
    }
    
}
