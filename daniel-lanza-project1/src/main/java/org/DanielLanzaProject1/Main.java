package org.DanielLanzaProject1;

import io.javalin.Javalin;
import org.DanielLanzaProject1.DataTypes.*;
import org.DanielLanzaProject1.DatabaseSQL.*;
import org.DanielLanzaProject1.Utils.SQLDatabaseConnection;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //ManagerDatabase managers = new ManagerDatabase();
        EmployeeDatabase employees = new EmployeeDatabase();
        TicketDatabase tickets = new TicketDatabase();

        //Manager m1 = new Manager("NerdSwag","geekin4ever","Jack","Smith","jackDeRipper@gmail.com","4076666767");
        Employee e1 = new Employee("HandyMan","all4sleep","Yoshikage","Kira","killerQueen@gmail.com","1999404444");
        e1.setId(employees.getEmployeeID(e1));

        //Ticket t2 = new Ticket(e1.getId(),50,"suit maintenance",e1.getFirstName(), e1.getLastName());



        //System.out.println(tickets.create(t2));

        System.out.println();

        System.out.println(tickets.getAll().toString());

        System.out.println();





        



    }
}