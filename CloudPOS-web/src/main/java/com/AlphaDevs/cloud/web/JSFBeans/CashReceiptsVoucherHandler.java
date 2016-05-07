

package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.CashBook;
import com.AlphaDevs.cloud.web.Entities.CashBookBalance;
import com.AlphaDevs.cloud.web.Entities.CashReceivedVoucher;
import com.AlphaDevs.cloud.web.Entities.CustomerBalance;
import com.AlphaDevs.cloud.web.Entities.CustomerTransaction;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.SystemNumbers;
import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.CashBookBalanceController;
import com.AlphaDevs.cloud.web.SessionBean.CashReceivedVoucherController;
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
public class CashReceiptsVoucherHandler {
   
    @EJB
    private CashReceivedVoucherController cashReceivedVoucherController;
    
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
    
    
    private CashReceivedVoucher current;
    private SystemNumbers currentSystemNumber;
    private Document currentDocument;
    
    public CashReceiptsVoucherHandler() {
        setCurrentDocument(Document.CASH_RECEIPT_CUST);
        current = new CashReceivedVoucher();
    }

    public CashReceivedVoucherController getCashReceivedVoucherController() {
        return cashReceivedVoucherController;
    }

    public void setCashReceivedVoucherController(CashReceivedVoucherController cashReceivedVoucherController) {
        this.cashReceivedVoucherController = cashReceivedVoucherController;
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

    public CashReceivedVoucher getCurrent() {
        return current;
    }

    public void setCurrent(CashReceivedVoucher current) {
        this.current = current;
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
    
    public String getReceiptNumber(){
        setCurrentSystemNumber(null);
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get(AlphaConstant.SESSION_USER);
        if(loggedUser != null && getCurrent().getReceiptLocation() != null){
            List<SystemNumbers> systemNumbers = getSystemNumbersController().findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getReceiptLocation(), getCurrentDocument());
            if(systemNumbers != null && !systemNumbers.isEmpty()){
                setCurrentSystemNumber(systemNumbers.get(0));
                getCurrent().setReceiptNumber(getCurrentSystemNumber().getDocumentSystemNo());
            }
        }
        return  getCurrentSystemNumber() != null ? getCurrentSystemNumber().getDocumentSystemNo() : "";
    }
    
    public void setReceiptNumber(String receiptNumber){
        getCurrent().setReceiptNumber(receiptNumber);
    }    
    
    public String createReceipt(){
        
        //Creating Logger
        Logger log = EntityHelper.createLogger("Cash Receipt Voucher - " + getCurrent().getReceiptDescription() , getCurrent().getReceiptNumber(), TransactionTypes.CASHREC);
        getLoggerController().create(log);
        getCurrent().setRelatedLogger(log);
        
        getCurrent().setRelatedCompany(EntityHelper.getLoggedCompany()); 
        
        //Customer Transaction
        CustomerTransaction custTran = new CustomerTransaction();
        custTran.setDescription("Cash Receipt Voucher - " + getCurrent().getReceiptNumber() + "-" + getCurrent().getReceiptRefNumber()+ " - "+ getCurrent().getReceiptDescription());
        custTran.setSupplier(getCurrent().getRelatedSupplier());
        custTran.setDR(0);
        custTran.setBillStat(getCurrent().getBillStatus());
        custTran.setDate(getCurrent().getReceiptDate());
        custTran.setCR(getCurrent().getReceiptAmount());
        
        //Getting Cust Balance
        CustomerBalance Balance = getCustomerBalanceController().getCustomerBalanceObject(getCurrent().getRelatedSupplier(),getCurrent().getBillStatus());
        if(Balance != null)
        {
            Balance.setBalance(Balance.getBalance() - getCurrent().getReceiptAmount() );
            getCustomerBalanceController().edit(Balance);
            custTran.setBalance(Balance.getBalance());
            
        }
        else
        {
            custTran.setBalance(getCurrent().getReceiptAmount());
        }
        
        custTran.setLogger(log);
        getCustomerTransactionController().create(custTran);
        
        //Cashbook
        CashBook cashBook = new CashBook();
        cashBook.setDescription("Cash Receipt Voucher - " + getCurrent().getReceiptNumber()+ "-" + getCurrent().getReceiptRefNumber() + " - "+ getCurrent().getReceiptDescription());
        cashBook.setCR(0);
        cashBook.setBillStat(getCurrent().getBillStatus());
        cashBook.setDR(getCurrent().getReceiptAmount());
        cashBook.setRelatedDate(getCurrent().getReceiptDate());
        cashBook.setLocation(getCurrent().getReceiptLocation());
        cashBook.setLogger(log);

        CashBookBalance cashBalance = getCashBookBalanceController().getCashBookBalanceObject(getCurrent().getReceiptLocation(), getCurrent().getBillStatus());
        
        if(cashBalance != null)
        {
            cashBalance.setCashBalance(cashBalance.getCashBalance() + getCurrent().getReceiptAmount());
            getCashBookBalanceController().edit(cashBalance);
            cashBook.setBalance(cashBalance.getCashBalance());            
        }
        else
        {
            cashBook.setBalance(getCurrent().getReceiptAmount());
        }

        getCashbookController().create(cashBook);
        
        //Increment the the Document No 
        if(getCurrentSystemNumber() != null){
            getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getIncrementedSystemNumber());
            getSystemNumbersController().edit(getCurrentSystemNumber());
        }
        getCashReceivedVoucherController().create(getCurrent());
        
        return "Home";
    }

}
