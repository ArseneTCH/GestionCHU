/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.RendezVousDTO;
import Entities.RendezVous;
import Entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashSet;
/**
 *
 * @author HP
 */
public class RendezVousDao implements IDao<RendezVous>{
    private final String SQL_INSERT ="INSERT INTO `rendezvous`(`patient_id`, `statut`, `motif`,`typeMedecin`) VALUES (?,'EN_COURS',?,?)";
   
    //private final String SQL_SELECT_RV_BY_ID = "SELECT * FROM `rendezvous` WHERE `patient_id` = ? AND `statut` NOT LIKE 'ANNULER' ";
    private final String SQL_SELECT_RV_BY_PATIENT = "SELECT * FROM `rendezvous` WHERE `patient_id` = ? AND `statut` NOT LIKE 'ANNULER' ";
    private final String SQL_SELECT_RV_BY_ID = "SELECT * FROM `rendezvous` WHERE `id_rv` = ? ";
    private final String SQL_SELECT_RV_BY_MOTIF_BY_PATIENT = "SELECT * FROM `rendezvous` WHERE `patient_id` = ? AND `motif` = ? AND `statut` NOT LIKE 'ANNULER'  ";
     private final String SQL_SELECT_RV_BY_MOTIF= "SELECT * FROM `rendezvous` WHERE `motif` = ? AND `statut` NOT LIKE 'ANNULER'  ";
    private final String SQL_SELECT_RV_BY_SECRETAIRE = "SELECT * FROM rendezvous r , user p WHERE r.patient_id = p.id AND   r.statut LIKE 'EN_COURS' ";
    private final String SQL_SELECT_RV_BY_Medecin = "SELECT * FROM `rendezvous` WHERE `medecin_id` = ? AND `statut` NOT LIKE 'ANNULER' ";
    private final String SQL_UPDATE="UPDATE `rendezvous` SET `statut`='ANNULER' WHERE `id_rv`= ?";
    private final String SQL_UPDATE_BY_SECRETAIRE="UPDATE `rendezvous` SET `statut`='VALIDER' ,`date`=? ,`medecin_id`=?  WHERE `id_rv`= ?";

    private final String SQL_SELECT_RV_BY_FONCTION_DATE = "SELECT u.id,u.nom_complet FROM rendezvous r,user u WHERE u.fonction= ? "
            + " OR (u.fonction=? AND r.typeMedecin =u.fonction AND r.date NOT LIKE ? AND r.statut NOT LIKE 'VALIDER')";
    private final String SQL_SELECT_RV_BY_MEDECIN="SELECT * FROM rendezvous r,user p WHERE r.patient_id=p.id AND r.medecin_id=? ";
    private final String SQL_SELECT_RV_BY_DATE_BY_MEDECIN="SELECT * FROM rendezvous r,user p WHERE r.patient_id=p.id AND r.medecin_id=? AND r.date=?";
    
    
    private DataBase dataBase = new DataBase();


    @Override
    public int insert(RendezVous rv) {
        int idRv = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT);
            dataBase.getPs().setInt(1, rv.getPatient_id() );
            dataBase.getPs().setString(2, rv.getMotif());
            dataBase.getPs().setString(3, rv.getTypeMedecin());

