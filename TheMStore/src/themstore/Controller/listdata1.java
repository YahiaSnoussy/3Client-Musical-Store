/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;

import themstore.Entity.reclamation;
import themstore.DAO.recDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author riadh
 */

public class listdata1 {
     private ObservableList<reclamation> reclams=FXCollections.observableArrayList();

    public listdata1() {
        
        recDao pdao=recDao.getInstance();
        reclams= pdao.displayAll();
        System.out.println(reclams);
    }
    
    public ObservableList<reclamation> getRec(){
        return reclams;
    
}}
