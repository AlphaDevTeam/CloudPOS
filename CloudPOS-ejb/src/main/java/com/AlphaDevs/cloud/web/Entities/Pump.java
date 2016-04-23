
package com.AlphaDevs.cloud.web.Entities;

import java.io.Serializable;
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
public class Pump implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String pumpCode;
    private String description;
    @OneToOne
    private Items relatedItem;
    @OneToOne
    private Location relatedLocation;
    private Double lastReading;
    @OneToOne
    private Logger logger;

    public Pump() {
    }

    public Pump(Items relatedItem) {
        this.relatedItem = relatedItem;
    }
    
    public Pump(String pumpCode, String description, Items relatedItem, Location relatedLocation, Double lastReading, Logger logger) {
        this.pumpCode = pumpCode;
        this.description = description;
        this.relatedItem = relatedItem;
        this.relatedLocation = relatedLocation;
        this.lastReading = lastReading;
        this.logger = logger;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    
    public String getPumpCode() {
        return pumpCode;
    }

    public void setPumpCode(String pumpCode) {
        this.pumpCode = pumpCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Items getRelatedItem() {
        return relatedItem;
    }

    public void setRelatedItem(Items relatedItem) {
        this.relatedItem = relatedItem;
    }

    public Location getRelatedLocation() {
        return relatedLocation;
    }

    public void setRelatedLocation(Location relatedLocation) {
        this.relatedLocation = relatedLocation;
    }

    public Double getLastReading() {
        return lastReading;
    }

    public void setLastReading(Double lastReading) {
        this.lastReading = lastReading;
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
        if (!(object instanceof Pump)) {
            return false;
        }
        Pump other = (Pump) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getPumpCode();
    }

}
