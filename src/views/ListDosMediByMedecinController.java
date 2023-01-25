/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Dto.DossierMedicalDTO;
import Services.Service;
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

/**
 * FXML Controller class
 *
 * @author arsen
 */
public class ListDosMediByMedecinController implements Initializable {

   @FXML
    private TableView<DossierMedicalDTO> tblvDosMedi;
    
    @FXML
    private TableColumn<DossierMedicalDTO, String> tblcMedecin;
    @FXML
    private TableColumn<DossierMedicalDTO, String> tblcMotif;
    @FXML
    private TableColumn<DossierMedicalDTO, String> tblcDate;
     Service service =new Service();
    ObservableList<DossierMedicalDTO> obvList;
    private List<DossierMedicalDTO> listDosMedi;
    private int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         loadTableView();
    }    
    
     private void loadTableView(){   
        String login=ListeRendezVousByMedecinController.getCtrl().getRv().getCode();
        System.out.println(login);
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
    
}
