/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.Convertors;

import com.AlphaDevs.cloud.web.Entities.Menu;
import com.AlphaDevs.cloud.web.SessionBean.MenuController;
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
public class MenuConvertor implements Converter{
    @EJB
    private MenuController menuController;

    
    /**
     * Creates a new instance of MenuItemConvertor
     */
    public MenuConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value.isEmpty()){
            return null;
        }else
        {
            return menuController.find(Long.valueOf(value));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
     if(value == null || "null".equals(value.toString())){
            return "";
        }else{
            return ((Menu) value).getId().toString();
        }
    }
    
}
