package com.AlphaDevs.cloud.web.Extra;

import com.AlphaDevs.cloud.web.Entities.ItemBincard;
import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.Stock;
import com.AlphaDevs.cloud.web.Enums.BillStatus;
import java.util.Date;

/**
 *
 * @author AlphaDevs
 */
public class DocumentEntityHelper {

    //Item Bincard Entry
    public static ItemBincard createItemBincardEntry(Logger logger, Location location, String description, Items item, Date relatedDate, String trnNumber, double qty, BillStatus billStatus, double stockBalance) {
        ItemBincard itemBin = new ItemBincard();
        itemBin.setDescription(description);
        itemBin.setItem(item);
        itemBin.setRelatedDate(relatedDate);
        itemBin.setTrnNumber(trnNumber);
        itemBin.setQty(qty);
        itemBin.setLocation(location);
        itemBin.setLog(logger);
        itemBin.setBillStat(billStatus);
        itemBin.setBalance(stockBalance);
        return itemBin;
    }

}
