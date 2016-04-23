package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Company;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.SessionBean.CompanyController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne Alpha Development Team (Pvt) Ltd
 *
 */
@ManagedBean
@SessionScoped
public class CompanyHandler {

    @EJB
    private CompanyController companyController;

    private Company current;

    private String RDStatus;

    public CompanyHandler() {
        if (current == null) {
            current = new Company();
        }
    }

    public Company getCurrent() {
        return current;
    }

    public void setCurrent(Company current) {
        this.current = current;
    }

    public List<Company> getListOfCompany() {
        return companyController.findAll();
    }

    public String createCompany() {
        if (getCurrent() != null) {
            companyController.create(getCurrent());
            MessageHelper.addSuccessMessage("New Company Added!");

            if (getRDStatus() != null) {
                System.out.println(RDStatus);
                return getRDStatus();
            } else {
               
                return "Home";
            }
            
           

        } else {
            MessageHelper.addErrorMessage("Error - createCompany", "Session Invalid");
            return "Login";
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
        System.out.println("RDStatus : " + RDStatus);
        this.RDStatus = RDStatus;
    }
    
}
