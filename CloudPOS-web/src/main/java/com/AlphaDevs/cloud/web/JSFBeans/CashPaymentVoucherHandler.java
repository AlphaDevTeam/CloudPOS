package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.CashBook;
import com.AlphaDevs.cloud.web.Entities.CashBookBalance;
import com.AlphaDevs.cloud.web.Entities.CashPaymentVoucher;
import com.AlphaDevs.cloud.web.Entities.CustomerBalance;
import com.AlphaDevs.cloud.web.Entities.CustomerTransaction;
import com.AlphaDevs.cloud.web.Entities.GRN;
import com.AlphaDevs.cloud.web.Entities.Invoice;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.PaymentDetails;
import com.AlphaDevs.cloud.web.Entities.SystemNumbers;
import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import static com.AlphaDevs.cloud.web.Enums.TransactionTypes.GRN;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.CashBookBalanceController;
import com.AlphaDevs.cloud.web.SessionBean.CashPaymentVoucherController;
import com.AlphaDevs.cloud.web.SessionBean.CashbookController;
import com.AlphaDevs.cloud.web.SessionBean.CustomerBalanceController;
import com.AlphaDevs.cloud.web.SessionBean.CustomerTransactionController;
import com.AlphaDevs.cloud.web.SessionBean.GRNController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import com.AlphaDevs.cloud.web.SessionBean.PaymentDetailsController;
import com.AlphaDevs.cloud.web.SessionBean.SystemNumbersController;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 *
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@ManagedBean
@ViewScoped
public class CashPaymentVoucherHandler {
    @EJB
    private GRNController gRNController;
    @EJB
    private PaymentDetailsController paymentDetailsController;
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
    private List<GRN> selectedGRNs;

    @PostConstruct
    public void init() {
        setCurrentDocument(Document.CASH_PAYMENT_CUST);
        if (current == null) {
            current = new CashPaymentVoucher();
        }
    }

    public CashPaymentVoucherHandler() {
    }

    public List<GRN> getSelectedGRNs() {
        return selectedGRNs;
    }

    public void setSelectedGRNs(List<GRN> selectedGRNs) {
        this.selectedGRNs = selectedGRNs;
    }

    public PaymentDetailsController getPaymentDetailsController() {
        return paymentDetailsController;
    }

