

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
public class Design implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String Description;
    @OneToOne
    private Product product;
    
    private String DesignCode;
    private double DesignProfitPerc;
    private String DesignPrefix;
    
    
    @OneToOne
    private Logger Logger;

    public Design() {
    }

    public Design(String Description, Product product, String DesignCode, double DesignProfitPerc, com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.Description = Description;
        this.product = product;
        this.DesignCode = DesignCode;
        this.DesignProfitPerc = DesignProfitPerc;
        this.Logger = Logger;
    }

    public Design(String Description, Product product, String DesignCode, double DesignProfitPerc, String DesignPrefix, com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.Description = Description;
        this.product = product;
        this.DesignCode = DesignCode;
        this.DesignProfitPerc = DesignProfitPerc;
        this.DesignPrefix = DesignPrefix;
        this.Logger = Logger;
    }
    
    

    public String getDesignPrefix() {
        return DesignPrefix;
    }

    public void setDesignPrefix(String DesignPrefix) {
        this.DesignPrefix = DesignPrefix;
    }
    

    public com.AlphaDevs.cloud.web.Entities.Logger getLogger() {
        return Logger;
    }

    public void setLogger(com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.Logger = Logger;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDesignCode() {
        return DesignCode;
    }

    public void setDesignCode(String DesignCode) {
        this.DesignCode = DesignCode;
    }

    public double getDesignProfitPerc() {
        return DesignProfitPerc;
    }

    public void setDesignProfitPerc(double DesignProfitPerc) {
        this.DesignProfitPerc = DesignProfitPerc;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
        if (!(object instanceof Design)) {
            return false;
        }
        Design other = (Design) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return Description;
    }

}
