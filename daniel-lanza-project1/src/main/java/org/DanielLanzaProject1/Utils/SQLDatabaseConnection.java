package org.DanielLanzaProject1.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLDatabaseConnection {

    private static Connection conn;
    private static Properties props;

    private SQLDatabaseConnection(){

    }



    public static Connection getConn() throws SQLException {

        if(props==null){
            props = loadProperties();
        }


        if((conn==null)||(conn.isClosed())){

            conn = DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("username"),
                    props.getProperty("password")
            );

        }

        return conn;
    }




    private static Properties loadProperties(){
        Properties properties = new Properties();


        try{

            FileInputStream fileInputStream = new FileInputStream("src/main/resources/jdcb.properties");
            properties.load(fileInputStream);

        }catch(IOException fileNotFoundException){
            System.out.println(fileNotFoundException.getMessage());
        }

        return properties;

    }



}