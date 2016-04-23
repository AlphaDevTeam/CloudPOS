/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.MenuItem;
import com.AlphaDevs.cloud.web.Entities.UserMenu;
import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.MenuItemController;
import com.AlphaDevs.cloud.web.SessionBean.UserMenuController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author AlphaDevs
 */
@ManagedBean
@SessionScoped
public class UserMenuHandler {

    @EJB
    private UserMenuController userMenuController;

    @EJB
    private MenuItemController menuItemController;

    private UserMenu usermenu;

    public UserMenu getUsermenu() {
        return usermenu;
    }

    public void setUsermenu(UserMenu usermenu) {
        this.usermenu = usermenu;
    }

    DualListModel<MenuItem> menuListModel;

    public MenuItemController getMenuItemController() {
        return menuItemController;
    }

    public void setMenuItemController(MenuItemController menuItemController) {
        this.menuItemController = menuItemController;
    }

    public DualListModel<MenuItem> getMenuListModel() {
        return menuListModel;
    }

    public void setMenuListModel(DualListModel<MenuItem> menuListModel) {
        this.menuListModel = menuListModel;
    }

    /**
     * Creates a new instance of UserMenuHandler
     */
    public UserMenuHandler() {

        if (usermenu == null) {
            usermenu = new UserMenu();
        }

    }

    public void onTransfer(TransferEvent event) {
        if (event != null) {
            System.out.println("Transfere Event Called" + event);
            if (event.getItems() != null) {
                for (Object item : event.getItems()) {
                    System.out.println("Transfered" + item);
                }
            }

        }

    }

    public String checkLoggedUser() {

        UserX loggeduser;
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        if (sessionMap != null && sessionMap.containsKey(AlphaConstant.SESSION_USER) && sessionMap.get(AlphaConstant.SESSION_USER) != null) {
            loggeduser = (UserX) sessionMap.get(AlphaConstant.SESSION_USER);

            if (loggeduser.getUserLevel().toString() != null && "ADMIN".equals(loggeduser.getUserLevel().toString())) {
                System.out.println(loggeduser.getUserLevel().toString());
                return "ToUserMenu";
            } else {
                return "cancel";
            }
        }
        return "cancel";
    }

    public String saveUserpages() {

        List<MenuItem> templist = getMenuListModel().getTarget();

        List<UserMenu> findSpecificUserMenu = userMenuController.findSpecificUserMenu(usermenu.getUser());
        if (findSpecificUserMenu != null && findSpecificUserMenu.size() > 0 && findSpecificUserMenu.get(0) != null) {
            usermenu = findSpecificUserMenu.get(0);
            usermenu.setMenuItem(templist);
            userMenuController.edit(usermenu);
           
        } else {
            usermenu.setMenuItem(templist);
            userMenuController.persist(usermenu);
        }
        return "Home";
    }

    @PostConstruct
    public void init() {
        //Themes
        List<MenuItem> themesSource = getMenuItemController().findAll();
        List<MenuItem> themesTarget = new ArrayList<MenuItem>();

        setMenuListModel(new DualListModel<MenuItem>(themesSource, themesTarget));
    }

    public void loadSpecificMenuItems() {

        getMenuListModel().setSource(getMenuItemController().findAll());

        if (getUsermenu().getUser() != null) {
            List<MenuItem> specificMenuItems = userMenuController.findSpecificMenu(getUsermenu().getUser());
            getMenuListModel().setTarget(specificMenuItems);

            if (getMenuListModel() != null) {
                for (MenuItem specificMenuItem : specificMenuItems) {
                    getMenuListModel().getSource().remove(specificMenuItem);
                }
            }
        }
    }

}
