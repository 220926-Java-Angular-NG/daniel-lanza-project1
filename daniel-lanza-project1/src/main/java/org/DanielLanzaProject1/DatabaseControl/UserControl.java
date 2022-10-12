package org.DanielLanzaProject1.DatabaseControl;

import org.DanielLanzaProject1.DataTypes.*;
import org.DanielLanzaProject1.DatabaseHandlers.*;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;

public class UserControl {

    EmployeeHandler employeeHandler;
    ManagerHandler managerHandler;

    public UserControl(){
        this.employeeHandler = new EmployeeHandler();
        this.managerHandler = new ManagerHandler();
    }

    public UserControl(EmployeeHandler employeeHandler, ManagerHandler managerHandler){
        this.employeeHandler = employeeHandler;
        this.managerHandler = managerHandler;
    }


    //The Employee Handlers
    public Handler createNewEmployee = context -> {

        Employee employee = context.bodyAsClass(Employee.class);
        int id = employeeHandler.createEmployee(employee);

        if(id>0){
            employee.setId(id);
            context.json(employee);
        }else{
            context.result("Employee user not created").status(400);
        }

    };

    public Handler readEmployee = context -> {

    };

    public Handler getAllEmployees = context -> {
        context.json(employeeHandler.getAllEmployees());
    };

    public Handler getEmployeeByID = context -> {
        String param = context.pathParam("id");

        try {
            int id = Integer.parseInt(param);
            Employee employee = employeeHandler.getByID(id);

            if(employee != null){
                context.json(employee).status(202);
            }else{
                context.result("Employee user not found").status(400);
            }

        }catch(NumberFormatException numberFormatException){
            System.out.println(numberFormatException.getMessage());
        }
    };

    public Handler updateEmployee = context -> {
        Employee employee = context.bodyAsClass(Employee.class);
        employee = employeeHandler.updateEmployee(employee);

        if(employee != null){
            context.json(employee).status(202);
        }else{
            context.result("Could not update the Employee user").status(400);
        }

    };

    public Handler deleteEmployee = context -> {
        Employee employee = context.bodyAsClass(Employee.class);

        if(employee != null){
            context.status(200).json(employeeHandler.deleteEmployee(employee));
        }
    };


    // The Manager Handlers
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

    public Handler getAllManagerUsernames = context ->{
        List<Manager> list = managerHandler.getAllManagers();
        String s = "";

        for(int i = 0;i<list.size();i++){
            s = s + " " + list.get(i).getUsername();
        }
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

    //Universal handlers.
    public Handler createNewUser = context -> {

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

    public Handler userLogin = context -> {
        
    };






}
