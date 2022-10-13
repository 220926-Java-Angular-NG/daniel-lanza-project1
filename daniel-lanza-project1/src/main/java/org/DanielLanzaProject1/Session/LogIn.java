package org.DanielLanzaProject1.Session;

import io.javalin.http.Handler;
import org.DanielLanzaProject1.DataTypes.Employee;
import org.DanielLanzaProject1.DataTypes.Manager;
import org.DanielLanzaProject1.DataTypes.Ticket;
import org.DanielLanzaProject1.DatabaseHandlers.*;
import java.util.List;

import java.util.Objects;

public class LogIn {

    private String username;
    private String password;
    static EmployeeHandler employeeHandler = new EmployeeHandler();
    static TicketHandler ticketHandler = new TicketHandler();
    static ManagerHandler managerHandler = new ManagerHandler();

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
        System.out.println("Im beinf called upon.");
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

    public static Handler userLogin = context -> {
        Manager m = context.bodyAsClass(Manager.class);

        String u = m.getPassword();
        String p = m.getPassword();
        m = managerHandler.getByCredentials(u,p);

        boolean exists = managerHandler.usernameExists(u);
        boolean isManager = managerHandler.isManager(m);

        if (exists && isManager){

        }else if(exists && !isManager){

        }else{
            context.result("Wrong login credentials, or the user does not exist.").status(404);
        }


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


    // Print an employee's previous tickets.
    public static Handler getUserTickets = context -> {
        int employeeID = Integer.parseInt(context.pathParam("id"));
        context.result(ticketHandler.getPrintedTickets(employeeID));
    };


    // Ticketing system feature.
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
          context.result("The status of the ticket has been successfully updated.");
      }else{
          context.result("Failed to update the ticket");
      }


    };

    public static Handler getPendingTickets = context -> {
        List<Ticket> list = ticketHandler.getAllPendingTickets();
        String s = "";
        for(Ticket t:list){
            s = s + t.toString();
        }
        context.result(s);
    };










}
