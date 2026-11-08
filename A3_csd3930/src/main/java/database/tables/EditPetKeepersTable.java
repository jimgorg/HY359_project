/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;
import com.google.gson.Gson;
import mainClasses.PetKeeper;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class EditPetKeepersTable {

 
    public void addPetKeeperFromJSON(String json) throws ClassNotFoundException{
         PetKeeper user=jsonToPetKeeper(json);
         addNewPetKeeper(user);
    }
    
    public PetKeeper jsonToPetKeeper(String json) {
        Gson gson = new Gson();

        PetKeeper user = gson.fromJson(json, PetKeeper.class);
        return user;
    }
    
    public String petKeeperToJSON(PetKeeper user){
         Gson gson = new Gson();

        String json = gson.toJson(user, PetKeeper.class);
        return json;
    }
    
   
    
    public void updatePetKeeper(String username,String personalpage) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET personalpage='"+personalpage+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void printPetKeeperDetails(String username, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE username = '" + username + "' AND password='"+password+"'");
            while (rs.next()) {
                System.out.println("===Result===");
                DB_Connection.printResults(rs);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    
    public PetKeeper databaseToPetKeepers(String username, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE username = '" + username + "' AND password='"+password+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            PetKeeper user = gson.fromJson(json, PetKeeper.class);
            return user;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    
   
    

    
     public ArrayList<PetKeeper> getAvailableKeepers(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<PetKeeper> keepers = new ArrayList<PetKeeper>();
        ResultSet rs = null;
        try {
            //if(type=="catkeeper")
            if("all".equals(type))     
            rs = stmt.executeQuery("SELECT * FROM `petKeepers` WHERE  `petKeepers`.`keeper_id` not in (select keeper_id "
                 + "from `bookings` where `status`='requested' or  `status`='accepted')\n" +"");
            else if ("catKeepers".equals(type))
                 rs = stmt.executeQuery("SELECT * FROM `petKeepers` WHERE (`petKeepers`.`catkeeper`='cat' or `petKeepers`.`catkeeper`='true') AND `petKeepers`.`keeper_id` not in (select keeper_id "
                 + "from `bookings` where `status`='requested' or  `status`='accepted')");         
             else if ("dogKeepers".equals(type))
                 rs = stmt.executeQuery("SELECT * FROM `petKeepers` WHERE ( `petKeepers`.`dogkeeper`='dog' or `petKeepers`.`dogkeeper`='true') AND `petKeepers`.`keeper_id` not in (select keeper_id "
                 + "from `bookings` where `status`='requested' or  `status`='accepted')");
        
           
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                PetKeeper keeper = gson.fromJson(json, PetKeeper.class);
                keepers.add(keeper);
            }
            return keepers;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
     
     public ArrayList<PetKeeper> getAvailableKeepersAll( ) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<PetKeeper> keepers = new ArrayList<PetKeeper>();
        ResultSet rs = null;
        try {
              
                 rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE ( catkeeper='cat' OR catkeeper='true' OR dogkeeper='dog' OR dogkeeper='true') AND keeper_id not in (select keeper_id "
                 + "from bookings where status='requested' OR  status='accepted')");
        
           
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                PetKeeper keeper = gson.fromJson(json, PetKeeper.class);
                keepers.add(keeper);
            }
            return keepers;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
     
     public void deletePetKeeper(String username,String email) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String delete="DELETE FROM petkeepers WHERE username='"+username+"' AND email = '"+email+"'";
        stmt.executeUpdate(delete);
    }
     
    
    public ArrayList<PetKeeper> getKeepers(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<PetKeeper> keepers = new ArrayList<PetKeeper>();
        ResultSet rs = null;
        try {
            if("catkeeper".equals(type))
                 rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE catkeeper='cat' OR catkeeper= '" + "true" + "'");
            else if ("dogkeeper".equals(type))
                  rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE dogkeeper='dog' OR dogkeeper= '" + "true" + "'");

           
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                PetKeeper keeper = gson.fromJson(json, PetKeeper.class);
                keepers.add(keeper);
            }
            return keepers;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    
    public ArrayList<PetKeeper> getKeepersAll() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<PetKeeper> keepers = new ArrayList<PetKeeper>();
        ResultSet rs = null;
        try {
                 rs = stmt.executeQuery("SELECT * FROM petkeepers ");

           
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                PetKeeper keeper = gson.fromJson(json, PetKeeper.class);
                keepers.add(keeper);
            }
            return keepers;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    
    public String databasePetKeeperToJSON(String username, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE username = '" + username + "' AND password='"+password+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public String databasePetKeeperToJSONFromEmail(String username, String email) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE username = '" + username + "' AND email='"+email+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    
       public String databasePetKeeperToJSONFromUsername(String username) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE username = '" + username + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    
    public String databasePetKeeperToJSONNoPassword(String keeper_id) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE keeper_id = '" + keeper_id + "' ");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    
   


     public void createPetKeepersTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE petkeepers "
                + "(keeper_id INTEGER not NULL AUTO_INCREMENT, "
                + "    username VARCHAR(30) not null unique,"
                + "    email VARCHAR(50) not null unique,	"
                + "    password VARCHAR(32) not null,"
                + "    firstname VARCHAR(30) not null,"
                + "    lastname VARCHAR(30) not null,"
                + "    birthdate DATE not null,"
                + "    gender  VARCHAR (7) not null,"
                + "    country VARCHAR(30) not null,"
                + "    city VARCHAR(50) not null,"
                + "    address VARCHAR(50) not null,"
                + "    personalpage VARCHAR(200) not null,"
                + "    job VARCHAR(200) not null,"
                + "    telephone VARCHAR(14),"
                + "    lat DOUBLE,"
                + "    lon DOUBLE,"
                + "    property VARCHAR(10) not null,"
                + "    propertydescription VARCHAR(200),"
                + "    catkeeper VARCHAR(10) not null,"
                + "    dogkeeper VARCHAR(10) not null,"
                + "    catprice INTEGER,"
                + "    dogprice INTEGER,"
                + " PRIMARY KEY (keeper_id))";
        stmt.execute(query);
        stmt.close();
    }
    
    
    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void addNewPetKeeper(PetKeeper user) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " petkeepers (username,email,password,firstname,lastname,birthdate,gender,country,city,address,personalpage,"
                    + "job,telephone,lat,lon,property,propertydescription,catkeeper,dogkeeper,catprice,dogprice)"
                    + " VALUES ("
                    + "'" + user.getUsername() + "',"
                    + "'" + user.getEmail() + "',"
                    + "'" + user.getPassword() + "',"
                    + "'" + user.getFirstname() + "',"
                    + "'" + user.getLastname() + "',"
                    + "'" + user.getBirthdate() + "',"
                    + "'" + user.getGender() + "',"
                    + "'" + user.getCountry() + "',"
                    + "'" + user.getCity() + "',"
                    + "'" + user.getAddress() + "',"
                    + "'" + user.getPersonalpage() + "',"
                     + "'" + user.getJob() + "',"
                    + "'" + user.getTelephone() + "',"
                    + "'" + user.getLat() + "',"
                    + "'" + user.getLon() + "',"
                    + "'" + user.getProperty() + "',"
                    + "'" + user.getPropertydescription()+ "',"
                    + "'" + user.getCatkeeper() + "',"
                    + "'" + user.getDogkeeper() + "',"
                    + "'" + user.getCatprice() + "',"
                    + "'" + user.getDogprice() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The pet owner was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditPetKeepersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
       public void updatePetKeeperPersonalPage(String username,String personalpage) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET personalpage='"+personalpage+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    } 
    
    public void updatePetKeeperUsername(String username,String uname) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET username='"+uname+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperEmail(String username,String email) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET email='"+email+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperPassword(String username,String password) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET password='"+password+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperFirstname(String username,String firstname) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET firstname='"+firstname+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperLastname(String username,String lastname) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET lastname='"+lastname+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperBirthdate(String username,String birthdate) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET birthdate='"+birthdate+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperGender(String username,String gender) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET gender='"+gender+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperJob(String username,String job) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET job='"+job+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperLat(String username,Double lat) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET lat='"+lat+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    
    public void updatePetKeeperLon(String username,Double lon) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET lon='"+lon+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperCountry(String username,String country) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET country='"+country+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperCity(String username,String city) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET city='"+city+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperAddress(String username,String address) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET address='"+address+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperTelephone(String username,String telephone) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET telephone='"+telephone+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperProperty(String username,String property) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET property='"+property+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperPropertyDescription(String username,String propertydescription) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET propertydescription='"+propertydescription+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    
    public void updatePetKeeperCatKeeper(String username,String catkeeper) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET catkeeper='"+catkeeper+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperDogKeeper(String username,String dogkeeper) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET dogkeeper='"+dogkeeper+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
     
    public void updatePetKeeperCatPrice(String username,String catprice) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET catprice='"+catprice+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }
    
    public void updatePetKeeperDogPrice(String username,String dogprice) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET dogprice='"+dogprice+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }

}
