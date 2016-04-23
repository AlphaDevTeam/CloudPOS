
package com.AlphaDevs.cloud.web.Convertors;

import com.AlphaDevs.cloud.web.Entities.Company;
import com.AlphaDevs.cloud.web.SessionBean.CompanyController;
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
public class CompanyConvertor implements Converter{
    @EJB
    private CompanyController companyController;

    public CompanyConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value.isEmpty()){
            return null;
        }else{
            return companyController.find(Long.valueOf(value));
        }
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null || "null".equals(value.toString())){
            return "";
        }else{
            return ((Company ) value ).getId().toString();
        }
        
    }
}
