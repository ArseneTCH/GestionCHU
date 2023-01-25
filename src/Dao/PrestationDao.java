/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Prestation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class PrestationDao implements IDao<Prestation>{

    private final String SQL_SELECT_PRESTATION_BY_PATIENT = "SELECT * FROM  `rendezvous` WHERE `patient_id`= ? AND `statut` LIKE 'VALIDER' AND `motif` NOT LIKE 'CONSULTATION' ";
   private final String SQL_INSERT_BY_PATIENT ="INSERT INTO `rendezvous`(`patient_id`, `statut`, `motif`,`typeMedecin`) VALUES (?,'EN_COURS',?,?)";
    private final String SQL_INSERT_RV_BY_MEDECIN ="INSERT INTO `rendezvous`(`date`,`patient_id`, `statut`, `motif`)"
            + "  VALUES (?,?,'EN_COURS',?)";
    private final String SQL_ALL = "SELECT * FROM  `rendezvous`";
    private final String SQL_ALL_BY_DATE = "SELECT * FROM  `rendezvous` WHERE `date`= ?";
    private DataBase dataBase = new DataBase();
    @Override
    public int insert(Prestation p) {
        int idp = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT_BY_PATIENT);
            dataBase.getPs().setInt(1, p.getPatient_id() );
            dataBase.getPs().setString(2, p.getMotif());
            dataBase.getPs().setString(3, p.getTypeMedecin());

            dataBase.executeUpdate(SQL_INSERT_BY_PATIENT);
            ResultSet rs =dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idp = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();   
        } 
        return idp;
        
    }

    @Override
    public int update(Prestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestation> findAll() {
        List<Prestation> prestations =new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_PRESTATION_BY_PATIENT);
       
            ResultSet rs =dataBase.executeSelect(SQL_SELECT_PRESTATION_BY_PATIENT);
        
        try {
            while(rs.next()){
                try {
                    //Mapping relation vers objet
                    Prestation p =new Prestation(rs.getString("date"),rs.getInt("patient_id"),rs.getString("statut"), rs.getString("motif"));
                    prestations.add(p);
                } catch (SQLException ex) {
                    Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return prestations;
    }

    @Override
    public Prestation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public List<Prestation> findAllByRP() {
        List<Prestation> prestations =new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_ALL);
       
            ResultSet rs =dataBase.executeSelect(SQL_ALL);
        
        try {
            while(rs.next()){
                
                    //Mapping relation vers objet
                   // int id_rv, String date, int patient_id, String statut, String motif, String typeMedecin
                    Prestation p =new Prestation(
                            rs.getInt("id_rv"),
                            rs.getString("date") ,
                            rs.getInt("patient_id"),
                            rs.getString("statut"),
                            rs.getString("motif"),
                            rs.getString("typeMedecin")
                            
                    );
                    prestations.add(p);
    
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return prestations;
    }
     
     public List<Prestation> findAllByDateByRP(String Date) {
        List<Prestation> prestations =new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_ALL_BY_DATE);
        try {
            dataBase.getPs().setString(1, Date);
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            ResultSet rs =dataBase.executeSelect(SQL_ALL_BY_DATE);
        
        try {
            while(rs.next()){
                
                    //Mapping relation vers objet
                    Prestation p =new Prestation(rs.getString("date") ,rs.getInt("patient_id") );
                    prestations.add(p);
    
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return prestations;
    }
    /*
    public List<Prestation> findByPatient(int id) {
        List<Prestation> prestations =new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_PRESTATION_BY_PATIENT);
       try {
            dataBase.getPs().setInt(1,id);
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            ResultSet rs =dataBase.executeSelect(SQL_SELECT_PRESTATION_BY_PATIENT);
        
        try {
            while(rs.next()){
                try {
                    Prestation p1 =new Prestation(){
                    
                    };
                    //Mapping relation vers objet
                    Prestation p =new Prestation(rs.getString("motif"),rs.getString("date"),rs.getInt("patient_id"),rs.getString("statut"), rs.getString("motif"));
                    prestations.add(p);
                } catch (SQLException ex) {
                    Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return prestations;
    }*/
     // date,patient_id,statut,motif;medecon_id,typeMedecin`=> ?,?,'EN_COURS','PRESTATION',?,?)";
    public int insertByMedecin(Prestation rv) {
        int idRv = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT_RV_BY_MEDECIN);
            dataBase.getPs().setString(1, rv.getDate());
            dataBase.getPs().setInt(2, rv.getPatient_id() );
            dataBase.getPs().setString(3, rv.getMotif());

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
    }
}
