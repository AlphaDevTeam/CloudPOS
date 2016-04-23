package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.AccessRequest;
import com.AlphaDevs.cloud.web.Entities.SystemNumbers;
import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.Enums.RequestType;
import com.AlphaDevs.cloud.web.Enums.Status;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.RequestAccessController;
import com.AlphaDevs.cloud.web.SessionBean.SystemNumbersController;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Mihindu Gajaba Karunarathne Alpha Development Team (Pvt) Ltd
 *
 */
@ManagedBean
@ViewScoped
public class AccessRequestHandler {

    @EJB
    private RequestAccessController requestAccessController;
    @ManagedProperty(value = "#{userHandler}")
    private UserHandler userHandler;
    private AccessRequest current;
    private boolean skip;
    private Document documentType;
    private List<AccessRequest> listRequests;
    private Status selectedStatus;

    public AccessRequestHandler() {
    }

    @PostConstruct
    public void init() {
        current = new AccessRequest();
        documentType = Document.REQUEST;
    }

    public Document getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Document documentType) {
        this.documentType = documentType;
    }

    public RequestAccessController getRequestAccessController() {
        return requestAccessController;
    }

    public void setRequestAccessController(RequestAccessController requestAccessController) {
        this.requestAccessController = requestAccessController;
    }

    public UserHandler getUserHandler() {
        return userHandler;
    }

    public void setUserHandler(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    public AccessRequest getCurrent() {
        return current;
    }

    public List<AccessRequest> getListRequests() {
        return listRequests;
    }

    public void setListRequests(List<AccessRequest> listRequests) {
        this.listRequests = listRequests;
    }

    public void setCurrent(AccessRequest current) {
        this.current = current;
    }

    public Status[] getStatuses() {
        return Status.values();
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public Status getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(Status selectedStatus) {
        this.selectedStatus = selectedStatus;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public String onFlowProcessDemoRequest(FlowEvent event) {

        getCurrent().setRequestStatus(Status.PENDING);
        getCurrent().setRequestType(RequestType.DEMO);
        getCurrent().setRequestNumber(getRequestNumber());

        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public String getRequestNumber() {
        return "REQ-" + getCurrent().getCompanyName().replaceAll("\\s", "").substring(0, 3).toUpperCase() + "-" + (1 + (int) (Math.random() * 100));
    }

    public String save() {
        getRequestAccessController().create(getCurrent());
        return "Login";
    }

    public List<AccessRequest> getList() {
        return getRequestAccessController().getRequests(getSelectedStatus());
    }

    public String approveRequest() {
        for (AccessRequest request : getListRequests()) {
            if (request != null && request.getRequestType() == RequestType.DEMO) {
                getUserHandler().demoLogin(request);
                request.setRequestStatus(Status.ACTIVE);
                getRequestAccessController().edit(request);
            }
        }
        return "Home";
    }

    public String getRequestedIPAddress() {
        return InfoGrabber.getTerminalString();
    }

}
