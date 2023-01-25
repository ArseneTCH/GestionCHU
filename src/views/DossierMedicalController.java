/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Dto.DossierMedicalDTO;
import Entities.DossierMedical;
import Services.Service;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DossierMedicalController implements Initializable {

    @FXML
    private TableView<DossierMedicalDTO> tblvDosMedi;
    
    @FXML
    private TableColumn<DossierMedicalDTO, String> tblcMedecin;
    @FXML
    private TableColumn<DossierMedicalDTO, String> tblcMotif;
    @FXML
    private TableColumn<DossierMedicalDTO, String> tblcDate;
    @FXML
    private JFXTextField lblNomComplet;
    @FXML
    private JFXTextField txtfCode;
    
    Service service =new Service();
    ObservableList<DossierMedicalDTO> obvList;
    private List<DossierMedicalDTO> listDosMedi;
    private int id;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         //loadTableView();
    }    
    private void loadTableView(){   
        String login=txtfCode.getText().trim();
       // lblNomComplet =; 
        
        listDosMedi = service.searchAllDosierMedicauxByLogin(login);
        
        
        obvList=FXCollections.observableArrayList(listDosMedi);
       
        //Construction des colonnes
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcMedecin.setCellValueFactory(new PropertyValueFactory<>("medecin"));
        tblcMotif.setCellValueFactory(new PropertyValueFactory<>("motif"));        
       
        //tblvListPrestation.getSelectionModel().getSelectedItems();
       tblvDosMedi.setItems(obvList); 
        
    }

    @FXML
    private void handleSearchByCode(MouseEvent event) {
        loadTableView();
    }
}
