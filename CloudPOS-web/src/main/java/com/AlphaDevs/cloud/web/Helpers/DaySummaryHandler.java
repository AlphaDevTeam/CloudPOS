package com.AlphaDevs.cloud.web.Helpers;

import com.AlphaDevs.cloud.web.Entities.BankDeposit;
import com.AlphaDevs.cloud.web.Entities.CashPaymentVoucher;
import com.AlphaDevs.cloud.web.Entities.CashPaymentVoucherExpenses;
import com.AlphaDevs.cloud.web.Entities.CashReceivedVoucher;
import com.AlphaDevs.cloud.web.Entities.CashReceivedVoucherExpenses;
import com.AlphaDevs.cloud.web.Entities.Cheques;
import com.AlphaDevs.cloud.web.Extra.DaySummaryReportHelper;
import com.AlphaDevs.cloud.web.Entities.CreditCardReceipts;
import com.AlphaDevs.cloud.web.Entities.CustomEmailSettings;
import com.AlphaDevs.cloud.web.Entities.CustomerBalance;
import com.AlphaDevs.cloud.web.Entities.GRN;
import com.AlphaDevs.cloud.web.Entities.Invoice;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.MeterReading;
import com.AlphaDevs.cloud.web.Entities.StockAdjestments;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Extra.MailUtil;
import com.AlphaDevs.cloud.web.Extra.NumberFormatUtil;
import com.AlphaDevs.cloud.web.SessionBean.BankDepositController;
import com.AlphaDevs.cloud.web.SessionBean.CashPaymentVoucherController;
import com.AlphaDevs.cloud.web.SessionBean.CashPaymentVoucherExpensesController;
import com.AlphaDevs.cloud.web.SessionBean.CashReceivedVoucherController;
import com.AlphaDevs.cloud.web.SessionBean.CashReceivedVoucherExpensesController;
import com.AlphaDevs.cloud.web.SessionBean.ChequesController;
import com.AlphaDevs.cloud.web.SessionBean.CreditCardReceiptsController;
import com.AlphaDevs.cloud.web.SessionBean.CreditCardTeminalsController;
import com.AlphaDevs.cloud.web.SessionBean.CustomEmailSettingsController;
import com.AlphaDevs.cloud.web.SessionBean.CustomerBalanceController;
import com.AlphaDevs.cloud.web.SessionBean.GRNController;
import com.AlphaDevs.cloud.web.SessionBean.InvoiceController;
import com.AlphaDevs.cloud.web.SessionBean.InvoiceDetailsController;
import com.AlphaDevs.cloud.web.SessionBean.MeterReadingController;
import com.AlphaDevs.cloud.web.SessionBean.PumpController;
import com.AlphaDevs.cloud.web.SessionBean.StockAdjestmentsController;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.activation.DataSource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 *
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@ManagedBean
@ViewScoped
public class DaySummaryHandler {

    @EJB
    private ChequesController chequesController;

    @EJB
    private CustomEmailSettingsController customEmailSettingsController;
    @EJB
    private BankDepositController bankDepositController;
    @EJB
    private CashReceivedVoucherController cashReceivedVoucherController;
    @EJB
    private CashReceivedVoucherExpensesController cashReceivedVoucherExpensesController;
    @EJB
    private CashPaymentVoucherController cashPaymentVoucherController;
    @EJB
    private CashPaymentVoucherExpensesController cashPaymentVoucherExpensesController;
    @EJB
    private InvoiceDetailsController invoiceDetailsController;
    @EJB
    private CustomerBalanceController customerBalanceController;
    @EJB
    private StockAdjestmentsController stockAdjestmentsController;
    @EJB
    private CreditCardReceiptsController creditCardReceiptsController;
    @EJB
    private CreditCardTeminalsController creditCardTeminalsController;
    @EJB
    private MeterReadingController meterReadingController;
    @EJB
    private PumpController pumpController;
    @EJB
    private InvoiceController invoiceController;
    @EJB
    private GRNController gRNController;

    private List<DataTableRow> current;

    private Date relatedDate;
    private Location relatedLocation;
    private DaySummaryReportHelper daySumaryReportHelper;
    private CustomEmailSettings emailSettings;

    Date inputDate;

    Map<String, Object> reportParams;

