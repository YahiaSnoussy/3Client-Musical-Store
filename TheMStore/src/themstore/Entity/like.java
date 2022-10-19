/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Entity;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author willy
 */
public class like {
    private SimpleIntegerProperty id_like;
    private SimpleIntegerProperty ID_Actu;
    private SimpleIntegerProperty ID_Perso;

    public like(int id_like, int ID_Actu, int ID_Perso) {
        this.id_like = new SimpleIntegerProperty(id_like);
        this.ID_Actu = new SimpleIntegerProperty(ID_Actu);
        this.ID_Perso = new SimpleIntegerProperty(ID_Perso);
    }

    public like() {
    }

    public like(int id_Actu, int id_Personne) {
        this.ID_Actu = new SimpleIntegerProperty(id_Actu);
        this.ID_Perso = new SimpleIntegerProperty(id_Personne);
    }

    public int getId_like() {
        return id_like.get();
    }

    public void setId_like(int id_like) {
        this.id_like = new SimpleIntegerProperty(id_like);    }

    public int getID_Actu() {
        return ID_Actu.get();
    }

    public void setID_Actu(int ID_Actu) {
        this.ID_Actu = new SimpleIntegerProperty(ID_Actu);
    }

    public int getID_Perso() {
        return ID_Perso.get();
    }

    public void setID_Perso(int ID_Perso) {
       this.ID_Perso = new SimpleIntegerProperty(ID_Perso);
    }
    
    
    
    
}
