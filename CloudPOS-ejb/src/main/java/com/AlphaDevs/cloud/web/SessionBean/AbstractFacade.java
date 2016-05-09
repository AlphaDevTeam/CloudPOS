package com.AlphaDevs.cloud.web.SessionBean;

import com.AlphaDevs.cloud.web.Enums.Document;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;




/**
 *
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @param <T>
 * @since 2012/06/16
 * @see Alpha Development Team ( www.AlphaDevs.com )
 * 
 */

public abstract class AbstractFacade<T> 
{
    
    
    private final Class<T> entityClass;
    
    
    protected abstract EntityManager getEntityManager();
    
    public AbstractFacade(Class<T> EntityClass)
    {
        this.entityClass = EntityClass;
        
    }
    
    public void create(T entity)
    {
       getEntityManager().persist(entity);
    }
    
    /**
    *TEst Description 
    * 
    * this method use to retrieve all the data from table
    * @author Mihindu Gajaba Karunarathne
    * @version 1.0.0
    * @since 2012/06/16
    * @see Alpha Development Team ( www.AlphaDevs.com )
    * @return return
    * @serialData 
    * 
    */
    public List<T> findAll() 
    {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
        
    }
    
    public void edit(T entity) 
    {
        getEntityManager().merge(entity);
    }
    
    
    public T find(Object id) 
    {
        //System.out.println("Getting id : " + id + " with : " + entityClass.getName());
        return getEntityManager().find(entityClass, id);
    }
    
    public T find(Document DocType, Object id) 
    {
        //System.out.println("Getting id : " + id + " with : " + entityClass.getName());
        return getEntityManager().find(entityClass, id);
    }
    
    public void destroy(T entity) 
    {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    public void beginTransaction(){
        getEntityManager().getTransaction().begin();
    }
    
    public void commit(){
        getEntityManager().getTransaction().commit();
    }
    
    public void rollback(){
        getEntityManager().getTransaction().rollback();
    }

}
