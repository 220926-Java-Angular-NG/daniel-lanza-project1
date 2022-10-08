package org.DanielLanzaProject1.DataTypes;

public class Manager extends User{

    public Manager(){
        this.setIsManager(true);
    }

    public Manager(String username,String password){
        this.setIsManager(true);
        this.setUsername(username);
        this.setPassword(password);
    }

    public Manager(String username,String password,String firstName,String lastName,String email){
        this(username,password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
    }

    public Manager(String username,String password,String firstName,String lastName,String email,String phoneNum){
        this(username,password,firstName,lastName,email);
        this.setPhoneNum(phoneNum);
    }




}
