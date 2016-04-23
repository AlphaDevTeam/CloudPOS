
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
public class InvoiceDetails implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    
    
    @OneToOne
    private Items Item;
    
    private float ItemQty;
    private double Price;
    
    @ManyToOne
    private Invoice invoice;

    public InvoiceDetails() {
    }

    public InvoiceDetails(Items Item, float ItemQty, double Price, Invoice invoice) {
        this.Item = Item;
        this.ItemQty = ItemQty;
        this.Price = Price;
        this.invoice = invoice;
    }

    public InvoiceDetails(Items Item, float ItemQty, double Price) {
        this.Item = Item;
        this.ItemQty = ItemQty;
        this.Price = Price;
    }
    

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    
    public Items getItem() {
        return Item;
    }

    public void setItem(Items Item) {
        this.Item = Item;
    }

    public float getItemQty() {
        return ItemQty;
    }

    public void setItemQty(float ItemQty) {
        this.ItemQty = ItemQty;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
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
        if (!(object instanceof InvoiceDetails)) {
            return false;
        }
        InvoiceDetails other = (InvoiceDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.cloud.web.Entities.InvoiceDetails[ id=" + id + " ]";
    }

}
