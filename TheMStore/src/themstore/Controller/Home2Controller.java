/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author metal
 */
public class Home2Controller implements Initializable {

    @FXML
    private Button out;
    @FXML
    private Button actu;
    @FXML
    private Button produit;
    @FXML
    private Button beat;
    @FXML
    private Button pan;
    @FXML
    private Button reclam;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
        produit.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/ListProduct2.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         actu.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/consultActu.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         reclam.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/listedesréclamations.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          out.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/Accueil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
}
