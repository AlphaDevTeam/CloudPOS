
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.BankBalance;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@Stateless
@LocalBean
public class BankBalanceController extends AbstractFacade<BankBalance>{
    
    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public BankBalanceController(){
        super(BankBalance.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return  em;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    

}
