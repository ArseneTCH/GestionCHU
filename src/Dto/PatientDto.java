/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import Entities.Patient;
import Entities.RendezVous;

/**
 *
 * @author HP
 */
public class PatientDto {
    private Patient patient;
    private RendezVous rv;
    
    public void toDto(Patient patient){
        this.patient= patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public RendezVous getRv() {
        return rv;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setRv(RendezVous rv) {
        this.rv = rv;
    }
    
}
