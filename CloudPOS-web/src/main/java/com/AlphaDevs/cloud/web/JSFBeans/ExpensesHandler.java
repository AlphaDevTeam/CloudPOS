

package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Expenses;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.SessionBean.ExpensesController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
public class ExpensesHandler {
    @EJB
    private LoggerController loggerController;
    @EJB
    private ExpensesController expensesController;
    
    
    private Expenses curent;

    public ExpensesHandler() {
        if(curent == null){
           curent = new Expenses();
        }
    }

    public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
    }

    
    public ExpensesController getExpensesController() {
        return expensesController;
    }

    public void setExpensesController(ExpensesController expensesController) {
        this.expensesController = expensesController;
    }

    public Expenses getCurent() {
        return curent;
    }

    public void setCurent(Expenses curent) {
        this.curent = curent;
    }
    
    public List<Expenses> getList(){
        return getExpensesController().findAll();
    }
    
    public String persistExpense(){
        
        //Creating Logger
        Logger log = EntityHelper.createLogger("Expense - " + getCurent().getExpDescription()  , getCurent().getExpCode(), TransactionTypes.EXP);
        getLoggerController().create(log);
               
        getCurent().setLogger(log);
        getExpensesController().create(getCurent());
        return "Home";
    }
    
}
