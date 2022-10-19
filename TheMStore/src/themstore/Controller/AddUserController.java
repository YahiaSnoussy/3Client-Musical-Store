/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;

import java.awt.AWTException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import themstore.DAO.PersonneDAO;
import themstore.Entity.Personne;
import themstore.JDBC.connection;


/**
 * FXML Controller class
 *
 * @author Hayou
 */
public class AddUserController implements Initializable {
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
    private TextField photo;
 @FXML
    private TextField num;
 String path ="";
 
 @FXML
    private Button retour;
 @FXML
    private Button btn;
    @FXML
    private Button image;
    @FXML
    private ImageView imageViewAdd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        // TODO
        
    
        btn.setOnAction(event -> {
            
 /* Personne p = new Personne ();
        
         p.setNom(nom.getText());
        p.setPrenom(prenom.getText());
        p.setRole(role.getText());
        
        p.setEmail(email.getText());
         p.setUsername(username.getText());
        p.setPassword(password.getText());
        /* p.setPhoto(photo.getText()); 
        p.setNum_telephone(num.getText());
      
        PersonneDAO sp = PersonneDAO.getInstance();
        sp.insert(p); */
 if (control()){
 
        String Nom=nom.getText();
        String Prenom=prenom.getText();
        String Role=role.getText();
        String Email=email.getText();
        String Username=username.getText();
        String Password=password.getText();
        String Num_telephone=num.getText();
        PersonneDAO pd = new PersonneDAO ();
        Personne p = new Personne();
        p.setNom(Nom);
         p.setEmail(Email);
          p.setPrenom(Prenom);
           p.setRole(Role);
            p.setUsername(Username);
             p.setPassword(Password);
             p.setPhoto(path);
             p.setNum_telephone(Num_telephone);
        pd.insert(p);
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Personne insérée avec succés!");
        alert.show();
 }
      
        });
        retour.setOnAction(event -> {
            try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/themstore/View/HomePage.fxml"));
               
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
           
    }
  private boolean control (){ 
      if (IsNotInteger(num.getText()) || (num.getText().length()> 8) ||(num.getText().length()< 8))
      {
          Alert alert = new Alert (Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText("le numero doit etre des numero et de 8 chiffres");
          alert.showAndWait();
          return false;
      }
      
         else if (nom.getText().length()<3)
                 {
                  Alert alert = new Alert (Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText("le nom est invalide!");
          alert.showAndWait();
          return false;
                  }
      else if (prenom.getText().length()<3)
                 {
                  Alert alert = new Alert (Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText("le prenom est invalide!");
          alert.showAndWait();
          return false;
                  }
      else if (username.getText().length()<6)
                 {
                  Alert alert = new Alert (Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText("le username est invalide!");
          alert.showAndWait();
          return false;
                  }
       else if (password.getText().length()<6)
                 {
                  Alert alert = new Alert (Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText("le mot de passe est invalide!");
          alert.showAndWait();
          return false;
                  }
    /*  else if(role.getText().equals("journaliste") || role.getText().equals("moderateur") || role.getText().equals("User") || role.getText().equals("admin") )
                 {
                  Alert alert = new Alert (Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText("le role est invalide!");
          alert.showAndWait();
          return false;
                  } */
       
      else if (email.getText().matches("\\w{3,}@."))
                 {
                  Alert alert = new Alert (Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText(null);
          alert.setContentText("Adresse email est invalide!");
          alert.showAndWait();
          return false;
                  }
      return true;
  }

  public static boolean IsNotInteger (String s) {
      try {
          Integer.parseInt(s);
          
      }
      catch (NumberFormatException | NullPointerException e){
          return true;
      }
      return false;
  }
  
  
    @FXML
    private void image(ActionEvent event) {
        BufferedOutputStream stream = null;
         String globalPath="C:\\xampp\\htdocs\\TheMStore\\src\\themstore\\Images\\ulpoadimage\\";


        try {

        JFileChooser fileChooser = new JFileChooser(); 
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getName();

            Path p = selectedFile.toPath();
            byte[] bytes = Files.readAllBytes(p); 
            File dir = new File(globalPath);

            File serverFile = new File(dir.getAbsolutePath()+File.separator + path);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();


            String path2 = selectedFile.toURI().toURL().toString();
            Image image = new Image(path2);
            imageViewAdd.setImage(image);

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("NoData");
        }

                } catch (IOException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);}

    }
    }

   
   
    

