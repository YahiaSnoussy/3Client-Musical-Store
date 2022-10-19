/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import themstore.DAO.PersonneDAO;
import themstore.Entity.Personne;

/**
 * FXML Controller class
 *
 * @author Hayou
 */
public class ListUsersController implements Initializable {
@FXML
    private TableView<Personne> table;
     @FXML
    private TableColumn<Personne, Integer> id;
    @FXML
    private TableColumn<Personne, String> fname;
     @FXML
    private TableColumn<Personne, String> lname;
      @FXML
    private TableColumn<Personne, String> ro;
      @FXML
    private TableColumn<Personne, String> mai;
       @FXML
    private TableColumn<Personne, String> usern;
        @FXML
    private TableColumn<Personne, String> psw;
         @FXML
    private TableColumn<Personne, String> foto;
             @FXML
    private TableColumn<Personne, String> numtel;
    
    @FXML
    private Button btn_up;
    @FXML
    private Button back;
    @FXML
    private Label IdLabel;



     
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
      
      
      
      btn_up.setOnAction(event -> {
            try {FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/themstore/View/UpdateDeleteUser.fxml"));
                Parent page2 = loader.load();
                
                Scene scene = new Scene(page2);
                UpdateDeleteUserController update= loader.getController();
                update.initData(table.getSelectionModel().getSelectedItem());
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
    @FXML
   private void afficherAction(ActionEvent event){
        PersonneDAO e = new PersonneDAO(); 
        List<Personne> listf = e.displayAll();
       // List<Formation> list = fService.getListFormation();
       ObservableList<Personne> flist = FXCollections.observableArrayList(listf);
      //  ObservableList<Formation> fList = FXCollections.observableArrayList(listf);
      id.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname.setCellValueFactory(new PropertyValueFactory<>("nom"));
        lname.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       ro.setCellValueFactory(new PropertyValueFactory<>("role"));
       mai.setCellValueFactory(new PropertyValueFactory<>("email"));
        usern.setCellValueFactory(new PropertyValueFactory<>("username"));
        psw.setCellValueFactory(new PropertyValueFactory<>("password"));
        foto.setCellValueFactory(new PropertyValueFactory<>("photo"));
        numtel.setCellValueFactory(new PropertyValueFactory<>("num_telephone"));
        table.setItems(flist);
        
       
        
        
}
   @FXML
          public void SupprimerUser(ActionEvent event) throws IOException, AWTException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	alert.setTitle("Confirmation Dialog");
	alert.setHeaderText("Do you want to save your current changes?");
	alert.setContentText("");
	
	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK){ 
        PersonneDAO cs = new PersonneDAO();
        Personne c = table.getSelectionModel().getSelectedItem();
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
        cs.delete(c);
        }
        
        
    }
      
}


