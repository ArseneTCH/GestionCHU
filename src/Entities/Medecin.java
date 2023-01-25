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
public class Medecin extends User{
    private String fonction;
    private final String ROLE="ROLE_MEDECIN";

    public Medecin() {
        this.role=ROLE;
    }

    public Medecin(int id, String nom_complet, String login, String password,String fonction) {
        super(id, nom_complet, login, password);
        this.fonction=fonction;
        this.role=ROLE;
    }

    public Medecin(String nom_complet, String login, String password,String fonction) {
        super(nom_complet, login, password);
        this.fonction=fonction;
        this.role=ROLE;
    }

    public Medecin(int id,String nom_complet) {
        super(id,nom_complet);
    }

  

    
    public String getROLE() {
        return ROLE;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    
    @Override
    public String toString() {
        return  nom_complet ;
    }

    
    
}
