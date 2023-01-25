/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.Prestation;
import Entities.RendezVous;
import Services.Service;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.ViewService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class PatientController implements Initializable {
    Service service =new Service();
    ObservableList<RendezVous> obvRv;
    private RendezVous RendezVousSelected;
   private List<RendezVous> rendezVous;
   private int id;
   private String filtre;
   private String typeMedecin;
   private String motif ;
   
    @FXML
    private AnchorPane anchorContent;
    @FXML
    private JFXComboBox<String> cboMedecin;
    @FXML
    private JFXComboBox<String> cboMotif;
    @FXML
    private Text txtName;
    @FXML
    private TableView<RendezVous> tblvList;
    @FXML
    private TableColumn<RendezVous, String> tblcMotif;
    @FXML
    private TableColumn<RendezVous, String> tblcDate;
    @FXML
    private TableColumn<RendezVous, String> tblcMedecin;
    @FXML
    private TableColumn<RendezVous, String> tblcStatut;
    @FXML
    private JFXComboBox<String> cboFilter;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        motif = cboMotif.getSelectionModel().getSelectedItem();
        if(motif =="CONSULTATION"){
            cboMedecin.setDisable(true);
        }
        typeMedecin = cboMedecin.getSelectionModel().getSelectedItem();
        
        txtName.setText(LoginController.getCtrl().getUser().getNom_complet());
        id=LoginController.getCtrl().getUser().getId();
        loadTableView();
        ViewService.loadComboBoxMotif(cboMotif);
        ViewService.loadComboBoxTypeMedecin(cboMedecin);
        ViewService.loadComboBoxFilterRendezVous(cboFilter);  
    }    
    
    private void loadView(String view) throws IOException{
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(root);
    }
    


    @FXML
    private void HandleSubmitRv(ActionEvent event) {
        //int id =LoginController.getCtrl().getUser().getId();
        String motif = cboMotif.getSelectionModel().getSelectedItem();
        //if(motif==null || motif.compareTo("CONSULTATION")!=0){
            
            String typeMedecin = cboMedecin.getSelectionModel().getSelectedItem();
          //  Prestation p=new Prestation(id,motif,typeMedecin);
       // }else{
         //   cboMedecin.disabledProperty();
        //}

        
        RendezVous rv;
        rv = new RendezVous(id,motif,typeMedecin);
        
        service.AddRvByPatient(rv);
        obvRv.add(rv);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("RendezVous");
        alert.setContentText("Demande reussie ");
        alert.show();
    }


    @FXML
    private void HandleSettings(MouseEvent event) {
    }


    private void loadTableView(){
        rendezVous = service.filterRvByPatient(id, filtre);
        obvRv=FXCollections.observableArrayList(rendezVous);
        //Construction des colonnes
        tblcMotif.setCellValueFactory(new PropertyValueFactory<>("motif"));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcMedecin.setCellValueFactory(new PropertyValueFactory<>("medecin_id"));
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        
        tblvList.setItems(obvRv);
    }
    
    @FXML
    private void HandleListeRendezVous(MouseEvent event) {
        filtre=null;
        loadTableView();
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
            Logger.getLogger(PatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void HandleFilterRendezVous(ActionEvent event) {
        filtre=cboFilter.getSelectionModel().getSelectedItem();
        loadTableView();
    }
    
}
