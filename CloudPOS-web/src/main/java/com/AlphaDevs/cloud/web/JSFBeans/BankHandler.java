
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Bank;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.SessionBean.BankController;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@ManagedBean
@ViewScoped
public class BankHandler {
    @EJB
    private BankController bankController;
    
    private Bank current;
    private Bank selectedBank;
    private List<Bank> virtualList;
    
    public BankHandler() {
        if(current == null){
            current = new Bank();
        }
        if(selectedBank == null){
            selectedBank = new Bank();
        }
        
        virtualList = new ArrayList<Bank>();
                
    }
    
    public void loadData(){
        virtualList = new ArrayList<Bank>();
        if(bankController != null){
            for(Bank bank : bankController.findAll()){
                virtualList.add(bank);
                System.out.println("Added Bank to List :" + bank);
            }
            MessageHelper.addSuccessMessage("Bank Data Loaded");
        }        
    }

    public BankController getBankController() {
        return bankController;
    }

    public void setBankController(BankController bankController) {
        this.bankController = bankController;
    }

    public Bank getCurrent() {
        return current;
    }

    public void setCurrent(Bank current) {
        this.current = current;
    }

    public Bank getSelectedBank() {
        return selectedBank;
    }

    public void setSelectedBank(Bank selectedBank) {
        this.selectedBank = selectedBank;
    }
    
    public List<Bank> getList(){
        return getBankController().findAll();
    }

    public List<Bank> getVirtualList() {
        return virtualList;
    }

    public void setVirtualList(List<Bank> virtualList) {
        this.virtualList = virtualList;
    }
    
    public void addBank()
    {
        boolean isFound =false;
        
        if(current.getBankCode()== null || current.getBankName()== null )
        {
            MessageHelper.addErrorMessage("Error", "Please Fill All Details");
            return;
        }
        
        for(Bank bank : getVirtualList())
        {
            if(bank.getBankCode().equalsIgnoreCase(current.getBankCode()))
            {
                isFound = true;
                break;
            }
            
        }
        if (!isFound)
        {
            getVirtualList().add(current);
        }
        
        current = new Bank();
       
    }
    
    public void saveList()
    { 
        if(getVirtualList() == null || getVirtualList().isEmpty() )
        {
            MessageHelper.addErrorMessage("Error", "No Data to save");
            return;
        }
        
        for(Bank bank : getVirtualList())
        {
            getBankController().create(bank);
        }
                
        current = new Bank();
        getVirtualList().clear();
        MessageHelper.addSuccessMessage("Data Saved");
    }
    
    public void removeSelectedItem()
    {
        if ( getSelectedBank() ==null)
        {
            MessageHelper.addErrorMessage("Selection ","No item Selected ");
         //   return "";
        }
        else
        {
            for(int a=0;a<getVirtualList().size();a++)
            {

                if(getVirtualList().get(a) == getSelectedBank())
                {
                    getVirtualList().remove(a);
                    break;
                }
            }
            
        }
        //return "#";
    }
   
    
    public void onEdit(RowEditEvent event) 
    {   
        for(Bank bank: getVirtualList())
        {
            if(bank.getBankCode() == null ? ((Bank)event.getObject()).getBankCode() == null : bank.getBankCode().equals(((Bank)event.getObject()).getBankCode()))
            {
                bank.setBankCode(((Bank)event.getObject()).getBankCode());
                bank.setBankName(((Bank)event.getObject()).getBankName());
                
            }
        }
        MessageHelper.addSuccessMessage(((Bank) event.getObject()).getBankCode() + " Updated!");
    }  
    public void onCancel(RowEditEvent event) 
    {  
        MessageHelper.addSuccessMessage(((Bank) event.getObject()).getBankCode() + "Not Updated!");
        
    }
}
