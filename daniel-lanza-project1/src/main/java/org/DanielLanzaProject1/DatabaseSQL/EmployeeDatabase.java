package org.DanielLanzaProject1.DatabaseSQL;

import org.DanielLanzaProject1.DataTypes.Employee;
import org.DanielLanzaProject1.Utils.*;

import java.util.ArrayList;
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

        try{
            String sql = "INSERT INTO user_list (id,username,password,first_name,last_name,email,phone_number,is_manager) VALUES (default,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1,employee.getUsername());
            pstmt.setString(2,employee.getPassword());
            pstmt.setString(3,employee.getFirstName());
            pstmt.setString(4,employee.getLastName());
            pstmt.setString(5,employee.getEmail());
            pstmt.setString(6,employee.getPhoneNum());
            pstmt.setBoolean(7,employee.getIsManager());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            return rs.getInt("id");
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return 0;
    }

    @Override
    public List<Employee> getAll() {

        List<Employee> employees = new ArrayList<Employee>();

        try{

            String sql = "SELECT * FROM user_list";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhoneNum(rs.getString("phone_number"));
                employee.setIsManager(rs.getBoolean("is_manager"));

                employees.add(employee);
            }

            return employees;

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }


        return null;
    }

    @Override
    public Employee getId(int id) {

        try{
            String sql = "SELECT * FROM user_list WHERE id=?";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();

            Employee employee = new Employee();

            while (rs.next()){
                employee.setId(rs.getInt("id"));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhoneNum(rs.getString("phone_number"));
                employee.setIsManager(rs.getBoolean("is_manager"));

            }

            return employee;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    public int getEmployeeID(Employee employee){

        try{
            String sql = "SELECT id FROM user_list WHERE username = ? AND password = ?";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql);
            pstmt.setString(1,employee.getUsername());
            pstmt.setString(2,employee.getPassword());
            ResultSet rs = pstmt.executeQuery();

            rs.next();

            return rs.getInt("id");



        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return 0;


    }



    @Override
    public Employee update(Employee employee) {

        try{
            String sql = "UPDATE user_list SET email = ? WHERE id=?";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,employee.getEmail());
            pstmt.setInt(2,employee.getId());
            ResultSet rs = pstmt.executeQuery();



            return employee;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }


        return null;
    }

    @Override
    public boolean delete(Employee employee) {

        try{
            String sql = "DELETE FROM user_list WHERE id=?";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql);
            pstmt.setInt(1,employee.getId());

            return pstmt.execute();

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return false;
    }
}
