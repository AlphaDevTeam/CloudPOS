

package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Enums.BillStatus;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@Entity
public class CustomerTransaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @OneToOne
    private Supplier supplier;
    
    private String Description;
    private double CR;
    private double DR;
    private double Balance;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    private BillStatus billStat;
    
    @OneToOne
    private Logger Logger;

    public CustomerTransaction() {
    }

    public CustomerTransaction(Supplier supplier, String Description, double CR, double DR, double Balance, Date date, BillStatus billStat, Logger Logger) {
        this.supplier = supplier;
        this.Description = Description;
        this.CR = CR;
        this.DR = DR;
        this.Balance = Balance;
        this.date = date;
        this.billStat = billStat;
        this.Logger = Logger;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public com.AlphaDevs.cloud.web.Entities.Logger getLogger() {
        return Logger;
    }

    public void setLogger(com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.Logger = Logger;
    }

    public CustomerTransaction(Supplier supplier, String Description, double CR, double DR, double Balance, com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.supplier = supplier;
        this.Description = Description;
        this.CR = CR;
        this.DR = DR;
        this.Balance = Balance;
        this.Logger = Logger;
    }

    public CustomerTransaction(Supplier supplier, String Description, double CR, double DR, double Balance, BillStatus billStat, com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.supplier = supplier;
        this.Description = Description;
        this.CR = CR;
        this.DR = DR;
        this.Balance = Balance;
        this.billStat = billStat;
        this.Logger = Logger;
    }

    public BillStatus getBillStat() {
        return billStat;
    }

    public void setBillStat(BillStatus billStat) {
        this.billStat = billStat;
    }
    
    
    public double getBalance() {
        return Balance;
    }

    public void setBalance(double Balance) {
        this.Balance = Balance;
    }

    public double getCR() {
        return CR;
    }

    public void setCR(double CR) {
        this.CR = CR;
    }

    public double getDR() {
        return DR;
    }

    public void setDR(double DR) {
        this.DR = DR;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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
        if (!(object instanceof CustomerTransaction)) {
            return false;
        }
        CustomerTransaction other = (CustomerTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.cloud.web.Entities.CustomerTransaction[ id=" + id + " ]";
    }

}
