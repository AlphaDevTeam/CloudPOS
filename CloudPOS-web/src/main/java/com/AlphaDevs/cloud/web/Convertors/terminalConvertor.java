/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.Convertors;

import com.AlphaDevs.cloud.web.Entities.Terminal;
import com.AlphaDevs.cloud.web.SessionBean.TerminalController;
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
public class terminalConvertor implements Converter{
    @EJB
    private TerminalController terminalController;
    
    

    /**
     * Creates a new instance of terminalConvertor
     */
    public terminalConvertor() {
    }
    
    
     @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        if(value.isEmpty()){
            return null;
        }else{
            return terminalController.find(Long.valueOf(value));
        }
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        if(value == null || "null".equals(value.toString())){
            return "";
        }else{
            return ((Terminal) value).getId().toString();
        }
        
    }
}
