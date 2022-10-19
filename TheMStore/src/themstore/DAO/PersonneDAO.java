package themstore.DAO;


import themstore.JDBC.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import themstore.Entity.Personne;
/**
 *
 * @author Hayou
 */

    public class PersonneDAO implements IPersonne<Personne>{
    
    private static PersonneDAO instance;
    private Statement st;
    private ResultSet rs;
    
    public PersonneDAO() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static PersonneDAO getInstance(){
        if(instance==null) 
            instance=new PersonneDAO();
        return instance;
    }
    
@Override
 public int authentification(Personne u){
        int test = 0;
        
          try {
          String req2="SELECT `id`, `username`, `password` FROM `personne`";
        

          rs=  st.executeQuery(req2);
          
          while (rs.next() && (test==0)) {
            if (u.getUsername().equals((rs.getString("username"))) && (u.getPassword().equals((rs.getString("password"))))) {
                System.err.println("1");
                 test=rs.getInt(1);
                 
                System.err.println(test);
                 
            }
            
            else{
           System.err.println("erreur");

            test=0;
            }
            }
    }   catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
        }
 
     @Override
    public String rechercherparrole(int id) {
        String test = null;
        String req = "SELECT role from personne where id='"+id+"'";
             
        try {
           
            ResultSet stp=st.executeQuery(req);
            stp.last();
            if(stp.getRow()!=0)
            {
               test= stp.getString(1);
                System.out.println(test);
            }
            else
            {
                test="not found";
                System.out.println(test);
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
        
    } 
    @Override
    public void insert(Personne o) {
        String req="insert into personne ( `nom`,`prenom`,`role`,`email`,`username`,`password`,`photo`,`num_telephone`) VALUES ('"+o.getNom()+"','"+o.getPrenom()+"','"+o.getRole()+"','"+o.getEmail()+"','"+o.getUsername()+"','"+o.getPassword()+"','"+o.getPhoto()+"','"+o.getNum_telephone()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(Personne o) {
        String req="delete from personne where id="+o.getId();
        Personne p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<Personne> displayAll() {
        String req="select * from personne";
        ObservableList<Personne> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Personne p=new Personne();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setRole(rs.getString("role"));
                p.setEmail(rs.getString("email"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                p.setPhoto(rs.getString("photo"));
                p.setNum_telephone(rs.getString("num_telephone"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Personne> displayAllList() {
        String req="select * from personne";
        List<Personne> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Personne p=new Personne();
                 p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setRole(rs.getString("role"));
                p.setEmail(rs.getString("email"));
                p.setEmail(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                p.setPhoto(rs.getString("photo"));
                p.setNum_telephone(rs.getString("num_telephone"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Personne displayById(int id) {
           String req="select * from personne where id ="+id;
           Personne p=new Personne();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                 p.setRole(rs.getString("role"));
                p.setEmail(rs.getString("email"));
                 p.setEmail(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                p.setPhoto(rs.getString("photo"));
                p.setNum_telephone(rs.getString("num_telephone"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public boolean update(Personne p) {
        String qry = "UPDATE personne SET nom = '"+p.getNom()+"', prenom = '"+p.getPrenom()+ "'"
                + ", role = '"+p.getRole()+ "' , email = '"+p.getEmail()+ "', username = '"+p.getUsername()+ "', password = '"+p.getPassword()+ "'"
                + ", photo = '"+p.getPhoto()+ "' , num_telephone = '"+p.getNum_telephone()+ "'  WHERE id = "+p.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
  
   

    
}