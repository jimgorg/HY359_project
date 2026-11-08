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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Booking;
import mainClasses.Pet;
import mainClasses.PetKeeper;
import mainClasses.PetOwner;
import org.json.JSONObject;

/**
 *
 * @author jgorg
 */
public class Statistics extends HttpServlet {

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
            out.println("<title>Servlet Statistics</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Statistics at " + request.getContextPath() + "</h1>");
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
        
        EditPetOwnersTable eot = new EditPetOwnersTable();
        EditPetKeepersTable eut = new EditPetKeepersTable();
        EditPetsTable ept = new EditPetsTable();
        EditBookingsTable ebt = new EditBookingsTable();
        
        ArrayList<PetOwner> owners = new ArrayList();
        ArrayList<PetKeeper> keepers = new ArrayList();
        ArrayList<Pet> pets = new ArrayList();
        ArrayList<Booking> bookings = new ArrayList();
        
        Integer numCats = 0;
        Integer numDogs = 0;
        Integer numPetOwners = 0;
        Integer numPetKeepers = 0;
        Integer petKeeperPrice = 0;
        Integer appPrice = 0;

        
        try {
            owners = eot.getOwners();
            keepers = eut.getAvailableKeepersAll();
            pets = ept.databaseToPets();
            bookings = ebt.getBookingsAll();
            
            for(int i=0 ; i<pets.size() ; i++){
                Pet p = pets.get(i);
                if(p.getType().equals("dog")){
                    numDogs++;
                }
                else{
                    numCats++;
                }
            }
            System.out.println("Cats : "+numCats+"\nDogs : "+numDogs);
            
            for(int i=0 ; i<owners.size() ; i++){
                PetOwner p = owners.get(i);
                numPetOwners++;
            }
            System.out.println("PetOwners : "+numPetOwners);
            
            for(int i=0 ; i<keepers.size() ; i++){
                PetKeeper p = keepers.get(i);
                numPetKeepers++;                
            }
            System.out.println("PetKeepers : "+numPetKeepers);
            
            for(int i=0 ; i<bookings.size() ; i++){
                Booking b = bookings.get(i);
                String status = b.getStatus();
                if(status.equals("finished")){
                    Integer price = b.getPrice();
                    System.out.println("\nPrice = "+price);
                    System.out.println("status :"+status);
                    
                    petKeeperPrice = petKeeperPrice+((price*85)/100);
                    appPrice = appPrice + ((price*15)/100);
                }
            }
            
            JSONObject obj = new JSONObject();
            obj.put("PetKeepers", numPetKeepers);
            obj.put("PetOwners", numPetOwners);
            obj.put("Cats", numCats);
            obj.put("Dogs", numDogs);
            obj.put("PetKeepersEarned", petKeeperPrice);
            obj.put("ApplicationEarned",appPrice);
            
            
            response.getWriter().write(obj.toString());
            
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
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
