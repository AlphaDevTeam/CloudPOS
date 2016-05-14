package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.GRNDetails;
import com.AlphaDevs.cloud.web.Entities.ItemBincard;
import com.AlphaDevs.cloud.web.Entities.Items;
import com.AlphaDevs.cloud.web.Entities.Job;
import com.AlphaDevs.cloud.web.Entities.JobDetails;
import com.AlphaDevs.cloud.web.Entities.Logger;
import com.AlphaDevs.cloud.web.Entities.Stock;
import com.AlphaDevs.cloud.web.Entities.SystemNumbers;
import com.AlphaDevs.cloud.web.Entities.UserX;
import com.AlphaDevs.cloud.web.Enums.Document;
import com.AlphaDevs.cloud.web.Enums.TransactionTypes;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Extra.DocumentEntityHelper;
import com.AlphaDevs.cloud.web.Helpers.EntityHelper;
import com.AlphaDevs.cloud.web.Helpers.MessageHelper;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.ItemBincardController;
import com.AlphaDevs.cloud.web.SessionBean.ItemsController;
import com.AlphaDevs.cloud.web.SessionBean.JobController;
import com.AlphaDevs.cloud.web.SessionBean.JobDetailsController;
import com.AlphaDevs.cloud.web.SessionBean.LocationController;
import com.AlphaDevs.cloud.web.SessionBean.LoggerController;
import com.AlphaDevs.cloud.web.SessionBean.StockController;
import com.AlphaDevs.cloud.web.SessionBean.SystemNumbersController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 *
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@ManagedBean
@ViewScoped
public class JobHandler extends SuperHandler {

    @EJB
    private StockController stockController;
    @EJB
    private ItemBincardController itemBincardController;

    @EJB
    private SystemNumbersController systemNumbersController;
    @EJB
    private LoggerController loggerController;
    @EJB
    private LocationController locationController;
    @EJB
    private ItemsController itemsController;
    @EJB
    private JobDetailsController jobDetailsController;
    @EJB
    private JobController jobController;

    private Job current;
    private JobDetails jobDetails;
    private JobDetails selectedJobDetail;
    private Document currentDocument;
    private SystemNumbers currentSystemNumber;
    private List<JobDetails> jobDetailList;

    private boolean editMode;
    private final String flashString = "JobCard";
    private final String isUpdateMode = "isUpdateMode";

    @PostConstruct
    public void init() {

        if (selectedJobDetail == null) {
            selectedJobDetail = new JobDetails();
        }

        if (SessionDataHelper.getFlash(flashString) != null) {
            setCurrent((Job) FacesContext.getCurrentInstance().getExternalContext().getFlash().get(flashString));
            setEditMode((boolean) SessionDataHelper.getFlash(isUpdateMode));
            System.out.println("@PostConstruct : JobHandler" + getCurrent() + " - " + isEditMode());
            System.out.println("@PostConstruct : JobHandler Details " + getCurrent().getJobDetails() + " - " + isEditMode());
        } else {
            setCurrent(new Job());
            setEditMode(false);
            setJobDetails(new JobDetails());
        }

        setJobDetailList(new ArrayList<JobDetails>());
        setCurrentDocument(Document.JOB);
    }

