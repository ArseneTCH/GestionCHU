/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author HP
 */
public class User {
    protected int id;
    protected String nom_complet;
    protected String login;
    protected String password;
    protected String role;
    
    

    public User() {
    }
    

    public User(int id, String nom_complet, String login, String password, String role) {
        this.id = id;
        this.nom_complet = nom_complet;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String nom_complet, String login, String password, String role) {
        this.nom_complet = nom_complet;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(int id,String nom_complet) {
        this.id = id;
        this.nom_complet = nom_complet;
    }

    
    //select Secre,medecin,rp

    public User(int id, String nom_complet, String login, String password) {
        this.id = id;
        this.nom_complet = nom_complet;
        this.login = login;
        this.password = password;
    }
    //update secre,medecin,rp
    public User(String nom_complet, String login, String password) {
        this.nom_complet = nom_complet;
        this.login = login;
        this.password = password;
    }
   
    
    
    
    
    
    
    public int getId() {
        return id;
    }

    public String getNom_complet() {
        return nom_complet;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_complet(String nom_complet) {
        this.nom_complet = nom_complet;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    } 

    @Override
    public String toString() {
        return nom_complet ;
    }
   
}
