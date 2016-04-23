
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.CashBook;
import com.AlphaDevs.cloud.web.Entities.CashBookBalance;
import com.AlphaDevs.cloud.web.Entities.CashPaymentVoucher;
import com.AlphaDevs.cloud.web.Entities.CustomerBalance;
import com.AlphaDevs.cloud.web.Entities.CustomerTransaction;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.SystemNumbers;
import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Enums.BillStatus;
import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.CashBookBalanceController;
import com.AlphaDevs.cloud.web.SessionBean.CashPaymentVoucherController;
import com.AlphaDevs.cloud.web.SessionBean.CashbookController;
import com.AlphaDevs.cloud.web.SessionBean.CustomerBalanceController;
import com.AlphaDevs.cloud.web.SessionBean.CustomerTransactionController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import com.AlphaDevs.cloud.web.SessionBean.SystemNumbersController;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


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
@SessionScoped
public class CashPaymentVoucherHandler {
    @EJB
    private SystemNumbersController systemNumbersController;
    @EJB
    private CashbookController cashbookController;
    @EJB
    private CashBookBalanceController cashBookBalanceController;
    @EJB
    private CustomerTransactionController customerTransactionController;
    @EJB
    private CustomerBalanceController customerBalanceController;
    @EJB
    private LoggerController loggerController;
    @EJB
    private CashPaymentVoucherController cashPaymentVoucherController;

    private CashPaymentVoucher current;
    
    private SystemNumbers currentSystemNumber;
    private Document currentDocument;
    
    public CashPaymentVoucherHandler() {
        
        setCurrentDocument(Document.CASH_PAYMENT_CUST);
        if(current == null){
            current = new CashPaymentVoucher();
        }
    }

    public SystemNumbersController getSystemNumbersController() {
        return systemNumbersController;
    }

    public void setSystemNumbersController(SystemNumbersController systemNumbersController) {
        this.systemNumbersController = systemNumbersController;
    }

    public CashbookController getCashbookController() {
        return cashbookController;
    }

    public void setCashbookController(CashbookController cashbookController) {
        this.cashbookController = cashbookController;
    }

    public CashBookBalanceController getCashBookBalanceController() {
        return cashBookBalanceController;
    }

    public void setCashBookBalanceController(CashBookBalanceController cashBookBalanceController) {
        this.cashBookBalanceController = cashBookBalanceController;
    }

    public CustomerTransactionController getCustomerTransactionController() {
        return customerTransactionController;
    }

    public void setCustomerTransactionController(CustomerTransactionController customerTransactionController) {
        this.customerTransactionController = customerTransactionController;
    }

    public CustomerBalanceController getCustomerBalanceController() {
        return customerBalanceController;
    }

    public void setCustomerBalanceController(CustomerBalanceController customerBalanceController) {
        this.customerBalanceController = customerBalanceController;
    }

    public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
    }

    public SystemNumbers getCurrentSystemNumber() {
        return currentSystemNumber;
    }

    public void setCurrentSystemNumber(SystemNumbers currentSystemNumber) {
        this.currentSystemNumber = currentSystemNumber;
    }

    public Document getCurrentDocument() {
        return currentDocument;
    }

    public void setCurrentDocument(Document currentDocument) {
        this.currentDocument = currentDocument;
    }
    
    

    public CashPaymentVoucherController getCashPaymentVoucherController() {
        return cashPaymentVoucherController;
    }

    public void setCashPaymentVoucherController(CashPaymentVoucherController cashPaymentVoucherController) {
        this.cashPaymentVoucherController = cashPaymentVoucherController;
    }

    public CashPaymentVoucher getCurrent() {
        return current;
    }

    public void setCurrent(CashPaymentVoucher current) {
        this.current = current;
    }
    
    public List<CashPaymentVoucher> getList(){
        return getCashPaymentVoucherController().findAll();
    }
     
    public String getPaymentNumber(){
        setCurrentSystemNumber(null);
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get(AlphaConstant.SESSION_USER);
        if(loggedUser != null && getCurrent().getPaymentLocation() != null){
            List<SystemNumbers> systemNumbers = getSystemNumbersController().findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getPaymentLocation(), getCurrentDocument());
            if(systemNumbers != null && !systemNumbers.isEmpty()){
                setCurrentSystemNumber(systemNumbers.get(0));
                System.out.println("Set Payment Number");
                getCurrent().setPaymentNumber(getCurrentSystemNumber().getDocumentSystemNo());
            }
            
        }
        return  getCurrentSystemNumber() != null ? getCurrentSystemNumber().getDocumentSystemNo() : "";
    }
    
    public void setPaymentNumber(String billNumber){
        getCurrent().setPaymentNumber(billNumber);
    }    
    
    public String createPayment(){
        
        //Creating Logger
        Logger log = EntityHelper.createLogger("Cash Payament Voucher - " + getCurrent().getPaymentDescription() , getCurrent().getPaymentNumber(), TransactionTypes.CASHPAY);
        loggerController.create(log);
        getCurrent().setRelatedLogger(log);
        
        getCurrent().setRelatedCompany(EntityHelper.getLoggedCompany()); 
        
        //Customer Transaction
        CustomerTransaction custTran = new CustomerTransaction();
        custTran.setDescription("Cash Payament Voucher - " + getCurrent().getPaymentNumber() + "-" + getCurrent().getPaymentRefNumber() + " - "+ getCurrent().getPaymentDescription());
        custTran.setSupplier(getCurrent().getRelatedSupplier());
        custTran.setDate(getCurrent().getPaymentDate());
        custTran.setDR(getCurrent().getPaymentAmount());
        custTran.setCR(0);
        
        //Getting Cust Balance
        CustomerBalance Balance = customerBalanceController.getCustomerBalanceObject(getCurrent().getRelatedSupplier());
        if(Balance != null)
        {
            Balance.setBalance(Balance.getBalance() + getCurrent().getPaymentAmount() );
            customerBalanceController.edit(Balance);
            custTran.setBalance(Balance.getBalance());
            
        }
        else
        {
            custTran.setBalance(getCurrent().getPaymentAmount());
        }
        
        custTran.setLogger(log);
        customerTransactionController.create(custTran);
        
        //Cashbook
        CashBook cashBook = new CashBook();
        cashBook.setDescription("Cash Payament Voucher - " + current.getPaymentNumber() + "-" + getCurrent().getPaymentRefNumber() + " - "+ getCurrent().getPaymentDescription());
        cashBook.setCR(getCurrent().getPaymentAmount());
        cashBook.setDR(0);
        cashBook.setRelatedDate(getCurrent().getPaymentDate());
        cashBook.setLocation(getCurrent().getPaymentLocation());
        cashBook.setLogger(log);

        CashBookBalance cashBalance = cashBookBalanceController.getCashBookBalanceObject(getCurrent().getPaymentLocation(), BillStatus.TAX);
        
        if(cashBalance != null)
        {
            cashBalance.setCashBalance(cashBalance.getCashBalance() - getCurrent().getPaymentAmount());
            cashBookBalanceController.edit(cashBalance);
            cashBook.setBalance(cashBalance.getCashBalance());            
        }
        else
        {
            cashBook.setBalance(getCurrent().getPaymentAmount());
        }

        cashbookController.create(cashBook);
        
        //Increment the the Document No 
        if(getCurrentSystemNumber() != null){
            getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getIncrementedSystemNumber());
            getSystemNumbersController().edit(getCurrentSystemNumber());
        }
        getCashPaymentVoucherController().create(getCurrent());     
        
        return "Home";
    }
}
