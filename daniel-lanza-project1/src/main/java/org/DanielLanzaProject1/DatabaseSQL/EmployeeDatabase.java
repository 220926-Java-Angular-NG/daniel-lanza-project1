package org.DanielLanzaProject1.DatabaseSQL;

import org.DanielLanzaProject1.DataTypes.Employee;
import org.DanielLanzaProject1.Utils.*;
import java.util.List;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeDatabase implements DatabaseInterface<Employee>{


    private static final Logger LOGGER = LoggerFactory.getLogger(Employee.class);
    Connection sqlDBconn;

    public EmployeeDatabase(){
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
    public int create(Employee employee) {
        return 0;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public Employee getId(int id) {
        return null;
    }

    @Override
    public Employee update(Employee employee) {
        return null;
    }

    @Override
    public boolean delete(Employee employee) {
        return false;
    }
}
