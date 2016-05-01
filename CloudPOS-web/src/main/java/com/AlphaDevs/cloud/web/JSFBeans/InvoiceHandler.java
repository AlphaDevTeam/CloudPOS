package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.CashBook;
import com.AlphaDevs.cloud.web.Entities.CashBookBalance;
import com.AlphaDevs.cloud.web.Entities.Cheques;
import com.AlphaDevs.cloud.web.Entities.CreditCardReceipts;
import com.AlphaDevs.cloud.web.Entities.CustomerBalance;
import com.AlphaDevs.cloud.web.Entities.CustomerTransaction;
import com.AlphaDevs.cloud.web.Entities.Invoice;
import com.AlphaDevs.cloud.web.Entities.InvoiceDetails;
import com.AlphaDevs.cloud.web.Entities.ItemBincard;
import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.PaymentDetails;
import com.AlphaDevs.cloud.web.Entities.Properties;
import com.AlphaDevs.cloud.web.Entities.PropertyManager;
import com.AlphaDevs.cloud.web.Entities.Stock;
import com.AlphaDevs.cloud.web.Entities.SystemNumbers;
import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Enums.BillStatus;
import com.AlphaDevs.cloud.web.Enums.ChequeStatus;
import com.AlphaDevs.cloud.web.Enums.CreditCardReceiptStatus;
import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.CashBookBalanceController;
import com.AlphaDevs.cloud.web.SessionBean.CashbookController;
import com.AlphaDevs.cloud.web.SessionBean.ChequesController;
import com.AlphaDevs.cloud.web.SessionBean.CreditCardReceiptsController;
import com.AlphaDevs.cloud.web.SessionBean.CustomerBalanceController;
import com.AlphaDevs.cloud.web.SessionBean.CustomerTransactionController;
import com.AlphaDevs.cloud.web.SessionBean.InvoiceController;
import com.AlphaDevs.cloud.web.SessionBean.ItemBincardController;
import com.AlphaDevs.cloud.web.SessionBean.ItemsController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import com.AlphaDevs.cloud.web.SessionBean.PaymentDetailsController;
import com.AlphaDevs.cloud.web.SessionBean.PropertiesController;
import com.AlphaDevs.cloud.web.SessionBean.PropertyManagerController;
import com.AlphaDevs.cloud.web.SessionBean.StockController;
import com.AlphaDevs.cloud.web.SessionBean.SystemNumbersController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Mihindu Gajaba Karunarathne Alpha Development Team (Pvt) Ltd
 *
 */
@ManagedBean
@SessionScoped
public class InvoiceHandler {
    @EJB
    private ItemsController itemsController;
    @EJB
    private CreditCardReceiptsController creditCardReceiptsController;
    @EJB
    private ChequesController chequesController;
    @EJB
    private SystemNumbersController systemNumbersController;
    @EJB
    private PaymentDetailsController paymentDetailsController;
    @EJB
    private ItemBincardController itemBincardController;
    @EJB
    private CustomerTransactionController customerTransactionController;
    @EJB
    private CustomerBalanceController customerBalanceController;
    @EJB
    private StockController stockController;
    @EJB
    private LoggerController loggerController;
    @EJB
    private CashbookController cashbookController;
    @EJB
    private CashBookBalanceController cashBookBalanceController;
    @EJB
    private PropertiesController propertiesController;
    @EJB
    private PropertyManagerController propertyManagerController;
    @EJB
    private InvoiceController invoiceController;
    private SystemNumbers currentSystemNumber;
    private Invoice current;
    private InvoiceDetails currentDetails;
    private List<InvoiceDetails> virtualList;
    private InvoiceDetails selectedInvoiceDetails;
    private PaymentDetails paymentDetails;
    private boolean bypassStock;
    private Document currentDocument;
    private Cheques receivedCheque;
    private CreditCardReceipts receivedCreditCardReceipts;

