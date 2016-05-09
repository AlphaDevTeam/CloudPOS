package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.*;
import com.AlphaDevs.cloud.web.Enums.ChequeStatus;
import com.AlphaDevs.cloud.web.Enums.CreditCardReceiptStatus;
import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 *
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@ManagedBean
@ViewScoped
public class GRNHandler {

    @EJB
    private ItemsController itemsController;
    @EJB
    private PaymentDetailsController paymentDetailsController;
    @EJB
    private CreditCardReceiptsController creditCardReceiptsController;
    @EJB
    private ChequesController chequesController;
    @EJB
    private SystemNumbersController systemNumbersController;
    @EJB
    private PropertiesController propertiesController;
    @EJB
    private PropertyManagerController propertyManagerController;
    @EJB
    private CashBookBalanceController cashBookBalanceController;
    @EJB
    private CustomerBalanceController customerBalanceController;
    @EJB
    private LoggerController loggerController;
    @EJB
    private CashbookController cashbookController;
    @EJB
    private ItemBincardController itemBincardController;
    @EJB
    private CustomerTransactionController customerTransactionController;
    @EJB
    private StockController stockController;
    @EJB
    private GrnDetailsController grnDetailsController;
    @EJB
    private SystemsController systemsController;
    @EJB
    private GRNController grnController;

    private Document currentDocument;
    private double cashAmount;
    private GRN current;
    private GRNDetails currentDetails;
    private List<GRNDetails> VirtualList;
    private GRNDetails selectedGrnData;
    private PaymentDetails paymentDetails;
    private Cheques receivedCheque;
    private CreditCardReceipts receivedCreditCardReceipts;
    private SystemNumbers currentSystemNumber;

    @PostConstruct
    public void init() {
        currentDocument = Document.GOODS_RECEIVED_NOTE;
        if (current == null) {
            current = new GRN();
        }
        if (currentDetails == null) {
            currentDetails = new GRNDetails();
        }
        if (selectedGrnData == null) {
            selectedGrnData = new GRNDetails();
        }
        if (paymentDetails == null) {
            paymentDetails = new PaymentDetails();
        }

        receivedCheque = new Cheques();
        receivedCreditCardReceipts = new CreditCardReceipts();
        VirtualList = new ArrayList<GRNDetails>();
        cashAmount = 0;
    }

    public GRNHandler() {
    }

    public ItemsController getItemsController() {
        return itemsController;
    }

    public void setItemsController(ItemsController itemsController) {
        this.itemsController = itemsController;
    }

