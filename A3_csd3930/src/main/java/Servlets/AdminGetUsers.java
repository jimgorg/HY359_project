/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import database.tables.EditPetKeepersTable;
import database.tables.EditPetOwnersTable;
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
import mainClasses.PetKeeper;
import mainClasses.PetOwner;

/**
 *
 * @author jgorg
 */
public class AdminGetUsers extends HttpServlet {

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
            out.println("<title>Servlet AdminGetUsers</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminGetUsers at " + request.getContextPath() + "</h1>");
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
        
        EditPetKeepersTable eut = new EditPetKeepersTable();
        EditPetOwnersTable eot = new EditPetOwnersTable();
        
        ArrayList<PetKeeper> availablekeepers = new ArrayList();
        ArrayList<PetOwner> availableowners = new ArrayList();
        JSON_Converter jc = new JSON_Converter();
        
        try {
            //        String json = jc.getJSONFromAjax(request.getReader());
//        JSONObject obj = new JSONObject(json);
//        String type = obj.getString("pettype");

        availablekeepers = eut.getAvailableKeepersAll();
        availableowners = eot.getOwners();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AdminGetUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.getWriter().write("<br>Pet owners :");
        
        for(int i=0 ; i<availableowners.size() ; i++){
            PetOwner p = availableowners.get(i);
            p.setPassword("******");
            response.getWriter().write("<br>"+ eot.petOwnerToJSON(p)+"<br>");
        }

        
        
        
        
        response.getWriter().write("<br>Pet Keepers for cats :");
        
        for(int i=0 ; i<availablekeepers.size() ; i++){
            PetKeeper a = availablekeepers.get(i);
            a.setPassword("*****");
            if(a.getCatkeeper().equals("cat") || a.getCatkeeper().equals("true")){
                response.getWriter().write("<br>"+eut.petKeeperToJSON(a)+"<br>");
            }
            
        }
        response.getWriter().write("<br>Pet Keepers for dogs :");
        
        for(int i=0 ; i<availablekeepers.size() ; i++){
            PetKeeper b = availablekeepers.get(i);
            b.setPassword("*****");
            if(b.getDogkeeper().equals("dog") || b.getDogkeeper().equals("true")){
                response.getWriter().write("<br>"+eut.petKeeperToJSON(b)+"<br>");
            }
            
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
