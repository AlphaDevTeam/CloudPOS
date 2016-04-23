

package com.AlphaDevs.cloud.web.Convertors;

import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.SessionBean.ItemsController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@RequestScoped
public class ItemConvertor implements Converter
{
    @EJB
    private ItemsController itemsController;
    
    
    public ItemConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        System.out.println("VALUE : " + value );
        if(value.isEmpty()){
            return null;
        }else{
            return itemsController.find(Long.valueOf(value));
        }
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
         System.out.println("VALUE Object: " + value );
        if(value == null || "null".equals(value.toString())){
            return "";
        }else{
            return ((Items) value).getId().toString();
        }
        
    }
    
    
}
