
package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Interfaces.Meterials;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author AlphaDevs
 */
@Entity
public class JobDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
   
    private Meterials meterial;
    private float qty;
    private String note;
    private double itemCost;
    @ManyToOne
    private Job relatedJob;

    public JobDetails() {
    }

    public Meterials getMeterial1() {
        if(meterial instanceof Items){
            System.out.println("Cast to Item and Returned");
            return (Items) meterial;
        }
        return (Items) meterial;
    }

    public void setMeterial1(Meterials meterial) {
        if(meterial instanceof Items){
            this.meterial = (Items) meterial;
            System.out.println("Cast to Item and assigned");
        }
        
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }
    
    public Job getRelatedJob() {
        return relatedJob;
    }

    public void setRelatedJob(Job relatedJob) {
        this.relatedJob = relatedJob;
    }

    public Meterials getMeterial() {
        return meterial;
    }

    public void setMeterial(Meterials meterial) {
        this.meterial = meterial;
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
        if (!(object instanceof JobDetails)) {
            return false;
        }
        JobDetails other = (JobDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.cloud.web.Entities.JobDetails[ id=" + id + " ]";
    }
    
}
