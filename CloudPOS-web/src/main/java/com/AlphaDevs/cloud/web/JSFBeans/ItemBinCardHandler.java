

package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.ItemBincard;
import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.SessionBean.ItemBincardController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@SessionScoped
public class ItemBinCardHandler 
{
    @EJB
    private ItemBincardController itemBincardController;
    
    private ItemBincard current;
    private Items selectedItem;
    
    private List<ItemBincard> listOfItemBinCards;
    private Date fromDate = new Date();
    private Date toDate = new Date();

    

    /** Creates a new instance of ItemBinCardHandler */
    public ItemBinCardHandler() 
    {
        if(current == null)
        {
            current = new ItemBincard();
        }
        selectedItem = new Items();
    }

    public ItemBincardController getItemBincardController() {
        return itemBincardController;
    }

    public void setItemBincardController(ItemBincardController itemBincardController) {
        this.itemBincardController = itemBincardController;
    }

    public Items getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Items selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    public ItemBincard getCurrent() {
        return current;
    }

    public void setCurrent(ItemBincard current) {
        this.current = current;
    }
    
    public List<ItemBincard> getList()
    {
        return itemBincardController.findAll();
    }
    
    public List<ItemBincard> getSelectedList(){
        return getItemBincardController().findItemByUnit(getCurrent().getItem());
    }
    
    
    
   public void initBinCardRecords(){
       
       System.out.println(fromDate.toString());
       System.out.println(toDate.toString());
       System.out.println(getCurrent().getItem().getItemName());
   
       setListOfItemBinCards(new ArrayList<ItemBincard>());
        
       setListOfItemBinCards(getItemBincardController().getRelevantItemBinCardRecords(fromDate, toDate, getCurrent().getItem()));
   }

   
   public List<ItemBincard> getListOfItemBinCards() {
        return listOfItemBinCards;
    }

    public void setListOfItemBinCards(List<ItemBincard> listOfItemBinCards) {
        this.listOfItemBinCards = listOfItemBinCards;
    }
    
    

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
