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

/**
 *
 * @author Kumuditha_2
 */
@Entity
public class MenuItem implements Serializable {
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String MenuItemTitle;
    private String url;
    @ManyToOne
    private Menu parentMenu;
    
    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }
    
    

    public MenuItem() {
    }

    public MenuItem(Long id, String MenuItemTitle, String url) {
        this.id = id;
        this.MenuItemTitle = MenuItemTitle;
        this.url = url;
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
        if (!(object instanceof MenuItem)) {
            return false;
        }
        MenuItem other = (MenuItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return MenuItemTitle;
    }

    /**
     * @return the MenuItemTitle
     */
    public String getMenuItemTitle() {
        return MenuItemTitle;
    }

    /**
     * @param MenuItemTitle the MenuItemTitle to set
     */
    public void setMenuItemTitle(String MenuItemTitle) {
        this.MenuItemTitle = MenuItemTitle;
    }

  
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
}
