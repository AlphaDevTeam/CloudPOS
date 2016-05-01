

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
public class ItemBincard implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private Items item;
    private String Description;
    private double Qty;
    private double Balance;
    private String TrnNumber;
    @OneToOne
    private Logger log;
    private BillStatus billStat;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date relatedDate;
    @OneToOne
    private Location location;

    public ItemBincard() {
    }

    public ItemBincard(Items item, String Description, double Qty, double Balance, String TrnNumber, Logger log, BillStatus billStat, Date relatedDate, Location location) {
        this.item = item;
        this.Description = Description;
        this.Qty = Qty;
        this.Balance = Balance;
        this.TrnNumber = TrnNumber;
        this.log = log;
        this.billStat = billStat;
        this.relatedDate = relatedDate;
        this.location = location;
    }

    public Date getRelatedDate() {
        return relatedDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    public void setRelatedDate(Date relatedDate) {
        this.relatedDate = relatedDate;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getQty() {
        return Qty;
    }

    public void setQty(double Qty) {
        this.Qty = Qty;
    }

    public String getTrnNumber() {
        return TrnNumber;
    }

    public void setTrnNumber(String TrnNumber) {
        this.TrnNumber = TrnNumber;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
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
        if (!(object instanceof ItemBincard)) {
            return false;
        }
        ItemBincard other = (ItemBincard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.cloud.web.Entities.ItemBincard[ id=" + id + " ]";
    }

}
