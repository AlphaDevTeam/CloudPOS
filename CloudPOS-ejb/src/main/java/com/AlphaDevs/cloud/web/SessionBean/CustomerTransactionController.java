package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.CustomerTransaction;
import com.AlphaDevs.cloud.web.Entities.CustomerTransaction_;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.Supplier;
import com.AlphaDevs.cloud.web.Enums.BillStatus;
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
 * @version 1.0.0
 * @since 2012/06/16
 * @see Alpha Development Team ( www.AlphaDevs.com )
 *
 */
@Stateless
@LocalBean
public class CustomerTransactionController extends AbstractFacade<CustomerTransaction> {

    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public CustomerTransactionController() {
        super(CustomerTransaction.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<CustomerTransaction> findItemByUnit(Supplier supplier) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CustomerTransaction> q = cb.createQuery(CustomerTransaction.class);
        Root<CustomerTransaction> c = q.from(CustomerTransaction.class);
        q.select(c);
        q.where(cb.equal(c.get("supplier"), supplier));

        return getEntityManager().createQuery(q).getResultList();

    }

    public List<CustomerTransaction> findSuppliersInTimePeriod(Supplier supplier, Date fromDate, Date toDate) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CustomerTransaction> q = cb.createQuery(CustomerTransaction.class);
        Root<CustomerTransaction> c = q.from(CustomerTransaction.class);
        q.select(c);

        q.where(cb.equal(c.get(CustomerTransaction_.supplier), supplier), cb.between(c.get(CustomerTransaction_.date), fromDate, toDate));

        List<CustomerTransaction> resultList = getEntityManager().createQuery(q).getResultList();
        if (resultList == null || resultList.isEmpty()) {
            return new ArrayList<CustomerTransaction>();
        } else {
            return resultList;
        }

//        CriteriaBuilder builder = em.getCriteriaBuilder();
//    CriteriaQuery cQuery = builder.createQuery();
//    Root<AcsTemplateDateJPA> root = cQuery.from(AcsTemplateDateJPA.class);
//    ParameterExpression<Date> d = builder.parameter(Date.class);
//    cQuery.where(builder.between(d, root.<Date>get("inservicedate"), root.<Date>get("outservicedate"))); 
//    Query query = em.createQuery(cQuery.select(root.get("acstemplateid"))).setParameter(d, planDate, TemporalType.DATE);
//    List results =query.getResultList(); 
    }

    public CustomerTransaction addCustomerTransactionEntry(String description,BillStatus billStatus,Logger logger, Supplier supplier,Date date,double debit,double credit) {
        //Set Customer Transaction entry
        CustomerTransaction custTranEntry = new CustomerTransaction();
        custTranEntry.setDescription(description);
        custTranEntry.setSupplier(supplier);
        custTranEntry.setDate(date);
        custTranEntry.setBillStat(billStatus);
        custTranEntry.setDR(debit);
        custTranEntry.setCR(credit);
        custTranEntry.setLogger(logger);
        this.create(custTranEntry);
        return custTranEntry;
    }
}
