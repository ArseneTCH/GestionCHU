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
public class Prestation extends RendezVous {

    public Prestation() {
        
    }

    public Prestation(String date, int patient_id) {
        super(date, patient_id);
        
    }

    public Prestation(String date, int patient_id, String statut, String motif) {
        super(date, patient_id, statut, motif);
        
    }

    
    //select

    public Prestation(int id_rv, String date, int patient_id,String motif) {
        super(id_rv, date, patient_id,motif);
        
    }
public Prestation(String date, int patient_id, String motif) {
        super(date, patient_id, motif);
        
    }

    public Prestation(int id_rv, String date, int patient_id, String statut, String motif, String typeMedecin) {
        super(id_rv, date, patient_id, statut, motif, typeMedecin);
    }

    public int getId_rv() {
        return id_rv;
    }

    public void setId_rv(int id_rv) {
        this.id_rv = id_rv;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getMedecin_id() {
        return medecin_id;
    }

    public void setMedecin_id(int medecin_id) {
        this.medecin_id = medecin_id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getTypeMedecin() {
        return typeMedecin;
    }

    public void setTypeMedecin(String typeMedecin) {
        this.typeMedecin = typeMedecin;
    }
    

    
 }
