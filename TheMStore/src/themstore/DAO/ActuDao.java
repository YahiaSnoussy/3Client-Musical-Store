/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.DAO;

import themstore.Entity.Actualite;
import themstore.Entity.Comment;
import themstore.Entity.like;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import themstore.JDBC.ConnexionSingleton;

/**
 *
 * @author willy
 */
public class ActuDao implements AIdao <Actualite>{
     private static ActuDao instance;

 
     private Statement st;
     private ResultSet rs;
    private ActuDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ActuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ActuDao getInstance(){
        if(instance==null) 
            instance=new ActuDao();
        return instance;
    }


    @Override
    public void insert(Actualite A) {
       //String req= "insert into actualite (body,date) values ('"+A.getID_Actu()+"','"+A.getID_Personne()+"','"+A.getDate()+"','"+A.getBody()+"','"+A.getCategorie()+"')";
       String req= "insert into actulites (body,date,categorie) values ('"+A.getBody()+"','"+A.getDate()+"','"+A.getCategorie()+"')";

       try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ActuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insert(Comment C) {
       //String req= "insert into actualite (body,date) values ('"+A.getID_Actu()+"','"+A.getID_Personne()+"','"+A.getDate()+"','"+A.getBody()+"','"+A.getCategorie()+"')";
       String req= "insert into commentaire (id_actu,id_perso,body,date) values ('"+C.getID_Actu()+"','"+C.getID_Personne()+"','"+C.getBody()+"','"+C.getDate().toString()+"')";

       try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ActuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void insert(like l) {
       //String req= "insert into actualite (body,date) values ('"+A.getID_Actu()+"','"+A.getID_Personne()+"','"+A.getDate()+"','"+A.getBody()+"','"+A.getCategorie()+"')";
       String req= "insert into likes (id_actu,id_perso) values ('"+l.getID_Actu()+"','"+l.getID_Perso()+"')";

       try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ActuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     @Override
    public ObservableList<Actualite> displayAll() {
        String req="select * from actulites";
        ObservableList<Actualite> list=FXCollections.observableArrayList();       
        try {
            
            rs=st.executeQuery(req);
            while(rs.next()){
                Actualite p=new Actualite();
                p.setID_Actu(rs.getInt("ID_actu"));
                p.setID_Personne(rs.getInt("ID_perso"));
                p.setBody(rs.getString("body"));
                p.setCategorie(rs.getString("categorie"));
                p.setDate(rs.getString("date"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public ObservableList<like> displayAlll(int id) {
        String req="select * from likes where id_actu = "+id;
        ObservableList<like> list=FXCollections.observableArrayList();       
        try {
            
            rs=st.executeQuery(req);
            while(rs.next()){
                like l = new like();
                l.setId_like(rs.getInt("id_like"));
                l.setID_Actu(rs.getInt("ID_Actu"));
                l.setID_Perso(rs.getInt("ID_Perso"));
                list.add(l);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public ObservableList<Comment> displayAl(int id) {
        String req="select * from commentaire where id_actu = "+id;
        ObservableList<Comment> list=FXCollections.observableArrayList();       
        try {
            
            rs=st.executeQuery(req);
            while(rs.next()){
                Comment l = new Comment();
                l.setId_comment(rs.getInt("id_comment"));
                l.setID_Actu(rs.getInt("ID_Actu"));
                l.setID_Personne(rs.getInt("ID_Perso"));
                l.setBody(rs.getString("body"));
                l.setDate(rs.getString("date"));
                list.add(l);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public void delete(Actualite o) {
    String req="delete from actulites where id_actu="+o.getID_Actu();
        Actualite A= displayById (o.getID_Actu());
        
          if(A!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ActuDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
}

    @Override
    public boolean update(Actualite A) {
        String qry = "UPDATE actulites SET body = '"+A.getBody()+"' WHERE id_actu = "+A.getID_Actu();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ActuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    
    @Override
    public Actualite displayById(int id) {
           String req="select * from actulites where id_actu ="+id;
           Actualite p=new Actualite();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setID_Actu(rs.getInt("id_actu"));
                p.setID_Personne(rs.getInt("id_perso"));
                
                //p.setPrenom(rs.getString("prenom"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ActuDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
    

 
    
}
