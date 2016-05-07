
package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Enums.BillStatus;
import java.io.Serializable;
import javax.persistence.*;


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
public class Stock implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @OneToOne
    private Items SockItem;
    private float StockQty;
    @OneToOne
    private Location StockLocation;
    @OneToOne
    private Company relatedCompany;
    private BillStatus billStatus;

    public Company getRelatedCompany() {
        return relatedCompany;
    }

    public void setRelatedCompany(Company relatedCompany) {
        this.relatedCompany = relatedCompany;
    }

    public Stock() {
    }

    public Stock(Items SockItem, float StockQty, Location StockLocation) {
        this.SockItem = SockItem;
        this.StockQty = StockQty;
        this.StockLocation = StockLocation;
    }

    public Items getSockItem() {
        return SockItem;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }
    
    public void setSockItem(Items SockItem) {
        this.SockItem = SockItem;
    }

    public Location getStockLocation() {
        return StockLocation;
    }

    public void setStockLocation(Location StockLocation) {
        this.StockLocation = StockLocation;
    }

    public float getStockQty() {
        return StockQty;
    }

    public void setStockQty(float StockQty) {
        this.StockQty = StockQty;
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
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.cloud.web.Entities.Stock[ id=" + id + " ]";
    }

}
