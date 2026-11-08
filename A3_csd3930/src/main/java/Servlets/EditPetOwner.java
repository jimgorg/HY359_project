/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import database.tables.EditPetOwnersTable;
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
public class EditPetOwner extends HttpServlet {

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
            out.println("<title>Servlet EditPetOwner</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditPetOwner at " + request.getContextPath() + "</h1>");
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
       
        response.setContentType("text/plain");               
        response.setCharacterEncoding("UTF-8");
        
        JSON_Converter jc = new JSON_Converter();
        
        String json = jc.getJSONFromAjax(request.getReader());
        System.out.println(json);
        
        Double lon = 25.0722869;
        Double lat = 35.3053121;
        
        JSONObject obj = new JSONObject(json);

        
        String username = obj.get("username").toString();
        String email = obj.get("email").toString();
        String password = obj.get("password").toString();
        String firstname = obj.get("firstname").toString();
        String lastname = obj.get("lastname").toString();
        String birthdate = obj.get("birthdate").toString();
        String gender = obj.get("gender").toString();
        String job = obj.get("job").toString();
//        String lat = obj.get("lat").toString();
//        String lon = obj.get("lon").toString();        
        String country = obj.get("country").toString();
        String city = obj.get("city").toString();
        String address = obj.get("address").toString();
        String telephone = obj.get("telephone").toString();
        String personalpage = obj.get("personalpage").toString();
        
        
        EditPetOwnersTable eot = new EditPetOwnersTable();
        try {
            eot.updatePetOwnerPersonalPage(username, personalpage);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditPetOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            eot.updatePetOwnerTelephone(username, telephone);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditPetOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            eot.updatePetOwnerEmail(username, email);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditPetOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            eot.updatePetOwnerPassword(username, password);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditPetOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            eot.updatePetOwnerFirstname(username, firstname);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditPetOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            eot.updatePetOwnerLastname(username, lastname);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditPetOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            eot.updatePetOwnerBirthdate(username, birthdate);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditPetOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            eot.updatePetOwnerGender(username, gender);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditPetOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            eot.updatePetOwnerJob(username, job);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditPetOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            eot.updatePetOwnerAddress(username, address);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditPetOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            eot.updatePetOwnerCountry(username, country);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditPetOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            eot.updatePetOwnerCity(username, city);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditPetOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            eot.updatePetOwnerLat(username, lat);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditPetOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            eot.updatePetOwnerLon(username, lon);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditPetOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.getWriter().write("Updated pet owner");
        
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
