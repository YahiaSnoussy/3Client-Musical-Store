/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import themstore.DAO.ProductDao;
import themstore.Entity.Product;

/**
 * FXML Controller class
 *
 * @author metal
 */
public class ListProduct2Controller implements Initializable {

    
    
    @FXML
    private Button Btn_Return ;

     
    private VBox vbox2;
    @FXML
    private ImageView image2;
    @FXML
    private Label label2;
    @FXML
    private Button btn2_product;
    private VBox vbox1;
    @FXML
    private ImageView image1;
    @FXML
    private Label label1;
    @FXML
    private Button btn1_product;
    private VBox vbox3;
    @FXML
    private ImageView image3;
    @FXML
    private Label label3;
    @FXML
    private Button btn3_product;
    private VBox vbox4;
    @FXML
    private ImageView image4;
    @FXML
    private Label label4;
    @FXML
    private Button btn4_product;
    @FXML
    private ImageView productsuivant;
    @FXML
    private ImageView productprecedent;
    private static int a, b, c, d, i, idproduct1, idproduct2, idproduct3, idproduct4;
    private ObservableList<Product> data;
    public static String value;
    @FXML
    private ImageView image11;
    @FXML
    private AnchorPane holderPane;
    @FXML
    private Button Btn_add;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String imgsuivant = "http://localhost/theMstore/src/themstore/Images/system/next.JPG" ;
        productsuivant.setImage(new Image (imgsuivant));
        String imgprecedent = "http://localhost/theMstore/src/themstore/Images/system/previous.JPG" ;
        productprecedent.setImage(new Image (imgprecedent));
     
        Btn_Return.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/Home2.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Btn_add.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/AddProduct.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });


        a = 0;
        b = 1;
        c = 2;
        d = 3;
        productprecedent.setVisible(false);
        buildData();

    }
    
    private void buildData() {
        data = FXCollections.observableArrayList();
        ProductDao su = ProductDao.getInstance();
        btn1_product.setVisible(false);
        label1.setVisible(false);
        image1.setVisible(false);
        btn2_product.setVisible(false);
        label2.setVisible(false);
        image2.setVisible(false);
        btn3_product.setVisible(false);
        label3.setVisible(false);
        image3.setVisible(false);
        btn4_product.setVisible(false);
        label4.setVisible(false);
        image4.setVisible(false);
        i = 0;
        int j = 0;
        try {
            ResultSet rs = su.displayAll();
            while (rs.next()) {

                Product pr = new Product();
                pr.setID(rs.getInt(1));
                pr.setLabel(rs.getString(3));
                pr.setPrice(rs.getDouble(4));
                pr.setImage(rs.getString(2));
                pr.setDescription(rs.getString(6));
                pr.setDate(rs.getString(5));
              

                if (value == null) {
                    data.add(pr);
                    j++;
                } ////////recherche ///////// 
                else if (pr.getLabel().toLowerCase().contains(value.toLowerCase()) || pr.getDescription().toLowerCase().contains(value.toLowerCase())) {
                    data.add(pr);
                    j++;

                }

                while (i < j) {
                    if (i == a) { 
                        btn1_product.setVisible(true);
                        label1.setVisible(true);
                        image1.setVisible(true);
                        label1.setText(data.get(i).getLabel());
                       String img = "http://localhost/theMstore/src/themstore/Images/" + data.get(i).getImage();
                        idproduct1 = data.get(i).getID();
                        image1.setImage(new Image(img));
                    } else if (i == b) {
                        btn2_product.setVisible(true);
                        label2.setVisible(true);
                        image2.setVisible(true);
                        label2.setText(data.get(i).getLabel());
                      String img = "http://localhost/theMstore/src/themstore/Images/" + data.get(i).getImage();
                        idproduct2 = data.get(i).getID();
                       image2.setImage(new Image(img));
                    } else if (i == c) {
                        btn3_product.setVisible(true);
                        label3.setVisible(true);
                        image3.setVisible(true);
                        label3.setText(data.get(i).getLabel());  
                         String img = "http://localhost/theMstore/src/themstore/Images/" + data.get(i).getImage();
                        idproduct3 = data.get(i).getID();
                        image3.setImage(new Image(img));
                    } else if (i == d) {
                        btn4_product.setVisible(true);
                        label4.setVisible(true);
                        image4.setVisible(true);
                        label4.setText(data.get(i).getLabel());
                         String img = "http://localhost/theMstore/src/themstore/Images/" + data.get(i).getImage();
                        idproduct4 = data.get(i).getID();
                        image4.setImage(new Image(img));
                    }

                    i++;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ListProduct2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @FXML
    private void affichage_2(ActionEvent event) throws IOException {
        TheProductController.id = idproduct2;
        AnchorPane page1 = FXMLLoader.load(getClass().getResource("/themstore/View/TheProduct.fxml"));
        setNode(page1);

    }

    @FXML
    private void affichage_1(ActionEvent event) throws IOException {
        TheProductController.id = idproduct1;
        AnchorPane page1 = FXMLLoader.load(getClass().getResource("/themstore/View/TheProduct.fxml"));
        setNode(page1);
    }

    @FXML
    private void affichage_3(ActionEvent event) throws IOException {
        TheProductController.id = idproduct3;
        AnchorPane page1 = FXMLLoader.load(getClass().getResource("/themstore/View/TheProduct.fxml"));
        setNode(page1);
    }

    @FXML
    private void affichage_4(ActionEvent event) throws IOException {
        TheProductController.id = idproduct4;
        AnchorPane page1 = FXMLLoader.load(getClass().getResource("/themstore/View/TheProduct.fxml"));
        setNode(page1);
    }
    
       @FXML
    private void suivant_product(MouseEvent event) {
        a = a + 4;
        b = b + 4;
        c = c + 4;
        d = d + 4;
        productprecedent.setVisible(true);
        buildData();
        if (b == i || a == i || c == i || d == i) {
            productsuivant.setVisible(false);
        }
    }

    @FXML
    private void product_precedent(MouseEvent event) {
        a = a - 4;
        b = b - 4;
        c = c - 4;
        d = d - 4;
        
        productsuivant.setVisible(true);

        if (a == 0) {
            productprecedent.setVisible(false);
        }
        buildData();
    }
    
    public void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

 

    
   
}
