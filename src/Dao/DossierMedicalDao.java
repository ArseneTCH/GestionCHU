/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.DossierMedical;
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
public class DossierMedicalDao implements IDao<DossierMedical>{

    private final String SQL_INSERT ="INSERT INTO `dosmedi`(`patient_id`, `motif`, `rendezVous_id`) VALUES (?,?,?)";
    private final String SQL_INSERT_CONSULTATION ="INSERT INTO `dosmedi`(`patient_id`,`consultation_id`) VALUES (?,?)";
    private final String SQL_INSERT_PRESTATION ="INSERT INTO `dosmedi`(`patient_id`, `prestation_id`) VALUES (?,?)";
    private final String SQL_SELECT_ALL_BY_CODE = "SELECT * FROM `dosmedi` WHERE `patient_id` = ?";
    private DataBase dataBase = new DataBase();
    
    @Override
    public int insert(DossierMedical dosM) {
        int idD = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT);
            dataBase.getPs().setInt(1, dosM.getPatient_id());
            dataBase.getPs().setString(2, dosM.getMotif());
            dataBase.getPs().setInt(3, dosM.getRendezVous_id());

            dataBase.executeUpdate(SQL_INSERT);
            ResultSet rs =dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idD = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DossierMedicalDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();   
        } 
        return idD;
    }

    @Override
    public int update(DossierMedical ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DossierMedical> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DossierMedical findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   public List<DossierMedical> findAllById(int id){
     List<DossierMedical>ListDM=new ArrayList<>();
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_ALL_BY_CODE);
        try {
            dataBase.getPs().setInt(1,id);
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            ResultSet rs =dataBase.executeSelect(SQL_SELECT_ALL_BY_CODE);
        
        try {
            while(rs.next()){
                try {
                    //Mapping relation vers objet
                   // RendezVous rv =new RendezVous(rs.getInt("id_rv"),rs.getString("date"),rs.getInt("patient_id"),rs.getString("statut"),rs.getString("motif"));
                    //rendezVous.add(rv);
                    DossierMedical dm =new DossierMedical(rs.getInt("patient_id"),rs.getInt("rendezVous_id"),rs.getString("motif"));
                    ListDM.add(dm);
                } catch (SQLException ex) {
                    Logger.getLogger(DossierMedicalDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DossierMedicalDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        return ListDM;
   }
   
}
