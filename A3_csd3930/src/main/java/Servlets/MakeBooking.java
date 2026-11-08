/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import database.tables.EditBookingsTable;
import database.tables.EditPetKeepersTable;
import database.tables.EditPetOwnersTable;
import database.tables.EditPetsTable;
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
import mainClasses.Pet;
import mainClasses.PetKeeper;
import org.json.JSONObject;

/**
 *
 * @author jgorg
 */
public class MakeBooking extends HttpServlet {

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
            out.println("<title>Servlet MakeBooking</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MakeBooking at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);

        EditPetsTable ept = new EditPetsTable();
        EditPetOwnersTable eot = new EditPetOwnersTable();
        EditPetKeepersTable eut = new EditPetKeepersTable();
        EditBookingsTable ebt = new EditBookingsTable();
        
        
        JSON_Converter jc = new JSON_Converter();
        
        String json = jc.getJSONFromAjax(request.getReader());
        System.out.println(json);
        
        
        
        JSONObject obj = new JSONObject(json);
        JSONObject booking  = new JSONObject();
        
        
//        String username = obj.getString("username");
//        String password = obj.getString("password");
        String owner_id = obj.getString("owner_id");
        String keeper_id = obj.getString("keeper_id");
        String pet_id = obj.getString("pet_id");
        String fromDate = obj.getString("fromDate");
        String toDate = obj.getString("toDate");
        String status = "requested";
        int price;
        System.out.println("\nthe id of the keeper is : " + keeper_id + "\nkeeper id is : " + owner_id);

        booking.put("owner_id", owner_id);
        booking.put("keeper_id", keeper_id);
        booking.put("pet_id", pet_id);
        booking.put("fromDate", fromDate);
        booking.put("toDate", toDate);
        booking.put("status", status);
        
        
        
        
        LocalDate start = LocalDate.parse(fromDate);
        LocalDate end   = LocalDate.parse(toDate);
        System.out.println("\nstart = "+start+" \nend = "+end);

        long days = ChronoUnit.DAYS.between(start, end);

        System.out.println(days);  
        
//        ArrayList<Booking> check = new ArrayList<Booking>();
        
br :try {
            String check = ebt.databaseBookingToJSONNoPassword(keeper_id);
            System.out.println("check is :" + check) ;
            if(check != null && !check.isEmpty()){
                JSONObject objcheck = new JSONObject(check);
                String contains = objcheck.getString("status");
                String checkfrom = objcheck.getString("fromdate");
                String checkto = objcheck.getString("todate");
                LocalDate checkstart = LocalDate.parse(checkfrom);
                LocalDate checkend = LocalDate.parse(checkto);
                System.out.println("\ncheckstart = "+checkstart+" \ncheckkend = "+checkend);
                if(contains.equals("requested")){
//                    if(start.isAfter(checkstart) && start.isBefore(checkend)){
                    if((start.compareTo(checkstart)>0 && end.compareTo(checkend)<0) || start.isEqual(checkstart) || end.equals(checkend) ) {
                        
                        response.getWriter().write("<br>keeper has an active booking and cannot keep your pet in that period");
                        break br;
                    }                   
                }
            }
            
          
            
             
            String keeper = eut.databasePetKeeperToJSONNoPassword(keeper_id);
            JSONObject objkeeper = new JSONObject(keeper);
            int catprice = objkeeper.getInt("catprice");
            int dogprice = objkeeper.getInt("dogprice");
            String catkeeper = objkeeper.getString("catkeeper");
            String dogkeeper = objkeeper.getString("dogkeeper");
            
            String petofowner = ept.databasePetsToJSONNoPassword(pet_id);
            JSONObject objpettype = new JSONObject(petofowner);
            String pettype = objpettype.getString("type");
            System.out.println("pet type isss : " + pettype);
            if(pettype.equals("cat")){
                if(catkeeper.equals("null" )|| catkeeper.equals("false")){
                    response.getWriter().write("<br>keeper only keeps dogs cannot take your pet");
                    break br;
                }
                price = (int)days*catprice;
                System.out.println("\ndays : "+days+"\ncatprice : "+catprice+ "\nPRICE = "+price);
                
            }
            else{
                if(dogkeeper.equals("null" )|| dogkeeper.equals("false")){
                    response.getWriter().write("<br>keeper only keeps cats cannot take your pet");
                    break br;
                }
                price = (int)days*dogprice;
                System.out.println("\ndays : "+days+"\ndogprice : "+dogprice+ "\nPRICE = "+price);
            }
            
            booking.put("price", price);
            String jsonbooking = booking.toString();
            ebt.addBookingFromJSON(jsonbooking);
            response.getWriter().write("<br>Successfull booking for pet with pet_id = "+pet_id+" of owner with owner_id = "+owner_id+" . <br>Keeper with keeper_id = "+keeper_id+ " will gladly welcome you .<br>Price = "+price);
//            String keeperjson = eut.databasePetKeeperToJSONNoPassword(obj.getString("keeper_id"));
//            JSONObject keeper = new JSONObject(keeperjson);
//            String keeper_id = keeper.getString("keeper_id");
        } catch (SQLException ex) {
            Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MakeBooking.class.getName()).log(Level.SEVERE, null, ex);
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
