
package com.AlphaDevs.cloud.web.Convertors;

import com.AlphaDevs.cloud.web.Entities.CustomerCategory;
import com.AlphaDevs.cloud.web.SessionBean.CustomerCategoryController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@RequestScoped
public class ManagedConvertor implements Converter
{
    @EJB
    private CustomerCategoryController customerCategoryController;
    
    /** Creates a new instance of ManagedConvertor */
    public ManagedConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        return customerCategoryController.find(Long.valueOf(value)); 
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        return ((CustomerCategory) value).getId().toString();
    }

   

}
