/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Medicament;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class MedicamentDao implements IDao<Medicament>{
    private final String SQL_INSERT ="INSERT INTO `medicament`(`code`, `nom`) VALUES (?,?)";
    private final String  SQL_SELECT_BY_CODE = "SELECT * FROM medicament WHERE code = ? ";
    private DataBase dataBase = new DataBase();
    @Override
    public int insert(Medicament m) {
        int idM = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT);
            dataBase.getPs().setString(1, m.getCode());
            dataBase.getPs().setString(2, m.getNom());

            dataBase.executeUpdate(SQL_INSERT);
            ResultSet rs =dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idM = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MedicamentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();   
        } 
        return idM;
    }

    @Override
    public int update(Medicament ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medicament> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Medicament findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Medicament findByCode(String code) {
        Medicament medoc = null;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_BY_CODE);
        try {
            dataBase.getPs().setString(1, code);
            ResultSet rs = dataBase.executeSelect(SQL_SELECT_BY_CODE);
        
            if(rs.next())
            {
                    medoc = new Medicament(
                    rs.getInt("id_medicament"),
                    rs.getString("code"),
                    rs.getString("nom")
                    );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medoc;
    }
    
}
