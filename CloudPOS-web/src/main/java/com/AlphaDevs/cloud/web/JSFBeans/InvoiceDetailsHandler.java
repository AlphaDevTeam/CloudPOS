
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.InvoiceDetails;
import com.AlphaDevs.cloud.web.SessionBean.InvoiceDetailsController;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@ViewScoped
public class InvoiceDetailsHandler 
{
    @EJB
    private InvoiceDetailsController invoiceDetailsController;
    
    private InvoiceDetails current;
    private List<InvoiceDetails> VirtualInvDetails;
    
    /** Creates a new instance of InvoiceDetailsHandler */
    public InvoiceDetailsHandler() 
    {
        if(current == null)
        {
            current = new InvoiceDetails();
        }
        VirtualInvDetails = new ArrayList<InvoiceDetails>();
    }

    public List<InvoiceDetails> getVirtualInvDetails() {
        return VirtualInvDetails;
    }

    public void setVirtualInvDetails(List<InvoiceDetails> VirtualInvDetails) {
        this.VirtualInvDetails = VirtualInvDetails;
    }
    
     public void handleSelect(SelectEvent event) 
    {   
        current.setPrice(current.getItem().getUnitPrice());
    }  
    
    
    public InvoiceDetails getCurrent() {
        return current;
    }

    public void setCurrent(InvoiceDetails current) {
        this.current = current;
    }

    public InvoiceDetailsController getInvoiceDetailsController() {
        return invoiceDetailsController;
    }

    public void setInvoiceDetailsController(InvoiceDetailsController invoiceDetailsController) {
        this.invoiceDetailsController = invoiceDetailsController;
    }
    
    public List<InvoiceDetails> getList()
    {
        return invoiceDetailsController.findAll();
    }

}
