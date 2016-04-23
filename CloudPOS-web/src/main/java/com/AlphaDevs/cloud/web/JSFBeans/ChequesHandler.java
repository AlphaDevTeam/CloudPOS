package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Cheques;
import com.AlphaDevs.cloud.web.Enums.ChequeStatus;
import com.AlphaDevs.cloud.web.SessionBean.ChequesController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 *
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@ManagedBean
@RequestScoped
public class ChequesHandler {

    @EJB
    private ChequesController chequesController;

    private Cheques current;
    private List<Cheques> selectedDTCheques;
    private ChequeStatus toStatus;

    public ChequesHandler() {
        current = new Cheques();
    }

    public ChequesController getChequesController() {
        return chequesController;
    }

    public void setChequesController(ChequesController chequesController) {
        this.chequesController = chequesController;
    }

    public List<Cheques> getSelectedDTCheques() {
        return selectedDTCheques;
    }

    public void setSelectedDTCheques(List<Cheques> selectedDTCheques) {
        this.selectedDTCheques = selectedDTCheques;
    }

    public Cheques getCurrent() {
        return current;
    }

    public void setCurrent(Cheques current) {
        this.current = current;
    }

    public List<Cheques> getList() {
        return getChequesController().findAll();
    }

    public ChequeStatus getToStatus() {
        return toStatus;
    }

    public void setToStatus(ChequeStatus toStatus) {
        this.toStatus = toStatus;
    }
    

    public String updateCheques() {
        for (Cheques selectedDTCheque : getSelectedDTCheques()) {
            selectedDTCheque.setStatus(getToStatus());
            selectedDTCheque.setRelatedBankAccount(getCurrent().getRelatedBankAccount());
            getChequesController().edit(selectedDTCheque);
            
        }
        System.out.println("Saved");
        return "#";
    }

}
