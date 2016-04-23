
package com.AlphaDevs.cloud.web.Entities;

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
public class CashPaymentVoucher implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String paymentNumber;
    private String paymentRefNumber;
    private String paymentDescription;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date paymentDate;
    private double paymentAmount;
    @OneToOne
    private Location paymentLocation;
    @OneToOne
    private Company relatedCompany;
    @OneToOne
    private Supplier relatedSupplier;
    @OneToOne
    private Logger relatedLogger;

    public CashPaymentVoucher() {
    }

    public CashPaymentVoucher(String paymentNumber, String paymentRefNumber, String paymentDescription, Date paymentDate, double paymentAmount, Location paymentLocation, Company relatedCompany, Supplier relatedSupplier, Logger relatedLogger) {
        this.paymentNumber = paymentNumber;
        this.paymentRefNumber = paymentRefNumber;
        this.paymentDescription = paymentDescription;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentLocation = paymentLocation;
        this.relatedCompany = relatedCompany;
        this.relatedSupplier = relatedSupplier;
        this.relatedLogger = relatedLogger;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Logger getRelatedLogger() {
        return relatedLogger;
    }

    public void setRelatedLogger(Logger relatedLogger) {
        this.relatedLogger = relatedLogger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getPaymentRefNumber() {
        return paymentRefNumber;
    }

    public void setPaymentRefNumber(String paymentRefNumber) {
        this.paymentRefNumber = paymentRefNumber;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Location getPaymentLocation() {
        return paymentLocation;
    }

    public void setPaymentLocation(Location paymentLocation) {
        this.paymentLocation = paymentLocation;
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
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CashPaymentVoucher)) {
            return false;
        }
        CashPaymentVoucher other = (CashPaymentVoucher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + getPaymentNumber() +"/" + getPaymentRefNumber() + ")";
    }

}
