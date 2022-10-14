package org.DanielLanzaProject1.Session;

import io.javalin.http.Handler;
import org.DanielLanzaProject1.DataTypes.Employee;
import org.DanielLanzaProject1.DataTypes.Manager;
import org.DanielLanzaProject1.DataTypes.Ticket;
import org.DanielLanzaProject1.DatabaseHandlers.*;
import java.util.List;

import java.util.Objects;

public class LogIn {

    private static String currentUser;
    private static int currentID = 0;
    private static boolean currentlyManager;

    private static final String employeeOps = "If you would like to post a new reimbursement ticket, please go\n"
                               + "http://localhost:8080/employee=" + Integer.toString(currentID) +"/submit-ticket, and\n"
                               + "submit a GET request for further instructions.\n\n"
                               + "If would like to see your past ticket submissions, please go\n"
                               + "http://localhost:8080/employee=" + Integer.toString(currentID) +"/submissions, and\n"
                               + "submit a GET request for further instructions.\n\n"
                               + "If you would like to Log Out, submit a GET request to\n"
                               + "http://localhost:8080/employee=" + Integer.toString(currentID) +"/log-out.";

    private static final String managerOps = "If you would like to view and process a reimbursement ticket, please go\n"
                                + "http://localhost:8080/manager=" + currentID +"/process-ticket, and\n"
                                + "submit a GET request for further instructions.\n\n"
                                + "If would like to see the tickets that require attention, please go\n"
                                + "http://localhost:8080/manager=" + currentID +"/pending-tickets, and\n"
                                + "submit a GET request for further instructions.\n\n"
                                + "If you would like to Log Out, submit a GET request to\n"
                                + "http://localhost:8080/manager=" + currentID +"/log-out.";

    static EmployeeHandler employeeHandler = new EmployeeHandler();
    static TicketHandler ticketHandler = new TicketHandler();
    static ManagerHandler managerHandler = new ManagerHandler();

    public LogIn(){}


    public static void setCurrentUser(String user){
        currentUser = user;
    }
    public static void setCurrentID(int id){
        currentID = id;
    }

    public static void setCurrentlyManager(boolean b){
        currentlyManager = b;
    }

    public static void reset(){
        currentUser = null;
        currentID = 0;
        currentlyManager = false;
    }


    public static String getCurrentUser(){
        return currentUser;
    }

    public static int getCurrentID(){
        return currentID;
    }

    public static boolean isCurrentlyManager(){
        return currentlyManager;
    }




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


