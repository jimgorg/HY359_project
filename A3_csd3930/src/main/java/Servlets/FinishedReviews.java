/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import database.tables.EditPetKeepersTable;
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
import mainClasses.JSON_Converter;
import mainClasses.Review;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author jgorg
 */
public class FinishedReviews extends HttpServlet {

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
            out.println("<title>Servlet FinishedReviews</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FinishedReviews at " + request.getContextPath() + "</h1>");
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
        System.out.println(request);
//        try {
//            ArrayList<Review> rev = new ArrayList();
//            
//            EditPetKeepersTable eut = new EditPetKeepersTable();
//            EditReviewsTable ert = new EditReviewsTable();
//            
//            JSON_Converter jc = new JSON_Converter();
//            String json = jc.getJSONFromAjax(request.getReader());
//            
//             System.out.println("JSON : "+json);
//            JSONObject obj = new JSONObject(json);
//            
//            String username =  obj.getString("username");
//            String keeper = eut.databasePetKeeperToJSONFromUsername(username);
//            System.out.println("keeper from username : "+ keeper);
//            
//            
//            String keeper_id = obj.getString("keeper_id");
//            
//            rev = ert.databaseTokeeperReviews(keeper_id);
//            String reviewText, reviewScore;
//            Integer owner_id;
//            for(int i=0 ; i<rev.size() ; i++){
//                Review r = rev.get(i);
//                
//                reviewText = r.getReviewText();
//                reviewScore = r.getReviewScore();
//                owner_id = r.getOwner_id();
//                
//                JSONObject fin = new JSONObject();
//                
//                fin.put("keeper_id", keeper_id);
//                fin.put("ownet_id", owner_id);
//                fin.put("reviewText", reviewText);
//                fin.put("reviewScore", reviewScore);
//                
//                
//                
//            }
//            response.getWriter().write(rev.toString());
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(FinishedReviews.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
        
        try {
            ArrayList<String> rev = new ArrayList();
            
            
            EditPetKeepersTable eut = new EditPetKeepersTable();
            EditReviewsTable ert = new EditReviewsTable();
            
            JSON_Converter jc = new JSON_Converter();
            String json = jc.getJSONFromAjax(request.getReader());
            
            System.out.println("JSON : "+json);
            JSONObject obj = new JSONObject(json);
            
            String username =  obj.getString("username");
            String keeper = eut.databasePetKeeperToJSONFromUsername(username);
            System.out.println("keeper from username : "+ keeper);
            
            JSONObject keep = new JSONObject(keeper);
            
            String keeper_id = keep.getString("keeper_id");
            System.out.println("keeperid is "+keeper_id);
            
            rev = ert.databaseTokeeperReviewsJSON(keeper_id);
            String reviewText, reviewScore;
            String owner_id;
            JSONObject fin = new JSONObject();
            JSONObject finfin = new JSONObject();
            
            JSONArray arr = new JSONArray();
            
            Integer j=0;
            for(int i=0 ; i<rev.size() ; i++){
                String r = rev.get(i);
                System.out.println("review r from database is : "+r);
                JSONObject revobj = new JSONObject(r);
                j++;
                
                reviewText = revobj.getString("reviewText");
                reviewScore = revobj.getString("reviewScore");
                owner_id = revobj.getString("owner_id");
                
                fin.put("keeper_id"+i, keeper_id);
                System.out.println("insert to finfin keeper_id:"+keeper_id);
                fin.put("owner_id"+i, owner_id);
                System.out.println("insert to finfin owner_id:"+owner_id);
                fin.put("reviewText"+i, reviewText);
                System.out.println("insert to finfin reviewText:"+reviewText);
                fin.put("reviewScore"+i, reviewScore);
                System.out.println("insert to finfin reviewScore:"+reviewScore);
                

            }
            
         
//            System.out.println("count is : "+j.toString());
            finfin.put("count", j.toString());
            finfin.put("review"+j, fin);
            System.out.println("finfin IS:"+finfin.toString());

            response.getWriter().write(finfin.toString());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FinishedReviews.class.getName()).log(Level.SEVERE, null, ex);
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
