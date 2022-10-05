package org.DanielLanzaProject1.DataObjClasses;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import javax.sql.*;

public class User {
    // Basic attributes for a user account.
    // A username and password.
    /* Attributes are set to private, so that they may only be
    *  accessed through getter and update methods*/

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String phoneNum;

    /*
    These are the constructor of the User class.
    There is the standard constructor.
    The non-phone number constructor.
    The phone number inclusive constructor;
     */
    public User(){

    }

    public User(String firstName,String lastName,String email,String username,String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String firstName,String lastName,String email,String username,String password,String phoneNum){
        this(firstName,lastName,email,username,password);
        this.phoneNum = phoneNum;
    }


    //The getter methods.


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }


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



}
