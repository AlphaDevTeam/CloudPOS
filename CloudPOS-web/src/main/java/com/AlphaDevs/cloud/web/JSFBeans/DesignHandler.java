package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Design;
import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.Product;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.SessionBean.DesignController;
import com.AlphaDevs.cloud.web.SessionBean.ItemsController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import com.AlphaDevs.cloud.web.SessionBean.ProductController;
import java.util.ArrayList;
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
public class DesignHandler {

    @EJB
    private ItemsController itemsController;

    @EJB
    private LoggerController loggerController;
    @EJB
    private ProductController productController;
    @EJB
    private DesignController designController;

    private Design current;
    private String RDStatus;

    private List<Items> Rel_Design_ItemList;

    private List<Design> designList;

    /**
     * Creates a new instance of DesignHandler
     */
    public DesignHandler() {
        if (current == null) {
            current = new Design();
        }
    }

    public Design getCurrent() {
        return current;
    }

    public void setCurrent(Design current) {
        this.current = current;
    }

    public List<Design> getList() {
        return designController.findAll();
    }

    public void changeProd() {
        System.out.println("Changed Prod : ");
        current.setProduct(productController.find(Long.valueOf(1)));
    }

    public String persistDesign() {

        Logger Log = EntityHelper.createLogger("Create Design", current.getDesignCode(), TransactionTypes.DESIGN);
        loggerController.create(Log);
        current.setLogger(Log);

        if (getRDStatus() != null) {
            MessageHelper.addSuccessMessage("Design Added!");
            designController.create(current);
            current = new Design();
            return getRDStatus();
        } else {
            MessageHelper.addSuccessMessage("Design Added!");
            designController.create(current);
            current = new Design();
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

    /**
     * @return the Rel_Design_ItemList
     */
    public List<Items> getRel_Design_ItemList() {
        return Rel_Design_ItemList;
    }

    /**
     * @param Rel_Design_ItemList the Rel_Design_ItemList to set
     */
    public void setRel_Design_ItemList(List<Items> Rel_Design_ItemList) {
        this.Rel_Design_ItemList = Rel_Design_ItemList;
    }

    public void setItemList(Long ID) {
        System.out.println(ID);
        Rel_Design_ItemList = itemsController.findItemByDesign(ID);
        for (Items Rel : Rel_Design_ItemList) {
            System.out.println(Rel.getId());
            System.out.println(Rel.getItemName());
        }

        if (Rel_Design_ItemList == null) {
            Rel_Design_ItemList = new ArrayList<Items>();
        }
    }

    public void getDesignlist(Long ID) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(ID);

        Product selectedProduct = productController.find(ID);
        if(selectedProduct != null){
         designList = designController.findSpecific(selectedProduct);
        }else{
            designList = new ArrayList<Design>();
        }
        
    }

    /**
     * @return the designList
     */
    public List<Design> getDesignList() {
        return designList;
    }

    /**
     * @param designList the designList to set
     */
    public void setDesignList(List<Design> designList) {
        this.designList = designList;
    }

}
