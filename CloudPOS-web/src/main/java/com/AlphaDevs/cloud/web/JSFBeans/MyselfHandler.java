/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.MYSELF;
import com.AlphaDevs.cloud.web.SessionBean.MyselfController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Kumuditha_2
 */
@ManagedBean
@ViewScoped
public class MyselfHandler {

    @EJB
    private MyselfController myselfController;

    private MYSELF my;
    private List<MYSELF> listName;

    /**
     * Creates a new instance of MyselfHandler
     */
    public MyselfHandler() {

        if (my == null) {
            my = new MYSELF();
            my.setName("Kuma");
            my.setAge(25);

        }
        System.out.println("Constructor");

    }

    /**
     * @return the my
     */
    public MYSELF getMy() {
        return my;
    }

    /**
     * @param my the my to set
     */
    public void setMy(MYSELF my) {
        this.my = my;
    }

    public void saveMyself() {
        System.out.println(my);
        myselfController.persist(my);
        System.out.println(my);
    }

    public List<MYSELF> getAllMySelfRecords() {

        return myselfController.findAll();

    }

    public String singleRecord() {

        my = myselfController.find(my.getId());
        return "#";
    }

    public String singleRecordByName() {

        listName = myselfController.getMyselfByName(my);
        return "#";
    }

    public String deleteRecord() {

        myselfController.destroy(my);
        return "#";
    }

    public String redirect() {
        return "Home";
    }

//    public MYSELF getSingleRecord(){
//    
//    }
    public List<MYSELF> getSearchedNames() {

        return listName;

    }

}
