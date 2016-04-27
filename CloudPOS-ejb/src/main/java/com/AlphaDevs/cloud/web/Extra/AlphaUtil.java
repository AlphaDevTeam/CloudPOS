/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.Extra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Mihindu Karunarathne
 */



public class AlphaUtil {
    public static List<?> nullSafe(List<?> list){
        if(list != null && !list.isEmpty()){
            return list;
        }else{
            return new ArrayList<>();
        }
    }
}
