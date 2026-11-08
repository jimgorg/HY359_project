/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import database.tables.EditBookingsTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class Finish extends HttpServlet {

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
            out.println("<title>Servlet Finish</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Finish at " + request.getContextPath() + "</h1>");
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
        
        EditBookingsTable ebt = new EditBookingsTable();
        
        JSON_Converter jc = new JSON_Converter();
        
        String json = jc.getJSONFromAjax(request.getReader());
        System.out.println("This is finish servlet");
        
        
        
        JSONObject obj = new JSONObject(json);
        
        
        String owner_id = obj.getString("owner_id");
        String keeper_id = obj.getString("keeper_id");
        String pet_id = obj.getString("pet_id");
        String fromDate = obj.getString("fromDate");
        String toDate = obj.getString("toDate");
        String status = "finished";
        int price;
        System.out.println("\nthe id of the keeper is : " + keeper_id + "\nowner id is : " + owner_id);

//        booking.put("owner_id", owner_id);
//        booking.put("keeper_id", keeper_id);
//        booking.put("pet_id", pet_id);
//        booking.put("fromDate", fromDate);
//        booking.put("toDate", toDate);
//        finish.put("status", status);
        
        try {
            response.getWriter().write("Accommodation ended.<br>Now if you would like you can review your servise.");
            String booking = ebt.databaseBookingToJSONFinish(fromDate, toDate, keeper_id, owner_id);
            System.out.println("the jason from finish is :"+booking);
//            String booking = ebt.databaseBookingToJSONNoPassword(keeper_id);
            JSONObject boo = new JSONObject(booking);
            
            String book_id = boo.getString("booking_id");
            int booking_id = Integer.valueOf(book_id);
            
            
//            int booking_id = book.

//            System.out.println("booking id is : " + booking_id);
            
            ebt.updateBooking(booking_id, status);
            
            
                    
            } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Finish.class.getName()).log(Level.SEVERE, null, ex);
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
