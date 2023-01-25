/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import Entities.*;


/**
 *
 * @author HP
 */
public class RendezVousDTO {
   private String date;
   private String motif;
   private int patient_id;
   private int id_rv;
   private String statut;
   private String nom_complet;
   private String code;
    private String login;
    
    public void toDto( RendezVous rv){
        this.date= rv.getDate();
        this.motif=rv.getMotif();
        this.statut=rv.getStatut();
        this.id_rv=rv.getId_rv();
        this.patient_id=rv.getPatient_id();
        
        //this.nom_complet=p.getNom_complet();
        //this.code=p.getCode();
    }

    public RendezVousDTO(){
    }


    public RendezVousDTO(String date, String motif, int patient_id, int id_rv, String statut, String nom_complet, String code, String login) {
        this.date = date;
        this.motif = motif;
        this.patient_id = patient_id;
        this.id_rv = id_rv;
        this.statut = statut;
        this.nom_complet = nom_complet;
        this.code = code;
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getStatut() {
        return statut;
    }

    public String getDate() {
        return date;
    }

    public String getMotif() {
        return motif;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getNom_complet() {
        return nom_complet;
    }

    public String getCode() {
        return code;
    }

    public void setNom_complet(String nom_complet) {
        this.nom_complet = nom_complet;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId_rv() {
        return id_rv;
    }

    public void setId_rv(int id_rv) {
        this.id_rv = id_rv;
    }

    @Override
    public String toString() {
        return "RendezVousDTO{" + "date=" + date + ", motif=" + motif + ", patient_id=" + patient_id + ", id_rv=" + id_rv + ", statut=" + statut + ", nom_complet=" + nom_complet + ", code=" + code + '}';
    }

    
    
}
