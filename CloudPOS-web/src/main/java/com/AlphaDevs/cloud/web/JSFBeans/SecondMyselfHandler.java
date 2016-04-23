/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.MYSELF;
import com.AlphaDevs.cloud.web.SessionBean.MyselfController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Kumuditha_2
 */
@ManagedBean
@SessionScoped
public class SecondMyselfHandler {

    @EJB
    private MyselfController mycontroller;

    private MYSELF mslf;

    /**
     * Creates a new instance of SecondMyselfHandler
     */
    public SecondMyselfHandler() {
        if (mslf == null) {
            mslf = new MYSELF();
        }
    }

    
    
     public String singleMyselfRecord() {

        setMslf(mycontroller.find(getMslf().getId()));
        return "#";
    }
     
     public void updateRecord(){
     mycontroller.edit(mslf);
     }

    /**
     * @return the mslf
     */
    public MYSELF getMslf() {
        return mslf;
    }

    /**
     * @param mslf the mslf to set
     */
    public void setMslf(MYSELF mslf) {
        this.mslf = mslf;
    }

}
