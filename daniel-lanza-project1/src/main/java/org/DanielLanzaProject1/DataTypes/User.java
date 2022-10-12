package org.DanielLanzaProject1.DataTypes;

public class User {
    // Basic attributes for a user account.
    // A username and password.
    /* Attributes are set to private, so that they may only be
    *  accessed through getter and update methods*/
    private String username;
    private String password;


    // Optinal attributes
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNum;

    private Boolean isManager;

    /*
    These are the constructor of the User class.
    There is the standard constructor.
    The non-phone number constructor.
    The phone number inclusive constructor;
     */


    //The getter methods.
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public Boolean getIsManager(){ return this.isManager;}


    // The setter methods.
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setIsManager(boolean manager){ this.isManager = manager;}



}
