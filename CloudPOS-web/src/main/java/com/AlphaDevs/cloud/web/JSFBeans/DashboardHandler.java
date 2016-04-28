/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.GRN;
import com.AlphaDevs.cloud.web.Entities.Location;
import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import com.AlphaDevs.cloud.web.Helpers.SessionDataHelper;
import com.AlphaDevs.cloud.web.SessionBean.GRNController;
import com.AlphaDevs.cloud.web.SessionBean.InvoiceController;
import com.AlphaDevs.cloud.web.SessionBean.LocationController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Tuple;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Mihindu Karunarathne
 */
@ManagedBean
@SessionScoped
public class DashboardHandler {
    @EJB
    private GRNController gRNController;
    @EJB
    private LocationController locationController;

    
    @EJB
    private InvoiceController invoiceController;
    
    

    private DashboardModel model;
    private LineChartModel animatedSalesModel;

    @PostConstruct
    public void init() {
        model = new DefaultDashboardModel();
        DashboardColumn sales = new DefaultDashboardColumn();
        sales.addWidget("sales");
        model.addColumn(sales);
       

    }
    
    public InvoiceController getInvoiceController() {
        return invoiceController;
    }

    public GRNController getgRNController() {
        return gRNController;
    }

    public void setgRNController(GRNController gRNController) {
        this.gRNController = gRNController;
    }

    public LocationController getLocationController() {
        return locationController;
    }

    public void setLocationController(LocationController locationController) {
        this.locationController = locationController;
    }
    
    public void setInvoiceController(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }

    public LineChartModel getAnimatedSalesModel() {
        initSalesChart();
        return animatedSalesModel;
    }

    public void setAnimatedSalesModel(LineChartModel animatedSalesModel) {
        this.animatedSalesModel = animatedSalesModel;
    }

    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());

        addMessage(message);
    }

    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");

        addMessage(message);
    }

    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public DashboardModel getModel() {
        return model;
    }

    public void initSalesChart() {
        animatedSalesModel = initLinearModel();
        animatedSalesModel.setTitle("Zoom for Details");
        animatedSalesModel.setZoom(true);
        animatedSalesModel.setAnimate(true);
        animatedSalesModel.getAxis(AxisType.Y).setLabel("Values");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        SimpleDateFormat format = new SimpleDateFormat(SessionDataHelper.getSystems() != null ? SessionDataHelper.getSystems().getDateTimeformat() : AlphaConstant.yyyy_MM_dd );
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DATE, +2);
        axis.setMax(format.format(today.getTime()));
        axis.setTickFormat("%b %#d, %y");
         
        animatedSalesModel.getAxes().put(AxisType.X, axis);
//        
//        animatedSalesModel = initLinearModel();
//        animatedSalesModel.setTitle("Sales Chart");
//        animatedSalesModel.setAnimate(true);
//        animatedSalesModel.setLegendPosition("se");
//        Axis yAxis = animatedSalesModel.getAxis(AxisType.Y);
//        yAxis.setMin(0);
//        yAxis.setMax(65000);
    }

    private LineChartModel initLinearModel() {

        LineChartModel tmpModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();

        Calendar today = Calendar.getInstance();
        Calendar weekBeforeToday = Calendar.getInstance();
        weekBeforeToday.add(Calendar.DATE, -7);
        
        Location find = locationController.find(1L);
        List<Tuple> findInvoicesByDateAggrigate = getInvoiceController().findInvoicesByDateAggrigate(weekBeforeToday.getTime(),today.getTime(),find);
        int a = 0;
        SimpleDateFormat format = new SimpleDateFormat(SessionDataHelper.getSystems() != null ? SessionDataHelper.getSystems().getDateTimeformat() : AlphaConstant.yyyy_MM_dd);
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        //DateFormat formatter = new SimpleDateFormat(SessionDataHelper.getSystems() != null ? SessionDataHelper.getSystems().getDateTimeformat() : AlphaConstant.yyyy_MM_dd);
//        Date inputDate = dateFormat.parse(input);
        for (Tuple invoice : findInvoicesByDateAggrigate) {
            try {
                System.out.println("Dashboard - " +  invoice.get(0) + " - " + invoice.get(1) + " - " + format.format(formatter.parse(invoice.get(2).toString())));
            } catch (ParseException ex) {
                Logger.getLogger(DashboardHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                series1.set(format.format(formatter.parse(invoice.get(2).toString())),Double.valueOf(invoice.get(0).toString()));
                series2.set(format.format(formatter.parse(invoice.get(2).toString())),Double.valueOf(invoice.get(0).toString())-1000);
            } catch (ParseException ex) {
                Logger.getLogger(DashboardHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        List<GRN> findByDateRange = getgRNController().findByDateRange(weekBeforeToday.getTime(),today.getTime(),find);
        for (GRN findByDateRange1 : findByDateRange) {
             try {
                series3.set(format.format(formatter.parse(findByDateRange1.getGrnDate().toString())),findByDateRange1.getTotalAmount());
            } catch (ParseException ex) {
                Logger.getLogger(DashboardHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        
//        series1 = new LineChartSeries();
//            series1.set(1, 1.75);
//            series1.set(2, 2.75);
//            series1.set(1.75, 1.75);
//            series1.set(10, 11.75);
//            series1.set(11, 25.75);
            
            tmpModel.addSeries(series1);
            tmpModel.addSeries(series2);
            tmpModel.addSeries(series3);
        
        System.out.println("Date Used : " + today.getTime() + " - " + weekBeforeToday.getTime() + " With Location :" + find);
        series1.setLabel("Series 1");
        series2.setLabel("Series 2");
        series3.setLabel("Series 3");
        return tmpModel;
    }

}
