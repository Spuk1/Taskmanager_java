package util;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  

public class DBConnector {  
  private static Connection conn;  
  private static String url = "jdbc:mariadb://localhost:3306/Taskmanager";  
  private static String user = "tc";//Username of database  
  private static String pass = "tc";//Password of database
  
  public static Connection connect() throws SQLException{  
    try{  
      Class.forName("com.mysql.jdbc.Driver").newInstance();  
    }catch(ClassNotFoundException cnfe){  
      System.err.println("Error Class not found: "+cnfe.getMessage());  
    }catch(InstantiationException ie){  
      System.err.println("Error Instantiation Exception: "+ie.getMessage());  
    }catch(IllegalAccessException iae){  
      System.err.println("Error Illegal AccessException: "+iae.getMessage());  
    }  
    conn = DriverManager.getConnection(url,user,pass);  
    return conn;  
  }  
  
  public static Connection getConnection() throws SQLException, ClassNotFoundException{  
    if(conn !=null && !conn.isClosed())  
      return conn;  
    connect();  
    return conn;  
  }  
}  
