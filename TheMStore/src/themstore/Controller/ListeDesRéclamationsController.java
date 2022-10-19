/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import themstore.DAO.recDao;
import themstore.Entity.reclamation;










public class ListeDesRéclamationsController implements Initializable {
   @FXML
    private Button retour;
   @FXML
    private Label labelliste;
    @FXML
    private TableView<reclamation> table;
    @FXML
    private TableColumn<reclamation, String> subject;
    @FXML
    private TableColumn<reclamation, String> msg;
    @FXML
    private Button delete;
  
    
      @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/themstore/View/FXMLDocument.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
    }
    public ArrayList<reclamation> extract(FXCollections c){
        ArrayList<reclamation> list = new ArrayList<reclamation>();
        FXCollections.observableArrayList(lData.getRec()).get(0).getId_rec();
        
        
        return list; 
    }
    private listdata1 lData = new listdata1();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setItems(lData.getRec());
        subject.setCellValueFactory(cell -> cell.
                getValue().getSujetProperty());
        msg.setCellValueFactory(cell -> cell.
                getValue().getMessageProperty());
          retour.setOnAction(event ->{
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/Home2.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }    
        });
    }

    @FXML
    private void buttondelete(ActionEvent event) {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation dialog");
        alert.setHeaderText("Do you want to delete?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get()== ButtonType.OK){
            recDao d = new recDao();
            reclamation s = table.getSelectionModel().getSelectedItem();
            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
            d.delete(s);
            
        }
        
    }
}
    
    

  
    

