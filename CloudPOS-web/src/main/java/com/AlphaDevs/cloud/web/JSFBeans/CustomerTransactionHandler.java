package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.CustomerTransaction;
import com.AlphaDevs.cloud.web.SessionBean.CustomerTransactionController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne Alpha Development Team (Pvt) Ltd
 *
 */
@ManagedBean
@ViewScoped
public class CustomerTransactionHandler {

    @EJB
    private CustomerTransactionController customerTransactionController;

    private CustomerTransaction current;
    private List<CustomerTransaction> findSuppliersInTimePeriod;

    private Date fromDate = new Date();
    private Date toDate = new Date();

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * Creates a new instance of CustomerTransactionHandler
     */
    public CustomerTransactionHandler() {
        if (current == null) {
            current = new CustomerTransaction();
        }
    }

    public CustomerTransaction getCurrent() {
        return current;
    }

    public void setCurrent(CustomerTransaction current) {
        this.current = current;
    }

     public CustomerTransactionController getCustomerTransactionController() {
        return customerTransactionController;
    }

    public void setCustomerTransactionController(CustomerTransactionController customerTransactionController) {
        this.customerTransactionController = customerTransactionController;
    }

    public List<CustomerTransaction> getList() {
        return customerTransactionController.findAll();
    }

    public void initCustomerAccount() {
        try {
//            SimpleDateFormat format = new SimpleDateFormat(AlphaConstant.yyyy_MM_dd);
            setFindSuppliersInTimePeriod(new ArrayList<CustomerTransaction>());
            setFindSuppliersInTimePeriod(getCustomerTransactionController().findSuppliersInTimePeriod(getCurrent().getSupplier(),getFromDate(), getToDate()));
        } catch (Exception ex) {
            Logger.getLogger(CustomerTransactionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<CustomerTransaction> getFindSuppliersInTimePeriod() {
        return findSuppliersInTimePeriod;
    }

    public void setFindSuppliersInTimePeriod(List<CustomerTransaction> findSuppliersInTimePeriod) {
        this.findSuppliersInTimePeriod = findSuppliersInTimePeriod;
    }
    
}
