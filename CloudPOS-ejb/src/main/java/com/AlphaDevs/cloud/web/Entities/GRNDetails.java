
package com.AlphaDevs.cloud.web.Entities;

import java.io.Serializable;
import javax.persistence.*;
import org.primefaces.model.SelectableDataModel;

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
public class GRNDetails implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private Items GrnItem;
    
    private float GrnQty;
    private double GrnItemCost;
    private float GrnItemDiscountPerc;
    
  
    @ManyToOne
    private GRN relatedGRN;

    public GRNDetails() {
    }

    public GRNDetails(Items GrnItem, float GrnQty, double GrnItemCost, float GrnItemDiscountPerc, GRN relatedGRN) {
        this.GrnItem = GrnItem;
        this.GrnQty = GrnQty;
        this.GrnItemCost = GrnItemCost;
        this.GrnItemDiscountPerc = GrnItemDiscountPerc;
        this.relatedGRN = relatedGRN;
    }

    public GRN getRelatedGRN() {
        return relatedGRN;
    }

    public void setRelatedGRN(GRN relatedGRN) {
        this.relatedGRN = relatedGRN;
    }

    

    public Items getGrnItem() {
        return GrnItem;
    }

    public void setGrnItem(Items GrnItem) {
        this.GrnItem = GrnItem;
    }

    public double getGrnItemCost() {
        return GrnItemCost;
    }

    public void setGrnItemCost(double GrnItemCost) {
        this.GrnItemCost = GrnItemCost;
    }

    public float getGrnItemDiscountPerc() {
        return GrnItemDiscountPerc;
    }

    public void setGrnItemDiscountPerc(float GrnItemDiscountPerc) {
        this.GrnItemDiscountPerc = GrnItemDiscountPerc;
    }

    public float getGrnQty() {
        return GrnQty;
    }

    public void setGrnQty(float GrnQty) {
        this.GrnQty = GrnQty;
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
        if (!(object instanceof GRNDetails)) {
            return false;
        }
        GRNDetails other = (GRNDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.cloud.web.Entities.GRNDetails[ id=" + id + " ]";
    }

    

}
