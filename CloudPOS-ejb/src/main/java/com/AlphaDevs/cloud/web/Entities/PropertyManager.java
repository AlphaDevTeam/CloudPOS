package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.Enums.PropertieType;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Mihindu Gajaba Karunarathne Alpha Development Team (Pvt) Ltd
 *
 */
@Entity
public class PropertyManager implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Document document;
    private String fieldName;
    @OneToOne
    private Company company;
    private boolean required;
    private PropertieType type;

    public PropertyManager() {
    }

    @PostConstruct
    public void init() {

    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public PropertieType getType() {
        return type;
    }

    public void setType(PropertieType type) {
        this.type = type;
    }

    public PropertyManager(Document document, String fieldName, Company company, boolean required, PropertieType type) {
        this.document = document;
        this.fieldName = fieldName;
        this.company = company;
        this.required = required;
        this.type = type;
    }

    public PropertyManager(Document document, String fieldName, Company company) {
        this.document = document;
        this.fieldName = fieldName;
        this.company = company;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
        if (!(object instanceof PropertyManager)) {
            return false;
        }
        PropertyManager other = (PropertyManager) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.cloud.web.Entities.PropertyManager[ id=" + id + " ]";
    }

}
