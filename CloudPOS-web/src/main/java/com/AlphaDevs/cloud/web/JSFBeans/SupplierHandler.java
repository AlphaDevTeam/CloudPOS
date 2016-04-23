package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.*;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.SessionBean.CustomerBalanceController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import com.AlphaDevs.cloud.web.SessionBean.SupplierController;
import java.util.Date;
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
public class SupplierHandler {

    @EJB
    private LoggerController loggerController;
    @EJB
    private CustomerBalanceController customerBalanceController;
    @EJB
    private SupplierController supplierController;

    private Supplier current;
    private Address currentAddress;
    private ContactInfo currentContact;
    private String RDStatus;
    
  

    
    

    public SupplierHandler() {
        if (current == null) {
            current = new Supplier();

        }
        if (currentAddress == null) {
            currentAddress = new Address();
        }
        if (currentContact == null) {
            currentContact = new ContactInfo();
        }
    }

    public Supplier getCurrent() {
        return current;
    }

    public void setCurrent(Supplier current) {
        this.current = current;
    }

    public String createSup() {
        Logger Log = EntityHelper.createLogger("Create Supplier", current.getFirstName(), TransactionTypes.SUPPLIER);
        loggerController.create(Log);
        current.setLogger(Log);

        if (getRDStatus() != null) {
            supplierController.create(current);
            CustomerBalance CustBal = new CustomerBalance();
            CustBal.setBalance(0);
            CustBal.setSupplier(current);
            customerBalanceController.create(CustBal);
            current = new Supplier();
            MessageHelper.addSuccessMessage("Supplier Added!");
            return getRDStatus();

        } else {

            supplierController.create(current);
            CustomerBalance CustBal = new CustomerBalance();
            CustBal.setBalance(0);
            CustBal.setSupplier(current);
            customerBalanceController.create(CustBal);
             current = new Supplier();
            MessageHelper.addSuccessMessage("Supplier Added!");
            return "Home";
        }

    }

    public List<Supplier> allSuppliers() {
        return supplierController.findAll();
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }

    public ContactInfo getCurrentContact() {
        return currentContact;
    }

    public void setCurrentContact(ContactInfo currentContact) {
        this.currentContact = currentContact;
    }

    public SupplierController getSupplierController() {
        return supplierController;
    }

    public void setSupplierController(SupplierController supplierController) {
        this.supplierController = supplierController;
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
