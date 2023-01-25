/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Dao.*;
import Dto.*;
import Dto.RendezVousDTO;
import Dto.ResponsablePrestationDTO;
import Entities.*;
import views.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author HP
 */
public class Service implements IService{
    UserDao daoUser=new UserDao();
    ConsultationDao daoConsul = new ConsultationDao();
    ConstantesDao  daoConst =new ConstantesDao();
    DossierMedicalDao daoDosMedi =new DossierMedicalDao();
    MedicamentDao daoMedoc = new MedicamentDao();
    OrdonnanceDao daoOrd =new OrdonnanceDao();
    PatientDao  daoPatient =new PatientDao();
    PrestationDao  daoPrestation =new PrestationDao();
    RendezVousDao daoRv =new RendezVousDao();
    MedecinDao daoMedecin =new MedecinDao();
    
    @Override 
    public User login(String login, String password) {
        return daoUser.findUserLoginAndPassword(login, password);
    }

    @Override
    public int AddRvByPatient(RendezVous rv) {
          return daoRv.insert(rv);
        
      }

    
    @Override
    public List<RendezVous> filterRvByPatient(int id,String motif) {
        List<RendezVous> rendezVous= new ArrayList<>();
        if(motif==null){
            rendezVous = daoRv.findByPatient(id);
        }else {
            rendezVous = daoRv.findByMotifByPatient(id, motif);
        }
       // System.out.println(motif);
        
        return rendezVous;
    }

    @Override
    public List<RendezVous> searchRvByPatient(int id) {
        return daoRv.findByPatient(id);
    }

    @Override
    public int AddCompte(Patient p) {
        return daoPatient.insert(p);
    }

    
    
    @Override
    public List<Medecin> searchAllMedecin() {
        return daoMedecin.findAll();
    }
    
     @Override
    public int AddPrestationByPatient(Prestation p) {
        return daoPrestation.insert(p);
    }

