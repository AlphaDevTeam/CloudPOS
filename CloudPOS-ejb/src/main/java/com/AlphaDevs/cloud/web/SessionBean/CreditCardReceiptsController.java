
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.CreditCardReceipts;
import com.AlphaDevs.cloud.web.Entities.CreditCardReceipts_;
import com.AlphaDevs.cloud.web.Entities.CreditCardTeminals;
import com.AlphaDevs.cloud.web.Entities.CreditCardTeminals_;
import com.AlphaDevs.cloud.web.Entities.Location;
import java.lang.Double;
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

 /*
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @since 2012/06/16
 * @see Alpha Development Team ( www.AlphaDevs.com )
 * 
 */

@Stateless
@LocalBean
public class CreditCardReceiptsController extends AbstractFacade<CreditCardReceipts> {
    
    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public CreditCardReceiptsController(){
        super(CreditCardReceipts.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<CreditCardReceipts> findTerminalByLocation(CreditCardTeminals terminal, Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CreditCardReceipts> q = cb.createQuery(CreditCardReceipts.class);
        Root<CreditCardReceipts> c = q.from(CreditCardReceipts.class);
        q.select(c);
        q.where(cb.equal(c.get("relatedTerminal"), terminal),cb.equal(c.get("relatedLocation"), location));
               
        List<CreditCardReceipts> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<CreditCardReceipts>();
        }else{
            return resultList;
        }
    }
    
    public ArrayList<CreditCardReceipts> findCreditCardReceiptsByLocationGroup(Date relatedDate, Location location) 
    {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQueryTuple = criteriaBuilder.createTupleQuery();
        Root<CreditCardReceipts> rootCreditCardReceipts = criteriaQueryTuple.from(CreditCardReceipts.class);
        Join<CreditCardReceipts, CreditCardTeminals> creditCardTerminal = rootCreditCardReceipts.join(CreditCardReceipts_.relatedTerminal);
        
        Path<Double> amount =  rootCreditCardReceipts.get(CreditCardReceipts_.amount);
        Path<String> description = creditCardTerminal.get(CreditCardTeminals_.terminalDescription);
        Path<String> code = creditCardTerminal.get(CreditCardTeminals_.terminalCode);
        
        criteriaQueryTuple.groupBy(creditCardTerminal.get(CreditCardTeminals_.terminalDescription));
        criteriaQueryTuple.multiselect(criteriaBuilder.sum(amount).alias("sumAmount"),code,description);
        criteriaQueryTuple.where(criteriaBuilder.equal(rootCreditCardReceipts.get(CreditCardReceipts_.relatedLocation) , location),
                            criteriaBuilder.equal(rootCreditCardReceipts.get(CreditCardReceipts_.receiptDate) , relatedDate));
        
        List<Tuple> resultListTuple = getEntityManager().createQuery(criteriaQueryTuple).getResultList();
        ArrayList<CreditCardReceipts> resultList = new ArrayList<CreditCardReceipts>();
        if(resultListTuple != null){
            for (Tuple resultTuple : resultListTuple) {
                if(resultTuple != null){
                    CreditCardReceipts receipt = new CreditCardReceipts((Double)resultTuple.get("sumAmount"), new CreditCardTeminals(resultTuple.get(code), resultTuple.get(description), location));
                    resultList.add(receipt);
                }
            }
        }
        return resultList;
    }


    
}
