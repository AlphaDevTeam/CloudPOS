
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Items_;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.MeterReading;
import com.AlphaDevs.cloud.web.Entities.MeterReading_;
import com.AlphaDevs.cloud.web.Entities.Pump;
import com.AlphaDevs.cloud.web.Entities.Pump_;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;
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
public class MeterReadingController extends AbstractFacade<MeterReading> {
    
    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public MeterReadingController(){
        super(MeterReading.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public double findReadingByItem(Items item, Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MeterReading> q = cb.createQuery(MeterReading.class);
        Root<MeterReading> c = q.from(MeterReading.class);
        q.select(c);
        q.where(cb.equal(c.get("relatedItem"), item),cb.equal(c.get("relatedLocation"), location));
        
        List<MeterReading> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return 0;
        }else{
            if(resultList.get(0) != null){
                return resultList.get(0).getReading();
            }else{
                return 0;
            }
        }
    }
    
    public List<MeterReading> findReadingByDate(Date relatedDate, Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MeterReading> q = cb.createQuery(MeterReading.class);
        Root<MeterReading> c = q.from(MeterReading.class);
        q.select(c);
        q.where(cb.equal(c.get("relatedDate"), relatedDate),cb.equal(c.get("relatedLocation"), location));
               
        List<MeterReading> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<MeterReading>();
        }else{
            return resultList;
        }
    }
    
    public List<MeterReading> findReadingByDateGroup(Date relatedDate, Location location) {
        
        List<MeterReading> meterReadingGroup = new ArrayList<MeterReading>();
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tuple> q;
        q = cb.createTupleQuery();
        Root<MeterReading> c = q.from(MeterReading.class);
        Join<MeterReading, Pump> pump = c.join(MeterReading_.relatedPump);
        Join<Pump, Items > joinedItem = pump.join(Pump_.relatedItem);
        q.multiselect(cb.sum(c.<Double>get(MeterReading_.reading)), pump.get(Pump_.relatedItem),joinedItem.get(Items_.UnitPrice));
        //q.multiselect(cb.avg(c.<Number>get("salary")), e.get("address").get("city"));
        q.groupBy(pump.get(Pump_.relatedItem));
        //q.select(cb.count(MeterReading_.relatedPump));
        q.where(cb.equal(c.get("relatedDate"), relatedDate), cb.equal(c.get("relatedLocation"), location));
        //q.groupBy(pump.get("relatedItem"));

        List<Tuple> resultList = getEntityManager().createQuery(q).getResultList();
        if (resultList != null) {
            for (Tuple tuple : resultList) {
                if (tuple != null) {
                    MeterReading reading = new MeterReading();
                    reading.setRelatedPump(new Pump((Items)tuple.get(1)));
                    reading.setReading((Double)tuple.get(0));
                    
                    meterReadingGroup.add(reading);
                }
            }
        } 
        return meterReadingGroup;
    }
}
