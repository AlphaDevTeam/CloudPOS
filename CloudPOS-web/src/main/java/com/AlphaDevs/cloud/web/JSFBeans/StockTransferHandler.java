package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.ItemBincard;
import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.Stock;
import com.AlphaDevs.cloud.web.Entities.StockTransfer;
import com.AlphaDevs.cloud.web.Entities.SystemNumbers;
import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Enums.AdjestmentTypes;
import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.ItemBincardController;
import com.AlphaDevs.cloud.web.SessionBean.ItemsController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import com.AlphaDevs.cloud.web.SessionBean.StockTransferController;
import com.AlphaDevs.cloud.web.SessionBean.StockController;
import com.AlphaDevs.cloud.web.SessionBean.SystemNumbersController;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * Alpha Development Team (Pvt) Ltd
 *
 */
@ManagedBean
@ViewScoped
public class StockTransferHandler {
    @EJB
    private StockTransferController stockTransferController;
    @EJB
    private ItemsController itemsController;
    @EJB
    private ItemBincardController itemBincardController;
    @EJB
    private StockController stockController;
    @EJB
    private LoggerController loggerController;
    @EJB
    private SystemNumbersController systemNumbersController;
    
    private StockTransfer current;
    private SystemNumbers currentSystemNumber;
    private Document currentDocument;

    @PostConstruct
    public void init() {
        setCurrentDocument(Document.STOCK_TRANSFER);
        if (current == null) {
            current = new StockTransfer();
        }
    }

    
    public ItemBincardController getItemBincardController() {
        return itemBincardController;
    }

    public void setItemBincardController(ItemBincardController itemBincardController) {
        this.itemBincardController = itemBincardController;
    }

    public StockController getStockController() {
        return stockController;
    }

    public void setStockController(StockController stockController) {
        this.stockController = stockController;
    }

    public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
    }

    public SystemNumbersController getSystemNumbersController() {
        return systemNumbersController;
    }

    public void setSystemNumbersController(SystemNumbersController systemNumbersController) {
        this.systemNumbersController = systemNumbersController;
    }

    public SystemNumbers getCurrentSystemNumber() {
        return currentSystemNumber;
    }

    public void setCurrentSystemNumber(SystemNumbers currentSystemNumber) {
        this.currentSystemNumber = currentSystemNumber;
    }

    public Document getCurrentDocument() {
        return currentDocument;
    }

    public void setCurrentDocument(Document currentDocument) {
        this.currentDocument = currentDocument;
    }

    public StockTransferController getStockTransferController() {
        return stockTransferController;
    }

    public void setStockTransferController(StockTransferController stockTransferController) {
        this.stockTransferController = stockTransferController;
    }

    public StockTransfer getCurrent() {
        return current;
    }

    public void setCurrent(StockTransfer current) {
        this.current = current;
    }

    public ItemsController getItemsController() {
        return itemsController;
    }

    public void setItemsController(ItemsController itemsController) {
        this.itemsController = itemsController;
    }
    
    public AdjestmentTypes[] getAdjTypes() {
        return AdjestmentTypes.values();
    }

    public void handleLocationSelect(SelectEvent event) {
        getCurrent().setTransferItem(null);
        getCurrent().setTransferQty(Double.valueOf("0"));
        getCurrent().setTransferToLocation(null);
    }
    
   
    public List<Items> autoCompleteItems(String query) {
        return getItemsController().findLike(query, getCurrent().getTransferFromLocation());
    }

    public String getRefNumber() {
        currentSystemNumber = null;
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get(AlphaConstant.SESSION_USER);
        if (loggedUser != null && getCurrent().getTransferFromLocation() != null) {
            List<SystemNumbers> systemNumbers = systemNumbersController.findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getTransferFromLocation(), getCurrentDocument());
            if (systemNumbers != null && !systemNumbers.isEmpty()) {
                currentSystemNumber = systemNumbers.get(0);
                getCurrent().setRefNumber(currentSystemNumber.getDocumentSystemNo());
            }
        }
        return currentSystemNumber != null ? currentSystemNumber.getDocumentSystemNo() : "";
    }

    public void setRefNumber(String refNumber) {
        getCurrent().setRefNumber(refNumber);
    }

    public String persistStockTransfer() {
        Logger log = EntityHelper.createLogger("Stock Transfer - (" + getCurrent().getTransferFromLocation() + "-" + getCurrent().getTransferToLocation() + ")" , getCurrent().getRefNumber(), TransactionTypes.STOCK_TRANSFER);
        getLoggerController().create(log);

        //Adjesting Stock - From
        Stock stockFrom = getStockController().findSpecific(getCurrent().getTransferItem(),getCurrent().getTransferFromLocation() , SessionDataHelper.getLoggedCompany(true));
        stockFrom.setStockQty((float) (stockFrom.getStockQty() - getCurrent().getTransferQty()));
        getStockController().edit(stockFrom);

        //Adjesting Stock - From
        Stock stockTo = getStockController().findSpecific(getCurrent().getTransferItem(),getCurrent().getTransferFromLocation() , SessionDataHelper.getLoggedCompany(true));
        stockTo.setStockQty((float) (stockTo.getStockQty() + getCurrent().getTransferQty()));
        getStockController().edit(stockTo);

        //Item Bincard Entry From
        ItemBincard itemBinFrom = new ItemBincard();
        itemBinFrom.setDescription("Stock Transfer to " + getCurrent().getTransferToLocation() + "  - " + getCurrent().getRefNumber());
        itemBinFrom.setItem(getCurrent().getTransferItem());
        itemBinFrom.setRelatedDate(getCurrent().getTransferDate());
        itemBinFrom.setTrnNumber(getCurrent().getRefNumber());
        itemBinFrom.setQty(getCurrent().getTransferQty() * -1);
        itemBinFrom.setLog(log);
        itemBinFrom.setBalance(stockFrom.getStockQty());
        getItemBincardController().create(itemBinFrom);
        
        //Item Bincard Entry To
        ItemBincard itemBinTo = new ItemBincard();
        itemBinTo.setDescription("Stock Transfere from " + getCurrent().getTransferFromLocation() + "  - " + getCurrent().getRefNumber());
        itemBinTo.setItem(getCurrent().getTransferItem());
        itemBinTo.setRelatedDate(getCurrent().getTransferDate());
        itemBinTo.setTrnNumber(getCurrent().getRefNumber());
        itemBinTo.setQty(getCurrent().getTransferQty() * 1);
        itemBinTo.setLog(log);
        itemBinTo.setBalance(stockTo.getStockQty());
        getItemBincardController().create(itemBinTo);

        //Increment the the Document No 
        if (getCurrentSystemNumber() != null) {
            getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getIncrementedSystemNumber());
            getSystemNumbersController().edit(getCurrentSystemNumber());
        }

        getStockTransferController().create(getCurrent());

        return "Home";

    }

}
