/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import database.tables.EditBookingsTable;
import database.tables.EditPetKeepersTable;
import database.tables.EditPetOwnersTable;
import database.tables.EditPetsTable;
import database.tables.EditReviewsTable;
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
import mainClasses.Booking;
import mainClasses.JSON_Converter;
import mainClasses.Pet;
import mainClasses.PetKeeper;
import mainClasses.PetOwner;
import mainClasses.Review;
import org.json.JSONObject;

/**
 *
 * @author jgorg
 */
public class AdminDeleteKeeper extends HttpServlet {

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
            out.println("<title>Servlet AdminDeleteKeeper</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminDeleteKeeper at " + request.getContextPath() + "</h1>");
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
        
         EditPetsTable ept = new EditPetsTable();
        EditPetOwnersTable eot = new EditPetOwnersTable();
        EditPetKeepersTable eut = new EditPetKeepersTable();
        EditBookingsTable ebt = new EditBookingsTable();
        EditReviewsTable ert = new EditReviewsTable();
        
        ArrayList<PetKeeper> availablekeepers = new ArrayList();
        ArrayList<Booking> availablebookings = new ArrayList();
        ArrayList<Review> availablereviews = new ArrayList();
        
        JSON_Converter jc = new JSON_Converter();
        
        String json = jc.getJSONFromAjax(request.getReader());
        System.out.println(json);
        
        JSONObject obj = new JSONObject(json);
        
        
        String user_id = obj.getString("user_id");
        String username = obj.getString("username");
        String email = obj.getString("email");
        
        System.out.println("user_id = "+user_id+" username ="+username+" email ="+email);
        try {
//            availablekeepers = eut.getAvailableKeepersAll();
            availablekeepers = eut.getKeepersAll();
//            availablebooking = ebt.getBookings(id)
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AdminDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        for(int i=0 ; i<availablekeepers.size() ; i++){
            PetKeeper p = availablekeepers.get(i);
            System.out.println("username :"+p.getUsername()+" email "+p.getEmail());
            if(p.getUsername().equals(username) && p.getEmail().equals(email)){
                response.getWriter().write("<br>Removing pet keeper from tables..<br><br>");
                System.out.println("Removing pet keeper from tables..");
                try {
                    JSONObject keeper = new JSONObject(eut.databasePetKeeperToJSONFromEmail(username, email));
                    String keeper_id = keeper.getString("keeper_id");
                    System.out.println("keeper_id = "+keeper_id);
                    
                    System.out.println("this is json from database _> "+ebt.databaseBookingToJSONFromKeeper(keeper_id));
                    String book = ebt.databaseBookingToJSONFromKeeper(keeper_id);
                    System.out.println("bookfromkeeper is : "+book);
                    
                    
                    if(book != null && !book.isEmpty()){
                        JSONObject boo = new JSONObject(book);
                        String booking_id = boo.getString("booking_id");
                        System.out.print("booking id = "+booking_id);
                        
                        ebt.deleteBooking(booking_id);
                        response.getWriter().write("<br>Removed booking from table<br>");
                        
                        availablereviews = ert.databaseTokeeperReviews(keeper_id);
                        for(int j=0 ; j<availablereviews.size() ; j++){
                            ert.deleteReviewsFromKeeper(keeper_id);
                        }
                        response.getWriter().write("<br>Removed reviews from table<br>");
                    }
                    
//                    ert.deleteReviewsFromKeeper(keeper_id);
//                    availablereviews = ert.databaseTokeeperReviews(keeper_id);
//                    for(int j=0 ; j<availablereviews.size() ; j++){
//                        ert.deleteReviewsFromKeeper(keeper_id);
//                    }

//                    ert.deleteReviewsFromKeeper(keeper_id);
//                    response.getWriter().write("<br>Removed reviews from table<br>");
                    
                    eut.deletePetKeeper(username, email);
                    response.getWriter().write("<br>Removed pet keeper from table<br>");
                    
                    
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(AdminDeleteKeeper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
