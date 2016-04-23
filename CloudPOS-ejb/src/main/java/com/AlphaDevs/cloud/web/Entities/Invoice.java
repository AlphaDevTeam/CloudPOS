

package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Enums.BillStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@Entity
public class Invoice  implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String BillNo;
    private String RefNo;
    private double TotalAmount;
    private BillStatus billStatus;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date TrnDate;
    
    @OneToOne
    private Supplier Customer;
    
    @OneToOne
    private Location Location;
    
    @OneToOne
    private Logger Logger;
        
    @OneToMany(mappedBy = "invoice" , cascade = CascadeType.PERSIST)
    private List<InvoiceDetails> InvDetails;
    
    @OneToMany
    private List<Properties> extraz;
    
    @OneToOne(cascade = CascadeType.ALL)
    private PaymentDetails paymentDetails;
    
    public Invoice() {
    }

    public Invoice(String BillNo, String RefNo, double TotalAmount, BillStatus BillStat, Date TrnDate, Supplier Customer, Location Location, Logger Logger, List<InvoiceDetails> InvDetails, List<Properties> extraz, PaymentDetails paymentDetails) {
        this.BillNo = BillNo;
        this.RefNo = RefNo;
        this.TotalAmount = TotalAmount;
        this.billStatus = BillStat;
        this.TrnDate = TrnDate;
        this.Customer = Customer;
        this.Location = Location;
        this.Logger = Logger;
        this.InvDetails = InvDetails;
        this.extraz = extraz;
        this.paymentDetails = paymentDetails;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getBillNo() {
        return BillNo;
    }

    public void setBillNo(String BillNo) {
        this.BillNo = BillNo;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }

    public Supplier getCustomer() {
        return Customer;
    }

    public void setCustomer(Supplier Customer) {
        this.Customer = Customer;
    }

    public com.AlphaDevs.cloud.web.Entities.Location getLocation() {
        return Location;
    }

    public void setLocation(com.AlphaDevs.cloud.web.Entities.Location Location) {
        this.Location = Location;
    }

    public com.AlphaDevs.cloud.web.Entities.Logger getLogger() {
        return Logger;
    }

    public void setLogger(com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.Logger = Logger;
    }

    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String RefNo) {
        this.RefNo = RefNo;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public Date getTrnDate() {
        return TrnDate;
    }

    public void setTrnDate(Date TrnDate) {
        this.TrnDate = TrnDate;
    }

    public List<InvoiceDetails> getInvDetails() {
        return InvDetails;
    }

    public void setInvDetails(List<InvoiceDetails> InvDetails) {
        this.InvDetails = InvDetails;
    }

    public List<Properties> getExtraz() {
        return extraz;
    }

    public void setExtraz(List<Properties> extraz) {
        this.extraz = extraz;
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() 
    {
        return "(" + getBillNo() + "/" + getRefNo() + ")";            
        
    }

}
