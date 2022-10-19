/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;

import themstore.DAO.PersonneDAO;
import themstore.Entity.Personne;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



/**
 *
 * @author wiemhjiri
 */
public class ListData {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Personne> persons=FXCollections.observableArrayList();

    public ListData() {
        
        PersonneDAO pdao=PersonneDAO.getInstance();
        persons= pdao.displayAll();
        System.out.println(persons);
    }
    
    public ObservableList<Personne> getPersons(){
        return persons;
    }
   
}
