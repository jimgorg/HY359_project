/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainClasses;

/**
 *
 * @author jgorg
 */
public class User {

    String username;
    String email;
    String password;
    String firstname;
    String lastname;
    String birthdate;
    String gender;
    String type;
    String place;
    String accomm;
    Integer catprice;
    Integer dogprice;
    String description;
    String country;
    String city;
    String address;
    String personalpage;
    String job;
    long telephone;
    String lat, lon;
    boolean catkeeper, dogkeeper;
    String property;

    public User(String username, String email, String password, String firstname, String lastname, String birthdate, String gender, String country, String city, String address, String personalpage,
            String job, long telephone, String lat, String lon, String property, String description, boolean catkeeper, boolean dogkeeper, Integer catprice, Integer dogprice) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.type = type;
        this.dogprice = dogprice;
        this.catprice = catprice;
        this.place = place;
        this.accomm = accomm;
        this.description = description;
        this.country = country;
        this.city = city;
        this.address = address;
        this.personalpage = personalpage;
        this.job = job;
        this.telephone = telephone;
        this.catkeeper = catkeeper;
        this.dogkeeper = dogkeeper;
        this.lat = lat;
        this.lon = lon;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail() {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getFistName() {
        return firstname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDogPrice(Integer dogprice) {
        this.dogprice = dogprice;
    }

    public Integer getDogPrice() {
        return dogprice;
    }

    public void setCatPrice(Integer catprice) {
        this.catprice = catprice;
    }

    public Integer getCatPrice() {
        return catprice;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setAccomm(String accomm) {
        this.accomm = accomm;

    }

    public String getAcomm() {
        return accomm;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDespription() {
        return description;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPersonalPage(String personalpage) {
        this.personalpage = personalpage;
    }

    public String getPersonalPage() {
        return personalpage;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    /**
     *
     * @return
     */
    public long getTelephone() {
        return telephone;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLon() {
        return lon;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public void setCatKeeper(boolean catkeeper) {
        this.catkeeper = catkeeper;
    }

    public boolean getCatKeeper() {
        return catkeeper;
    }

    public void setDogKeeper(boolean dogkeeper) {
        this.dogkeeper = dogkeeper;
    }

    public boolean getDogKeeper() {
        return dogkeeper;
    }

}
