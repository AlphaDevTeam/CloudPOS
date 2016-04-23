
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
public class CashPaymentVoucherExpenses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String paymentNumber;
    private String paymentRefNumber;
    private String paymentDescription;
    private double paymentAmount;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date paymentDate;
    @OneToOne
    private Location paymentLocation;
    @OneToOne
    private Company relatedCompany;
    @OneToOne
    private Expenses relatedExpenses;
    @OneToOne
    private Logger relatedLogger;

    public CashPaymentVoucherExpenses() {
    }

    public CashPaymentVoucherExpenses(String paymentNumber, String paymentRefNumber, String paymentDescription, double paymentAmount, Date paymentDate, Location paymentLocation, Company relatedCompany, Expenses relatedExpenses, Logger relatedLogger) {
        this.paymentNumber = paymentNumber;
        this.paymentRefNumber = paymentRefNumber;
        this.paymentDescription = paymentDescription;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.paymentLocation = paymentLocation;
        this.relatedCompany = relatedCompany;
        this.relatedExpenses = relatedExpenses;
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

    public Expenses getRelatedExpenses() {
        return relatedExpenses;
    }

    public void setRelatedExpenses(Expenses relatedExpenses) {
        this.relatedExpenses = relatedExpenses;
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
        if (!(object instanceof CashPaymentVoucherExpenses)) {
            return false;
        }
        CashPaymentVoucherExpenses other = (CashPaymentVoucherExpenses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + getPaymentNumber() + " / " + getPaymentRefNumber() + ")" ;
    }

}
