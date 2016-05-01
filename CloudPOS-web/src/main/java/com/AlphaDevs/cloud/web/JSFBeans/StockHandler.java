package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Company;
import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.Stock;
import com.AlphaDevs.cloud.web.Entities.Units;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.ItemsController;
import com.AlphaDevs.cloud.web.SessionBean.StockController;
import com.AlphaDevs.cloud.web.SessionBean.UnitsController;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne Alpha Development Team (Pvt) Ltd
 *
 */
@ManagedBean
@SessionScoped
public class StockHandler {

    @EJB
    private UnitsController unitsController;
    @EJB
    private ItemsController itemsController;
    @EJB
    private StockController stockController;

    private Stock current;

    public StockHandler() {
        if (current == null) {
            current = new Stock();
        }
    }

    public UnitsController getUnitsController() {
        return unitsController;
    }

    public void setUnitsController(UnitsController unitsController) {
        this.unitsController = unitsController;
    }

    public ItemsController getItemsController() {
        return itemsController;
    }

    public void setItemsController(ItemsController itemsController) {
        this.itemsController = itemsController;
    }

    public StockController getStockController() {
        return stockController;
    }

    public void setStockController(StockController stockController) {
        this.stockController = stockController;
    }

    public List<Stock> getList() {
        List<Stock> liquidStockList = new ArrayList<>();
        if (getCurrent().getStockLocation() != null) {
            List<Units> searchUnit = getUnitsController().findUnitsByCode("L");
            if (searchUnit != null && !searchUnit.isEmpty()) {
                List<Items> liquidItems = getItemsController().findItemByUnit(searchUnit.get(0), getCurrent().getStockLocation());
                if (liquidItems != null && !liquidItems.isEmpty()) {
                    for (Items item : liquidItems) {
                        if (item != null) {
                            Stock itemStock = getStockController().findSpecific(item,getCurrent().getStockLocation(), SessionDataHelper.getLoggedCompany(true));
                            liquidStockList.add(itemStock);
                        }
                    }
                }
            }
        }
        return liquidStockList;
    }

    public Stock getCurrent() {
        return current;
    }

    public void setCurrent(Stock current) {
        this.current = current;
    }

    public Stock getSpecificStock(Items item,Location location, Company company) {
        return stockController.findSpecific(item,location,company);
    }

    public List<Stock> loadStockData() {

        List<Stock> location_stock = new ArrayList<>();
        if (getStockController() != null && getCurrent().getStockLocation() != null) {
            return getStockController().getLocationStock(getCurrent().getStockLocation());
        }
        return location_stock;
    }

}
