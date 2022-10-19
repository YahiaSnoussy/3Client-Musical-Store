/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import themstore.DAO.NotificationDAO;

import themstore.Entity.Notification;


/**
 * FXML Controller class
 *
 * @author Hayou
 */
public class ListOublieController implements Initializable {
  @FXML
    private TableView<Notification> table;
     @FXML
    private TableColumn<Notification, Integer> idd;
    @FXML
    private TableColumn<Notification, String> adressmail;
     
    
    @FXML
    private Button back;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   back.setOnAction(event -> {
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
       
        
 table.setEditable(true);
        // TODO
        
       ActionEvent event = new ActionEvent();
       afficherAction(event);
    }    
   private void afficherAction(ActionEvent event){
        NotificationDAO e = new NotificationDAO(); 
        List<Notification> listf = e.displayAll();
       
       ObservableList<Notification> flist = FXCollections.observableArrayList(listf);
      
      idd.setCellValueFactory(new PropertyValueFactory<>("id"));
        adressmail.setCellValueFactory(new PropertyValueFactory<>("emailtoreply"));
        
        table.setItems(flist);
        
       
        
        
}
   @FXML
          public void SupprimerMsg(ActionEvent event) throws IOException, AWTException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	alert.setTitle("Confirmation Dialog");
	alert.setHeaderText("Do you want to save your current changes?");
	alert.setContentText("");
	
	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK){ 
        NotificationDAO cs = new NotificationDAO();
        Notification c = table.getSelectionModel().getSelectedItem();
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
        cs.delete(c);
        }
        
        
    }
      
}