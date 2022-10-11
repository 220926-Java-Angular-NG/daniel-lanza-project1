package org.DanielLanzaProject1.DatabaseControl;

import org.DanielLanzaProject1.DataTypes.*;
import org.DanielLanzaProject1.DatabaseHandlers.*;
import io.javalin.http.Handler;

public class EmployeeControl {

    EmployeeHandler employeeHandler;

    public EmployeeControl(){
        this.employeeHandler = new EmployeeHandler();
    }

    public EmployeeControl(EmployeeHandler employeeHandler){
        this.employeeHandler = employeeHandler;
    }


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



    public Handler getManagerByID = context -> {
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





    public Handler updateManager = context -> {
        Employee employee = context.bodyAsClass(Employee.class);
        employee = employeeHandler.updateEmployee(employee);

        if(employee != null){
            context.json(employee).status(202);
        }else{
            context.result("Could not update the Employee user").status(400);
        }

    };


    public Handler deleteManager = context -> {
        Employee employee = context.bodyAsClass(Employee.class);

        if(employee != null){
            context.status(200).json(employeeHandler.deleteEmployee(employee));
        }
    };





}
