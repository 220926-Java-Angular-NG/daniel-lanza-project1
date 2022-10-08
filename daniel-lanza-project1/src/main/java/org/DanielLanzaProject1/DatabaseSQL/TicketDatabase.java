package org.DanielLanzaProject1.DatabaseSQL;

import org.DanielLanzaProject1.DataTypes.Ticket;
import org.DanielLanzaProject1.Utils.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketDatabase implements DatabaseInterface{

    private static final Logger LOGGER = LoggerFactory.getLogger(Ticket.class);
    Connection sqlDBconn;

    public TicketDatabase(){
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
    public int create(Object o) {
        return 0;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Object getId(int id) {
        return null;
    }

    @Override
    public Object update(Object o) {
        return null;
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }
}
