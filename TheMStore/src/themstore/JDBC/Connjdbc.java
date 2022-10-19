/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author metal
 */
public class Connjdbc {
    
    private String url="jdbc:mysql://localhost:3306/mstore";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static Connjdbc instance;

    public Connection getCnx() {
        return cnx;
    }
    
    
    private Connjdbc() {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(Connjdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static Connjdbc getInstance(){
       
       if(instance==null)
           instance=new Connjdbc();
       return instance;
   }
    
}
