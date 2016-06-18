package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Enums.Document;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author AlphaDevs
 */
@MappedSuperclass
public class PersistableDocument implements Serializable {

    private String documentNumber;
    private String documentReference;
    @Enumerated(EnumType.STRING)
    private Document documentType;
    @OneToOne
    private Logger logger;
    @OneToOne
    private Location relatedLocation;
    @OneToOne
    private Company relatedCompany;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date documentDate;

    @PostConstruct
    public void init() {
        setDocumentDate(Calendar.getInstance().getTime());
        setDocumentType(Document.NON_INIT);
    }

    public PersistableDocument() {
        documentDate = Calendar.getInstance().getTime();
        documentType = Document.NON_INIT;
    }

    public PersistableDocument(Document documentType, Logger logger, Location relatedLocation, Company relatedCompany, Date documentDate) {
        this.documentType = documentType;
        this.logger = logger;
        this.relatedLocation = relatedLocation;
        this.relatedCompany = relatedCompany;
        this.documentDate = documentDate;
    }

    public Document getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Document documentType) {
        this.documentType = documentType;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Location getRelatedLocation() {
        return relatedLocation;
    }

    public void setRelatedLocation(Location relatedLocation) {
        this.relatedLocation = relatedLocation;
    }

    public Company getRelatedCompany() {
        return relatedCompany;
    }

    public void setRelatedCompany(Company relatedCompany) {
        this.relatedCompany = relatedCompany;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentReference() {
        return documentReference;
    }

    public void setDocumentReference(String documentReference) {
        this.documentReference = documentReference;
    }
    
    @Override
    public String toString() {
        return "com.AlphaDevs.cloud.web.Entities.PersistableDocument[ Type=" + getDocumentType() + " ]";
    }

}