    public JobHandler() {

    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public JobController getJobController() {
        return jobController;
    }

    public List<JobDetails> getJobDetailList() {
        return jobDetailList;
    }

    public void setJobDetailList(List<JobDetails> jobDetailList) {
        this.jobDetailList = jobDetailList;
    }

    public ItemsController getItemsController() {
        return itemsController;
    }

    public SystemNumbersController getSystemNumbersController() {
        return systemNumbersController;
    }

    public void setSystemNumbersController(SystemNumbersController systemNumbersController) {
        this.systemNumbersController = systemNumbersController;
    }

    public LoggerController getLoggerController() {
        return loggerController;
    }

    public JobDetails getSelectedJobDetail() {
        return selectedJobDetail;
    }

    public void setSelectedJobDetail(JobDetails selectedJobDetail) {
        this.selectedJobDetail = selectedJobDetail;
    }

    public SystemNumbers getCurrentSystemNumber() {
        return currentSystemNumber;
    }

    public StockController getStockController() {
        return stockController;
    }

    public void setStockController(StockController stockController) {
        this.stockController = stockController;
    }

    public ItemBincardController getItemBincardController() {
        return itemBincardController;
    }

    public void setItemBincardController(ItemBincardController itemBincardController) {
        this.itemBincardController = itemBincardController;
    }

    public void setCurrentSystemNumber(SystemNumbers currentSystemNumber) {
        this.currentSystemNumber = currentSystemNumber;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
    }

    public Document getCurrentDocument() {
        return currentDocument;
    }

    public void setCurrentDocument(Document currentDocument) {
        this.currentDocument = currentDocument;
    }

    public LocationController getLocationController() {
        return locationController;
    }

    public void setLocationController(LocationController locationController) {
        this.locationController = locationController;
    }

    public void setItemsController(ItemsController itemsController) {
        this.itemsController = itemsController;
    }

    public void setJobController(JobController jobController) {
        this.jobController = jobController;
    }

    public Job getCurrent() {
        return current;
    }

    public String preapareList(Job job) {
        setCurrent(job);
        System.out.println("Job Set " + job);
        SessionDataHelper.setFlash(flashString, getCurrent());
        SessionDataHelper.setFlash(isUpdateMode, true);
        return "Update";
    }

    public String getDocumentNumber() {
        setCurrentSystemNumber(null);
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get(AlphaConstant.SESSION_USER);
        if (loggedUser != null && getCurrent().getRelatedLocation() != null) {
            List<SystemNumbers> systemNumbers = getSystemNumbersController().findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getRelatedLocation(), getCurrentDocument());
            if (systemNumbers != null && !systemNumbers.isEmpty()) {
                setCurrentSystemNumber(systemNumbers.get(0));
                getCurrent().setJobNumber(getCurrentSystemNumber().getDocumentSystemNo());
            }
        }
        return getCurrentSystemNumber() != null ? getCurrentSystemNumber().getDocumentSystemNo() : "";
    }

    public void setDocumentNumber(String documentNumber) {
        getCurrent().setJobNumber(documentNumber);
    }

    public void setMeterial(Items item) {
        getJobDetails().setMeterial(item);
    }

    public Items getMeterial() {
        return (Items) getJobDetails().getMeterial();
    }

    public void setCurrent(Job current) {
        this.current = current;
    }

    public List<Job> getList() {
        return jobController.findAll();
    }

    public JobDetailsController getJobDetailsController() {
        return jobDetailsController;
    }

    public void setJobDetailsController(JobDetailsController jobDetailsController) {
        this.jobDetailsController = jobDetailsController;
    }

    public JobDetails getJobDetails() {
        return jobDetails;
    }

    public void handleLocationSelect(SelectEvent event) {
        MessageHelper.addSuccessMessage("Handle location Select" + current.getRelatedLocation());

    }

    public void setJobDetails(JobDetails jobDetails) {
        this.jobDetails = jobDetails;
    }

    public List<Items> autoCompleteItems(String query) {
        return getItemsController().findLike(query, getCurrent().getRelatedLocation() != null ? getCurrent().getRelatedLocation() : getLocationController().find(1L));
    }

    public void handleSelect(SelectEvent event) {
        MessageHelper.addSuccessMessage("Handle Select" + getMeterial());
        getJobDetails().setMeterial(getMeterial());
        getJobDetails().setItemCost(getMeterial().getItemCost());
    }

    public double getTotal() {
        double TotalValue = 0;
        for (JobDetails job : getJobDetailList()) {
            TotalValue = TotalValue + (job.getItemCost() * job.getQty());
        }
        return TotalValue;
    }

