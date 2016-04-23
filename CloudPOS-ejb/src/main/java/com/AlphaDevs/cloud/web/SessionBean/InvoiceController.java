
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.Invoice;
import com.AlphaDevs.cloud.web.Entities.Invoice_;
import com.AlphaDevs.cloud.web.Entities.Location;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.eclipse.persistence.jpa.JpaQuery;

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
public class InvoiceController extends AbstractFacade<Invoice>
{
    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    
    public InvoiceController()
    {
        super(Invoice.class);
    }
    
    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<Invoice> findInvoicesByDate(Date relatedDate, Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Invoice> q = cb.createQuery(Invoice.class);
        Root<Invoice> c = q.from(Invoice.class);
        q.select(c);
        q.where(cb.equal(c.get("TrnDate"), relatedDate),cb.equal(c.get("Location"), location));
               
        List<Invoice> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<Invoice>();
        }else{
            return resultList;
        }
    }
    
    public List<Tuple> findInvoicesByDateAggrigate(Date relatedFromDate,Date relatedToDate ,Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tuple> q = cb.createTupleQuery();
        Root<Invoice> c = q.from(Invoice.class);
        q.multiselect(cb.sum(c.get(Invoice_.TotalAmount)),c.get(Invoice_.Location),c.get(Invoice_.TrnDate));
        q.where(cb.between(c.get(Invoice_.TrnDate), relatedFromDate,relatedToDate),
                cb.equal(c.get(Invoice_.Location), location));
        q.groupBy(c.get(Invoice_.TrnDate),c.get(Invoice_.Location));
        List<Tuple> resultList = getEntityManager().createQuery(q).getResultList();
        
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<Tuple>();
        }else{
            return resultList;
        }
    }
    
     public List<Invoice> findInvoicesByDate(Date relatedFromDate,Date relatedToDate ,Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Invoice> q = cb.createQuery(Invoice.class);
        Root<Invoice> c = q.from(Invoice.class);
        q.select(c);
        q.where(cb.between(c.get(Invoice_.TrnDate), cb.literal(relatedFromDate),cb.literal(relatedToDate)),cb.equal(c.get(Invoice_.Location), location));
               
        List<Invoice> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<Invoice>();
        }else{
            return resultList;
        }
    }
    
    
}