    @PostConstruct
    public void init() {
        setCurrentDocument(Document.INVOICE);
        setBypassStock(false);
        if (current == null) {
            current = new Invoice();
        }
        if (currentDetails == null) {
            currentDetails = new InvoiceDetails();
        }
        if (selectedInvoiceDetails == null) {
            selectedInvoiceDetails = new InvoiceDetails();
        }
        virtualList = new ArrayList<InvoiceDetails>();
        paymentDetails = new PaymentDetails();
        receivedCheque = new Cheques();
        receivedCreditCardReceipts = new CreditCardReceipts();
    }

    public InvoiceHandler() {

    }

    public boolean isBypassStock() {
        return bypassStock;
    }

    public void setBypassStock(boolean bypassStock) {
        this.bypassStock = bypassStock;
    }

    public CreditCardReceiptsController getCreditCardReceiptsController() {
        return creditCardReceiptsController;
    }

    public void setCreditCardReceiptsController(CreditCardReceiptsController creditCardReceiptsController) {
        this.creditCardReceiptsController = creditCardReceiptsController;
    }

    public ChequesController getChequesController() {
        return chequesController;
    }

    public ItemsController getItemsController() {
        return itemsController;
    }

    public void setItemsController(ItemsController itemsController) {
        this.itemsController = itemsController;
    }
    
    public void setChequesController(ChequesController chequesController) {
        this.chequesController = chequesController;
    }

    public Cheques getReceivedCheque() {
        return receivedCheque;
    }

    public void setReceivedCheque(Cheques receivedCheque) {
        this.receivedCheque = receivedCheque;
    }

    public CreditCardReceipts getReceivedCreditCardReceipts() {
        return receivedCreditCardReceipts;
    }

    public void setReceivedCreditCardReceipts(CreditCardReceipts receivedCreditCardReceipts) {
        this.receivedCreditCardReceipts = receivedCreditCardReceipts;
    }

    public Document getCurrentDocument() {
        return currentDocument;
    }

    public final void setCurrentDocument(Document currentDocument) {
        this.currentDocument = currentDocument;
    }

    public SystemNumbersController getSystemNumbersController() {
        return systemNumbersController;
    }

    public void setSystemNumbersController(SystemNumbersController systemNumbersController) {
        this.systemNumbersController = systemNumbersController;
    }

    public SystemNumbers getCurrentSystemNumber() {
        return currentSystemNumber;
    }

    public void setCurrentSystemNumber(SystemNumbers currentSystemNumber) {
        this.currentSystemNumber = currentSystemNumber;
    }

    public PaymentDetailsController getPaymentDetailsController() {
        return paymentDetailsController;
    }

