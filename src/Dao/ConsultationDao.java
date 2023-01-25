/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Consultation;
import Entities.RendezVous;
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
public class ConsultationDao implements IDao<Consultation> {
    private final String SQL_SELECT_CONSULTATION_BY_PATIENT = "SELECT * FROM  `rendezvous` WHERE `patient_id`= ? AND `statut` LIKE 'VALIDER' AND `motif` LIKE 'CONSULTATION' ";
    private final String SQL_INSERT_BY_PATIENT ="INSERT INTO `rendezvous`(`patient_id`, `statut`, `motif`,`typeMedecin`) VALUES (?,'EN_COURS',?,?)";
    private final String SQL_UPDATE="UPDATE `rendezvous` SET `ordonnance_id`=?,`constante_id`=?,`constante_id`=`VALIDE` WHERE `id_rv`= ?";
    private DataBase dataBase = new DataBase();

    @Override
    public int insert(Consultation c) {
        int idC = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT_BY_PATIENT);
            dataBase.getPs().setInt(1, c.getPatient_id() );
            dataBase.getPs().setString(2, c.getMotif());
             dataBase.getPs().setString(3,c.getTypeMedecin());
            

            dataBase.executeUpdate(SQL_INSERT_BY_PATIENT);
            ResultSet rs =dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idC = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();   
        } 
        return idC;
    }

    @Override
    public int update(Consultation c) {
       int nbrLigne=0;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_UPDATE);
        try {
            dataBase.getPs().setInt(1, c.getOrdonnance_id()); 
             dataBase.getPs().setInt(2, c.getConstante_id()); 
              dataBase.getPs().setInt(3, c.getId_rv()); 
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
    public List<Consultation> findAll() {
        List<Consultation> consultations =new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_CONSULTATION_BY_PATIENT);
        
       
            ResultSet rs =dataBase.executeSelect(SQL_SELECT_CONSULTATION_BY_PATIENT);
        
        try {
            while(rs.next()){
                try {
                    //Mapping relation vers objet
                    Consultation c =new Consultation(rs.getInt("medecin_id"), rs.getInt("id_rv"), rs.getString("date"), rs.getInt("patient_id"), rs.getString("statut"),rs.getString("motif"));
                    consultations.add(c);
                } catch (SQLException ex) {
                    Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        
        return consultations;
    }

    @Override
    public Consultation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*
    public List<RendezVous> findByPatient(int id) {
        
        List<RendezVous> consultations =new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_CONSULTATION_BY_PATIENT);
        try {
            dataBase.getPs().setInt(1,id);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            ResultSet rs =dataBase.executeSelect(SQL_SELECT_CONSULTATION_BY_PATIENT);
        
        try {
            while(rs.next()){
               
                    //Mapping relation vers objet
                    RendezVous c =new RendezVous();
                    
                    consultations.add(c);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return consultations;
    }*/
    
}
