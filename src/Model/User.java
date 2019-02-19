package Model;

public class User {
    private int id;
    private String username;
    private String password;
    private int role;
    private int lockStatus;
    private String lockTime;

    //lucia here
        
    //end lucia here

    //tadhg here

    //end tadhg here

    //miggy here

    //end miggy here

    public User(String username, String password){
        this.username = username;
        this.password = password;
        
        //lucia here
        
        //end lucia here

        //tadhg here

        //end tadhg here

        //miggy here

        //end miggy here
    }
    
    public User(int id, String username, String password, int role, int lockStatus, String lockTime){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.lockStatus = lockStatus;
        this.lockTime = lockTime;
        
        //lucia here
        
        //end lucia here

        //tadhg here

        //end tadhg here

        //miggy here

        //end miggy here
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    public int getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(int lockStatus) {
        this.lockStatus = lockStatus;
    }
    
    public String getLockTime() {
        return lockTime;
    }

    public void setLockTime(String lockTime) {
        this.lockTime = lockTime;
    }
    
    //lucia here
        
    //end lucia here

    //tadhg here

    //end tadhg here

    //miggy here

    //end miggy here
}
