package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.Stock;
import com.AlphaDevs.cloud.web.Entities.Stock_;
import java.util.ArrayList;
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
public class StockController extends AbstractFacade<Stock> {

    @PersistenceContext(unitName = "com.AlphaDevs.cloud.web_CloudPOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public StockController() {
        super(Stock.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Stock findSpecific(Items items) {
//        System.out.println(items.getItemName());
//        System.out.println(items.getItemLocation().getDescription());
        Stock st = new Stock();

        if (items != null) {
            Location location = items.getItemLocation();
            List<Stock> resultList = getLocatedItemList(items, location);
            if (resultList != null && resultList.size() > 0 && resultList.get(0) != null && resultList.get(0).getStockLocation() != null) {

                st = resultList.get(0);
            }

        }
        return st;
        //ParameterExpression<Product> p = cb.parameter(Product.class);
        /*CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
         CriteriaQuery cq = cb.createQuery(Design.class);
         Root<Design> root = cq.from(Design.class);
         cq.select(cq.from(Design.class)).where(cb.equal(root.get("product"), prod));
         */
    }

    public List<Stock> getLocatedItemList(Items item, Location location) {

        List<Stock> locatedItemList = new ArrayList<Stock>();
        if (item != null && location != null) {

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Stock> q = cb.createQuery(Stock.class);
            Root<Stock> c = q.from(Stock.class);
            q.select(c);
            q.where(cb.equal(c.get("SockItem"), item), cb.equal(c.get("StockLocation"), location),cb.equal(c.get("relatedCompany"), location.getRelatedCompany()));
            locatedItemList = getEntityManager().createQuery(q).getResultList();
        }
        return locatedItemList;
    }

    public List<Stock> getLocationStock(Location location) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Stock> q = cb.createQuery(Stock.class);
        Root<Stock> c = q.from(Stock.class);
        q.select(c);
        q.where(cb.equal(c.get("StockLocation"), location));
        return getEntityManager().createQuery(q).getResultList();
    }
    
    public List<Stock> getItemStockList(Location location,Items item) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Stock> q = cb.createQuery(Stock.class);
        Root<Stock> c = q.from(Stock.class);
        q.select(c);
        q.where(cb.equal(c.get(Stock_.StockLocation), location),cb.equal(c.get(Stock_.SockItem), item));
        return getEntityManager().createQuery(q).getResultList();
    }
    
    public Stock getItemStock(Location location,Items item) {        
        List<Stock> itemStockList = getItemStockList(location, item);
        if(itemStockList != null && !itemStockList.isEmpty() && itemStockList.get(0) != null){
           return itemStockList.get(0);
        }else{
            return new Stock();
        }
    }

}
