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
public class Consultation extends RendezVous {
    private int medecin_id;
    private int ordonnance_id;
    private int constante_id;
    

    public Consultation() {
    }
    //select
    public Consultation(int medecin_id, int ordonnance_id, int constante_id, int id_rv, String date, int patient_id,String motif, int dosMedi_id) {
        super(id_rv, date, patient_id,motif);
        this.medecin_id = medecin_id;
        this.ordonnance_id = ordonnance_id;
        this.constante_id = constante_id;
    }

    public Consultation(int medecin_id, int ordonnance_id, int constante_id, int dosMedi_id, int id_rv, String date, int patient_id, String statut, String motif, String typeMedecin) {
        super(id_rv, date, patient_id, statut, motif, typeMedecin);
        this.medecin_id = medecin_id;
        this.ordonnance_id = ordonnance_id;
        this.constante_id = constante_id;
       // this.dosMedi_id = dosMedi_id;
    }

    public Consultation(int medecin_id, int id_rv, String date, int patient_id, String statut, String motif) {
        super(id_rv, date, patient_id, statut, motif);
        this.medecin_id = medecin_id;
    }

    
    
    //insert par medecin avec ordonnance
    public Consultation(int medecin_id, int ordonnance_id, int constante_id, String date, int patient_id) {
        super(date, patient_id);
        this.medecin_id = medecin_id;
        this.ordonnance_id = ordonnance_id;
        this.constante_id = constante_id;
        
    }
    //insert par medecin sans ordonnance

    public Consultation(int medecin_id, int constante_id,  String date, int patient_id,String motif) {
        super(date, patient_id);
        this.medecin_id = medecin_id;
        this.constante_id = constante_id;
       
    }

    public Consultation(int ordonnance_id, int constante_id) {
        this.ordonnance_id = ordonnance_id;
        this.constante_id = constante_id;
    }

    public Consultation(int ordonnance_id, int constante_id, int idRv) {
        super(idRv);
        this.ordonnance_id = ordonnance_id;
        this.constante_id = constante_id;
    }
    
    

    public int getMedecin_id() {
        return medecin_id;
    }

    public int getOrdonnance_id() {
        return ordonnance_id;
    }

    public int getConstante_id() {
        return constante_id;
    }

   

    /**
     *
     * @return
     */
    @Override
    public int getId_rv() {
        return id_rv;
    }

    /**
     *
     * @return
     */
    @Override
    public String getDate() {
        return date;
    }

    @Override
    public int getPatient_id() {
        return patient_id;
    }

    public void setMedecin_id(int medecin_id) {
        this.medecin_id = medecin_id;
    }

    public void setOrdonnance_id(int ordonnance_id) {
        this.ordonnance_id = ordonnance_id;
    }

    public void setConstante_id(int constante_id) {
        this.constante_id = constante_id;
    }

  

    /**
     *
     * @param id_rv
     */
    @Override
    public void setId_rv(int id_rv) {
        this.id_rv = id_rv;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @param patient_id
     */
    @Override
    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    @Override
    public String toString() {
        return "Consultation{" + "medecin_id=" + medecin_id + ", ordonnance_id=" + ordonnance_id + ", constante_id=" + constante_id + '}';
    }
    
    

   
    
}
