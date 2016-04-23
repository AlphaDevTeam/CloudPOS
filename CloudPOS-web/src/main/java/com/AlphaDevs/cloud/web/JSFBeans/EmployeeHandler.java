
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Employee;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.SessionBean.EmployeeController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
@RequestScoped
public class EmployeeHandler {
    @EJB
    private EmployeeController employeController;
    
    private Employee current;

    public EmployeeHandler() {
        if(current == null){
            current = new Employee();
        }
    }
    
    public List<Employee> getList(){
        return employeController.findAll();
    }

    public EmployeeController getEmployeController() {
        return employeController;
    }

    public void setEmployeController(EmployeeController employeController) {
        this.employeController = employeController;
    }

    public Employee getCurrent() {
        return current;
    }

    public void setCurrent(Employee current) {
        this.current = current;
    }
    
    public String createEmploye()
    {
        try{
            System.out.println("Creating Log");
            Logger Log = EntityHelper.createLogger("Create Employee ", "", TransactionTypes.EMPLOYEE);
            System.out.println("Creating Log....ok");
            if(Log != null){
                System.out.println("Log Validate");
                current.setLogger(Log);
                employeController.create(current);
                current = new Employee();
                MessageHelper.addSuccessMessage("Employe Added!");
                return "Home";
                
            }
            else
            {
                MessageHelper.addErrorMessage("Error - createEmploye", "Session Invalid");
                return "Login";
            }
        
            
            
        }
        catch(Exception e)
        {
            MessageHelper.addErrorMessage("Error - createEmploye", e.toString());
            return "Login";
        }
        
    }
}
