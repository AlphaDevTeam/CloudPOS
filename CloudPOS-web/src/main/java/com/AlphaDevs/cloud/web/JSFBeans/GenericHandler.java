
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Enums.ViewMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * @param <T>
 * 
 */

public abstract class GenericHandler<T>  {
    
    private T current;
    private T selectedCurrent;
    private List<T> listCurrent;
    private ViewMode viewMode;  
    
    public abstract String persistObject();
    

    public GenericHandler(T currentObject) {
        this.current = currentObject;
        this.selectedCurrent = currentObject;
        this.listCurrent = new ArrayList<>();
    }

    public T getCurrent() {
        return current;
    }

    public T getSelectedCurrent() {
        return selectedCurrent;
    }

    public List<T> getListCurrent() {
        return listCurrent;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public void setSelectedCurrent(T current) {
        this.selectedCurrent = current;
    }

    public void getListCurrent(List<T> currentList) {
        this.listCurrent = currentList;
    }

    public ViewMode getViewMode() {
        return viewMode;
    }

    public void setViewMode(ViewMode viewMode) {
        this.viewMode = viewMode;
    }

}
