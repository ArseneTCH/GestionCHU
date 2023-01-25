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
public class Patient extends User{
    private String antecedent;
    private int dosMedi_id;
    private String code;
    
    private final String ROLE="ROLE_PATIENT";

    public Patient() {
        this.role=ROLE;
    }

    

    public Patient(int id, String nom_complet, String login, String password, String code,String antecedent, int dosMedi_id) {
        super(id, nom_complet, login, password);
        this.code =code;
        this.antecedent = antecedent;
        this.dosMedi_id=dosMedi_id;
        this.role=ROLE;
    }

    public Patient(String code, int id, String nom_complet, String login, String password, String role) {
        super(id, nom_complet, login, password, role);
        this.code = code;
    }
    
    public Patient(String nom_complet, String login, String password,String code) {
        super(nom_complet, login, password);
        this.code=code;
        this.role=ROLE;
    }

    public Patient(String nom_complet, String login, String password, String code,String antecedent, int dosMedi_id) {
        super(nom_complet, login, password);
        this.code =code;
        this.antecedent = antecedent;
        this.dosMedi_id=dosMedi_id;
        this.role=ROLE;
    }

    public Patient(String nom_complet, String login, String password, String code,String antecedent) {
        super(nom_complet, login, password);
        this.code =code;
        this.antecedent = antecedent;
        this.role=ROLE;
    }

    public String getAntecedent() {
        return antecedent;
    }

    public int getDosMedi_id() {
        return dosMedi_id;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setAntecedent(String antecedent) {
        this.antecedent = antecedent;
    }

    public void setDosMedi_id(int dosMedi_id) {
        this.dosMedi_id = dosMedi_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Patient{" + "antecedent=" + antecedent + ", dosMedi_id=" + dosMedi_id + ", code=" + code + ", ROLE=" + ROLE + '}';
    }
    
   
    
    
}
