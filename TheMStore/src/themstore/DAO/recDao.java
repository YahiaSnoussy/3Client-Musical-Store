/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import themstore.Entity.reclamation;
import themstore.JDBC.userconnexion;

/**
 *
 * @author riadh
 */
public class recDao implements idao <reclamation> {

    private static recDao instance;
    private Statement st;
    private ResultSet rs;
    
    public recDao() {
        userconnexion cs=userconnexion.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(recDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static recDao getInstance(){
        if(instance==null) 
            instance=new recDao();
        return instance;
    }
    
    @Override
    public void insert(reclamation r) {
    String req="insert into reclamation (message,sujet) values ('"+r.getMessage()+"','"+r.getSujet()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(recDao.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }

    @Override
    public void delete(reclamation o) {
        String req ="delete from reclamation where id_rec="+o.getId_rec();
        reclamation p=displayById(o.getId_rec()); 
        if (p!= null)
            try 
            {
                st.executeQuery(req);
            } catch (SQLException ex) {
            Logger.getLogger(recDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<reclamation> displayAll() {
        String req="select * from reclamation";
        ObservableList<reclamation> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                reclamation p=new reclamation();
                p.setId_rec(rs.getInt("id_rec"));
                p.setId_produit(rs.getInt("id_produit"));
                p.setId_user(rs.getInt("id_user"));
                p.setSujet(rs.getString("sujet"));
                p.setMessage(rs.getString("message"));
                p.setId_moderateur(rs.getInt("id_moderateur"));
                
               // p.setId_rec(rs.getString());
                
                
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(recDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

        
    }

    @Override
    public reclamation displayById(int id_rec) {
          
String req="select * from reclamation where id_rec=" +id_rec;
reclamation r = new reclamation ();
try 
{
  rs=st.executeQuery(req);
  rs.next();
     r.setId_rec(rs.getInt("id_rec"));
     r.setId_user(rs.getInt("id_user"));
  

    r.setId_produit(rs.getInt("id_produit"));
    r.setMessage(rs.getString("message"));
     r.setSujet(rs.getString("sujet"));
      
       r.setId_moderateur(rs.getInt("id_moderateur"));
    
  
    
    }   catch (SQLException ex) {
            Logger.getLogger(recDao.class.getName()).log(Level.SEVERE, null, ex);
        }
return r;
    }

    @Override
    public boolean update(reclamation os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
 