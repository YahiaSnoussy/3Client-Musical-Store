
package themstore.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import themstore.DAO.PersonneDAO;
import themstore.Entity.Personne;

import themstore.JDBC.connection;


/**
 * FXML Controller class
 *
 * @author Hayou
 */
public class AccueilController implements Initializable {
 
    @FXML 
    Label dbConlb;
    @FXML 
    Label check;
        @FXML
    private Button status;
         @FXML
    private TextField username;
    @FXML
    private TextField password;
    
    public static int test ;
    
    private Parent parentPage;
    private Stage primaryStage;
        @FXML
    private Button login;
    @FXML
    private Hyperlink hyperinsc;
        
         
    @FXML
    public void statusDB(ActionEvent e) throws IOException,SQLException{

if(! connection.connect().isClosed()){
    dbConlb.setText("Connected successfully");
    
}else{
     dbConlb.setText("Not connected");
}

}
    @FXML
  public void EnterCP(ActionEvent e) throws IOException,SQLException{

 Personne p = new Personne();
        p.setUsername(username.getText());
        p.setPassword(password.getText());
        
         PersonneDAO sp = new PersonneDAO();
        test=sp.authentification(p);
        
        System.err.println(test);
           int id=test;
          
         String test1= sp.rechercherparrole(id); 
           
        
    
        if((test!=0) && (test1.equals("admin"))) {  
        
                try {
                    check.setText("Connected successfully");
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/HomePage.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
               
    }
        
        
       else if((test!=0) && (test1.equals("User"))) {  
        
                try {
                    check.setText("Connected successfully");
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/Home2.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
               
    }
       else if((test!=0) && (test1.equals("journalist"))) {  
        
                try {
                    check.setText("Connected successfully");
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/acceuil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
               
    }
        else if((test!=0) && (test1.equals("moderateur"))) {  
        
                try {
                    check.setText("Connected successfully");
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/FXMLDocument.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
               
    }
        

        
       else {
           check.setText("Something went wrong ,try again!");             }    
        }
  
  

 
  
  
    @FXML
     public void insc(ActionEvent e) throws IOException,SQLException{

Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/Inscrire.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }


    @FXML
 public void oub(ActionEvent e) throws IOException,SQLException{

Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/Oublie.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
