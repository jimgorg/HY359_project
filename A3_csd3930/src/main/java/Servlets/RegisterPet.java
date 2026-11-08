/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import database.tables.EditPetKeepersTable;
import database.tables.EditPetOwnersTable;
import database.tables.EditPetsTable;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.max;
import java.sql.SQLException;
import java.util.Random;
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
public class RegisterPet extends HttpServlet {

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
            out.println("<title>Servlet RegisterPet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterPet at " + request.getContextPath() + "</h1>");
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
        
        EditPetsTable ept = new EditPetsTable();
        EditPetOwnersTable eot = new EditPetOwnersTable();
        
        
        
        JSON_Converter jc = new JSON_Converter();
        
        String json = jc.getJSONFromAjax(request.getReader());
        System.out.println(json);
        
        JSONObject obj = new JSONObject(json);
        JSONObject pet = new JSONObject();
        
        String username = obj.getString("ownerusername");
        
        System.out.println("username :"+username);
//        String password = obj.getString("password");

        try {
            String owner = eot.databasePetOwnerToJSONFromUsername(username);
            System.out.println("ckeck null pointer : owner :"+owner);

            JSONObject objowner = new JSONObject(owner);
            System.out.println("ckeck null pointer : owner :"+owner);
            String owner_id = objowner.getString("owner_id");
            System.out.println("username : " + username + "owner_id : " + owner_id );
            pet.put("owner_id", owner_id);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RegisterPet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//    long randomWithMathRandom = (long) ((Math.random() * (9999999 - 1111111)) + 1111111);
      int random_int = (int)Math.floor(Math.random() * (999999999 - 111111111 + 1) + 1111111111);

        String gender = obj.getString("petgender");
        String type = obj.get("pettype").toString();
        String name = obj.getString("name");
        String breed = obj.getString("breed");
        String birthyear = obj.get("birthyear").toString();
        String weight = obj.get("weight").toString();
        String description = obj.getString("description");
        String photo = obj.getString("photo");
        String pet_id = Integer.toString(random_int);
//        String owner_id = obj.getString("owner_id");
        
        
        pet.put("name", name);
//        pet.put("type", type);
//        pet.put("gender", gender);
        pet.put("breed", breed);
        pet.put("birthyear", birthyear);
        pet.put("weight", weight);
        pet.put("photo", photo);
        pet.put("description", description);
        pet.put("pet_id", pet_id);
//        pet.put("owner_id", owner_id);
                  
        obj.remove("pettype");
        obj.remove("petgender");
        
        pet.put("gender", gender);
        pet.put("type", type);
        
        String newjson = pet.toString();
        System.out.println("json is  " + newjson);
        
        try {
            ept.addPetFromJSON(newjson);
            response.getWriter().write("\nPet successfully registered");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterPet.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write("\nError in pet registration");

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
