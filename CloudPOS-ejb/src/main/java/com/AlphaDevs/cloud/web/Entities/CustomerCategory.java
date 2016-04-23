
package com.AlphaDevs.cloud.web.Entities;

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
public class CustomerCategory implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String Description;

    @OneToOne(cascade= CascadeType.ALL)
    private Logger Logger;
    
    public CustomerCategory(String Description) {
        this.Description = Description;
    }

    public String getDescription() 
    {
        return Description;
    }

    public void setDescription(String Description) 
    {
        this.Description = Description;
    }

    public CustomerCategory() {
    }

    public com.AlphaDevs.cloud.web.Entities.Logger getLogger() {
        return Logger;
    }

    public void setLogger(com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.Logger = Logger;
    }

    public CustomerCategory(String Description, com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.Description = Description;
        this.Logger = Logger;
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
        if (!(object instanceof CustomerCategory)) {
            return false;
        }
        CustomerCategory other = (CustomerCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() 
    {
        return Description;
    }
    
}