    public void setPaymentDetailsController(PaymentDetailsController paymentDetailsController) {
        this.paymentDetailsController = paymentDetailsController;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
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

    public PropertiesController getPropertiesController() {
        return propertiesController;
    }

    public void setPropertiesController(PropertiesController propertiesController) {
        this.propertiesController = propertiesController;
    }

    public Invoice getCurrent() {
        return current;
    }

    public void setCurrent(Invoice current) {
        this.current = current;
    }

    public InvoiceController getInvoiceController() {
        return invoiceController;
    }

    public void setInvoiceController(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }

    public InvoiceDetails getCurrentDetails() {
        return currentDetails;
    }

    public void setCurrentDetails(InvoiceDetails currentDetails) {
        this.currentDetails = currentDetails;
    }

    public PropertyManagerController getPropertyManagerController() {
        return propertyManagerController;
    }

    public void setPropertyManagerController(PropertyManagerController propertyManagerController) {
        this.propertyManagerController = propertyManagerController;
    }

    public ItemBincardController getItemBincardController() {
        return itemBincardController;
    }

    public void setItemBincardController(ItemBincardController itemBincardController) {
        this.itemBincardController = itemBincardController;
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

    public StockController getStockController() {
        return stockController;
    }

    public void setStockController(StockController stockController) {
        this.stockController = stockController;
    }

    public List<InvoiceDetails> getVirtualList() {
        return virtualList;
    }

    public void setVirtualList(List<InvoiceDetails> virtualList) {
        this.virtualList = virtualList;
    }

    public InvoiceDetails getSelectedInvoiceDetails() {
        return selectedInvoiceDetails;
    }

    public void setSelectedInvoiceDetails(InvoiceDetails selectedInvoiceDetails) {
        this.selectedInvoiceDetails = selectedInvoiceDetails;
    }

    public List<Invoice> getList() {
        return invoiceController.findAll();
    }

    public void handleSelect(SelectEvent event) {
        currentDetails.setPrice(currentDetails.getItem().getUnitPrice());
    }

    public String getBillNumber() {
        setCurrentSystemNumber(null);
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get(AlphaConstant.SESSION_USER);
        if (loggedUser != null && getCurrent().getLocation() != null) {
            List<SystemNumbers> systemNumbers = getSystemNumbersController().findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getLocation(), getCurrentDocument());
            if (systemNumbers != null && !systemNumbers.isEmpty()) {
                setCurrentSystemNumber(systemNumbers.get(0));
                getCurrent().setBillNo(getCurrentSystemNumber().getDocumentSystemNo());
            }

        }
        return getCurrentSystemNumber() != null ? getCurrentSystemNumber().getDocumentSystemNo() : "";

    }

    public void setBillNumber(String billNumbere) {
        getCurrent().setBillNo(billNumbere);
    }

    public List<Properties> getPropList() {

        if (getCurrent().getExtraz() == null && getPropertiesController() != null && getPropertiesController().findAll() != null) {
            List<Properties> propertyList = new ArrayList<Properties>();
            for (PropertyManager propertyMng : getPropertyManagerController().findProperty(SessionDataHelper.getLoggedCompany(true), getCurrentDocument())) {
                Properties property = new Properties(propertyMng.getFieldName(), null);
                propertyList.add(property);
            }
            getCurrent().setExtraz(propertyList);
        }

        return getCurrent().getExtraz();
    }

    public void handleLocationSelect(SelectEvent event) {
        getCurrentDetails().setItem(null);
        getCurrentDetails().setPrice(0);
    }
    
    public List<Items> autoCompleteItems(String query) {
        return getItemsController().findLike(query, getCurrent().getLocation());
    }

    public void addItem() {
        boolean isFound = false;

        if (currentDetails.getItem() == null || currentDetails.getPrice() == 0 || currentDetails.getItemQty() == 0) {
            MessageHelper.addErrorMessage("Error", "Please Fill All Details");
            return;
        }

        currentDetails.setInvoice(current);

        for (InvoiceDetails invDetails : getVirtualList()) {
            if (invDetails.getItem().getId() == currentDetails.getItem().getId()) {
                invDetails.setItemQty(invDetails.getItemQty() + currentDetails.getItemQty());
                isFound = true;
                break;
            }

        }
        if (!isFound) {
            getVirtualList().add(currentDetails);
        }

        System.out.println("Total : " + getTotal());
        current.setTotalAmount(getTotal());
        currentDetails = new InvoiceDetails();
    }

    public Double getTotal() {
        double TotalValue = 0;
        for (InvoiceDetails invDetails : getVirtualList()) {
            //System.out.println("Det :  " + details.getGrnItem().getItemDescription() + " : " + details.getGrnItem().getItemCost());
            TotalValue = TotalValue + (invDetails.getPrice() * invDetails.getItemQty());
        }
        return TotalValue;
    }

    public void onEdit(RowEditEvent event) {

        MessageHelper.addSuccessMessage(((InvoiceDetails) event.getObject()).getItem().getItemCode() + " Updated!");
        for (InvoiceDetails invDetails : getVirtualList()) {
            if (invDetails.getItem().getId() == ((InvoiceDetails) event.getObject()).getItem().getId()) {

                invDetails.setItemQty(((InvoiceDetails) event.getObject()).getItemQty());
                invDetails.setPrice(((InvoiceDetails) event.getObject()).getPrice());

            }
        }

        current.setTotalAmount(getTotal());
    }

    public void onCancel(RowEditEvent event) {
        MessageHelper.addSuccessMessage(((InvoiceDetails) event.getObject()).getItem().getItemCode() + "Not Updated!");

    }

    public void validateItems(FacesContext context, UIComponent component, Object value) {
        if (getVirtualList().isEmpty()) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Item Empty");
            message.setDetail("Item is Empty");
            context.addMessage(null, message);
            throw new ValidatorException(message);
        }
    }

