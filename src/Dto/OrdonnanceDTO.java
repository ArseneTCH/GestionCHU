/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import Entities.Constantes;
import Entities.Medicament;
import Entities.Ordonnance;
import Entities.Prestation;
import java.util.List;

/**
 *
 * @author HP
 */
public class OrdonnanceDTO {
    private int id_medicament;
    private int id_Rv;
    private String code;
    private String nom;
    private String posologie; 
    private Prestation prestation; 
    private String date; 
    private List<Medicament> ListMedoc;
    private List<OrdonnanceDTO> ListOrdnc;
    private List<Constantes> ListConst;

    public OrdonnanceDTO() {
    }

    public void toDto(Medicament m,Ordonnance o){
        this.id_medicament=m.getId_medicament();
        this.code=m.getCode();
        this.nom=m.getNom();
        this.posologie=o.getPosologie();
    }
    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public String getPosologie() {
        return posologie;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }

    public List<Medicament> getListMedoc() {
        return ListMedoc;
    }

    public void setListMedoc(List<Medicament> ListMedoc) {
        this.ListMedoc = ListMedoc;
    }

    public List<OrdonnanceDTO> getListOrdnc() {
        return ListOrdnc;
    }

    public void setListOrdnc(List<OrdonnanceDTO> ListOrdnc) {
        this.ListOrdnc = ListOrdnc;
    }

    public int getId_medicament() {
        return id_medicament;
    }

    public void setId_medicament(int id_medicament) {
        this.id_medicament = id_medicament;
    }

    public List<Constantes> getListConst() {
        return ListConst;
    }

    public void setListConst(List<Constantes> ListConst) {
        this.ListConst = ListConst;
    }

    public Prestation getPrestation() {
        return prestation;
    }

    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_Rv() {
        return id_Rv;
    }

    public void setId_Rv(int id_Rv) {
        this.id_Rv = id_Rv;
    }

    @Override
    public String toString() {
        return "OrdonnanceDTO{" + "id_medicament=" + id_medicament + ", id_Rv=" + id_Rv + ", posologie=" + posologie + '}';
    }
    

   

    
    
    
}
