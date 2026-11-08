/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import database.tables.EditBookingsTable;
import database.tables.EditMessagesTable;
import database.tables.EditPetKeepersTable;
import database.tables.EditPetOwnersTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Booking;
import mainClasses.JSON_Converter;
import org.json.JSONObject;

/**
 *
 * @author jgorg
 */
public class MessageToKeeper extends HttpServlet {

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
            out.println("<title>Servlet MessageToOwner</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MessageToOwner at " + request.getContextPath() + "</h1>");
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
        
        EditMessagesTable emt = new EditMessagesTable();
        EditBookingsTable ebt = new EditBookingsTable();
        EditPetOwnersTable eot = new EditPetOwnersTable();
        
        JSON_Converter jc = new JSON_Converter();
        
        String json = jc.getJSONFromAjax(request.getReader());
        
        JSONObject obj = new JSONObject(json);
        
        String username = obj.getString("username");
        String keeper_id = obj.getString("keeper_id");
        String booking_id = obj.getString("booking_id");
        String message = obj.getString("messageowner");
        String sender = "owner";
        
        DateTimeFormatter datetime = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        
        int book_id = Integer.parseInt(booking_id);
        
        try {
            
            String owner =eot.databasePetOwnerToJSONFromUsername(username);
            JSONObject objowner = new JSONObject(owner);
            String owner_id = objowner.getString("owner_id");
            obj.remove("username");
            obj.put("owner", owner_id);
            
            Booking booking = ebt.databaseToBooking(book_id);
            
            String status = booking.getStatus();
            
            
            JSONObject objmes = new JSONObject();
            objmes.put("booking_id", booking_id);
            objmes.put("message", message);
            objmes.put("sender", sender);
            objmes.put("datetime", datetime.format(now));
            
            if(status.equals("accepted")){
                emt.addMessageFromJSON(objmes.toString());
                response.getWriter().write("<br>Message sent!");
            }
            else{
                response.getWriter().write("Yoy cannot messa pet owner, the booking is not active.");
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MessageToKeeper.class.getName()).log(Level.SEVERE, null, ex);
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
