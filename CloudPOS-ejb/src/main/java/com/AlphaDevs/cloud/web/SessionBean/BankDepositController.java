
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.BankDeposit;
import com.AlphaDevs.cloud.web.Entities.Location;
import java.util.ArrayList;
import java.util.Date;
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
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@Stateless
@LocalBean
public class BankDepositController extends AbstractFacade<BankDeposit>{
    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public BankDepositController(){
        super(BankDeposit.class);
    }

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<BankDeposit> findBankDeposits(Date relatedDate, Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BankDeposit> q = cb.createQuery(BankDeposit.class);
        Root<BankDeposit> c = q.from(BankDeposit.class);
        q.select(c);
        q.where(cb.equal(c.get("depositDate"), relatedDate),cb.equal(c.get("relatedLocation"), location));
               
        List<BankDeposit> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<BankDeposit>();
        }else{
            return resultList;
        }
    }

}