    @Override
    public int AddConsultationByPatient(Consultation c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*Secretaire*/
    @Override
    public List<RendezVous> searchRvBySecretaire() {
        return daoRv.findBySecretaire();
    }

    @Override
    public int ValideRv(RendezVous rv) {
        return daoRv.updateRvBySecretaire(rv);
    }
    @Override
    public Patient serachPatientById(int id) {
        return daoPatient.findById(id);
    }

    @Override
    public int AnnulerRvBySecretaire(RendezVous rv) {
        return daoRv.update(rv);
    }

    @Override
    public Medecin searchMedecinById(int id) {
        return daoMedecin.findById(id);
    }


    @Override
    public HashSet<User> searchRvByFonctionDate(RendezVous rv) {
        return daoRv.findByFonctionDate(rv);
    }

    /*Medecin*/
    
    @Override
    public int planifierRvByMedecin(RendezVous rv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DossierMedical> listDosMediByMedecin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public int AnnulerRvByMedecin(RendezVousDTO rv) {
        return daoRv.updateByMedecin(rv);
    }
    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<RendezVous> searchAllRvByMedecin(int id) {
        return daoRv.findByMedecin(id);
    }

    @Override
    public int AnnulerConsultationByMedecin(Consultation c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RendezVousDTO> searchRvByMedecin(int id) {
        return daoRv.findAll(id);
    }

   
    @Override
    public int AddOrdonnance(OrdonnanceDTO o) {
        int idO=0;
        int idConst=0;
        RendezVousDTO rvs =ListeRendezVousByMedecinController.getCtrl().getRv();
        
        //System.out.println(rvs);
        List<OrdonnanceDTO> ors= o.getListOrdnc();
        List<Constantes> Lc=o.getListConst();

        for(OrdonnanceDTO os:ors){
            System.out.println(os);
            idO=daoOrd.insertOrd(os.getPosologie(),os.getId_medicament(),os.getId_Rv());
        }
        if(Lc!=null){
            for(Constantes c:Lc){
                Constantes constante =new Constantes(c.getLibelle(),c.getValeur(),rvs.getId_rv());
                idConst =daoConst.insert(constante);  
            }
        }
        Consultation C =new Consultation(idO,idConst,rvs.getId_rv());
        daoConsul.update(C);
       // daoConsul.insert(C);
        
        DossierMedical dosM=new DossierMedical(rvs.getPatient_id(),rvs.getId_rv(),rvs.getMotif());
        daoDosMedi.insert(dosM);
        //Lc.forEach((c)->); ;
       
        if(o.getPrestation()!=null){
           Prestation r =new Prestation(o.getPrestation().getDate(),rvs.getPatient_id(),o.getPrestation().getMotif());
           daoPrestation.insertByMedecin(r);
            
        }
        
        
        return idO;
        
    } 
    @Override
    public int AddDosMedi(int patient_id,int prestation_id,int consultation_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Medicament serachMedocByCode(String code) {
        return daoMedoc.findByCode(code);
    }

    @Override
    public List<RendezVousDTO> searchRvByDateByMedecin(int id, String date) {
        //System.out.println(date);
        return daoRv.findAllByDateByMedecin(id, date);
    }

    @Override
    public int prescrirConsultationByMedecin(RendezVous rv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*RP*/
    
    @Override
    public List<ResponsablePrestationDTO> searchAllPrestation() {
        
        List<ResponsablePrestationDTO>  list =new ArrayList<>();
        ResponsablePrestationDTO rpd=null;
        List<RendezVous>  R =new ArrayList<>();
        Patient P=new  Patient();
        Medecin M=new Medecin();
       
            R=daoRv.findByMotif("PRESTATION");
        //System.out.println(R);
        for(RendezVous r:R){
            P=daoPatient.findById(r.getPatient_id());
            M=daoMedecin.findById(r.getMedecin_id());
            //System.out.println(r);
           //System.out.println(M);
            if(M.getNom_complet()!=null){
              
                rpd= new ResponsablePrestationDTO(M.getNom_complet(),P.getNom_complet(),r.getDate());
            }else{
                 rpd= new ResponsablePrestationDTO("AUCUN",P.getNom_complet(),r.getDate()); 
            }
            list.add(rpd);
            
        }
        //System.out.println(list);
        return list;
    }
        
    @Override
    public List<ResponsablePrestationDTO> searchAllPrestationByDate(String date) {
         List<ResponsablePrestationDTO>  list =new ArrayList<>();
        ResponsablePrestationDTO rpd=null;
        List<Prestation>  R =new ArrayList<>();
        Patient P=new  Patient();
        Medecin M=new Medecin();
       
        R=daoPrestation.findAllByDateByRP(date);
        for(RendezVous r:R){
            P=daoPatient.findById(r.getPatient_id());
            M=daoMedecin.findById(r.getMedecin_id());
            //System.out.println(P);
            //System.out.println(M);
            rpd= new ResponsablePrestationDTO("Nom Medecin",P.getNom_complet(),r.getDate());
            list.add(rpd);
            
        }
        //System.out.println(list);
        return list;
    }

    @Override
    public User searchUserByLogin(String login) {
        return daoUser.findUserLogin(login);
    }

    @Override
    public List<DossierMedicalDTO> searchAllDosierMedicauxByLogin(String login) {
        List<DossierMedicalDTO>  listdto =new ArrayList<>();
        List<DossierMedical>  list =new ArrayList<>();
        DossierMedical dm =new DossierMedical();
        
        Patient user =daoPatient.findByMail(login);
        Medecin m=new Medecin();
       // System.out.println(user);
       // 
        list=daoDosMedi.findAllById(user.getId());
        for(DossierMedical d:list){
            String motif =d.getMotif();
            String date= daoRv.findById(d.getRendezVous_id()).getDate();
            String mm =daoMedecin.findById(d.getRendezVous_id()).getNom_complet();
           // System.out.println(mm);
            DossierMedicalDTO dmdto=new DossierMedicalDTO(user.getNom_complet(),motif,date);
            listdto.add(dmdto);
        }
        System.out.println(listdto);
        
        return listdto;
    }

   
}
