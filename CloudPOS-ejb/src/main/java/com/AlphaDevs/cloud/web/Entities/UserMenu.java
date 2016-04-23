/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Kumuditha_2
 */
@Entity
public class UserMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    
    @OneToMany
    private List<MenuItem> menuItem; 
    
    @ManyToOne
    private UserX user;
    
    

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
        if (!(object instanceof UserMenu)) {
            return false;
        }
        UserMenu other = (UserMenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.cloud.web.Entities.UserMenu[ id=" + id + " ]";
    }

    /**
     * @return the menuItem
     */
    public List<MenuItem> getMenuItem() {
        return menuItem;
    }

    /**
     * @param menuItem the menuItem to set
     */
    public void setMenuItem(List<MenuItem> menuItem) {
        this.menuItem = menuItem;
    }

    /**
     * @return the user
     */
    public UserX getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserX user) {
        this.user = user;
    }

   
    
}
