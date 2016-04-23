package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.ItemBincard;
import com.AlphaDevs.cloud.web.Entities.ItemBincard_;
import com.AlphaDevs.cloud.web.Entities.Items;
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
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@Stateless
@LocalBean
public class ItemBincardController extends AbstractFacade<ItemBincard> {

    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ItemBincardController() {
        super(ItemBincard.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<ItemBincard> findItemByUnit(Items item) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ItemBincard> q = cb.createQuery(ItemBincard.class);
        Root<ItemBincard> c = q.from(ItemBincard.class);
        q.select(c);
        q.where(cb.equal(c.get("item"), item));

        return getEntityManager().createQuery(q).getResultList();

    }

    public List<ItemBincard> getRelevantItemBinCardRecords(Date fromDate, Date toDate, Items item) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ItemBincard> q = cb.createQuery(ItemBincard.class);
        Root<ItemBincard> c = q.from(ItemBincard.class);
        q.select(c);

        q.where(cb.equal(c.get(ItemBincard_.item), item), cb.between(c.get(ItemBincard_.relatedDate), fromDate, toDate));

        List<ItemBincard> resultList = getEntityManager().createQuery(q).getResultList();
        if (resultList == null || resultList.isEmpty()) {
            return new ArrayList<ItemBincard>();
        } else {
            return resultList;
        }

    }

}
