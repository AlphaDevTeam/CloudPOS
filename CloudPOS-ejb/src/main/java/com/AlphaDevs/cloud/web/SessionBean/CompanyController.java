
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.Company;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

 /*
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @since 2013/07/23
 * @see Alpha Development Team ( www.AlphaDevs.com )
 * 
 */
@Stateless
@LocalBean
public class CompanyController extends AbstractFacade<Company>{
    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public CompanyController(){
        super(Company.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    

}
