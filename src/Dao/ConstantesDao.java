/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Constantes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class ConstantesDao implements IDao<Constantes>{
    private final String SQL_INSERT="INSERT into `constantes`(`libelle`,`valeur`,`consultation_id`) VALUES(?,?,?)";
    private DataBase dataBase = new DataBase();
    @Override
    public int insert(Constantes c) {
        int idConst = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT);
            dataBase.getPs().setString(1, c.getLibelle() );
            dataBase.getPs().setString(2, c.getValeur());
            dataBase.getPs().setInt(3, c.getConsultation_id());

            dataBase.executeUpdate(SQL_INSERT);
            ResultSet rs =dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idConst = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConstantesDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();   
        } 
        return idConst;
    }

    @Override
    public int update(Constantes ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Constantes> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Constantes findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
