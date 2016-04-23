
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
 * 2013-12-26
 * 
 */
@Entity
public class Units implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String unitDescription;
    private String unitCode;
    
    
    @OneToOne
    private Logger Logger;

    public Units() {
    }

    public Units(String unitDescription, String unitCode) {
        this.unitDescription = unitDescription;
        this.unitCode = unitCode;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
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
        if (!(object instanceof Units)) {
            return false;
        }
        Units other = (Units) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getUnitCode();
    }

    /**
     * @return the Logger
     */
    public Logger getLogger() {
        return Logger;
    }

    /**
     * @param Logger the Logger to set
     */
    public void setLogger(Logger Logger) {
        this.Logger = Logger;
    }
    
}
