
package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.PurchaseReturnDetails;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
public class PurchaseReturnDetailsController extends AbstractFacade<PurchaseReturnDetails>
{
    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PurchaseReturnDetailsController()
    {
        super(PurchaseReturnDetails.class);
    }
    
    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }

    
}
