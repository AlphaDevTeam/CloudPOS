package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Design;
import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.Stock;
import com.AlphaDevs.cloud.web.Entities.Units;
import com.AlphaDevs.cloud.web.Enums.BillStatus;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.DesignController;
import com.AlphaDevs.cloud.web.SessionBean.ItemsController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import com.AlphaDevs.cloud.web.SessionBean.StockController;
import com.AlphaDevs.cloud.web.SessionBean.UnitsController;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 *
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@ManagedBean
@ViewScoped
public class ItemsHandler {

    @EJB
    private UnitsController unitsController;
    @EJB
    private LoggerController loggerController;
    @EJB
    private DesignController designController;
    @EJB
    private StockController stockController;

    @EJB
    private ItemsController itemsController;

    private Items current;

    public ItemsHandler() {
        if (current == null) {
            current = new Items();
        }
    }

    public Items getCurrent() {
        return current;
    }

    public UnitsController getUnitsController() {
        return unitsController;
    }

    public void setUnitsController(UnitsController unitsController) {
        this.unitsController = unitsController;
    }

    public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
    }

    public DesignController getDesignController() {
        return designController;
    }

    public void setDesignController(DesignController designController) {
        this.designController = designController;
    }

    public StockController getStockController() {
        return stockController;
    }

    public void setStockController(StockController stockController) {
        this.stockController = stockController;
    }

    public ItemsController getItemsController() {
        return itemsController;
    }

    public void setItemsController(ItemsController itemsController) {
        this.itemsController = itemsController;
    }

    public void setCurrent(Items current) {
        this.current = current;
    }

    public List<Items> getList() {
        List<Items> itemList = itemsController.findAll();
        System.out.println(itemList);
        return itemList;
    }

    public List<String> getTempList() {

        List<String> temp_list = new ArrayList<>();
        List<Items> itemList = itemsController.findAll();

        for (Items tempItem : itemList) {
            temp_list.add(tempItem.getItemName());
        }

        return temp_list;
    }

    public List<Items> getListOfItems(Location location) {
        if (location != null) {
            return getItemsController().findItems(location);
        } else {
            return new ArrayList<>();
        }
    }

    public List<Items> getListOfLiquids(Location location) {
        if (location != null) {
            List<Units> searchUnit = getUnitsController().findUnitsByCode("L");
            if (searchUnit != null && !searchUnit.isEmpty()) {
                return getItemsController().findItemByUnit(searchUnit.get(0), location);
            } else {
                return new ArrayList<>();
            }

        } else {
            return new ArrayList<>();
        }

    }

    public String persistItem() {
        Logger Log = EntityHelper.createLogger("Create Item", getCurrent().getItemCode(), TransactionTypes.ITEM);
        getLoggerController().create(Log);

        getCurrent().setLogger(Log);
        getItemsController().create(getCurrent());

        Stock stock = new Stock();
        stock.setSockItem(getCurrent());
        stock.setRelatedCompany(SessionDataHelper.getLoggedCompany(true));
        stock.setStockLocation(getCurrent().getItemLocation());
        stock.setStockQty(0);
        //Fix me - Hardcoding the value Tax for Now
        stock.setBillStatus(BillStatus.TAX);
        getStockController().create(stock);
        setCurrent(new Items());
        return "Home";
    }

    public List<Design> getListSpec() {
        return designController.findSpecific(current.getItemProduct());
    }

    public List<String> complete(String q) {
        List<String> tmpList = new ArrayList<>();

        for (Items it : itemsController.findLike(q)) {
            tmpList.add(it.getItemName() + " - " + it.getItemCode() + " - " + it.getItemDescription());
        }
        return tmpList;
    }

    public List<Items> completetest(String q) {
        System.out.println("completetest : with " + q + " - " + getCurrent().getItemLocation());
        return itemsController.findLike(q, getCurrent().getItemLocation());

    }

    public List<Items> autoCompleteItems(String query) {
        return getItemsController().findLike(query);
    }
    public List<Items> autoCompleteItems(String query,Location location) {
        return getItemsController().findLike(query,location);
    }

//    public String redirectTo(String relevantPage) {
//
//        if ("Location".equals(relevantPage)) {
//            return "ToRegisterNewLocation";
//        } else if ("Product".equals(relevantPage)) {
//            return "ToRegisterNewProduct";
//        } else if ("Design".equals(relevantPage)) {
//            return "ToRegisterNewDesign";
//        } else {
//            return "ToRegisterNewSupplier";
//        }
//
//    }
    public String redirectToLocation() {

        return "ToRegisterNewLocation";

    }

    public String redirectToProduct() {

        return "ToRegisterNewProduct";

    }

    public String redirectToDesign() {

        return "ToRegisterNewDesign";

    }

    public String redirectToSupplier() {

        return "ToRegisterNewSupplier";

    }

    public String redirectToUOM() {
        return "ToRegisterNewUOM";

    }

}
