/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Controller;

import themstore.DAO.ActuDao;
import themstore.Entity.Actualite;
import themstore.Entity.like;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import themstore.Entity.Comment;

/**
 *
 * @author willy
 */
class ListData2 {
    private ObservableList<Actualite> actualite=FXCollections.observableArrayList();
    private ObservableList<like> like=FXCollections.observableArrayList();
    private ObservableList<Comment> comment=FXCollections.observableArrayList();
    public ListData2() {
        
        ActuDao pdao=ActuDao.getInstance();
        //like = pdao.displayAlll(id);
        actualite= pdao.displayAll();
        System.out.println(actualite);
    }
    public ListData2(int id) {
        
        ActuDao pdao=ActuDao.getInstance();
        like = pdao.displayAlll(id);
        comment = pdao.displayAl(id);
        actualite= pdao.displayAll();
        System.out.println(actualite);
    }
    public ObservableList<Comment> getComment(){
        return comment;
    }
    public ObservableList<like> getLike(){
        return like;
    }
    public ObservableList<Actualite> getActu(){
        return actualite;
    }
    
}
