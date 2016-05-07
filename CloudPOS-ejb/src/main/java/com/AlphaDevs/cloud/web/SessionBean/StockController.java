package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Entities.Company;
import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.Stock;
import com.AlphaDevs.cloud.web.Entities.Stock_;
import com.AlphaDevs.cloud.web.Enums.BillStatus;
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

    public Stock findSpecific(Items items,Company relatedCompany) {
//        System.out.println(items.getItemName());
//        System.out.println(items.getItemLocation().getDescription());
        Stock st = new Stock();

        if (items != null && relatedCompany != null) {
            Location location = items.getItemLocation();
            List<Stock> resultList = getLocatedItemList(items, location,relatedCompany);
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
    
    public Stock findSpecific(Items items,Location location,Company relatedCompany) {

        Stock stock = new Stock();

        if (items != null && location != null && relatedCompany != null) {
            List<Stock> resultList = getLocatedItemList(items, location,relatedCompany);
            if (resultList != null && resultList.size() > 0 && resultList.get(0) != null && resultList.get(0).getStockLocation() != null) {
                stock = resultList.get(0);
            }
        }
        return stock;
    }

    public List<Stock> getLocatedItemList(Items item, Location location,Company relatedCompany) {

        List<Stock> locatedItemList = new ArrayList<>();
        if (item != null && location != null && relatedCompany != null) {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Stock> q = cb.createQuery(Stock.class);
            Root<Stock> c = q.from(Stock.class);
            q.select(c);
            q.where(cb.equal(c.get(Stock_.SockItem), item), cb.equal(c.get(Stock_.StockLocation), location),cb.equal(c.get(Stock_.relatedCompany), relatedCompany));
            locatedItemList = getEntityManager().createQuery(q).getResultList();
        }
        return locatedItemList;
    }

    public List<Stock> getLocationStock(Location location) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Stock> q = cb.createQuery(Stock.class);
        Root<Stock> c = q.from(Stock.class);
        q.select(c);
        q.where(cb.equal(c.get(Stock_.StockLocation), location));
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
    
    public List<Stock> getItemStockList(Location location,Items item,BillStatus billStatus) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Stock> q = cb.createQuery(Stock.class);
        Root<Stock> c = q.from(Stock.class);
        q.select(c);
        q.where(cb.equal(c.get(Stock_.StockLocation), location),cb.equal(c.get(Stock_.billStatus), billStatus),cb.equal(c.get(Stock_.SockItem), item));
        return getEntityManager().createQuery(q).getResultList();
    }
    
    public Stock getItemStock(Location location,Items item,BillStatus billStatus) {        
        List<Stock> itemStockList = getItemStockList(location, item,billStatus);
        if(itemStockList != null && !itemStockList.isEmpty() && itemStockList.get(0) != null){
           return itemStockList.get(0);
        }else{
            return new Stock();
        }
    }

}
