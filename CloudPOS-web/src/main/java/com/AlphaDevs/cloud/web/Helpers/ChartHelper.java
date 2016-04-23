
package com.AlphaDevs.cloud.web.Helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.chart.MeterGaugeChartModel;  

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */



@ManagedBean
@RequestScoped
public class ChartHelper implements Serializable {
    
    private MeterGaugeChartModel meterGaugeModel;  
        
    public ChartHelper() {  
    }  
  
    public MeterGaugeChartModel getChart(double value,double reOrderLevel,double capacity ) {
        
        final Number reOrder = (Number) reOrderLevel;
        final Number max = (Number)((capacity > 0) ? capacity : value + 100 );
        final Number valueAsNumber = (Number)value;
        List<Number> reOrderLevels = new ArrayList<Number>(){{  
            add(reOrder);
            add(max);  
        }};  
        
        List<Number> tickelList = new ArrayList<Number>(){{  
            add(valueAsNumber);
            add(10);  
        }};  
    
        meterGaugeModel = new MeterGaugeChartModel(value, reOrderLevels); 
        return meterGaugeModel;
    }

}
