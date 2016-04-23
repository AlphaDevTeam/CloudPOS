

package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.BankBranch;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.SessionBean.BankBranchController;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
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
@SessionScoped
public class BankBranchHandler {
    @EJB
    private BankBranchController bankBranchController;
    
    private BankBranch current;
    private BankBranch selectedBankBranch;
    private List<BankBranch> virtualList;
    
     
    public BankBranchHandler() {
        if(current == null){
            current = new BankBranch();
        }
        if(selectedBankBranch == null){
            selectedBankBranch = new BankBranch();
        }
        
        virtualList = new ArrayList<BankBranch>();
    }


    public BankBranchController getBankBranchController() {
        return bankBranchController;
    }

    public void setBankBranchController(BankBranchController bankBranchController) {
        this.bankBranchController = bankBranchController;
    }

    public BankBranch getCurrent() {
        return current;
    }

    public void setCurrent(BankBranch current) {
        this.current = current;
    }
    
    public List<BankBranch> getList(){
        return getBankBranchController().findAll();
    }

    public BankBranch getSelectedBankBranch() {
        return selectedBankBranch;
    }

    public void setSelectedBankBranch(BankBranch selectedBankBranch) {
        this.selectedBankBranch = selectedBankBranch;
    }

    public List<BankBranch> getVirtualList() {
        return virtualList;
    }

    public void setVirtualList(List<BankBranch> virtualList) {
        this.virtualList = virtualList;
    }
    
    public void addBankBranch()
    {
        boolean isFound =false;
        
        if(current.getBrnachCode()== null || current.getBranchName()== null )
        {
            MessageHelper.addErrorMessage("Error", "Please Fill All Details");
            return;
        }
        
        for(BankBranch bankBranch : getVirtualList())
        {
            if(bankBranch.getBrnachCode().equalsIgnoreCase(current.getBrnachCode()))
            {
                isFound = true;
                break;
            }
            
        }
        if (!isFound)
        {
            getVirtualList().add(current);
        }
        
        current = new BankBranch();
       
    }
    
    public void saveList()
    { 
        if(getVirtualList() == null || getVirtualList().isEmpty() )
        {
            MessageHelper.addErrorMessage("Error", "No Data to save");
            return;
        }
        
        for(BankBranch bankBranch : getVirtualList())
        {
            
            getBankBranchController().create(bankBranch);
        }
                
        current = new BankBranch();
        getVirtualList().clear();
        MessageHelper.addSuccessMessage("Data Saved");
    }
    
    public void removeSelectedItem()
    {
        if ( getSelectedBankBranch()==null)
        {
            MessageHelper.addErrorMessage("Selection ","No item Selected ");
         //   return "";
        }
        else
        {
            for(int a=0;a<getVirtualList().size();a++)
            {

                if(getVirtualList().get(a) == getSelectedBankBranch())
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
        for(BankBranch bankBranch : getVirtualList())
        {
            if(bankBranch.getBrnachCode()== null ? ((BankBranch)event.getObject()).getBrnachCode()== null : bankBranch.getBrnachCode().equals(((BankBranch)event.getObject()).getBrnachCode()))
            {
                bankBranch.setBrnachCode(((BankBranch)event.getObject()).getBrnachCode());
                bankBranch.setBranchName(((BankBranch)event.getObject()).getBranchName());
                
            }
        }
        MessageHelper.addSuccessMessage(((BankBranch) event.getObject()).getBrnachCode()+ " Updated!");
    }  
    public void onCancel(RowEditEvent event) 
    {  
        MessageHelper.addSuccessMessage(((BankBranch) event.getObject()).getBrnachCode()+ "Not Updated!");
        
    }
    
}