    public void setPaymentDetailsController(PaymentDetailsController paymentDetailsController) {
        this.paymentDetailsController = paymentDetailsController;
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

    public GRNController getgRNController() {
        return gRNController;
    }

    public void setgRNController(GRNController gRNController) {
        this.gRNController = gRNController;
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

    public List<CashPaymentVoucher> getList() {
        return getCashPaymentVoucherController().findAll();
    }

    public List<GRN> getPendingGRNs() {
        return getPaymentDetailsController().findPendingGRNs(getCurrent().getPaymentLocation(), getCurrent().getRelatedSupplier());
    }

    public void calculateSelectedDocumentAmounts() {
        double totalAmout = 0;
        for (GRN selectedGRN : getSelectedGRNs()) {
            totalAmout = totalAmout + selectedGRN.getTotalAmount();
        }
        getCurrent().setPaymentAmount(totalAmout);
    }

    public void onRowSelect(SelectEvent event) {
        calculateSelectedDocumentAmounts();
    }

    public void onToggleCheckbox(ToggleSelectEvent event) {
        calculateSelectedDocumentAmounts();
    }

    public void onRowUnselect(UnselectEvent event) {
        calculateSelectedDocumentAmounts();
    }

    public String getPaymentNumber() {
        setCurrentSystemNumber(null);
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get(AlphaConstant.SESSION_USER);
        if (loggedUser != null && getCurrent().getPaymentLocation() != null) {
            List<SystemNumbers> systemNumbers = getSystemNumbersController().findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getPaymentLocation(), getCurrentDocument());
            if (systemNumbers != null && !systemNumbers.isEmpty()) {
                setCurrentSystemNumber(systemNumbers.get(0));
                System.out.println("Set Payment Number");
                getCurrent().setPaymentNumber(getCurrentSystemNumber().getDocumentSystemNo());
            }

        }
        return getCurrentSystemNumber() != null ? getCurrentSystemNumber().getDocumentSystemNo() : "";
    }

    public void setPaymentNumber(String billNumber) {
        getCurrent().setPaymentNumber(billNumber);
    }
    

    private String createGenericPayment(String description, TransactionTypes trnType) {

        //Creating Logger
        Logger log = EntityHelper.createLogger(description,getCurrent().getPaymentNumber(), TransactionTypes.CASHPAY);
        getLoggerController().create(log);
        getCurrent().setRelatedLogger(log);

        getCurrent().setRelatedCompany(EntityHelper.getLoggedCompany());

        //Customer Transaction
        CustomerTransaction custTran = new CustomerTransaction();
        custTran.setDescription(description);
        custTran.setSupplier(getCurrent().getRelatedSupplier());
        custTran.setDate(getCurrent().getPaymentDate());
        custTran.setBillStat(getCurrent().getBillStatus());
        custTran.setDR(getCurrent().getPaymentAmount());
        custTran.setCR(0);

        //Getting Cust Balance
        CustomerBalance Balance = getCustomerBalanceController().getCustomerBalanceObject(getCurrent().getRelatedSupplier(), getCurrent().getBillStatus());
        if (Balance != null) {
            Balance.setBalance(Balance.getBalance() + getCurrent().getPaymentAmount());
            getCustomerBalanceController().edit(Balance);
            custTran.setBalance(Balance.getBalance());
        } else {
            custTran.setBalance(getCurrent().getPaymentAmount());
        }

        custTran.setLogger(log);
        customerTransactionController.create(custTran);

        //Cashbook
        CashBook cashBook = new CashBook();
        cashBook.setDescription(description);
        cashBook.setCR(getCurrent().getPaymentAmount());
        cashBook.setDR(0);
        cashBook.setBillStat(getCurrent().getBillStatus());
        cashBook.setRelatedDate(getCurrent().getPaymentDate());
        cashBook.setLocation(getCurrent().getPaymentLocation());
        cashBook.setLogger(log);

        CashBookBalance cashBalance = getCashBookBalanceController().getCashBookBalanceObject(getCurrent().getPaymentLocation(), getCurrent().getBillStatus());

        if (cashBalance != null) {
            cashBalance.setCashBalance(cashBalance.getCashBalance() - getCurrent().getPaymentAmount());
            getCashBookBalanceController().edit(cashBalance);
            cashBook.setBalance(cashBalance.getCashBalance());
        } else {
            cashBook.setBalance(getCurrent().getPaymentAmount());
        }

        getCashbookController().create(cashBook);

        //Increment the the Document No 
        if (getCurrentSystemNumber() != null) {
            getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getIncrementedSystemNumber());
            getSystemNumbersController().edit(getCurrentSystemNumber());
        }
        getCashPaymentVoucherController().create(getCurrent());

        return "Home";
    }
    
    public String selectedGRNListNumbers() {
        String strGRNNumbers = "";
        for (GRN selectedGRN : getSelectedGRNs()) {
            strGRNNumbers = strGRNNumbers + (strGRNNumbers.isEmpty() ? "" : "/") + selectedGRN.getGrnNo();
        }
        return strGRNNumbers;
    }
    
    public String createVoucher(){
       String strDescription = "Cash Payament Voucher - " + getCurrent() + getCurrent().getPaymentDescription();
       return createGenericPayment(strDescription, TransactionTypes.CASHPAY);
    }
    public String createReceipSettle() {
        String strDescription = "Cash Payament Voucher (Settelment) - " + getCurrent() + " [ " + selectedGRNListNumbers() + " ] " + getCurrent().getPaymentDescription();
        createGenericPayment(strDescription, TransactionTypes.CASH_PAY_SETTLE);
        double allocatedAmount = 0;
        double availableAmount = getCurrent().getPaymentAmount();
        for (GRN selectedGRN : getSelectedGRNs()) {
            if (selectedGRN.getPaymentDetails() != null) {
                PaymentDetails paymentDetail = selectedGRN.getPaymentDetails();
                if (availableAmount >= selectedGRN.getTotalAmount()) {
                    paymentDetail.setSettledAmount(selectedGRN.getTotalAmount());
                    paymentDetail.setCreditAmount(0);
                    allocatedAmount = allocatedAmount + selectedGRN.getTotalAmount();
                    availableAmount = availableAmount - selectedGRN.getTotalAmount();
                } else if(availableAmount > 0) {
                    paymentDetail.setSettledAmount(availableAmount);
                    paymentDetail.setCreditAmount(selectedGRN.getTotalAmount() - availableAmount);
                    allocatedAmount = allocatedAmount + availableAmount;
                    availableAmount = availableAmount - availableAmount;
                }
                getPaymentDetailsController().edit(paymentDetail);
                getgRNController().edit(selectedGRN);
            }
           
        }
        return "Home";
    }
}
