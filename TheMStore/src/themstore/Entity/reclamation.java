/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Entity;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class reclamation {
    private SimpleIntegerProperty id_rec;
    private SimpleIntegerProperty id_produit;
    private SimpleIntegerProperty id_user;
    private SimpleStringProperty sujet;
    private SimpleStringProperty message;
    private SimpleIntegerProperty id_moderateur;

    reclamation(String message, String sujet) {
        this.message=new SimpleStringProperty (message);
        this.sujet=new SimpleStringProperty(sujet);
       
       
    }

    public reclamation() {
    }

    public int getId_rec() {
        return id_rec.get();
    }

    public void setId_rec(int id_rec) {
        this.id_rec =new SimpleIntegerProperty(id_rec);
    }

    public int getId_produit() {
        return id_produit.get();
    }

    public void setId_produit(int id_produit) {
        this.id_produit = new SimpleIntegerProperty(id_produit);
    }

    public int getId_user() {
        return id_user.get();
    }

    public void setId_user(int id_user) {
        this.id_user = new SimpleIntegerProperty(id_user);
    }

    public String getSujet() {
        return sujet.get();
    }

    public void setSujet(String sujet) {
        this.sujet = new SimpleStringProperty(sujet);
    }

    public String getMessage() {
        return message.get();
    }

    public void setMessage(String message) {
        this.message =new SimpleStringProperty( message);
    }

    public int getId_moderateur() {
        return id_moderateur.get();
    }

    public void setId_moderateur(int id_moderateur) {
        this.id_moderateur = new SimpleIntegerProperty(id_moderateur);
    }
     public SimpleStringProperty getSujetProperty(){
        return sujet;
    }
    public SimpleStringProperty getMessageProperty(){
        return message;
    }
    
    
}

