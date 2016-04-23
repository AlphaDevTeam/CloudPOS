package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.Product;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import com.AlphaDevs.cloud.web.SessionBean.ProductController;
import java.util.List;
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
public class ProductHandler {

    @EJB
    private LoggerController loggerController;

    @EJB
    private ProductController productController;

    private Product current;
    private String RDStatus;

    /**
     * Creates a new instance of ProductHandler
     */
    public ProductHandler() {
        if (current == null) {
            current = new Product();
        }

    }

    public Product getCurrent() {
        return current;
    }

    public void setCurrent(Product current) {
        this.current = current;
    }

    public List<Product> getList() {
        return productController.findAll();
    }

    public String persistProduct() {
        Logger Log = EntityHelper.createLogger("Product Design", current.getProdCode(), TransactionTypes.PRODUCT);
        loggerController.create(Log);
        current.setLogger(Log);

        if (getRDStatus() != null) {
            MessageHelper.addSuccessMessage("Product Added!");
            productController.create(current);
            current = new Product();
            return getRDStatus();
        } else {
            MessageHelper.addSuccessMessage("Product Added!");
            productController.create(current);
            current = new Product();
            return "Home";

        }
    }

    /**
     * @return the RDStatus
     */
    public String getRDStatus() {
        return RDStatus;
    }

    /**
     * @param RDStatus the RDStatus to set
     */
    public void setRDStatus(String RDStatus) {
        this.RDStatus = RDStatus;
    }

}
