/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import themstore.Entity.Product ;
import themstore.JDBC.Connjdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author metal
 */public class ProductDao implements PIdao<Product> {
    private static ProductDao instance ;
        Connection cx = Connjdbc.getInstance().getCnx();

    private Statement st;
    private ResultSet rs;
    
    
    public static ProductDao getInstance(){
    
        if(instance==null) 
            instance=new ProductDao();
        return instance;
}
    @Override
    public void AddProduct(Product p){
        
        Connection cnx = Connjdbc.getInstance().getCnx();
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement("INSERT INTO product (Image,Label,Price,Date,Description) VALUES(?,?,?,?,?)");
            ps.setString(1 , p.getImage());
            ps.setString(2 , p.getLabel());
            ps.setDouble(3 , p.getPrice());
            ps.setString(4 , p.getDate());
            ps.setString(5 , p.getDescription());
            ps.executeUpdate();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        };
  
    }

    @Override
    public void DeleteProduct(Product p) {
        
        Connection cnx = Connjdbc.getInstance().getCnx();
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement("delete from product where id=?");
            ps.setInt(1 , p.getID());          
            ps.executeUpdate();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        };
        
    }

    @Override
    public void UpdateProduct (Product p){
        Connection cnx = Connjdbc.getInstance().getCnx();
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement("UPDATE product SET Image =? ,Label =? , Price = ?, Description = ?  WHERE id = ?");
            ps.setString(1 , p.getImage());
            ps.setString(2 , p.getLabel());
            ps.setDouble(3 , p.getPrice());
            ps.setString(4 , p.getDescription());
            ps.setInt(5, p.getID());
            ps.executeUpdate();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        };
    }

    
    @Override
     public Product displayById(int id) {
         Product  p=  new Product();
            try {
            
           
            PreparedStatement pt = cx.prepareStatement("select * from product WHERE id = ?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
               p.setID(rs.getInt("id"));
                p.setImage(rs.getString("Image"));
                p.setLabel(rs.getString("Label"));
                p.setPrice(rs.getDouble("Price"));
                p.setDate(rs.getString("Date"));
                p.setDescription(rs.getString("Description"));

            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        
        return p;
    }

    
    public ResultSet displayAll() {
        ResultSet rs = null;
        try {
             PreparedStatement pt = cx.prepareStatement("SELECT * FROM product");
            rs = pt.executeQuery();
            
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return rs;
    }

    
    
  
}