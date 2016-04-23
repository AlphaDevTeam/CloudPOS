/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.MenuItem;
import com.AlphaDevs.cloud.web.Entities.UserMenu;
import com.AlphaDevs.cloud.web.Entities.UserMenu_;
import com.AlphaDevs.cloud.web.Entities.UserX;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Kumuditha_2
 */
@Stateless
@LocalBean
public class UserMenuController extends AbstractFacade<UserMenu> {

    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public UserMenuController() {
        super(UserMenu.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);

    }

    public List<MenuItem> findSpecificMenu(UserX user) {

        List<MenuItem> menuList = new ArrayList<MenuItem>();
        if (user != null) {
            List<UserMenu> resultList = findSpecificUserMenu(user);
            if (resultList != null && resultList.size() > 0 && resultList.get(0) != null && resultList.get(0).getMenuItem() != null) {
                menuList = resultList.get(0).getMenuItem();
            }
        }

        return menuList;

    }

    public List<UserMenu> findSpecificUserMenu(UserX user) {

        List<UserMenu> userMenuList = new ArrayList<UserMenu>();
        if (user != null) {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<UserMenu> q = cb.createQuery(UserMenu.class);
            Root<UserMenu> c = q.from(UserMenu.class);
            q.select(c);
            q.where(cb.equal(c.get(UserMenu_.user), user));

            userMenuList = getEntityManager().createQuery(q).getResultList();
        }
        return userMenuList;

    }

}
