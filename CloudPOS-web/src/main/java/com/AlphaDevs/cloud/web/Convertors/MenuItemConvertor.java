/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.Convertors;

import com.AlphaDevs.cloud.web.Entities.MenuItem;
import com.AlphaDevs.cloud.web.SessionBean.MenuItemController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Kumuditha_2
 */
@ManagedBean
@RequestScoped
public class MenuItemConvertor implements Converter{
    @EJB
    private MenuItemController menuItemController;

    
    /**
     * Creates a new instance of MenuItemConvertor
     */
    public MenuItemConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value.isEmpty()){
            return null;
        }else
        {
            return menuItemController.find(Long.valueOf(value));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
     if(value == null || "null".equals(value.toString())){
            return "";
        }else{
            return ((MenuItem) value).getId().toString();
        }
    }
    
}
