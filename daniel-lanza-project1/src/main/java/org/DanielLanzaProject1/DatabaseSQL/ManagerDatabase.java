package org.DanielLanzaProject1.DatabaseSQL;

import org.DanielLanzaProject1.DataTypes.Manager;
import org.DanielLanzaProject1.Utils.*;
import java.util.List;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManagerDatabase implements DatabaseInterface<Manager>{


    private static final Logger LOGGER = LoggerFactory.getLogger(Manager.class);
    Connection sqlDBconn;

    public ManagerDatabase(){
        try{

            this.sqlDBconn  = SQLDatabaseConnection.getConn();
            System.out.println(sqlDBconn.toString());
            System.out.println(sqlDBconn.getSchema());
            System.out.println();

        }catch(SQLException sqlException){
            LOGGER.error(sqlException.getMessage());
        }
    }


    @Override
    public int create(Manager manager) {
        return 0;
    }

    @Override
    public List<Manager> getAll() {
        return null;
    }

    @Override
    public Manager getId(int id) {
        return null;
    }

    @Override
    public Manager update(Manager manager) {
        return null;
    }

    @Override
    public boolean delete(Manager manager) {
        return false;
    }
}
