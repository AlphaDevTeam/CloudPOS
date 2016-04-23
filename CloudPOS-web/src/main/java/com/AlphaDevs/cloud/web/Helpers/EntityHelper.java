

package com.AlphaDevs.cloud.web.Helpers;

import com.AlphaDevs.cloud.web.Entities.Company;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.Terminal;
import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

public class EntityHelper 
{
    public static Logger Logger;
    
    

    public static Logger getLogger() {
        return Logger;
    }

    public static void setLogger(Logger Logger) {
        EntityHelper.Logger = Logger;
    }
    
    public static Logger createLogger(String Description , String TrnNo,TransactionTypes TrnType)
    {
        try
        {
            FacesContext context = FacesContext.getCurrentInstance();
            UserX LogUser = (UserX) context.getExternalContext().getSessionMap().get(AlphaConstant.SESSION_USER);
            Terminal LogTerminal = (Terminal) context.getExternalContext().getSessionMap().get(AlphaConstant.SESSION_TERMINAL);
            Location LogLocation = (Location) context.getExternalContext().getSessionMap().get(AlphaConstant.SESSION_LOCATION);
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            
            if( LogUser!= null || LogTerminal != null )
            {
                setLogger(new Logger(Description , dateFormat.parse(dateFormat.format(cal.getTime())) ,LogUser ,LogTerminal ,TrnType, TrnNo,LogLocation));
                return  getLogger();
            }
            else
            {
                MessageHelper.addErrorMessage("Error - createLogger", "Session Invalid , Please Login");
                return null;
            }
            
        }
        catch(Exception ex)
        {
            MessageHelper.addErrorMessage("Error - createLogger", ex.toString());
            return null;
        }
        
    }
    
    public static Company getLoggedCompany()
    {
        try
        {
            FacesContext context = FacesContext.getCurrentInstance();
            UserX LogUser = (UserX) context.getExternalContext().getSessionMap().get(AlphaConstant.SESSION_USER);
            Terminal LogTerminal = (Terminal) context.getExternalContext().getSessionMap().get("Terminal");
          
            if( LogUser!= null || LogTerminal != null )
            {
                return  LogUser.getAssociatedCompany();
            }
            else
            {
                MessageHelper.addErrorMessage("Error - getLoggedCompany", "Session Invalid , Please Login");
                return null;
            }
            
        }
        catch(Exception ex)
        {
            MessageHelper.addErrorMessage("Error - getLoggedCompany", ex.toString());
            return null;
        }
                
    }

}