    public DaySummaryHandler() {
        current = new ArrayList<DataTableRow>();
        daySumaryReportHelper = new DaySummaryReportHelper();
        reportParams = new HashMap<String, Object>();
        emailSettings = new CustomEmailSettings();
    }

    public ChequesController getChequesController() {
        return chequesController;
    }

    public void setChequesController(ChequesController chequesController) {
        this.chequesController = chequesController;
    }

    public BankDepositController getBankDepositController() {
        return bankDepositController;
    }

    public void setBankDepositController(BankDepositController bankDepositController) {
        this.bankDepositController = bankDepositController;
    }

    public CashReceivedVoucherController getCashReceivedVoucherController() {
        return cashReceivedVoucherController;
    }

    public void setCashReceivedVoucherController(CashReceivedVoucherController cashReceivedVoucherController) {
        this.cashReceivedVoucherController = cashReceivedVoucherController;
    }

    public CashReceivedVoucherExpensesController getCashReceivedVoucherExpensesController() {
        return cashReceivedVoucherExpensesController;
    }

    public void setCashReceivedVoucherExpensesController(CashReceivedVoucherExpensesController cashReceivedVoucherExpensesController) {
        this.cashReceivedVoucherExpensesController = cashReceivedVoucherExpensesController;
    }

    public CashPaymentVoucherController getCashPaymentVoucherController() {
        return cashPaymentVoucherController;
    }

    public void setCashPaymentVoucherController(CashPaymentVoucherController cashPaymentVoucherController) {
        this.cashPaymentVoucherController = cashPaymentVoucherController;
    }

    public CashPaymentVoucherExpensesController getCashPaymentVoucherExpensesController() {
        return cashPaymentVoucherExpensesController;
    }

    public void setCashPaymentVoucherExpensesController(CashPaymentVoucherExpensesController cashPaymentVoucherExpensesController) {
        this.cashPaymentVoucherExpensesController = cashPaymentVoucherExpensesController;
    }

    public CustomEmailSettingsController getCustomEmailSettingsController() {
        return customEmailSettingsController;
    }

    public void setCustomEmailSettingsController(CustomEmailSettingsController customEmailSettingsController) {
        this.customEmailSettingsController = customEmailSettingsController;
    }

    public CustomEmailSettings getEmailSettings() {
        return emailSettings;
    }

    public void setEmailSettings(CustomEmailSettings emailSettings) {
        this.emailSettings = emailSettings;
    }

    public CustomerBalanceController getCustomerBalanceController() {
        return customerBalanceController;
    }

    public void setCustomerBalanceController(CustomerBalanceController customerBalanceController) {
        this.customerBalanceController = customerBalanceController;
    }

    public StockAdjestmentsController getStockAdjestmentsController() {
        return stockAdjestmentsController;
    }

    public void setStockAdjestmentsController(StockAdjestmentsController stockAdjestmentsController) {
        this.stockAdjestmentsController = stockAdjestmentsController;
    }

    public DaySummaryReportHelper getDaySumaryReportHelper() {
        return daySumaryReportHelper;
    }

