/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themstore.Entity;

import java.util.Date;
import java.util.Objects;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author metal
 */
public class Product {
    private SimpleIntegerProperty ID ;
    private String Image ;
    private String Label ;
    private Double Price ;
    private String Date ;
    private String Description ;

    public Product(String Image, String Label, Double Price, String Date, String Description) {
        this.Image = Image;
        this.Label = Label;
        this.Price = Price;
        this.Date = Date;
        this.Description = Description;
    }

    public Product() {
    }
 
    public Product(int ID) {
        this.ID = new SimpleIntegerProperty(ID) ;
    }
    
    public Product(String Label, Double Price, String Image, String Description) {
        this.Label = new String(Label);
        this.Image = new String(Image);
        this.Price = new Double(Price);
        this.Description = new String(Description);
    }

    public int getID() {
        return ID.get();
    }

    public void setID(int ID) {
        this.ID = new SimpleIntegerProperty(ID);
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = new String(Image);
    }
 
    public String getLabel() {
        return Label;
    }

    public void setLabel(String Label) {
        this.Label = new String(Label);
    }
    public String getLabelProperty(){
        return Label;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = new Double(Price);
    }

 
    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = new String(Description);
    }


    


    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.ID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{"+"id"+ID+ ", Image=" + Image+", Label=" + Label+ ", Price=" + Price+ ", Date=" + Date+", Description=" + Description+'}';
    }

    
}
