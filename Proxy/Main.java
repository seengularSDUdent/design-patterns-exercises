import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) {
		DatabasePrimary service = new DatabasePrimary();
		DatabaseProxy proxy = new DatabaseProxy(service);
		DatabaseUsers databaseUsers = DatabaseUsers.getInstance();
		
		databaseUsers.addUser("hello");
		
		System.out.println(databaseUsers.getUser(0));
		System.out.println();
		
		proxy.checkAuth(1, "hello1");
		System.out.println(proxy.auth);
		System.out.println();
		
		databaseUsers.getUser(0).setPermissionLevel(2);
		System.out.println("first - " + proxy.getFirstLevelData());
		System.out.println("second - " + proxy.getSecondLevelData());
		System.out.println("third - " + proxy.getThirdLevelData());
		
		proxy.checkAuth(1, "hello");
		System.out.println(proxy.auth);
		System.out.println();
		
		System.out.println("first - " + proxy.getFirstLevelData());
		System.out.println("second - " + proxy.getSecondLevelData());
		System.out.println("third - " + proxy.getThirdLevelData());
	}
}

interface DatabaseAccess{
    
    public String getFirstLevelData();
    public String getSecondLevelData();
    public String getThirdLevelData();
    
    public String getData(int index);
}

class DatabasePrimary implements DatabaseAccess{
    
    ArrayList<String> documents = new ArrayList<>();
    
    public String getFirstLevelData(){
        return "It's a first level Data!";
    }
    
    public String getSecondLevelData(){
        return "It's a second level Data!";
    }
    
    public String getThirdLevelData(){
        return "It's a third level Data!";
    }
    
    public String getData(int index){
        return this.documents.get(index);
    }
}

class DatabaseProxy implements DatabaseAccess{
    
    DatabaseAccess service;
    User currentUser;
    boolean auth = false;
    
    public DatabaseProxy(DatabasePrimary setValue){
        this.service = setValue;
    }
    
    public void checkAuth(int userId, String userPassword){
        DatabaseUsers databaseUsers = DatabaseUsers.getInstance();
        
        for(User user : databaseUsers.getUserList()){
            if(user.getId() == userId){
                if(user.getPassword().equals(userPassword)){
                    this.auth = true;
                    this.currentUser = user;
                }
            }
        }
    }
    
    public String getFirstLevelData(){
        if(auth == false){
            return "";
        }
        
        if(this.currentUser.getPermissionLevel() >= 1){
            return service.getFirstLevelData();
        }
        
        return "";
    }
    
    public String getSecondLevelData(){
        if(auth == false){
            return "";
        }
        
        if(this.currentUser.getPermissionLevel() >= 2){
            return service.getSecondLevelData();
        }
        
        return "";
    }
    
    public String getThirdLevelData(){
        if(auth == false){
            return "";
        }
        
        if(this.currentUser.getPermissionLevel() >= 3){
            return service.getThirdLevelData();
        }
        
        return "";
    }
    
    public String getData(int index){
        if(auth == false){
            return "";
        }
        
        return service.getData(index);
    }
}

class User{
    
    private int id;
    private int permissionLevel = 1;
    private static int lastId = 1;
    private String password;
    
    public User(String password){
        this.id = this.lastId++;
        this.password = password;
    }
    
    public void setPermissionLevel(int setValue){
        this.permissionLevel = setValue;
    }
    
    public int getPermissionLevel(){
        return this.permissionLevel;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getPassword(){
        return this.password;
    }
}

class DatabaseUsers{
    
    private static DatabaseUsers instance;
    private ArrayList<User> userList = new ArrayList<>();
    
    private DatabaseUsers(){
    }
    
    public void addUser(String password){
        this.userList.add(new User(password));
    }
    
    public User getUser(int index){
        return this.userList.get(index);
    }
    
    public ArrayList<User> getUserList(){
        return this.userList;
    }
    
    static public DatabaseUsers getInstance(){
        if(instance == null){
            instance = new DatabaseUsers();
            return instance;
        }
        else{
            return instance;
        }
    }
}