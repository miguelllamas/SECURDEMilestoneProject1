package Controller;

import Model.User;
import View.Frame;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Main {
    
    public SQLite sqlite;
    
    public String encryptThisString(String input) 
    { 
        try { 
            // getInstance() method is called with algorithm SHA-512 
            MessageDigest md = MessageDigest.getInstance("SHA-512"); 
  
            // digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
  
            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            // return the HashText 
            return hashtext; 
        } 
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
    
    //lucia here
        
    //end lucia here

    //tadhg here

    //end tadhg here

    //miggy here

    //end miggy here
    
    public static void main(String[] args) {
        new Main().init();
    }
    
    public void init(){
        // Initialize a driver object
        sqlite = new SQLite();

        // Create a database
        sqlite.createNewDatabase();
        
        // Drop users table if needed
        sqlite.dropUserTable();
        
        // Create users table if not exist
        sqlite.createUserTable();
        
        // Add users with hashed passwords
        String password = encryptThisString("qwerty1234");
        
        sqlite.addUser("admin", password , 5);
        sqlite.addUser("manager", password, 4);
        sqlite.addUser("staff", password, 3);
        sqlite.addUser("client1", password, 2);
        sqlite.addUser("client2", password, 2);
        
        // Get users
        ArrayList<User> users = sqlite.getUsers();
        for(int nCtr = 0; nCtr < users.size(); nCtr++){
            System.out.println("===== User " + users.get(nCtr).getId() + " =====");
            System.out.println(" Username: " + users.get(nCtr).getUsername());
            System.out.println(" Password: " + users.get(nCtr).getPassword());
            System.out.println(" Role: " + users.get(nCtr).getRole());
        }
        
        //lucia here
        
        //end lucia here
        
        //tadhg here
        
        //end tadhg here
        
        //miggy here
        
        //end miggy here
        
        // Initialize User Interface
        Frame frame = new Frame();
        frame.init(this);
    }
    
}
