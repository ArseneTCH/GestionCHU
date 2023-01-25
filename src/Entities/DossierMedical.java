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
public class DossierMedical {
    private int id_dosMedi;
    private int patient_id;
    private int rendezVous_id;
    private String motif;

    public DossierMedical() {
    }

    public DossierMedical(int id_dosMedi, int patient_id, int rendezVous_id, String motif) {
        this.id_dosMedi = id_dosMedi;
        this.patient_id = patient_id;
        this.rendezVous_id = rendezVous_id;
        this.motif = motif;
    }

    public DossierMedical(int patient_id, int rendezVous_id, String motif) {
        this.patient_id = patient_id;
        this.rendezVous_id = rendezVous_id;
        this.motif = motif;
    }

    
    
    public int getId_dosMedi() {
        return id_dosMedi;
    }

    public void setId_dosMedi(int id_dosMedi) {
        this.id_dosMedi = id_dosMedi;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getRendezVous_id() {
        return rendezVous_id;
    }

    public void setRendezVous_id(int rendezVous_id) {
        this.rendezVous_id = rendezVous_id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }
    

   

    
}
