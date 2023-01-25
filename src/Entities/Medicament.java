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
public class Medicament {
    private int id_medicament;
    private String code;
    private String nom;

    public Medicament() {
    }

    public Medicament(int id_medicament, String code, String nom) {
        this.id_medicament = id_medicament;
        this.code = code;
        this.nom = nom;
    }

    public Medicament(String code, String nom) {
        this.code = code;
        this.nom = nom;
       
    }

    public int getId_medicament() {
        return id_medicament;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public void setId_medicament(int id_medicament) {
        this.id_medicament = id_medicament;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    public void toMedicament(OrdonnanceDTO ordnc){
        this.id_medicament=ordnc.getId_medicament();
       this.code=ordnc.getCode();
       this.nom=ordnc.getNom();
    }

    @Override
    public String toString() {
        return "Medicament{" + "id_medicament=" + id_medicament + ", code=" + code + ", nom=" + nom +'}';
    }
    
    
}
