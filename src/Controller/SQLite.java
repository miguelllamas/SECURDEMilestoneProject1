package Controller;

import Model.User;
import Model.Attempt;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLite {
    
    String driverURL = "jdbc:sqlite:" + "database.db";
    
    public void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(driverURL)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Database database.db created.");
            }
        } catch (Exception ex) {}
    }
    
    public void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " username TEXT NOT NULL,\n"
            + " password TEXT NOT NULL,\n"
            + " role INTEGER DEFAULT 2,\n"
            + " lockStatus INTEGER DEFAULT 0\n"
            + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table users in database.db created.");
        } catch (Exception ex) {}
    }
    
    public void dropUserTable() {
        String sql = "DROP TABLE users;";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table users in database.db dropped.");
        } catch (Exception ex) {}
    }
    
    public ArrayList<User> getUsers(){
        String sql = "SELECT id, username, password, role, lockStatus FROM users";
        ArrayList<User> users = new ArrayList<User>();
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                users.add(new User(rs.getInt("id"),
                                   rs.getString("username"),
                                   rs.getString("password"),
                                   rs.getInt("role"),
                                   rs.getInt("lockStatus")));
            
            }
        } catch (Exception ex) {}
        return users;
    }
    
    public void addUser(String username, String password) {
        String sql = "INSERT INTO users(username,password) VALUES('" + username.toLowerCase() + "','" + password + "')";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            addAttempts();
            
//  For this activity, we would not be using prepared statements first.
//      String sql = "INSERT INTO users(username,password) VALUES(?,?)";
//      PreparedStatement pstmt = conn.prepareStatement(sql)) {
//      pstmt.setString(1, username);
//      pstmt.setString(2, password);
//      pstmt.executeUpdate();
        } catch (Exception ex) {}
    }
    
    public void addUser(String username, String password, int role) {
        String sql = "INSERT INTO users(username,password,role) VALUES('" + username + "','" + password + "','" + role + "')";        
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            addAttempts();
            //for testing if addingLogs works
            addLogs(1, "test", "now");
            
        } catch (Exception ex) {}        
    }
    
    public void removeUser(String username) {
        String sql = "DELETE FROM users WHERE username='" + username + "');";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("User " + username + " has been deleted.");
        } catch (Exception ex) {}
    }
    
    //lucia here
        
    //end lucia here

    //tadhg here

    //kinda weird, not sure if good implementation hahaha. Will change later on if its really bad.
    public int countUsers(){
        
        String sql = "SELECT id, username, password, role, lockStatus FROM users";
        ArrayList<User> users = new ArrayList<User>();
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                users.add(new User(rs.getInt("id"),
                                   rs.getString("username"),
                                   rs.getString("password"),
                                   rs.getInt("role"),
                                   rs.getInt("lockStatus")));
            
            }
        } catch (Exception ex) {}
        
        return users.size();
    }
    
    public void createLogsTable(){
        String sql = "CREATE TABLE IF NOT EXISTS logs (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " user INTEGER DEFAULT 0, \n"
                + " message STRING DEFAULT '', \n"
                + " time STRING DEFAULT ''\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table logs in database.db created.");
        } catch (Exception ex) {}
    }
    
    public void dropLogsTable(){
        String sql = "DROP TABLE logs;";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table logs in database.db dropped.");
        } catch (Exception ex) {}
    }
    
    public void addLogs(int user, String msg, String time) {
        String sql = "INSERT INTO logs(user, message, time) VALUES ("+user+", '"+msg+"', '"+time+"');";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()){
            System.out.println("inside addLogs");
            stmt.execute(sql);
            
        } catch (Exception ex) {
        }
    }
    
    //end tadhg here

    //miggy here
    public void createAttemptsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS attempts (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " trackedFailedAttempts INTEGER DEFAULT 0\n"
            + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table attempts in database.db created.");
        } catch (Exception ex) {}
    }
    
    public void dropAttemptsTable() {
        String sql = "DROP TABLE attempts;";

        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table attempts in database.db dropped.");
        } catch (Exception ex) {}
    }
    
    public void addAttempts() {
        String sql = "INSERT INTO attempts DEFAULT VALUES";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            
        } catch (Exception ex) {
            System.out.println("did this happen?");
        }
    }
    
    public ArrayList<Attempt> getAttempts(){
        String sql = "SELECT id, trackedFailedAttempts FROM attempts";
        ArrayList<Attempt> attempts = new ArrayList<Attempt>();
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                attempts.add(new Attempt(rs.getInt("id"),
                                   rs.getInt("trackedFailedAttempts")));
            
            }
        } catch (Exception ex) {}
        return attempts;
    }
    
    public void addFailedAttempt(String username) {  
        String sql = "UPDATE attempts\n"
                + "SET trackedFailedAttempts = trackedFailedAttempts + 1\n"
                + "WHERE id IN\n"
                + "(SELECT id FROM users WHERE username = '" + username + "');";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Failed attempts for " + username + " updated.");
        } catch (Exception ex) {
        }
    }
    
    public void resetAttempts(String username) {  
        String sql = "UPDATE attempts\n"
                + "SET trackedFailedAttempts = '0'\n"
                + "WHERE id IN\n"
                + "(SELECT id FROM users WHERE username = '" + username + "');";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Reset attempts for " + username + ".");
        } catch (Exception ex) {
        }
    }
    
    public void changeLockStatus(String username, int x) {  
        String sql = "UPDATE users\n"
                + "SET lockStatus = '" + x + "'\n"
                + "WHERE username = '" + username + "';";
        
        try (Connection conn = DriverManager.getConnection(driverURL);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Lock status for " + username + " updated.");
        } catch (Exception ex) {
        }
    }

    //end miggy here
    
}
