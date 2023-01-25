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
public class Ordonnance {
    private int id_ordonnance;
    private String posologie;
    private int consultation_id;
    private int medicament_id;

    public Ordonnance() {
    }

    public Ordonnance(int id_ordonnance, String posologie, int consultation_id, int medicament_id) {
        this.id_ordonnance = id_ordonnance;
        this.posologie = posologie;
        this.consultation_id = consultation_id;
        this.medicament_id = medicament_id;
    }

    public Ordonnance(String posologie, int consultation_id, int medicament_id) {
        this.posologie = posologie;
        this.consultation_id = consultation_id;
        this.medicament_id = medicament_id;
    }

    public int getId_ordonnance() {
        return id_ordonnance;
    }

    public String getPosologie() {
        return posologie;
    }

    public int getConsultation_id() {
        return consultation_id;
    }

    public int getMedicament_id() {
        return medicament_id;
    }

    public void setId_ordonnance(int id_ordonnance) {
        this.id_ordonnance = id_ordonnance;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }

    public void setConsultation_id(int consultation_id) {
        this.consultation_id = consultation_id;
    }

    public void setMedicament_id(int medicament_id) {
        this.medicament_id = medicament_id;
    }
    
    public void toOrdonnance (OrdonnanceDTO ordnc){
       this.posologie=ordnc.getPosologie() ;
    }

    @Override
    public String toString() {
        return "Ordonnance{" + "id_ordonnance=" + id_ordonnance + ", posologie=" + posologie + ", consultation_id=" + consultation_id + ", medicament_id=" + medicament_id + '}';
    }
    
}
