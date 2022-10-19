/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.DAO;

import java.sql.SQLException;
import java.util.List;
import themstore.Entity.Personne;




/**
 *
 * @author Hayou
 * @param <Personne>
 * 
 *  
 */

public interface IPersonne<Personne> {
     public String rechercherparrole(int id);
    public int authentification (Personne p);
  public void insert(Personne p);
    public void delete(Personne p);
    public List<Personne> displayAll();
    public Personne displayById(int id);
     
    
    public boolean update(Personne p);
    
}
