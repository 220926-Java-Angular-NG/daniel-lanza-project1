package org.DanielLanzaProject1.DataObjClasses;

public class Ticket {

    public static final String[] statusCase = {"approved","declined","pending"};


    private int id;
    private int employeeID;
    private String employeeFN;
    private String getEmployeeLN;
    private double cash;
    private String status;
    private String date;
    private String message = "";


    // The constructors for the ticket objects.
    public Ticket(){

    }

    public Ticket(int id,int employeeID,String employeeFN, String getEmployeeLN,double cash){
        this.id = id;
        this.employeeID = employeeID;
        this.employeeFN = employeeFN;
        this.getEmployeeLN = employeeFN;
        this.cash = cash;
    }


    // The getter methods.


    public int getId() {
        return id;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeFN() {
        return employeeFN;
    }

    public String getGetEmployeeLN() {
        return getEmployeeLN;
    }

    public double getCash() {
        return cash;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }


    // The setter methods.

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setEmployeeFN(String employeeFN) {
        this.employeeFN = employeeFN;
    }

    public void setGetEmployeeLN(String getEmployeeLN) {
        this.getEmployeeLN = getEmployeeLN;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
