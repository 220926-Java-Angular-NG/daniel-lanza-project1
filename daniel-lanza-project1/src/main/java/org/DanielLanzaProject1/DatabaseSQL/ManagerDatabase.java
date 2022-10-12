package org.DanielLanzaProject1.DatabaseSQL;

import org.DanielLanzaProject1.DataTypes.Manager;
import org.DanielLanzaProject1.Utils.*;

import java.util.ArrayList;
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

        try{
            String sql = "INSERT INTO user_list (id,username,password,first_name,last_name,email,phone_number,is_manager) VALUES (default,?,?,?,?,?,?,?)";
            PreparedStatement stmt = sqlDBconn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,manager.getUsername());
            stmt.setString(2,manager.getPassword());
            stmt.setString(3,manager.getFirstName());
            stmt.setString(4,manager.getLastName());
            stmt.setString(5,manager.getEmail());
            stmt.setString(6,manager.getPhoneNum());
            stmt.setBoolean(7,manager.getIsManager());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt("id");

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return 0;

    }

    @Override
    public List<Manager> getAll() {

        List<Manager> managers = new ArrayList<Manager>();

        try{

            String sql = "SELECT * FROM user_list WHERE is_manager = true";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                Manager manager = new Manager();
                manager.setId(rs.getInt("id"));
                manager.setUsername(rs.getString("username"));
                manager.setPassword(rs.getString("password"));
                manager.setFirstName(rs.getString("first_name"));
                manager.setLastName(rs.getString("last_name"));
                manager.setEmail(rs.getString("email"));
                manager.setPhoneNum(rs.getString("phone_number"));
                manager.setIsManager(rs.getBoolean("is_manager"));

                managers.add(manager);
            }

            return managers;

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }




        return null;
    }

    @Override
    public Manager getId(int id) {

        try{
            String sql = "SELECT * FROM user_list WHERE id=? AND is_manager = true";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();

            Manager manager = new Manager();

            while (rs.next()){
                manager.setId(rs.getInt("id"));
                manager.setUsername(rs.getString("username"));
                manager.setPassword(rs.getString("password"));
                manager.setFirstName(rs.getString("first_name"));
                manager.setLastName(rs.getString("last_name"));
                manager.setEmail(rs.getString("email"));
                manager.setPhoneNum(rs.getString("phone_number"));
                manager.setIsManager(rs.getBoolean("is_manager"));

            }

            return manager;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }


        return null;
    }

    public Manager getByUsername(String username){
        try{
            String sql = "SELECT * FROM user_list WHERE username = ?";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();

            Manager manager = new Manager();

            while (rs.next()){
                manager.setId(rs.getInt("id"));
                manager.setUsername(rs.getString("username"));
                manager.setPassword(rs.getString("password"));
                manager.setFirstName(rs.getString("first_name"));
                manager.setLastName(rs.getString("last_name"));
                manager.setEmail(rs.getString("email"));
                manager.setPhoneNum(rs.getString("phone_number"));
                manager.setIsManager(rs.getBoolean("is_manager"));

            }

            return manager;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public Manager getByCredentials(String username, String password) {

        try{
            String sql = "SELECT * FROM user_list WHERE username = ? AND password = ? AND is_manager = true";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            ResultSet rs = pstmt.executeQuery();

            Manager manager = new Manager();

            while (rs.next()){
                manager.setId(rs.getInt("id"));
                manager.setUsername(rs.getString("username"));
                manager.setPassword(rs.getString("password"));
                manager.setFirstName(rs.getString("first_name"));
                manager.setLastName(rs.getString("last_name"));
                manager.setEmail(rs.getString("email"));
                manager.setPhoneNum(rs.getString("phone_number"));
                manager.setIsManager(rs.getBoolean("is_manager"));

            }

            return manager;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    //Considering making updates on the update method.
    @Override
    public Manager update(Manager manager) {

        try{
            String sql = "UPDATE user_list SET email = ? WHERE id=?";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,manager.getEmail());
            pstmt.setInt(2,manager.getId());
            ResultSet rs = pstmt.executeQuery();



            return manager;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }


        return null;
    }

    @Override
    public boolean delete(Manager manager) {

        try{
            String sql = "DELETE FROM user_list WHERE id=?";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql);
            pstmt.setInt(1,manager.getId());

            return pstmt.execute();

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return false;
    }
}
