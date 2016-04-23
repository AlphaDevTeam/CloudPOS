package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.Supplier;
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
public class SupplierController extends AbstractFacade<Supplier> {

    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public SupplierController() {
        super(Supplier.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    

}