    public String removeSelectedItem() {
        if (getSelectedInvoiceDetails() == null) {
            MessageHelper.addErrorMessage("Selection ", "No item Selected ");
            return "";
        } else {
            for (int a = 0; a < getVirtualList().size(); a++) {

                if (getVirtualList().get(a) == getSelectedInvoiceDetails()) {
                    getVirtualList().remove(a);
                    break;
                }
            }
            current.setTotalAmount(getTotal());
        }
        return "#";
    }

    public String saveInvoice() {

        if (getVirtualList().isEmpty()) {
            MessageHelper.addErrorMessage("Error", "No Items Found!");
            return "#";
        }

        //Saving Logger
        Logger logger = EntityHelper.createLogger("Commercial Invoice", getCurrent().getBillNo(), TransactionTypes.SALE);
        getLoggerController().create(logger);

        //Saving Properties
        for (Properties properties : getCurrent().getExtraz()) {
            System.out.println("Trying Saving ... " + properties.getPropertyName() + " : " + properties.getPropertyValue());
            getPropertiesController().create(properties);
            System.out.println("Done");
        }

        //Saving Invoice
        getCurrent().setBillStatus(getCurrent().getBillStatus());
        getCurrent().setInvDetails(getVirtualList());
        getCurrent().setLogger(logger);
        getCurrent().setPaymentDetails(getPaymentDetails());
        getInvoiceController().create(getCurrent());

        for (InvoiceDetails invDetails : getVirtualList()) {
            invDetails.setInvoice(getCurrent());

            if (!isBypassStock()) {
                Stock stock = getStockController().getItemStock(getCurrent().getLocation(), invDetails.getItem());
                stock.setStockLocation(getCurrent().getLocation());
                stock.setSockItem(invDetails.getItem());
                stock.setStockQty(stock.getStockQty() - invDetails.getItemQty());
                stock.setRelatedCompany(SessionDataHelper.getLoggedCompany(true));
                getStockController().edit(stock);

                //Item Bincard Entry 
                ItemBincard itemBin = new ItemBincard();
                itemBin.setDescription(getCurrentDocument().getDocumentDisplayName() + " - " + getCurrent());
                itemBin.setItem(invDetails.getItem());
                itemBin.setRelatedDate(getCurrent().getTrnDate());
                itemBin.setTrnNumber(getCurrent().toString());
                itemBin.setQty(invDetails.getItemQty() * -1);
                itemBin.setLog(logger);
                itemBin.setBalance(stock.getStockQty());
                getItemBincardController().create(itemBin);
            }

        }

        //Customer Transaction - Commercial Invoice
        CustomerTransaction customerTransactionEntry = getCustomerTransactionController().addCustomerTransactionEntry(getCurrentDocument().getDocumentDisplayName() + " - " + getCurrent(), getCurrent().getBillStatus(), logger, getCurrent().getCustomer(), getCurrent().getTrnDate(), 0, getCurrent().getTotalAmount());

        //Getting Cust Balance
        CustomerBalance customerBalance = getCustomerBalanceController().getCustomerBalanceObject(getCurrent().getCustomer());
        if (customerBalance != null) {
            customerBalance.setBalance(customerBalance.getBalance() - getCurrent().getTotalAmount());
            getCustomerBalanceController().edit(customerBalance);
        } else {
            customerBalance = new CustomerBalance(getCurrent().getCustomer(), getCurrent().getTotalAmount());
            getCustomerBalanceController().create(customerBalance);
        }
        customerTransactionEntry.setBalance(customerBalance.getBalance());
        getCustomerTransactionController().edit(customerTransactionEntry);

        if (getPaymentDetails() != null) {

            getPaymentDetails().setTotalAmount(getCurrent().getTotalAmount());
            getPaymentDetails().setLogger(logger);
            getPaymentDetails().setDocumentID(getCurrent().getId());
            getPaymentDetails().setDocumentType(getCurrentDocument());

            if (getReceivedCheque() != null && getReceivedCheque().getChequeAmount() != null) {
                Cheques relatedCheque = getReceivedCheque();
                if (relatedCheque.getChequeAmount() > 0) {
                    relatedCheque.setStatus(ChequeStatus.RECEIVED);
                    relatedCheque.setRelatedLocation(getCurrent().getLocation());
                    relatedCheque.setChequeRecDate(getCurrent().getTrnDate());
                    relatedCheque.setRelatedPayment(getPaymentDetails());
                    getChequesController().create(relatedCheque);
                    getPaymentDetails().setChequeAmount(getReceivedCheque().getChequeAmount());
                    getPaymentDetails().setRelatedCheque(relatedCheque);

                    //Customer Transaction - GRN - CHEQUE
                    CustomerTransaction customerTransactionEntryCheque = getCustomerTransactionController().addCustomerTransactionEntry("PAID - " + getCurrentDocument().getDocumentDisplayName() + " - CHEQUE - " + getCurrent(), getCurrent().getBillStatus(), logger, getCurrent().getCustomer(), getCurrent().getTrnDate(), getPaymentDetails().getChequeAmount(), 0);
                    customerBalance.setBalance(customerBalance.getBalance() + getPaymentDetails().getChequeAmount());
                    customerTransactionEntryCheque.setBalance(customerBalance.getBalance());
                    getCustomerTransactionController().edit(customerTransactionEntryCheque);
                }
            }

            if (getReceivedCreditCardReceipts() != null) {
                CreditCardReceipts relatedCreditCardReceipt = getReceivedCreditCardReceipts();
                if (relatedCreditCardReceipt.getAmount() > 0) {
                    relatedCreditCardReceipt.setReceiptStatus(CreditCardReceiptStatus.RECEIVED);
                    relatedCreditCardReceipt.setRelatedLocation(getCurrent().getLocation());
                    relatedCreditCardReceipt.setReceiptDate(getCurrent().getTrnDate());
                    relatedCreditCardReceipt.setRelatedPaymentDetails(getPaymentDetails());
                    relatedCreditCardReceipt.setRelatedLogger(logger);
                    getCreditCardReceiptsController().create(relatedCreditCardReceipt);
                    getPaymentDetails().setCreditCardAmount(relatedCreditCardReceipt.getAmount());
                    getPaymentDetails().setRelatedCreditCardReceipts(relatedCreditCardReceipt);

                    //Customer Transaction - GRN - CREDIT CARD
                    CustomerTransaction customerTransactionEntryCC = getCustomerTransactionController().addCustomerTransactionEntry("PAID - " + getCurrentDocument().getDocumentDisplayName() + " - CREDIT CARD - " + getCurrent(), getCurrent().getBillStatus(), logger, getCurrent().getCustomer(), getCurrent().getTrnDate(), getPaymentDetails().getCreditCardAmount(), 0);
                    customerBalance.setBalance(customerBalance.getBalance() + getPaymentDetails().getCreditCardAmount());
                    customerTransactionEntryCC.setBalance(customerBalance.getBalance());
                    getCustomerTransactionController().edit(customerTransactionEntryCC);
                }
            }

            if (getPaymentDetails().getCashAmount() > 0) {

                //Customer Transaction - Commercial Invoice - CASH
                CustomerTransaction customerTransactionEntryCash = getCustomerTransactionController().addCustomerTransactionEntry("PAID - " + getCurrentDocument().getDocumentDisplayName() + " - CASH - " + getCurrent(), getCurrent().getBillStatus(), logger, getCurrent().getCustomer(), getCurrent().getTrnDate(), getPaymentDetails().getCashAmount(), 0);
                customerBalance.setBalance(customerBalance.getBalance() + getPaymentDetails().getCashAmount());
                customerTransactionEntryCash.setBalance(customerBalance.getBalance());
                getCustomerTransactionController().edit(customerTransactionEntryCash);

                //Cashbook
                CashBook cashBook = new CashBook();
                cashBook.setDescription(getCurrentDocument().getDocumentDisplayName() + " - " + getCurrent());
                cashBook.setDR(getPaymentDetails().getCashAmount());
                cashBook.setCR(0);
                cashBook.setRelatedDate(getCurrent().getTrnDate());
                cashBook.setLocation(getCurrent().getLocation());
                cashBook.setLogger(logger);

                CashBookBalance cashBalance = getCashBookBalanceController().getCashBookBalanceObject(getCurrent().getLocation(), getCurrent().getBillStatus());
                if (cashBalance != null) {
                    cashBalance.setCashBalance(cashBalance.getCashBalance() + getPaymentDetails().getCashAmount());
                    getCashBookBalanceController().edit(cashBalance);
                    cashBook.setBalance(cashBalance.getCashBalance());
                } else {
                    cashBook.setBalance(getPaymentDetails().getCashAmount());
                }

                getCashbookController().create(cashBook);

            }
            getCustomerBalanceController().edit(customerBalance);
            getPaymentDetails().setTrnType(TransactionTypes.SALE);
            getPaymentDetailsController().edit(getPaymentDetails());

        }

        //Increment the the Document No 
        if (getCurrentSystemNumber() != null) {
            getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getIncrementedSystemNumber());
            getSystemNumbersController().edit(getCurrentSystemNumber());
        }

