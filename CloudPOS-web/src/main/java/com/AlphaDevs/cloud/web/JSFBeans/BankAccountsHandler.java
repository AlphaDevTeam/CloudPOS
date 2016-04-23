
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.BankAccounts;
import com.AlphaDevs.cloud.web.SessionBean.BankAccountsController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@ManagedBean
@RequestScoped
public class BankAccountsHandler {
    @EJB
    private BankAccountsController bankAccountsController;

    private BankAccounts current;
    
    public BankAccountsHandler() {
        if(current == null){
            current = new BankAccounts();
        }
    }

    public BankAccountsController getBankAccountsController() {
        return bankAccountsController;
    }

    public void setBankAccountsController(BankAccountsController bankAccountsController) {
        this.bankAccountsController = bankAccountsController;
    }

    public BankAccounts getCurrent() {
        return current;
    }

    public void setCurrent(BankAccounts current) {
        this.current = current;
    }

    public List<BankAccounts> getList(){
        return getBankAccountsController().findAll();
    }
    
    public String persistBankAccount(){
        getBankAccountsController().create(getCurrent());
        return "Home";
    }
}
