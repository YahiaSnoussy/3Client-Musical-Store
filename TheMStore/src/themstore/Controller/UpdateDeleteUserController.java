/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import themstore.DAO.PersonneDAO;
import themstore.Entity.Personne;

/**
 * FXML Controller class
 *
 * @author Hayou
 */
public class UpdateDeleteUserController implements Initializable {
      private Personne p;

    @FXML
    private TextField nom;
 @FXML
    private TextField prenom;
 @FXML
    private TextField role;
 @FXML
    private TextField email;
 @FXML
    private TextField username;
 @FXML
    private TextField password;
 @FXML
    private TextField photo;
 @FXML
    private TextField num;
 
 @FXML
    private Button retour;
 @FXML
    private Button btn_update;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          retour.setOnAction(event -> {
            try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/themstore/View/ListUsers.fxml"));
                
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          btn_update.setOnAction(event -> {
           Personne p2 = new Personne ();
           p2.setId(p.getId());
           p2.setNom(nom.getText());
           p2.setPrenom(prenom.getText());
           p2.setRole(role.getText());
        
           p2.setEmail(email.getText());
           p2.setUsername(username.getText());
           p2.setPassword(password.getText());
           p2.setPhoto(photo.getText());
           p2.setNum_telephone(num.getText());
           PersonneDAO pdao = PersonneDAO.getInstance();
           pdao.update(p2);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
           alert.setHeaderText(null);
           alert.setContentText("the Person's informations has been updated successfully");
           alert.show();
        });
        
    }   
    
    
  public void initData (Personne e){
   p=e;
   nom.setText(p.getNom());
   prenom.setText(p.getPrenom());
   role.setText(p.getRole());
   email.setText(p.getEmail());
   username.setText(p.getUsername());
   password.setText(p.getPassword());
   photo.setText(p.getPhoto());
   num.setText(p.getNum_telephone());
   
   

   }
   
}
