package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.Logger_;
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
 *
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@Stateless
@LocalBean
public class LoggerController extends AbstractFacade<Logger> {

    public LoggerController() {
        super(Logger.class);
    }

    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Logger> getSearchedLoggerDetails(Logger logger, Date fromDate, Date toDate) {

//        System.out.println(logger);
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Logger> q = cb.createQuery(Logger.class);
        Root<Logger> c = q.from(Logger.class);
        q.select(c);

        if (logger != null && logger.getDescription() != null && !logger.getDescription().isEmpty() ) {
            q.where(cb.like(c.get(Logger_.Description),"%"+logger.getDescription()+"%"));
        
        
        }if(logger.getTerminal() != null){
            System.out.println("TERMINAL CHECK");
            q.where(cb.and(cb.equal(c.get(Logger_.terminal), logger.getTerminal()), cb.between(c.get(Logger_.TrnTimeStamp), fromDate, toDate)));
            
        }if(logger.getUser() != null){
              q.where(cb.equal(c.get(Logger_.user), logger.getUser()));
        }if(logger.getTrnNumber() != null ){
             q.where(cb.like(c.get(Logger_.TrnNumber),"%"+logger.getTrnNumber()+"%"));
        
        }
//        else if(logger !=null && logger.getDescription() != null && !logger.getDescription().isEmpty()){
//        
//        }

        List<Logger> resultList = getEntityManager().createQuery(q).getResultList();
        if (resultList == null || resultList.isEmpty()) {
            return new ArrayList<Logger>();
        } else {
            return resultList;
        }

    }

}
