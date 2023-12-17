package com.example.toourapplication.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {
    private static Connection getConnection(){
        try {
            Class.forName("org.postgresql.Driver"); // <1>

            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/tour", "postgres", "5858");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Admin getAdmin(String username){
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM admins WHERE username=?");
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            Admin admin = null;
            if (resultSet.next()){
                admin = new Admin(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
            }
            statement.close();
            getConnection().close();
            return admin;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Ticket> getTickets(){
        try {
            List<Ticket> tickets = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tickets");
            while (resultSet.next()){
                tickets.add(
                        new Ticket(
                                resultSet.getLong(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getLong(4),
                                resultSet.getDate(5),
                                resultSet.getDate(6),
                                resultSet.getBoolean(7)
                        )
                );
            }
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveTicket(String country,String ticket_type,Long price,String flight_time,String landing_time,boolean from_uzb){
        try {
            String sql = "INSERT INTO tickets (country, ticket_type, price, flight_time, landing_time, from_uzb) VALUES ('"+country+"','"+ticket_type+"', "+price+", '"+flight_time+"', '"+landing_time+"', '"+from_uzb+"')";
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(sql);
            statement.close();
            getConnection().close();
            System.out.println("ok");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteTicket(Long id){
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("DELETE FROM tickets WHERE id="+id);
            statement.close();
            getConnection().close();
            System.out.println("deleted id:"+id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveClient(ClientDTO client){
        String sql = "insert into clients (lname,fname,email,phone,username,passid,password) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1,client.getLname());
            preparedStatement.setString(2,client.getFname());
            preparedStatement.setString(3,client.getEmail());
            preparedStatement.setString(4, client.getPhone());
            preparedStatement.setString(5, client.getUsername());
            preparedStatement.setString(6,client.getPassid());
            preparedStatement.setString(7,client.getPassword());

            preparedStatement.executeUpdate();
            System.out.println("ok");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Client getClient(String username){
        String sql = "select * from clients where username=?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new Client(
                        resultSet.getLong("id"),
                        resultSet.getString("lname"),
                        resultSet.getString("fname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("username"),
                        resultSet.getString("passid"),
                        resultSet.getString("password")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
