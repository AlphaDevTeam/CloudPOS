package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.CustomerCategory;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.SessionBean.CustomerCategoryController;
import java.util.List;
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
public class CustomerCategoryHandler {

    @EJB
    private CustomerCategoryController customerCategoryController;

    private CustomerCategory current;

    public CustomerCategoryHandler() {
        if (current == null) {
            current = new CustomerCategory();
        }
    }

    public CustomerCategory getCurrent() {
        return current;
    }

    public void setCurrent(CustomerCategory current) {
        this.current = current;
    }

    public String createCustomerCategory() {
        try {
            Logger Log = EntityHelper.createLogger("Create Customer Category", getCurrent().getDescription(), TransactionTypes.CUSTOMERCAT);
            if (Log != null) {
                current.setLogger(Log);
                customerCategoryController.create(current);
                current = new CustomerCategory();
                MessageHelper.addSuccessMessage("Customer Category Added!");
                return "Home";
            } else {
                MessageHelper.addErrorMessage("Error - Create Customer Category", "Session Invalid");
                return "Login";
            }

        } catch (Exception e) {
            MessageHelper.addErrorMessage("Error - Create Customer Category", e.toString());
            return "Login";
        }

    }

    public List<CustomerCategory> getList() {
        return customerCategoryController.findAll();
    }

    public CustomerCategoryController getCustomerCategoryController() {
        return customerCategoryController;
    }

    public void setCustomerCategoryController(CustomerCategoryController customerCategoryController) {
        this.customerCategoryController = customerCategoryController;
    }

    public String updateCutomerCatogary(Long cat_id) {

        System.out.println(cat_id);
        current = customerCategoryController.find(cat_id);
        return "Update";
    }

    public String updateRecord() {

        Logger Log = EntityHelper.createLogger("Customer Category Updated", "", TransactionTypes.CUSTOMERCAT);
        if (Log != null) {
            current.setLogger(Log);
            customerCategoryController.edit(current);
            // current = new CustomerCategory();
            MessageHelper.addSuccessMessage("Customer Category Updated!");
            return "updated";
        } else {
            MessageHelper.addErrorMessage("Error - updateCustomerCategory", "Session Invalid");
            return "Login";
        }

    }

}
