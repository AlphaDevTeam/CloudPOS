
package com.AlphaDevs.cloud.web.Enums;

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
public enum Document {
    
    INVOICE("Invoice","Commercial Invoice"),
    GOOD_RECEIPT_NOTE("Grn","Goods Receipt Note"),
    GOOD_RECEIPT_NOTE_RETURN("GRNReturn","Goods Receipt Note - Return"),
    METER_READING("Meter" ,"Meter Reading"),
    CASH_PAYMENT_CUST("CashPaymentCust","Cash Payment Voucher (Cust/Sup)"),
    CASH_PAYMENT_EXP("CashPaymentExpenses","Cash Payment Voucher (Expenses)"),
    CASH_RECEIPT_CUST("CashRecieptCust","Cash Receipt Voucher (Cust/Sup)"),
    CASH_RECEIPT_EXP("CashRecieptExpenses","Cash Receipt Voucher (Expenses)"),
    BANK_DEPOSIT("BankDeposit","Bank Deposit"),
    REQUEST("Request","Request"),
    STOCK_ADJESTMENT("StockAdjestment","Stock Adjestments"),
    STOCK_TRANSFER("StockTransfer","Stock Transfers");;
    
    private String fieldName;
    private String displayName;
    private Document(String feild,String displayName){
        this.fieldName = feild;
        this.displayName = displayName;
    }
    
    public String getDocumentName(){
        return fieldName;
    }
    
      public String getDocumentDisplayName(){
        return displayName;
    }
    
}
