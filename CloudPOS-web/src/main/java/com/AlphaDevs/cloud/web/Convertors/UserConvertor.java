
package com.AlphaDevs.cloud.web.Convertors;

import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.SessionBean.UserController;
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
public class UserConvertor implements Converter {
    
    @EJB
    private UserController userController;

    
    public UserConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        if(value.isEmpty()){
            return null;
        }else{
            return userController.find(Long.valueOf(value));
        }
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        if(value == null || "null".equals(value.toString())){
            return "";
        }else{
            return ((UserX) value).getId().toString();
        }
        
    }
}
