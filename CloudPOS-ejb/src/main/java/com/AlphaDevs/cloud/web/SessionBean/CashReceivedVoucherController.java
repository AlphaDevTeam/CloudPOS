
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.CashReceivedVoucher;
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

/*
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @since 2012/06/16
 * @see Alpha Development Team ( www.AlphaDevs.com )
 * 
 */

@Stateless
@LocalBean
public class CashReceivedVoucherController extends AbstractFacade<CashReceivedVoucher> {
    
    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public CashReceivedVoucherController(){
        super(CashReceivedVoucher.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<CashReceivedVoucher> findCashReceivedVoucher(Date relatedDate, Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CashReceivedVoucher> q = cb.createQuery(CashReceivedVoucher.class);
        Root<CashReceivedVoucher> c = q.from(CashReceivedVoucher.class);
        q.select(c);
        q.where(cb.equal(c.get("receiptDate"), relatedDate),cb.equal(c.get("receiptLocation"), location));
               
        List<CashReceivedVoucher> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<CashReceivedVoucher>();
        }else{
            return resultList;
        }
    }

  
}
