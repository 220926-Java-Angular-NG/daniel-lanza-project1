package org.DanielLanzaProject1;

import io.javalin.Javalin;
import org.DanielLanzaProject1.DataTypes.Employee;
import org.DanielLanzaProject1.DatabaseControl.UserControl;
import org.DanielLanzaProject1.DatabaseHandlers.EmployeeHandler;
import org.DanielLanzaProject1.DatabaseSQL.EmployeeDatabase;
import org.DanielLanzaProject1.Session.LogIn;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8080);
        EmployeeDatabase eDB = new EmployeeDatabase();
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
        //ManagerControl mangerCrtl = new ManagerControl();

        app.get("/", LogIn.welcomePage);
        app.get("/create-new-account", LogIn.register);

        app.get("/create-new-account/{userType}",LogIn.createUserInstructions);
        app.post("/create-new-account/{userType}", userControl.createNewUser);

        app.get("/log-in",context -> {context.result("Welcome to ticket master's lon-in page.\n"
                + "Please enter your username and password in the JSON format shown below\n"
                +"\n"
                +"\n"
                +"{\n"
                +"username: yourUsername\n"
                +"password: yourPassword\n"
                +"}\n"
                +"}\n"
                +" and POST it at http://localhost:8080/log-in.\n");
        });


        /*
        app.get("/confirm",context -> {
            Boolean isManager = eDB.getByUsername("Joyboy").getIsManager();
            boolean confirm = eH.usernameExists("Joyboy");

            context.result("For a user that does not exist, the logic returns a" + isManager.toString()
            + " .\n But the Handler sees it as " + Boolean.toString(confirm)+".");
        });
         */


        app.get("/user={id}/submit-ticket", context -> {
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

        });


        app.post("/user={id}/submit-ticket",LogIn.getSubmitTicket);





    }
}