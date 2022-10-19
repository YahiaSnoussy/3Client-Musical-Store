/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import themstore.Entity.Notification;

import themstore.JDBC.ConnexionSingleton;

/**
 *
 * @author Hayou
 */
public class NotificationDAO implements INotification <Notification> {
    
    private static NotificationDAO instance;
    private Statement st;
    private ResultSet rs;
    
public NotificationDAO() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static NotificationDAO getInstance(){
        if(instance==null) 
            instance=new NotificationDAO();
        return instance;
    }
 @Override
    public void insert(Notification n) {
        String req="insert into notification ( `emailtoreply`) VALUES ('"+n.getEmailtoreply()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(Notification n) {
        String req="delete from notification where id="+n.getId();
        Notification p=displayById(n.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<Notification> displayAll() {
        String req="select * from notification";
        ObservableList<Notification> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Notification n=new Notification ();
                n.setId(rs.getInt(1));
                n.setEmailtoreply(rs.getString("emailtoreply"));
               
                list.add(n);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Notification> displayAllList() {
        String req="select * from notification ";
        List<Notification > list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Notification n=new Notification ();
                 n.setId(rs.getInt(1));
                n.setEmailtoreply(rs.getString("emailtoreply"));
                
                list.add(n);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Notification displayById(int id) {
           String req="select * from notification where id ="+id;
           Notification n=new Notification();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                n.setId(rs.getInt("id"));
                n.setEmailtoreply(rs.getString("emailtoreply"));
               
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return n;
    }

    @Override
    public boolean update(Notification n) {
        String qry = "UPDATE notification SET email = '"+n.getEmailtoreply()+"' WHERE id = "+n.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
  
   

}


