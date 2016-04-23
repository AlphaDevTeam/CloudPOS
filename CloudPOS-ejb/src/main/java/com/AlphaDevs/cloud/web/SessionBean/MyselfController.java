/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.MYSELF;
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
 * @author Kumuditha_2
 */
@Stateless
@LocalBean
public class MyselfController extends AbstractFacade<MYSELF> {

    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public MyselfController() {
        super(MYSELF.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void persist(Object object) {
        em.persist(object);
    }

    public List<MYSELF> getMyselfByName(MYSELF msf) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MYSELF> q = cb.createQuery(MYSELF.class);
        Root<MYSELF> c = q.from(MYSELF.class);
        q.select(c);

        q.where(cb.equal(c.get("name"), msf.getName()));

        return getEntityManager().createQuery(q).getResultList();
    }

}
