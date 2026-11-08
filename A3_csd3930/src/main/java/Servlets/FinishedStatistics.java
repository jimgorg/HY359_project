/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import database.tables.EditBookingsTable;
import database.tables.EditPetKeepersTable;
import database.tables.EditReviewsTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Booking;
import mainClasses.JSON_Converter;
import mainClasses.Review;
import org.json.JSONObject;

/**
 *
 * @author jgorg
 */
public class FinishedStatistics extends HttpServlet {

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
            out.println("<title>Servlet FinishedStatistics</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FinishedStatistics at " + request.getContextPath() + "</h1>");
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
//        
//        EditBookingsTable ebt = new EditBookingsTable();
//        EditReviewsTable ert = new EditReviewsTable();
//        EditPetKeepersTable eut = new EditPetKeepersTable();
//        
//        ArrayList<String> bookings = new ArrayList();
//        ArrayList<String> reviews = new ArrayList();
//        
//        String status = "finished";
//        Integer numOfBookings=0;
//        long sumOfDays=0;
//        Integer income=0;
//        long daysOfBooking=0;
//        
//        
////        JSON_Converter jc = new JSON_Converter();
////        String json = jc.getJSONFromAjax(request.getReader());
////        JSONObject obj = new JSONObject(json);
////        
////        String username =  obj.getString("username");
////        String keeper;
//     
//            
//        
//        
//        try {
//            JSON_Converter jc = new JSON_Converter();
//            String json = jc.getJSONFromAjax(request.getReader());
//            
//            System.out.println("JSON : "+json);
//            JSONObject obj = new JSONObject(json);
//
//            String keeper;
//            String username =  obj.getString("username");
//            keeper = eut.databasePetKeeperToJSONFromUsername(username);
//            System.out.println("keeper from username : "+ keeper);
//            
//            JSONObject objkeeper = new JSONObject(keeper);
//            String keeper_id = objkeeper.getString("keeper_id");           
//            String booking_id = obj.getString("booking_id");
//            
//            bookings = ebt.getBookingsForKeeperRequests(keeper_id, status);
//            reviews = ert.databaseTokeeperReviewsJSON(keeper_id);
//            
//            for(int i=0 ; i<bookings.size() ; i++){
//               
//                String boo = bookings.get(i);
//                JSONObject objboo = new JSONObject(boo);
//                
//                numOfBookings ++;
//                
//                LocalDate dayIn = LocalDate.parse(objboo.getString("fromdate"));
//                LocalDate dayOut   = LocalDate.parse(objboo.getString("todate"));
//                daysOfBooking = ChronoUnit.DAYS.between(dayIn, dayOut); 
//                
//                sumOfDays = sumOfDays + daysOfBooking;
//                
//                String price = objboo.getString("price");
//                income = income + Integer.valueOf(price);                
//                
//            }
//            JSONObject fin = new JSONObject();
//            
//            fin.put("numOfBookings", numOfBookings);
//            fin.put("sumOfDays", sumOfDays);
//            fin.put("income", income);
//            
//            for(int i=0 ; i<reviews.size() ; i++){
//                String rev = reviews.get(i);
//                
//                JSONObject objrev = new JSONObject(rev);
//                String owner_id = objrev.getString("owner_id");
//                String reviewScore = objrev.getString("reviewScore");
//                String reviewText = objrev.getString("reviewText");
//                
//                fin.put("owner_id", owner_id);
//                fin.put("reviewScore", reviewScore);
//                fin.put("reviewText", reviewText);
//            }
//            
//            response.getWriter().write(fin.toString());
//             
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(FinishedStatistics.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
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
        EditReviewsTable ert = new EditReviewsTable();
        EditPetKeepersTable eut = new EditPetKeepersTable();
        
        ArrayList<String> bookings = new ArrayList();
        ArrayList<String> reviews = new ArrayList();
        
        String status = "finished";
        Integer numOfBookings=0;
        long daysSummary=0;
        Integer income=0;
        long daysOfBooking=0;
        
        
//        JSON_Converter jc = new JSON_Converter();
//        String json = jc.getJSONFromAjax(request.getReader());
//        JSONObject obj = new JSONObject(json);
//        
//        String username =  obj.getString("username");
//        String keeper;
     
            
        
        
        try {
            JSON_Converter jc = new JSON_Converter();
            String json = jc.getJSONFromAjax(request.getReader());
            
            System.out.println("JSON : "+json);
            JSONObject obj = new JSONObject(json);

            String keeper;
            String username =  obj.getString("username");
            keeper = eut.databasePetKeeperToJSONFromUsername(username);
            System.out.println("keeper from username : "+ keeper);
            
            JSONObject objkeeper = new JSONObject(keeper);
            String keeper_id = objkeeper.getString("keeper_id");           
            String booking_id = obj.getString("booking_id");
            
            System.out.println("1  keeper id : "+keeper_id+ " book_id : "+booking_id);
            
            bookings = ebt.getBookingsForKeeperRequests(keeper_id, status);
            reviews = ert.databaseTokeeperReviewsJSON(keeper_id);
//            if(bookings.size() == 0){
////                String boo = bookings.get(0);
//                String boo = ebt.databaseBookingToJSONForKeeperRequests(keeper_id, status);
//                System.out.println("boo is :"+boo);
//                JSONObject objboo = new JSONObject(boo);
//                
//                numOfBookings ++;
//                
//                LocalDate dayIn = LocalDate.parse(objboo.getString("fromdate"));
//                LocalDate dayOut   = LocalDate.parse(objboo.getString("todate"));
//                daysOfBooking = ChronoUnit.DAYS.between(dayIn, dayOut); 
//                
//                System.out.println("daiIn: "+dayIn+" dayOut : "+dayOut);
//                
//                sumOfDays = sumOfDays + daysOfBooking;
//                
//                String price = objboo.getString("price");
//                income = income + Integer.valueOf(price);
//                System.out.println("price : "+price);
//            }
            for(int i=0 ; i<bookings.size() ; i++){
               
                String boo = bookings.get(i);
                JSONObject objboo = new JSONObject(boo);
                
                numOfBookings ++;
                
                LocalDate dayIn = LocalDate.parse(objboo.getString("fromdate"));
                LocalDate dayOut   = LocalDate.parse(objboo.getString("todate"));
                daysOfBooking = ChronoUnit.DAYS.between(dayIn, dayOut); 
                
                daysSummary = daysSummary + daysOfBooking;
                
                String price = objboo.getString("price");
                income = income +( (85*Integer.valueOf(price))/100);                
                
            }
            JSONObject fin = new JSONObject();
            
            fin.put("numOfBookings", numOfBookings);
            fin.put("daysSummary", daysSummary);
            fin.put("income", income);
            
//            for(int i=0 ; i<reviews.size() ; i++){
//                String rev = reviews.get(i);
//                
//                JSONObject objrev = new JSONObject(rev);
//                String owner_id = objrev.getString("owner_id");
//                String reviewScore = objrev.getString("reviewScore");
//                String reviewText = objrev.getString("reviewText");
//                
//                fin.put("owner_id", owner_id);
//                fin.put("reviewScore", reviewScore);
//                fin.put("reviewText", reviewText);
//            }
            
            response.getWriter().write(fin.toString());
             
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FinishedStatistics.class.getName()).log(Level.SEVERE, null, ex);
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
