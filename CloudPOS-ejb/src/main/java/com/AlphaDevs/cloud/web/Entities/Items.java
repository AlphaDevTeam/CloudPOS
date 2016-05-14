
package com.AlphaDevs.cloud.web.Entities;

import com.AlphaDevs.cloud.web.Interfaces.Meterials;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@Entity
public class Items implements Serializable ,Meterials
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String ItemCode;
    private String ItemName;
    private String ItemBarcode;
    private String ItemDescription;
    @OneToOne
    private Units unitOfMeasurement;
    
    @OneToOne
    private Product ItemProduct;
    
    @OneToOne
    private Design ItemDesign;
    
    @OneToOne
    private Supplier ItemSupplier;
    
    private double ItemCost;
    private double UnitPrice;
    private double ReorderLevel;
    @OneToOne
    private Location ItemLocation;
    
    @OneToOne
    private Logger Logger;
    private double capacity;

    public Items() {
    }

    public Items(String ItemCode, String ItemName, String ItemBarcode, String ItemDescription, Units unitOfMeasurement, Product ItemProduct, Design ItemDesign, Supplier ItemSupplier, double ItemCost, double UnitPrice, double ReorderLevel, Location ItemLocation, Logger Logger, double capacity) {
        this.ItemCode = ItemCode;
        this.ItemName = ItemName;
        this.ItemBarcode = ItemBarcode;
        this.ItemDescription = ItemDescription;
        this.unitOfMeasurement = unitOfMeasurement;
        this.ItemProduct = ItemProduct;
        this.ItemDesign = ItemDesign;
        this.ItemSupplier = ItemSupplier;
        this.ItemCost = ItemCost;
        this.UnitPrice = UnitPrice;
        this.ReorderLevel = ReorderLevel;
        this.ItemLocation = ItemLocation;
        this.Logger = Logger;
        this.capacity = capacity;
    }

    public Items(String ItemDescription) {
        this.ItemDescription = ItemDescription;
    }

    public Items(String ItemCode, String ItemName) {
        this.ItemCode = ItemCode;
        this.ItemName = ItemName;
    }

    public Items(String ItemName, double UnitPrice) {
        this.ItemName = ItemName;
        this.UnitPrice = UnitPrice;
    }
    
        

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public Units getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(Units unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getItemBarcode() {
        return ItemBarcode;
    }

    public com.AlphaDevs.cloud.web.Entities.Logger getLogger() {
        return Logger;
    }

    public void setLogger(com.AlphaDevs.cloud.web.Entities.Logger Logger) {
        this.Logger = Logger;
    }

    public void setItemBarcode(String ItemBarcode) {
        this.ItemBarcode = ItemBarcode;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String ItemCode) {
        this.ItemCode = ItemCode;
    }

    public double getItemCost() {
        return ItemCost;
    }

    public void setItemCost(double ItemCost) {
        this.ItemCost = ItemCost;
    }

    public String getItemDescription() {
        return ItemDescription;
    }

    public void setItemDescription(String ItemDescription) {
        this.ItemDescription = ItemDescription;
    }

    public Design getItemDesign() {
        return ItemDesign;
    }

    public void setItemDesign(Design ItemDesign) {
        this.ItemDesign = ItemDesign;
    }

    public Location getItemLocation() {
        return ItemLocation;
    }

    public void setItemLocation(Location ItemLocation) {
        this.ItemLocation = ItemLocation;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public Product getItemProduct() {
        return ItemProduct;
    }

    public void setItemProduct(Product ItemProduct) {
        System.out.println("Item Prod Set");
        this.ItemProduct = ItemProduct;
    }

    public Supplier getItemSupplier() {
        return ItemSupplier;
    }

    public void setItemSupplier(Supplier ItemSupplier) {
        this.ItemSupplier = ItemSupplier;
    }

    public double getReorderLevel() {
        return ReorderLevel;
    }

    public void setReorderLevel(double ReorderLevel) {
        this.ReorderLevel = ReorderLevel;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }
    
    
    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ItemName;
    }

}
