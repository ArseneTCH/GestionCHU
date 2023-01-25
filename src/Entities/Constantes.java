/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Dto.OrdonnanceDTO;

/**
 *
 * @author HP
 */
public class Constantes {
    private int id_constante;
    private String libelle;
    private String valeur;
    private int consultation_id;

    public Constantes() {
    }

    public Constantes(int id_constante, String libelle, String valeur, int consultation_id) {
        this.id_constante = id_constante;
        this.libelle = libelle;
        this.valeur = valeur;
        this.consultation_id = consultation_id;
    }

    public Constantes(String libelle, String valeur, int consultation_id) {
        this.libelle = libelle;
        this.valeur = valeur;
        this.consultation_id = consultation_id;
    }

    public int getId_constante() {
        return id_constante;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getValeur() {
        return valeur;
    }

    public int getConsultation_id() {
        return consultation_id;
    }

    public void setId_constante(int id_constante) {
        this.id_constante = id_constante;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public void setConsultation_id(int consultation_id) {
        this.consultation_id = consultation_id;
    }

    @Override
    public String toString() {
        return "Constantes{" + "id_constante=" + id_constante + ", libelle=" + libelle + ", valeur=" + valeur + ", consultation_id=" + consultation_id + '}';
    }
 
    
    
}

