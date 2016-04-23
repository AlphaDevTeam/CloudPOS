
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.CreditCardTeminals;
import com.AlphaDevs.cloud.web.SessionBean.CreditCardTeminalsController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
public class CreditCardTerminalHandler {
    @EJB
    private CreditCardTeminalsController creditCardTeminalsController;
    
    private CreditCardTeminals current;
    
    public CreditCardTerminalHandler() {
        if(current == null){
            current = new CreditCardTeminals();
        }
    }

    public CreditCardTeminalsController getCreditCardTeminalsController() {
        return creditCardTeminalsController;
    }

    public void setCreditCardTeminalsController(CreditCardTeminalsController creditCardTeminalsController) {
        this.creditCardTeminalsController = creditCardTeminalsController;
    }

    public CreditCardTeminals getCurrent() {
        return current;
    }

    public void setCurrent(CreditCardTeminals current) {
        this.current = current;
    }
    
    public List<CreditCardTeminals> getList(){
        return getCreditCardTeminalsController().findAll();
    }
    
    public String persistCreditCardTerminals(){
        getCreditCardTeminalsController().create(getCurrent());
        return "Home";
    }

}
