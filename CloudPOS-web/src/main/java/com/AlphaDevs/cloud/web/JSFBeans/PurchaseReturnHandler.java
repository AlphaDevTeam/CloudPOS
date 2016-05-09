package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.*;
import com.AlphaDevs.cloud.web.Enums.BillStatus;
import com.AlphaDevs.cloud.web.Enums.ChequeStatus;
import com.AlphaDevs.cloud.web.Enums.CreditCardReceiptStatus;
import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Mihindu Gajaba Karunarathne Alpha Development Team (Pvt) Ltd
 *
 */
@ManagedBean
@ViewScoped
public class PurchaseReturnHandler {

    @EJB
    private PropertiesController propertiesController;
    @EJB
    private PropertyManagerController propertyManagerController;
    @EJB
    private ItemsController itemsController;
    @EJB
    private SystemNumbersController systemNumbersController;
    @EJB
    private CustomerTransactionController customerTransactionController;
    @EJB
    private CustomerBalanceController customerBalanceController;
    @EJB
    private CashbookController cashbookController;
    @EJB
    private CashBookBalanceController cashBookBalanceController;
    @EJB
    private ItemBincardController itemBincardController;
    @EJB
    private StockController stockController;
    @EJB
    private LoggerController loggerController;
    @EJB
    private PurchaseReturnController purchaseReturnController;

    private PurchaseReturn current;
    private PurchaseReturnDetails currentDetails;
    private List<PurchaseReturnDetails> VirtualList;
    private PurchaseReturnDetails selectedGrnData;
    private SystemNumbers currentSystemNumber;
    private Document currentDocument;

    @PostConstruct
    public void init() {
        setCurrentDocument(Document.GOODS_RECEIVED_NOTE_RETURN);
        if (current == null) {
            current = new PurchaseReturn();
        }
        if (currentDetails == null) {
            currentDetails = new PurchaseReturnDetails();
        }
        if (selectedGrnData == null) {
            selectedGrnData = new PurchaseReturnDetails();
        }
        VirtualList = new ArrayList<>();
    }

    public PurchaseReturnHandler() {

    }

    public ItemsController getItemsController() {
        return itemsController;
    }

    public void setItemsController(ItemsController itemsController) {
        this.itemsController = itemsController;
    }

