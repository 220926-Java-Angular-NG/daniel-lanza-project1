package org.DanielLanzaProject1.DatabaseControl;

import org.DanielLanzaProject1.DataTypes.*;
import org.DanielLanzaProject1.DatabaseHandlers.*;
import io.javalin.http.Handler;

public class ManagerControl {

    ManagerHandler managerHandler;

    public ManagerControl(){
        this.managerHandler = new ManagerHandler();
    }

    public ManagerControl(ManagerHandler managerHandler){
        this.managerHandler = managerHandler;
    }




    public Handler createNewManager = context -> {

        Manager manager = context.bodyAsClass(Manager.class);
        int id = managerHandler.createUser(manager);

        if(id>0){
            manager.setId(id);
            context.json(manager);
        }else{
            context.result("User not created").status(400);
        }

    };



    public Handler readManager = context -> {

    };



    public Handler getAllManagers = context -> {
        context.json(managerHandler.getAllManagers());
    };



    public Handler getManagerByID = context -> {
        String param = context.pathParam("id");

        try {
            int id = Integer.parseInt(param);
            User user = managerHandler.getByID(id);

            if(user != null){
                context.json(user).status(202);
            }else{
                context.result("User not found").status(400);
            }

        }catch(NumberFormatException numberFormatException){
            System.out.println(numberFormatException.getMessage());
        }
    };





    public Handler updateManager = context -> {
        Manager manager = context.bodyAsClass(Manager.class);
        manager = managerHandler.updateManager(manager);

        if(manager != null){
            context.json(manager).status(202);
        }else{
            context.result("Could not update the Manager user").status(400);
        }

    };


    public Handler deleteManager = context -> {
        Manager manager = context.bodyAsClass(Manager.class);

        if(manager != null){
            context.status(200).json(managerHandler.deleteManager(manager));
        }
    };



}
