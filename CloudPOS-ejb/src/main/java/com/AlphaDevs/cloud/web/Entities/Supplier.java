
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
public class Supplier implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private CustomerCategory custcategory;
    
    private String firstName;
    private String lastName;
    private String custCode;
    private String custNIC;
    
    private String Street;
    private String City;
    private String AddressState;
    private String ZipCode;
    
    private String Mobile;
    private String Phone;
    private String Fax;
    private String EmailAddress;
    private String URL;
    
    @OneToOne(cascade= CascadeType.ALL)
    private Extras extra;
    
    private float creditLimit;
    private boolean inactive;
    
    @OneToOne
    private Logger Logger;
    
    @OneToOne(mappedBy = "supplier")
    private CustomerBalance customerBalance;
    
    private String vatNumber;

    public Supplier() {
    }

    public Supplier(CustomerCategory custcategory, String firstName, String lastName, String custCode, String custNIC, String Street, String City, String AddressState, String ZipCode, String Mobile, String Phone, String Fax, String EmailAddress, String URL, Extras extra, float creditLimit, boolean inactive, Logger Logger, CustomerBalance customerBalance, String vatNumber) {
        this.custcategory = custcategory;
        this.firstName = firstName;
        this.lastName = lastName;
        this.custCode = custCode;
        this.custNIC = custNIC;
        this.Street = Street;
        this.City = City;
        this.AddressState = AddressState;
        this.ZipCode = ZipCode;
        this.Mobile = Mobile;
        this.Phone = Phone;
        this.Fax = Fax;
        this.EmailAddress = EmailAddress;
        this.URL = URL;
        this.extra = extra;
        this.creditLimit = creditLimit;
        this.inactive = inactive;
        this.Logger = Logger;
        this.customerBalance = customerBalance;
        this.vatNumber = vatNumber;
    }
    
    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public CustomerBalance getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(CustomerBalance customerBalance) {
        this.customerBalance = customerBalance;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public com.AlphaDevs.cloud.web.Entities.Logger getLogger() {
        return Logger;
    }

    public void setLogger(com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.Logger = Logger;
    }
    
    

    public String getAddressState() {
        return AddressState;
    }

    public void setAddressState(String AddressState) {
        this.AddressState = AddressState;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String EmailAddress) {
        this.EmailAddress = EmailAddress;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String ZipCode) {
        this.ZipCode = ZipCode;
    }

    public float getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(float creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustNIC() {
        return custNIC;
    }

    public void setCustNIC(String custNIC) {
        this.custNIC = custNIC;
    }

    public CustomerCategory getCustcategory() {
        return custcategory;
    }

    public void setCustcategory(CustomerCategory custcategory) {
        this.custcategory = custcategory;
    }

    public Extras getExtra() {
        return extra;
    }

    public void setExtra(Extras extra) {
        this.extra = extra;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() 
    {
        return firstName;
    }
    
}
