
package com.AlphaDevs.cloud.web.Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author AlphaDevs
 */
@Entity
public class JobTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String jobTypeCode;
    private String jobTypeDescription;
    private double profitMargin;
    private Logger logger;

    public JobTypes() {
    }

    public JobTypes(String jobTypeCode, String jobTypeDescription, double profitMargin) {
        this.jobTypeCode = jobTypeCode;
        this.jobTypeDescription = jobTypeDescription;
        this.profitMargin = profitMargin;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    
    public String getJobTypeCode() {
        return jobTypeCode;
    }

    public void setJobTypeCode(String jobTypeCode) {
        this.jobTypeCode = jobTypeCode;
    }

    public String getJobTypeDescription() {
        return jobTypeDescription;
    }

    public void setJobTypeDescription(String jobTypeDescription) {
        this.jobTypeDescription = jobTypeDescription;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
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
        if (!(object instanceof JobTypes)) {
            return false;
        }
        JobTypes other = (JobTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getJobTypeCode() + " - " + getJobTypeDescription();
    }
    
}
 