/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Menu;
import com.AlphaDevs.cloud.web.SessionBean.MenuController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Kumuditha_2
 */
@ManagedBean
@SessionScoped
public class MenuHandler {
   
    
    
    @EJB
    private MenuController menuController;

    private Menu current;

    /**
     * Creates a new instance of MenuHandler
     */
    public MenuHandler() {

        if (current == null) {
            current = new Menu();
        }
    }
    
    public String createMenu(){   
        menuController.create(current);     
        current = new Menu();
        return "Home";
    }
    
    public List<Menu> getMenuList(){
        return menuController.findAll();
    } 
   

    /**
     * @return the current
     */
    public Menu getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(Menu current) {
        this.current = current;
    }

}
