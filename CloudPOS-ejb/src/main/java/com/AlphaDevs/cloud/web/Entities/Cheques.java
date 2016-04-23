package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Enums.ChequeStatus;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 *
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@Entity
public class Cheques implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ChequeStatus status;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date chequeDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date chequeRecDate;
    @OneToOne
    private Location relatedLocation;
    @OneToOne
    private BankAccounts relatedBankAccount;
    private Double chequeAmount;
    private String chequeNumber;
    private String chequeNote;
    @OneToOne(cascade = CascadeType.MERGE)
    private PaymentDetails relatedPayment;

    public Cheques() {
    }

    public Cheques(ChequeStatus status, Date chequeDate, Location relatedLocation, BankAccounts relatedBankAccount, Double chequeAmount, String chequeNumber, String chequeNote, PaymentDetails relatedPayment) {
        this.status = status;
        this.chequeDate = chequeDate;
        this.relatedLocation = relatedLocation;
        this.relatedBankAccount = relatedBankAccount;
        this.chequeAmount = chequeAmount;
        this.chequeNumber = chequeNumber;
        this.chequeNote = chequeNote;
        this.relatedPayment = relatedPayment;
    }

    public Cheques(ChequeStatus status, Date chequeDate, Location relatedLocation, BankAccounts relatedBankAccount, Double chequeAmount, String chequeNumber, String chequeNote) {
        this.status = status;
        this.chequeDate = chequeDate;
        this.relatedLocation = relatedLocation;
        this.relatedBankAccount = relatedBankAccount;
        this.chequeAmount = chequeAmount;
        this.chequeNumber = chequeNumber;
        this.chequeNote = chequeNote;
    }

    public Cheques(ChequeStatus status, Date chequeDate, Date chequeRecDate, Location relatedLocation, BankAccounts relatedBankAccount, Double chequeAmount, String chequeNumber, String chequeNote, PaymentDetails relatedPayment) {
        this.status = status;
        this.chequeDate = chequeDate;
        this.chequeRecDate = chequeRecDate;
        this.relatedLocation = relatedLocation;
        this.relatedBankAccount = relatedBankAccount;
        this.chequeAmount = chequeAmount;
        this.chequeNumber = chequeNumber;
        this.chequeNote = chequeNote;
        this.relatedPayment = relatedPayment;
    }

    public Date getChequeRecDate() {
        return chequeRecDate;
    }

    public void setChequeRecDate(Date chequeRecDate) {
        this.chequeRecDate = chequeRecDate;
    }

    public PaymentDetails getRelatedPayment() {
        return relatedPayment;
    }

    public void setRelatedPayment(PaymentDetails relatedPayment) {
        this.relatedPayment = relatedPayment;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getChequeNote() {
        return chequeNote;
    }

    public void setChequeNote(String chequeNote) {
        this.chequeNote = chequeNote;
    }

    public ChequeStatus getStatus() {
        return status;
    }

    public void setStatus(ChequeStatus status) {
        this.status = status;
    }

    public Date getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(Date chequeDate) {
        this.chequeDate = chequeDate;
    }

    public Location getRelatedLocation() {
        return relatedLocation;
    }

    public void setRelatedLocation(Location relatedLocation) {
        this.relatedLocation = relatedLocation;
    }

    public BankAccounts getRelatedBankAccount() {
        return relatedBankAccount;
    }

    public void setRelatedBankAccount(BankAccounts relatedBankAccount) {
        this.relatedBankAccount = relatedBankAccount;
    }

    public Double getChequeAmount() {
        return chequeAmount;
    }

    public void setChequeAmount(Double chequeAmount) {
        this.chequeAmount = chequeAmount;
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
        if (!(object instanceof Cheques)) {
            return false;
        }
        Cheques other = (Cheques) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getChequeNumber() + " - " + getChequeNote();
    }

}
