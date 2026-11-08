/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import database.init.Resources;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.tables.EditPetKeepersTable;
import database.tables.EditPetOwnersTable;
import database.tables.EditPetsTable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import mainClasses.PetKeeper;
import org.json.JSONObject;
import mainClasses.JSON_Converter;
import mainClasses.PetOwner;
import mainClasses.PetKeeper;


/**
 *
 * @author jgorg
 */
public class Resgister extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Resgister</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Resgister at " + request.getContextPath() + "</h1>");
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
        
        Double lon = 25.0722869;
        Double lat = 35.3053121;
        
        int flag = 0;
        Resources res =  new Resources();
        res.initializeOwnersKeeperArrayList();

        
        EditPetKeepersTable eut = new EditPetKeepersTable();
        EditPetOwnersTable eot = new EditPetOwnersTable();
        EditPetsTable ept = new EditPetsTable();

        JSON_Converter jc = new JSON_Converter();
        
        String json = jc.getJSONFromAjax(request.getReader());
        System.out.println(json);
        
        JSONObject obj = new JSONObject(json);
        JSONObject keeper = new JSONObject();
        JSONObject owner = new JSONObject();
        
        
        String username = obj.getString("username");
        String email = obj.getString("email");
        String password = obj.getString("password");
        String firstname = obj.getString("firstname");
        String lastname = obj.getString("lastname");
        String birthdate = obj.getString("birthdate");
        String gender = obj.getString("gender");
        String country = obj.getString("country");
        String city = obj.getString("city");
        String address = obj.getString("address");
        String personalpage = obj.getString("personalpage");
        String job = obj.getString("job");
        String telephone = obj.getString("telephone");
        
        System.out.println("gender = "+gender);
        
        owner.put("username", username);
        owner.put("email", email);
        owner.put("password", password);
        owner.put("firstname", firstname);
        owner.put("lastname", lastname);
        owner.put("birthdate", birthdate);
        owner.put("gender", gender);
        owner.put("country", country);
        owner.put("city", city);
        owner.put("address", address);
        owner.put("personalpage", personalpage);
        owner.put("job", job);
        owner.put("telephone", telephone);        
        owner.put("lat", lat);
        owner.put("lon", lon);
        
        String ownerjson = owner.toString();
        
        keeper.put("username", username);
        keeper.put("email", email);
        keeper.put("password", password);
        keeper.put("firstname", firstname);
        keeper.put("lastname", lastname);
        keeper.put("birthdate", birthdate);
        keeper.put("gender", gender);
        keeper.put("country", country);
        keeper.put("city", city);
        keeper.put("address", address);
        keeper.put("personalpage", personalpage);
        keeper.put("job", job);
        keeper.put("telephone", telephone);        
        keeper.put("lat", lat);
        keeper.put("lon", lon);
       

        
//        System.out.println("After adding lat and lon json is : " + newjson);
        
        System.out.println("type = " +  obj.getString("type"));
                

        ArrayList<PetOwner> ownersList = new ArrayList<PetOwner>();
        
        ArrayList<PetKeeper> catlist = new ArrayList<PetKeeper>();
        ArrayList<PetKeeper> doglist = new ArrayList<PetKeeper>();
        ArrayList<PetKeeper> keepersList = new ArrayList<PetKeeper>();


        PetKeeper a; 
        PetOwner b;
        try(PrintWriter out = response.getWriter()){
            catlist = eut.getKeepers("catkeeper");
            doglist = eut.getKeepers("dogkeeper");
            keepersList.addAll(catlist);
            keepersList.addAll(doglist);
            ownersList = eot.getOwners();
            for (int i = 0; i < keepersList.size();i++) 
            { 	
                a = keepersList.get(i);

                if(a.getUsername() == obj.getString("username").toString() || a.getEmail() == obj.getString("email").toString()){
                     response.getWriter().write("\nusername or email taken could not subscribe ");
                     out.println();  
                     flag = 1;
                }
//                System.out.println("\nfrom database petKeepersList is : " + eut.petKeeperToJSON(a)); 		

            }
            for(int j = 0 ; j<ownersList.size() ; j++){
                b = ownersList.get(j);

                if(b.getUsername().equals(obj.get("username")) || b.getEmail().equals(obj.getString("email"))){
                     response.getWriter().write("\nusername or email taken could not subscribe");
                     out.println();
                     flag = 1;
                } 
//                System.out.println("\nfrom database petOwnersList is : " + eot.petOwnerToJSON(b)); 		
                
            }
            if( flag == 0){
                if(obj.getString("type").equals("PetKeeper")){
                    String property = obj.getString("property");
                    String propertydescription = obj.getString("propertydescription");
//        String catkeeper = obj.getString("catkeeper");
//        String dogkeeper = obj.getString("dogkeeper");
                    String catprice = obj.getString("catprice");
                    String dogprice = obj.getString("dogprice");
//                    String keepes = obj.getString("typeofani");
                    
                    keeper.put("property", property);
                    keeper.put("propertydescription", propertydescription);
//        keeper.put("catkeeper", catkeeper);
//        keeper.put("dogkeeper", dogkeeper);
                    keeper.put("catprice", catprice);
                    keeper.put("dogprice", dogprice);        
        
                    if(obj.getString("typeofani").equals("dog")){
                        keeper.put("dogkeeper", "dog");
                        keeper.put("catkeeper", "null");
                    }
                    else{
                        keeper.put("catkeeper", "cat");
                        keeper.put("dogkeeper", "null");
                    }
        
                    String keeperjson = keeper.toString();
                    eut.addPetKeeperFromJSON(keeperjson);
                }
                else{
                    eot.addPetOwnerFromJSON(ownerjson);   
//                    ept.addPetFromJSON(petjson);
                }
                
                response.getWriter().write("Registarion Successfull");            
            }
            
             
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Resgister.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Resgister.class.getName()).log(Level.SEVERE, null, ex);
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
