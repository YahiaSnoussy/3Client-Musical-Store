/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Hayou
 */
public class TheMStore extends Application {

    private Stage primaryStage;
     
    private Parent parentPage;
   
    @Override
    public void start(Stage primaryStage) throws IOException {
      this.primaryStage = primaryStage;
        this.primaryStage.setTitle("The M-Store");
        
        parentPage = FXMLLoader.load(getClass().getResource("/themstore/View/Accueil.fxml"));
        Scene scene = new Scene(parentPage);
        
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args) ;
    }
    
}