    public void onEdit(RowEditEvent event) {

        if (((JobDetails) event.getObject()).getMeterial() instanceof Items) {
            Items tempItem = ((Items) ((JobDetails) event.getObject()).getMeterial());
            JobDetails tempJobDetails = ((JobDetails) event.getObject());
            MessageHelper.addSuccessMessage(((Items) ((JobDetails) event.getObject()).getMeterial()).getItemCode() + " Updated!");
            for (JobDetails jobDetail : getJobDetailList()) {
                if (Objects.equals(((Items) jobDetail.getMeterial()).getId(), tempItem.getId())) {
                    jobDetail.setQty(tempJobDetails.getQty());
                    jobDetail.setItemCost(tempJobDetails.getItemCost());
                }
            }
        }

        getCurrent().setTotalAmount(getTotal());
    }

    public void onCancel(RowEditEvent event) {
        MessageHelper.addSuccessMessage(((GRNDetails) event.getObject()).getGrnItem().getItemCode() + "Not Updated!");

    }

    public String removeSelectedItem() {
        if (getSelectedJobDetail() == null) {
            MessageHelper.addErrorMessage("No Item Selected", "Please select an item to Remove");
            return "";
        } else {
            for (int a = 0; a < getJobDetailList().size(); a++) {
                if (getJobDetailList().get(a) == getSelectedJobDetail()) {
                    getJobDetailList().remove(a);
                    break;
                }
            }
            getCurrent().setTotalAmount(getTotal());
        }
        return "#";
    }

    public void addItem() {
        boolean isFound = false;
        if (getJobDetails().getMeterial() == null || getJobDetails().getItemCost() == 0 || getJobDetails().getQty() == 0) {
            MessageHelper.addErrorMessage("Item Information Missing", "Please Fill All Details");
            return;
        }
        getJobDetails().setRelatedJob(getCurrent());
        for (JobDetails jobDetail : getJobDetailList()) {
            if (Objects.equals(((Items) jobDetail.getMeterial()).getId(), ((Items) getJobDetails().getMeterial()).getId())) {
                jobDetail.setQty(jobDetail.getQty() + getJobDetails().getQty());
                isFound = true;
                break;
            }

        }
        if (!isFound) {
            getJobDetailList().add(getJobDetails());
        }

        getCurrent().setTotalAmount(getTotal());
        setJobDetails(new JobDetails());

    }

    public String editJob() {
        getJobController().edit(getCurrent());
        return "Home";
    }

    public String createJob() {

        Logger logger = EntityHelper.createLogger("Create " + getCurrentDocument().getDocumentDisplayName(), getCurrent().getJobNumber(), TransactionTypes.JOB);
        getLoggerController().create(logger);
        getCurrent().setLogger(logger);
        for (JobDetails jobDetail : getJobDetailList()) {

            //Update Stock
            Stock stock = getStockController().getItemStock(getCurrent().getRelatedLocation(), (Items) jobDetail.getMeterial(), getCurrent().getBillStatus());
            stock.setStockLocation(getCurrent().getRelatedLocation());
            stock.setSockItem((Items) jobDetail.getMeterial());
            stock.setStockQty(stock.getStockQty() - jobDetail.getQty());
            stock.setRelatedCompany(SessionDataHelper.getLoggedCompany(true));
            getStockController().edit(stock);

            //Item Bincard Entry 
            ItemBincard itemBin = DocumentEntityHelper.createItemBincardEntry(logger, getCurrent().getRelatedLocation(), getCurrentDocument().getDocumentDisplayName() + " - " + getCurrent(), (Items) jobDetail.getMeterial(), getCurrent().getStartedDate(), getCurrent().toString(), (jobDetail.getQty() * -1), getCurrent().getBillStatus(), stock.getStockQty());
            getItemBincardController().create(itemBin);
            jobDetail.setRelatedJob(getCurrent());

        }
        getCurrent().setJobDetails(getJobDetailList());
        getJobController().create(getCurrent());
        setCurrent(new Job());

        if (getCurrentSystemNumber() != null) {
            getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getIncrementedSystemNumber());
            getSystemNumbersController().edit(getCurrentSystemNumber());
        }

        return "Home";
    }
}
