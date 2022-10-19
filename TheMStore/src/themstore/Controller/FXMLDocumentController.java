/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;

import themstore.DAO.recDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import themstore.Entity.reclamation;

/**
 *
 * @author riadh
 */
public class FXMLDocumentController implements Initializable {
    
   
    @FXML
    private Button ajouterreclam;

    @FXML
    private TextField writereclam;
    @FXML
    private TextField sujet;
    @FXML
    private Button listereclam;
    @FXML
    private Button logout;
    @FXML
    void ajouter_réclamation(ActionEvent event) {
/*StringSelection stringSelection = new StringSelection (writereclam.getText());
Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
clpbrd.setContents (stringSelection, null);
File file=new File("reclamation.txt");

    }*/}

    @FXML
    void réclam(ActionEvent event) {

    }
    void afficher_liste(ActionEvent event)throws IOException{
          try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/listedesréclamations.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        logout.setOnAction(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/themstore/View/Accueil.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListData());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        
        ajouterreclam.setOnAction(event -> {
            
            
            reclamation p = new reclamation();
            p.setMessage(writereclam.getText());
             p.setSujet(sujet.getText());
            recDao pdao = recDao.getInstance();
            pdao.insert(p);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("reclamation user");
        alert.setContentText("reclamation insérée avec succés!");
        alert.show();
       
      //  nom.setText("");
       // prenom.setText("");
        });
        
        listereclam.setOnAction(event ->{
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/ListProduct2.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }    
        });
         
    
}}
