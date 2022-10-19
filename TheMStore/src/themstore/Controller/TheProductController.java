/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import themstore.DAO.ProductDao;
import themstore.Entity.Product;

/**
 * FXML Controller class
 *
 * @author metal
 */
public class TheProductController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML 
    private Button Btn_Return ;
    @FXML 
    private Button Btn_update ;
    @FXML 
    private Button Btn_delete ;
    @FXML 
    private Button signal ;
    
    
   
    
    public static int id;

    ProductDao pdao = new ProductDao();
    Product p = pdao.displayById(id);
    @FXML
    private Label NomProduit;
    @FXML
    private Label date;
    @FXML
    private ImageView image;
    @FXML
    private Label prix;
    @FXML
    private Text description;
    @FXML
    private AnchorPane holderPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
             NomProduit.setText(p.getLabel());
             description.setText(p.getDescription());
             prix.setText(Double.toString(p.getPrice()));
             date.setText(p.getDate());
              String img = "http://localhost/theMstore/src/themstore/Images/" + p.getImage();
             image.setImage(new Image(img));


             
             Btn_Return.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/ListProduct2.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
           signal.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/FXMLDocument.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
               
                   
    }    

    @FXML
    private void update(ActionEvent event) throws IOException {
         UpdateProductController.id = p.getID();
        holderPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/themstore/View/UpdateProduct.fxml"));
        holderPane.getChildren().add(parent);
        holderPane.toFront();
    }
    
    @FXML
    private void delete (ActionEvent event)throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION DIALOG");
        alert.setHeaderText("NOTICE");
        alert.setContentText("ARE YOU SURE TO DELETE?");
        Optional <ButtonType> action = alert.showAndWait();
        if(action.get() == ButtonType.OK){
            ProductDao pdao = new ProductDao();
            Product p2 = new Product ();
            p2.setID(id);
            pdao.DeleteProduct(p2);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DELETE PRODUCT");
            alert.setHeaderText("INFORMATION");
            alert.setContentText("Product HAS BEEN DELETED");
            alert.showAndWait();
        }
        
    }
  
}
