package org.DanielLanzaProject1.DatabaseSQL;

import org.DanielLanzaProject1.DataTypes.Employee;
import org.DanielLanzaProject1.DataTypes.Ticket;
import org.DanielLanzaProject1.Utils.*;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketDatabase implements DatabaseInterface<Ticket>{

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
    public int create(Ticket ticket) {

        try{
            String sql = "INSERT INTO ticket_list (id,user_id,first_name,last_name,amount,description,status,submission_date,processed)"
                        +" VALUES (default,?,?,?,?,?,?,NOW(),?)";
            PreparedStatement stmt = sqlDBconn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1,ticket.getEmployeeID());
            stmt.setString(2,ticket.getEmployeeFN());
            stmt.setString(3,ticket.getEmployeeLN());
            stmt.setDouble(4,ticket.getCash());
            stmt.setString(5,ticket.getDescription());
            stmt.setString(6,ticket.getStatus());
            stmt.setBoolean(7,ticket.getIsProcessed());

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
    public List<Ticket> getAll() {

        List<Ticket> tickets = new ArrayList<Ticket>();

        try{

            String sql = "SELECT * FROM ticket_list";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setEmployeeID(rs.getInt("user_id"));
                ticket.setEmployeeFN(rs.getString("first_name"));
                ticket.setEmployeeLN(rs.getString("last_name"));
                ticket.setCash(rs.getDouble("amount"));
                ticket.setDescription(rs.getString("description"));
                ticket.setStatus(rs.getString("status"));
                ticket.setDate(rs.getDate("submission_date").toString());
                ticket.setIsProcessed(rs.getBoolean("processed"));

                tickets.add(ticket);
            }

            return tickets;

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }


        return null;
    }

    @Override
    public Ticket getId(int id) {

        try{
            String sql = "SELECT * FROM ticket_list WHERE id=?";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();

            Ticket ticket = new Ticket();

            while (rs.next()){
                ticket.setId(rs.getInt("id"));
                ticket.setEmployeeID(rs.getInt("user_id"));
                ticket.setEmployeeFN(rs.getString("first_name"));
                ticket.setEmployeeLN(rs.getString("last_name"));
                ticket.setCash(rs.getDouble("amount"));
                ticket.setDescription(rs.getString("description"));
                ticket.setStatus(rs.getString("status"));
                ticket.setDate(rs.getDate("submission_date").toString());
                ticket.setIsProcessed(rs.getBoolean("processed"));

            }

            return ticket;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }


        return null;
    }

    @Override
    public Ticket getByCredentials(String username, String password) {
        return null;
    }

    @Override
    public Ticket update(Ticket ticket) {

        try{
            String sql = "UPDATE ticket_list SET status = ?,processed = ? WHERE id=?";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,ticket.getStatus());
            pstmt.setBoolean(2,ticket.getIsProcessed());
            pstmt.setInt(3,ticket.getId());
            ResultSet rs = pstmt.executeQuery();



            return ticket;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    public Ticket updateStatus(Ticket ticket,int i){


        if((i<0)&&(i>Ticket.statusCase.length)){
            System.out.println("Invalid index. Enter an integer from 0 to 2.");
            return null;
        }else{
            ticket.setStatus(Ticket.statusCase[i]);

            return this.update(ticket);

        }





    }


    @Override
    public boolean delete(Ticket ticket) {

        try{
            String sql = "DELETE FROM ticket_list WHERE id=?";
            PreparedStatement pstmt = sqlDBconn.prepareStatement(sql);
            pstmt.setInt(1,ticket.getId());

            return pstmt.execute();

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return false;
    }


}
