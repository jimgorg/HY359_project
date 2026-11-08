/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import database.tables.EditBookingsTable;
import database.tables.EditPetKeepersTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class KeeperViewPendingRequests extends HttpServlet {

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
            out.println("<title>Servlet KeeperViewPendingRequests</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet KeeperViewPendingRequests at " + request.getContextPath() + "</h1>");
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
        
        JSON_Converter jc = new JSON_Converter();
        String json = jc.getJSONFromAjax(request.getReader());
        System.out.println("json is :"+ json );
        JSONObject obj = new JSONObject(json);
        
        EditBookingsTable ebt = new EditBookingsTable();
        EditPetKeepersTable eut = new EditPetKeepersTable();
        
        String username = obj.getString("username");
        System.out.println("username : "+username);
        String booking_id = obj.getString("booking_id");
        String status = "requested";
        
        ArrayList<String> bookings = new ArrayList<String>();
        
        try {
            String keeper = eut.databasePetKeeperToJSONFromUsername(username);
            System.out.println("keeper from username : "+ keeper);
            
            JSONObject objkeeper = new JSONObject(keeper);
            String keeper_id = objkeeper.getString("keeper_id");
            System.out.println("keeper_id : "+keeper_id);
            
            bookings = ebt.getBookingsForKeeperRequests(keeper_id, status);
            
            for(int i=0 ; i<bookings.size() ; i++ ){
                response.getWriter().write("<br>"+bookings.get(i)+"<br>");
            }
            
//            response.getWriter().write(ebt.databaseBookingToJSONForKeeperRequests(keeper_id,status));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(KeeperViewPendingRequests.class.getName()).log(Level.SEVERE, null, ex);
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
