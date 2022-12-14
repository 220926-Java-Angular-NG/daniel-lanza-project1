package org.DanielLanzaProject1.DataTypes;

public class Ticket {

    public static final String[] statusCase = {"approved","declined","pending"};


    // The basic attributes of a ticket.
    private double cash;
    private String description = "";
    private String status; // Intialized in the constructor


    //The other basic attributes of a ticket.
    private int id; // Initialized in the SQL
    private int employeeID;
    private String employeeFN;
    private String employeeLN;



    private String date; // Initialized in the SQL

    private boolean processed; // Initialized in the constructor


    // The constructors for the ticket objects.


    public Ticket(){
        this.status = statusCase[2];
    }

    public Ticket(int id,int status){
        this.id=id;
        this.status = statusCase[status];
    }

    public Ticket(double cash,String description){
        this();
        this.cash = cash;
        this.description = description;
        this.processed = false;
    }

    public Ticket(int employeeID,double cash,String description,String employeeFN, String employeeLN){
        this(cash,description);
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

    public boolean getIsProcessed() {
        return processed;
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

    public void setEmployeeLN(String employeeLN) {
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

    public void setIsProcessed(boolean b){
        this.processed = b;
    }




    //Methods for ticket status.
    public void processTicket(){
        this.processed = true;
    }

    @Override
    public String toString() {
        String s = Integer.toString(this.getId()) + "    "
                + Double.toString(this.getCash()) + "    "
                + this.getDescription() + "    "
                + this.getStatus() + "    "
                + this.getDate() + "\n";
        return s;

    }
}
