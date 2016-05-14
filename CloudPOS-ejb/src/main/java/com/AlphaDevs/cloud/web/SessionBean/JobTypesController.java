
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.Job;
import com.AlphaDevs.cloud.web.Entities.JobTypes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AlphaDevs
 */
@Stateless
@LocalBean
public class JobTypesController extends AbstractFacade<JobTypes>{
    
    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public JobTypesController() {
        super(JobTypes.class);
    }

    public JobTypesController(EntityManager em, Class<JobTypes> EntityClass) {
        super(EntityClass);
        this.em = em;
    }
   
    public void persist(Object object) {
        try {
            em.persist(object);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
}
