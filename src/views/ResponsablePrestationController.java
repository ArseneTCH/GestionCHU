/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Dto.ResponsablePrestationDTO;
import Services.Service;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ResponsablePrestationController implements Initializable {

    @FXML
    private TableView<ResponsablePrestationDTO> tblvListPrestation;
    @FXML
    private TableColumn<ResponsablePrestationDTO, String> tblcPrestataire;
    @FXML
    private TableColumn<ResponsablePrestationDTO, String> tblcPatient;
    @FXML
    private TableColumn<ResponsablePrestationDTO, String> tblcDate;
    @FXML
    private JFXDatePicker txtfDate;
    @FXML
    private Text txtName12;
    @FXML
    private Text txtName;
    
    Service service =new Service();
     private ResponsablePrestationDTO PrestationSelected;
    ObservableList<ResponsablePrestationDTO> obvPrestation;
    private List<ResponsablePrestationDTO> listPrestation;
    private int id;
    @FXML
    private Text txtName121;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtName.setText(LoginController.getCtrl().getUser().getNom_complet());
        id=LoginController.getCtrl().getUser().getId();
        
        loadTableView();
        
    }    

    private void loadTableView(){    
        listPrestation = service.searchAllPrestation();
        //String date=txtfDate.getValue().toString();
         listPrestation = service.searchAllPrestation();
        
        obvPrestation=FXCollections.observableArrayList(listPrestation);
       
        //Construction des colonnes
        tblcPrestataire.setCellValueFactory(new PropertyValueFactory<>("medecin"));
        tblcPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("rendezVous"));        
       
        //tblvListPrestation.getSelectionModel().getSelectedItems();
        tblvListPrestation.setItems(obvPrestation); 
        
    }
    @FXML
    private void handleSearchByDate(MouseEvent event) {
        listPrestation = service.searchAllPrestationByDate(txtfDate.getValue().toString());
        obvPrestation=FXCollections.observableArrayList(listPrestation);
       
        //Construction des colonnes
        tblcPrestataire.setCellValueFactory(new PropertyValueFactory<>("medecin"));
        tblcPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("rendezVous"));        
       
        //tblvListPrestation.getSelectionModel().getSelectedItems();
        tblvListPrestation.setItems(obvPrestation);
        
    }

    @FXML
    private void HandleLogOut(MouseEvent event) {
         try {
            this.txtName.getScene().getWindow().hide();
            AnchorPane root = null;
                        
            root = FXMLLoader.load(getClass().getResource("/views/v_login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ResponsablePrestationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleSelctedPrestation(MouseEvent event) {
        PrestationSelected=tblvListPrestation.getSelectionModel().getSelectedItem();

        try {
            Parent parent =FXMLLoader.load(getClass().getResource("v_detailPrestation.fxml"));
            Scene scene =new Scene(parent);
            Stage stage =new Stage() ;
            stage.setScene(scene);
            stage.show();
             PrestationSelected=null;
        } catch (IOException ex) {
            Logger.getLogger(ResponsablePrestationController.class.getName()).log(Level.SEVERE, null, ex);
        } 
       
    }

    @FXML
    private void HandleListAll(MouseEvent event) {
          loadTableView();
    }
   
   
}
