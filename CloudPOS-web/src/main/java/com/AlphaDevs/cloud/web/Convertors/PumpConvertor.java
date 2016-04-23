
package com.AlphaDevs.cloud.web.Convertors;

import com.AlphaDevs.cloud.web.Entities.Pump;
import com.AlphaDevs.cloud.web.SessionBean.PumpController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@ManagedBean
@RequestScoped
public class PumpConvertor implements Converter {
    
    @EJB
    private PumpController pumpController;

    public PumpConvertor() {
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        if(value.isEmpty()){
            return null;
        }else{
            return pumpController.find(Long.valueOf(value));
        }
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        if(value == null || "null".equals(value.toString())){
            return "";
        }else{
            return ((Pump) value).getId().toString();
        }
        
    }

}
