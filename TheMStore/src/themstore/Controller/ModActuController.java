/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;

import java.io.IOException;
import themstore.DAO.ActuDao;
import themstore.Entity.Actualite;
import themstore.Entity.like;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author willy
 */
public class ModActuController implements Initializable {

    @FXML
    private HTMLEditor actuView;
    @FXML
    private Button modBtn;
    @FXML
    private TextField searchTxt;
    @FXML
    private TableView<Actualite> listTab;
    @FXML
    private Button supBtn;
    @FXML
    private TableColumn<Actualite, String> id_col;
    @FXML
    private TableColumn<Actualite, String> cat_col;
    @FXML
    private TableColumn<Actualite, String> date_col;

    
    private ListData2 listdata = new ListData2();
    private Button refresh;
    @FXML
    private Button home;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
     //private ListData listdata = new ListData();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listTab.setItems(listdata.getActu());
        id_col.setCellValueFactory(cell -> cell.
                getValue().getCategorieProperty());
        cat_col.setCellValueFactory(cell -> cell.
                getValue().getBodyProperty());
        date_col.setCellValueFactory(cell -> cell.
                getValue().getDateProperty());
        //clic souris----------------------------------------------------------------------------------------------------
        listTab.setOnMouseClicked(event->{
            actuView.setHtmlText(String.valueOf(listdata.getActu()
                .get(listTab.getSelectionModel().getSelectedIndex()).getBody())); 
        });
        
        //suppression-------------------------------------------------------------------------------------------------------
        supBtn.setOnAction(event -> { 
            Actualite A = new Actualite(listdata.getActu()
                .get(listTab.getSelectionModel().getSelectedIndex()).getID_Actu());
            ActuDao Adao = ActuDao.getInstance();
            Adao.delete(A);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Actualité supprimée avec succés!");
        alert.show();
        listdata.getActu().clear();
            listdata = new ListData2();
            listTab.setItems(listdata.getActu());
        //nom.setText("");
        //prenom.setText("");
        });
        
        //modification------------------------------------------------------------------------------------------------------
        modBtn.setOnAction(event -> { 
            Actualite A = new Actualite(listdata.getActu()
                .get(listTab.getSelectionModel().getSelectedIndex()).getID_Actu(), actuView.getHtmlText());
            ActuDao Adao = ActuDao.getInstance();
            Adao.update(A);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Actualité modifiee avec succés!");
        alert.show();
        listdata.getActu().clear();
            listdata = new ListData2();
            listTab.setItems(listdata.getActu());
        });
        
        
            // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Actualite> filteredData = new FilteredList<>(listdata.getActu(), b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(actua -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (actua.getCategorie().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Actualite> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(listTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		listTab.setItems(sortedData);
                
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

    

