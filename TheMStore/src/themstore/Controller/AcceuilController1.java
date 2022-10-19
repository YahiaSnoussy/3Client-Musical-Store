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
 * @author willy
 */
public class AcceuilController1 implements Initializable {

    @FXML
    private Button btnAjout;
    private Button btnConsult;
    @FXML
    private Button btnMod;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnAjout.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/ajoutActu.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjoutActuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
        
        btnMod.setOnAction(event -> {
            try {
                Parent page3 = FXMLLoader.load(getClass().getResource("/themstore/View/modActu.fxml"));
                Scene scene = new Scene(page3);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ModActuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        logout.setOnAction(event -> {
            try {
                Parent page3 = FXMLLoader.load(getClass().getResource("/themstore/View/Accueil.fxml"));
                Scene scene = new Scene(page3);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ModActuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
    }    
    
}
