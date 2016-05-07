

package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Enums.BillStatus;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@Entity
public class CashReceivedVoucher implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String receiptNumber;
    private String receiptRefNumber;
    private String receiptDescription;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date receiptDate;
    private double receiptAmount;
    @OneToOne
    private Location receiptLocation;
    @OneToOne
    private Company relatedCompany;
    @OneToOne
    private Supplier relatedSupplier;
    
    @OneToOne
    private Logger relatedLogger;
    private BillStatus billStatus;
    
    public CashReceivedVoucher() {
    }

    public CashReceivedVoucher(String receiptNumber, String receiptRefNumber, String receiptDescription, Date receiptDate, double receiptAmount, Location receiptLocation, Company relatedCompany, Supplier relatedSupplier, Logger relatedLogger) {
        this.receiptNumber = receiptNumber;
        this.receiptRefNumber = receiptRefNumber;
        this.receiptDescription = receiptDescription;
        this.receiptDate = receiptDate;
        this.receiptAmount = receiptAmount;
        this.receiptLocation = receiptLocation;
        this.relatedCompany = relatedCompany;
        this.relatedSupplier = relatedSupplier;
        this.relatedLogger = relatedLogger;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }
    
    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Logger getRelatedLogger() {
        return relatedLogger;
    }

    public void setRelatedLogger(Logger relatedLogger) {
        this.relatedLogger = relatedLogger;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getReceiptRefNumber() {
        return receiptRefNumber;
    }

    public void setReceiptRefNumber(String receiptRefNumber) {
        this.receiptRefNumber = receiptRefNumber;
    }

    public String getReceiptDescription() {
        return receiptDescription;
    }

    public void setReceiptDescription(String receiptDescription) {
        this.receiptDescription = receiptDescription;
    }

    public double getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(double receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public Location getReceiptLocation() {
        return receiptLocation;
    }

    public void setReceiptLocation(Location receiptLocation) {
        this.receiptLocation = receiptLocation;
    }

    public Company getRelatedCompany() {
        return relatedCompany;
    }

    public void setRelatedCompany(Company relatedCompany) {
        this.relatedCompany = relatedCompany;
    }

    public Supplier getRelatedSupplier() {
        return relatedSupplier;
    }

    public void setRelatedSupplier(Supplier relatedSupplier) {
        this.relatedSupplier = relatedSupplier;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CashReceivedVoucher)) {
            return false;
        }
        CashReceivedVoucher other = (CashReceivedVoucher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + getReceiptNumber() + "/" + getReceiptRefNumber() + ")";
    }

}
