
package com.AlphaDevs.cloud.web.Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.primefaces.model.SelectableDataModel;


/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@Entity
public class BankBranch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String brnachCode;
    private String branchName;
    @OneToOne
    private Bank relatedBank;
    public BankBranch() {
    }

    public BankBranch(String brnachCode, String branchName) {
        this.brnachCode = brnachCode;
        this.branchName = branchName;
    }

    public BankBranch(String brnachCode, String branchName, Bank relatedBank) {
        this.brnachCode = brnachCode;
        this.branchName = branchName;
        this.relatedBank = relatedBank;
    }

    public Bank getRelatedBank() {
        return relatedBank;
    }

    public void setRelatedBank(Bank relatedBank) {
        this.relatedBank = relatedBank;
    }
    
    public String getBrnachCode() {
        return brnachCode;
    }

    public void setBrnachCode(String brnachCode) {
        this.brnachCode = brnachCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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
        if (!(object instanceof BankBranch)) {
            return false;
        }
        BankBranch other = (BankBranch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getBrnachCode() + " - " + getBranchName();
    }
    
}
