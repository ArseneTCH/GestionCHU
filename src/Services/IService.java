/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Dto.DossierMedicalDTO;
import Dto.OrdonnanceDTO;
import Dto.RendezVousDTO;
import Dto.ResponsablePrestationDTO;
import Entities.*;

import java.util.HashSet;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IService {
    
    /*Patient*/
    public int AddRvByPatient(RendezVous rv);
    public int AddPrestationByPatient(Prestation p);
    public int AddConsultationByPatient(Consultation c);
    //public List<RendezVous> searchAllConsultationByPatient(int id);
    public List<RendezVous> filterRvByPatient(int id,String motif);
    public List<RendezVous> searchRvByPatient(int id);
    
    public int AddCompte(Patient p);
    public List<Medecin> searchAllMedecin();
    
    /*Secretaire*/
    public List<RendezVous> searchRvBySecretaire();
    public int ValideRv(RendezVous rv);
    public int AnnulerRvBySecretaire(RendezVous rv);
    public Medecin searchMedecinById(int id);
    public HashSet <User> searchRvByFonctionDate(RendezVous rv);
    
    /*Medecin*/
    public int planifierRvByMedecin(RendezVous rv);
    public int prescrirConsultationByMedecin(RendezVous rv);
    public List<DossierMedical> listDosMediByMedecin();
    public List<RendezVous> searchAllRvByMedecin(int id);
    public int AnnulerConsultationByMedecin(Consultation c);
    public List<RendezVousDTO> searchRvByMedecin(int id);
    public List<RendezVousDTO> searchRvByDateByMedecin(int id,String date);
    public int AddOrdonnance(OrdonnanceDTO o);
    public int AddDosMedi(int patient_id,int prestation_id,int consultation_id);
    public Medicament serachMedocByCode(String code);
    public int AnnulerRvByMedecin(RendezVousDTO rv);
    
    /*Responsable de Prestation*/
    
    public List<ResponsablePrestationDTO> searchAllPrestation();
    public List<ResponsablePrestationDTO> searchAllPrestationByDate(String date);
    public List<DossierMedicalDTO>  searchAllDosierMedicauxByLogin(String login);
    
    /*login*/
    public User login(String login,String password);
    public User searchUserByLogin(String login);
    public Patient serachPatientById(int id);
      

    
}
