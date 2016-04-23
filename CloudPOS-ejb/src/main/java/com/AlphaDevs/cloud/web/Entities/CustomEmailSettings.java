
package com.AlphaDevs.cloud.web.Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class CustomEmailSettings implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    private Company relatedCompany;
    @OneToOne
    private Location relatedLocation;
    @OneToOne
    private Logger relatedLoger;
    @ElementCollection
    @CollectionTable(name = "emailLinks", joinColumns = {@JoinColumn(name="EmailLinkId")})
    private List<EmailAddress> toEmailAddress;
    private String fromEmailAddress;
    private String fromEmailPassword;
    private String attachmentPrefix;
    private String messageBody;
    private String emailSubject;
    private String smtpServer; 

    public CustomEmailSettings() {
    }

    public CustomEmailSettings(Company relatedCompany, Location relatedLocation, Logger relatedLoger, List<EmailAddress> toEmailAddress, String fromEmailAddress, String fromEmailPassword, String attachmentPrefix, String messageBody, String emailSubject, String smtpServer) {
        this.relatedCompany = relatedCompany;
        this.relatedLocation = relatedLocation;
        this.relatedLoger = relatedLoger;
        this.toEmailAddress = toEmailAddress;
        this.fromEmailAddress = fromEmailAddress;
        this.fromEmailPassword = fromEmailPassword;
        this.attachmentPrefix = attachmentPrefix;
        this.messageBody = messageBody;
        this.emailSubject = emailSubject;
        this.smtpServer = smtpServer;
    }

    
    /**
     * @return the toEmailAddress
     */
    public List<EmailAddress> getToEmailAddress() {
        return toEmailAddress;
    }

    /**
     * @param toEmailAddress the toEmailAddress to set
     */
    public void setToEmailAddress(List<EmailAddress> toEmailAddress) {
        this.toEmailAddress = toEmailAddress;
    }
    
    

    /**
     * @return the fromEmailAddress
     */
    public String getFromEmailAddress() {
        return fromEmailAddress;
    }

    /**
     * @param fromEmailAddress the fromEmailAddress to set
     */
    public void setFromEmailAddress(String fromEmailAddress) {
        this.fromEmailAddress = fromEmailAddress;
    }

    /**
     * @return the messageBody
     */
    public String getMessageBody() {
        return messageBody;
    }

    /**
     * @param messageBody the messageBody to set
     */
    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    /**
     * @return the emailSubject
     */
    public String getEmailSubject() {
        return emailSubject;
    }

    /**
     * @param emailSubject the emailSubject to set
     */
    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    /**
     * @return the smtpServer
     */
    public String getSmtpServer() {
        return smtpServer;
    }

    /**
     * @param smtpServer the smtpServer to set
     */
    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
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
        if (!(object instanceof CustomEmailSettings)) {
            return false;
        }
        CustomEmailSettings other = (CustomEmailSettings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getRelatedCompany() + " - " + getRelatedLocation() ;
    }

    /**
     * @return the relatedCompany
     */
    public Company getRelatedCompany() {
        return relatedCompany;
    }

    /**
     * @param relatedCompany the relatedCompany to set
     */
    public void setRelatedCompany(Company relatedCompany) {
        this.relatedCompany = relatedCompany;
    }

    /**
     * @return the relatedLocation
     */
    public Location getRelatedLocation() {
        return relatedLocation;
    }

    /**
     * @param relatedLocation the relatedLocation to set
     */
    public void setRelatedLocation(Location relatedLocation) {
        this.relatedLocation = relatedLocation;
    }

    /**
     * @return the relatedLoger
     */
    public Logger getRelatedLoger() {
        return relatedLoger;
    }

    /**
     * @param relatedLoger the relatedLoger to set
     */
    public void setRelatedLoger(Logger relatedLoger) {
        this.relatedLoger = relatedLoger;
    }

    /**
     * @return the fromEmailPassword
     */
    public String getFromEmailPassword() {
        return fromEmailPassword;
    }

    /**
     * @param fromEmailPassword the fromEmailPassword to set
     */
    public void setFromEmailPassword(String fromEmailPassword) {
        this.fromEmailPassword = fromEmailPassword;
    }

    /**
     * @return the attachmentPrefix
     */
    public String getAttachmentPrefix() {
        return attachmentPrefix;
    }

    /**
     * @param attachmentPrefix the attachmentPrefix to set
     */
    public void setAttachmentPrefix(String attachmentPrefix) {
        this.attachmentPrefix = attachmentPrefix;
    }
    
}

