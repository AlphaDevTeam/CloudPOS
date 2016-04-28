package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.ItemBincard;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.MeterReading;
import com.AlphaDevs.cloud.web.Entities.Pump;
import com.AlphaDevs.cloud.web.Entities.Stock;
import com.AlphaDevs.cloud.web.Entities.SystemNumbers;
import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.ItemBincardController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import com.AlphaDevs.cloud.web.SessionBean.MeterReadingController;
import com.AlphaDevs.cloud.web.SessionBean.PumpController;
import com.AlphaDevs.cloud.web.SessionBean.StockController;
import com.AlphaDevs.cloud.web.SessionBean.SystemNumbersController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 *
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@ManagedBean
@SessionScoped
public class MeterReadingHandler {

    @EJB
    private SystemNumbersController systemNumbersController;
    @EJB
    private PumpController pumpController;

    @EJB
    private ItemBincardController itemBincardController;
    @EJB
    private StockController stockController;

    @EJB
    private LoggerController loggerController;

    @EJB
    private MeterReadingController meterReadingController;

    private MeterReading current;
    private double currentReading;

    private SystemNumbers currentSystemNumber;
    private Document currentDocument;

    @PostConstruct
    public void init() {
        setCurrentDocument(Document.METER_READING);
        current = new MeterReading();
    }

    public MeterReadingHandler() {
    }

    public Document getCurrentDocument() {
        return currentDocument;
    }

    public void setCurrentDocument(Document currentDocument) {
        this.currentDocument = currentDocument;
    }

    public SystemNumbers getCurrentSystemNumber() {
        return currentSystemNumber;
    }

    public void setCurrentSystemNumber(SystemNumbers currentSystemNumber) {
        this.currentSystemNumber = currentSystemNumber;
    }

    public SystemNumbersController getSystemNumbersController() {
        return systemNumbersController;
    }

    public void setSystemNumbersController(SystemNumbersController systemNumbersController) {
        this.systemNumbersController = systemNumbersController;
    }

    public MeterReadingController getMeterReadingController() {
        return meterReadingController;
    }

    public PumpController getPumpController() {
        return pumpController;
    }

    public void setPumpController(PumpController pumpController) {
        this.pumpController = pumpController;
    }

    public void setMeterReadingController(MeterReadingController meterReadingController) {
        this.meterReadingController = meterReadingController;
    }

    public MeterReading getCurrent() {
        return current;
    }

    public void setCurrent(MeterReading current) {
        this.current = current;
    }

    public List<MeterReading> getList() {
        return getMeterReadingController().findAll();
    }

    public double getLastReading() {
        if (getCurrent() != null && getCurrent().getRelatedPump() != null && getCurrent().getRelatedPump().getLastReading() != null) {
            return getCurrent().getRelatedPump().getLastReading();
        } else {
            return 0;
        }
    }

    public String getReferenceNumber() {
        setCurrentSystemNumber(null);
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get(AlphaConstant.SESSION_USER);
        if (loggedUser != null && getCurrent().getRelatedLocation() != null) {
            List<SystemNumbers> systemNumbers = getSystemNumbersController().findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getRelatedLocation(), getCurrentDocument());
            if (systemNumbers != null && !systemNumbers.isEmpty()) {
                setCurrentSystemNumber(systemNumbers.get(0));
                getCurrent().setReferenceNumber(getCurrentSystemNumber().getDocumentSystemNo());
            }
        }
        return getCurrentSystemNumber() != null ? getCurrentSystemNumber().getDocumentSystemNo() : "";
    }

    public void setReferenceNumber(String referrenccNumberre) {
        getCurrent().setReferenceNumber(referrenccNumberre);
    }

    public List<Pump> getPumpListAccordingToLocation() {
        if (getCurrent() != null && getCurrent().getRelatedLocation() != null) {
            return getPumpController().findReadingByPump(getCurrent().getRelatedLocation());
        } else {
            return new ArrayList<>();
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

    public double getCurrentReading() {
        return currentReading;
    }

    public void setCurrentReading(double currentReading) {
        this.currentReading = currentReading;
    }

    public void calculateReading() {
        //System.out.println("awas"  + getLastReading() + " -- " + getCurrentReading());
        getCurrent().setReading(getCurrentReading() - getLastReading());
    }

    private void saveMeterReading() {
        Logger Log = EntityHelper.createLogger("Meter Reading", getCurrent().toString(), TransactionTypes.READINGS);
        getLoggerController().create(Log);
        getCurrent().setLogger(Log);

        Stock stock = getStockController().getItemStock(getCurrent().getRelatedLocation(), getCurrent().getRelatedPump().getRelatedItem());
        stock.setStockQty((float) (stock.getStockQty() - getCurrent().getReading()));
        getStockController().edit(stock);

        ItemBincard itemBin = new ItemBincard();
        itemBin.setDescription("Meter Reading - " + getCurrent().getRelatedPump() + " - " + getCurrent() + getCurrent().getNote());
        itemBin.setItem(getCurrent().getRelatedPump().getRelatedItem());
        itemBin.setTrnNumber(getCurrent().getReferenceNumber());
        itemBin.setQty((getCurrent().getReading() * -1));
        itemBin.setRelatedDate(getCurrent().getRelatedDate());
        itemBin.setLog(Log);
        itemBin.setBalance(stock.getStockQty());
        getItemBincardController().create(itemBin);

        Pump pump = getCurrent().getRelatedPump();
        pump.setLastReading(getCurrentReading());
        getPumpController().edit(pump);

        //Increment the the Document No 
        if (getCurrentSystemNumber() != null) {
            getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getIncrementedSystemNumber());
            getSystemNumbersController().edit(getCurrentSystemNumber());
        }
        getCurrent().setLastReading(getLastReading() - getCurrent().getReading());
        getMeterReadingController().create(getCurrent());
    }

    public void saveMeterReadingAndStay() {

        saveMeterReading();
        setCurrent(new MeterReading());
        setCurrentReading(0);

    }

    public String saveMeterReadingAndMoveToHome() {
        saveMeterReading();
        setCurrent(new MeterReading());
        setCurrentReading(0);
        return "Home";
    }

}
