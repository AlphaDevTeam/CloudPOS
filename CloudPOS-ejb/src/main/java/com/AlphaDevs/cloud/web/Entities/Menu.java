/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Kumuditha_2
 */
@Entity
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String MenuTitle;
    private String url;
    
    
    @OneToMany(mappedBy = "parentMenu", cascade = CascadeType.PERSIST)
    private List<MenuItem> menuitem;

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
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getMenuTitle();
    }

    /**
     * @return the MenuTitle
     */
    public String getMenuTitle() {
        return MenuTitle;
    }

    /**
     * @param MenuTitle the MenuTitle to set
     */
    public void setMenuTitle(String MenuTitle) {
        this.MenuTitle = MenuTitle;
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

    /**
     * @return the menuitem
     */
    public List<MenuItem> getMenuitem() {
        return menuitem;
    }

    /**
     * @param menuitem the menuitem to set
     */
    public void setMenuitem(List<MenuItem> menuitem) {
        this.menuitem = menuitem;
    }
    
}
