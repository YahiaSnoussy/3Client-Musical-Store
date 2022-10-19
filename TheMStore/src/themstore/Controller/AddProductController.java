/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import themstore.DAO.ProductDao;
import themstore.Entity.Product;

/**
 * FXML Controller class
 *
 * @author metal
 */
public class AddProductController implements Initializable {

    @FXML
    private AnchorPane btnAjouter;
    @FXML
    private TextField Labelch;
    @FXML
    private DatePicker Datech;
    @FXML
    private TextField Prixch;
    @FXML
    private Button image;
    @FXML
    private TextField Descriptionch;
    @FXML
    private Button btnAjout;
    
    String path = "";
    @FXML
    private ImageView imageViewAdd;
    @FXML
    private Button Btn_Return;

    /**
     * Initializes the controller class.
     */
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
   
    }   
    
    @FXML
    private void ajouter(ActionEvent event) {
        String label = Labelch.getText();
        String description = Descriptionch.getText();
        String date = Datech.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Double prix = (Double) Double.parseDouble(Prixch.getText()) + 0;
        ProductDao pdao = new ProductDao();
        Product p = new Product(path, label, prix, date, description);
        pdao.AddProduct(p);
         System.out.println("Product ajouté");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Product Has been added");

            alert.showAndWait();
    }

    @FXML
    private void image(ActionEvent event)throws MalformedURLException, IOException {

         BufferedOutputStream stream = null;
         String globalPath="C:\\xampp\\htdocs\\theMstore\\src\\themstore\\Images\\";


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
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);}

    }

    
    
}
