package org.DanielLanzaProject1.DataTypes;

public class Employee extends User{



    public Employee(){
        this.setIsManager(false);
    }

    public Employee(String username,String password){
        this.setIsManager(false);
        this.setUsername(username);
        this.setPassword(password);
    }

    public Employee(String username,String password,String firstName,String lastName,String email){
        this(username,password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
    }

    public Employee(String username,String password,String firstName,String lastName,String email,String phoneNum){
        this(username,password,firstName,lastName,email);
        this.setPhoneNum(phoneNum);
    }





}
