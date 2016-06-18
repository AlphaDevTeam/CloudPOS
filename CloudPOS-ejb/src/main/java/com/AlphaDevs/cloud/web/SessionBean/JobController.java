
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.Job;
import com.AlphaDevs.cloud.web.Entities.Job_;
import com.AlphaDevs.cloud.web.Entities.Location;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
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
public class JobController extends AbstractFacade<Job> {
    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public JobController(){
        super(Job.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Job> findLike(String query, Location location) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Job> q = cb.createQuery(Job.class);
        Root<Job> c = q.from(Job.class);
        q.select(c);
        //ParameterExpression<Product> p = cb.parameter(Product.class);
        Expression<String> path = c.get(Job_.jobNumber);
        q.where(cb.like(path, "%" + query + "%"), cb.equal(c.get(Job_.relatedLocation), location));
        return getEntityManager().createQuery(q).getResultList();

    }

}
