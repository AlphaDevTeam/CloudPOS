/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.Servlets;

import com.AlphaDevs.cloud.web.Entities.CashBook;
import com.AlphaDevs.cloud.web.SessionBean.CashbookController;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Home
 */
@WebServlet(name = "ReportView", urlPatterns = {"/ReportView"})
public class ReportView extends HttpServlet {
    
    @EJB
    private CashbookController cashbookController;

    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try 
        {
            JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(cashbookController.findAll());
            
            String reportPath = getServletContext().getRealPath("/Reports/CashBook.jasper");
            System.out.println("Path Servlet: " + reportPath);
            JasperPrint jasPrint =  JasperFillManager.fillReport(reportPath, new HashMap(), beanCollection);
            
            response.setContentType("application/pdf");
            String filename = new StringBuffer(reportPath).append(".pdf").toString();  
            ServletOutputStream output = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasPrint, output);
            response.addHeader("Content-Disposition", "inline; filename=CashBook.pdf");  
            
            System.out.println("Servlet : Report Done");
        } 
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {            
            
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
