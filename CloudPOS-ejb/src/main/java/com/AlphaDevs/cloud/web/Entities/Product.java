
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
public class Product implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String ProductDescription;
    private String ProdCode;
    private double ProfitPerc;
    private String ProductPrefix;

    @OneToOne
    private Logger Logger;
    
    public Product() {
    }

    public Product(String ProductDescription, String ProdCode, double ProfitPerc, com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.ProductDescription = ProductDescription;
        this.ProdCode = ProdCode;
        this.ProfitPerc = ProfitPerc;
        this.Logger = Logger;
    }

    public Product(String ProductDescription, String ProdCode, double ProfitPerc, String ProductPrefix, com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.ProductDescription = ProductDescription;
        this.ProdCode = ProdCode;
        this.ProfitPerc = ProfitPerc;
        this.ProductPrefix = ProductPrefix;
        this.Logger = Logger;
    }
    

    public String getProductPrefix() {
        return ProductPrefix;
    }

    public void setProductPrefix(String ProductPrefix) {
        this.ProductPrefix = ProductPrefix;
    }
    

    public com.AlphaDevs.cloud.web.Entities.Logger getLogger() {
        return Logger;
    }

    public void setLogger(com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.Logger = Logger;
    }

    
    

    public String getProdCode() {
        return ProdCode;
    }

    public void setProdCode(String ProdCode) {
        this.ProdCode = ProdCode;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String ProductDescription) {
        this.ProductDescription = ProductDescription;
    }

    public double getProfitPerc() {
        return ProfitPerc;
    }

    public void setProfitPerc(double ProfitPerc) {
        this.ProfitPerc = ProfitPerc;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ProductDescription;
    }

}
