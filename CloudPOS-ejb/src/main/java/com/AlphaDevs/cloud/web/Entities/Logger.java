

package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@Entity
public class Logger implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String Description;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date TrnTimeStamp;
    
    @OneToOne
    private UserX user;
    @OneToOne
    private Terminal terminal;
    
    @Enumerated(EnumType.STRING)
    private TransactionTypes trnType;
    
    @OneToOne
    private Location relevantLocation;
    
    private String TrnNumber;

    public Logger() {
    }

    public Logger(String Description, Date TrnTimeStamp, UserX user, Terminal terminal, TransactionTypes trnType, String TrnNumber, Location relLocation ) {
        this.Description = Description;
        this.TrnTimeStamp = TrnTimeStamp;
        this.user = user;
        this.terminal = terminal;
        this.trnType = trnType;
        this.TrnNumber = TrnNumber;
        this.relevantLocation = relLocation;
    }
    
     public Logger(String Description, Date TrnTimeStamp,  TransactionTypes trnType, String TrnNumber ) {
        this.Description = Description;
        this.TrnTimeStamp = TrnTimeStamp;
        this.trnType = trnType;
        this.TrnNumber = TrnNumber;
    }
    

    public Logger(String Description, Date TrnTimeStamp, Terminal terminal, TransactionTypes trnType, String TrnNumber) {
        this.Description = Description;
        this.TrnTimeStamp = TrnTimeStamp;
        this.terminal = terminal;
        this.trnType = trnType;
        this.TrnNumber = TrnNumber;
    }

    public String getTrnNumber() {
        return TrnNumber;
    }

    public void setTrnNumber(String TrnNumber) {
        this.TrnNumber = TrnNumber;
    }

    

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getTrnTimeStamp() {
        return TrnTimeStamp;
    }

    public void setTrnTimeStamp(Date TrnTimeStamp) {
        this.TrnTimeStamp = TrnTimeStamp;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public TransactionTypes getTrnType() {
        return trnType;
    }

    public void setTrnType(TransactionTypes trnType) {
        this.trnType = trnType;
    }

    public UserX getUser() {
        return user;
    }

    public void setUser(UserX user) {
        this.user = user;
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
        if (!(object instanceof Logger)) {
            return false;
        }
        Logger other = (Logger) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    } 

    @Override
    public String toString() {
        return user.getUserName() + " - " + terminal.getTerminalName() + " @ " + TrnTimeStamp ;
    }

    /**
     * @return the relevantLocation
     */
    public Location getRelevantLocation() {
        return relevantLocation;
    }

    /**
     * @param relevantLocation the relevantLocation to set
     */
    public void setRelevantLocation(Location relevantLocation) {
        this.relevantLocation = relevantLocation;
    }

}
