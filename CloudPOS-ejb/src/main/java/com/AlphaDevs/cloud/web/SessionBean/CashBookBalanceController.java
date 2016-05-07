
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.CashBookBalance;
import com.AlphaDevs.cloud.web.Entities.CashBookBalance_;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Enums.BillStatus;
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
public class CashBookBalanceController extends AbstractFacade<CashBookBalance> 
{
    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public CashBookBalanceController()
    {
        super(CashBookBalance.class);
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }
    
    public CashBookBalance getCashBookBalanceObject(Location location , BillStatus billStat) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CashBookBalance> q = cb.createQuery(CashBookBalance.class);
        Root<CashBookBalance> c = q.from(CashBookBalance.class);
        q.select(c);
        //ParameterExpression<Product> p = cb.parameter(Product.class);
        q.where(cb.equal(c.get(CashBookBalance_.location), location)
                ,cb.equal(c.get(CashBookBalance_.billStatus), billStat));
        //q.where(cb.equal(c.get("supplier"), location)/);
        
        if(!getEntityManager().createQuery(q).getResultList().isEmpty())
        {
           return getEntityManager().createQuery(q).getSingleResult(); 
        }
        else
        {
            return null;
        }
        
        
    }

}
