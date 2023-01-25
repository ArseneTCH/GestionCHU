/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Patient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class PatientDao implements IDao<Patient>{
    private final String SQL_INSERT = "INSERT INTO `user` "
            + " ( `nom_complet`,`login`,`password`, `role`,  `code`,  `antecedent`) "
            + " VALUES ( ?,?,?, 'ROLE_PATIENT',?,?)";
    
    private final String  SQL_SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ? ";
     private final String  SQL_SELECT_USER_BY_CODE = "SELECT * FROM user WHERE code LIKE ? ";
      private final String  SQL_SELECT_USER_BY_MAIL = "SELECT * FROM user WHERE login LIKE ? ";
    private DataBase dataBase = new DataBase();

    @Override
    public int insert(Patient patient) {
        int idPatient = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT);
            dataBase.getPs().setString(1, patient.getNom_complet() );
            dataBase.getPs().setString(2, patient.getLogin());
            dataBase.getPs().setString(3, patient.getPassword() );
            dataBase.getPs().setString(4, patient.getCode() );
            dataBase.getPs().setString(5, patient.getAntecedent() );
            dataBase.executeUpdate(SQL_INSERT);
            ResultSet rs =dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idPatient = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();   
        } 
        return idPatient;
    }

    @Override
    public int update(Patient ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patient> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Patient findById(int id) {
        Patient patient = null;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_USER_BY_ID);
        try {
            dataBase.getPs().setInt(1, id);
            
            ResultSet rs = dataBase.executeSelect(SQL_SELECT_USER_BY_ID);
        
            if(rs.next())
            {
                    patient = new Patient(
                    rs.getInt("id"),
                    rs.getString("nom_complet"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("code"),
                    rs.getString("antecedent"),
                    rs.getInt("dosMedi_id")
                    
                    //rs.getString("antecedent")
                    );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patient;
    }
    public Patient findByCode(String code) {
        Patient patient = null;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_USER_BY_CODE);
        try {
            dataBase.getPs().setString(1, code);
            
            ResultSet rs = dataBase.executeSelect(SQL_SELECT_USER_BY_CODE);
        
            if(rs.next())
            {
                    patient = new Patient(
                    rs.getInt("id"),
                    rs.getString("nom_complet"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("code"),
                    rs.getString("antecedent"),
                    rs.getInt("dosMedi_id")
                    
                    //rs.getString("antecedent")
                    );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patient;
    }
      public Patient findByMail(String login) {
        Patient patient = null;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_USER_BY_MAIL);
        try {
            dataBase.getPs().setString(1, login);
            
            ResultSet rs = dataBase.executeSelect(SQL_SELECT_USER_BY_MAIL);
        
            if(rs.next())
            {
                    patient = new Patient(
                    rs.getInt("id"),
                    rs.getString("nom_complet"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("code"),
                    rs.getString("antecedent"),
                    rs.getInt("dosMedi_id")
                    
                    //rs.getString("antecedent")
                    );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patient;
    }
    
}