    public void setDaySumaryReportHelper(DaySummaryReportHelper daySumaryReportHelper) {
        this.daySumaryReportHelper = daySumaryReportHelper;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public Date getRelatedDate() {
        return relatedDate;
    }

    public InvoiceDetailsController getInvoiceDetailsController() {
        return invoiceDetailsController;
    }

    public void setInvoiceDetailsController(InvoiceDetailsController invoiceDetailsController) {
        this.invoiceDetailsController = invoiceDetailsController;
    }

    public Map<String, Object> getReportParams() {
        return reportParams;
    }

    public void setReportParams(Map<String, Object> reportParams) {
        this.reportParams = reportParams;
    }

    public MeterReadingController getMeterReadingController() {
        return meterReadingController;
    }

    public void setMeterReadingController(MeterReadingController meterReadingController) {
        this.meterReadingController = meterReadingController;
    }

    public void setRelatedDate(Date relatedDate) {
        System.out.println("Related" + relatedDate.toString());
        this.relatedDate = relatedDate;
    }

    public Location getRelatedLocation() {
        return relatedLocation;
    }

    public void setRelatedLocation(Location relatedLocation) {
        this.relatedLocation = relatedLocation;
    }

    public InvoiceController getInvoiceController() {
        return invoiceController;
    }

    public CreditCardReceiptsController getCreditCardReceiptsController() {
        return creditCardReceiptsController;
    }

    public void setCreditCardReceiptsController(CreditCardReceiptsController creditCardReceiptsController) {
        this.creditCardReceiptsController = creditCardReceiptsController;
    }

    public CreditCardTeminalsController getCreditCardTeminalsController() {
        return creditCardTeminalsController;
    }

    public void setCreditCardTeminalsController(CreditCardTeminalsController creditCardTeminalsController) {
        this.creditCardTeminalsController = creditCardTeminalsController;
    }

    public PumpController getPumpController() {
        return pumpController;
    }

    public void setPumpController(PumpController pumpController) {
        this.pumpController = pumpController;
    }

    public void setInvoiceController(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }

    public GRNController getgRNController() {
        return gRNController;
    }

    public void setgRNController(GRNController gRNController) {
        this.gRNController = gRNController;
    }

    public List<DataTableRow> getCurrent() {
        return current;
    }

    public void setCurrent(List<DataTableRow> current) {
        this.current = current;
    }

    public List<DataTableRow> InitReport() {

        double totalAmount = 0;
        double totalLiquidAmount = 0;
        getCurrent().clear();
        getDaySumaryReportHelper().clean();
        inputDate = null;
        try {

            if (getRelatedDate() == null) {
                setRelatedDate(new Date());
            }

            String inputStr = new SimpleDateFormat(SessionDataHelper.getSystems() != null ? SessionDataHelper.getSystems().getDateTimeformat() : AlphaConstant.yyyy_MM_dd).format(getRelatedDate());

            DateFormat dateFormat = new SimpleDateFormat(SessionDataHelper.getSystems() != null ? SessionDataHelper.getSystems().getDateTimeformat() : AlphaConstant.yyyy_MM_dd);
            inputDate = dateFormat.parse(inputStr);
            setInputDate(inputDate);
        } catch (ParseException ex) {
            MessageHelper.addErrorMessage("Error While Converting Date", ex.getLocalizedMessage());
        }

        //Meter Readings 
        //List<MeterReading> meterReading1 = new ArrayList<MeterReading>();
        List<MeterReading> meterReading = getMeterReadingController().findReadingByDate(getInputDate(), getRelatedLocation());
        if (meterReading != null && !meterReading.isEmpty()) {
            getDaySumaryReportHelper().setMeterReadings(meterReading);

            for (MeterReading reading : meterReading) {
                if (reading != null) {
                    if (reading.getRelatedPump() != null && reading.getRelatedPump().getRelatedItem() != null) {
                        double unitPrice = reading.getRelatedPump().getRelatedItem().getUnitPrice();
                        totalAmount = totalAmount + (unitPrice * reading.getReading());
                        totalLiquidAmount = totalLiquidAmount + (unitPrice * reading.getReading());
                        //System.out.println("Meter Reading " + totalAmount + " - Unit : " + unitPrice + " - Reading " + reading.getReading());
                    }
                     //Pump relatedPump = reading.getRelatedPump();

                     //meterRed.se(reading.get((TupleElement<Pump>) MeterReading_.relatedPump));
                    //meterRed.setRelatedPump(reading.get((TupleElement<Pump>) MeterReading_.relatedPump));
                     //getCurrent().add(new DataTableRow("Pump Readings Opening - " + relatedPump.getPumpCode() + "-" + relatedPump.getRelatedItem() , String.valueOf(reading.getLastReading()) , "0"));
                    //getCurrent().add(new DataTableRow("Pump Readings Closing - " + relatedPump.getPumpCode() + "-" + relatedPump.getRelatedItem() ,"0",String.valueOf( (reading.getLastReading()+ reading.getReading()))));
                    //getCurrent().add(new DataTableRow("Pump Issues - " + relatedPump.getPumpCode() + "-" + relatedPump.getRelatedItem() ,"0",String.valueOf( reading.getReading())));
                }
            }
        }

        System.out.println("Total Amount for Fuel " + totalAmount);
        
        //Purchaces 
        List<GRN> findGRNByDate = getgRNController().findByDateRange(getInputDate(), getRelatedLocation());
        double totalGRNDetails = 0;
        for (GRN grn : findGRNByDate) {
            if (grn != null) {
                totalGRNDetails = totalGRNDetails + grn.getTotalAmount();
                getCurrent().add(new DataTableRow("Purchase - " + grn , String.valueOf(grn.getTotalAmount()), "0"));
            }
            totalAmount = totalAmount + totalGRNDetails;
        }


        //Incoice 
        List<Invoice> findInvoicesByDate = getInvoiceController().findInvoicesByDate(getInputDate(),getInputDate(), getRelatedLocation());
        //getDaySumaryReportHelper().setInvoiceDetails(findInvoicesByDate);
        double totalInvoiceDetails = 0;
        for (Invoice invoice : findInvoicesByDate) {
            if (invoice != null) {
                totalInvoiceDetails = totalInvoiceDetails + invoice.getTotalAmount();
                getCurrent().add(new DataTableRow("Invoice - " + invoice , String.valueOf(invoice.getTotalAmount()), "0"));
            }
            totalAmount = totalAmount + totalInvoiceDetails;
        }

        //Sock Adjestments
        List<StockAdjestments> adjestmentsList = getStockAdjestmentsController().findAdjestmentsByLocationGroup(getInputDate(), getRelatedLocation());
        if (adjestmentsList != null && !adjestmentsList.isEmpty()) {
            getDaySumaryReportHelper().setStockAdjestments(adjestmentsList);
            for (StockAdjestments stockAdjestment : adjestmentsList) {
                if (stockAdjestment != null) {
                    if (stockAdjestment.getAdjestmentItems() != null) {
                        double unitPrice = stockAdjestment.getAdjestmentItems().getUnitPrice();
                        totalAmount = totalAmount - (stockAdjestment.getAdjestmentQty() * unitPrice);
                    }
                    getCurrent().add(new DataTableRow("Stock Adjestments (" + stockAdjestment.getAdjestmentType() + ") - " + stockAdjestment.getAdjestmentItems(), String.valueOf(stockAdjestment.getAdjestmentQty()), "0"));
                }
            }
        }

        //Credit Cards
        List<CreditCardReceipts> creditCardReceiptsByLocationGroup = getCreditCardReceiptsController().findCreditCardReceiptsByLocationGroup(getInputDate(), getRelatedLocation());
        getDaySumaryReportHelper().setCreditCardReceipts(creditCardReceiptsByLocationGroup);
        double totalcreditCardReceipt = 0;
        if (creditCardReceiptsByLocationGroup != null && !creditCardReceiptsByLocationGroup.isEmpty()) {
            for (CreditCardReceipts creditCardReceipt : creditCardReceiptsByLocationGroup) {
                if (creditCardReceipt != null) {
                    totalcreditCardReceipt = totalcreditCardReceipt + creditCardReceipt.getAmount();
                    getCurrent().add(new DataTableRow("Credit Card Receipts  - " + creditCardReceipt, String.valueOf(creditCardReceipt.getAmount()), "0"));
                }
            }

            totalAmount = totalAmount - totalcreditCardReceipt;
        }
        
        //Cheque Details
        List<Cheques> chequesByDate = getChequesController().findChequesByReceivedDate(getInputDate(), getRelatedLocation());
        getDaySumaryReportHelper().setReceivedCheques(chequesByDate);
        double totalReceivedCheques = 0;
        if (chequesByDate != null && !chequesByDate.isEmpty()) {
            for (Cheques receivedCheque : chequesByDate) {
                if (receivedCheque != null) {
                    totalReceivedCheques = totalReceivedCheques + receivedCheque.getChequeAmount();
                    getCurrent().add(new DataTableRow("Received Cheque  - " + receivedCheque, String.valueOf(receivedCheque.getChequeAmount()), "0"));
                }
            }

            totalAmount = totalAmount - totalReceivedCheques;
        }

        //Credit Customers
        List<CustomerBalance> creditCustomers = getCustomerBalanceController().findCustomerBalances(false);
        if (creditCustomers != null && !creditCustomers.isEmpty()) {
            getDaySumaryReportHelper().setCustomerBalances(creditCustomers);
            for (CustomerBalance customerBalance : creditCustomers) {
                if (customerBalance != null) {
                    getCurrent().add(new DataTableRow("Credit Cust  - " + customerBalance.getSupplier(), String.valueOf(customerBalance.getBalance()), "0"));
                }
            }
        }

        //Cash Payment Vouchers Exp
        List<CashPaymentVoucherExpenses> cashPaymentExp = getCashPaymentVoucherExpensesController().findCashPaymentVoucherExpenses(getInputDate(), getRelatedLocation());
        if (cashPaymentExp != null && !cashPaymentExp.isEmpty()) {
            getDaySumaryReportHelper().setCashPaymentVouchersExpenses(cashPaymentExp);
            double totalCashPaymentExp = 0;
            for (CashPaymentVoucherExpenses cashPaymentVoucherExpenses : cashPaymentExp) {
                if (cashPaymentVoucherExpenses != null) {
                    totalCashPaymentExp = totalCashPaymentExp + cashPaymentVoucherExpenses.getPaymentAmount();
                    getCurrent().add(new DataTableRow("Cash Pay Voucher (Expenses) - " + cashPaymentVoucherExpenses + " - " + cashPaymentVoucherExpenses.getPaymentDescription(), String.valueOf(cashPaymentVoucherExpenses.getPaymentAmount()), "0"));
                }
            }
            totalAmount = totalAmount - totalCashPaymentExp;
        }

        //Cash Payment Vouchers
        List<CashPaymentVoucher> cashPaymentVouchers = getCashPaymentVoucherController().findCashPaymentVoucher(getInputDate(), getRelatedLocation());
        if (cashPaymentVouchers != null && !cashPaymentVouchers.isEmpty()) {
            getDaySumaryReportHelper().setCashPaymentVouchers(cashPaymentVouchers);
            double totalCashPaymentVouchers = 0;
            for (CashPaymentVoucher cashPaymentVoucher : cashPaymentVouchers) {
                if (cashPaymentVoucher != null) {
                    totalCashPaymentVouchers = totalCashPaymentVouchers + cashPaymentVoucher.getPaymentAmount();
                    getCurrent().add(new DataTableRow("Cash Pay Voucher - " + cashPaymentVoucher + " - " + cashPaymentVoucher.getPaymentDescription(), String.valueOf(cashPaymentVoucher.getPaymentAmount()), "0"));
                }
            }

            totalAmount = totalAmount - totalCashPaymentVouchers;
        }

        //Cash Received Vouchers Exp
        List<CashReceivedVoucherExpenses> cashReceivedVoucherExpenses = getCashReceivedVoucherExpensesController().findCashReceivedVoucherExpenses(getInputDate(), getRelatedLocation());
        if (cashReceivedVoucherExpenses != null && !cashReceivedVoucherExpenses.isEmpty()) {
            getDaySumaryReportHelper().setCashReceivedVouchersExpenses(cashReceivedVoucherExpenses);
            double totalCashReceivedVoucherExpenses = 0;
            for (CashReceivedVoucherExpenses cashReceivedVoucherExpense : cashReceivedVoucherExpenses) {
                if (cashReceivedVoucherExpense != null) {
                    totalCashReceivedVoucherExpenses = totalCashReceivedVoucherExpenses + cashReceivedVoucherExpense.getReceiptAmount();
                    getCurrent().add(new DataTableRow("Cash Received Voucher (Expenses) - " + cashReceivedVoucherExpense + " - " + cashReceivedVoucherExpense.getReceiptDescription(), String.valueOf(cashReceivedVoucherExpense.getReceiptAmount()), "0"));
                }
            }

            totalAmount = totalAmount + totalCashReceivedVoucherExpenses;

        }

        //Cash Received Vouchers Cust / Sup
        List<CashReceivedVoucher> cashCashReceivedVouchers = getCashReceivedVoucherController().findCashReceivedVoucher(getInputDate(), getRelatedLocation());
        if (cashCashReceivedVouchers != null && !cashCashReceivedVouchers.isEmpty()) {
            getDaySumaryReportHelper().setCashReceivedVouchers(cashCashReceivedVouchers);
            double totalCashCashReceivedVouchers = 0;
            for (CashReceivedVoucher cashCashReceivedVoucher : cashCashReceivedVouchers) {
                if (cashCashReceivedVoucher != null) {
                    totalCashCashReceivedVouchers = totalCashCashReceivedVouchers + cashCashReceivedVoucher.getReceiptAmount();
                    getCurrent().add(new DataTableRow("Cash Received Voucher - " + cashCashReceivedVoucher + " - " + cashCashReceivedVoucher.getReceiptDescription(), String.valueOf(cashCashReceivedVoucher.getReceiptAmount()), "0"));
                }
            }
            totalAmount = totalAmount + totalCashCashReceivedVouchers;
        }

        //Bank Deposit
        List<BankDeposit> bankDeposits = getBankDepositController().findBankDeposits(getInputDate(), getRelatedLocation());
        if (bankDeposits != null && !bankDeposits.isEmpty()) {
            getDaySumaryReportHelper().setBankDeposits(bankDeposits);
            double totalBankDeposits = 0;
            for (BankDeposit bankDeposit : bankDeposits) {
                if (bankDeposit != null && bankDeposit.getAmount() != null) {
                    totalBankDeposits = totalBankDeposits + bankDeposit.getAmount().doubleValue();
                    getCurrent().add(new DataTableRow("Bank Deposit  - " + bankDeposit + " - " + bankDeposit.getDescription(), String.valueOf(bankDeposit.getAmount()), "0"));
                }
            }
            totalAmount = totalAmount - totalBankDeposits;
        }
        double totalAmountRound = NumberFormatUtil.roundToTwo(new BigDecimal(totalAmount)).doubleValue();
        getCurrent().add(new DataTableRow("Total  - "  , String.valueOf(totalAmountRound), String.valueOf(totalAmountRound)));
        getReportParams().put("totalLiquidAmount", totalLiquidAmount);
        getReportParams().put("totalAmount", totalAmountRound);
        return getCurrent();

    }

    public void genarateReport() {
        InitReport();
        List<DaySummaryReportHelper> listSummary = new ArrayList<DaySummaryReportHelper>();
        listSummary.add(getDaySumaryReportHelper());
        printReportSpecificGrn(listSummary);

        //Remove ME
        List<CustomEmailSettings> findRelatedEmailSettings = getCustomEmailSettingsController().findRelatedEmailSettings(EntityHelper.getLoggedCompany());
        if (findRelatedEmailSettings != null && !findRelatedEmailSettings.isEmpty()) {
            MailUtil mailUtil = new MailUtil();
            mailUtil.sendMail(findRelatedEmailSettings.get(0), null);
        }

    }

    public void emailReport() {
        InitReport();
        List<DaySummaryReportHelper> listSummary = new ArrayList<DaySummaryReportHelper>();
        listSummary.add(getDaySumaryReportHelper());
        emailReportAsAttachment(listSummary);
       

    }

    public void printReportSpecificGrn(List<DaySummaryReportHelper> daySummary) {
        try {
            JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(daySummary);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reports/DaySumarry.jasper");
            System.out.println("Path : " + reportPath);
            getReportParams().put("date", inputDate.toString());
            getReportParams().put("location", getRelatedLocation());
            JasperPrint jasPrint = JasperFillManager.fillReport(reportPath, getReportParams(), beanCollection);
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

    public void emailReportAsAttachment(List<DaySummaryReportHelper> daySummary) {
        try {
            JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(daySummary);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reports/DaySumarry.jasper");
            System.out.println("Path : " + reportPath);
            getReportParams().put("date", inputDate.toString());
            getReportParams().put("location", getRelatedLocation());
            JasperPrint jasPrint = JasperFillManager.fillReport(reportPath, getReportParams(), beanCollection);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasPrint, baos);
            DataSource attachment = new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
            FacesContext.getCurrentInstance().responseComplete();
            System.out.println("Report Done");

            List<CustomEmailSettings> findRelatedEmailSettings = getCustomEmailSettingsController().findRelatedEmailSettings(EntityHelper.getLoggedCompany());
            if (findRelatedEmailSettings != null && !findRelatedEmailSettings.isEmpty()) {
                MailUtil mailUtil = new MailUtil();
                mailUtil.sendMail(findRelatedEmailSettings.get(0), attachment);
                MessageHelper.addSuccessMessage("Email Sent");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
