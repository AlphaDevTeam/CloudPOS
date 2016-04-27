package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.Design;
import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Items_;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.Product;
import com.AlphaDevs.cloud.web.Entities.Units;
import java.util.ArrayList;
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
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@Stateless
@LocalBean
public class ItemsController extends AbstractFacade<Items> {

    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ItemsController() {
        super(Items.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Items> findLike(String query) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Items> q = cb.createQuery(Items.class);
        Root<Items> c = q.from(Items.class);
        q.select(c);
        //ParameterExpression<Product> p = cb.parameter(Product.class);
        Expression<String> path = c.get("ItemDescription");
        q.where(cb.like(path, "%" + query + "%"));

        /*CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
         CriteriaQuery cq = cb.createQuery(Design.class);
         Root<Design> root = cq.from(Design.class);
         cq.select(cq.from(Design.class)).where(cb.equal(root.get("product"), prod));
         */
        return getEntityManager().createQuery(q).getResultList();

    }

    public List<Items> findItemByUnit(Units units, Location itemLocation) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Items> q = cb.createQuery(Items.class);
        Root<Items> c = q.from(Items.class);
        q.select(c);
        q.where(cb.equal(c.get(Items_.unitOfMeasurement), units), cb.equal(c.get(Items_.ItemLocation), itemLocation));
        if (getEntityManager().createQuery(q).getResultList() == null) {
            return new ArrayList<>();
        } else {
            return getEntityManager().createQuery(q).getResultList();
        }
    }

    public List<Items> findItemByDesign(Long design_ID) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Items> q = cb.createQuery(Items.class);
        Root<Items> c = q.from(Items.class);
        q.select(c);
        q.where(cb.equal(c.get("ItemDesign"), design_ID));

        if (getEntityManager().createQuery(q).getResultList() == null) {
            return new ArrayList<Items>();
        } else {
            return getEntityManager().createQuery(q).getResultList();
        }
    }

}
