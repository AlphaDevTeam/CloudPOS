
package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Enums.BillStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
public class BankBalance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private Long id;
    
    @OneToOne
    private Location location;
    
    private double bankBalance;
    private BillStatus billStatus;
    @OneToOne
    private BankAccounts relatedAccount;
    private BigDecimal balance;

    public BankBalance() {
    }

    public BankBalance(Location location, double bankBalance, BillStatus billStatus, BankAccounts relatedAccount, BigDecimal balance) {
        this.location = location;
        this.bankBalance = bankBalance;
        this.billStatus = billStatus;
        this.relatedAccount = relatedAccount;
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BankAccounts getRelatedAccount() {
        return relatedAccount;
    }

    public void setRelatedAccount(BankAccounts relatedAccount) {
        this.relatedAccount = relatedAccount;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(double bankBalance) {
        this.bankBalance = bankBalance;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
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
        if (!(object instanceof BankBalance)) {
            return false;
        }
        BankBalance other = (BankBalance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.cloud.web.Entities.BankBalance[ id=" + id + " ]";
    }

}
