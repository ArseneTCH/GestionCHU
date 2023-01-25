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
public class Secretaire extends User{
    private final String ROLE="ROLE_SECRETAIRE";

    public Secretaire() {
        this.role=ROLE;
    }

    public Secretaire(int id, String nom_complet, String login, String password) {
        super(id, nom_complet, login, password);
        this.role=ROLE;
    }

    public Secretaire(String nom_complet, String login, String password) {
        super(nom_complet, login, password);
        this.role=ROLE;
    }

    public String getROLE() {
        return ROLE;
    }
    
    
    
}
