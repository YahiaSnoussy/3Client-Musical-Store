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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
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
public class UpdateProductController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button update;
    @FXML
    private Button Btn_Return;
    @FXML
    private TextField LabelText;
    @FXML
    private TextField PriceText;
    @FXML
    private TextArea DescriptionText ;
    private Stage primaryStage;
    private FileChooser filechooser ;
    private File file ;
    public static int id;
    ProductDao pdao = new ProductDao();
    Product p = pdao.displayById(id);
    @FXML
    private ImageView image;
    @FXML
    private Button image1;
    String path = "";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              LabelText.setText(p.getLabel());
              PriceText.setText(String.valueOf(p.getPrice()));
              DescriptionText.setText(p.getDescription());             
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
            Image ima = new Image(path2);
            image.setImage(ima);

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("NoData");
        }

                } catch (IOException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);}

    }

   
    @FXML
    private void update (ActionEvent event){
    String label = LabelText.getText();
    String description = DescriptionText.getText();
    Double price = (Double) Double.parseDouble(PriceText.getText()) + 0;
    ProductDao pdao = new ProductDao();
    Product p = new Product(label, price, path , description);
    p.setID(id);
    pdao.UpdateProduct(p);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Product Has been Updated");

            alert.showAndWait();
    
}
}

       