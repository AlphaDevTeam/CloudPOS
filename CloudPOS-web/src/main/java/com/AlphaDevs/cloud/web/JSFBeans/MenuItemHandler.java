/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Menu;
import com.AlphaDevs.cloud.web.Entities.MenuItem;
import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.MenuController;
import com.AlphaDevs.cloud.web.SessionBean.MenuItemController;
import com.AlphaDevs.cloud.web.SessionBean.UserMenuController;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Kumuditha_2
 */
@ManagedBean
@SessionScoped
public class MenuItemHandler {

    @EJB
    private UserMenuController userMenuController;

    @EJB
    private MenuController menuController;

    @EJB
    private MenuItemController menuItemController;

    MenuModel menumodelsingle;

    private MenuItem current;
    private MenuItem currentSelected;
    private Menu currentMenu;
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public UserMenuController getUserMenuController() {
        return userMenuController;
    }

    public void setUserMenuController(UserMenuController userMenuController) {
        this.userMenuController = userMenuController;
    }

    public MenuItem getCurrentSelected() {
        return currentSelected;
    }

    public void setCurrentSelected(MenuItem currentSelected) {
        this.currentSelected = currentSelected;
    }

    
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public MenuItemController getMenuItemController() {
        return menuItemController;
    }

    public void setMenuItemController(MenuItemController menuItemController) {
        this.menuItemController = menuItemController;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public MenuController getMenuController() {
        return menuController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public List<Menu> getMenuList() {
        return getMenuController().findAll();
    }

    public void redirrectURL() {
        MessageHelper.addSuccessMessage("Selected " + getCurrent().getUrl());
    }

    /**
     * Creates a new instance of MenuItemHandler
     */
    public MenuItemHandler() {

        if (current == null) {
            current = new MenuItem();
        }
    }

    public void createMenuItem() {

        getCurrentMenu().setMenuitem(getMenuItems());
        //getMenuItemController().create(getCurrent());
        getMenuController().edit(getCurrentMenu());
    }

    public void addItem() {

        System.out.println("AddItem");
        MenuItem genaratedMenuItem = new MenuItem();
        genaratedMenuItem.setMenuItemTitle(getCurrent().getMenuItemTitle());
        genaratedMenuItem.setUrl(getCurrent().getUrl());
        genaratedMenuItem.setParentMenu(getCurrentMenu());
        getMenuItems().add(genaratedMenuItem);

        setCurrent(new MenuItem());
     //   setCurrentMenu(new Menu());

        System.out.println(getMenuItems());
    }

    /**
     * @return the current
     */
    public MenuItem getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(MenuItem current) {
        this.current = current;
    }

    public MenuModel getMenumodelsingle() {
        return menumodelsingle;
    }

    public void setMenumodelsingle(MenuModel menumodelsingle) {
        this.menumodelsingle = menumodelsingle;
    }

    @PostConstruct
    public void testMenu() {

        UserX loggedUser = null;
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        if (sessionMap != null && sessionMap.containsKey(AlphaConstant.SESSION_USER) && sessionMap.get(AlphaConstant.SESSION_USER) != null) {
            loggedUser = (UserX) sessionMap.get(AlphaConstant.SESSION_USER);
        }

        menumodelsingle = new DefaultMenuModel();
        DefaultMenuItem menuitem = new DefaultMenuItem();
        this.menumodelsingle.addElement(menuitem);

        Set<Menu> testTest = new HashSet<Menu>();

        Set<MenuItem> menuItemSet = new HashSet<MenuItem>();

        for (MenuItem menuItem : userMenuController.findSpecificMenu(loggedUser)) {
            testTest.add(menuItem.getParentMenu());
            menuItemSet.add(menuItem);
        }

        System.out.println("List : " + testTest.toString());
        System.out.println("Itenm List : " + menuItemSet.toString());

        for (Menu testTest1 : testTest) {
            DefaultSubMenu submenu = new DefaultSubMenu();
            submenu.setLabel(testTest1.getMenuTitle());
            submenu.setIcon("fa fa-yelp");
            this.menumodelsingle.addElement(submenu);

            for (MenuItem menuItem : testTest1.getMenuitem()) {
                if (menuItemSet.contains(menuItem)) {
                    DefaultMenuItem item = new DefaultMenuItem();
                    item.setValue(menuItem.getMenuItemTitle());
                    item.setIcon("fa fa-yelp");
                    item.setUrl(menuItem.getUrl());
                    submenu.addElement(item);
                }
            }
        }

    }

    

    public List<MenuItem> getList() {
        return menuItemController.findAll();

    }

    public String deleteRow(MenuItem menuitem) {
        getMenuItems().remove(menuitem);
        return null;
    }

    public void onRowEdit(RowEditEvent event) {
         
        MessageHelper.addSuccessMessage(((MenuItem) event.getObject()).getParentMenu().getMenuTitle() + " Updated!");
        for(MenuItem mi : getMenuItems())
        {
            if(mi.getParentMenu().getId() == ((MenuItem)event.getObject()).getParentMenu().getId() )
            {
                
                mi.setMenuItemTitle(((MenuItem)event.getObject()).getMenuItemTitle());
                mi.setParentMenu(((MenuItem)event.getObject()).getParentMenu());
               mi.setUrl(((MenuItem)event.getObject()).getUrl());
            }
        }
        
        getCurrent().setParentMenu(currentMenu);
       
    }
     
    public void onRowCancel(RowEditEvent event) {
          MessageHelper.addSuccessMessage(((MenuItem) event.getObject()).getParentMenu().getMenuTitle() + "Not Updated!");
        
    }
}
