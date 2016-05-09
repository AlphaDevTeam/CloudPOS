package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.CashBook;
import com.AlphaDevs.cloud.web.Entities.CashBookBalance;
import com.AlphaDevs.cloud.web.Entities.CashReceivedVoucher;
import com.AlphaDevs.cloud.web.Entities.CustomerBalance;
import com.AlphaDevs.cloud.web.Entities.CustomerTransaction;
import com.AlphaDevs.cloud.web.Entities.Invoice;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.PaymentDetails;
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
import com.AlphaDevs.cloud.web.SessionBean.InvoiceController;
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
public class CashReceiptsVoucherHandler {
    @EJB
    private InvoiceController invoiceController;
    @EJB
    private PaymentDetailsController paymentDetailsController;
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
    private List<Invoice> selectedInvoices;

    @PostConstruct
    public void init() {
        setCurrentDocument(Document.CASH_RECEIPT_CUST);
        current = new CashReceivedVoucher();
    }

    public CashReceiptsVoucherHandler() {
    }

    public PaymentDetailsController getPaymentDetailsController() {
        return paymentDetailsController;
    }

    public void setPaymentDetailsController(PaymentDetailsController paymentDetailsController) {
        this.paymentDetailsController = paymentDetailsController;
    }

    public InvoiceController getInvoiceController() {
        return invoiceController;
    }

    public void setInvoiceController(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }

    public CashReceivedVoucherController getCashReceivedVoucherController() {
        return cashReceivedVoucherController;
    }

    public List<Invoice> getSelectedInvoices() {
        return selectedInvoices;
    }

    public String selectedInvoiceList() {
        String strInvNumbers = "";
        for (Invoice selectedInvoice : getSelectedInvoices()) {
            strInvNumbers = strInvNumbers + (strInvNumbers.isEmpty() ? "" : "/") + selectedInvoice.getBillNo();
        }
        return strInvNumbers;
    }

    public void setSelectedInvoices(List<Invoice> selectedInvoices) {
        this.selectedInvoices = selectedInvoices;
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

    public List<Invoice> getPendingInvoices() {
        return getPaymentDetailsController().findPendingInvoices(getCurrent().getReceiptLocation(), getCurrent().getRelatedSupplier());
    }

    public void calculateSelectedDocumentAmounts() {
        double totalAmout = 0;
        for (Invoice selectedInvoice : getSelectedInvoices()) {
            totalAmout = totalAmout + selectedInvoice.getTotalAmount();
        }
        getCurrent().setReceiptAmount(totalAmout);
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

    public String getReceiptNumber() {
        setCurrentSystemNumber(null);
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get(AlphaConstant.SESSION_USER);
        if (loggedUser != null && getCurrent().getReceiptLocation() != null) {
            List<SystemNumbers> systemNumbers = getSystemNumbersController().findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getReceiptLocation(), getCurrentDocument());
            if (systemNumbers != null && !systemNumbers.isEmpty()) {
                setCurrentSystemNumber(systemNumbers.get(0));
                getCurrent().setReceiptNumber(getCurrentSystemNumber().getDocumentSystemNo());
            }
        }
        return getCurrentSystemNumber() != null ? getCurrentSystemNumber().getDocumentSystemNo() : "";
    }

    public String createReceipSettle() {
        String strDescription = "Cash Receipt Voucher (Settelment) - " + getCurrent() + " [ " + selectedInvoiceList() + " ] " + getCurrent().getReceiptDescription();
        createGenericReceipt(strDescription, TransactionTypes.CASH_REC_SETTLE);
        double allocatedAmount = 0;
        double availableAmount = getCurrent().getReceiptAmount();
        for (Invoice selectedInvoice : getSelectedInvoices()) {
            if (selectedInvoice.getPaymentDetails() != null) {
                PaymentDetails paymentDetail = selectedInvoice.getPaymentDetails();
                if (availableAmount >= selectedInvoice.getTotalAmount()) {
                    paymentDetail.setSettledAmount(selectedInvoice.getTotalAmount());
                    paymentDetail.setCreditAmount(0);
                    allocatedAmount = allocatedAmount + selectedInvoice.getTotalAmount();
                    availableAmount = availableAmount - selectedInvoice.getTotalAmount();
                } else if(availableAmount > 0) {
                    paymentDetail.setSettledAmount(availableAmount);
                    paymentDetail.setCreditAmount(selectedInvoice.getTotalAmount() - availableAmount);
                    allocatedAmount = allocatedAmount + availableAmount;
                    availableAmount = availableAmount - availableAmount;
                }
                getPaymentDetailsController().edit(paymentDetail);
                getInvoiceController().edit(selectedInvoice);
            }
           
        }
        return "Home";
    }

    public void setReceiptNumber(String receiptNumber) {
        getCurrent().setReceiptNumber(receiptNumber);
    }

    public String createReceipt() {
        String strDescription = "Cash Receipt Voucher - " + getCurrent() + getCurrent().getReceiptDescription();
        return createGenericReceipt(strDescription, TransactionTypes.CASHREC);
    }

    private String createGenericReceipt(String description, TransactionTypes trnType) {
        //Creating Logger
        Logger log = EntityHelper.createLogger(description, getCurrent().getReceiptNumber(), trnType);
        getLoggerController().create(log);
        getCurrent().setRelatedLogger(log);

        getCurrent().setRelatedCompany(EntityHelper.getLoggedCompany());

        //Customer Transaction
        CustomerTransaction custTran = new CustomerTransaction();
        custTran.setDescription(description);
        custTran.setSupplier(getCurrent().getRelatedSupplier());
        custTran.setDR(0);
        custTran.setBillStat(getCurrent().getBillStatus());
        custTran.setDate(getCurrent().getReceiptDate());
        custTran.setCR(getCurrent().getReceiptAmount());

        //Getting Cust Balance
        CustomerBalance Balance = getCustomerBalanceController().getCustomerBalanceObject(getCurrent().getRelatedSupplier(), getCurrent().getBillStatus());
        if (Balance != null) {
            Balance.setBalance(Balance.getBalance() - getCurrent().getReceiptAmount());
            getCustomerBalanceController().edit(Balance);
            custTran.setBalance(Balance.getBalance());

        } else {
            custTran.setBalance(getCurrent().getReceiptAmount());
        }

        custTran.setLogger(log);
        getCustomerTransactionController().create(custTran);

        //Cashbook
        CashBook cashBook = new CashBook();
        cashBook.setDescription(description);
        cashBook.setCR(0);
        cashBook.setBillStat(getCurrent().getBillStatus());
        cashBook.setDR(getCurrent().getReceiptAmount());
        cashBook.setRelatedDate(getCurrent().getReceiptDate());
        cashBook.setLocation(getCurrent().getReceiptLocation());
        cashBook.setLogger(log);

        CashBookBalance cashBalance = getCashBookBalanceController().getCashBookBalanceObject(getCurrent().getReceiptLocation(), getCurrent().getBillStatus());

        if (cashBalance != null) {
            cashBalance.setCashBalance(cashBalance.getCashBalance() + getCurrent().getReceiptAmount());
            getCashBookBalanceController().edit(cashBalance);
            cashBook.setBalance(cashBalance.getCashBalance());
        } else {
            cashBook.setBalance(getCurrent().getReceiptAmount());
        }

        getCashbookController().create(cashBook);

        //Increment the the Document No 
        if (getCurrentSystemNumber() != null) {
            getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getIncrementedSystemNumber());
            getSystemNumbersController().edit(getCurrentSystemNumber());
        }
        getCashReceivedVoucherController().create(getCurrent());

        return "Home";
    }

}
