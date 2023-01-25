/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import Entities.Medecin;
import Entities.Patient;
import Entities.RendezVous;

/**
 *
 * @author arsen
 */
public class ResponsablePrestationDTO {
    private String medecin;
    private String patient;
    private String rendezVous ;
    
    public void toDto( Medecin m,Patient p,RendezVous rv){
        this.medecin = m.getNom_complet();
        this.patient = p.getNom_complet();
        this.rendezVous=rv.getDate();
    }

    public ResponsablePrestationDTO() {
    }

    public ResponsablePrestationDTO(String medecin, String patient, String rendezVous) {
        this.medecin = medecin;
        this.patient = patient;
        this.rendezVous = rendezVous;
    }

    public String getMedecin() {
        return medecin;
    }

    public void setMedecin(String medecin) {
        this.medecin = medecin;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(String rendezVous) {
        this.rendezVous = rendezVous;
    }

    
  

    @Override
    public String toString() {
        return "ResponsblePrestationDTO{" + "medecin=" + medecin + ", patient=" + patient + ", rendezVous=" + rendezVous + '}';
    }
    
}
