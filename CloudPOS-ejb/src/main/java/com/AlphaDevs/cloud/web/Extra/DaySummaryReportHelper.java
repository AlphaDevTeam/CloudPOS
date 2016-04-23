

package com.AlphaDevs.cloud.web.Extra;

import com.AlphaDevs.cloud.web.Entities.BankDeposit;
import com.AlphaDevs.cloud.web.Entities.CashPaymentVoucher;
import com.AlphaDevs.cloud.web.Entities.CashPaymentVoucherExpenses;
import com.AlphaDevs.cloud.web.Entities.CashReceivedVoucher;
import com.AlphaDevs.cloud.web.Entities.CashReceivedVoucherExpenses;
import com.AlphaDevs.cloud.web.Entities.Cheques;
import com.AlphaDevs.cloud.web.Entities.CreditCardReceipts;
import com.AlphaDevs.cloud.web.Entities.CustomerBalance;
import com.AlphaDevs.cloud.web.Entities.InvoiceDetails;
import com.AlphaDevs.cloud.web.Entities.MeterReading;
import com.AlphaDevs.cloud.web.Entities.StockAdjestments;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

public class DaySummaryReportHelper {
    
    private List<MeterReading> meterReadings;
    private List<InvoiceDetails> invoiceDetails;
    private List<StockAdjestments> stockAdjestments;
    private List<CustomerBalance> customerBalances;
    private List<CreditCardReceipts> creditCardReceipts;
    private List<CashPaymentVoucher> cashPaymentVouchers;
    private List<CashReceivedVoucher> cashReceivedVouchers;
    private List<CashPaymentVoucherExpenses> cashPaymentVouchersExpenses;
    private List<CashReceivedVoucherExpenses> cashReceivedVouchersExpenses;
    private List<BankDeposit> bankDeposits;
    private List<Cheques> receivedCheques;

    public DaySummaryReportHelper(List<MeterReading> meterReadings, List<InvoiceDetails> invoiceDetails, List<StockAdjestments> stockAdjestments, List<CustomerBalance> customerBalances, List<CreditCardReceipts> creditCardReceipts, List<CashPaymentVoucher> cashPaymentVouchers, List<CashReceivedVoucher> cashReceivedVouchers, List<CashPaymentVoucherExpenses> cashPaymentVouchersExpenses, List<CashReceivedVoucherExpenses> cashReceivedVouchersExpenses, List<BankDeposit> bankDeposits, List<Cheques> receivedCheques) {
        this.meterReadings = meterReadings;
        this.invoiceDetails = invoiceDetails;
        this.stockAdjestments = stockAdjestments;
        this.customerBalances = customerBalances;
        this.creditCardReceipts = creditCardReceipts;
        this.cashPaymentVouchers = cashPaymentVouchers;
        this.cashReceivedVouchers = cashReceivedVouchers;
        this.cashPaymentVouchersExpenses = cashPaymentVouchersExpenses;
        this.cashReceivedVouchersExpenses = cashReceivedVouchersExpenses;
        this.bankDeposits = bankDeposits;
        this.receivedCheques = receivedCheques;
    }

    

    public DaySummaryReportHelper() {
    }

    public List<Cheques> getReceivedCheques() {
        return receivedCheques;
    }

    public void setReceivedCheques(List<Cheques> receivedCheques) {
        this.receivedCheques = receivedCheques;
    }
    
    public List<BankDeposit> getBankDeposits() {
        return bankDeposits;
    }

    public void setBankDeposits(List<BankDeposit> bankDeposits) {
        this.bankDeposits = bankDeposits;
    }

    public List<CreditCardReceipts> getCreditCardReceipts() {
        return creditCardReceipts;
    }

    public void setCreditCardReceipts(List<CreditCardReceipts> creditCardReceipts) {
        this.creditCardReceipts = creditCardReceipts;
    }

    public List<CashPaymentVoucher> getCashPaymentVouchers() {
        return cashPaymentVouchers;
    }

    public void setCashPaymentVouchers(List<CashPaymentVoucher> cashPaymentVouchers) {
        this.cashPaymentVouchers = cashPaymentVouchers;
    }

    public List<CashReceivedVoucher> getCashReceivedVouchers() {
        return cashReceivedVouchers;
    }

    public void setCashReceivedVouchers(List<CashReceivedVoucher> cashReceivedVouchers) {
        this.cashReceivedVouchers = cashReceivedVouchers;
    }

    public List<CashPaymentVoucherExpenses> getCashPaymentVouchersExpenses() {
        return cashPaymentVouchersExpenses;
    }

    public void setCashPaymentVouchersExpenses(List<CashPaymentVoucherExpenses> cashPaymentVouchersExpenses) {
        this.cashPaymentVouchersExpenses = cashPaymentVouchersExpenses;
    }

    public List<CashReceivedVoucherExpenses> getCashReceivedVouchersExpenses() {
        return cashReceivedVouchersExpenses;
    }

    public void setCashReceivedVouchersExpenses(List<CashReceivedVoucherExpenses> cashReceivedVouchersExpenses) {
        this.cashReceivedVouchersExpenses = cashReceivedVouchersExpenses;
    }

    public List<CustomerBalance> getCustomerBalances() {
        return customerBalances;
    }

    public void setCustomerBalances(List<CustomerBalance> customerBalances) {
        this.customerBalances = customerBalances;
    }
    
    public List<StockAdjestments> getStockAdjestments() {
        return stockAdjestments;
    }

    public void setStockAdjestments(List<StockAdjestments> stockAdjestments) {
        this.stockAdjestments = stockAdjestments;
    }

    public List<MeterReading> getMeterReadings() {
        return meterReadings;
    }

    public void setMeterReadings(List<MeterReading> meterReadings) {
        this.meterReadings = meterReadings;
    }

    public List<InvoiceDetails> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetails> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
    
    public void clean(){
        setMeterReadings(new ArrayList<MeterReading>());
        setInvoiceDetails(new ArrayList<InvoiceDetails>());
        setStockAdjestments(new ArrayList<StockAdjestments>());
        setCustomerBalances(new ArrayList<CustomerBalance>());
        setCreditCardReceipts(new ArrayList<CreditCardReceipts>());
        setCashPaymentVouchers(new ArrayList<CashPaymentVoucher>());
        setCashReceivedVouchers(new ArrayList<CashReceivedVoucher>());
        setCashPaymentVouchersExpenses(new ArrayList<CashPaymentVoucherExpenses>());
        setCashReceivedVouchersExpenses(new ArrayList<CashReceivedVoucherExpenses>());
    }
    
    
    
}
