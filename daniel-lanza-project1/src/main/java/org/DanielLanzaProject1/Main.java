package org.DanielLanzaProject1;

import io.javalin.Javalin;

import org.DanielLanzaProject1.DataTypes.Employee;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        Scanner s = new Scanner(System.in);
        Javalin j = Javalin.create().start(8080);

        Employee e = new Employee();

        



    }
}