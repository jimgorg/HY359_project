/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import database.tables.EditReviewsTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.JSON_Converter;
import org.json.JSONObject;

/**
 *
 * @author jgorg
 */
public class AddReview extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddReview</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddReview at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("hello am review servlet");
        EditReviewsTable ert = new EditReviewsTable();
        JSON_Converter jc = new JSON_Converter();
        
        String json = jc.getJSONFromAjax(request.getReader());
        JSONObject obj = new JSONObject(json);
        JSONObject review = new JSONObject();
        
        String owner_id = obj.getString("owner_id");
        String keeper_id = obj.getString("keeper_id");
        String reviewText = obj.getString("reviewText");
        String reviewScore = obj.getString("reviewScore");
        
        review.put("owner_id", owner_id);
        review.put("keeper_id", keeper_id);
        review.put("reviewText", reviewText);
        review.put("reviewScore", reviewScore);
        
        String jsonreview = review.toString();
        
        try {
            response.getWriter().write("<br>Thank you for your review.");
            ert.addReviewFromJSON(jsonreview);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddReview.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
