

package com.AlphaDevs.cloud.web.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

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
public class MeterReading implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String Note;
    @OneToOne
    private Pump relatedPump;
    private double reading;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date relatedDate;
    private String referenceNumber;
    @OneToOne
    private Logger logger;
    @OneToOne
    private Location relatedLocation;
    private double lastReading;
    
    public MeterReading() {
    }

    public MeterReading(String Note, Pump relatedPump, double reading, Date relatedDate, String referenceNumber, Logger logger, Location relatedLocation, double lastReading) {
        this.Note = Note;
        this.relatedPump = relatedPump;
        this.reading = reading;
        this.relatedDate = relatedDate;
        this.referenceNumber = referenceNumber;
        this.logger = logger;
        this.relatedLocation = relatedLocation;
        this.lastReading = lastReading;
    }

    public double getLastReading() {
        return lastReading;
    }

    public void setLastReading(double lastReading) {
        this.lastReading = lastReading;
    }

    public Location getRelatedLocation() {
        return relatedLocation;
    }

    public void setRelatedLocation(Location relatedLocation) {
        this.relatedLocation = relatedLocation;
    }
    
    public Date getRelatedDate() {
        return relatedDate;
    }

    public void setRelatedDate(Date relatedDate) {
        this.relatedDate = relatedDate;
    }
    
    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
    
    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    
    
    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public Pump getRelatedPump() {
        return relatedPump;
    }

    public void setRelatedPump(Pump relatedPump) {
        this.relatedPump = relatedPump;
    }
    
    public double getReading() {
        return reading;
    }

    public void setReading(double reading) {
        this.reading = reading;
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
        if (!(object instanceof MeterReading)) {
            return false;
        }
        MeterReading other = (MeterReading) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.cloud.web.Entities.MeterReading[ id=" + id + " ]";
    }

}
