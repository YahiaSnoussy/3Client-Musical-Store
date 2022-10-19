/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author bhk
 */
public class Personne {
    private int id;
    private String nom;
    private String prenom;
    private String role;
    private String email;
    private String username;
    private String password;
    private String photo;
    private String num_telephone;

    public Personne() {
    }

    

    public Personne(int id, String nom, String prenom, String role, String email, String username, String password, String photo, String num_telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.email = email;
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.num_telephone = num_telephone;
    }
    
    public Personne( String nom, String prenom, String role, String email, String username, String password, String photo, String num_telephone) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.email = email;
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.num_telephone = num_telephone;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoto() {
        return photo;
    }

    public String getNum_telephone() {
        return num_telephone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setNum_telephone(String num_telephone) {
        this.num_telephone = num_telephone;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
   

   
    
}
