
package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Enums.JOBStatus;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;


/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@Entity
public class Job implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String jobNumber;
    private String jobDescription;
    @Enumerated(EnumType.STRING)
    private JOBStatus jobStatus;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date jobAssingedDate;
    
    @OneToOne
    private Logger logger;

    public Job() {
    }

    public Job(String jobNumber, String jobDescription, JOBStatus jobStatus, Date jobAssingedDate) {
        this.jobNumber = jobNumber;
        this.jobDescription = jobDescription;
        this.jobStatus = jobStatus;
        this.jobAssingedDate = jobAssingedDate;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public JOBStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JOBStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Date getJobAssingedDate() {
        return jobAssingedDate;
    }

    public void setJobAssingedDate(Date jobAssingedDate) {
        this.jobAssingedDate = jobAssingedDate;
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
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.cloud.web.Entities.Job[ id=" + id + " ]";
    }
    
}
