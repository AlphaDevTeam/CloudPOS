

package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.*;
import com.AlphaDevs.cloud.web.Enums.BillStatus;
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
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@ViewScoped
public class PurchaseReturnHandler 
{
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
    
    public PurchaseReturnHandler() 
    {
        currentDocument = Document.GOOD_RECEIPT_NOTE_RETURN;
        if(current == null)
        {
            current = new PurchaseReturn();
        }
        if(currentDetails == null)
        {
            currentDetails = new PurchaseReturnDetails();
        }
        if(selectedGrnData == null)
        {
            selectedGrnData = new PurchaseReturnDetails();
        }
        
        VirtualList = new ArrayList<PurchaseReturnDetails>();
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
    
    public List<PurchaseReturn> getList()
    {
        return purchaseReturnController.findAll();
    }

    public PurchaseReturn getCurrent() {
        return current;
    }

    public void setCurrent(PurchaseReturn current) {
        this.current = current;
    }
    
    public void handleSelect(SelectEvent event) 
    {   
        currentDetails.setGrnItemCost(currentDetails.getGrnItem().getItemCost());
    }  
  
    public void onEdit(RowEditEvent event) 
    {   
        
        MessageHelper.addSuccessMessage(((PurchaseReturnDetails) event.getObject()).getGrnItem().getItemCode() + " Updated!");
        for(PurchaseReturnDetails details : getVirtualList())
        {
            PurchaseReturnDetails purchRetDetails  = new PurchaseReturnDetails();
            purchRetDetails = (PurchaseReturnDetails)event.getObject();
            if(details.getGrnItem().getId() == purchRetDetails.getGrnItem().getId() )
            {
                
                details.setGrnQty(purchRetDetails.getGrnQty());
                details.setGrnItemCost(purchRetDetails.getGrnItemCost());
                details.setGrnItemDiscountPerc(purchRetDetails.getGrnItemDiscountPerc());
            }
        }
        
        current.setTotalAmount(calculateTotal());
    }  
    
    public void onCancel(RowEditEvent event) 
    {  
        MessageHelper.addSuccessMessage(((PurchaseReturnDetails) event.getObject()).getGrnItem().getItemCode() + "Not Updated!");
        
    }
    
    public Double calculateTotal()
    {
        double TotalValue=0;
        for(PurchaseReturnDetails details : getVirtualList())
        {
            TotalValue = TotalValue +  (details.getGrnItemCost() * details.getGrnQty());
        }
        return TotalValue;
    }
    
    public void addItem()
    {
        boolean isFound =false;
        
        if(currentDetails.getGrnItem() == null || currentDetails.getGrnItemCost() ==0 || currentDetails.getGrnQty() == 0)
        {
            MessageHelper.addErrorMessage("Error", "Please Fill All Details");
            return;
        }
        
        currentDetails.setRelatedPurchaseRet(current);
        
        for(PurchaseReturnDetails Retdetails : getVirtualList())
        {
            if(Retdetails.getGrnItem().getId() == currentDetails.getGrnItem().getId())
            {
                Retdetails.setGrnQty(Retdetails.getGrnQty() + currentDetails.getGrnQty());
                isFound = true;
                break;
            }
            
        }
        if (!isFound)
        {
            getVirtualList().add(currentDetails);
        }
        
        current.setTotalAmount(calculateTotal());
        currentDetails = new PurchaseReturnDetails();
    }
    
    public String removeItem(PurchaseReturnDetails RetDetails)
    {
        
        for(int a=0;a<getVirtualList().size();a++)
        {
        
            if(getVirtualList().get(a) == RetDetails )
            {
                getVirtualList().remove(a);

                break;
            }
        }
        current.setTotalAmount(calculateTotal());
        
        return "#";
    }
    
    public String removeSelectedItem()
    {
        if ( getSelectedGrnData() ==null)
        {
            MessageHelper.addErrorMessage("Selection ","No item Selected ");
            return "";
        }
        else
        {
            for(int a=0;a<getVirtualList().size();a++)
            {

                if(getVirtualList().get(a) == getSelectedGrnData() )
                {
                    getVirtualList().remove(a);

                    break;
                }
            }
            current.setTotalAmount(calculateTotal());
        }
        return "#";
    }
    
    public void printReport() 
    {
        try
        {
            List<PurchaseReturn> ReturnList = new ArrayList<PurchaseReturn>();
            PurchaseReturn PurRet = (PurchaseReturn) purchaseReturnController.find(current.getId());
            ReturnList.add(PurRet);
            JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(ReturnList);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reports/PurchaseRetReport.jasper");
            JasperPrint jasPrint =  JasperFillManager.fillReport(reportPath, new HashMap(), beanCollection);
            HttpServletResponse responce = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            responce.setContentType("application/pdf");
            String filename = new StringBuffer(reportPath).append(".pdf").toString();  
            ServletOutputStream output = responce.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasPrint, output);
            responce.addHeader("Content-Disposition", "inline; filename="+ filename);  
            //responce.addHeader("Content-disposition", "attachment; filename=CashBook.pdf");
            FacesContext.getCurrentInstance().responseComplete();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    public String grnReturn()
    {
        if(getVirtualList().isEmpty())
        {
            MessageHelper.addErrorMessage("Error", "No Items Found!");
            return "";
        }
        
        Logger log = EntityHelper.createLogger("Purchase Return " , current.getGrnRetNo(), TransactionTypes.GRNRETURN);
        loggerController.create(log);
        
        
        current.setBillStatus(BillStatus.TAX);
        
        
        current.setPurchaceRtnDetails(VirtualList);
        current.setLogger(log);
        purchaseReturnController.create(current);
        
        
        for(PurchaseReturnDetails PurRet : getVirtualList())
        {
           PurRet.setRelatedPurchaseRet(current);
           
           Stock  stock = getStockController().findSpecific(PurRet.getGrnItem(),getCurrent().getLocation(), SessionDataHelper.getLoggedCompany(true));
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
        CustomerBalance Balance = getCustomerBalanceController().getCustomerBalanceObject(getCurrent().getSupplier(),getCurrent().getBillStatus());
        if(Balance != null)
        {
            Balance.setBalance(Balance.getBalance() - getCurrent().getTotalAmount() );
            getCustomerBalanceController().edit(Balance);
            custTran.setBalance(Balance.getBalance());
        }
        else
        {
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
