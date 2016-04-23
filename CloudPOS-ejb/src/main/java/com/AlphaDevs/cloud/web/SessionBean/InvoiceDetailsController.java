
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.Invoice;
import com.AlphaDevs.cloud.web.Entities.InvoiceDetails;
import com.AlphaDevs.cloud.web.Entities.InvoiceDetails_;
import com.AlphaDevs.cloud.web.Entities.Invoice_;
import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Items_;
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
public class InvoiceDetailsController extends AbstractFacade<InvoiceDetails>
{
    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public InvoiceDetailsController()
    {
        super(InvoiceDetails.class);
    }
    
    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }
    
    public List<InvoiceDetails> findInvoiceDetailsByDateGroup(Date relatedDate, Location location) 
    {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQueryTuple = criteriaBuilder.createTupleQuery();
        Root<InvoiceDetails> rootInvoiceDetails = criteriaQueryTuple.from(InvoiceDetails.class);
        Join<InvoiceDetails, Items> joinedItems = rootInvoiceDetails.join(InvoiceDetails_.Item);
        Join<InvoiceDetails, Invoice> joinedInvoice = rootInvoiceDetails.join(InvoiceDetails_.invoice);
        
        
        Path<Float> quantity =  rootInvoiceDetails.get(InvoiceDetails_.ItemQty);
        Path<Double> price =  rootInvoiceDetails.get(InvoiceDetails_.Price);
        Path<String> item = joinedItems.get(Items_.ItemName);
        Path<String> itemCode = joinedItems.get(Items_.ItemCode);
        Path<Date> invDate = joinedInvoice.get(Invoice_.TrnDate);
        
        criteriaQueryTuple.groupBy(item);
        criteriaQueryTuple.multiselect(criteriaBuilder.sum(quantity).alias("sumQuantity"),criteriaBuilder.sum(price).alias("sumAmount"),item,itemCode);
        criteriaQueryTuple.where(criteriaBuilder.equal(joinedInvoice.get(Invoice_.Location) , location),
                            criteriaBuilder.equal(invDate , relatedDate));
        
        List<Tuple> resultListTuple = getEntityManager().createQuery(criteriaQueryTuple).getResultList();
        ArrayList<InvoiceDetails> resultList = new ArrayList<InvoiceDetails>();
        if(resultListTuple != null){
            for (Tuple resultTuple : resultListTuple) {
                if(resultTuple != null){
                    InvoiceDetails invDetails = new InvoiceDetails(new Items(resultTuple.get(itemCode) ,resultTuple.get(item) ), (Float)resultTuple.get("sumQuantity"),(Double)resultTuple.get("sumAmount"));
                    resultList.add(invDetails);
                }
            }
        }
        return resultList;
    }

      
}
