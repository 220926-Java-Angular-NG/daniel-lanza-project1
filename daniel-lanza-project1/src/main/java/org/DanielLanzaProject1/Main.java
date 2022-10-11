package org.DanielLanzaProject1;

import io.javalin.Javalin;
import org.DanielLanzaProject1.DataTypes.*;
import org.DanielLanzaProject1.DatabaseControl.ManagerControl;
import org.DanielLanzaProject1.DatabaseSQL.*;
import org.DanielLanzaProject1.Utils.SQLDatabaseConnection;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Javalin app = Javalin.create().start(8080);
        ManagerControl mangerCrtl = new ManagerControl();





    }
}