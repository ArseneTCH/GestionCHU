/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Dto.RendezVousDTO;

/**
 *
 * @author HP
 */
public class RendezVous  {
    protected int id_rv;
    protected String date;
    protected int patient_id;
    protected int medecin_id;
    protected String statut;
    protected String motif;
    protected String typeMedecin;
    public RendezVous() {
    }

    public RendezVous(int id_rv, String date, int patient_id, String statut, String motif, String typeMedecin) {
        this.id_rv = id_rv;
        this.date = date;
        this.patient_id = patient_id;
        this.statut = statut;
        this.motif = motif;
        this.typeMedecin = typeMedecin;
       
    }

    public RendezVous(int id_rv, String date, int patient_id, int medecin_id, String statut, String motif, String typeMedecin) {
        this.id_rv = id_rv;
        this.date = date;
        this.patient_id = patient_id;
        this.medecin_id = medecin_id;
        this.statut = statut;
        this.motif = motif;
        this.typeMedecin = typeMedecin;
    }

    public RendezVous(String date, int patient_id, String statut, String motif, String typeMedecin) {
        this.date = date;
        this.patient_id = patient_id;
        this.statut = statut;
        this.motif = motif;
        this.typeMedecin = typeMedecin;
    }

    public RendezVous(String date, int patient_id, String motif) {
        this.date = date;
        this.patient_id = patient_id;
        this.motif = motif;
    }
   
      //innsert by patient
    public RendezVous(int patient_id, String statut, String motif,String typeMedecin) {
        this.patient_id = patient_id;
        this.statut = statut;
        this.motif = motif;
        this.typeMedecin = typeMedecin;
    }
    public RendezVous(int patient_id, String motif,String typeMedecin) {
        this.patient_id = patient_id;
        this.motif = motif;
        this.typeMedecin = typeMedecin;
    }
    
    
    
    
    
    
    
    
   
    public RendezVous(String date, int patient_id,String statut,String motif) {
        this.date = date;
        this.patient_id = patient_id;
        this.statut=statut;
        this.motif=motif;
    }
  
    
    //insert by medecin
    public RendezVous(String date, int patient_id) {
        this.date = date;
        this.patient_id = patient_id;
    }
    
    public RendezVous(int id_rv, String date, int patient_id,String statut,String motif) {
        this.id_rv = id_rv;
        this.date = date;
        this.patient_id = patient_id;
        this.statut=statut;
        this.motif=motif;
    }

    public RendezVous(int id_rv, String date, int patient_id,String motif) {
        this.id_rv = id_rv;
        this.date = date;
        this.patient_id = patient_id;
        this.motif=motif;
    }

    public RendezVous(int idRv) {
        this.id_rv = idRv;
    }

    public RendezVous(String prestation, int patient_id, int id, String motif) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getMotif() {
        return motif;
    }
    



    public int getId_rv() {
        return id_rv;
    }

    public String getDate() {
        return date;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public String getStatut() {
        return statut;
    }

    
    public void setId_rv(int id_rv) {
        this.id_rv = id_rv;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getTypeMedecin() {
        return typeMedecin;
    }

    public void setTypeMedecin(String typeMedecin) {
        this.typeMedecin = typeMedecin;
    }

    public int getMedecin_id() {
        return medecin_id;
    }

    public void setMedecin_id(int medecin_id) {
        this.medecin_id = medecin_id;
    }

 

    
    
    public void RendezVousDTO(RendezVousDTO rv){
        this.date=rv.getDate();
        this.motif=rv.getMotif();
        this.statut=rv.getStatut();
    }

    @Override
    public String toString() {
        return "RendezVous{" + "id_rv=" + id_rv + ", date=" + date + ", patient_id=" + patient_id + ", medecin_id=" + medecin_id + ", statut=" + statut + ", motif=" + motif + ", typeMedecin=" + typeMedecin + '}';
    }

    
   
    

}