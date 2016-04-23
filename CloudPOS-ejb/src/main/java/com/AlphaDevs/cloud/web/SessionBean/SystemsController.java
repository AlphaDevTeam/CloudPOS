
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.Company;
import com.AlphaDevs.cloud.web.Entities.Systems;
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
public class SystemsController extends AbstractFacade<Systems>
{
    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public SystemsController()
    {
        super(Systems.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Systems> getSystemsHeaders(Company company) {
        //System.out.println("Got Here With " + user.getPassWord() + " - " + user.getUserName());
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Systems> q = cb.createQuery(Systems.class);
        Root<Systems> c = q.from(Systems.class);
        q.select(c);
        //ParameterExpression<Product> p = cb.parameter(Product.class);
        q.where(cb.equal(c.get("relatedCompany"), company));

        /*CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
         CriteriaQuery cq = cb.createQuery(Design.class);
         Root<Design> root = cq.from(Design.class);
         cq.select(cq.from(Design.class)).where(cb.equal(root.get("product"), prod));
         */
        return getEntityManager().createQuery(q).getResultList();

    }
    
    
}
