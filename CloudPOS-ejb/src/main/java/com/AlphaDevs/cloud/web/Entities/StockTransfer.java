
package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Enums.TransferTypes;
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
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@Entity
public class StockTransfer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String refNumber;
    private String transferDescription;
    private Double transferQty;
    @OneToOne
    private Logger logger;
    
    @OneToOne
    private Location transferFromLocation;
    
    @OneToOne
    private Location transferToLocation;
   
    @OneToOne
    private Items transferItem;
    
    @Enumerated(EnumType.STRING)
    private TransferTypes transferType;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date transferDate;

    public StockTransfer() {
    }

    public StockTransfer(Double transferQty, Items transferItem, TransferTypes transferType) {
        this.transferQty = transferQty;
        this.transferItem = transferItem;
        this.transferType = transferType;
    }

    public StockTransfer(String refNumber, String transferDescription, Double transferQty, Logger logger, Location transferFromLocation, Location transferToLocation, Items transferItem, TransferTypes transferType, Date transferDate) {
        this.refNumber = refNumber;
        this.transferDescription = transferDescription;
        this.transferQty = transferQty;
        this.logger = logger;
        this.transferFromLocation = transferFromLocation;
        this.transferToLocation = transferToLocation;
        this.transferItem = transferItem;
        this.transferType = transferType;
        this.transferDate = transferDate;
    }

  
    public Location getTransferToLocation() {
        return transferToLocation;
    }

    public void setTransferToLocation(Location transferToLocation) {
        this.transferToLocation = transferToLocation;
    }
    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getTransferDescription() {
        return transferDescription;
    }

    public void setTransferDescription(String transferDescription) {
        this.transferDescription = transferDescription;
    }

    public Double getTransferQty() {
        return transferQty;
    }

    public void setTransferQty(Double transferQty) {
        this.transferQty = transferQty;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Location getTransferFromLocation() {
        return transferFromLocation;
    }

    public void setTransferFromLocation(Location transferFromLocation) {
        this.transferFromLocation = transferFromLocation;
    }

    public Items getTransferItem() {
        return transferItem;
    }

    public void setTransferItem(Items transferItem) {
        this.transferItem = transferItem;
    }

    public TransferTypes getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferTypes transferType) {
        this.transferType = transferType;
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
        if (!(object instanceof StockTransfer)) {
            return false;
        }
        StockTransfer other = (StockTransfer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getRefNumber();
    }
    
}
