/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.Patient;
import Entities.RendezVous;
import Entities.User;
import Services.Service;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ValideRendezVousController implements Initializable {

    @FXML
    private JFXTextField txtfCode;
    @FXML
    private JFXTextField txtfNomComplet;
    @FXML
    private JFXTextField txtfMotif;
    @FXML
    private JFXDatePicker txtfDate;
    @FXML
    private JFXTextField txtfFonction;
    @FXML
    private JFXComboBox<User> cboMedecin;
    @FXML
    private JFXTextArea txtfAntecedent;
    
    private final RendezVous rv = SecretaireController.getCtrl().getRv();
    private ObservableList<User> obMedecin;
    private RendezVous RendezVousSelected;
    Service service =new Service();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadRvData();
        loadComboBoxMedecin(cboMedecin);
    }    
    private void loadRvData(){
        //Construction des colonnes
         
         
         Patient p = service.serachPatientById(rv.getPatient_id());
         
         System.out.println(p.getAntecedent());
        txtfCode.setText(p.getCode());
        txtfNomComplet.setText(p.getNom_complet());
        txtfMotif.setText(rv.getMotif());
        txtfAntecedent.setText(p.getAntecedent());
        txtfFonction.setText(rv.getTypeMedecin());
        disableFields(true);       
    }
    public void disableFields(boolean param) {
        
        txtfCode.setDisable(param);
        txtfNomComplet.setDisable(param);
        txtfMotif.setDisable(param);
        txtfFonction.setDisable(param);
        txtfAntecedent.setDisable(param);
    }

    //@SuppressWarnings("empty-statement")
    private void loadComboBoxMedecin(ComboBox<User> cbo)
    {
        if(txtfDate.toString()!=null){
        HashSet <User> SetMedecin =service.searchRvByFonctionDate(rv) ;
        System.out.println(SetMedecin); 
        obMedecin = FXCollections.observableArrayList(service.searchRvByFonctionDate(rv));
        cbo.setItems(obMedecin);
       
        }
    }
    
    @FXML
    private void handleValiderRv(ActionEvent event) {
        service.ValideRv(rv);
        RendezVousSelected =null;
    }

    @FXML
    private void handleAnnulerRv(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Etes vous sure de vouloire annuler ce rendezVous ?",ButtonType.YES, ButtonType.NO);
     
        Optional<ButtonType> result =alert.showAndWait();
        if(result.get() == ButtonType.YES){
            
            service.AnnulerRvBySecretaire(rv);
            this.txtfNomComplet.getScene().getWindow().hide();
            //loadRvData();
        }
    }
}
