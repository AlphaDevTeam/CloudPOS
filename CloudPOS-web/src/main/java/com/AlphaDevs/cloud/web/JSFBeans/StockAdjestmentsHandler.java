package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.ItemBincard;
import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.Stock;
import com.AlphaDevs.cloud.web.Entities.StockAdjestments;
import com.AlphaDevs.cloud.web.Entities.SystemNumbers;
import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Enums.AdjestmentTypes;
import com.AlphaDevs.cloud.web.Enums.BillStatus;
import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Extra.DocumentEntityHelper;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.ItemBincardController;
import com.AlphaDevs.cloud.web.SessionBean.ItemsController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import com.AlphaDevs.cloud.web.SessionBean.StockAdjestmentsController;
import com.AlphaDevs.cloud.web.SessionBean.StockController;
import com.AlphaDevs.cloud.web.SessionBean.SystemNumbersController;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Mihindu Gajaba Karunarathne Alpha Development Team (Pvt) Ltd
 *
 */
@ManagedBean
@ViewScoped
public class StockAdjestmentsHandler {

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
    @EJB
    private StockAdjestmentsController stockAdjestmentsController;

    private StockAdjestments current;
    private SystemNumbers currentSystemNumber;
    private Document currentDocument;

    @PostConstruct
    public void init() {
        setCurrentDocument(Document.STOCK_ADJESTMENT);
        if (current == null) {
            current = new StockAdjestments();
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

    public StockAdjestmentsController getStockAdjestmentsController() {
        return stockAdjestmentsController;
    }

    public void setStockAdjestmentsController(StockAdjestmentsController stockAdjestmentsController) {
        this.stockAdjestmentsController = stockAdjestmentsController;
    }

    public StockAdjestments getCurrent() {
        return current;
    }

    public void setCurrent(StockAdjestments current) {
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
        getCurrent().setAdjestmentItems(null);
        getCurrent().setAdjestmentQty(Double.valueOf("0"));
    }

    public List<Items> autoCompleteItems(String query) {
        return getItemsController().findLike(query, getCurrent().getAdjestmentLocation());
    }

    public String getRefNumber() {
        currentSystemNumber = null;
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get(AlphaConstant.SESSION_USER);
        if (loggedUser != null && getCurrent().getAdjestmentLocation() != null) {
            List<SystemNumbers> systemNumbers = systemNumbersController.findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getAdjestmentLocation(), getCurrentDocument());
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

    public String persistStockAdjestment() {
        Logger logger = EntityHelper.createLogger("Stock Adjestment ", getCurrent().getRefNumber(), TransactionTypes.STOCK_ADJ);
        getLoggerController().create(logger);

        //Adjesting Stock
        Stock stock = getStockController().findSpecific(getCurrent().getAdjestmentItems(),getCurrent().getAdjestmentLocation(), SessionDataHelper.getLoggedCompany(true));
        stock.setStockQty((float) (stock.getStockQty() + getCurrent().getAdjestmentQty()));
        getStockController().edit(stock);
        
        //Fix Me BillType Hardcoded
        ItemBincard itemBin = DocumentEntityHelper.createItemBincardEntry(logger, getCurrent().getAdjestmentLocation(), getCurrentDocument().getDocumentDisplayName() + " - " + getCurrent(),getCurrent().getAdjestmentItems(), getCurrent().getAdjestmentDate(), getCurrent().toString(), (getCurrent().getAdjestmentQty() * 1), BillStatus.TAX, stock.getStockQty());
        getItemBincardController().create(itemBin);

        //Increment the the Document No 
        if (getCurrentSystemNumber() != null) {
            getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getIncrementedSystemNumber());
            getSystemNumbersController().edit(getCurrentSystemNumber());
        }

        getStockAdjestmentsController().create(getCurrent());

        return "Home";

    }

}
