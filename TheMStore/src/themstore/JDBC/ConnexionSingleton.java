package themstore.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hayou
 */


public class ConnexionSingleton {
    
    private String url="jdbc:mysql://localhost:3306/mstore";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static ConnexionSingleton instance;

    public Connection getCnx() {
        return cnx;
    }
    
    
    private ConnexionSingleton() {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static ConnexionSingleton getInstance(){
       
       if(instance==null)
           instance=new ConnexionSingleton();
       return instance;
   }
    
    
    
    
}