    public String getGrnNumber() {
        setCurrentSystemNumber(null);
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get(AlphaConstant.SESSION_USER);
        if (loggedUser != null && getCurrent().getLocation() != null) {
            List<SystemNumbers> systemNumbers = getSystemNumbersController().findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getLocation(), getCurrentDocument());
            if (systemNumbers != null && !systemNumbers.isEmpty()) {
                setCurrentSystemNumber(systemNumbers.get(0));
                getCurrent().setGrnNo(getCurrentSystemNumber().getDocumentSystemNo());
            }
        }
        return getCurrentSystemNumber() != null ? getCurrentSystemNumber().getDocumentSystemNo() : "";
    }

    public CreditCardReceiptsController getCreditCardReceiptsController() {
        return creditCardReceiptsController;
    }

    public void setCreditCardReceiptsController(CreditCardReceiptsController creditCardReceiptsController) {
        this.creditCardReceiptsController = creditCardReceiptsController;
    }

    public CreditCardReceipts getReceivedCreditCardReceipts() {
        return receivedCreditCardReceipts;
    }

    public void setReceivedCreditCardReceipts(CreditCardReceipts receivedCreditCardReceipts) {
        this.receivedCreditCardReceipts = receivedCreditCardReceipts;
    }

    public ChequesController getChequesController() {
        return chequesController;
    }

    public Cheques getReceivedCheque() {
        return receivedCheque;
    }

    public void setReceivedCheque(Cheques receivedCheque) {
        this.receivedCheque = receivedCheque;
    }

    public void setChequesController(ChequesController chequesController) {
        this.chequesController = chequesController;
    }

    public SystemNumbersController getSystemNumbersController() {
        return systemNumbersController;
    }

    public void setSystemNumbersController(SystemNumbersController systemNumbersController) {
        this.systemNumbersController = systemNumbersController;
    }

    public PropertiesController getPropertiesController() {
        return propertiesController;
    }

    public void setPropertiesController(PropertiesController propertiesController) {
        this.propertiesController = propertiesController;
    }

    public PropertyManagerController getPropertyManagerController() {
        return propertyManagerController;
    }

    public void setPropertyManagerController(PropertyManagerController propertyManagerController) {
        this.propertyManagerController = propertyManagerController;
    }

    public PaymentDetailsController getPaymentDetailsController() {
        return paymentDetailsController;
    }

    public void setPaymentDetailsController(PaymentDetailsController paymentDetailsController) {
        this.paymentDetailsController = paymentDetailsController;
    }

    public CashBookBalanceController getCashBookBalanceController() {
        return cashBookBalanceController;
    }

    public void setCashBookBalanceController(CashBookBalanceController cashBookBalanceController) {
        this.cashBookBalanceController = cashBookBalanceController;
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

    public CashbookController getCashbookController() {
        return cashbookController;
    }

    public void setCashbookController(CashbookController cashbookController) {
        this.cashbookController = cashbookController;
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

    public StockController getStockController() {
        return stockController;
    }

    public void setStockController(StockController stockController) {
        this.stockController = stockController;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public GrnDetailsController getGrnDetailsController() {
        return grnDetailsController;
    }

    public void setGrnDetailsController(GrnDetailsController grnDetailsController) {
        this.grnDetailsController = grnDetailsController;
    }

    public SystemsController getSystemsController() {
        return systemsController;
    }

    public void setSystemsController(SystemsController systemsController) {
        this.systemsController = systemsController;
    }

    public GRNController getGrnController() {
        return grnController;
    }

    public void setGrnController(GRNController grnController) {
        this.grnController = grnController;
    }

    public Document getCurrentDocument() {
        return currentDocument;
    }

    public void setCurrentDocument(Document currentDocument) {
        this.currentDocument = currentDocument;
    }

    public SystemNumbers getCurrentSystemNumber() {
        return currentSystemNumber;
    }

    public void setCurrentSystemNumber(SystemNumbers currentSystemNumber) {
        this.currentSystemNumber = currentSystemNumber;
    }

    public void setGrnNumber(String grnNumber) {
        getCurrent().setGrnNo(grnNumber);
    }

    public GRNDetails getSelectedGrnData() {
        return selectedGrnData;
    }

    public void setSelectedGrnData(GRNDetails selectedGrnData) {
        this.selectedGrnData = selectedGrnData;
    }

    public GRN getCurrent() {
        return current;
    }

    public void setCurrent(GRN current) {
        this.current = current;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public void handleSelect(SelectEvent event) {
        getCurrentDetails().setGrnItemCost(getCurrentDetails().getGrnItem().getItemCost());
    }
    
    public void handleLocationSelect(SelectEvent event) {
         getCurrentDetails().setGrnItem(null);
         getCurrentDetails().setGrnItemCost(0);
    }

    public void handleKeyup(SelectEvent event) {
        System.out.println("handleKeyup :" + event.toString());
        System.out.println("handleKeyup :" + getCurrent().getLocation());
    }

    public String addTEst() {

        if (getVirtualList().isEmpty()) {
            MessageHelper.addErrorMessage("Error", "No Items Found!");
            return "";
        }

        //Saving Logger
        Logger logger = EntityHelper.createLogger("Good Received Note", getCurrent().getGrnNo(), TransactionTypes.GRN);
        getLoggerController().create(logger);

        //Saving Properties
        for (Properties properties : getCurrent().getExtraz()) {
            System.out.println("Trying Saving ... " + properties.getPropertyName() + " : " + properties.getPropertyValue());
            getPropertiesController().create(properties);
            System.out.println("Done");
        }

        //Saving GRN
        getCurrent().setBillStatus(getCurrent().getBillStatus());
        getCurrent().setGrnDetails(getVirtualList());
        getCurrent().setLogger(logger);
        getCurrent().setPaymentDetails(getPaymentDetails());
        getGrnController().create(getCurrent());

        //Process GRN Details
        for (GRNDetails grnDetail : getVirtualList()) {
            grnDetail.setRelatedGRN(getCurrent());

            //Update Stock
            Stock stock = getStockController().getItemStock(getCurrent().getLocation(), grnDetail.getGrnItem(),getCurrent().getBillStatus());
            stock.setStockLocation(getCurrent().getLocation());
            stock.setSockItem(grnDetail.getGrnItem());
            stock.setStockQty(stock.getStockQty() + grnDetail.getGrnQty());
            stock.setRelatedCompany(SessionDataHelper.getLoggedCompany(true));
            getStockController().edit(stock);

            //Item Bincard Entry 
            ItemBincard itemBin = new ItemBincard();
            itemBin.setDescription(getCurrentDocument().getDocumentDisplayName() + " - " + getCurrent());
            itemBin.setItem(grnDetail.getGrnItem());
            itemBin.setRelatedDate(getCurrent().getGrnDate());
            itemBin.setTrnNumber(getCurrent().toString());
            itemBin.setQty(grnDetail.getGrnQty());
            itemBin.setLog(logger);
            itemBin.setBillStat(getCurrent().getBillStatus());
            itemBin.setBalance(stock.getStockQty());
            getItemBincardController().create(itemBin);
        }

        //Customer Transaction - GRN
        CustomerTransaction customerTransactionEntry = getCustomerTransactionController().addCustomerTransactionEntry(getCurrentDocument().getDocumentDisplayName() + " - " + getCurrent(), getCurrent().getBillStatus(), logger, getCurrent().getSupplier(), getCurrent().getGrnDate(), 0,getCurrent().getTotalAmount());

        //Getting Cust Balance
        CustomerBalance customerBalance = getCustomerBalanceController().getCustomerBalanceObject(getCurrent().getSupplier(),getCurrent().getBillStatus());
        if (customerBalance != null) {
            customerBalance.setBalance(customerBalance.getBalance() - getCurrent().getTotalAmount());
            getCustomerBalanceController().edit(customerBalance);
        } else {
            customerBalance = new CustomerBalance(getCurrent().getSupplier(), (getCurrent().getTotalAmount() * -1));
            getCustomerBalanceController().create(customerBalance);
        }

        customerTransactionEntry.setBalance(customerBalance.getBalance());
        getCustomerTransactionController().edit(customerTransactionEntry);

        if (getPaymentDetails() != null) {
            double totalPay = 0;
            getPaymentDetails().setTotalAmount(getCurrent().getTotalAmount());
            getPaymentDetails().setLogger(logger);
            getPaymentDetails().setDocumentID(getCurrent().getId());
            getPaymentDetails().setDocumentType(getCurrentDocument());

            if (getReceivedCheque() != null && getReceivedCheque().getChequeAmount() != null) {
                Cheques relatedCheque = getReceivedCheque();
                if (relatedCheque.getChequeAmount() > 0) {
                    relatedCheque.setStatus(ChequeStatus.RECEIVED);
                    relatedCheque.setRelatedLocation(getCurrent().getLocation());
                    relatedCheque.setChequeRecDate(getCurrent().getGrnDate());
                    relatedCheque.setRelatedPayment(getPaymentDetails());
                    getChequesController().create(relatedCheque);
                    getPaymentDetails().setChequeAmount(getReceivedCheque().getChequeAmount());
                    getPaymentDetails().setRelatedCheque(relatedCheque);

                    //Customer Transaction - GRN - CHEQUE
                    CustomerTransaction customerTransactionEntryCheque = getCustomerTransactionController().addCustomerTransactionEntry("PAID - " + getCurrentDocument().getDocumentDisplayName() + " - CHEQUE - " + getCurrent(), getCurrent().getBillStatus(), logger, getCurrent().getSupplier(), getCurrent().getGrnDate(), getPaymentDetails().getChequeAmount(),0);
                    customerBalance.setBalance(customerBalance.getBalance() + getPaymentDetails().getChequeAmount());
                    customerTransactionEntryCheque.setBalance(customerBalance.getBalance());
                    getCustomerTransactionController().edit(customerTransactionEntryCheque);
                    
                    //Calculate Total
                    totalPay = totalPay +  getPaymentDetails().getChequeAmount();
                    
                }
            }

            if (getReceivedCreditCardReceipts() != null) {
                CreditCardReceipts relatedCreditCardReceipt = getReceivedCreditCardReceipts();
                if (relatedCreditCardReceipt.getAmount() > 0) {
                    relatedCreditCardReceipt.setReceiptStatus(CreditCardReceiptStatus.RECEIVED);
                    relatedCreditCardReceipt.setRelatedLocation(getCurrent().getLocation());
                    relatedCreditCardReceipt.setReceiptDate(getCurrent().getGrnDate());
                    relatedCreditCardReceipt.setRelatedPaymentDetails(getPaymentDetails());
                    relatedCreditCardReceipt.setRelatedLogger(logger);
                    getCreditCardReceiptsController().create(relatedCreditCardReceipt);
                    getPaymentDetails().setCreditCardAmount(relatedCreditCardReceipt.getAmount());
                    getPaymentDetails().setRelatedCreditCardReceipts(relatedCreditCardReceipt);

                    //Customer Transaction - GRN - CREDIT CARD
                    CustomerTransaction customerTransactionEntryCC = getCustomerTransactionController().addCustomerTransactionEntry("PAID - " + getCurrentDocument().getDocumentDisplayName() + " - CREDIT CARD - " + getCurrent(), getCurrent().getBillStatus(), logger, getCurrent().getSupplier(), getCurrent().getGrnDate(), getPaymentDetails().getCreditCardAmount(),0);
                    customerBalance.setBalance(customerBalance.getBalance() + getPaymentDetails().getCreditCardAmount());
                    customerTransactionEntryCC.setBalance(customerBalance.getBalance());
                    getCustomerTransactionController().edit(customerTransactionEntryCC);
                    
                    //Calculate Total
                    totalPay = totalPay +  getPaymentDetails().getCreditCardAmount();
                }
            }

            if (getPaymentDetails().getCashAmount() > 0) {

                //Customer Transaction - GRN - CASH
                CustomerTransaction customerTransactionEntryCash = getCustomerTransactionController().addCustomerTransactionEntry("PAID - " + getCurrentDocument().getDocumentDisplayName() + " - CASH - " + getCurrent(), getCurrent().getBillStatus(), logger, getCurrent().getSupplier(), getCurrent().getGrnDate(), getPaymentDetails().getCashAmount(),0);
                customerBalance.setBalance(customerBalance.getBalance() + getPaymentDetails().getCashAmount());
                customerTransactionEntryCash.setBalance(customerBalance.getBalance());
                getCustomerTransactionController().edit(customerTransactionEntryCash);

                //Cashbook
                CashBook cashBook = new CashBook();
                cashBook.setDescription(getCurrentDocument().getDocumentDisplayName() + " - " + getCurrent());
                cashBook.setCR(getPaymentDetails().getCashAmount());
                cashBook.setDR(0);
                cashBook.setBillStat(getCurrent().getBillStatus());
                cashBook.setRelatedDate(getCurrent().getGrnDate());
                cashBook.setLocation(getCurrent().getLocation());
                cashBook.setLogger(logger);

                
                CashBookBalance cashBalance = getCashBookBalanceController().getCashBookBalanceObject(getCurrent().getLocation(), getCurrent().getBillStatus());
                if (cashBalance != null) {
                    cashBalance.setCashBalance(cashBalance.getCashBalance() - getPaymentDetails().getCashAmount());
                    getCashBookBalanceController().edit(cashBalance);
                    cashBook.setBalance(cashBalance.getCashBalance());
                } else {
                    cashBook.setBalance(getPaymentDetails().getCashAmount());
                }

                getCashbookController().create(cashBook);
                //Calculate Total
                totalPay = totalPay +  getPaymentDetails().getCashAmount();
                

            }
            getCustomerBalanceController().edit(customerBalance);
            getPaymentDetails().setTrnType(TransactionTypes.GRN);
            getPaymentDetails().setSettledAmount(totalPay);
            getPaymentDetails().setCreditAmount(getPaymentDetails().getTotalAmount() - totalPay);
            getPaymentDetailsController().edit(getPaymentDetails());
        }

        if (getCurrentSystemNumber() != null) {
            getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getIncrementedSystemNumber());
            getSystemNumbersController().edit(getCurrentSystemNumber());
        }

        printReportDownload();
        setCurrent(new GRN());
        setCurrentDetails(new GRNDetails());
        setVirtualList(new ArrayList<GRNDetails>());
        setCashAmount(0);

        return "Home";
    }

    public List<GRN> getGrnList() {
        return grnController.findByLocation(getCurrent().getLocation());
    }

    public int getListCount() {
        return getCurrent().getExtraz().size();
    }

    public String rePrintGRN() {
        System.out.println("Reprinting GRN " + getCurrent().getId() + ":" + getCurrent().getGrnNo());
        printReportSpecificGrn(getCurrent());
        return "Home";
    }

    public void printReportSpecificGrn(GRN printGrn) {
        try {
            List<GRN> GrnList = new ArrayList<GRN>();
            GRN printableGrn = (GRN) grnController.find(printGrn.getId());
            System.out.println("Grn : Added for Reprint " + printableGrn.getGrnNo() + " With :" + printableGrn.getId());
            GrnList.add(printableGrn);
            JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(GrnList);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reports/GoodReceivedNote.jasper");
            System.out.println("Path : " + reportPath);
            JasperPrint jasPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollection);
            HttpServletResponse responce = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            responce.setContentType("application/pdf");
            String filename = new StringBuffer(reportPath).append(".pdf").toString();
            ServletOutputStream output = responce.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasPrint, output);
            responce.addHeader("Content-Disposition", "inline; filename=" + filename);
            //responce.addHeader("Content-disposition", "attachment; filename=CashBook.pdf");
            FacesContext.getCurrentInstance().responseComplete();
            System.out.println("Report Done");
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void printReportDownload() {
        try {
            List<GRN> GrnList = new ArrayList<GRN>();
            GRN nn = (GRN) grnController.find(getCurrent().getId());
            System.out.println("Grn : re d " + nn.getGrnNo() + " With :" + nn.getId());
            GrnList.add(nn);
            JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(GrnList);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reports/GoodReceivedNote.jasper");
            System.out.println("Path : " + reportPath);
            JasperPrint jasPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollection);
            HttpServletResponse responce = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            responce.setContentType("application/pdf");
            String filename = new StringBuffer(reportPath).append(".pdf").toString();
            ServletOutputStream output = responce.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasPrint, output);
            responce.addHeader("Content-Disposition", "inline; filename=" + filename);
            //responce.addHeader("Content-disposition", "attachment; filename=CashBook.pdf");
            FacesContext.getCurrentInstance().responseComplete();
            System.out.println("Report Done");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String removeItem(GRNDetails details) {

        for (int a = 0; a < getVirtualList().size(); a++) {

            if (getVirtualList().get(a) == details) {
                getVirtualList().remove(a);

                break;
            }
        }
        getCurrent().setTotalAmount(getTotal());

        return "#";
    }

    public String removeSelectedItem() {
        if (getSelectedGrnData() == null) {
            MessageHelper.addErrorMessage("Selection ", "No item Selected ");
            return "";
        } else {
            for (int a = 0; a < getVirtualList().size(); a++) {

                if (getVirtualList().get(a) == getSelectedGrnData()) {
                    getVirtualList().remove(a);

                    break;
                }
            }
            getCurrent().setTotalAmount(getTotal());
        }
        return "#";
    }

    public String loadRTE() {
        Systems sys = new Systems("Header");
        sys.setId(1L);
        systemsController.create(sys);
        System.out.println("Done");
        return "#";
    }

    public GRNDetails getCurrentDetails() {
        return currentDetails;
    }

    public void setCurrentDetails(GRNDetails currentDetails) {
        this.currentDetails = currentDetails;
    }

    public List<GRNDetails> getVirtualList() {
        return VirtualList;
    }

    public void setVirtualList(List<GRNDetails> VirtualList) {
        this.VirtualList = VirtualList;
    }

    public void addItem() {
        boolean isFound = false;

        if (currentDetails.getGrnItem() == null || currentDetails.getGrnItemCost() == 0 || currentDetails.getGrnQty() == 0) {
            MessageHelper.addErrorMessage("Error", "Please Fill All Details");
            return;
        }

        currentDetails.setRelatedGRN(getCurrent());

        for (GRNDetails det : getVirtualList()) {
            if (det.getGrnItem().getId() == currentDetails.getGrnItem().getId()) {
                det.setGrnQty(det.getGrnQty() + currentDetails.getGrnQty());
                isFound = true;
                break;
            }

        }
        if (!isFound) {
            getVirtualList().add(currentDetails);
        }

        currentDetails = new GRNDetails();

        System.out.println("Total : " + getTotal());
        getCurrent().setTotalAmount(getTotal());
        currentDetails = new GRNDetails();
    }

    public List<Items> autoCompleteItems(String query) {
        return getItemsController().findLike(query, getCurrent().getLocation());
    }

    public Double getTotal() {
        double TotalValue = 0;
        for (GRNDetails details : getVirtualList()) {
            //System.out.println("Det :  " + details.getGrnItem().getItemDescription() + " : " + details.getGrnItem().getItemCost());
            TotalValue = TotalValue + (details.getGrnItemCost() * details.getGrnQty());
        }
        return TotalValue;
    }

    public void onEdit(RowEditEvent event) {

        MessageHelper.addSuccessMessage(((GRNDetails) event.getObject()).getGrnItem().getItemCode() + " Updated!");
        for (GRNDetails det : getVirtualList()) {
            if (Objects.equals(det.getGrnItem().getId(), ((GRNDetails) event.getObject()).getGrnItem().getId())) {

                det.setGrnQty(((GRNDetails) event.getObject()).getGrnQty());
                det.setGrnItemCost(((GRNDetails) event.getObject()).getGrnItemCost());
                det.setGrnItemDiscountPerc(((GRNDetails) event.getObject()).getGrnItemDiscountPerc());
            }
        }

        getCurrent().setTotalAmount(getTotal());
    }

    public void onCancel(RowEditEvent event) {
        MessageHelper.addSuccessMessage(((GRNDetails) event.getObject()).getGrnItem().getItemCode() + "Not Updated!");

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

    public String preapareList(GRN grn) {
        setCurrent(grn);
        System.out.println("Grn Set " + grn.getGrnNo());
        return "#";
    }

    public String preapareList(Long id) {
        setCurrent(getGrnController().find(id));
        return "SpecificGRN";
    }

    public List<GRNDetails> getSpecificGrnList() {
        return grnController.findSpecific(getCurrent());
    }

    public List<Properties> getPropList() {
        if (getCurrent().getExtraz() == null && getPropertiesController() != null && getPropertiesController().findAll() != null) {
            List<Properties> propertyList = new ArrayList<>();
            for (PropertyManager propertyMng : getPropertyManagerController().findProperty(SessionDataHelper.getLoggedCompany(true), getCurrentDocument())) {
                Properties property = new Properties(propertyMng.getFieldName(), null);
                propertyList.add(property);
            }
            getCurrent().setExtraz(propertyList);
        }
        return getCurrent().getExtraz();
    }

}
