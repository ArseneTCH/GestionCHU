/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.RendezVous;
import Services.Service;
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
public class SecretaireController implements Initializable {
    Service service =new Service();
    ObservableList<RendezVous> obvRv;
    private RendezVous RendezVousSelected;
    private static SecretaireController ctrl;

    @FXML
    private TableView<RendezVous> tblvListRV;
    @FXML
    private TableColumn<RendezVous, String> tblcPatient;
    @FXML
    private TableColumn<RendezVous, String> tblcMotif;
    @FXML
    private TableColumn<RendezVous, String> tblcFonction;
    @FXML
    private Text txtName;
    @FXML
    private Text txtLogOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableView();
        ctrl = this;
    }

    private void loadTableView(){
        List<RendezVous> rendezVous = service.searchRvBySecretaire();
        obvRv=FXCollections.observableArrayList(rendezVous);
        txtName.setText(LoginController.getCtrl().getUser().getNom_complet());
        
        //Construction des colonnes
        tblcPatient.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        tblcMotif.setCellValueFactory(new PropertyValueFactory<>("motif"));
        tblcFonction.setCellValueFactory(new PropertyValueFactory<>("typeMedecin"));
        tblvListRV.getSelectionModel().getSelectedItems();
        
        tblvListRV.setItems(obvRv);
        
        
        
       
    }

    @FXML
    private void handleSelectedRv(MouseEvent event) {
        RendezVousSelected = tblvListRV.getSelectionModel().getSelectedItem();
        

        //System.out.println(RendezVousSelected);
        if(RendezVousSelected!=null){
            try {
            
            Parent parent =FXMLLoader.load(getClass().getResource("v_valideRendezVous.fxml"));
            Scene scene =new Scene(parent);
            Stage stage =new Stage() ;
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
            Logger.getLogger(SecretaireController.class.getName()).log(Level.SEVERE, null, ex);

            }
            
        }
        
    }
    public static SecretaireController getCtrl() {
        return ctrl;
    }
    public RendezVous getRv(){
     return RendezVousSelected;
    }

    
    @FXML
    private void handleLogOut(MouseEvent event) {
        try {
            this.txtName.getScene().getWindow().hide();
            AnchorPane root = null;
            
            
            root = FXMLLoader.load(getClass().getResource("/views/v_login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SecretaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }
}
