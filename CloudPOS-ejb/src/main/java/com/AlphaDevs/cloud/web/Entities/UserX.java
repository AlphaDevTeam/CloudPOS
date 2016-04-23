
package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Enums.UserLevel;
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
public class UserX implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String userName;
    private String passWord;
   private String imgUrl;
    
    @Enumerated(EnumType.STRING)
    private UserLevel userLevel;
    
    @OneToOne
    private Logger Logger;
    
    @OneToOne
    private Company associatedCompany;
    
    public UserX() {
    }

    public UserX(String userName, String passWord, Logger Logger, Company associatedCompany) {
        this.userName = userName;
        this.passWord = passWord;
        this.Logger = Logger;
        this.associatedCompany = associatedCompany;
    }

    public UserX(String userName, String passWord, com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.userName = userName;
        this.passWord = passWord;
        this.Logger = Logger;
    }

    public UserX(String userName, String passWord, UserLevel userLevel, Logger Logger, Company associatedCompany) {
        this.userName = userName;
        this.passWord = passWord;
        this.userLevel = userLevel;
        this.Logger = Logger;
        this.associatedCompany = associatedCompany;
    }
    
    public UserX(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }
    
    public Company getAssociatedCompany() {
        return associatedCompany;
    }

    public void setAssociatedCompany(Company associatedCompany) {
        this.associatedCompany = associatedCompany;
    }

    public com.AlphaDevs.cloud.web.Entities.Logger getLogger() {
        return Logger;
    }

    public void setLogger(com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.Logger = Logger;
    }
    
    

   
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        if (!(object instanceof UserX)) {
            return false;
        }
        UserX other = (UserX) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getUserName();
    }

    /**
     * @return the imgUrl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl the imgUrl to set
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
}
