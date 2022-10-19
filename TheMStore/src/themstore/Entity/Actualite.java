/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import themstore.JDBC.ConnexionSingleton;

/**
 *
 * @author willy
 */
public class Actualite {
    private SimpleIntegerProperty ID_Actu;
    private SimpleIntegerProperty ID_Personne;
    private SimpleStringProperty date;
    private SimpleStringProperty body;
    private SimpleStringProperty categorie;

    public Actualite(String date, String body) {
        this.date = new SimpleStringProperty(date);
        this.body = new SimpleStringProperty(body) ;
    }

    public Actualite() {
        
    }

    public Actualite(int ID_Actu, String body) {
        this.ID_Actu = new SimpleIntegerProperty(ID_Actu);
        this.body =  new SimpleStringProperty(body) ;
    }

    public Actualite(int ID_Actu, int ID_Personne, String date, String body, String categorie) {
        this.ID_Actu = new SimpleIntegerProperty(ID_Actu);
        //this.ID_Actu = ID_Actu;
        this.ID_Personne = new SimpleIntegerProperty(ID_Personne);
        this.date = new SimpleStringProperty(date);
        this.body = new SimpleStringProperty(body) ;
       this.categorie= new SimpleStringProperty(categorie);
    }

    public Actualite(String body) {
        this.body = new SimpleStringProperty(body) ;
    }

    public Actualite(String date, String body, String categorie) {
          this.date = new SimpleStringProperty(date);
         this.body = new SimpleStringProperty(body) ;
         this.categorie= new SimpleStringProperty(categorie);
    }
    
    
    
    
    
    public int getID_Actu() {
        return ID_Actu.get() ;
    }

    public void setID_Actu(int ID_Actu) {
        
        this.ID_Actu = new SimpleIntegerProperty(ID_Actu);
         //this.ID_Actu = ID_Actu;
    }

    public int getID_Personne() {
        return ID_Personne.get();
    }

    public void setID_Personne(int ID_Personne) {
        this.ID_Personne = new SimpleIntegerProperty(ID_Personne);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date = new SimpleStringProperty(date);
    }

    public String getBody() {
        return body.get();
    }

    public void setBody(String body) {
        this.body = new SimpleStringProperty(body);
    }

    public String getCategorie() {
        return categorie.get();
    }

    public Actualite(int ID_Actu) {
        this.ID_Actu = new SimpleIntegerProperty(ID_Actu);
    }

    public void setCategorie(String categorie) {
        this.categorie = new SimpleStringProperty(categorie);
    }
    
     public String getIdProperty(){
        return String.valueOf(ID_Actu);
    }
      public SimpleStringProperty getCategorieProperty(){
        return categorie;
    }
       public SimpleStringProperty getDateProperty(){
        return date;
    }
    public SimpleStringProperty getBodyProperty(){
        return body;
    }
    
    Connection cnx = ConnexionSingleton.getInstance().getCnx();
    
    public List<Actualite> getListActu() {
        List<Actualite> list = new ArrayList<>();
        String requete = "SELECT * from actulite ";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Actualite A = new Actualite();
                A.setID_Actu(rs.getInt(1));
                A.setID_Personne(rs.getInt(2));
                A.setBody(rs.getString(3));
                A.setCategorie(rs.getString(4));
                A.setDate(rs.getString(5));

                list.add(A);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Actualite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    
    
    
}
