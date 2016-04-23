
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.Company;
import static com.AlphaDevs.cloud.web.Entities.GRN_.location;
import com.AlphaDevs.cloud.web.Entities.PropertyManager;
import com.AlphaDevs.cloud.web.Entities.PropertyManager_;
import com.AlphaDevs.cloud.web.Enums.Document;
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
public class PropertyManagerController extends AbstractFacade<PropertyManager>{

    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PropertyManagerController(){
        super(PropertyManager.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<PropertyManager> findProperty(Company company,Document document) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PropertyManager> q = cb.createQuery(PropertyManager.class);
        Root<PropertyManager> c = q.from(PropertyManager.class);
        q.select(c);
        q.where(cb.equal(c.get(PropertyManager_.company), company),cb.equal(c.get(PropertyManager_.document), document));
        return getEntityManager().createQuery(q).getResultList();
    }
    
}
