package com.AlphaDevs.cloud.web.Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @since 2013/07/23
 * @see Alpha Development Team ( www.AlphaDevs.com )
 *
 */
@Entity
public class Company implements Serializable {

    @OneToOne(mappedBy = "associatedCompany")
    private UserX userX;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String companyName;
    private String companyDescription;

    @OneToMany(mappedBy = "relatedCompany")
    private List<Location> locations;

    public Company() {
    }
    
    public Company(String companyName, String companyDescription) {
        this.companyName = companyName;
        this.companyDescription = companyDescription;
    }

    public Company(UserX userX, String companyName, String companyDescription) {
        this.userX = userX;
        this.companyName = companyName;
        this.companyDescription = companyDescription;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public UserX getUserX() {
        return userX;
    }

    public void setUserX(UserX userX) {
        this.userX = userX;
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
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getCompanyName();
    }

}
