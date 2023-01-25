/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Medecin;
import Entities.Patient;
import Entities.RendezVous;
import Entities.User;
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
public class MedecinDao implements IDao<Medecin>{
    private final String  SQL_SELECT_ALL = "SELECT nom_complet FROM user WHERE role ='ROLE_MEDECIN'";
    private final String  SQL_SELECT_BY_ID = "SELECT id,nom_complet FROM user WHERE id = ?";
    private final DataBase database= new DataBase();

    @Override
    public int insert(Medecin ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Medecin ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medecin> findAll() {
        List<Medecin> Medecins =new ArrayList<>();
        database.openConnexion();
        database.initPrepareStatement(SQL_SELECT_ALL);
       
            ResultSet rs =database.executeSelect(SQL_SELECT_ALL);
        
        try {
            while(rs.next()){
                try {
                    //Mapping relation vers objet
                    Medecin m =new Medecin(rs.getInt("id"),rs.getString("nom_complet"));
                    Medecins.add(m);
                } catch (SQLException ex) {
                    Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return Medecins;
    }
    

    @Override
    public Medecin findById(int id) {
         User user= null;
        database.openConnexion();
        database.initPrepareStatement(SQL_SELECT_BY_ID);
        try {
            database.getPs().setInt(1, id);
            
            ResultSet rs = database.executeSelect(SQL_SELECT_BY_ID);
        
            if(rs.next())
            {
                      user = new Medecin(
                             rs.getInt("id"),
                    rs.getString("nom_complet")
                    );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (Medecin) user;
    
    }
    public Medecin findByIdRv(RendezVous rv) {
         Medecin medecin = null;
            database.openConnexion();
        try {
           
            database.initPrepareStatement(SQL_SELECT_BY_ID);
            
            database.getPs().setInt(1, rv.getId_rv());
            
            ResultSet rs = database.executeSelect(SQL_SELECT_BY_ID);
        
            if(rs.next())
            {
                medecin = new Medecin(rs.getInt("id"),rs.getString("nom_complet"));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return medecin;
    }

    
    /*public List<Medecin> findAllByFonctionDate(String fonction,String date) {
        List<Medecin> Medecins =new ArrayList<>();
        Medecin m =new Medecin();
        database.openConnexion();
        database.initPrepareStatement(SQL_SELECT_ALL);
        try {    
            database.getPs().setString(1,m.getFonction());
            database.getPs().setString(1,m.get);
            //database.getPs().setString(1,m.);
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            ResultSet rs =database.executeSelect(SQL_SELECT_ALL);
        
        try {
            while(rs.next()){
                try {
                    //Mapping relation vers objet
                     m =new Medecin(rs.getInt("id"),rs.getString("nom_complet"));
                    Medecins.add(m);
                } catch (SQLException ex) {
                    Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return Medecins;
    }*/
}
