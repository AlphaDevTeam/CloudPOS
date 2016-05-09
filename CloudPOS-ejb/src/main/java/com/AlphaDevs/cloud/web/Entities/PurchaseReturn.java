package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Enums.BillStatus;
import com.AlphaDevs.cloud.web.Enums.GRNFlags;
import com.AlphaDevs.cloud.web.Enums.PurchaseType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 *
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@Entity
public class PurchaseReturn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String grnRetNo;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date grnRetDate;

    @OneToOne
    private Supplier supplier;

    private String invNo;

    @Enumerated(EnumType.ORDINAL)
    private PurchaseType purchaseType;

    private double CreditPeriod;
    private double TotalAmount;

    @Enumerated(EnumType.ORDINAL)
    private GRNFlags grnFlags;

    @OneToOne
    private Location location;

    @Enumerated(EnumType.ORDINAL)
    private BillStatus billStatus;

    @OneToMany(mappedBy = "relatedPurchaseRet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PurchaseReturnDetails> purchaceRtnDetails;

    @OneToOne
    private Logger logger;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Properties> extraz;

    public PurchaseReturn() {
    }

    public PurchaseReturn(String grnRetNo, Date grnRetDate, Supplier supplier, String invNo, PurchaseType purchaseType, double CreditPeriod, double TotalAmount, GRNFlags grnFlags, Location location, BillStatus billStatus, List<PurchaseReturnDetails> purchaceRtnDetails, Logger logger) {
        this.grnRetNo = grnRetNo;
        this.grnRetDate = grnRetDate;
        this.supplier = supplier;
        this.invNo = invNo;
        this.purchaseType = purchaseType;
        this.CreditPeriod = CreditPeriod;
        this.TotalAmount = TotalAmount;
        this.grnFlags = grnFlags;
        this.location = location;
        this.billStatus = billStatus;
        this.purchaceRtnDetails = purchaceRtnDetails;
        this.logger = logger;
    }

    public double getCreditPeriod() {
        return CreditPeriod;
    }

    public void setCreditPeriod(double CreditPeriod) {
        this.CreditPeriod = CreditPeriod;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }

    public GRNFlags getGrnFlags() {
        return grnFlags;
    }

    public void setGrnFlags(GRNFlags grnFlags) {
        this.grnFlags = grnFlags;
    }

    public Date getGrnRetDate() {
        return grnRetDate;
    }

    public void setGrnRetDate(Date grnRetDate) {
        this.grnRetDate = grnRetDate;
    }

    public String getGrnRetNo() {
        return grnRetNo;
    }

    public void setGrnRetNo(String grnRetNo) {
        this.grnRetNo = grnRetNo;
    }

    public List<Properties> getExtraz() {
        return extraz;
    }

    public void setExtraz(List<Properties> extraz) {
        this.extraz = extraz;
    }
    
    public String getInvNo() {
        return invNo;
    }

    public void setInvNo(String invNo) {
        this.invNo = invNo;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public List<PurchaseReturnDetails> getPurchaceRtnDetails() {
        return purchaceRtnDetails;
    }

    public void setPurchaceRtnDetails(List<PurchaseReturnDetails> purchaceRtnDetails) {
        this.purchaceRtnDetails = purchaceRtnDetails;
    }

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(PurchaseType purchaseType) {
        this.purchaseType = purchaseType;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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
        if (!(object instanceof PurchaseReturn)) {
            return false;
        }
        PurchaseReturn other = (PurchaseReturn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.cloud.web.Entities.PurchaseReturn[ id=" + id + " ]";
    }

}
