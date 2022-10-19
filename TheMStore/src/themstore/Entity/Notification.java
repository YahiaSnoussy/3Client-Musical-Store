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
 * @author Hayou
 */
public class Notification {
     private SimpleIntegerProperty id;
    private SimpleStringProperty emailtoreply;

    public Notification() {
    }

    public Notification(SimpleIntegerProperty id, SimpleStringProperty emailtoreply) {
        this.id = id;
        this.emailtoreply = emailtoreply;
    }
    
    
     public Notification( SimpleStringProperty emailtoreply) {
        
        this.emailtoreply = emailtoreply;
    }
     
     public Notification( String emailtoreply) {
        
        this.emailtoreply = new SimpleStringProperty (emailtoreply);
    }
     
    public Integer getId() {
        return id.get(); 
    }

    public SimpleStringProperty getEmailtoreplyProperty() {
        return emailtoreply;
    }
    public String getEmailtoreply() {
        return emailtoreply.get();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    public void setEmailtoreply(String emailtoreply) {
        this.emailtoreply = new SimpleStringProperty(emailtoreply);
    }

    public void setId(Integer id) {
        this.id = new SimpleIntegerProperty(id);
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
   
    
}
