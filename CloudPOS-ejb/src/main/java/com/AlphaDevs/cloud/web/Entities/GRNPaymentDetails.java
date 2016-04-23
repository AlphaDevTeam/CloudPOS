

package com.AlphaDevs.cloud.web.Entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@Entity
public class GRNPaymentDetails implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @OneToOne
    private GRN relatedGrn;
    private double TotalAmount;
    private double CashAmount;
    private double CreditCardAmount;
    private double ChequeAmount;
    @OneToOne
    private Logger logger;
    
    @OneToOne
    private Cheques relatedCheque;
    
    @OneToOne
    private CreditCardReceipts relatedCreditCardReceipts;
    
    public GRNPaymentDetails() {
    }

    public GRNPaymentDetails(GRN relatedGrn, double TotalAmount, double CashAmount, double CreditCardAmount, double ChequeAmount, Logger logger, Cheques relatedCheque, CreditCardReceipts relatedCreditCardReceipts) {
        this.relatedGrn = relatedGrn;
        this.TotalAmount = TotalAmount;
        this.CashAmount = CashAmount;
        this.CreditCardAmount = CreditCardAmount;
        this.ChequeAmount = ChequeAmount;
        this.logger = logger;
        this.relatedCheque = relatedCheque;
        this.relatedCreditCardReceipts = relatedCreditCardReceipts;
    }

    public CreditCardReceipts getRelatedCreditCardReceipts() {
        return relatedCreditCardReceipts;
    }

    public void setRelatedCreditCardReceipts(CreditCardReceipts relatedCreditCardReceipts) {
        this.relatedCreditCardReceipts = relatedCreditCardReceipts;
    }

    public Cheques getRelatedCheque() {
        return relatedCheque;
    }

    public void setRelatedCheque(Cheques relatedCheque) {
        this.relatedCheque = relatedCheque;
    }
    
    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    
    public double getCashAmount() {
        return CashAmount;
    }

    public void setCashAmount(double CashAmount) {
        this.CashAmount = CashAmount;
    }

    public double getChequeAmount() {
        return ChequeAmount;
    }

    public void setChequeAmount(double ChequeAmount) {
        this.ChequeAmount = ChequeAmount;
    }

    public double getCreditCardAmount() {
        return CreditCardAmount;
    }

    public void setCreditCardAmount(double CreditCardAmount) {
        this.CreditCardAmount = CreditCardAmount;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public GRN getRelatedGrn() {
        return relatedGrn;
    }

    public void setRelatedGrn(GRN relatedGrn) {
        this.relatedGrn = relatedGrn;
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
        if (!(object instanceof GRNPaymentDetails)) {
            return false;
        }
        GRNPaymentDetails other = (GRNPaymentDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getRelatedGrn().toString();
    }

}