    //The log-in and register feature.
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
                +"\"username\": \"yourUsername\",\n"
                +"\"password\": \"yourPassword\",\n"
                +"\"firstName\": \"yourFirstName\",\n"
                +"\"lastName\": \"yourLastName\",\n"
                +"\"email\": \"yourEmail@something.smt\",\n"
                +"\"phoneNum\":\"XXXXXXXXXX\"    (limited to 10 numbers plus country codes)\n"
                +"\n"
                +"}\n"
                +"\n"
                +" and POST it at http://localhost:8080/create-new-user/"+context.pathParam("userType")+".");
    };

    public static Handler createNewUser = context -> {

        if(context.pathParam("userType").equals("employees")){
            Employee employee = context.bodyAsClass(Employee.class);
            if(!employeeHandler.usernameExists(employee.getUsername())){
                int id = employeeHandler.createEmployee(employee);
                if(id>0){
                    employee.setId(id);
                    context.json(employee);
                }else{
                    context.result("Employee user not created").status(400);
                }
            }else{
                context.result("This username already exists. Please enter a different one.");
            }
        } else if (context.pathParam("userType").equals("managers")) {

            Manager manager = context.bodyAsClass(Manager.class);
            if(!managerHandler.usernameExists(manager.getUsername())){
                int id = managerHandler.createUser(manager);
                if(id>0){
                    manager.setId(id);
                    context.json(manager);
                }else{
                    context.result("Manager not created").status(400);
                }
            }else{
                context.result("This username already exists. Please enter a different one.");
            }

        }else {
            context.result("Invalid url").status(404);
        }


    };

    public static Handler logInPage = context -> {

        context.result("Welcome to ticket master's lon-in page.\n"
                + "Please enter your username and password in the JSON format shown below\n"
                +"\n"
                +"\n"
                +"{\n"
                +"\"username\": \"yourUsername\", \n"
                +"\"password\": \"yourPassword\" \n"
                +"}\n"
                +"\n"
                +" and POST it at http://localhost:8080/log-in.\n");
    };

    public static Handler userLogin = context -> {
        Manager m = context.bodyAsClass(Manager.class);
        String u = m.getUsername();
        String p = m.getPassword();

        m = managerHandler.getByCredentials(u,p);
        boolean exists = managerHandler.usernameExists(u);
        boolean isManager = m.getIsManager();


        if (exists && !isManager){
            Employee e = employeeHandler.getByCredentials(u,p);
            LogIn.setCurrentUser(e.getUsername());
            LogIn.setCurrentID(e.getId());
            LogIn.setCurrentlyManager(false);
            context.json(e);
            context.result("Submit a GET request to http://localhost:8080/employee=" + currentID);
        }else if(exists && isManager){
            LogIn.setCurrentUser(m.getUsername());
            LogIn.setCurrentID(m.getId());
            LogIn.setCurrentlyManager(true);
            context.json(m);
            context.result("Submit a GET request to http://localhost:8080/manager=" + currentID);
        }else{
            context.result("Wrong login credentials, or the user does not exist.").status(404);
        }


    };

    public static Handler session = context -> {

        String firstName = "";
        String lastName = "";
        String sessionInstructions = "";
        if((currentID>0)&&currentlyManager){
            Manager m = managerHandler.getByID(currentID);
            firstName = m.getFirstName();
            lastName = m.getLastName();
            sessionInstructions = managerOps;
        }else if((currentID>0)&&(!currentlyManager)){
            Employee e = employeeHandler.getByID(currentID);
            firstName = e.getFirstName();
            lastName = e.getLastName();
            sessionInstructions = employeeOps;
        }
        context.result("Welcome " + firstName + " " + lastName + "! What would you like to do?\n\n"
                      + sessionInstructions);
    };

    public static Handler logOut = context -> {
       LogIn.reset();
       context.result("You have logged out.");
    };




    // Submit ticket feature.
    public static Handler submitTicketInstructions = context -> {
        context.result("To create and submit reimbursement ticket, please enter\n"
                +"the ticket information in the JSON format shown below\n"
                +"\n"
                +"\n"
                +"{\n"
                +"\"cash\""+":"+  "\"employeeAmount\""+",\n"
                +"\"description\""+":"+"\"employeeDescription\""+"\n"
                +"}\n"
                +"\n"
                +"and POST it to http://localhost:8080/user="
                + context.pathParam("id")+"/submit-ticket");

    };

    public static Handler getSubmitTicket = context -> {
        Ticket ticket = context.bodyAsClass(Ticket.class);

        boolean validAmount = ticket.getCash() > 0;
        boolean validDescription = ticket.getDescription().length()>0;

        System.out.println(validAmount);
        System.out.println(validDescription);

        if((validAmount)&&(validDescription)){
            int employeeID = Integer.parseInt(context.pathParam("id"));
            Employee employee = employeeHandler.getByID(employeeID);

            ticket.setEmployeeID(employee.getId());
            ticket.setEmployeeFN(employee.getFirstName());
            ticket.setEmployeeLN(employee.getLastName());

            int ticketID = ticketHandler.createTicket(ticket);

            if(ticketID>0){
                context.result("Ticket with ID = "
                        + Integer.toString(ticketID)
                        + " has been successfully submitted");
            }else{
                context.result("Failed to submit ticket.");
            }
        }else{
            context.result("The description cannot be left blank, and the amount must be greter than 0.");
        }



    };


    // Print an employee's previous ticket submissions.
    public static Handler getUserTickets = context -> {
        int employeeID = Integer.parseInt(context.pathParam("id"));
        context.result(ticketHandler.getPrintedTickets(employeeID));
    };



    // Ticketing system feature.
    private static String pendingTickets(){
        List<Ticket> list = ticketHandler.getAllPendingTickets();
        String s = "";
        for(Ticket t:list){
            s = s + t.toString();
        }
        return s;
    }

    public static Handler processTicketInstructions = context -> {
        context.result("Please enter the id of the ticket you wish to process and either \n"
                + "a 0 for APPROVED or 1 for DECLINED in the JSON format shown below\n"
                +"\n"
                +"\n"
                +"{\n"
                +"\"id\": \"ticketID\", \n"
                +"\"status\": \"yourChoice\" \n"
                +"}\n"
                +"\n"
                +" and POST it at http://localhost:8080/manager="+context.pathParam("id")
                +"/process-ticket"+".\n");
    };
    public static Handler processTicket = context -> {
      Ticket ticket = context.bodyAsClass(Ticket.class);
      String status = Ticket.statusCase[Integer.parseInt(ticket.getStatus())];


      ticket = ticketHandler.getByID(ticket.getId());
      boolean b = ticket.getIsProcessed();


      if(b){
          context.result("This ticket has already been processed.\n"
                         + " No further changes can be made to it");
      }else if(ticket.getId()>0){
          ticket.setStatus(status);
          ticket.processTicket();

          System.out.println(ticket.getIsProcessed());

          ticket = ticketHandler.updateTicket(ticket);
          context.result("The status of the ticket has been successfully updated.\n"+LogIn.pendingTickets());
      }else{
          context.result("Failed to update the ticket");
      }


    };

    public static Handler getPendingTickets = context -> {
        context.result(LogIn.pendingTickets());
    };










}
