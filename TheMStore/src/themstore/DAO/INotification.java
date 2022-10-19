/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.DAO;

import java.util.List;
import themstore.Entity.Notification;

/**
 *
 * @author Hayou
 */
public interface INotification <Notification> {
    public void insert(Notification n);
    public void delete( Notification n);
    public List<Notification > displayAll();
    public Notification  displayById(int id);
     
    
    public boolean update(Notification n);
}