            dataBase.executeUpdate(SQL_INSERT);
            ResultSet rs =dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idRv = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();   
        } 
        return idRv;
    }
    
    @Override
    public int update(RendezVous rv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int updateByMedecin(RendezVousDTO rv) {
        int nbrLigne=0;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_UPDATE);
        try {
            dataBase.getPs().setInt(1, rv.getId_rv());            
            nbrLigne=dataBase.executeUpdate(SQL_UPDATE);         
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataBase.closeConnexion();
        return nbrLigne;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RendezVous> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RendezVous findById(int id) {
        RendezVous rv = null;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_RV_BY_ID);
        try {
            dataBase.getPs().setInt(1, id);
            ResultSet rs = dataBase.executeSelect(SQL_SELECT_RV_BY_ID);
        
            if(rs.next())
            {
                    rv = new RendezVous(
                    rs.getInt("id_rv"),
                    rs.getString("date"),
                    rs.getInt("patient_id"),
                    rs.getString("statut"),
                    rs.getString("motif"),
                    rs.getString("typeMedecin")
                    );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rv;
    }
    //
    public List<RendezVous> findByPatient(int id) {
        List<RendezVous> rendezVous=new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_RV_BY_PATIENT);
        try {
            dataBase.getPs().setInt(1,id);
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            ResultSet rs =dataBase.executeSelect(SQL_SELECT_RV_BY_PATIENT);
        
        try {
            while(rs.next()){
                try {
                    //Mapping relation vers objet
                    
                    RendezVous rv =new RendezVous(
                            rs.getInt("id_rv"),rs.getString("date"),
                            rs.getInt("patient_id"),
                             rs.getInt("medecin_id"),
                            rs.getString("statut"),
                            rs.getString("motif"),
                             rs.getString("typeMedecin")
                    );
                    rendezVous.add(rv);
                } catch (SQLException ex) {
                    Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return rendezVous;
    }
    //
    public List<RendezVous> findByMotifByPatient(int id,String motif) {
        List<RendezVous> rendezVous=new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_RV_BY_MOTIF_BY_PATIENT);
        try {
            dataBase.getPs().setInt(1,id);
            dataBase.getPs().setString(2,motif);
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            ResultSet rs =dataBase.executeSelect(SQL_SELECT_RV_BY_MOTIF_BY_PATIENT);
        
        try {
            while(rs.next()){
                try {
                    //Mapping relation vers objet
                    RendezVous rv =new RendezVous(rs.getInt("id_rv"),rs.getString("date"),rs.getInt("patient_id"),rs.getString("statut"),rs.getString("motif"));
                    rendezVous.add(rv);
                } catch (SQLException ex) {
                    Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return rendezVous;
    }
    
    public List<RendezVous> findByMotif(String motif) {
        List<RendezVous> rendezVous=new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_RV_BY_MOTIF);
        try {
            dataBase.getPs().setString(1,motif);
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            ResultSet rs =dataBase.executeSelect(SQL_SELECT_RV_BY_MOTIF);
        
        try {
            while(rs.next()){
                try {
                    //Mapping relation vers objet
                    RendezVous rv =new RendezVous(
                            
                             rs.getInt("id_rv"),rs.getString("date"),
                            rs.getInt("patient_id"),
                             rs.getInt("medecin_id"),
                            rs.getString("statut"),
                            rs.getString("motif"),
                             rs.getString("typeMedecin"));
                    rendezVous.add(rv);
                } catch (SQLException ex) {
                    Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return rendezVous;
    }
    //
    public List<RendezVous> findByMedecin(int id) {
        List<RendezVous> rendezVous=new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_RV_BY_Medecin);
        try {
            dataBase.getPs().setInt(1,id);
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            ResultSet rs =dataBase.executeSelect(SQL_SELECT_RV_BY_Medecin);
        
        try {
            while(rs.next()){
                try {
                    //Mapping relation vers objet
                    RendezVous rv =new RendezVous(rs.getInt("id_rv"),rs.getString("date"),
                            rs.getInt("patient_id"),rs.getString("statut"),rs.getString("motif"));
                    rendezVous.add(rv);
                } catch (SQLException ex) {
                    Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return rendezVous;
    }
    
    //
    public List<RendezVous> findBySecretaire() {
        List<RendezVous> rendezVous =new ArrayList<>();
            
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_SELECT_RV_BY_SECRETAIRE);
            
            ResultSet rs =dataBase.executeSelect(SQL_SELECT_RV_BY_SECRETAIRE);
        try {
            
            while(rs.next()){     

                RendezVous rv = new RendezVous(rs.getInt("id_rv"),rs.getString("date"),rs.getInt("patient_id"),rs.getString("statut"),rs.getString("motif"),rs.getString("typeMedecin"));
                
                rendezVous.add(rv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();
        }
        return rendezVous;
 
    }

    public int updateRvBySecretaire(RendezVous rv) {
        int nbrLigne=0;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_UPDATE_BY_SECRETAIRE);
        try {
            dataBase.getPs().setInt(1, rv.getId_rv());
            nbrLigne=dataBase.executeUpdate(SQL_UPDATE_BY_SECRETAIRE);         
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataBase.closeConnexion();
        return nbrLigne;
    }
    
    public HashSet <User> findByFonctionDate(RendezVous rv) {
        //List<User> medecin =new ArrayList<>();
        HashSet<User> medecin = new HashSet<User>();
            //RendezVous rv = null;
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_SELECT_RV_BY_FONCTION_DATE);
            
        try {
            dataBase.getPs().setString(1, rv.getTypeMedecin());
            dataBase.getPs().setString(2, rv.getTypeMedecin());
            dataBase.getPs().setString(3, rv.getDate());
            
            ResultSet rs =dataBase.executeSelect(SQL_SELECT_RV_BY_FONCTION_DATE);
            while(rs.next()){
                
              User  m = new User(rs.getInt("id"),rs.getString("nom_complet"));
                
                medecin.add(m);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();
        }
        return medecin;
 
    }
    
    public List<RendezVousDTO> findAll(int id) {
        List<RendezVousDTO> rendezvous = new ArrayList(); 
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_RV_BY_MEDECIN);
        try {
            dataBase.getPs().setInt(1, id);
            ResultSet rs = dataBase.executeSelect(SQL_SELECT_RV_BY_MEDECIN);
            
            while(rs.next())
            {
                RendezVousDTO rv = new RendezVousDTO();
               
                rv.setDate(rs.getString("date"));
                rv.setPatient_id(rs.getInt("patient_id"));
                rv.setId_rv(rs.getInt("id_rv"));
                rv.setStatut(rs.getString("statut"));
                rv.setMotif(rs.getString("motif"));
                rv.setNom_complet(rs.getString("nom_complet"));
                rv.setCode(rs.getString("code"));
                rv.setCode(rs.getString("login"));
               //
                rendezvous.add(rv);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        
        return rendezvous;  
        
    }
    public List<RendezVousDTO> findAllByDateByMedecin(int id,String date) {
        List<RendezVousDTO> rendezvous = new ArrayList(); 
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_RV_BY_DATE_BY_MEDECIN);
        try {
            dataBase.getPs().setInt(1, id);
            dataBase.getPs().setString(2, date);

            ResultSet rs = dataBase.executeSelect(SQL_SELECT_RV_BY_DATE_BY_MEDECIN);
            
            while(rs.next())
            {
                RendezVousDTO rv = new RendezVousDTO();
               
                rv.setDate(rs.getString("date"));
                rv.setPatient_id(rs.getInt("patient_id"));
                rv.setId_rv(rs.getInt("id_rv"));
                rv.setStatut(rs.getString("statut"));
                rv.setMotif(rs.getString("motif"));
                rv.setNom_complet(rs.getString("nom_complet"));
                rv.setCode(rs.getString("code"));
               //
                rendezvous.add(rv);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        
        return rendezvous;  
        
    }
   // date,patient_id,statut,motif;medecon_id,typeMedecin`=> ?,?,'EN_COURS','PRESTATION',?,?)";
    /*public int insertByMedecin(RendezVous rv) {
        int idRv = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT_RV_BY_MEDECIN);
            dataBase.getPs().setString(1, rv.getDate());
            dataBase.getPs().setInt(2, rv.getPatient_id() );
            dataBase.getPs().setInt(3, rv.getMe );
            dataBase.getPs().setString(4, rv.getTypeMedecin());

            dataBase.executeUpdate(SQL_INSERT_RV_BY_MEDECIN);
            ResultSet rs =dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idRv = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();   
        } 
        return idRv;
    }*/

}
