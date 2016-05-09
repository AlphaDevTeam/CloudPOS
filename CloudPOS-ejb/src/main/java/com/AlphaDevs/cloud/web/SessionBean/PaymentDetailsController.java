package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.GRN;
import com.AlphaDevs.cloud.web.Entities.GRN_;
import com.AlphaDevs.cloud.web.Entities.Invoice;
import com.AlphaDevs.cloud.web.Entities.Invoice_;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.PaymentDetails;
import com.AlphaDevs.cloud.web.Entities.PaymentDetails_;
import com.AlphaDevs.cloud.web.Entities.Supplier;
import com.AlphaDevs.cloud.web.Enums.Document;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 *
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@Stateless
@LocalBean
public class PaymentDetailsController extends AbstractFacade<PaymentDetails> {

    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PaymentDetailsController() {
        super(PaymentDetails.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public List<GRN> findPending(Document document) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GRN> query = cb.createQuery(GRN.class);
        Root<GRN> grn = query.from(GRN.class);
        Join<GRN, PaymentDetails> payments = grn.join(GRN_.paymentDetails);
        Subquery<PaymentDetails> subquery = query.subquery(PaymentDetails.class);
        Root<PaymentDetails> subPayment = query.from(PaymentDetails.class);
        Expression<Double> creditAmount = subPayment.get(PaymentDetails_.creditAmount);
        subquery.where(cb.and(cb.equal(payments, subPayment), cb.equal(subPayment.get(PaymentDetails_.documentType), document), cb.greaterThan(creditAmount, Double.parseDouble("0"))));
        query.where(cb.not(cb.exists(subquery)));

        return getEntityManager().createQuery(query).getResultList();
    }

    public List<GRN> findPending(Document document, Location location) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GRN> query = cb.createQuery(GRN.class);
        Root<GRN> grn = query.from(GRN.class);
        Join<GRN, PaymentDetails> payments = grn.join(GRN_.paymentDetails);
        Expression<Double> creditAmount = payments.get(PaymentDetails_.creditAmount);
        query.select(grn);
        query.where(cb.equal(grn.get(GRN_.location), location), cb.equal(payments.get(PaymentDetails_.documentType), document), cb.greaterThan(creditAmount, Double.parseDouble("0")));

        return getEntityManager().createQuery(query).getResultList();
    }

    public List<GRN> findPendingGRNs(Location location, Date fromDate, Date toDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GRN> query = cb.createQuery(GRN.class);
        Root<GRN> grn = query.from(GRN.class);
        Join<GRN, PaymentDetails> payments = grn.join(GRN_.paymentDetails);
        Expression<Double> creditAmount = payments.get(PaymentDetails_.creditAmount);
        query.select(grn);
        query.where(cb.equal(grn.get(GRN_.location), location), cb.equal(payments.get(PaymentDetails_.documentType), Document.GOODS_RECEIVED_NOTE), cb.greaterThan(creditAmount, Double.parseDouble("0")), cb.between(grn.get(GRN_.grnDate), fromDate, toDate));
        return getEntityManager().createQuery(query).getResultList();
    }

    public List<Invoice> findPendingInvoices(Location location, Date fromDate, Date toDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Invoice> query = cb.createQuery(Invoice.class);
        Root<Invoice> invoice = query.from(Invoice.class);
        Join<Invoice, PaymentDetails> payments = invoice.join(Invoice_.paymentDetails);
        Expression<Double> creditAmount = payments.get(PaymentDetails_.creditAmount);
        query.select(invoice);
        query.where(cb.equal(invoice.get(Invoice_.Location), location), cb.equal(payments.get(PaymentDetails_.documentType), Document.INVOICE), cb.greaterThan(creditAmount, Double.parseDouble("0")), cb.between(invoice.get(Invoice_.TrnDate), fromDate, toDate));
        return getEntityManager().createQuery(query).getResultList();
    }
    
    public List<GRN> findPendingGRNs(Location location,Supplier supplier, Date fromDate, Date toDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GRN> query = cb.createQuery(GRN.class);
        Root<GRN> grn = query.from(GRN.class);
        Join<GRN, PaymentDetails> payments = grn.join(GRN_.paymentDetails);
        Expression<Double> creditAmount = payments.get(PaymentDetails_.creditAmount);
        query.select(grn);
        query.where(cb.equal(grn.get(GRN_.location), location),cb.equal(grn.get(GRN_.supplier), supplier), cb.equal(payments.get(PaymentDetails_.documentType), Document.GOODS_RECEIVED_NOTE), cb.greaterThan(creditAmount, Double.parseDouble("0")), cb.between(grn.get(GRN_.grnDate), fromDate, toDate));
        return getEntityManager().createQuery(query).getResultList();
    }

    public List<Invoice> findPendingInvoices(Location location,Supplier supplier, Date fromDate, Date toDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Invoice> query = cb.createQuery(Invoice.class);
        Root<Invoice> invoice = query.from(Invoice.class);
        Join<Invoice, PaymentDetails> payments = invoice.join(Invoice_.paymentDetails);
        Expression<Double> creditAmount = payments.get(PaymentDetails_.creditAmount);
        query.select(invoice);
        query.where(cb.equal(invoice.get(Invoice_.Location), location),cb.equal(invoice.get(Invoice_.Customer), supplier), cb.equal(payments.get(PaymentDetails_.documentType), Document.INVOICE), cb.greaterThan(creditAmount, Double.parseDouble("0")), cb.between(invoice.get(Invoice_.TrnDate), fromDate, toDate));
        return getEntityManager().createQuery(query).getResultList();
    }
    
    public List<GRN> findPendingGRNs(Location location,Supplier supplier) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GRN> query = cb.createQuery(GRN.class);
        Root<GRN> grn = query.from(GRN.class);
        Join<GRN, PaymentDetails> payments = grn.join(GRN_.paymentDetails);
        Expression<Double> creditAmount = payments.get(PaymentDetails_.creditAmount);
        query.select(grn);
        query.where(cb.equal(grn.get(GRN_.location), location),cb.equal(grn.get(GRN_.supplier), supplier), cb.equal(payments.get(PaymentDetails_.documentType), Document.GOODS_RECEIVED_NOTE), cb.greaterThan(creditAmount, Double.parseDouble("0")));
        return getEntityManager().createQuery(query).getResultList();
    }

    public List<Invoice> findPendingInvoices(Location location,Supplier supplier) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Invoice> query = cb.createQuery(Invoice.class);
        Root<Invoice> invoice = query.from(Invoice.class);
        Join<Invoice, PaymentDetails> payments = invoice.join(Invoice_.paymentDetails);
        Expression<Double> creditAmount = payments.get(PaymentDetails_.creditAmount);
        query.select(invoice);
        query.where(cb.equal(invoice.get(Invoice_.Location), location),cb.equal(invoice.get(Invoice_.Customer), supplier), cb.equal(payments.get(PaymentDetails_.documentType), Document.INVOICE), cb.greaterThan(creditAmount, Double.parseDouble("0")));
        return getEntityManager().createQuery(query).getResultList();
    }

}