        printReportDownload();
        setReceivedCheque(new Cheques());
        setReceivedCreditCardReceipts(new CreditCardReceipts());
        setCurrent(new Invoice());
        setCurrentDetails(new InvoiceDetails());
        setVirtualList(new ArrayList<InvoiceDetails>());
        setPaymentDetails(new PaymentDetails());
        return "Home";
    }

    public void printReportDownload() {
        try {
//            List<GRN> GrnList = new ArrayList<GRN>();
//            GRN nn = (GRN) gRNController.find(getCurrent().getId());
//            System.out.println("Grn : re d " + nn.getGrnNo() + " With :" + nn.getId());
//            GrnList.add(nn);
//            JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(GrnList);
//            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reports/GrnReport.jasper");
//            System.out.println("Path : " + reportPath);
//            JasperPrint jasPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollection);
//            HttpServletResponse responce = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//            responce.setContentType("application/pdf");
//            String filename = new StringBuffer(reportPath).append(".pdf").toString();
//            ServletOutputStream output = responce.getOutputStream();
//            JasperExportManager.exportReportToPdfStream(jasPrint, output);
//            responce.addHeader("Content-Disposition", "inline; filename=" + filename);
//            //responce.addHeader("Content-disposition", "attachment; filename=CashBook.pdf");
            FacesContext.getCurrentInstance().responseComplete();
            System.out.println("Report Done");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void typeNumber() {
        String s1 = new String().valueOf(currentDetails.getItemQty());
        //   String s2 = new String().valueOf(currentDetails.setItemQty(2));

        currentDetails.setItemQty(2);
        System.out.println(currentDetails.getItemQty());
    }
}
