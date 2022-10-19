/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;

import java.io.IOException;
import themstore.DAO.ActuDao;
import themstore.Entity.Actualite;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author willy
 */
public class AjoutActuController implements Initializable {

    @FXML
    private HTMLEditor bodyActu;
    @FXML
    private DatePicker dateActu;
    @FXML
    private Button pubActu;
    @FXML
    private TextField categorie;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pubActu.setOnAction(event -> { 
            String date = dateActu.getValue().toString();
            String cat = categorie.getText();
            String body = bodyActu.getHtmlText();
            if (date.isEmpty()||cat.isEmpty()||body.isEmpty())
            {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setHeaderText(null);
                alert1.setContentText("Veuillez remplir tous les champs");
                alert1.showAndWait();
            }
            else
            {
                Actualite A = new Actualite(dateActu.getValue().toString(), bodyActu.getHtmlText(), categorie.getText());
            
            ActuDao Adao = ActuDao.getInstance();
            Adao.insert(A);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Actualité insérée avec succés!");
        alert.show();
            }
            
        //nom.setText("");
        //prenom.setText("");
        });
        home.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/acceuil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjoutActuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    }    
    

