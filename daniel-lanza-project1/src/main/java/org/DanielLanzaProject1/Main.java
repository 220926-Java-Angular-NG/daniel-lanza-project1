package org.DanielLanzaProject1;

import io.javalin.Javalin;
import org.DanielLanzaProject1.DataTypes.Employee;
import org.DanielLanzaProject1.DatabaseControl.UserControl;
import org.DanielLanzaProject1.DatabaseHandlers.EmployeeHandler;
import org.DanielLanzaProject1.DatabaseSQL.*;
import org.DanielLanzaProject1.Session.LogIn;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8080);
        EmployeeDatabase eDB = new EmployeeDatabase();
        TicketDatabase ticketDatabase = new TicketDatabase();
        EmployeeHandler eH = new EmployeeHandler();

        /*
        app.post("http://localhost:8080/create-new-account/employees",context -> {
            Employee e = context.bodyAsClass(Employee.class);
            Employee e2 = eDB.getByUsername(e.getUsername());

            context.result(Boolean.toString(e2==null));

        });
         */

        LogIn logIn = new LogIn();
        UserControl userControl = new UserControl();

        app.get("/", LogIn.welcomePage);
        app.get("/create-new-account", LogIn.register);

        app.get("/create-new-account/{userType}",LogIn.createUserInstructions);
        app.post("/create-new-account/{userType}", LogIn.createNewUser);

        app.get("/log-in",LogIn.logInPage);
        app.post("/log-in",LogIn.userLogin);


        /*
        app.get("/confirm",context -> {
            Boolean isManager = eDB.getByUsername("Joyboy").getIsManager();
            boolean confirm = eH.usernameExists("Joyboy");

            context.result("For a user that does not exist, the logic returns a" + isManager.toString()
            + " .\n But the Handler sees it as " + Boolean.toString(confirm)+".");
        });
         */





        app.get("/user={id}/submit-ticket",LogIn.submitTicketInstructions);
        app.post(("/user={id}/submit-ticket"),LogIn.getSubmitTicket);


        app.post("/user={7}/edit-tickets",LogIn.processTicket);

        app.get("/pending-tickets",LogIn.getPendingTickets);
        //





    }
}