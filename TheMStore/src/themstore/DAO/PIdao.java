/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.DAO;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author metal
 */
public interface PIdao<Product> {
    public void AddProduct(Product p);
    public void DeleteProduct(Product p);
    public void UpdateProduct(Product p);
    public Product displayById(int id);
    public ResultSet displayAll();
}
