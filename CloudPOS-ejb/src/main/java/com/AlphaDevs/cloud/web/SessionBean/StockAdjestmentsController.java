
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Items_;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.StockAdjestments;
import com.AlphaDevs.cloud.web.Entities.StockAdjestments_;
import com.AlphaDevs.cloud.web.Enums.AdjestmentTypes;
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
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */


@Stateless
@LocalBean
public class StockAdjestmentsController extends AbstractFacade<StockAdjestments> {
    
    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public StockAdjestmentsController(){
        super(StockAdjestments.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<StockAdjestments> findAdjestmentsByLocation(Date relatedDate , Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<StockAdjestments> q = cb.createQuery(StockAdjestments.class);
        Root<StockAdjestments> c = q.from(StockAdjestments.class);
        q.select(c);
        q.where(cb.equal(c.get("AdjestmentLocation"), location),cb.equal(c.get("adjestmentDate"), relatedDate));
               
        List<StockAdjestments> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<StockAdjestments>();
        }else{
            return resultList;
        }
    }
    
    public List<StockAdjestments> findAdjestmentsByLocationGroup(Date relatedDate , Location location) 
    {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQueryTuple = criteriaBuilder.createTupleQuery();
        Root<StockAdjestments> rootStockAdjestments = criteriaQueryTuple.from(StockAdjestments.class);
        Join<StockAdjestments, Items> joinedItems = rootStockAdjestments.join(StockAdjestments_.AdjestmentItems);
        
        Path<Double> quantity =  rootStockAdjestments.get(StockAdjestments_.AdjestmentQty);
        Path<AdjestmentTypes> type =  rootStockAdjestments.get(StockAdjestments_.adjestmentType);
        Path<String> description = joinedItems.get(Items_.ItemDescription);
        Path<Double> unitPrice = joinedItems.get(Items_.UnitPrice);
        
        criteriaQueryTuple.groupBy(description);
        criteriaQueryTuple.multiselect(criteriaBuilder.sum(quantity).alias("sumQuantity"),description,type,unitPrice);
        criteriaQueryTuple.where(criteriaBuilder.equal(rootStockAdjestments.get(StockAdjestments_.AdjestmentLocation) , location),
                            criteriaBuilder.equal(rootStockAdjestments.get(StockAdjestments_.adjestmentDate) , relatedDate));
        
        List<Tuple> resultListTuple = getEntityManager().createQuery(criteriaQueryTuple).getResultList();
        ArrayList<StockAdjestments> resultList = new ArrayList<StockAdjestments>();
        if(resultListTuple != null){
            for (Tuple resultTuple : resultListTuple) {
                if(resultTuple != null){
                    StockAdjestments receipt = new StockAdjestments((Double)resultTuple.get("sumQuantity"), new Items(resultTuple.get(description),resultTuple.get(unitPrice)),resultTuple.get(type));
                    resultList.add(receipt);
                }
            }
        }
        return resultList;
    }

  

}
