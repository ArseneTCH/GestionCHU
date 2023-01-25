/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Ordonnance;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class OrdonnanceDao implements IDao<Ordonnance>{

    private final String SQL_INSERT ="INSERT INTO `ordonnance`(`posologie`, `medicament_id`, `consultation_id`) VALUES (?,?,?)";
    private DataBase dataBase = new DataBase();
    @Override
    public int insert(Ordonnance o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public int update(Ordonnance ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ordonnance> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ordonnance findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int insertOrd(String posologie,int medicament_id,int consultation_id) {
         int idO= 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT);
            dataBase.getPs().setString(1, posologie);
            dataBase.getPs().setInt(2, medicament_id );
            dataBase.getPs().setInt(3,consultation_id);

            dataBase.executeUpdate(SQL_INSERT);
            ResultSet rs =dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idO = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OrdonnanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();   
        } 
        return idO;
    }
}
