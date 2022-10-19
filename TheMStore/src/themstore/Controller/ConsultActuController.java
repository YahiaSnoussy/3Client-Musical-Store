/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;

import java.io.IOException;
import java.io.StringReader;
import static java.lang.System.out;
import themstore.DAO.ActuDao;
import themstore.Entity.Comment;
import themstore.Entity.like;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.text.Document;

/**
 * FXML Controller class
 *
 * @author willy
 */
public class ConsultActuController implements Initializable {

    @FXML
    private WebView actu;
    @FXML
    private TextField comment;
    @FXML
    private Button comEnvoyer;
    @FXML
    private Button imprimer;
    @FXML
    private Button like;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    private ListData2 listdata = new ListData2();
    @FXML
    private Label nb_like;
    @FXML
    private Pagination page;
    @FXML
    private TextArea comShow;
    @FXML
    private Button home;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // actu.set
       comShow.setEditable(false);
       //listdata.getActu().clear();
       listdata = new ListData2(listdata.getActu().get(page.getCurrentPageIndex()).getID_Actu());
       if(listdata.getComment().size()!=0)
       {
           for(int i=0;i<listdata.getComment().size();i++)
           {
               comShow.setText("***"+listdata.getComment().get(i).getDate()+ "     " +listdata.getComment().get(i).getBody() );
           }
       }
            
       page.setPageCount(listdata.getActu().size());
       WebEngine webEngine = actu.getEngine();
       webEngine.loadContent(listdata.getActu().get(page.getCurrentPageIndex()).getBody());
//page selector------------------------------------------------------------------------------------------------       
       page.currentPageIndexProperty().addListener(event -> {
           //listdata = new ListData(listdata.getActu().get(page.getCurrentPageIndex()).getID_Actu());
           System.out.printf("Nouvelle page %d", page.getCurrentPageIndex()).println();
           webEngine.loadContent(listdata.getActu().get(page.getCurrentPageIndex()).getBody());
           listdata.getComment().clear();
           listdata = new ListData2(listdata.getActu().get(page.getCurrentPageIndex()).getID_Actu());
       if(listdata.getComment().size()!=0)
       {
           for(int i=0;i<listdata.getComment().size();i++)
           {
               comShow.setText("***"+listdata.getComment().get(i).getDate()+ "     " +listdata.getComment().get(i).getBody() );
           }
       }
               });
//like bouton--------------------------------------------------------------------------------------------------
       like.setOnAction(event ->{
           like l = new like(listdata.getActu().get(page.getCurrentPageIndex()).getID_Actu(),listdata.getActu().get(page.getCurrentPageIndex()).getID_Personne());
           ActuDao Adao = ActuDao.getInstance();
            Adao.insert(l);
            listdata.getLike().clear();
            listdata = new ListData2(listdata.getActu().get(page.getCurrentPageIndex()).getID_Actu());
            nb_like.setText(String.valueOf(listdata.getLike().size()));
       });
//envoyer comment------------------------------------------------------------------------------------------------
       comEnvoyer.setOnAction(event ->{
           String body=comment.getText();
           DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
           Date date = new Date();
           if (body.isEmpty())
            {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setHeaderText(null);
                alert1.setContentText("Veuillez remplir tous les champs");
                alert1.showAndWait();
            }
           else
            {
                //Actualite A = new Actualite(dateActu.getValue().toString(), bodyActu.getHtmlText(), categorie.getText());
            Comment C = new Comment(listdata.getActu().get(page.getCurrentPageIndex()).getID_Actu(), listdata.getActu().get(page.getCurrentPageIndex()).getID_Personne(), body, format.format(date));
            ActuDao Adao = ActuDao.getInstance();
            Adao.insert(C);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Actualité insérée avec succés!");
        alert.show();
        listdata = new ListData2(listdata.getActu().get(page.getCurrentPageIndex()).getID_Actu());
       if(listdata.getComment().size()!=0)
       {
           for(int i=0;i<listdata.getComment().size();i++)
           {
               comShow.setText("***"+listdata.getComment().get(i).getDate()+ "     " +listdata.getComment().get(i).getBody() );
           }
       }
            }
       });
 // to pdf-----------------------------------------------------------------------------------------------------      
     imprimer.setOnAction(event -> {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) 
        {
       // webEngine.print();
        job.endJob ();
    }
     });
       
       home.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/themstore/View/Home2.fxml"));
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