    public Document getCurrentDocument() {
        return currentDocument;
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

    public void setCurrentDocument(Document currentDocument) {
        this.currentDocument = currentDocument;
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

    public ItemBincardController getItemBincardController() {
        return itemBincardController;
    }

    public void setItemBincardController(ItemBincardController itemBincardController) {
        this.itemBincardController = itemBincardController;
    }

    public StockController getStockController() {
        return stockController;
    }

    public void setStockController(StockController stockController) {
        this.stockController = stockController;
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

    public SystemNumbersController getSystemNumbersController() {
        return systemNumbersController;
    }

    public void setSystemNumbersController(SystemNumbersController systemNumbersController) {
        this.systemNumbersController = systemNumbersController;
    }

    public void handleSelect(SelectEvent event) {
        getCurrentDetails().setGrnItemCost(getCurrentDetails().getGrnItem().getItemCost());
    }

    public void handleLocationSelect(SelectEvent event) {
        getCurrentDetails().setGrnItem(null);
        getCurrentDetails().setGrnItemCost(0);
    }

    public List<Items> autoCompleteItems(String query) {
        return getItemsController().findLike(query, getCurrent().getLocation());
    }

    public void handleKeyup(SelectEvent event) {
        System.out.println("handleKeyup :" + event.toString());
        System.out.println("handleKeyup :" + getCurrent().getLocation());
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

    public List<PurchaseReturnDetails> getVirtualList() {
        return VirtualList;
    }

    public void setVirtualList(List<PurchaseReturnDetails> VirtualList) {
        this.VirtualList = VirtualList;
    }

    public PurchaseReturnDetails getCurrentDetails() {
        return currentDetails;
    }

    public void setCurrentDetails(PurchaseReturnDetails currentDetails) {
        this.currentDetails = currentDetails;
    }

    public PurchaseReturnController getPurchaseReturnController() {
        return purchaseReturnController;
    }

    public void setPurchaseReturnController(PurchaseReturnController purchaseReturnController) {
        this.purchaseReturnController = purchaseReturnController;
    }

    public PurchaseReturnDetails getSelectedGrnData() {
        return selectedGrnData;
    }

    public void setSelectedGrnData(PurchaseReturnDetails selectedGrnData) {
        this.selectedGrnData = selectedGrnData;
    }

    public List<PurchaseReturn> getList() {
        return purchaseReturnController.findAll();
    }

    public PurchaseReturn getCurrent() {
        return current;
    }

    public void setCurrent(PurchaseReturn current) {
        this.current = current;
    }

    public void onEdit(RowEditEvent event) {

        MessageHelper.addSuccessMessage(((PurchaseReturnDetails) event.getObject()).getGrnItem().getItemCode() + " Updated!");
        for (PurchaseReturnDetails details : getVirtualList()) {
            PurchaseReturnDetails purchRetDetails = new PurchaseReturnDetails();
            purchRetDetails = (PurchaseReturnDetails) event.getObject();
            if (details.getGrnItem().getId() == purchRetDetails.getGrnItem().getId()) {

                details.setGrnQty(purchRetDetails.getGrnQty());
                details.setGrnItemCost(purchRetDetails.getGrnItemCost());
                details.setGrnItemDiscountPerc(purchRetDetails.getGrnItemDiscountPerc());
            }
        }

        current.setTotalAmount(calculateTotal());
    }

    public void onCancel(RowEditEvent event) {
        MessageHelper.addSuccessMessage(((PurchaseReturnDetails) event.getObject()).getGrnItem().getItemCode() + "Not Updated!");

    }

    public Double calculateTotal() {
        double TotalValue = 0;
        for (PurchaseReturnDetails details : getVirtualList()) {
            TotalValue = TotalValue + (details.getGrnItemCost() * details.getGrnQty());
        }
        return TotalValue;
    }

    public void addItem() {
        boolean isFound = false;

        if (getCurrentDetails().getGrnItem() == null || getCurrentDetails().getGrnItemCost() == 0 || getCurrentDetails().getGrnQty() == 0) {
            MessageHelper.addErrorMessage("Error", "Please Fill All Details");
            return;
        }

        getCurrentDetails().setRelatedPurchaseRet(getCurrent());

        for (PurchaseReturnDetails Retdetails : getVirtualList()) {
            if (Retdetails.getGrnItem().getId() == getCurrentDetails().getGrnItem().getId()) {
                Retdetails.setGrnQty(Retdetails.getGrnQty() + getCurrentDetails().getGrnQty());
                isFound = true;
                break;
            }

        }
        if (!isFound) {
            getVirtualList().add(getCurrentDetails());
        }

        getCurrent().setTotalAmount(calculateTotal());
        setCurrentDetails(new PurchaseReturnDetails());
    }

    public String removeItem(PurchaseReturnDetails RetDetails) {
        for (int a = 0; a < getVirtualList().size(); a++) {
            if (getVirtualList().get(a) == RetDetails) {
                getVirtualList().remove(a);
                break;
            }
        }
        getCurrent().setTotalAmount(calculateTotal());
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
            current.setTotalAmount(calculateTotal());
        }
        return "#";
    }

    public void printReport() {
        try {
            List<PurchaseReturn> ReturnList = new ArrayList<>();
            PurchaseReturn PurRet = (PurchaseReturn) purchaseReturnController.find(current.getId());
            ReturnList.add(PurRet);
            JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(ReturnList);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reports/PurchaseRetReport.jasper");
            JasperPrint jasPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollection);
            HttpServletResponse responce = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            responce.setContentType("application/pdf");
            String filename = new StringBuffer(reportPath).append(".pdf").toString();
            ServletOutputStream output = responce.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasPrint, output);
            responce.addHeader("Content-Disposition", "inline; filename=" + filename);
            //responce.addHeader("Content-disposition", "attachment; filename=CashBook.pdf");
            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
//    public String addTEst() {
//
//        if (getVirtualList().isEmpty()) {
//            MessageHelper.addErrorMessage("Error", "No Items Found!");
//            return "";
//        }
//
//        //Saving Logger
//        Logger logger = EntityHelper.createLogger("Good Received Note", getCurrent().getGrnNo(), TransactionTypes.GRN);
//        getLoggerController().create(logger);
//
//        //Saving Properties
//        for (Properties properties : getCurrent().getExtraz()) {
//            System.out.println("Trying Saving ... " + properties.getPropertyName() + " : " + properties.getPropertyValue());
//            getPropertiesController().create(properties);
//            System.out.println("Done");
//        }
//
//        //Saving GRN
//        getCurrent().setBillStatus(getCurrent().getBillStatus());
//        getCurrent().setGrnDetails(getVirtualList());
//        getCurrent().setLogger(logger);
//        getCurrent().setPaymentDetails(getPaymentDetails());
//        getGrnController().create(getCurrent());
//
//        //Process GRN Details
//        for (GRNDetails grnDetail : getVirtualList()) {
//            grnDetail.setRelatedGRN(getCurrent());
//
//            //Update Stock
//            Stock stock = getStockController().getItemStock(getCurrent().getLocation(), grnDetail.getGrnItem(),getCurrent().getBillStatus());
//            stock.setStockLocation(getCurrent().getLocation());
//            stock.setSockItem(grnDetail.getGrnItem());
//            stock.setStockQty(stock.getStockQty() + grnDetail.getGrnQty());
//            stock.setRelatedCompany(SessionDataHelper.getLoggedCompany(true));
//            getStockController().edit(stock);
//
//            //Item Bincard Entry 
//            ItemBincard itemBin = new ItemBincard();
//            itemBin.setDescription(getCurrentDocument().getDocumentDisplayName() + " - " + getCurrent());
//            itemBin.setItem(grnDetail.getGrnItem());
//            itemBin.setRelatedDate(getCurrent().getGrnDate());
//            itemBin.setTrnNumber(getCurrent().toString());
//            itemBin.setQty(grnDetail.getGrnQty());
//            itemBin.setLog(logger);
//            itemBin.setBillStat(getCurrent().getBillStatus());
//            itemBin.setBalance(stock.getStockQty());
//            getItemBincardController().create(itemBin);
//        }
//
//        //Customer Transaction - GRN
//        CustomerTransaction customerTransactionEntry = getCustomerTransactionController().addCustomerTransactionEntry(getCurrentDocument().getDocumentDisplayName() + " - " + getCurrent(), getCurrent().getBillStatus(), logger, getCurrent().getSupplier(), getCurrent().getGrnDate(), 0,getCurrent().getTotalAmount());
//
//        //Getting Cust Balance
//        CustomerBalance customerBalance = getCustomerBalanceController().getCustomerBalanceObject(getCurrent().getSupplier(),getCurrent().getBillStatus());
//        if (customerBalance != null) {
//            customerBalance.setBalance(customerBalance.getBalance() - getCurrent().getTotalAmount());
//            getCustomerBalanceController().edit(customerBalance);
//        } else {
//            customerBalance = new CustomerBalance(getCurrent().getSupplier(), (getCurrent().getTotalAmount() * -1));
//            getCustomerBalanceController().create(customerBalance);
//        }
//
//        customerTransactionEntry.setBalance(customerBalance.getBalance());
//        getCustomerTransactionController().edit(customerTransactionEntry);
//
//        if (getPaymentDetails() != null) {
//            double totalPay = 0;
//            getPaymentDetails().setTotalAmount(getCurrent().getTotalAmount());
//            getPaymentDetails().setLogger(logger);
//            getPaymentDetails().setDocumentID(getCurrent().getId());
//            getPaymentDetails().setDocumentType(getCurrentDocument());
//
//            if (getReceivedCheque() != null && getReceivedCheque().getChequeAmount() != null) {
//                Cheques relatedCheque = getReceivedCheque();
//                if (relatedCheque.getChequeAmount() > 0) {
//                    relatedCheque.setStatus(ChequeStatus.RECEIVED);
//                    relatedCheque.setRelatedLocation(getCurrent().getLocation());
//                    relatedCheque.setChequeRecDate(getCurrent().getGrnDate());
//                    relatedCheque.setRelatedPayment(getPaymentDetails());
//                    getChequesController().create(relatedCheque);
//                    getPaymentDetails().setChequeAmount(getReceivedCheque().getChequeAmount());
//                    getPaymentDetails().setRelatedCheque(relatedCheque);
//
//                    //Customer Transaction - GRN - CHEQUE
//                    CustomerTransaction customerTransactionEntryCheque = getCustomerTransactionController().addCustomerTransactionEntry("PAID - " + getCurrentDocument().getDocumentDisplayName() + " - CHEQUE - " + getCurrent(), getCurrent().getBillStatus(), logger, getCurrent().getSupplier(), getCurrent().getGrnDate(), getPaymentDetails().getChequeAmount(),0);
//                    customerBalance.setBalance(customerBalance.getBalance() + getPaymentDetails().getChequeAmount());
//                    customerTransactionEntryCheque.setBalance(customerBalance.getBalance());
//                    getCustomerTransactionController().edit(customerTransactionEntryCheque);
//                    
//                    //Calculate Total
//                    totalPay = totalPay +  getPaymentDetails().getChequeAmount();
//                    
//                }
//            }
//
//            if (getReceivedCreditCardReceipts() != null) {
//                CreditCardReceipts relatedCreditCardReceipt = getReceivedCreditCardReceipts();
//                if (relatedCreditCardReceipt.getAmount() > 0) {
//                    relatedCreditCardReceipt.setReceiptStatus(CreditCardReceiptStatus.RECEIVED);
//                    relatedCreditCardReceipt.setRelatedLocation(getCurrent().getLocation());
//                    relatedCreditCardReceipt.setReceiptDate(getCurrent().getGrnDate());
//                    relatedCreditCardReceipt.setRelatedPaymentDetails(getPaymentDetails());
//                    relatedCreditCardReceipt.setRelatedLogger(logger);
//                    getCreditCardReceiptsController().create(relatedCreditCardReceipt);
//                    getPaymentDetails().setCreditCardAmount(relatedCreditCardReceipt.getAmount());
//                    getPaymentDetails().setRelatedCreditCardReceipts(relatedCreditCardReceipt);
//
//                    //Customer Transaction - GRN - CREDIT CARD
//                    CustomerTransaction customerTransactionEntryCC = getCustomerTransactionController().addCustomerTransactionEntry("PAID - " + getCurrentDocument().getDocumentDisplayName() + " - CREDIT CARD - " + getCurrent(), getCurrent().getBillStatus(), logger, getCurrent().getSupplier(), getCurrent().getGrnDate(), getPaymentDetails().getCreditCardAmount(),0);
//                    customerBalance.setBalance(customerBalance.getBalance() + getPaymentDetails().getCreditCardAmount());
//                    customerTransactionEntryCC.setBalance(customerBalance.getBalance());
//                    getCustomerTransactionController().edit(customerTransactionEntryCC);
//                    
//                    //Calculate Total
//                    totalPay = totalPay +  getPaymentDetails().getCreditCardAmount();
//                }
//            }
//
//            if (getPaymentDetails().getCashAmount() > 0) {
//
//                //Customer Transaction - GRN - CASH
//                CustomerTransaction customerTransactionEntryCash = getCustomerTransactionController().addCustomerTransactionEntry("PAID - " + getCurrentDocument().getDocumentDisplayName() + " - CASH - " + getCurrent(), getCurrent().getBillStatus(), logger, getCurrent().getSupplier(), getCurrent().getGrnDate(), getPaymentDetails().getCashAmount(),0);
//                customerBalance.setBalance(customerBalance.getBalance() + getPaymentDetails().getCashAmount());
//                customerTransactionEntryCash.setBalance(customerBalance.getBalance());
//                getCustomerTransactionController().edit(customerTransactionEntryCash);
//
//                //Cashbook
//                CashBook cashBook = new CashBook();
//                cashBook.setDescription(getCurrentDocument().getDocumentDisplayName() + " - " + getCurrent());
//                cashBook.setCR(getPaymentDetails().getCashAmount());
//                cashBook.setDR(0);
//                cashBook.setBillStat(getCurrent().getBillStatus());
//                cashBook.setRelatedDate(getCurrent().getGrnDate());
//                cashBook.setLocation(getCurrent().getLocation());
//                cashBook.setLogger(logger);
//
//                
//                CashBookBalance cashBalance = getCashBookBalanceController().getCashBookBalanceObject(getCurrent().getLocation(), getCurrent().getBillStatus());
//                if (cashBalance != null) {
//                    cashBalance.setCashBalance(cashBalance.getCashBalance() - getPaymentDetails().getCashAmount());
//                    getCashBookBalanceController().edit(cashBalance);
//                    cashBook.setBalance(cashBalance.getCashBalance());
//                } else {
//                    cashBook.setBalance(getPaymentDetails().getCashAmount());
//                }
//
//                getCashbookController().create(cashBook);
//                //Calculate Total
//                totalPay = totalPay +  getPaymentDetails().getCashAmount();
//                
//
//            }
//            getCustomerBalanceController().edit(customerBalance);
//            getPaymentDetails().setTrnType(TransactionTypes.GRN);
//            getPaymentDetails().setSettledAmount(totalPay);
//            getPaymentDetails().setCreditAmount(getPaymentDetails().getTotalAmount() - totalPay);
//            getPaymentDetailsController().edit(getPaymentDetails());
//        }
//
//        if (getCurrentSystemNumber() != null) {
//            getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getIncrementedSystemNumber());
//            getSystemNumbersController().edit(getCurrentSystemNumber());
//        }
//
//        printReportDownload();
//        setCurrent(new GRN());
//        setCurrentDetails(new GRNDetails());
//        setVirtualList(new ArrayList<GRNDetails>());
//        setCashAmount(0);
//
//        return "Home";
//    }

    public String grnReturn() {
        if (getVirtualList().isEmpty()) {
            MessageHelper.addErrorMessage("Error", "No Items Found!");
            return "";
        }

        Logger log = EntityHelper.createLogger("Purchase Return ", current.getGrnRetNo(), TransactionTypes.GRNRETURN);
        loggerController.create(log);

        current.setBillStatus(BillStatus.TAX);

        current.setPurchaceRtnDetails(VirtualList);
        current.setLogger(log);
        purchaseReturnController.create(current);

        for (PurchaseReturnDetails PurRet : getVirtualList()) {
            PurRet.setRelatedPurchaseRet(current);

            Stock stock = getStockController().findSpecific(PurRet.getGrnItem(), getCurrent().getLocation(), SessionDataHelper.getLoggedCompany(true));
            stock.setStockQty(stock.getStockQty() - PurRet.getGrnQty());
            getStockController().edit(stock);

            ItemBincard itemBin = new ItemBincard();
            itemBin.setDescription("Purchase Return - " + getCurrent().getGrnRetNo());
            itemBin.setItem(PurRet.getGrnItem());
            itemBin.setTrnNumber(getCurrent().getInvNo());
            itemBin.setRelatedDate(getCurrent().getGrnRetDate());
            itemBin.setQty(PurRet.getGrnQty() * -1);
            itemBin.setLog(log);
            itemBin.setBalance(stock.getStockQty());
            getItemBincardController().create(itemBin);

        }

        //Customer Transaction
        CustomerTransaction custTran = new CustomerTransaction();
        custTran.setDescription("Purchase Return - " + getCurrent().getGrnRetNo());
        custTran.setSupplier(getCurrent().getSupplier());
        custTran.setDR(0);
        custTran.setDate(getCurrent().getGrnRetDate());
        custTran.setCR(getCurrent().getTotalAmount());

        //Getting Cust Balance
        CustomerBalance Balance = getCustomerBalanceController().getCustomerBalanceObject(getCurrent().getSupplier(), getCurrent().getBillStatus());
        if (Balance != null) {
            Balance.setBalance(Balance.getBalance() - getCurrent().getTotalAmount());
            getCustomerBalanceController().edit(Balance);
            custTran.setBalance(Balance.getBalance());
        } else {
            custTran.setBalance(getCurrent().getTotalAmount());
        }

        custTran.setLogger(log);
        getCustomerTransactionController().create(custTran);

        /*    
         //Cashbook
         CashBook cashBook = new CashBook();
         cashBook.setDescription("Purchase Return - " + current.getGrnRetNo());
         cashBook.setCR(0);
         cashBook.setDR(current.getTotalAmount());
         cashBook.setLocation(current.getLocation());
         cashBook.setLogger(log);

         CashBookBalance cashBalance = cashBookBalanceController.getCashBookBalanceObject(current.getLocation(), current.getBillStatus());
         if(cashBalance != null)
         {
            
         cashBalance.setCashBalance(cashBalance.getCashBalance() - current.getTotalAmount());
         cashBookBalanceController.edit(cashBalance);
         cashBook.setBalance(cashBalance.getCashBalance());            
         }
         else
         {
         cashBook.setBalance(current.getTotalAmount());
         }
         
         cashbookController.create(cashBook);
         * 
         * 
         * 
         */
        printReport();
        current = new PurchaseReturn();
        currentDetails = new PurchaseReturnDetails();
        VirtualList = new ArrayList<PurchaseReturnDetails>();
        return "Home";
    }

    public String getGrnReturnNumber() {
        setCurrentSystemNumber(null);
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get(AlphaConstant.SESSION_USER);
        if (loggedUser != null && getCurrent().getLocation() != null) {
            List<SystemNumbers> systemNumbers = getSystemNumbersController().findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getLocation(), currentDocument);
            if (systemNumbers != null && !systemNumbers.isEmpty()) {
                setCurrentSystemNumber(systemNumbers.get(0));
                getCurrent().setGrnRetNo(getCurrentSystemNumber().getDocumentSystemNo());
            }
        }
        return getCurrentSystemNumber() != null ? getCurrentSystemNumber().getDocumentSystemNo() : "";
    }

}
