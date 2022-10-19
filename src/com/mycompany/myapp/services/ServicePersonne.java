/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Personne;
import com.mycompany.myapp.gui.AddPersonneForm;
import com.mycompany.myapp.gui.HomeForm;
import com.mycompany.myapp.utils.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServicePersonne  {

    public ArrayList<Personne> personnes;
    
    public static ServicePersonne instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicePersonne() {
         req = new ConnectionRequest();
    }

    public static ServicePersonne getInstance() {
        if (instance == null) {
            instance = new ServicePersonne();
        }
        return instance;
    }
  // ADD
    public boolean addPersonne(Personne t) {
        String url = Statics.BASE_URL + "/add?nom=" + t.getNom() + "&prenom=" + t.getPrenom() + "&role=" + t.getRole()
               + "&email=" + t.getEmail() + "&username=" + t.getUsername() + "&password=" + t.getPassword()
                       + "&photo=" + t.getPhoto() + "&num_telephone=" + t.getNum_telephone(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener((e)-> {
            String str= new String(req.getResponseData());
            System.out.println("data =="+str);
        });
        
         
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public void SignUp(TextField nom,TextField prenom,TextField role,TextField email,TextField username,
            TextField password,TextField confirmPassword,TextField photo,TextField num_telephone ,Resources res) {
        String url = Statics.BASE_URL + "/tasjil?nom=" + nom.getText().toString() + "&prenom=" + prenom.getText().toString() + "&role=" + role.getText().toString()
               + "&email=" + email.getText().toString() + "&username=" + username.getText().toString() + "&password=" + password.getText().toString()
                       + "&photo=" + photo.getText().toString() + "&num_telephone=" + num_telephone.getText().toString(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        if ( nom.getText().equals("") && prenom.getText().equals("") )
            Dialog.show("Erreur","Veuiellez remplir les champs", null);
        
        req.addResponseListener((e)-> {
            byte[]data = (byte[]) e.getMetaData();
String responseData = new String(data);
System.out.println("data ===>"+responseData);

        });
        
         
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
   
 public ArrayList<Personne>affichagePersonnes(){
      ArrayList<Personne> result = new ArrayList<>();
      String Url = Statics.BASE_URL+"/displayPersonne";
      req.setUrl(Url);
      req.setHttpMethod("GET");
      req.addResponseListener(new ActionListener<NetworkEvent>() {
          @Override
          public void actionPerformed(NetworkEvent evt) {
               JSONParser jsonp;
               jsonp = new JSONParser();
               
               try {
                   Map<String,Object>mapPersonnes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
               List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapPersonnes.get("root");
               
               for (Map<String,Object> obj : listOfMaps) {
                   Personne t = new Personne(); 
                 //  float id = Float.parseFloat(obj.get("id").toString());
                String nom =obj.get("nom").toString();
              String prenom =obj.get("prenom").toString();
               /*   String role =obj.get("role").toString();
                String email =obj.get("email").toString();
                String username =obj.get("username").toString();
                String password =obj.get("password").toString();
                String photo =obj.get("photo").toString();
                String num_telephone =obj.get("num_telephone").toString(); */
              //  t.setId((int)id);
                t.setNom(nom);
                t.setPrenom(prenom);
              /*  t.setRole(role);
                t.setEmail(email);
                t.setUsername(username);
                t.setPassword(password);
                t.setPhoto(photo);
                t.setNum_telephone(num_telephone); */
                
                result.add(t);
            
               }
               
               }catch (Exception ex) {
                   ex.printStackTrace();
               }
            
          }
      });
      
 NetworkManager.getInstance().addToQueueAndWait(req);
      
return result;
}

  public void SignIn(TextField username,TextField password ,Resources theme){
      
      String url = Statics.BASE_URL + "/logina?username="+username.getText().toString()+"&password="
              +password.getText().toString() ;
      req.setUrl(url);
      req.addResponseListener((e)-> {
            
          JSONParser j = new JSONParser();
          String json = new String (req.getResponseData()) + "";
         
         
              try {
                  
             
          if (json.equals("failed")) 
              Dialog.show("Echec d'authentification" ,"Username ou mot de passe invalid", null);  
          
          else {
              System.out.println("data===>"+json);
              Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray())); 
              
              if(user.size() >0)
                   
              new HomeForm(theme).show();
              
                  }
              }catch (Exception ex) {
                  ex.printStackTrace();
                  
              }
        });
       NetworkManager.getInstance().addToQueueAndWait(req);
        
  }
}
