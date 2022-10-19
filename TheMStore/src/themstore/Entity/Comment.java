/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author willy
 */
public class Comment {
    private SimpleIntegerProperty id_comment;
    private SimpleIntegerProperty ID_Actu;
    private SimpleIntegerProperty ID_Personne;
    private SimpleStringProperty body;
    private SimpleStringProperty date;

    public Comment(int id_comment, int ID_Actu, int ID_Personne, String body, String date) {
        this.id_comment = new SimpleIntegerProperty(id_comment);
        this.ID_Actu = new SimpleIntegerProperty(ID_Actu);
        this.ID_Personne = new SimpleIntegerProperty(ID_Personne);
        this.body = new SimpleStringProperty(body);
        this.date = new SimpleStringProperty(date);
    }

    public Comment(int id_Actu, int id_Personne, String body, String format) {
        this.ID_Actu = new SimpleIntegerProperty(id_Actu);
        this.ID_Personne = new SimpleIntegerProperty(id_Personne);
        this.body = new SimpleStringProperty(body);
        this.date = new SimpleStringProperty(format);
    }

    public String getBody() {
        return body.get();
    }

    public void setBody(String body) {
        this.body = new SimpleStringProperty(body);
    }

    public Comment() {
        
    }

    public int getId_comment() {
        return id_comment.get();
    }

    public void setId_comment(int id_comment) {
        this.id_comment = new SimpleIntegerProperty (id_comment);
    }

    public int getID_Actu() {
        return ID_Actu.get();
    }

    public void setID_Actu(int ID_Actu) {
        this.ID_Actu = new SimpleIntegerProperty(ID_Actu);
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
    
    
    
    
    
    
}
