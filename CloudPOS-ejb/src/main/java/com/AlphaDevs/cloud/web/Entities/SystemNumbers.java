
package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.Extra.NumberFormatUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class SystemNumbers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Document document;
    private String documentPrefix;
    private BigDecimal systemNumber;
    @OneToOne
    private Company relatedCompany;
    @ManyToOne
    private Location relatedLocation;

    public SystemNumbers() {
    }

    public SystemNumbers(Document document, String documentPrefix, BigDecimal systemNumber, Company relatedCompany, Location relatedLocation) {
        this.document = document;
        this.documentPrefix = documentPrefix;
        this.systemNumber = systemNumber;
        this.relatedCompany = relatedCompany;
        this.relatedLocation = relatedLocation;
    }

    public Company getRelatedCompany() {
        return relatedCompany;
    }

    public void setRelatedCompany(Company relatedCompany) {
        this.relatedCompany = relatedCompany;
    }

    public Location getRelatedLocation() {
        return relatedLocation;
    }

    public void setRelatedLocation(Location relatedLocation) {
        this.relatedLocation = relatedLocation;
    }
    
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getDocumentPrefix() {
        return documentPrefix;
    }

    public void setDocumentPrefix(String documentPrefix) {
        this.documentPrefix = documentPrefix;
    }

    public BigDecimal getSystemNumber() {
        return NumberFormatUtil.formatSystemNumbers(systemNumber);
    }

    public void setSystemNumber(BigDecimal systemNumber) {
        this.systemNumber = NumberFormatUtil.formatSystemNumbers(systemNumber);
    }
    
    public BigDecimal getIncrementedSystemNumber() {
        BigDecimal formatSystemNumbers = NumberFormatUtil.formatSystemNumbers(systemNumber).add(new BigDecimal(1));
        return formatSystemNumbers;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDocumentSystemNo()
    {
        BigDecimal systemNo = getSystemNumber().add(new BigDecimal(1));
        return getDocumentPrefix() + "-" + systemNo;
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
        if (!(object instanceof SystemNumbers)) {
            return false;
        }
        SystemNumbers other = (SystemNumbers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.cloud.web.Entities.SystemNumbers[ id=" + id + " ]";
    }
    
}
