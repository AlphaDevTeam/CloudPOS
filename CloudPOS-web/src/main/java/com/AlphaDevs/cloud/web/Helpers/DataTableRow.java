
package com.AlphaDevs.cloud.web.Helpers;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

public class DataTableRow {
    
    private String descriptionColumn;
    private String drColumn;
    private String crColumn;

    public DataTableRow() {
    }

    public DataTableRow(String descriptionColumn, String drColumn, String crColumn) {
        this.descriptionColumn = descriptionColumn;
        this.drColumn = drColumn;
        this.crColumn = crColumn;
    }

    public String getDescriptionColumn() {
        return descriptionColumn;
    }

    public void setDescriptionColumn(String descriptionColumn) {
        this.descriptionColumn = descriptionColumn;
    }

    public String getDrColumn() {
        return drColumn;
    }

    public void setDrColumn(String drColumn) {
        this.drColumn = drColumn;
    }

    public String getCrColumn() {
        return crColumn;
    }

    public void setCrColumn(String crColumn) {
        this.crColumn = crColumn;
    }
    
}
