package org.DanielLanzaProject1.Session;

import io.javalin.http.Handler;
import org.DanielLanzaProject1.DataTypes.Employee;
import org.DanielLanzaProject1.DataTypes.Ticket;
import org.DanielLanzaProject1.DatabaseHandlers.*;

public class LogIn {

    private String username;
    private String password;
    static EmployeeHandler employeeHandler = new EmployeeHandler();
    static TicketHandler ticketHandler = new TicketHandler();

    public LogIn(){}


    /**
     * This is a Javadoc comment.
     */
    public static Handler welcomePage = context -> {
        context.result("Welcome to ticket master's main page.\n"
                + "If you have an existing account, please log in with your username and password\n"
                +" at http://localhost:8080/log-in as GET request.\n"
                +"\n"
                + "If you do not have an account with us yet, please create an new account by going to\n"
                + "the SIGN-UP page at http://localhost:8080/create-new-account as a GET request,\n"
                +"and follow the instructions to come");


    };


    public static Handler register = context -> {
        context.result("If you are registering as an Employee, please\n"
                + "click on the link for Employee registration:\n"
                +" at http://localhost:8080/create-new-account/employees.\n"
                +"\n"
                + "If you are instead registering as a Manager, please\n"
                + "click on the link for Employee registration:\n"
                +" at http://localhost:8080/create-new-account/managers.\n");
    };

    public static Handler createUserInstructions = context -> {
        context.result("In order to make your account.\n"
                + "Please enter a username and password along with the information\n"
                +"requested in the format shown below:\n"
                +"\n"
                +"{\n"
                +"username: yourUsername,\n"
                +"password: yourPassword,\n"
                +"firstName: yourFirstName,\n"+
                "lastName: yourLastName,\n"
                +"email: yourEmail@something.smt,\n"+"" +
                "phoneNum:XXXXXXXXXX (   limited to 10 numbers plus country codes)\n"
                +"\n"
                +"}\n"
                +"\n"
                +" and POST it at http://localhost:8080/create-new-user/"+context.pathParam("userType")+".");
    };








    //The log-in page
    public Handler logInPage = context -> {
        context.result("Welcome to ticket master's lon-in page.\n"
                + "Please enter your username and password in the JSON format shown below\n"
                +"\n"
                +"\n"
                +"{\n"
                +"username: yourUsername\n"
                +"password: yourPassword\n"
                +"}\n"
                +"\n"
                +" and POST it at http://localhost:8080/log-in.\n");
    };


    // Submit ticket feature.
    public static Handler submitTicketInstructions = context -> {
        context.result("To create and submit reimbursement ticket, please enter\n"
                +"the ticket information in the JSON format shown below\n"
                +"\n"
                +"\n"
                +"{\n"
                +"cash: employeeAmount\n"
                +"description: employeeDescription\n"
                +"}\n"
                +"\n"
                +"and POST it to http://localhost:8080/user="
                + context.pathParam("id")+"/submit-ticket");

    };

    public static Handler getSubmitTicket = context -> {
        Ticket ticket = context.bodyAsClass(Ticket.class);

        int employeeID = Integer.parseInt(context.pathParam("id"));
        Employee employee = employeeHandler.getByID(employeeID);

        ticket.setEmployeeID(employee.getId());
        ticket.setEmployeeFN(employee.getFirstName());
        ticket.setEmployeeLN(employee.getLastName());

        int ticketID = ticketHandler.createTicket(ticket);

        if(ticketID>0){
            context.result("Ticket with ID = "
                            + Integer.toBinaryString(ticketID)
                            + " has been successfully submitted");
        }else{
            context.result("Failed to submit ticket.");
        }

    };


    // Print employee tickets.
    public static Handler getUserTickets = context -> {
        int employeeID = Integer.parseInt(context.pathParam("id"));
        context.result(ticketHandler.getPrintedTickets(employeeID));
    };










}
