/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import Entities.Medecin;
import Entities.RendezVous;

/**
 *
 * @author arsen
 */
public class DossierMedicalDTO {
    private String medecin;
    private String motif;
    private String date ;
    
    public void toDto( Medecin m,RendezVous rv){
        this.medecin = m.getNom_complet();
        this.motif = rv.getMotif()  ;
        this.date=rv.getDate();
    }

    public DossierMedicalDTO() {
    }

    public DossierMedicalDTO(String medecin, String motif, String date) {
        this.medecin = medecin;
        this.motif = motif;
        this.date = date;
    }

    
    
    public String getMedecin() {
        return medecin;
    }

    public void setMedecin(String medecin) {
        this.medecin = medecin;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DossierMedicalDTO{" + "medecin=" + medecin + ", motif=" + motif + ", date=" + date + '}';
    }
    
}
