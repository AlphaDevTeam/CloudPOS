package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Enums.BillStatus;
import com.AlphaDevs.cloud.web.Enums.JOBStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Mihindu Gajaba Karunarathne Alpha Development Team (Pvt) Ltd
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
    private Date startedDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastModifiedDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date closedDate;
    @OneToMany(mappedBy = "relatedJob",fetch= FetchType.LAZY,cascade= CascadeType.ALL)
    private List<JobDetails> jobDetails;
    @OneToOne
    private Location relatedLocation;
    @OneToOne
    private JobTypes jobType;
    @OneToMany
    private List<Employee> assignedEmployees;
    private BillStatus billStatus;
    private double totalAmount;
    private double costLimit;

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

    public List<JobDetails> getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(List<JobDetails> jobDetails) {
        this.jobDetails = jobDetails;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public JobTypes getJobType() {
        return jobType;
    }

    public void setJobType(JobTypes jobType) {
        this.jobType = jobType;
    }

    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setAssignedEmployees(List<Employee> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }

    public Location getRelatedLocation() {
        return relatedLocation;
    }

    public void setRelatedLocation(Location relatedLocation) {
        this.relatedLocation = relatedLocation;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getCostLimit() {
        return costLimit;
    }

    public void setCostLimit(double costLimit) {
        this.costLimit = costLimit;
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
        return getJobNumber();
    }

}
