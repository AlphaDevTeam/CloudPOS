package com.AlphaDevs.cloud.web.Enums;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 *
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
public enum Document {

    INVOICE("Invoice", "Commercial Invoice"),
    GOODS_RECEIVED_NOTE("Grn", "Goods Received Note"),
    GOODS_RECEIVED_NOTE_RETURN("GRNReturn", "Goods Received Note - Return"),
    METER_READING("Meter", "Meter Reading"),
    CASH_PAYMENT_CUST("CashPaymentCust", "Cash Payment Voucher (Cust/Sup)"),
    CASH_PAYMENT_EXP("CashPaymentExpenses", "Cash Payment Voucher (Expenses)"),
    CASH_RECEIPT_CUST("CashRecieptCust", "Cash Receipt Voucher (Cust/Sup)"),
    CASH_RECEIPT_EXP("CashRecieptExpenses", "Cash Receipt Voucher (Expenses)"),
    BANK_DEPOSIT("BankDeposit", "Bank Deposit"),
    REQUEST("Request", "Request"),
    STOCK_ADJESTMENT("StockAdjestment", "Stock Adjestments"),
    JOB("Job", "Job"),
    STOCK_TRANSFER("StockTransfer", "Stock Transfers"),
    NON_INIT("NotInitialized","Not Initialized Object");

    private final String fieldName;
    private final String displayName;

    private Document(String feild, String displayName) {
        this.fieldName = feild;
        this.displayName = displayName;
    }

    public String getDocumentName() {
        return fieldName;
    }

    public String getDocumentDisplayName() {
        return displayName;
    }

}
