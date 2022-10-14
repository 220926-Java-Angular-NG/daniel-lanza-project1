package org.DanielLanzaProject1;

import io.javalin.Javalin;
import org.DanielLanzaProject1.DataTypes.Employee;
import org.DanielLanzaProject1.DataTypes.Manager;
import org.DanielLanzaProject1.DatabaseControl.UserControl;
import org.DanielLanzaProject1.DatabaseHandlers.EmployeeHandler;
import org.DanielLanzaProject1.DatabaseHandlers.ManagerHandler;
import org.DanielLanzaProject1.DatabaseSQL.*;
import org.DanielLanzaProject1.Session.LogIn;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8080);
        EmployeeDatabase eDB = new EmployeeDatabase();
        TicketDatabase ticketDatabase = new TicketDatabase();
        EmployeeHandler eH = new EmployeeHandler();
        ManagerHandler mH = new ManagerHandler();

        LogIn logIn = new LogIn();


        app.get("/", LogIn.welcomePage);
        app.get("/create-new-account", LogIn.register);

        app.get("/create-new-account/{userType}",LogIn.createUserInstructions);
        app.post("/create-new-account/{userType}", LogIn.createNewUser);

        app.get("/log-in",LogIn.logInPage);
        app.post("/log-in",LogIn.userLogin);


        app.get("/employee={id}",LogIn.session);
        app.get("/employee={id}/submit-ticket",LogIn.submitTicketInstructions);
        app.post("/employee={id}/submit-ticket",LogIn.getSubmitTicket);
        app.get("/employee={id}/submissions",LogIn.getUserTickets);
        app.get("/employee={id}/log-out",LogIn.logOut);






        app.get("/manager={id}",LogIn.session);
        app.get("/manager={id}/process-tickets",LogIn.processTicketInstructions);
        app.post("/manager={id}/process-tickets",LogIn.processTicket);
        app.get("/manager={id}/pending-tickets",LogIn.getPendingTickets);
        app.get("/manager={id}/log-out",LogIn.logOut);








    }
}