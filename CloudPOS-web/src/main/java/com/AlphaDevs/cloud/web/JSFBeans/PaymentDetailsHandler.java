package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.GRN;
import com.AlphaDevs.cloud.web.Entities.Invoice;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.PaymentDetails;
import com.AlphaDevs.cloud.web.Entities.Supplier;
import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.SessionBean.PaymentDetailsController;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 *
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@ManagedBean
@ViewScoped
public class PaymentDetailsHandler {

    @EJB
    private PaymentDetailsController paymentDetailsController;

    private PaymentDetails current;
    private List<PaymentDetails> selectedPayments;
    private Document document;
    private Location location;
    private Date fromDate;
    private Date toDate;
    private Supplier supplier;


    @PostConstruct
    public void init() {
        if (current == null) {
            current = new PaymentDetails();
        }
    }

    public PaymentDetailsHandler() {
    }

    public Document getDocument() {
        return document;
    }

    public Location getLocation() {
        return location;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public PaymentDetailsController getPaymentDetailsController() {
        return paymentDetailsController;
    }

    public List<PaymentDetails> getSelectedPayments() {
        return selectedPayments;
    }

    public void setSelectedPayments(List<PaymentDetails> selectedPayments) {
        this.selectedPayments = selectedPayments;
    }

    public void setPaymentDetailsController(PaymentDetailsController paymentDetailsController) {
        this.paymentDetailsController = paymentDetailsController;
    }

    public PaymentDetails getCurrent() {
        return current;
    }

    public void setCurrent(PaymentDetails current) {
        this.current = current;
    }

    public List<PaymentDetails> getList() {
        return getPaymentDetailsController().findAll();
    }

    public List<GRN> getPendingGRNs() {
        return getPaymentDetailsController().findPendingGRNs(getLocation(),getSupplier(),getFromDate(),getToDate());
    }
    public List<Invoice> getPendingInvoices() {
        return getPaymentDetailsController().findPendingInvoices(getLocation(),getSupplier(),getFromDate(),getToDate());
    }

}
