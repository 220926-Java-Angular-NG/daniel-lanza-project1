package org.DanielLanzaProject1.DataTypes;

public class Ticket {

    public static final String[] statusCase = {"approved","declined","pending"};


    // The basic attributes of a ticket.
    private double cash;
    private String description = "";
    private String status;


    //The other basic attributes of a ticket.
    private int id;
    private int employeeID;
    private String employeeFN;
    private String employeeLN;


    private String date;



    // The constructors for the ticket objects.
    public Ticket(){
        this.status = statusCase[2];
    }

    public Ticket(int employeeID,double cash,String description){
        this();
        this.employeeID = employeeID;
        this.cash = cash;
        this.description = description;
    }

    public Ticket(int employeeID,double cash,String description,String employeeFN, String employeeLN){
        this(employeeID,cash,description);
        this.employeeFN = employeeFN;
        this.employeeLN = employeeLN;
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

    public String getEmployeeLN() {
        return employeeLN;
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

    public String getDescription() {
        return description;
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

    public void setGetEmployeeLN(String employeeLN) {
        this.employeeLN = employeeLN;
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

    public void setDescription(String message) {
        this.description = message;
    }


    //Methods for ticket status.
    public void changeStatus(int i){
        this.setStatus(Ticket.statusCase[i]);
    }


}
