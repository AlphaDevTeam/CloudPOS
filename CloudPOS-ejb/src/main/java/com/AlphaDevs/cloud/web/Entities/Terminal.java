
package com.AlphaDevs.cloud.web.Entities;

import java.io.Serializable;
import javax.persistence.*;

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
public class Terminal implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String terminalName;
    private String terminalCode;
    private String terminalIP;
    
    @OneToOne
    private Logger Logger;

    public Terminal() {
    }

    public com.AlphaDevs.cloud.web.Entities.Logger getLogger() {
        return Logger;
    }

    public void setLogger(com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.Logger = Logger;
    }

    public Terminal(String terminalName, String terminalCode, String terminalIP, com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.terminalName = terminalName;
        this.terminalCode = terminalCode;
        this.terminalIP = terminalIP;
        this.Logger = Logger;
    }

    public String getTerminalIP() {
        return terminalIP;
    }

    public void setTerminalIP(String terminalIP) {
        this.terminalIP = terminalIP;
    }

    public Terminal(String terminalName, String terminalCode, String terminalIP) {
        this.terminalName = terminalName;
        this.terminalCode = terminalCode;
        this.terminalIP = terminalIP;
    }

    

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
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
        if (!(object instanceof Terminal)) {
            return false;
        }
        Terminal other = (Terminal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return terminalName;
    }
    
}
