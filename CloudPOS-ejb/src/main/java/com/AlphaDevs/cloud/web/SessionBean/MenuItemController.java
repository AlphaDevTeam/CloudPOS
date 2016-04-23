/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.MenuItem;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kumuditha_2
 */
@Stateless
@LocalBean
public class MenuItemController extends AbstractFacade<MenuItem>{
    
    
    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    EntityManager em;

    public MenuItemController() {
        super(MenuItem.class);
    }

 
    @Override
    protected EntityManager getEntityManager() {
    return em;
    
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
     public void persist(Object object) {
        
        em.persist(object);
    }
}
