

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
public class Expenses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String expCode;
    private String expDescription;
    @OneToOne
    private Location expLcoation;
    @OneToOne
    private Logger logger;
    public Expenses() {
    }

    public Expenses(String expCode, String expDescription, Location expLcoation) {
        this.expCode = expCode;
        this.expDescription = expDescription;
        this.expLcoation = expLcoation;
    }

    public Expenses(String expCode, String expDescription, Location expLcoation, Logger logger) {
        this.expCode = expCode;
        this.expDescription = expDescription;
        this.expLcoation = expLcoation;
        this.logger = logger;
    }

    public String getExpCode() {
        return expCode;
    }

    public void setExpCode(String expCode) {
        this.expCode = expCode;
    }

    public String getExpDescription() {
        return expDescription;
    }

    public void setExpDescription(String expDescription) {
        this.expDescription = expDescription;
    }

    public Location getExpLcoation() {
        return expLcoation;
    }

    public void setExpLcoation(Location expLcoation) {
        this.expLcoation = expLcoation;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
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
        if (!(object instanceof Expenses)) {
            return false;
        }
        Expenses other = (Expenses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getExpCode() + " - " + getExpDescription() ;
    }

}
