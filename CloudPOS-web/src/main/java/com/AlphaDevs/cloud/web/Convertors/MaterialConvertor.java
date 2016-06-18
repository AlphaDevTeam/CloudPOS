package com.AlphaDevs.cloud.web.Convertors;

import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Interfaces.Meterials;
import com.AlphaDevs.cloud.web.SessionBean.ItemsController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Mihindu Gajaba Karunarathne Alpha Development Team (Pvt) Ltd
 *
 */
@ManagedBean
@RequestScoped
public class MaterialConvertor implements Converter {

    @EJB
    private ItemsController itemsController;

    public MaterialConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.isEmpty()) {
            return null;
        } else {
            return itemsController.find(Long.valueOf(value));
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || "null".equals(value.toString())) {
            return "";
        } else {
            if (value instanceof Items && ((Items) value).getId() != null) {
                return ((Items) value).getId().toString();
            }
        }
        return "";
    }

}
