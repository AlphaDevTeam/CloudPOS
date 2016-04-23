

package com.AlphaDevs.cloud.web.Entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class BankDeposit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String refNumber;
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date depositDate;
    @OneToOne
    private Location relatedLocation;
    private BigDecimal amount;
    @OneToOne
    private BankAccounts relatedAccount;
    @OneToOne
    private Logger relatedLogger;
    
    @OneToOne
    private Company relatedCompany;

    public BankDeposit() {
    }

    public BankDeposit(String refNumber, String description, Date depositDate, Location relatedLocation, BigDecimal amount, BankAccounts relatedAccount, Logger relatedLogger) {
        this.refNumber = refNumber;
        this.description = description;
        this.depositDate = depositDate;
        this.relatedLocation = relatedLocation;
        this.amount = amount;
        this.relatedAccount = relatedAccount;
        this.relatedLogger = relatedLogger;
    }

    public BankDeposit(String refNumber, String description, Date depositDate, Location relatedLocation, BigDecimal amount, BankAccounts relatedAccount, Logger relatedLogger, Company relatedCompany) {
        this.refNumber = refNumber;
        this.description = description;
        this.depositDate = depositDate;
        this.relatedLocation = relatedLocation;
        this.amount = amount;
        this.relatedAccount = relatedAccount;
        this.relatedLogger = relatedLogger;
        this.relatedCompany = relatedCompany;
    }

    public Company getRelatedCompany() {
        return relatedCompany;
    }

    public void setRelatedCompany(Company relatedCompany) {
        this.relatedCompany = relatedCompany;
    }

    public Logger getRelatedLogger() {
        return relatedLogger;
    }

    public void setRelatedLogger(Logger relatedLogger) {
        this.relatedLogger = relatedLogger;
    }
    
    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public Location getRelatedLocation() {
        return relatedLocation;
    }

    public void setRelatedLocation(Location relatedLocation) {
        this.relatedLocation = relatedLocation;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BankAccounts getRelatedAccount() {
        return relatedAccount;
    }

    public void setRelatedAccount(BankAccounts relatedAccount) {
        this.relatedAccount = relatedAccount;
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
        if (!(object instanceof BankDeposit)) {
            return false;
        }
        BankDeposit other = (BankDeposit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + getRelatedAccount() + " - " + getRefNumber() + ")";
    }

}
