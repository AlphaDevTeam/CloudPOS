/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.JSFBeans;

import com.AlphaDevs.cloud.web.Entities.Design;
import com.AlphaDevs.cloud.web.Entities.Product;
import com.AlphaDevs.cloud.web.SessionBean.DesignController;
import com.AlphaDevs.cloud.web.SessionBean.ProductController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

/**
 *
 * @author AlphaDevs
 */
@ManagedBean
@ViewScoped
public class ProductMindMapHandler {
    @EJB
    private DesignController designController;
    @EJB
    private ProductController productController;

    private Product currentProduct;
    
    private MindmapNode root;

    public DesignController getDesignController() {
        return designController;
    }

    public void setDesignController(DesignController designController) {
        this.designController = designController;
    }

    public ProductController getProductController() {
        return productController;
    }

    public void setProductController(ProductController productController) {
        this.productController = productController;
    }

    public MindmapNode getRoot() {
        return root;
    }

    public void setRoot(MindmapNode root) {
        this.root = root;
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
        root = new DefaultMindmapNode("Product", "Product", "FFCC00", false);
        List<Design> relatedDesign = designController.findSpecific(getCurrentProduct());
        for (Design design : relatedDesign) {
            System.out.println("Design Loaded : " + design);
            MindmapNode designNode = new DefaultMindmapNode(design.getDescription(), design.getDesignCode(), "6e9ebf", true);
            root.addNode(designNode);
        }
    }
 
    @PostConstruct
    public void ProductMindMapHandlerInit() {
        root = new DefaultMindmapNode("Product", "Product", "FFCC00", false);
        List<Design> relatedDesign = designController.findSpecific(getCurrentProduct());
        for (Design design : relatedDesign) {
            System.out.println("Design Loaded : " + design);
            MindmapNode designNode = new DefaultMindmapNode(design.getDescription(), design.getDesignCode(), "6e9ebf", true);
            root.addNode(designNode);
        }
        
    }
    
}
