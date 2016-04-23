

package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@SessionScoped
public class LoggerHandler 
{
    @EJB
    private LoggerController loggerController;
    
    private Logger current;
    
    private String desc;
    
    private Date fromDate = new Date();
    private Date toDate = new Date();
    
    private List<Logger> listOfLoggerData;

   
    
    public LoggerHandler() 
    {
        if(current == null)
        {
            current = new Logger();
        }
            
    }
    

    public Logger getCurrent() {
        return current;
    }

    public void setCurrent(Logger current) {
        this.current = current;
    }

    public String getLogID()
    {
        loggerController.create(current);
        return "#";
    }
    
    public HashSet<String> getAllLoggerDetailList(){
        
        List<Logger> loggerList = loggerController.findAll();
        List<String> loggerDescriptionList= new ArrayList<String>();
        
        for(Logger log: loggerList){        
            loggerDescriptionList.add(log.getDescription());        
        }
        HashSet<String> loggerDescriptionSet = new HashSet<String>(loggerDescriptionList);
        return loggerDescriptionSet;
        
    }
    
    
    public void initRelevantLoggerDetails(){
        
//        System.out.println(getCurrent().getDescription());
//        System.out.println(getCurrent().getUser().getUserName());
//        System.out.println(getCurrent().getTerminal().getTerminalName());
//        System.out.println(getCurrent().getTrnType());
//        System.out.println(getCurrent().getTrnNumber());
//        
//        System.out.println(fromDate.toString());
//        System.out.println(toDate.toString());
        
        setListOfLoggerData(new ArrayList<Logger>());
        setListOfLoggerData(getLoggerController().getSearchedLoggerDetails(getCurrent(), fromDate, toDate));
       
       
    }
    
    
     public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
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

    public List<Logger> getListOfLoggerData() {
        return listOfLoggerData;
    }

    public void setListOfLoggerData(List<Logger> listOfLoggerData) {
        this.listOfLoggerData = listOfLoggerData;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public TransactionTypes[]  getTrnTypes(){
        return TransactionTypes.values();
                
    }
    
    

}
