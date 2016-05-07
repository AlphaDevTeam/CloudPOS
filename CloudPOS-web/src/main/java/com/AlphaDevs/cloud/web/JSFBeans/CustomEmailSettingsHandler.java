

package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Company;
import com.AlphaDevs.cloud.web.Entities.CustomEmailSettings;
import com.AlphaDevs.cloud.web.Entities.EmailAddress;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.SessionBean.CustomEmailSettingsController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
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
public class CustomEmailSettingsHandler {
    @EJB
    private LoggerController loggerController;
    @EJB
    private CustomEmailSettingsController customEmailSettingsController;
    
    private CustomEmailSettings current;
    private List<EmailAddress> virtualList;
    private EmailAddress selectedEmail;
    private String emailAddress;
    private String emailName;

    public CustomEmailSettingsHandler() {
        if(current == null){
            current = new CustomEmailSettings();
        }
        current.setToEmailAddress(new ArrayList<EmailAddress>());
        virtualList = new ArrayList<EmailAddress>();
    }

    public String getEmailName() {
        return emailName;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }

    public EmailAddress getSelectedEmail() {
        return selectedEmail;
    }

    public void setSelectedEmail(EmailAddress selectedEmail) {
        this.selectedEmail = selectedEmail;
    }
    
    public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
    }
    
    public CustomEmailSettingsController getCustomEmailSettingsController() {
        return customEmailSettingsController;
    }

    public void setCustomEmailSettingsController(CustomEmailSettingsController customEmailSettingsController) {
        this.customEmailSettingsController = customEmailSettingsController;
    }

    public CustomEmailSettings getCurrent() {
        return current;
    }

    public void setCurrent(CustomEmailSettings current) {
        this.current = current;
    }
    
    public List<EmailAddress> getVirtualList() {
        return virtualList;
    }

    public void setVirtualList(List<EmailAddress> virtualList) {
        this.virtualList = virtualList;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public List<CustomEmailSettings> getList(){
        List<CustomEmailSettings> relatedEmailSettings = new ArrayList<CustomEmailSettings>();
        Company loggedCompany = EntityHelper.getLoggedCompany();
        if(loggedCompany != null ){
            relatedEmailSettings = getCustomEmailSettingsController().findRelatedEmailSettings(loggedCompany);
        }
        
        return relatedEmailSettings;
    }
    
    public String createCusomEmailSetting() {

        //Creating Logger
        Logger log = EntityHelper.createLogger("Custom Email Setup - " + getCurrent().getEmailSubject(), getCurrent().toString(), TransactionTypes.EMAIL_SETUP);
        loggerController.create(log);
        getCurrent().setRelatedLoger(log);

        getCurrent().setRelatedCompany(EntityHelper.getLoggedCompany());
        for (EmailAddress emailAddressAdd : getVirtualList()) {
            if(emailAddressAdd != null && getCurrent().getToEmailAddress() != null ){
                getCurrent().getToEmailAddress().add(emailAddressAdd);
            }
        }
        
        
        MessageHelper.addSuccessMessage("Data Saved");
        
        getCustomEmailSettingsController().create(getCurrent());
        System.out.println("sad" + getCurrent().getToEmailAddress());
        getVirtualList().clear();
        return "Home";
    }
    
    
    public void onEdit(RowEditEvent event) 
    {   
        for(EmailAddress emailAddres : getVirtualList())
        {
            if(emailAddres.getEmailAddress() == null ? ((EmailAddress)event.getObject()).getEmailAddress() == null : emailAddres.getEmailAddress().equals(((EmailAddress)event.getObject()).getEmailAddress()))
            {
                emailAddres.setEmailAddress((((EmailAddress)event.getObject()).getEmailAddress()));
                emailAddres.setName((((EmailAddress)event.getObject()).getName()));
            }
        }
        MessageHelper.addSuccessMessage( (((EmailAddress)event.getObject()).getName()) + " Updated!");
    }  
    public void onCancel(RowEditEvent event) 
    {  
        MessageHelper.addSuccessMessage( (((EmailAddress)event.getObject()).getName()) + "Not Updated!");
        
    }
    
    public void removeSelectedItem()
    {
        if ( getSelectedEmail() == null)
        {
            MessageHelper.addErrorMessage("Selection ","No item Selected ");
        }
        else
        {
            if(getVirtualList() != null && !getVirtualList().isEmpty()){
                for (EmailAddress emailAddressCheck : getVirtualList()) {
                    if(emailAddressCheck != null && emailAddressCheck.getEmailAddress() != null && getSelectedEmail() != null ){
                        if(emailAddressCheck.getEmailAddress().equalsIgnoreCase(getSelectedEmail().getEmailAddress())){
                            getVirtualList().remove(emailAddressCheck);
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public void addEmailToList()
    {
        boolean isFound =false;
        
        if(getEmailAddress() == null && getEmailAddress().isEmpty())
        {
            MessageHelper.addErrorMessage("Error", "Please Fill All Details");
            return;
        }
        if(getVirtualList() != null){
            for(EmailAddress emailAddressFromList : getVirtualList())
            {
                if(emailAddressFromList != null && emailAddressFromList.getEmailAddress() != null && emailAddressFromList.getEmailAddress().equalsIgnoreCase(getEmailAddress()))
                {
                    isFound = true;
                    break;
                }

            }
            if (!isFound)
            {
                getVirtualList().add(new EmailAddress(getEmailName(), getEmailAddress()));
            }
        }
        
        
        MessageHelper.addSuccessMessage("Data Added");
       
    }
    
    
}
