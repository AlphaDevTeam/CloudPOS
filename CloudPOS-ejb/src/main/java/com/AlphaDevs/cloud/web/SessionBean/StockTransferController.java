package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Items_;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.StockTransfer;
import com.AlphaDevs.cloud.web.Entities.StockTransfer_;
import com.AlphaDevs.cloud.web.Enums.TransferTypes;
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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
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
public class StockTransferController extends AbstractFacade<StockTransfer> {

    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public StockTransferController() {
        super(StockTransfer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public List<StockTransfer> findTransferByFromLocation(Date relatedDate, Location location) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<StockTransfer> q = cb.createQuery(StockTransfer.class);
        Root<StockTransfer> c = q.from(StockTransfer.class);
        q.select(c);
        q.where(cb.equal(c.get(StockTransfer_.transferFromLocation), location), cb.equal(c.get(StockTransfer_.transferDate), relatedDate));

        List<StockTransfer> resultList = getEntityManager().createQuery(q).getResultList();
        if (resultList == null || resultList.isEmpty()) {
            return new ArrayList<>();
        } else {
            return resultList;
        }
    }

    public List<StockTransfer> findTransferByToLocation(Date relatedDate, Location location) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<StockTransfer> q = cb.createQuery(StockTransfer.class);
        Root<StockTransfer> c = q.from(StockTransfer.class);
        q.select(c);
        q.where(cb.equal(c.get(StockTransfer_.transferToLocation), location), cb.equal(c.get(StockTransfer_.transferDate), relatedDate));

        List<StockTransfer> resultList = getEntityManager().createQuery(q).getResultList();
        if (resultList == null || resultList.isEmpty()) {
            return new ArrayList<>();
        } else {
            return resultList;
        }
    }

    public List<StockTransfer> findTransfersByDateRange(Date fromDate, Date toDate, Location fromLocation, Location toLocation) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<StockTransfer> q = cb.createQuery(StockTransfer.class);
        Root<StockTransfer> c = q.from(StockTransfer.class);
        q.select(c);
        q.where(cb.equal(c.get(StockTransfer_.transferFromLocation), fromLocation), cb.equal(c.get(StockTransfer_.transferToLocation), toLocation), cb.between(c.get(StockTransfer_.transferDate), fromDate, toDate));

        List<StockTransfer> resultList = getEntityManager().createQuery(q).getResultList();
        if (resultList == null || resultList.isEmpty()) {
            return new ArrayList<>();
        } else {
            return resultList;
        }
    }

    public List<StockTransfer> findAdjestmentsByLocationGroup(Date relatedDate, Location location) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQueryTuple = criteriaBuilder.createTupleQuery();
        Root<StockTransfer> rootStockAdjestments = criteriaQueryTuple.from(StockTransfer.class);
        Join<StockTransfer, Items> joinedItems = rootStockAdjestments.join(StockTransfer_.transferItem);

        Path<Double> quantity = rootStockAdjestments.get(StockTransfer_.transferQty);
        Path<TransferTypes> type = rootStockAdjestments.get(StockTransfer_.transferType);
        Path<String> description = joinedItems.get(Items_.ItemDescription);
        Path<Double> unitPrice = joinedItems.get(Items_.UnitPrice);

        criteriaQueryTuple.groupBy(description);
        criteriaQueryTuple.multiselect(criteriaBuilder.sum(quantity).alias("sumQuantity"), description, type, unitPrice);
        criteriaQueryTuple.where(criteriaBuilder.equal(rootStockAdjestments.get(StockTransfer_.transferFromLocation), location),
                criteriaBuilder.equal(rootStockAdjestments.get(StockTransfer_.transferDate), relatedDate));

        List<Tuple> resultListTuple = getEntityManager().createQuery(criteriaQueryTuple).getResultList();
        ArrayList<StockTransfer> resultList = new ArrayList<>();
        if (resultListTuple != null) {
            for (Tuple resultTuple : resultListTuple) {
                if (resultTuple != null) {
                    StockTransfer transfer = new StockTransfer((Double) resultTuple.get("sumQuantity"), new Items(resultTuple.get(description), resultTuple.get(unitPrice)), resultTuple.get(type));
                    resultList.add(transfer);
                }
            }
        }
        return resultList;
    }
}
